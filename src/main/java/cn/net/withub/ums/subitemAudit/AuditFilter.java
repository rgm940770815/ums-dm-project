package cn.net.withub.ums.subitemAudit;

import cn.net.withub.ums.entity.UmsLevelInfo;
import cn.net.withub.ums.entity.UmsSubitemRule;
import cn.net.withub.ums.util.StringTools;
import cn.net.withub.ums.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

public class AuditFilter extends AbstractInterceptor {

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {

        boolean canInvoke = true;
        // 如果被拦截要返回拦截信息
        Map<String, Object> filterMap = new HashMap<>();
        try {

            String methodName = invocation.getInvocationContext().getName();
            // 只拦截 save 方法
            if (methodName.equalsIgnoreCase("save")) {
                // 获取到bean
                ApplicationContext ac = (ApplicationContext) invocation.getInvocationContext().getApplication().get(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
                SubItemAuditRepository repository = ac.getBean(SubItemAuditRepository.class);

                // 这个parameter是什么：是前端传来的参数名称
                Map<String, Object> parameters = invocation.getInvocationContext().getParameters();
                String[] t1 = (String[]) parameters.get("t");
                String t = t1[0];
                if (StringUtils.isEmpty(t)) {
                    return null;
                }
                // 获取到拦截的表名
                String simpleClassName = "Ums" + Character.toUpperCase(t.charAt(0)) + t.substring(1);

                // 看配置是否拦截
                UmsSubitemRule ruleByKey = repository.getRuleByKey(simpleClassName);
                // 没配置不拦截 直接放过
                if (ruleByKey == null) {
                    //直接执行
                    invocation.invoke();
                    return null;
                }

                Map<String, Object> entityMap = new HashMap<>();
                for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                    String key = entry.getKey();
                    String[] value = (String[]) entry.getValue();
                    if (key.startsWith("p.") && StringUtils.isNotEmpty(value[0])) {
                        entityMap.put(entry.getKey().replace("p.", ""), value[0]);
                    }
                }

                // 反射找到实体类
                String fullClassName = "cn.net.withub.ums.entity." + simpleClassName;
                Class<?> table = Class.forName(fullClassName);
                Object entity = table.newInstance();
                ConvertUtils.register(new Converter() {
                    public Object convert(Class type, Object value) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        if (value != null && StringUtils.isNotEmpty(value.toString())) {
                            try {
                                return simpleDateFormat.parse(value.toString());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        return null;
                    }
                }, Date.class);
                // 设置属性
                BeanUtils.populate(entity, entityMap);
                // 查看是否设置了拦截条件 注意如果没得过滤条件
                // 没有配置默认是全部拦截
                boolean skipFilter = skipFilter(ruleByKey, entity);
                if (skipFilter) {
                    //直接执行
                    invocation.invoke();
                    return null;
                }

                // 看有没有id
                String[] ids = (String[]) parameters.get("id");
                String id = ids[0];
                if (StringUtils.isNotEmpty(id)) {
                    // id 不为空是新增
                    canInvoke = insertFilterRule(ruleByKey, entity, repository, id, filterMap);
                } else {
                    // id 为空是更新 更新获取到拦截规则 符合规则才拦截
                    // 更新拦截
                    canInvoke = updateFilterRule(ruleByKey, entity, repository, filterMap);
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        if (canInvoke) {
            invocation.invoke();
        } else {

            filterMap.put("success", false);
            invocation.getInvocationContext().put("result", filterMap);
            return "subItemRes";
        }


        return null;
    }

    //参数拦截规则
    private boolean skipFilter(UmsSubitemRule ruleByKey, Object entity) {
        String filterRule = ruleByKey.getFilterRule();
        boolean skipFilter = false;
        // 有拦截条件要满足拦截条件才拦截
        if (StringUtils.isNotEmpty(filterRule)) {
            try {
                skipFilter = true;
                Map<String, List<String>> parameterRule = JSON.parseObject(filterRule.trim(), new TypeReference<Map<String, List<String>>>() {
                });
                // 第一步 传参本身条件
                // 满足过滤条件
                boolean flag = true;
                for (String key : parameterRule.keySet()) {
                    List<String> strings = parameterRule.get(key);
                    Field field = entity.getClass().getDeclaredField(key);
                    field.setAccessible(true);
                    Object o = field.get(entity);
                    if (strings != null && strings.size() == 0 && o != null) {
                        // 如果没有配置那么就是只要有值就算满足
                        flag = true;
                    } else if (o != null && strings != null && strings.contains(o.toString())) {
                        // 值满足条件
                        flag = true;
                    } else {
                        // 不满足直接跳过
                        flag = false;
                        break;
                    }
                }
                skipFilter = !flag;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return skipFilter;
    }

    /**
     * 新增：判断该条信息是否需要插入
     *
     * @param ruleByKey  数据库中配置的审核条件信息
     * @param entity     前端传来的参数赋值给的实体类
     * @param repository 保存审核信息的类
     * @param id         id 不为空是新增
     * @param filterMap  返回到前台的提示信息，例如“值的变更需要进行审核才能更新数据”；
     * @return
     */
    private boolean insertFilterRule(UmsSubitemRule ruleByKey, Object entity, SubItemAuditRepository repository, String id, Map<String, Object> filterMap) {

        boolean canInvoke = true;
        // 满足过滤条件
        boolean flag = true;
        // 具体判断是那种操作类型
        String czlx = "";
        try {
            StringBuilder str = new StringBuilder("");

            // 这里先判断 是否入额，退额时间、退额原因；如果判断后得出结论是需要审核，那么就跳过根据数据库中规则判断的逻辑，否则进入该逻辑；
            String entityClassName = entity.getClass().getSimpleName();
            if ("UmsLevelInfo".equals(entityClassName)) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

                // 将object类型的entity 转为 UmsLevelInfo类型
                UmsLevelInfo qtUmsLevelInfo = (UmsLevelInfo) entity;

                String isYefg = qtUmsLevelInfo.getIsYefg() == null ? "" : qtUmsLevelInfo.getIsYefg();
                String yefgEndTime = qtUmsLevelInfo.getYefgEndTime() == null ? "" : simpleDateFormat.format(qtUmsLevelInfo.getYefgEndTime());
                String yefgEndReason = qtUmsLevelInfo.getYefgEndReason() == null ? "" : qtUmsLevelInfo.getYefgEndReason();
                // 是否需要审批
                boolean sfxysp = false;
                if (StringUtils.isNotEmpty(isYefg) || StringUtils.isNotEmpty(yefgEndTime) || StringUtils.isNotEmpty(yefgEndReason)) {
                    sfxysp = true;
                }

                // 具体判断是那种操作类型
                if (sfxysp) {
                    // 因为是否入额，前台限制了，是必传，所以这里省略判断为空的情况
                    if ("1".equals(isYefg)) {
                        czlx = "申请入额";
                    } else {
                        if (StringUtils.isNotEmpty(yefgEndTime) || StringUtils.isNotEmpty(yefgEndReason)) {
                            czlx = "申请退额";
                        } else {
                            czlx = "修改入额信息为：否";
                        }
                    }
                }

                // 赋值
                flag = sfxysp;
                str = new StringBuilder(czlx);
            }

            // 根据上面针对ums_level_info表的特殊逻辑，不需要审批，那么就根据数据库中的拦截规则，再次判断是否需要审批
            if (!flag) {
                // 新增过滤条件，涉及到的新增操作中配置的过滤字段和条件
                Map<String, List<String>> insertRules = JSON.parseObject(ruleByKey.getInsertRule().trim(), new TypeReference<Map<String, List<String>>>() {});

                for (String key : insertRules.keySet()) {
                    // 数据库中配置的新增规则{"isYefg":["1"],"yefgEndTime":["1"]} // rules = 1
                    // rules 代表数据库中配置的该字段的值，如果数据库中配置的值包含新传入的值，那就要判断是否需要审核
                    List<String> rules = insertRules.get(key);

                    // 根据数据库中配置的字段，获取是实体类中的字段
                    Field field = entity.getClass().getDeclaredField(key);
                    field.setAccessible(true);

                    // 实体类中字段对应的数据
                    Object o = field.get(entity);
                    String newValue = objToString(o);

                    if (rules != null && rules.size() > 0) {
                        if (rules.contains(newValue)) {
                            // 符合条件 情况就复杂了 看旧值到新值是否是有变化的
                            // 获取userid
                            String userId = repository.getValueByReflect(entity, "userId");
                            if (StringUtils.isEmpty(userId)) {
                                // 先算为不符合条件
                                flag = false;
                                break;
                            }
                            // 查询这次新增前的当前信息
                            Map<String, Object> presentDataInfo = repository.presentDataInfo(ruleByKey.getTableKey(), userId);

                            // 空集合认为有变化
                            if (presentDataInfo == null) {
                                flag = true;
                                str.append(getFieldDescribe(field)).append(" ");
                                break;
                            }

                            // 集合不为空要看值是否有变化
                            Object old = presentDataInfo.get(StringTools.camelOrPascalToUnderline(field.getName()));
                            String oldValue = objToString(old);

                            // 这里需要修改一下：新增时，只要是否入额不为空或（退额时间不为空 || 退额原因不为空），就需要审核
                            if (oldValue.equals(newValue)) {
                                // 相同的值 不用拦截
                                flag = false;
                            } else {
                                // 不同的值要拦截  目前只要一个满足就要拦截
                                flag = true;
                                str.append(getFieldDescribe(field)).append(" ");
                                break;
                            }
                        } else {
                            // 不满足直接跳过
                            flag = false;
                        }
                    } else {
                        // 没有配置全部拦截
                        flag = true;
                        str.append(getFieldDescribe(field)).append(" ");
                        break;
                    }
                }
            }

            // 返回前端的额提示信息
            if (StringUtils.isNotEmpty(str.toString())) {
                filterMap.put("msg", "新增操作中 " + str.toString() + " 值的变更需要进行审核才能更新数据,已经提交审核信息");
            }

            // 不能执行 保存申请记录
            if (flag) {
                // id 不为空是新增
                BeanUtils.setProperty(entity, "id", id);
                //保存申请记录
                boolean b = repository.saveAuditInfo(ruleByKey.getTableKey(), ruleByKey.getTableText(), ExamineEnum.INSERT, entity, null, czlx);
                //如果保存错误 使用默认的提示信息
                if (!b) {
                    filterMap.remove("mgs");
                }
            }
            canInvoke = !flag;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return canInvoke;
    }

    /**
     * 修改：判断该条信息是否需要插入
     *
     * @param ruleByKey  数据库中配置的审核条件信息
     * @param entity     前端传来的参数赋值给的实体类
     * @param repository 保存审核信息的类
     * @param filterMap  返回到前台的提示信息，例如“值的变更需要进行审核才能更新数据”；
     * @return
     */
    private boolean updateFilterRule(UmsSubitemRule ruleByKey, Object entity, SubItemAuditRepository repository, Map<String, Object> filterMap) {

        // 是否继续执行方法
        boolean canInvoke = true;
        // 返回前端的额提示信息
        StringBuilder str = new StringBuilder("");
        // 修改前的该条数据
        Map<String, Object> oldDataInfo = null;
        // 操作类型
        String czlx = "";
        try {
            // 这里先判断 是否入额，退额时间、退额原因；如果判断后得出结论是需要审核，那么就跳过根据数据库中规则判断的逻辑，否则进入该逻辑；
            String entityClassName = entity.getClass().getSimpleName();
            if ("UmsLevelInfo".equals(entityClassName)) {
                // 将object类型的entity 转为 UmsLevelInfo类型
                UmsLevelInfo qtUmsLevelInfo = (UmsLevelInfo) entity;

                // 前台出来的是否是当前信息的参数
                String nPresentInfoStr = repository.getValueByReflect(entity, "nPresentInfo");
                String idStr = repository.getValueByReflect(entity, "id");
                String userIdStr = repository.getValueByReflect(entity, "userId");

                // 查询该条数据修改前的数据
                oldDataInfo = repository.oldDataInfo(ruleByKey.getTableKey(), idStr);

                // 查询这次新增前的当前信息
                Map<String, Object> presentDataInfo = repository.presentDataInfo("UmsLevelInfo", userIdStr);


                Map<String, Object> map = getReOrTeSpzt(qtUmsLevelInfo, oldDataInfo);
                boolean sfxysp = (boolean) map.get("sfxysp");
                czlx = (String) map.get("czlx");

                // 前台传来的参数：是当前信息，不是当前信息
                if ("1".equals(nPresentInfoStr)) {
                    if (!sfxysp) {
                        map = getReOrTeSpzt(qtUmsLevelInfo, presentDataInfo);
                        sfxysp = (boolean) map.get("sfxysp");
                        czlx = (String) map.get("czlx");
                    }
                }

                // 赋值
                canInvoke = !sfxysp;
                str = new StringBuilder(czlx);
            }

            // 根据上面特殊逻辑，不需要审批，那么就根据数据库中的拦截规则，再次判断是否需要审批
            if (canInvoke) {
                // 数据库中配置的拦截规则
                String updateRule = ruleByKey.getUpdateRule();
                if (StringUtils.isNotEmpty(updateRule)) {
                    Map<String, List<String>> rule = JSON.parseObject(updateRule.trim(), new TypeReference<Map<String, List<String>>>() {
                    });
                    Set<String> keys = rule.keySet();
                    // 如果数据库中的拦截规则配置了ALL，那么拦截全部
                    if (keys.contains("ALL")) {
                        canInvoke = false;
                    }
                    if (canInvoke) {
                        // 进一步细致判断
                        for (String key : rule.keySet()) {
                            // key：数据库中配置的字段
                            // 反射找到对应字段
                            Field field = null;
                            try {
                                field = entity.getClass().getDeclaredField(key);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (field != null) {
                                // 将该字段设置为可访问
                                field.setAccessible(true);

                                // 规则
                                List<String> rules = rule.get(key);
                                if (rules != null && rules.size() > 0) {
                                    // 传来的参数中，该字段的值
                                    Object o = field.get(entity);
                                    String newValue = objToString(o);

                                    // 数据中配置的该字段的值 是否包含 前台传来的该参数的值
                                    if (rules.contains(newValue)) {
                                        // 这个就复杂了 还要拿到原先的值看是不是变动
                                        // 有特殊规则 看值是否符合特殊规则
                                        String oldId = repository.getValueByReflect(entity, "id");
                                        if (StringUtils.isEmpty(oldId)) {
                                            // 没得id更新怎么玩
                                            canInvoke = false;
                                            throw new Exception("empty id");
                                        }
                                        // 查询该条数据修改前的数据
                                        oldDataInfo = repository.oldDataInfo(ruleByKey.getTableKey(), oldId);
                                        // 该字段修改前的值
                                        Object old = oldDataInfo.get(StringTools.camelOrPascalToUnderline(field.getName()));
                                        String oldValue = objToString(old);

                                        if (oldValue.equals(newValue)) {
                                            // 相同的值 不用拦截
                                            canInvoke = true;
                                        } else {
                                            // 不同的值要拦截  目前只要一个满足就要拦截
                                            canInvoke = false;
                                            str.append(getFieldDescribe(field)).append(" ");
                                            break;
                                        }
                                    }
                                } else {
                                    // 没有特殊规则只要有字段就拦截
                                    canInvoke = false;
                                    str.append(getFieldDescribe(field)).append(" ");
                                    break;
                                }

                            }
                        }
                    }


                }
            }


            // 返回前端的额提示信息
            if (StringUtils.isNotEmpty(str.toString())) {
                filterMap.put("msg", "修改操作中 " + str.toString() + " 值的变更需要进行审核才能更新数据,已经提交审核信息");
            }

            // 不能执行 保存申请记录
            if (!canInvoke) {
                boolean b = repository.saveAuditInfo(ruleByKey.getTableKey(), ruleByKey.getTableText(), ExamineEnum.UPDATE, entity, oldDataInfo, czlx);
                if (!b) {
                    filterMap.remove("mgs");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return canInvoke;
    }

    private String objToString(Object obj) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String res = "";
        try {
            if (obj != null) {
                if (obj instanceof Date) {
                    res = sdf.format(obj);
                } else {
                    res = obj.toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    private String getFieldDescribe(Field field) {
        FieldDescribe annotation = field.getAnnotation(FieldDescribe.class);
        if (annotation != null) {
            return annotation.value();
        }
        return "";
    }

    /**
     * getReOrTeSpzt：获取入额或退额审批状态
     * 判断前台传来的数据，与 传入该方法的数据中的字段是否相同，并得出是入额、退额原因、或是退额时间修改三种审批状态
     *
     * @param qtUmsLevelInfo   前台传来的数据
     * @param xybjUmsLevelInfo 需要比较的数据（该条数据修改前的数据，或者该人员的法官信息的当前数据）
     * @return
     */
    public Map<String, Object> getReOrTeSpzt(UmsLevelInfo qtUmsLevelInfo, Map<String, Object> xybjUmsLevelInfo) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // 前台传来的参数： 是否入额 退出员额时间 退出员额原因
        String qtUmsLevelInfoIsYefg = qtUmsLevelInfo.getIsYefg() == null ? "" : qtUmsLevelInfo.getIsYefg();
        String qtUmsLevelInfoYefgEndTime = qtUmsLevelInfo.getYefgEndTime() == null ? "" : simpleDateFormat.format(qtUmsLevelInfo.getYefgEndTime());
        String qtUmsLevelInfoYefgEndReason = qtUmsLevelInfo.getYefgEndReason() == null ? "" : qtUmsLevelInfo.getYefgEndReason();

        // 需要比较的参数（该条数据修改前的数据，或者该人员的法官信息的当前数据）： 是否入额 退出员额时间 退出员额原因
        String xybjUmsLevelInfoIsYefg = xybjUmsLevelInfo.get("is_yefg") == null ? "" : xybjUmsLevelInfo.get("is_yefg").toString();
        String xybjUmsLevelInfoYefgEndTime = xybjUmsLevelInfo.get("yefg_end_time") == null ? "" : simpleDateFormat.format(xybjUmsLevelInfo.get("yefg_end_time"));
        String xybjUmsLevelInfoYefgEndReason = xybjUmsLevelInfo.get("yefg_end_reason") == null ? "" : xybjUmsLevelInfo.get("yefg_end_reason").toString();

        // 操作类型
        String czlx = "";
        // 是否需要审批
        boolean sfxysp = false;
        // 只要三个字段中的值有变化，就需要进行审批
        if (!qtUmsLevelInfoIsYefg.equals(xybjUmsLevelInfoIsYefg) || !qtUmsLevelInfoYefgEndTime.equals(xybjUmsLevelInfoYefgEndTime) || !qtUmsLevelInfoYefgEndReason.equals(xybjUmsLevelInfoYefgEndReason)) {
            sfxysp = true;
        }

        if (sfxysp) {
            if (qtUmsLevelInfoIsYefg.equals(xybjUmsLevelInfoIsYefg) && "1".equals(qtUmsLevelInfoIsYefg)) {
                if (StringUtils.isNotEmpty(qtUmsLevelInfoYefgEndTime) || StringUtils.isNotEmpty(qtUmsLevelInfoYefgEndReason)) {
                    // 这种情况，前台限制了。是否入额 = 是，退额时间或退额原因 只能是空
                } else {
                    czlx = "";
                }
            } else if (qtUmsLevelInfoIsYefg.equals(xybjUmsLevelInfoIsYefg) && "2".equals(qtUmsLevelInfoIsYefg)) {
                if (StringUtils.isNotEmpty(qtUmsLevelInfoYefgEndTime) || StringUtils.isNotEmpty(qtUmsLevelInfoYefgEndReason)) {
                    // 退额时间或退额原因 不为空
                    if (StringUtils.isEmpty(xybjUmsLevelInfoYefgEndTime) && StringUtils.isEmpty(xybjUmsLevelInfoYefgEndReason)) {
                        // 如果本身是空，前台传来的退额时间和退额原因不为空，就代表是申请退额
                        czlx = "申请退额";
                    } else {
                        czlx = "修改退额信息";
                    }
                } else {
                    // 退额时间或退额原因 为空
                    czlx = "";
                }
            } else {
                // 前台传来的是否员额 与 数据库中的不同
                if ("1".equals(qtUmsLevelInfoIsYefg)) {
                    czlx = "申请入额";
                } else if ("2".equals(qtUmsLevelInfoIsYefg)) {
                    if (StringUtils.isNotEmpty(qtUmsLevelInfoYefgEndTime) || StringUtils.isNotEmpty(qtUmsLevelInfoYefgEndReason)) {
                        // 前台退额时间或退额原因 不为空
                        if (StringUtils.isEmpty(xybjUmsLevelInfoYefgEndTime) && StringUtils.isEmpty(xybjUmsLevelInfoYefgEndReason)) {
                            // 如果本身是空
                            czlx = "申请退额";
                        } else {
                            // 如果本身不是空
                            czlx = "修改退额信息";
                        }
                    } else {
                        // 前台退额时间或退额原因 为空
                        czlx = "修改入额信息为否";
                    }
                }
            }
        }

        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("sfxysp", sfxysp);
        returnMap.put("czlx", czlx);
        return returnMap;
    }
}
