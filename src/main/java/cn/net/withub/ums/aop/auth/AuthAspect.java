/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.aop.auth;

import cn.net.withub.ums.auth.AuthType;
import cn.net.withub.ums.common.UmsConstant;
import com.opensymphony.xwork2.ActionContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 *
 * @author Diluka
 */
@Aspect
@Component
public class AuthAspect {

    /**
     * 角色访问控制
     *
     * @param jointPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(public * cn.net.withub.ums.service.UmsRoleService.*(..))")
    public Object roleServiceCall(ProceedingJoinPoint jointPoint) throws Throwable {


        switch (jointPoint.getSignature().getName()) {
            case "replaceUserRoles": //分配角色
                return jointPoint.proceed();
            //增删改
            case "insert":
                return jointPoint.proceed();
            case "delete":
                return jointPoint.proceed();
            case "update":
                return jointPoint.proceed();
            case "deleteById":
                return authCommonProcess(jointPoint);
            default:
                return jointPoint.proceed();
        }
    }

    @Around("execution(public * cn.net.withub.ums.service.UmsAuthService.*(..))")
    public Object authServiceCall(ProceedingJoinPoint jointPoint) throws Throwable {
        switch (jointPoint.getSignature().getName()) {
            //增删改
            case "insert":
            case "delete":
            case "update":
            case "deleteById":
                return authCommonProcess(jointPoint);
            default:
                return jointPoint.proceed();
        }
    }

    private Object authCommonProcess(ProceedingJoinPoint jointPoint) throws Throwable {
        AuthType authType = (AuthType) ActionContext.getContext().getSession().get(UmsConstant.CURRENT_USER_AUTH.toString());
        if (authType == AuthType.ADMIN) {
            return jointPoint.proceed();
        } else {
            return null;
        }
    }
}
