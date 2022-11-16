/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.aop.auth;

import cn.net.withub.ums.auth.AuthorityHelper;
import cn.net.withub.ums.common.UmsConstant;
import cn.net.withub.ums.entity.UmsUserInfoView;
import com.opensymphony.xwork2.ActionContext;
import java.util.ArrayList;
import java.util.List;
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
public class StatAuthAspect {

    @Autowired
    private AuthorityHelper authorityHelper;

    @Around("execution (public * cn.net.withub.ums.service.statistics.StatisticsService.*Chart(..))"
            + " && args(courts)")
    public Object statSelectServiceCall(ProceedingJoinPoint joinPoint, List<Integer> courts) throws Throwable {

        List<Integer> courtNoList = authorityHelper.accessibleCourtNoList("查看");

        if (courtNoList.contains(-9999)) { //超级管理员来了
            courts = null;
        } else if (authorityHelper.isSelfOnly(courtNoList)) {
            UmsUserInfoView u = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
            courts = new ArrayList<>();
            courts.add(u.getCourtNo());
        } else {
            courts = courtNoList;
        }

        //如果courts的size==0，那么执行这句SQL会出现in后面没有东西的错误语句，所以直接返回空集合就行了
        if (courts != null && courts.isEmpty()) {
            return new ArrayList<>();
        }

        return joinPoint.proceed(new Object[]{courts});
    }

}
