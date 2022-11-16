package cn.net.withub.ums.service;

/**
 * Created by Administrator on 2015/12/23.
 */

import cn.net.withub.ums.entity.UmsTemporaryPosition;
import cn.net.withub.ums.entity.UmsTemporaryPositionExample;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/12/23.
 */
public interface UmsTemporaryPositionService {

    int insert(UmsTemporaryPosition entity);

    int delete(UmsTemporaryPosition entity);

    int deleteByUUID(UmsTemporaryPosition entity);

    int update(UmsTemporaryPosition entity);

    List<UmsTemporaryPosition> selectByExample(UmsTemporaryPositionExample example);

    List<Map> selectViewByExample(UmsTemporaryPositionExample example);

    int updatePartTimeJob(Map map);
}
