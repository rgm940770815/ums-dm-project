/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service;

import cn.net.withub.ums.entity.UmsUserInfo;
import cn.net.withub.ums.entity.UmsUserInfoCriteria;
import cn.net.withub.ums.entity.UmsUserInfoOld;
import cn.net.withub.ums.entity.UmsUserInfoOldExample;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Diluka
 */
@Service
public interface UmsUserInfoOldService extends BaseService<UmsUserInfoOld, UmsUserInfoOldExample> {




    /**
     * 获取法院旧时人员
     * @param fydm
     * @return
     */
    List<UmsUserInfoOld> getUserOldList(String fydm,Map<String,Object> parameters);


}
