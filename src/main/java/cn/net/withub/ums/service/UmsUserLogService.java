package cn.net.withub.ums.service;

import cn.net.withub.ums.entity.UmsUserLog;
import cn.net.withub.ums.entity.UmsUserLogExample;

import java.util.List;

/**
 * Created by Administrator on 2015/12/29.
 */
public interface UmsUserLogService {

    int insertSelective(UmsUserLog record);

    List<UmsUserLog> selectByExample(UmsUserLogExample example);

    UmsUserLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UmsUserLog record);
}
