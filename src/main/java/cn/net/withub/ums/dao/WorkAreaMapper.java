package cn.net.withub.ums.dao;

import cn.net.withub.ums.entity.WorkArea;
import cn.net.withub.ums.entity.WorkAreaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WorkAreaMapper {
    int countByExample(WorkAreaExample example);

    int deleteByExample(WorkAreaExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WorkArea record);

    int insertSelective(WorkArea record);

    List<WorkArea> selectByExample(WorkAreaExample example);

    WorkArea selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WorkArea record, @Param("example") WorkAreaExample example);

    int updateByExample(@Param("record") WorkArea record, @Param("example") WorkAreaExample example);

    int updateByPrimaryKeySelective(WorkArea record);

    int updateByPrimaryKey(WorkArea record);
}