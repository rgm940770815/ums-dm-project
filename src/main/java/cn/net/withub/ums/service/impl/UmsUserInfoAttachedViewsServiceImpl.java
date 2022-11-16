/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.dao.attach.UmsUserInfoAttachedViewsMapper;
import cn.net.withub.ums.log.UmsLogger;
import cn.net.withub.ums.service.UmsUserInfoAttachedViewsService;
import java.lang.reflect.Method;
import java.util.List;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UmsUserInfoAttachedViewsServiceImpl implements UmsUserInfoAttachedViewsService {

    @Autowired
    private UmsUserInfoAttachedViewsMapper attachedViewsMapper;

    @Autowired
    UmsLogger umsLogger;

    @Override
    public <T> List<T> attachedView(String viewName, String userId) {
        return attachedView(viewName, userId, RowBounds.DEFAULT);
    }

    @Override
    public <T> List<T> attachedView(String viewName, String userId, RowBounds rowBounds) {
        List<T> list = null;
        try {
            Method method = UmsUserInfoAttachedViewsMapper.class.getMethod(viewName, String.class, RowBounds.class);
            list = (List<T>) method.invoke(attachedViewsMapper, userId, rowBounds);
        } catch (Exception e) {
            umsLogger.log(UmsLogger.LogType.Suspicious, String.format("可疑的操作：v: %s, u: %s", viewName, userId));
        }
        return list;
    }

    @Override
    public int attachedCount(String viewName, String userId) {
        int c = 0;
        try {
            Method method = UmsUserInfoAttachedViewsMapper.class.getMethod(viewName + "Count", String.class);
            c = (int) method.invoke(attachedViewsMapper, userId);
        } catch (Exception e) {
            umsLogger.log(UmsLogger.LogType.Suspicious, String.format("可疑的操作：v: %s, u: %s", viewName, userId));
        }
        return c;
    }

}
