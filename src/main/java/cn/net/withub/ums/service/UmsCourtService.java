/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service;

import cn.net.withub.ums.entity.UmsCourt;
import cn.net.withub.ums.entity.UmsCourtCriteria;
import java.util.List;

/**
 *
 * @author Diluka
 */
public interface UmsCourtService extends BaseService<UmsCourt, UmsCourtCriteria> {

    List<UmsCourt> listAll();

    Integer courtNo2CourtStdNo(Integer courtNo);

    String courtNo2CourtCode(Integer courtNo);

    //查询法院名称(courtStdNo)
    String courtTexForCourtStdNo(Integer courtStdNo);

    Integer courtNoForCourtStdNo(Integer courtStdNo);

    String courtTexForCourtCode(String courtCode);


}
