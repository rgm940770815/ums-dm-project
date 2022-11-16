/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service.footprint;

import cn.net.withub.ums.entity.UmsUserInfo;
import cn.net.withub.ums.entity.UmsUserInfoView;
import cn.net.withub.ums.model.footprint.Footprint;
import java.util.List;

/**
 *
 * @author Diluka
 */
public interface FootprintService {

    /**
     * 获取用户足迹
     *
     * @param userId
     * @return
     */
    List<Footprint> userFootprints(String userId);

    /**
     * 获取用户足迹
     *
     * @param userInfo
     * @return
     */
    List<Footprint> userFootprints(UmsUserInfo userInfo);

    /**
     * 获取用户足迹
     *
     * @param userInfo
     * @return
     */
    List<Footprint> userFootprints(UmsUserInfoView userInfo);

}
