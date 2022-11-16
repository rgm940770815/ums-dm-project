/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service;

import cn.net.withub.ums.entity.UmsExternalCompanyInfo;
import cn.net.withub.ums.entity.UmsExternalCompanyInfoCriteria;
import java.util.List;

/**
 *
 * @author Diluka
 */
public interface UmsExternalCompanyInfoService extends BaseService<UmsExternalCompanyInfo, UmsExternalCompanyInfoCriteria> {

    List<UmsExternalCompanyInfo> findAll();

    List<UmsExternalCompanyInfo> findByName(String companyName);

    int insertSelective(UmsExternalCompanyInfo entity);
}
