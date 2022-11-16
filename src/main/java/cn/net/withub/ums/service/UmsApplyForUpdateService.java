package cn.net.withub.ums.service;

import cn.net.withub.ums.entity.UmsApplyForUpdate;
import cn.net.withub.ums.entity.UmsApplyForUpdateExample;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * Created by Cypress on 2016/12/7.
 */
public interface UmsApplyForUpdateService {

    int insert(UmsApplyForUpdate record);

    List<UmsApplyForUpdate> selectByExample(UmsApplyForUpdateExample example);

    int updateByPrimaryKeySelective(UmsApplyForUpdate record);

    int countByExample(UmsApplyForUpdateExample example);
}
