package cn.net.withub.ums.action.config;


import cn.net.withub.ums.action.config.verify.AuthUtils;
import cn.net.withub.ums.action.config.verify.PageUtil;
import cn.net.withub.ums.action.config.verify.VerifyRepository;
import cn.net.withub.ums.entity.UmsCourtVerifyConfig;
import cn.net.withub.ums.entity.UmsVerifyConfig;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/verify")
@Results({
        @Result(name = "json", type = "json", params = {"root", "data"})
})
//验证方法
public class VerifyAction {


    private Object data;
    private Integer courtNo;
    private String scope;
    private String param;

    //分页
    private Integer page;
    private Integer limit;

    //只计算通知信息
    private Integer special = 0;

    public Integer getCourtNo() {
        return courtNo;
    }

    public void setCourtNo(Integer courtNo) {
        this.courtNo = courtNo;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getSpecial() {
        return special;
    }

    public void setSpecial(Integer special) {
        this.special = special;
    }

    @Autowired
    VerifyRepository verifyFactory;

    @Autowired
    VerifyConfigAction verifyConfigAction;

    @Autowired
    CourtVerifyAction courtVerifyAction;

    @Autowired
    AuthUtils authUtils;

    //统计  special 为 1 的时候只进行特殊查询(用来通知)
    @Action("count")
    public String count() {

        if (!StringUtils.hasText(scope) && special == 0) {
            return "json";
        }

        courtVerifyAction.setCourtNo(courtNo);
        courtVerifyAction.setScope(scope);
        courtVerifyAction.getConfig();
        //获取该法院的配置信息
        ArrayList<UmsCourtVerifyConfig> configs = (ArrayList<UmsCourtVerifyConfig>) courtVerifyAction.getData();

        if (configs.size() == 0) {
            return "json";
        }

        //人员余量
        Integer threshold = null;
        if (courtNo != null) {
            //获取到公共的配置信息;
            verifyConfigAction.setScope("threshold");
            verifyConfigAction.getConfig();
            ArrayList<UmsVerifyConfig> data = (ArrayList<UmsVerifyConfig>) verifyConfigAction.getData();
            if (data != null && data.size() > 0) {
                try {
                    threshold = Integer.valueOf(data.get(0).getcValue());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        List<Map<String, Object>> re = new ArrayList<>();

        if (scope.equals(courtVerifyEnum.LEVEL.getcScope())) {

            //法官晋升年限的查询
            verifyConfigAction.setScope("level");
            verifyConfigAction.getConfig();
            ArrayList<UmsVerifyConfig> promoteConfig = (ArrayList<UmsVerifyConfig>) verifyConfigAction.getData();

            Map<Object, List<Map<String, Object>>> level = null;
            if (special == 0) {
                //进行统一查询再分组
                List<Map<String, Object>> maps = verifyFactory.countForLevelByGroup(courtNo);
                level = maps.stream().collect(Collectors.groupingBy(map -> map.get("level")));
            } else {
                level = new HashMap<>();
            }


            Map<String, List<UmsVerifyConfig>> configCollect = promoteConfig.stream()
                    .filter(umsVerifyConfig -> StringUtils.hasText(umsVerifyConfig.getcKey()) && umsVerifyConfig.getcValue() != null).collect(Collectors.groupingBy(UmsVerifyConfig::getcKey));

            for (UmsCourtVerifyConfig config : configs) {

                String ckey = config.getcKey();
                List<Map<String, Object>> result = level.get(ckey);
                Integer count = null;
                if (result != null) {
                    count = Integer.valueOf(result.get(0).getOrDefault("count", 0).toString());
                }

                Map<String, Object> stringObjectMap = handleToMap(config, threshold, count);
                List<UmsVerifyConfig> configList = configCollect.get(ckey);
                if (configList != null && configList.size() == 1) {

                    //配置了晋升年限  同时查询满足晋升条件的法官人数
                    int i = verifyFactory.countForPromote(courtNo, ckey, Integer.valueOf(configList.get(0).getcValue()));
                    stringObjectMap.put("promote", i);
                } else {
                    stringObjectMap.put("promote", 0);
                }

                re.add(stringObjectMap);

            }


        } else {

            //其实这个只有一条
            UmsCourtVerifyConfig config = configs.get(0);

            String cScope = config.getcScope();
            Integer count = null;
            if (special == 0) {
                count = verifyFactory.count(courtNo, cScope);
            }

            Map<String, Object> stringObjectMap = handleToMap(config, threshold, count);

            if (scope.equals(courtVerifyEnum.ZZZX.getcScope())) {
                //退休年龄距离还要1个月的人员查询
                int i = 0;
                try {
                    i = verifyFactory.countForRetire(courtNo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                stringObjectMap.put("retire", i);
            }


            re.add(stringObjectMap);

        }

        data = re;

        return "json";
    }

    //页面上的查询
    @Action("query")
    public String query() {
        if (!StringUtils.hasText(scope)) {
            return "json";
        }
        //一定要分页
        if (page == null || limit == null) {
            return "json";
        }
        //分页
        PageUtil pageUtil = new PageUtil(page, limit);
        List<Map<String, Object>> maps = null;
        int count = 0;
        if (scope.equals(courtVerifyEnum.LEVEL.getcScope())) {
            if (!StringUtils.hasText(param)) {
                return "json";
            }

            maps = verifyFactory.queryForLevel(courtNo, param, pageUtil);
            count = verifyFactory.countForLevel(courtNo, param);

        } else {
            maps = verifyFactory.queryForPage(courtNo, scope, pageUtil);
            count = verifyFactory.count(courtNo, scope);
        }


        Map<String, Object> re = new HashMap<>();
        re.put("code", 0);
        re.put("count", count);
        re.put("data", maps);

        data = re;
        return "json";
    }

    //法官等级晋升反查
    @Action("queryForPromote")
    public String queryForPromote() {
        if (!StringUtils.hasText(scope) && !StringUtils.hasText(param)) {
            return "json";
        }

        //一定要分页
        if (page == null || limit == null) {
            return "json";
        }
        //分页
        PageUtil pageUtil = new PageUtil(page, limit);

        //法官晋升年限的查询
        verifyConfigAction.setScope("level");
        verifyConfigAction.getConfig();
        ArrayList<UmsVerifyConfig> promoteConfig = (ArrayList<UmsVerifyConfig>) verifyConfigAction.getData();


        Map<String, List<UmsVerifyConfig>> configCollect = promoteConfig.stream()
                .filter(umsVerifyConfig -> StringUtils.hasText(umsVerifyConfig.getcKey()) && umsVerifyConfig.getcValue() != null).collect(Collectors.groupingBy(UmsVerifyConfig::getcKey));

        List<UmsVerifyConfig> configList = configCollect.get(param);

        List<Map<String, Object>> maps = new ArrayList<>();
        int count = 0;
        if (configList != null && configList.size() == 1) {

            //满足法官晋升年限的人员
            count = verifyFactory.countForPromote(courtNo, param, Integer.valueOf(configList.get(0).getcValue()));
            maps = verifyFactory.queryForPromote(courtNo, param, Integer.valueOf(configList.get(0).getcValue()), pageUtil);

        }


        Map<String, Object> re = new HashMap<>();
        re.put("code", 0);
        re.put("count", count);
        re.put("data", maps);

        data = re;
        return "json";
    }

    //退休年龄的查询
    @Action("queryForRetire")
    public String queryForRetire() {
        if (!StringUtils.hasText(scope) && !StringUtils.hasText(param)) {
            return "json";
        }

        //一定要分页
        if (page == null || limit == null) {
            return "json";
        }
        //分页
        PageUtil pageUtil = new PageUtil(page, limit);

        //满足法官晋升年限的人员
        int count = verifyFactory.countForRetire(courtNo);
        List<Map<String, Object>> maps = verifyFactory.queryForRetire(courtNo, pageUtil);


        Map<String, Object> re = new HashMap<>();
        re.put("code", 0);
        re.put("count", count);
        re.put("data", maps);

        data = re;
        return "json";
    }


    //通知提醒
    @Action("notice")
    public String notice() {


        List<String> strings = Arrays.asList(courtVerifyEnum.ZZZX.getcScope(), courtVerifyEnum.LEVEL.getcScope());

        // 返回的通知
        List<Map<String, Object>> res = new ArrayList<>();
        for (String str : strings) {
            this.setScope(str);
            this.setSpecial(1);
            count();
            List<Map<String, Object>> data = (List<Map<String, Object>>) this.getData();

            if (str.equals(courtVerifyEnum.ZZZX.getcScope())) {

                for (Map<String, Object> datum : data) {

                    Integer da = Integer.valueOf(datum.getOrDefault("retire", 0).toString());
                    if (da > 0) {
                        Map<String, Object> in = new HashMap<>();
                        in.put("prefix", "即将退休人员");
                        in.put("suffix", da);
                        //反查类型 用于前台
                        in.put("type", "3");
                        in.put("scope",str);
                        in.put("param",datum.getOrDefault("cKey", ""));
                        res.add(in);
                    }
                }

            } else if (str.equals(courtVerifyEnum.LEVEL.getcScope())) {

                for (Map<String, Object> datum : data) {

                    Integer da = Integer.valueOf(datum.getOrDefault("promote", 0).toString());
                    if (da > 0) {
                        Map<String, Object> in = new HashMap<>();
                        in.put("prefix", datum.getOrDefault("cName", "") + "满足晋升人数");
                        in.put("suffix", da);
                        //反查类型 用于前台
                        in.put("type", "2");
                        in.put("scope",str);
                        in.put("param",datum.getOrDefault("cKey", ""));
                        res.add(in);
                    }

                }
            }

        }

        data = res;
        return "json";
    }


    public static Map<String, Object> entityToMap(Object o) {
        Map<String, Object> map = new HashMap<>();

        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field : fields) {

            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }

            field.setAccessible(true);

            try {
                map.put(field.getName(), field.get(o));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    private Map<String, Object> handleToMap(UmsCourtVerifyConfig config, Integer threshold, Integer count) {


        String cValue = config.getcValue();
        //配置的人员总数
        Integer value = null;
        if (StringUtils.hasText(cValue)) {
            try {
                value = Integer.valueOf(cValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        Map<String, Object> stringObjectMap = entityToMap(config);
        stringObjectMap.put("real", count);
        if (value != null && threshold != null && count != null && (value - count) < threshold) {
            stringObjectMap.put("warning", true);
            stringObjectMap.put("threshold", threshold);
            stringObjectMap.put("config", value);
        } else {
            stringObjectMap.put("warning", false);
        }

        return stringObjectMap;

    }

}
