package cn.net.withub.ums.aop.auth;

import cn.net.withub.ums.auth.AuthorityHelper;
import cn.net.withub.ums.entity.UmsDepartmentCriteria;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/1/12.
 */
@Aspect
@Component
public class DeptAuthAspect {

    @Autowired
    private AuthorityHelper authorityHelper;

    @Before("execution(public * cn.net.withub.ums.service.UmsDepartmentService.selectByExample(..)) && args(criteria,..)" )
    public void userinfoViewQueryServiceCall(JoinPoint jointPoint, UmsDepartmentCriteria criteria) throws Exception {

        for (UmsDepartmentCriteria.Criteria c : criteria.getOredCriteria()) {
            authorityHelper.selectRestriction(c);
        }

    }

}
