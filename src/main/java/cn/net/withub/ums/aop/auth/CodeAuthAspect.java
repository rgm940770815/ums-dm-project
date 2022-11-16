/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.aop.auth;

import cn.net.withub.ums.auth.AuthorityHelper;
import cn.net.withub.ums.common.UmsConstant;
import cn.net.withub.ums.entity.UmsCode;
import cn.net.withub.ums.entity.UmsUserInfoView;
import com.opensymphony.xwork2.ActionContext;
import java.util.List;
import java.util.Objects;
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
public class CodeAuthAspect {

    @Autowired
    private AuthorityHelper authorityHelper;

    @Around("execution(public * cn.net.withub.ums.service.UmsCodeService.selectCodesByType(..))"
            + " && args(typeId)")
    public Object codeSelectCalls(ProceedingJoinPoint joinPoint, Integer typeId) throws Throwable {
        List<UmsCode> list = (List<UmsCode>) joinPoint.proceed();

        switch (typeId) {
            case 1:
                filterCourts(list);
                break;

        }

        return list;
    }

    private void filterCourts(List<UmsCode> list) {
        List<Integer> courtNoList = authorityHelper.accessibleCourtNoList("查看");

        if (courtNoList.contains(-9999)) { //超级管理员来了
            return;
        } else if (authorityHelper.isSelfOnly(courtNoList)) {
            UmsUserInfoView u = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
            for (int i = list.size() - 1; i >= 0; i--) {
                if (!Objects.equals(u.getCourtNo(), list.get(i).getId())) {//麻烦，删掉其他的- -
                    list.remove(i);
                }
            }
        }

        for (int i = list.size() - 1; i >= 0; i--) {
            if (!courtNoList.contains(list.get(i).getId())) {
                list.remove(i);
            }
        }
    }

}
