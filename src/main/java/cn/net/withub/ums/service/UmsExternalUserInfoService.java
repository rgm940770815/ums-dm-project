/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service;

import cn.net.withub.ums.entity.UmsExternalUserInfo;
import cn.net.withub.ums.entity.UmsExternalUserInfoCriteria;

/**
 * @author Diluka
 */
public interface UmsExternalUserInfoService extends BaseService<UmsExternalUserInfo, UmsExternalUserInfoCriteria> {
    int updateEnabled(UmsExternalUserInfo info);

    int insertSelective(UmsExternalUserInfo entity);

    int updateByPrimaryKeySelective(UmsExternalUserInfo entity);
}
