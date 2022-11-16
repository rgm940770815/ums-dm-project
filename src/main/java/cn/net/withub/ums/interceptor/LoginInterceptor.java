/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.interceptor;

import cn.net.withub.ums.common.UmsConstant;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.springframework.stereotype.Component;

/**
 *
 * @author Diluka
 */
@Component
public class LoginInterceptor extends MethodFilterInterceptor {

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        Object u = invocation.getInvocationContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
        String name = invocation.getInvocationContext().getName();
        if(name.equals("showInfoForCourt")){
            return doIntercept(invocation);
        }
        if(name.equals("getRyList")){
            return doIntercept(invocation);
        }
        else if(name.equals("downLoadFj"))
        {
            return doIntercept(invocation);
        } else if(name.equals("getRsaModule"))
        {
            return doIntercept(invocation);
        }
        if (u == null) {
            return Action.LOGIN;
        } else {
            return doIntercept(invocation);
        }
    }

    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        return invocation.invoke();
    }

}
