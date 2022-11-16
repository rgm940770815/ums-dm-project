package cn.net.withub.ums.dao;

import cn.net.withub.ums.entity.UmsLogView;
import cn.net.withub.ums.entity.UmsLogViewCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UmsLogViewMapper {
    int countByExample(UmsLogViewCriteria example);

    int deleteByExample(UmsLogViewCriteria example);

    int insert(UmsLogView record);

    int insertSelective(UmsLogView record);

    List<UmsLogView> selectByExample(UmsLogViewCriteria example);

    List<UmsLogView> selectByExample(UmsLogViewCriteria example, RowBounds rowBounds);

    int updateByExampleSelective(@Param("record") UmsLogView record, @Param("example") UmsLogViewCriteria example);

    int updateByExample(@Param("record") UmsLogView record, @Param("example") UmsLogViewCriteria example);
}