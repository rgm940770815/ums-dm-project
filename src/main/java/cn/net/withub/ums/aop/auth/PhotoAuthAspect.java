/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.aop.auth;

import cn.net.withub.ums.auth.AuthorityHelper;
import cn.net.withub.ums.common.UmsConstant;
import cn.net.withub.ums.dao.UmsPhotoMapper;
import cn.net.withub.ums.entity.UmsPhoto;
import cn.net.withub.ums.entity.UmsUserInfo;
import cn.net.withub.ums.entity.UmsUserInfoView;
import cn.net.withub.ums.service.UmsUserInfoService;
import com.opensymphony.xwork2.ActionContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 * @author Diluka
 */
@Aspect
@Component
public class PhotoAuthAspect {

    @Autowired
    private AuthorityHelper authorityHelper;

    @Autowired
    private UmsPhotoMapper photoMapper;

    @Autowired
    private UmsUserInfoService userInfoService;

    @Around("execution(public * cn.net.withub.ums.service.UmsPhotoService.*(..))"
            + " && args(entity,..)" +
            "&& !execution(* cn.net.withub.ums.service.UmsPhotoService*.selectByIdWithNOAspect(..))")
    public Object photoCalls(ProceedingJoinPoint joinPoint, Object entity) throws Throwable {

        switch (joinPoint.getSignature().getName()) {
            case "insert":
            case "update":
                if (authorityHelper.entityCheck(entity)) {
                    return joinPoint.proceed();
                } else {
                    return -1;
                }

            case "deleteById":
                UmsPhoto p = photoMapper.selectByPrimaryKey((String) entity);
                if (p != null && canDealWith(p, "编辑")) {
                    return joinPoint.proceed();
                } else {
                    return -1;
                }

            case "selectById":

                entity = joinPoint.proceed();
                if (entity != null
//                        && canDealWith((UmsPhoto) entity, "查看")
                        ) {
                    return entity;
                } else {
                    return null;
                }
            default:
                return joinPoint.proceed();
        }

    }

    private boolean canDealWith(UmsPhoto photo, String authName) {
        List<Integer> courtNoList = authorityHelper.accessibleCourtNoList(authName);
        // 获取被操作人员的法院20190311
        UmsUserInfo umsUserInfo = userInfoService.selectById(photo.getUserId());
        Integer bczrfydm = umsUserInfo.getCourtNo();
        if (courtNoList.contains(-9999)) {
            return true;
        } else if (courtNoList.size() == 1 && courtNoList.get(0) == -1) {
            UmsUserInfoView u = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
            return u.getId().equalsIgnoreCase(photo.getUserId());
        } else {
//            return courtNoList.contains(photo.getCourtNo());// 操作人的可操作法院包含被操作人的图片的法院
            // 改成操作人可操作的法院,包含被操作人的法院20190311
            return courtNoList.contains(bczrfydm);
        }
    }

}
