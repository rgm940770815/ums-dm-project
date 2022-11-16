/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.aop.auth;

import cn.net.withub.ums.auth.AuthorityHelper;
import cn.net.withub.ums.common.UmsConstant;
import cn.net.withub.ums.dao.UmsUserInfoMapper;
import cn.net.withub.ums.entity.UmsUserInfo;
import cn.net.withub.ums.entity.UmsUserInfoView;
import cn.net.withub.ums.util.ReflectionTools;
import cn.net.withub.ums.util.StringUtils;
import com.opensymphony.xwork2.ActionContext;
import java.lang.reflect.Method;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Diluka
 */
@Aspect
@Component
public class UserAttachedAuthAspect {

    @Autowired
    private AuthorityHelper authorityHelper;

    @Autowired
    private UmsUserInfoMapper userInfoMapper;

    @Autowired
    private SqlSession sqlSession;

    @Around("execution(public * cn.net.withub.ums.service.UmsAttachedTableService.selectView(String,String,..))"
            + " && args(viewName,userId,..)")
    public Object attachedSelectViewServiceCall(ProceedingJoinPoint jointPoint, String viewName, String userId) throws Throwable {
        List<Integer> courtNoList = authorityHelper.accessibleCourtNoList("查看");

        if (authorityHelper.isSelfOnly(courtNoList)) {//基本权限
            UmsUserInfoView user = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
            if (user.getId().equalsIgnoreCase(userId)) {
                return jointPoint.proceed();
            } else {
                return null;
            }
        } else if (courtNoList.contains(-9999)) { //管理员
            return jointPoint.proceed();
        }

        UmsUserInfo u = userInfoMapper.selectByPrimaryKey(userId);
        if (u == null) {
            //无此用户ID
            return null;
        }

        if (courtNoList.contains(u.getCourtNo())) {
            return jointPoint.proceed();
        } else {
            //无权限，非法访问
            return null;
        }
    }

    @Around("execution(public int cn.net.withub.ums.service.UmsAttachedTableService.*(..))"
            + " && args(entity,primarykey, ..)")
    public Object attachedEditServiceCall(ProceedingJoinPoint jointPoint, Object entity, String primarykey) throws Throwable {

        switch (jointPoint.getSignature().getName()) {
            case "insert":
            case "update":
                break;

//            case "delete":
//                Object mapper = getEntityMapper(entity);
//                Method method = mapper.getClass().getDeclaredMethod("selectByPrimaryKey", String.class);
//                entity = method.invoke(mapper, ReflectionTools.getField(entity, "id"));
//                break;
//            case "deleteById":
//                Object mapper2 = getEntityMapper(entity);
//                Method method2 = mapper2.getClass().getDeclaredMethod("selectByPrimaryKey", String.class);
//                entity = method2.invoke(mapper2, jointPoint.getArgs()[1]);
//                break;
            default:
                return jointPoint.proceed();
        }

        boolean flag;
        if (StringUtils.isNotEmpty(primarykey)) flag = true;
        else flag = authorityHelper.entityCheck(entity);
        if (flag) {
            return jointPoint.proceed();
        } else {
            return -1;
        }
    }

    private Object getEntityMapper(Object entity) throws Exception {
        String entityName = entity.getClass().getSimpleName();
        return sqlSession.getMapper(Class.forName("cn.net.withub.ums.dao." + entityName + "Mapper"));
    }
}
