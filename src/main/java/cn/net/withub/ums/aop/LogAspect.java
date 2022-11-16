package cn.net.withub.ums.aop;

import cn.net.withub.ums.common.UmsConstant;
import cn.net.withub.ums.entity.*;
import cn.net.withub.ums.log.UmsLogger;
import cn.net.withub.ums.service.*;
import cn.net.withub.ums.util.IpTools;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import org.apache.ibatis.session.SqlSession;
import org.apache.struts2.ServletActionContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * 日志记录，添加、删除、修改方法AOP Created by D.Yang on 2014/12/24 0024.
 */
@Aspect
@Component
public class LogAspect {

    @Autowired
    private UmsLogger umsLogger;

    @Autowired
    private UmsLogService logService;

    @Autowired
    private UmsUserLogService umsUserLogService;


    @Autowired
    private UmsUserDeptLogService umsUserDeptLogService;


    @Autowired
    private UmsCourtService umsCourtService;

    @Autowired
    private UmsDepartmentService umsDepartmentService;

    @Autowired
    private UmsTemporaryPositionService umsTemporaryPositionService;

    @Autowired
    private SqlSession sqlSession;

    private Gson gson = new Gson();

    /**
     * 添加业务逻辑方法切入点
     */
    @Pointcut("execution(* cn.net.withub.ums.service..*.insert*(..)) && !execution(* cn.net.withub.ums.service..UmsLogService*.*(..))  " +
            "&& !execution(* cn.net.withub.ums.service..UmsUserLogService*.*(..))" +
            "&& !execution(* cn.net.withub.ums.service..UmsUserDeptLogService*.*(..))" +
            "&& !execution(* cn.net.withub.ums.service.UmsUserInfoService.insertNoAspect(..))" +
            "&& !execution(* cn.net.withub.ums.service.UmsJurorInfoService.insertNoAspect(..))" +
            "&& !execution(* cn.net.withub.ums.service.UmsTemporaryPositionService*.insert(..))")
    public void insertServiceCall() {
    }

    /**
     * 修改业务逻辑方法切入点
     */
    @Pointcut("execution(* cn.net.withub.ums.service..*.update*(..)) && !execution(* cn.net.withub.ums.service..UmsLogService*.*(..))  " +
            "&& !execution(* cn.net.withub.ums.service..UmsUserLogService*.*(..))" +
            "&& !execution(* cn.net.withub.ums.service..UmsUserDeptLogService*.*(..))" +
            "&& !execution(* cn.net.withub.ums.service.UmsTemporaryPositionService*.updatePartTimeJob(..))" +
            "&& !execution(* cn.net.withub.ums.service.UmsTemporaryPositionService*.update(..))")
    public void updateServiceCall() {
    }

    /**
     * 删除业务逻辑方法切入点
     */
    @Pointcut("execution(* cn.net.withub.ums.service..*.delete*(..)) && !execution(* cn.net.withub.ums.service..UmsLogService*.*(..))  " +
            "&& !execution(* cn.net.withub.ums.service..UmsUserLogService*.*(..))" +
            "&& !execution(* cn.net.withub.ums.service..UmsUserDeptLogService*.*(..))" +
            "&& !execution(* cn.net.withub.ums.service.UmsTemporaryPositionService*.deleteByUUID(..))")
    public void deleteServiceCall() {
    }


    /**
     * 添加业务逻辑方法切入点
     */
    @Pointcut("execution(* cn.net.withub.ums.service.UmsTemporaryPositionService*.insert(..)) ")
    public void insertForTemporaryPositionServiceCall() {

    }

    /**
     * 修改业务逻辑方法切入点
     */
    @Pointcut("execution(* cn.net.withub.ums.service.UmsTemporaryPositionService*.update(..)) ")
    public void updateForTemporaryPositionServiceCall() {
    }


