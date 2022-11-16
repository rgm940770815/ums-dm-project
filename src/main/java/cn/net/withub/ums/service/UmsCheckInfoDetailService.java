package cn.net.withub.ums.service;

import cn.net.withub.ums.entity.UmsCheckInfoDetail;
import cn.net.withub.ums.entity.UmsCheckInfoDetailExample;

import java.util.List;

public interface UmsCheckInfoDetailService {

    int countByExample(UmsCheckInfoDetailExample example);


    int insert(UmsCheckInfoDetail record);


    int deleteByExample(UmsCheckInfoDetailExample example);

    List<UmsCheckInfoDetail> selectByExample(UmsCheckInfoDetailExample example);

}
