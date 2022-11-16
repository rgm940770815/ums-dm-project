/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service;

import cn.net.withub.ums.entity.UmsExternalJobType;
import cn.net.withub.ums.entity.UmsExternalJobTypeCriteria;
import java.util.List;

/**
 *
 * @author Diluka
 */
public interface UmsExternalJobTypeService extends BaseService<UmsExternalJobType, UmsExternalJobTypeCriteria> {

    List<UmsExternalJobType> findAll();

    List<UmsExternalJobType> findBySrcId(Integer srcId);

    List<UmsExternalJobType> findByPid(Integer pid);

    List<UmsExternalJobType> findRootBySrcId(Integer srcId);
}
