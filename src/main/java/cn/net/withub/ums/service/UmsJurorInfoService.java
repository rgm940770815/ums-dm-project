/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service;

import cn.net.withub.ums.entity.UmsJurorInfo;
import cn.net.withub.ums.entity.UmsJurorInfoCriteria;

/**
 * @author Diluka
 */
public interface UmsJurorInfoService extends BaseService<UmsJurorInfo, UmsJurorInfoCriteria> {

    boolean hasJurorId(String userId);

    int updateByPrimaryKeySelective(UmsJurorInfo user);

    public int insertNoAspect(UmsJurorInfo entity);

}