    /**
     * 删除业务逻辑方法切入点
     */
    @Pointcut("execution(* cn.net.withub.ums.service.UmsTemporaryPositionService*.deleteByUUID(..))")
    public void deleteForTemporaryPositionServiceCall() {
    }

    /**
     * 添加日志（后置通知）
     *
     * @param joinPoint
     * @param rtv
     * @throws Throwable
     */
    @AfterReturning(pointcut = "insertServiceCall()", returning = "rtv")
    public void insertServiceCallCalls(JoinPoint joinPoint, Object rtv) throws Throwable {

        //获取方法名
        String methodName = joinPoint.getSignature().getName();

        //取得被操作用户，及用户法院
        String cu_user_id = null;
        Integer cu_court_no = null;
        String entity_id = null;
        boolean isUmsUserImpl = false;
        // 获取操作内容
        String opContent = adminOptionContent(joinPoint.getArgs(),  joinPoint.getTarget().getClass().getSimpleName() + "." + methodName);
        if (joinPoint.getTarget().getClass().getSimpleName().equals("UmsUserInfoServiceImpl")) {
            isUmsUserImpl = true;
            try {
                for (Object arg : joinPoint.getArgs()) {
                    if (arg instanceof UmsUserInfo) {
                        cu_user_id = ((UmsUserInfo) arg).getId();
                        cu_court_no = ((UmsUserInfo) arg).getCourtNo();
                        entity_id = cu_user_id;
                    }

                }
                umsLog(1, opContent, cu_user_id);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        //  不是修改基本信息时的取值方式，可能只能取到子集信息的东西
        if (!isUmsUserImpl) {
            for (Object arg : joinPoint.getArgs()) {
                Map<String, Object> map = entityToMap(arg);
                if (cu_court_no == null && map.get("courtNo") != null) {
                    cu_court_no = Integer.parseInt(map.get("courtNo").toString());
                }
                if (cu_user_id == null && map.get("userId") != null) {
                    cu_user_id = map.get("userId").toString();
                }
                if (entity_id == null && map.get("id") != null) {
                    entity_id = map.get("id").toString();
                }
            }
        }
        umsLogger.log(UmsLogger.LogType.Insert, opContent, entity_id, cu_user_id, cu_court_no);

    }

    /**
     * 修改
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around(value = "updateServiceCall()")
    public Object updateServiceCallCalls(ProceedingJoinPoint pjp) throws Throwable {
        Object result = null;
        String methodName = pjp.getSignature().getName();

        List<Map<String, Object>> uList = new ArrayList<>();
        List<Map<String, Object>> oList = new ArrayList<>();
        //是否是UmsUserInfo 的处理
        boolean isUmsUserImpl = false;
        String cuCourtCode = null;
        Integer dept_id = null;
        //取得被操作用户，及用户法院
        String cu_user_id = null;
        Integer cu_court_no = null;
        String entity_id = null;
        if (pjp.getTarget().getClass().getSimpleName().equals("UmsUserInfoServiceImpl")) {
            isUmsUserImpl = true;
            try {
                for (Object arg : pjp.getArgs()) {
                    if (arg instanceof UmsUserInfo) {
                        cu_user_id = ((UmsUserInfo) arg).getId();
                        cuCourtCode = ((UmsUserInfo) arg).getCourtCode();
                        dept_id = ((UmsUserInfo) arg).getDepartment();
                        cu_court_no = ((UmsUserInfo) arg).getCourtNo();
                        entity_id = cu_user_id;
                        break;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (Object arg : pjp.getArgs()) {
            Map<String, Object> map = entityToMap(arg);
            uList.add(map);

            Object orig = getOrignalEntity(arg);
            Map<String, Object> map2 = entityToMap(orig);
            oList.add(map2);
            //记录部门变更
            try {
                if (isUmsUserImpl && arg instanceof UmsUserInfo && cu_user_id != null) {
                    if (map != null && map2 != null && map.get("department") != null && map2.get("department") != null) {
                        if (!map.get("department").toString().equals(map2.get("department").toString())) {

                            int dept_1 = Integer.parseInt(map2.get("department").toString().trim());
                            int dept_2 = Integer.parseInt(map.get("department").toString().trim());

                            //查信息
                            String courtText = umsCourtService.courtTexForCourtCode(cuCourtCode);

                            UmsDepartmentCriteria criteria = new UmsDepartmentCriteria();
                            criteria.createCriteria().andCourtCodeEqualTo(cuCourtCode)
                                    .andDeptNoEqualTo(dept_1);
                            String oldName = umsDepartmentService.selectByExample(criteria).get(0).getDeptName();

                            criteria.clear();
                            criteria.createCriteria().andCourtCodeEqualTo(cuCourtCode)
                                    .andDeptNoEqualTo(dept_2);
                            String newName = umsDepartmentService.selectByExample(criteria).get(0).getDeptName();


                            //拼接
                            StringBuffer s = new StringBuffer("法院: ");
                            s.append(courtText + "   部门由 ");
                            s.append(oldName + "(" + map2.get("department") + ")" + "  变更为  " + newName + "(" + map.get("department") + ")");

                            //记录
                            umsDeptLog(s.toString(), cu_user_id, 1);

                            isUmsUserImpl = false;

                            //删除 原来挂靠部门下同法院同部门的信息
                            UmsTemporaryPositionExample example = new UmsTemporaryPositionExample();
                            example.createCriteria().andCourtCodeEqualTo(cuCourtCode)
                                    .andDepartmentEqualTo(dept_id).andUuidEqualTo(cu_user_id);
                            List<UmsTemporaryPosition> list = umsTemporaryPositionService.selectByExample(example);
                            if (list.size() > 0) {
                                for (UmsTemporaryPosition l : list) {
                                    umsTemporaryPositionService.deleteByUUID(l);
                                }
                            }
                        }

                    }
                    //法院有变动时候 挂靠部门也需要进行处理
                    if (map != null && map2 != null && map.get("cuCourtCode") != null && map2.get("cuCourtCode") != null && !map.get("cuCourtCode").toString().equals(map2.get("cuCourtCode").toString())) {

                        String courtCode_1 = map2.get("cuCourtCode").toString().trim();
                        String courtCode_2 = map.get("cuCourtCode").toString().trim();

                        //查信息
                        String courtText_1 = umsCourtService.courtTexForCourtCode(courtCode_1);
                        String courtText_2 = umsCourtService.courtTexForCourtCode(courtCode_2);


                        //拼接
                        StringBuffer s = new StringBuffer("法院由 ");
                        s.append(courtText_1 + "  变更为  " + courtText_2);
                        //记录
                        umsDeptLog(s.toString(), cu_user_id, 1);

                        isUmsUserImpl = false;

                        Map<String, Object> map_job = new HashMap<>();

                        map_job.put("partTimeJob", 0);
                        map_job.put("uuid", cu_user_id);
                        umsTemporaryPositionService.updatePartTimeJob(map_job);

                        map_job.put("partTimeJob", 1);
                        map_job.put("cuCourtCode", cuCourtCode);
                        umsTemporaryPositionService.updatePartTimeJob(map_job);

                        //删除 原来挂靠部门下同法院同部门的信息
                        UmsTemporaryPositionExample example = new UmsTemporaryPositionExample();
                        example.createCriteria().andCourtCodeEqualTo(cuCourtCode)
                                .andDepartmentEqualTo(dept_id).andUuidEqualTo(cu_user_id);
                        List<UmsTemporaryPosition> list = umsTemporaryPositionService.selectByExample(example);
                        if (list.size() > 0) {
                            for (UmsTemporaryPosition l : list) {
                                umsTemporaryPositionService.deleteByUUID(l);
                            }
                        }

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        result = pjp.proceed();

        //        // 获取操作内容
        String opContent = updateContent( pjp.getTarget().getClass().getSimpleName() + "." + methodName, oList, uList);

        if (pjp.getTarget().getClass().getSimpleName().equals("UmsUserInfoServiceImpl")) {
            try {
                umsLog(2, opContent, cu_user_id);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //  不是修改基本信息时的取值方式，可能只能取到子集信息的东西
        if (!isUmsUserImpl) {
            for (Map<String, Object> map : uList) {
                if (cu_court_no == null && map.get("courtNo") != null) {
                    cu_court_no = Integer.parseInt(map.get("courtNo").toString());
                }
                if (cu_user_id == null && map.get("userId") != null) {
                    cu_user_id = map.get("userId").toString();
                }
                if (entity_id == null && map.get("id") != null) {
                    entity_id = map.get("id").toString();
                }
            }
        }
        umsLogger.log(UmsLogger.LogType.Update, opContent, entity_id, cu_user_id, cu_court_no);

        return result;
    }

    /**
     * 删除日志（环绕通知：目的是在信息被删除前线查询出信息用于记录）
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around(value = "deleteServiceCall()")
    public Object deleteServiceCallCalls(ProceedingJoinPoint pjp) throws Throwable {
        Object result = null;
        try {

            //执行删除操作
            result = pjp.proceed();
            String methodName = pjp.getSignature().getName();

            //取得被操作用户，及用户法院
            String cu_user_id = null;
            Integer cu_court_no = null;
            String entity_id = null;
            boolean isUmsUserImpl = false;
            if (pjp.getTarget().getClass().getSimpleName().equals("UmsUserInfoServiceImpl")) {
                isUmsUserImpl = true;
                try {
                    for (Object arg : pjp.getArgs()) {
                        if (arg instanceof UmsUserInfo) {
                            cu_user_id = ((UmsUserInfo) arg).getId();
                            cu_court_no = ((UmsUserInfo) arg).getCourtNo();
                            entity_id = cu_user_id;
                            break;
                        }
                        if (arg instanceof String) {
                            cu_user_id = ((String) arg);
                            break;
                        }
                        //UmsUserInfo 调离原因 要特殊记录

                    }
                    umsLog(3, "调用：" + pjp.getTarget().getClass().getSimpleName() + "." + methodName, cu_user_id);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            //  不是修改基本信息时的取值方式，可能只能取到子集信息的东西
            if (!isUmsUserImpl) {
                for (Object arg : pjp.getArgs()) {
                    Map<String, Object> map = entityToMap(arg);
                    if (cu_court_no == null && map.get("courtNo") != null) {
                        cu_court_no = Integer.parseInt(map.get("courtNo").toString());
                    }
                    if (cu_user_id == null && map.get("userId") != null) {
                        cu_user_id = map.get("userId").toString();
                    }
                    if (entity_id == null && map.get("id") != null) {
                        entity_id = map.get("id").toString();
                    }
                }
            }
            umsLogger.log(UmsLogger.LogType.Delete, "调用：" + pjp.getTarget().getClass().getSimpleName() + "." + methodName, entity_id, cu_user_id, cu_court_no);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 添加日志（后置通知）
     *
     * @param joinPoint
     * @param rtv
     * @throws Throwable
     */
    @AfterReturning(pointcut = "insertForTemporaryPositionServiceCall()", returning = "rtv")
    public void insertForTemporaryPositionServiceCall(JoinPoint joinPoint, Object rtv) throws Throwable {
        try {


            String id = null;
            String opContent = "";
            for (Object arg : joinPoint.getArgs()) {
                if (arg instanceof UmsTemporaryPosition) {
                    id = ((UmsTemporaryPosition) arg).getUuid().toString();
                    String courtCode = ((UmsTemporaryPosition) arg).getCourtCode();
                    Integer dept = ((UmsTemporaryPosition) arg).getDepartment();
                    String courtText = umsCourtService.courtTexForCourtCode(courtCode);

                    UmsDepartmentCriteria criteria = new UmsDepartmentCriteria();
                    criteria.createCriteria().andCourtCodeEqualTo(courtCode)
                            .andDeptNoEqualTo(dept);
                    String dept_name = umsDepartmentService.selectByExample(criteria).get(0).getDeptName();

                    opContent = "新增挂靠部门  法院: " + courtText + " 部门: " + dept_name;
                    break;
                }
            }

            umsDeptLog(opContent, id, 2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Around(value = "updateForTemporaryPositionServiceCall()")
    public Object updateForTemporaryPositionServiceCall(ProceedingJoinPoint pjp) throws Throwable {

        Object result = null;
        String methodName = pjp.getSignature().getName();

        List<Map<String, Object>> uList = new ArrayList<>();
        List<Map<String, Object>> oList = new ArrayList<>();


        try {
            String id = "";
            for (Object arg : pjp.getArgs()) {
                if (arg instanceof UmsTemporaryPosition) {
                    //取和usm_user_info关联的uuid
                    id = ((UmsTemporaryPosition) arg).getUuid().toString();
                    Map<String, Object> map = entityToMap(arg);
                    uList.add(map);

                    UmsTemporaryPositionExample example = new UmsTemporaryPositionExample();
                    example.createCriteria().andIdEqualTo(((UmsTemporaryPosition) arg).getId());
                    UmsTemporaryPosition l = umsTemporaryPositionService.selectByExample(example).get(0);
                    Map<String, Object> map2 = entityToMap(l);
                    oList.add(map2);
                }
            }
            String opContent = updateContent(methodName, oList, uList);
            umsDeptLog(opContent, id, 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        result = pjp.proceed();
        return result;

    }


    /**
     * 删除日志（环绕通知：目的是在信息被删除前线查询出信息用于记录）
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around(value = "deleteForTemporaryPositionServiceCall()")
    public Object deleteForTemporaryPositionServiceCall(ProceedingJoinPoint pjp) throws Throwable {
        //执行删除操作
        Object result = pjp.proceed();

        try {

            String id = null;
            String opContent = "";
            for (Object arg : pjp.getArgs()) {
                if (arg instanceof UmsTemporaryPosition) {
                    id = ((UmsTemporaryPosition) arg).getUuid().toString();
                    String courtCode = ((UmsTemporaryPosition) arg).getCourtCode();
                    Integer dept = ((UmsTemporaryPosition) arg).getDepartment();
                    String courtText = umsCourtService.courtTexForCourtCode(courtCode);

                    UmsDepartmentCriteria criteria = new UmsDepartmentCriteria();
                    criteria.createCriteria().andCourtCodeEqualTo(courtCode)
                            .andDeptNoEqualTo(dept);
                    String dept_name = umsDepartmentService.selectByExample(criteria).get(0).getDeptName();

                    opContent = "删除挂靠部门  法院: " + courtText + " 部门: " + dept_name;
                    break;
                }
            }
            umsDeptLog(opContent, id, 2);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return result;
    }


    /**
     * 使用Java反射来获取被拦截方法(insert、update)的参数值， 将参数值拼接为操作内容
     *
     * @param args
     * @param mName
     * @return
     * @throws Exception
     */
    public String adminOptionContent(Object[] args, String mName) throws Exception {

        if (args == null) {
            return null;
        }

        StringBuilder rs = new StringBuilder();
        rs.append("调用：").append(mName);

        // 遍历参数对象
        for (Object arg : args) {

            //获取对象类型
            String className = arg.getClass().getName();
            className = className.substring(className.lastIndexOf(".") + 1);
            rs.append("，类型：").append(className).append("，值：");

            Field[] fields = arg.getClass().getDeclaredFields();

            for (Field field : fields) {
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }

                field.setAccessible(true);

                rs.append(field.getName()).append(":").append(field.get(arg)).append(", ");
            }

        }

        //限制一下大小，不然太大了插入会出错
        return limitString(rs.toString(), 333);
    }

    /**
     * 实体类转换为Map
     *
     * @param o
     * @return
     */
    private Map<String, Object> entityToMap(Object o) {
        Map<String, Object> map = new HashMap<>();
        if (o == null) {
            return map;
        }

        Field[] fields = o.getClass().getDeclaredFields();

        for (Field field : fields) {
            try {
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }

                field.setAccessible(true);

                map.put(field.getName(), field.get(o));
            } catch (Exception e) {
            }
        }

        return map;
    }

    /**
     * 获取原始内容
     *
     * @param newEntity 修改的
     * @return
     */
    private Object getOrignalEntity(Object newEntity) {
        try {
            Class<?> entityClass = newEntity.getClass();
            String className = entityClass.getSimpleName();
            Object mapper = sqlSession.getMapper(Class.forName("cn.net.withub.ums.dao." + className + "Mapper"));
            Method[] methods = mapper.getClass().getMethods();
            for (Method method : methods) {
                if (method.getName().equals("selectByPrimaryKey")) {
                    Field idField = entityClass.getDeclaredField("id");
                    idField.setAccessible(true);

                    return method.invoke(mapper, idField.get(newEntity));
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 修改的内容
     *
     * @param methodName
     * @param oList      原始
     * @param uList      修改
     * @return
     */
    private String updateContent(String methodName, List<Map<String, Object>> oList, List<Map<String, Object>> uList) {
        StringBuilder sb = new StringBuilder("调用：" + methodName);

        for (int i = 0; i < uList.size(); i++) {
            Map<String, Object> um = uList.get(i);
            Map<String, Object> om = oList.get(i);

            for (String key : um.keySet()) {
                try {
                    Object uObject = um.get(key);
                    Object oObject = om.get(key);
                    if (!Objects.equals(oObject, uObject)) {
                        sb.append("，").append(key).append(":").append(oObject).append("->").append(uObject);
                    }
                } catch (Exception e) {
                }
            }

            sb.append("|");
        }

        //限制一下大小，不然太大了插入会出错
        return limitString(sb.toString(), 333);
    }

    private String limitString(String s, int size) {
        return s != null ? s.substring(0, s.length() > size ? size : s.length()) : "";
    }

    /**
     * ums_user_info记录日志
     *
     * @param logType 日志类型
     * @param content 操作内容
     */
    public void umsLog(Integer logType, String content, String id) {
        ActionContext ctx = ActionContext.getContext();
        HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);

        String ip = IpTools.getIpAddress(request);

        UmsUserInfoView user = (UmsUserInfoView) request.getSession().getAttribute(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
        UmsUserLog log = new UmsUserLog();

        log.setId(UUID.randomUUID().toString());
        log.setOpTime(new Date());
        log.setOpUserId(user.getId());
        log.setOpId(id);
        if (user != null) {

            log.setOpUser(user.getFullname());
        }
        log.setOpContent(content);
        log.setOpType(logType);
        log.setOpIp(ip);

        umsUserLogService.insertSelective(log);
    }

    /**
     * ums_user_dept_info 部门修改记录日志
     *
     * @param content 操作内容
     */
    public void umsDeptLog(String content, String id, int type) {

        //类型 1是人员信息部门法院的修改  2是挂靠部门的修改删除
        ActionContext ctx = ActionContext.getContext();
        HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);

        String ip = IpTools.getIpAddress(request);

        UmsUserInfoView user = (UmsUserInfoView) request.getSession().getAttribute(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
        UmsUserDeptLog log = new UmsUserDeptLog();

        log.setId(UUID.randomUUID().toString());
        log.setOpTime(new Date());
        log.setOpUserId(user.getId());
        log.setOpId(id);
        if (user != null) {
            log.setOpUser(user.getFullname());
        }
        log.setOpContent(content);
        log.setOpIp(ip);
        log.setLogType(type);

        umsUserDeptLogService.insert(log);

    }
}
