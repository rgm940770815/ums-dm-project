package cn.net.withub.ums.sbrmpsyDao;

import cn.net.withub.ums.entity.sbrmpsy.TRmpsyCourtRelationship;
import cn.net.withub.ums.entity.sbrmpsy.TRmpsyCourtRelationshipExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRmpsyCourtRelationshipMapper {
    int countByExample(TRmpsyCourtRelationshipExample example);

    int deleteByExample(TRmpsyCourtRelationshipExample example);

    int insert(TRmpsyCourtRelationship record);

    int insertSelective(TRmpsyCourtRelationship record);

    List<TRmpsyCourtRelationship> selectByExample(TRmpsyCourtRelationshipExample example);

    int updateByExampleSelective(@Param("record") TRmpsyCourtRelationship record, @Param("example") TRmpsyCourtRelationshipExample example);

    int updateByExample(@Param("record") TRmpsyCourtRelationship record, @Param("example") TRmpsyCourtRelationshipExample example);
}