/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.dao.UmsLevelInfoMapper;
import cn.net.withub.ums.dao.UmsUserInfoMapper;
import cn.net.withub.ums.dao.attach.experimental.UmsAttachedTableMapper;
import cn.net.withub.ums.entity.*;
import cn.net.withub.ums.log.UmsLogger;
import cn.net.withub.ums.service.UmsAttachedTableService;
import cn.net.withub.ums.util.ReflectionTools;
import cn.net.withub.ums.util.StringTools;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UmsAttachedTableServiceImpl implements UmsAttachedTableService {

    @Autowired
    private UmsAttachedTableMapper attachedTableMapper;

    @Autowired
    private UmsUserInfoMapper umsUserInfoMapper;

    @Autowired
    private SqlSession sqlSession;

    @Autowired
    UmsLogger umsLogger;

    @Autowired
    UmsLevelInfoMapper umsLevelInfoMapper;

    private final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    @Override
    public int countView(String viewName, String userId) {
        try {
            return attachedTableMapper.countView(viewName, userId);
        } catch (Exception e) {
            umsLogger.log(UmsLogger.LogType.Suspicious, String.format("e: %s, v: %s, u: %s", e.getMessage(), viewName, userId));
            throw e;
        }
    }

    @Override
    public int countView1(String viewName, String userId, Map otherParam) {
        try {
            return attachedTableMapper.countView1(viewName, userId, otherParam);
        } catch (Exception e) {
            umsLogger.log(UmsLogger.LogType.Suspicious, String.format("e: %s, v: %s, u: %s", e.getMessage(), viewName, userId));
            throw e;
        }
    }

    @Override
    public List selectView(String viewName, String userId, String order, RowBounds rowBounds) {
        try {
            return attachedTableMapper.selectView(viewName, userId, order, rowBounds);
        } catch (Exception e) {
            e.printStackTrace();
            umsLogger.log(UmsLogger.LogType.Suspicious, String.format("e: %s, v: %s, u: %s", e.getMessage(), viewName, userId));
            return null;
        }
    }

    @Override
    public List selectView1(String viewName, String userId, String order, Map otherParam, RowBounds rowBounds) {
        try {
            return attachedTableMapper.selectView1(viewName, userId, order, otherParam, rowBounds);
        } catch (Exception e) {
            e.printStackTrace();
            umsLogger.log(UmsLogger.LogType.Suspicious, String.format("e: %s, v: %s, u: %s", e.getMessage(), viewName, userId));
            return null;
        }
    }

    @Override
    public List selectViewAppendWhere(String viewName, String userId, String order, RowBounds rowBounds, String where) {
        try {
            return attachedTableMapper.selectViewAppendWhere(viewName, userId, order, rowBounds, where);
        } catch (Exception e) {
            e.printStackTrace();
            umsLogger.log(UmsLogger.LogType.Suspicious, String.format("e: %s, v: %s, u: %s", e.getMessage(), viewName, userId, where));
            return null;
        }
    }

    public Map<String, Object> selectDataSetView(Map views, String userId) {
        try {
            return attachedTableMapper.selectDataSetView(views, userId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int insert(Object entity) {
        int invoke = 0;
        try {
            Object mapper = getEntityMapper(entity);
            Method method = mapper.getClass().getDeclaredMethod("insert", entity.getClass());
            invoke = (int) method.invoke(mapper, entity);

            //????????????
            updateChildInfo(mapper, entity);

        } catch (Exception e) {
//            umsLogger.log(UmsLogger.LogType.Suspicious, "e: " + e.getMessage() + ", entity: " + gson.toJson(entity));
            e.printStackTrace();
        }
        return invoke;
    }

    @Override
    public int deleteById(Object entity, String id) {
        try {
            Object mapper = getEntityMapper(entity);
            Method method = mapper.getClass().getDeclaredMethod("deleteByPrimaryKey", String.class);
            return (int) method.invoke(mapper, id);
        } catch (Exception e) {
            umsLogger.log(UmsLogger.LogType.Suspicious, "e: " + e.getMessage() + ", id: " + id);
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Object entity) {
        int invoke = 0;
        try {
            Object mapper = getEntityMapper(entity);
            Method method = mapper.getClass().getDeclaredMethod("updateByPrimaryKey", entity.getClass());
            invoke = (int) method.invoke(mapper, entity);

            //????????????
            updateChildInfo(mapper, entity);

        } catch (Exception e) {
            umsLogger.log(UmsLogger.LogType.Suspicious, "e: " + e.getMessage() + ", entity: " + gson.toJson(entity));
            e.printStackTrace();
        }
        return invoke;
    }

    @Override
    public int updateSelective(Object entity, String primarykey, boolean onlyaddnew, List<String> compareFields, boolean tempTable) {
        int invoke = 0;
        try {
            Object mapper = getEntityMapper(entity);
            Object oldval = null;
            if (compareFields.size() == 0) {
                Method method1 = mapper.getClass().getDeclaredMethod("selectByPrimaryKey", String.class);
                oldval = method1.invoke(mapper, primarykey);
            } else {
                //todo ???????????????????????????
            }
            if (onlyaddnew) {
                if (oldval == null) {
                    Method method = mapper.getClass().getDeclaredMethod(tempTable ? "insert1" : "insert", entity.getClass());
                    invoke = (int) method.invoke(mapper, entity);
                } else {
                    //??????
                }
            } else {
                if (oldval != null) {
                    entity = ReflectionTools.combineSydwCore(oldval, entity);
                }
                Method method = mapper.getClass().getDeclaredMethod(tempTable ? "insert1" : "updateByPrimaryKey", entity.getClass());
                invoke = (int) method.invoke(mapper, entity);
            }

            //????????????
            if (!tempTable && !(entity instanceof UmsUserInfo)) {
                updateChildInfo(mapper, entity);
            }
        } catch (Exception e) {
            umsLogger.log(UmsLogger.LogType.Suspicious, "e: " + e.getMessage() + ", entity: " + gson.toJson(entity));
            e.printStackTrace();
        }
        return invoke;
    }

    private Object getEntityMapper(Object entity) throws Exception {
        String entityName = entity.getClass().getSimpleName();
        return sqlSession.getMapper(Class.forName("cn.net.withub.ums.dao." + entityName + "Mapper"));
    }

    @Override
    public int delete(Object entity) {
        try {
            Object mapper = getEntityMapper(entity);
            //?????????????????????????????????????????????????????????
            Object infoEntity = checkIsNeedUpdateTable(mapper, entity);
            Method method = mapper.getClass().getDeclaredMethod("deleteByPrimaryKey", String.class);
            int updateRes = (int) method.invoke(mapper, ReflectionTools.getField(entity, "id"));
            //??????????????????
            if (infoEntity != null && updateRes > 0) {
                updateUmsTableForDelete(mapper,entity,infoEntity);
            }
            return updateRes;

        } catch (Exception e) {
            umsLogger.log(UmsLogger.LogType.Suspicious, "e: " + e.getMessage() + ", entity: " + gson.toJson(entity));
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List selectView(String viewName, String userId) {
        try {
            return attachedTableMapper.selectView(viewName, userId, "sort_no", RowBounds.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
//            umsLogger.log(UmsLogger.LogType.Suspicious, String.format("e: %s, v: %s, u: %s", e.getMessage(), viewName, userId));
            return null;
        }
    }

    @Override
    public Map<String, Object> selectViewNoAspect(String viewName, String userId, String order, String[] filed) {
        return attachedTableMapper.selectViewNoAspect(viewName, userId, order, filed);
    }

    @Override
    public <T> List<T> selectView(Class<?> mapperClass, String userId) {
        try {
            //??????mapper
            Object mapper = sqlSession.getMapper(mapperClass);

            //??????Criteria
            Object criteria = Class.forName("cn.net.withub.ums.entity." + mapperClass.getSimpleName().replaceAll("Mapper$", "Criteria")).newInstance();

            //????????????Criteria
            Object c = criteria.getClass().getMethod("createCriteria", (Class<?>[]) null).invoke(criteria, (Object[]) null);

            //??????????????????
            c.getClass().getMethod("andUserIdEqualTo", String.class).invoke(c, userId);

            //???????????????List
            return (List<T>) mapper.getClass().getMethod("selectByExample", criteria.getClass()).invoke(mapper, criteria);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Map<String, Object>> selectAll(String tableName, int start, int limit) {
        try {
            return attachedTableMapper.selectAll(tableName, start, limit);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> selectAllView(String tableName, int start, int limit) {
        try {
            return attachedTableMapper.selectAllView(tableName, start, limit);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int count(String tableName) {
        return attachedTableMapper.count(tableName);
    }

    @Override
    public Integer countNoAspect(String tableName) {
        return attachedTableMapper.count(tableName);
    }

    @Override
    public List<Map<String, Object>> selectSingleInfo(String tableName, String userid) {

        List<Map<String, Object>> stringObjectMap = null;
        try {

            stringObjectMap = attachedTableMapper.selectSingleInfo(tableName, userid);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringObjectMap;

    }

    private int updateChildInfo(Object mapper, Object entity) throws Exception {
        int result = 0;
        Method method1 = null;
        Method method2 = null;
        try {
            //try catch??????????????????????????????????????????????????????????????????????????????
            method1 = mapper.getClass().getDeclaredMethod("updateUmsUserInfo", entity.getClass());
        } catch (NoSuchMethodException ignore) {
        }
        try {
            //try catch??????????????????????????????????????????????????????????????????????????????
            method2 = mapper.getClass().getDeclaredMethod("updateXtptUserInfo", entity.getClass());
        } catch (NoSuchMethodException ignore) {
        }
        Field nPresentInfo = null;
        //?????????????????????????????????????????????????????????????????????????????????
        try {
            nPresentInfo = entity.getClass().getDeclaredField("nPresentInfo");
        } catch (NoSuchFieldException ignored) {
            return 0;
        }

        //??????nLevelType???????????????????????????4??????????????????nLevelType????????????
        Object nLevelType = null;
        try {
            Field field = entity.getClass().getDeclaredField("nLevelType");
            field.setAccessible(true);
            nLevelType = field.get(entity);
        } catch (NoSuchFieldException ignored) {
        }

        //??????????????????  ??????????????????
        Object is_removal = null;
        try {
            Field field = entity.getClass().getDeclaredField("nAssignType");
            field.setAccessible(true);
            is_removal = field.get(entity);
        } catch (NoSuchFieldException ignored) {
        }
        if (is_removal != null && (int) is_removal == 2) {
            try {
                //???????????????????????? 2 ??????????????????????????? ???
                if (entity instanceof UmsAdministrativeJob) {
                    //????????????
                    UmsAdministrativeJob entity1 = (UmsAdministrativeJob) entity;
                    entity1.setnJob(null);
                    entity1.setnJobReport(null);
                    entity1.setdAssignDate(null);
                } else if (entity instanceof UmsLegalJob) {
                    //????????????
                    UmsLegalJob entity1 = (UmsLegalJob) entity;
                    entity1.setnJob(null);
                    entity1.setnJobReport(null);
                    entity1.setdAssignDate(null);
                } else if (entity instanceof UmsRankInfo) {
                    //??????
                    UmsRankInfo entity1 = (UmsRankInfo) entity;
                    entity1.setnRank(null);
                    entity1.setnRankReport(null);
                    entity1.setdAssignDate(null);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        nPresentInfo.setAccessible(true);
        Object invoke = nPresentInfo.get(entity);
        if (invoke != null && (int) invoke == 1) {

            if (method1 != null) {
                if ("UmsLevelInfo".equals(entity.getClass().getSimpleName()) && 1 != ((Integer) nLevelType).intValue()) { //?????????????????????????????????????????????.???????????? '???????????????1?????????2?????????3???????????????4?????????', ?????????????????????????????????,????????????????????????????????????????????????
                    // UmsLevelInfo nLevelType = 2/3/4   UmsLevelInfo ??????4????????? ????????????1??????????????? ???????????? 2???3???4 ???????????? ums_user_info ?????? level ?????????
                    UmsLevelInfo umsLevelInfoEntity = (UmsLevelInfo) entity;
                    //??????????????????sql ?????????1
                    umsLevelInfoEntity.setnLevelType(1);
                    umsLevelInfoEntity.setJudgeLevel(null);
                    umsLevelInfoEntity.setdStartDate(null);
                    umsLevelInfoEntity.setIsYefg("0");
                }
                // UmsLevelInfo nLevelType =1 ??????????????????
                result = (int) method1.invoke(mapper, entity);

            }
            if (method2 != null) {
                result = (int) method2.invoke(mapper, entity);
            }

            //???????????????????????????
            String name = entity.getClass().getName();
            int i = name.lastIndexOf(".");
            String tableName = StringTools.camelOrPascalToUnderline(name.substring(i + 1));
            Field id = entity.getClass().getDeclaredField("id");
            id.setAccessible(true);
            Field userId = entity.getClass().getDeclaredField("userId");
            userId.setAccessible(true);

            Map map = new HashMap<>();
            map.put("userId", userId.get(entity));
            map.put("id", id.get(entity));
            map.put("table", tableName);
            map.put("nLevelType", nLevelType);

            // ?????????????????????????????????n_present_info   =  2 ??????????????????
            umsUserInfoMapper.updatePresentInfoByUserId(map);
            // ????????????????????????n_present_info   = 1 ???????????????
            umsUserInfoMapper.updatePresentInfoById(map);

        }
        return result;

    }

    //?????????????????????????????????????????????
    private Object checkIsNeedUpdateTable(Object mapper, Object entity) {
        //?????????????????????????????? ?????????????????? ????????????ums_user_info??????????????????
        if (!isNeedUpdateEntity(entity)) {
            return null;
        }
        Field nPresentInfo = null;
        //?????????????????????????????????????????????????????????????????????????????????
        try {
            nPresentInfo = entity.getClass().getDeclaredField("nPresentInfo");
        } catch (NoSuchFieldException ignored) {
            return null;
        }

        //????????????????????????
        Method selectByPrimaryKey = null;
        try {
            selectByPrimaryKey = mapper.getClass().getDeclaredMethod("selectByPrimaryKey", String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Field IdField = null;
        try {
            IdField = entity.getClass().getDeclaredField("id");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        try {
            if (IdField != null && selectByPrimaryKey != null) {
                IdField.setAccessible(true);
                Object Ido = IdField.get(entity);
                if (Ido != null) {
                    String Id = Ido.toString();
                    //??????????????????
                    Object baseDataINfo = selectByPrimaryKey.invoke(mapper, Id);
                    if (baseDataINfo != null) {
                        nPresentInfo.setAccessible(true);
                        Object isPresentInfo = nPresentInfo.get(baseDataINfo);
                        if (isPresentInfo != null && (int) isPresentInfo == 1) {
                            //??????????????? ???????????????
                            return baseDataINfo;
                        }
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //????????????????????? ????????????????????????
    private void updateUmsTableForDelete(Object mapper, Object deleteEntity,Object infoEntity) {

        if(!isNeedUpdateEntity(deleteEntity)){
            return;
        }

        Method method1 = null;
        Method method2 = null;
        try {
            //try catch??????????????????????????????????????????????????????????????????????????????
            method1 = mapper.getClass().getDeclaredMethod("updateUmsUserInfo", deleteEntity.getClass());
        } catch (NoSuchMethodException ignore) {
        }
        try {
            //try catch??????????????????????????????????????????????????????????????????????????????
            method2 = mapper.getClass().getDeclaredMethod("updateXtptUserInfo", deleteEntity.getClass());
        } catch (NoSuchMethodException ignore) {
        }

        //??????nLevelType???????????????????????????4??????????????????nLevelType????????????  deleteEntity ???????????? ??????????????? infoEntity
        Object nLevelType = null;
        try {
            if("UmsLevelInfo".equals(infoEntity.getClass().getSimpleName()) ){
                Field field = infoEntity.getClass().getDeclaredField("nLevelType");
                field.setAccessible(true);
                nLevelType = field.get(infoEntity);
            }

        } catch (Exception ignored) {
        }

        try {
            if (method1 != null) {
                if (nLevelType != null  ) { //?????????????????????????????????????????????.???????????? '???????????????1?????????2?????????3???????????????4?????????', ?????????????????????????????????,????????????????????????????????????????????????

                    if(1 ==  Integer.parseInt(nLevelType.toString())){
                        // UmsLevelInfo nLevelType =1  ???????????????
                        UmsLevelInfo umsLevelInfoEntity = (UmsLevelInfo) deleteEntity;
                        //??????????????????sql ?????????1
                        umsLevelInfoEntity.setnLevelType(1);
                        umsLevelInfoEntity.setJudgeLevel(null);
                        umsLevelInfoEntity.setdStartDate(null);
                        umsLevelInfoEntity.setIsYefg("0");
                        method1.invoke(mapper, deleteEntity);
                    }
                    // UmsLevelInfo nLevelType = 2/3/4 ????????????

                } else {
                   //??????????????????
                    method1.invoke(mapper, deleteEntity);
                }
            }
            if (method2 != null) {
                method2.invoke(mapper, deleteEntity);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private boolean isNeedUpdateEntity(Object entity) {
        String simpleName = entity.getClass().getSimpleName();
        List<String> umsPoliticalInfo = Arrays.asList("UmsPoliticalInfo","UmsLegalJob","UmsAdministrativeJob","UmsRankInfo","UmsLevelInfo","UmsCivilServantLevel","UmsEducationInfo","UmsDegreeInfo");
        return umsPoliticalInfo.contains(simpleName);
    }

    @Override
    public int countViewBatch(String viewName) {
        try {
            return attachedTableMapper.countViewBatch(viewName);
        } catch (Exception e) {
            umsLogger.log(UmsLogger.LogType.Suspicious, String.format("e: %s, v: %s, u: %s", e.getMessage(), viewName));
            throw e;
        }
    }

    @Override
    public List selectViewBatch(String viewName, String order, RowBounds rowBounds) {
        List list = null;
        try {
            list = attachedTableMapper.selectViewBatch(viewName, order, rowBounds);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            umsLogger.log(UmsLogger.LogType.Suspicious, String.format("e: %s, v: %s, u: %s", e.getMessage(), viewName));
            return null;
        }
    }

    @Override
    public List selectViewAllBatch(String viewName, String order, String where) {
        try {
            return attachedTableMapper.selectViewAllBatch(viewName, order, where);
        } catch (Exception e) {
            umsLogger.log(UmsLogger.LogType.Suspicious, String.format("e: %s, v: %s, u: %s", e.getMessage(), viewName));
            return null;
        }
    }

    @Override
    public int insertBatch(Object entity) {
        int invoke = 0;
        try {
            Object mapper = getEntityMapper(entity);
            Method method = mapper.getClass().getDeclaredMethod("insert", entity.getClass());
            invoke = (int) method.invoke(mapper, entity);
        } catch (Exception e) {
//            umsLogger.log(UmsLogger.LogType.Suspicious, "e: " + e.getMessage() + ", entity: " + gson.toJson(entity));
            e.printStackTrace();
        }
        return invoke;
    }

    ;

    @Override
    public int updateBatch(Object entity) {
        int invoke = 0;
        try {
            Object mapper = getEntityMapper(entity);
            Method method = mapper.getClass().getDeclaredMethod("updateByPrimaryKey", entity.getClass());
            invoke = (int) method.invoke(mapper, entity);

        } catch (Exception e) {
            umsLogger.log(UmsLogger.LogType.Suspicious, "e: " + e.getMessage() + ", entity: " + gson.toJson(entity));
            e.printStackTrace();
        }
        return invoke;
    }

    @Override
    public int AttachmentCourtUpdate(String userId, Integer courtNo) {
        return attachedTableMapper.AttachmentCourtUpdate(userId, courtNo);
    }

    @Override
    public Map<String, Object> selectViewPresent(String viewName, String userId) {
        return attachedTableMapper.selectViewPresent(viewName, userId);
    }

    @Override
    public List<Map<String, Object>> selectActiveLevelInfo(Integer nLevelType) {
        return attachedTableMapper.selectActiveLevelInfo(nLevelType);
    }


}
