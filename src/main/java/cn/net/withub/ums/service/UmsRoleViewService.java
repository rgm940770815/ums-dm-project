/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service;

import cn.net.withub.ums.entity.UmsRoleView;
import cn.net.withub.ums.entity.UmsRoleViewCriteria;
import java.util.List;

/**
 *
 * @author Diluka
 */
public interface UmsRoleViewService extends BaseService<UmsRoleView, UmsRoleViewCriteria> {

    List<UmsRoleView> getAll();
}
