package cn.net.withub.ums.aop.auth;

import cn.net.withub.ums.auth.AuthorityHelper;
import cn.net.withub.ums.common.UmsConstant;
import cn.net.withub.ums.dao.UmsUserInfoMapper;
import cn.net.withub.ums.entity.UmsUserInfo;
import cn.net.withub.ums.entity.UmsUserInfoView;
import cn.net.withub.ums.entity.UmsUserInfoViewCriteria;
import com.opensymphony.xwork2.ActionContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 权限拦截AOP Created by D.Yang on 2015/1/19 0019.
 */
@Aspect
@Component
public class UserInfoAuthAspect {

    @Autowired
    private AuthorityHelper authorityHelper;

    @Autowired
    private UmsUserInfoMapper userInfoMapper;

    @Before("execution(public * cn.net.withub.ums.service.UmsUserInfoViewService.search(..)) && args(criteria,..)")
    public void userinfoViewQueryServiceCall(JoinPoint jointPoint, UmsUserInfoViewCriteria criteria) throws Exception {

        for (UmsUserInfoViewCriteria.Criteria c : criteria.getOredCriteria()) {
            authorityHelper.selectRestriction(c);
        }

    }


    @Before("execution(public * cn.net.withub.ums.service.UmsUserInfoViewService.seleteJurorInfo(..))" +
                    "&& args(criteria,..)")
    public void seleteJurorInfoCall(JoinPoint jointPoint, UmsUserInfoViewCriteria criteria) throws Exception {

        for (UmsUserInfoViewCriteria.Criteria c : criteria.getOredCriteria()) {
            authorityHelper.selectRestriction(c);
        }

    }

    @Before("execution(public * cn.net.withub.ums.service.UmsUserInfoViewService.seleteOffStaffInfo(..))" +
            "&& args(criteria,..)")
    public void seleteOffStaffInfoCall(JoinPoint jointPoint, UmsUserInfoViewCriteria criteria) throws Exception {

        for (UmsUserInfoViewCriteria.Criteria c : criteria.getOredCriteria()) {
            authorityHelper.selectRestriction(c);
        }

    }

    @Before(" (execution(public * cn.net.withub.ums.service.UmsUserInfoViewService.searchBySort(..)) " +
            " || execution(public * cn.net.withub.ums.service.UmsUserInfoViewService.simpleSearchBySort(..))" +
            " || execution(public * cn.net.withub.ums.service.UmsUserInfoViewService.countForAspect(..))" +
            " )" +
            "&& args(criteria,..)")
    public void umsUserInfoView(JoinPoint jointPoint, UmsUserInfoViewCriteria criteria) throws Exception {

        for (UmsUserInfoViewCriteria.Criteria c : criteria.getOredCriteria()) {
            authorityHelper.selectRestriction(c);
        }

    }

    @Around("execution(public * cn.net.withub.ums.service.UmsUserInfoViewService.selectById(..))")
    public Object userinfoViewQueryOneServiceCall(ProceedingJoinPoint jointPoint) throws Throwable {
        UmsUserInfoView entity = (UmsUserInfoView) jointPoint.proceed();

        if (entity != null) {
            List<Integer> courtNoList = authorityHelper.accessibleCourtNoList("查看");
            if (courtNoList.contains(-9999) || courtNoList.contains(entity.getCourtNo())) {//不要把管理员忘了 
                return entity;
            } else if (authorityHelper.isSelfOnly(courtNoList)) {//默认权限
                UmsUserInfoView u = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
                if (u.getId().equalsIgnoreCase(entity.getId())) {
                    return entity;
                }
            }
        }

        return null;
    }

    @Around("execution(public int cn.net.withub.ums.service.UmsUserInfoService.*(..))"
            + "&& !execution(* cn.net.withub.ums.service.UmsUserInfoService.insertNoAspect(..))"
            + "&& !execution(* cn.net.withub.ums.service.UmsUserInfoService.selectByExampleWithNoAspect(..))"
            + " && args(entity)")
    public Object userinfoServiceCall(ProceedingJoinPoint jointPoint, Object entity) throws Throwable {
        UmsUserInfoView u = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());

        switch (jointPoint.getSignature().getName()) {
            case "insert":
            case "update":
                break;
            case "delete":
                entity = userInfoMapper.selectByPrimaryKey(((UmsUserInfo) entity).getId());
                break;
            case "deleteById":
                entity = userInfoMapper.selectByPrimaryKey((String) entity);//这里传的是ID字符串
                break;
            case "selectById":
                entity = jointPoint.proceed();
                if (entity == null) {
                    //无此用户
                    return null;
                }
                List<Integer> courtNoList = authorityHelper.accessibleCourtNoList("查看");
                //默认权限
                if (authorityHelper.isSelfOnly(courtNoList) && u.getId().equalsIgnoreCase(((UmsUserInfo) entity).getId())) {
                    return entity;
                }

                if (!courtNoList.contains(((UmsUserInfo) entity).getCourtNo())) {
                    //非法访问
                    return null;
                }
            case "changePassword":
                if (!u.getId().equalsIgnoreCase((String) entity)) {
                    //非法访问,只能改自己的密码
                    return false;
                }
                break;
            default:
                return jointPoint.proceed();
        }

        if (authorityHelper.entityCheck(entity)) {
            return jointPoint.proceed();
        } else {
            return -1;
        }
    }
}
