package cn.net.withub.ums.dao;

import cn.net.withub.ums.entity.UmsSubitemAudit;
import cn.net.withub.ums.entity.UmsSubitemAuditExample;
import cn.net.withub.ums.entity.UmsSubitemAuditWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsSubitemAuditMapper {
    int countByExample(UmsSubitemAuditExample example);

    int deleteByExample(UmsSubitemAuditExample example);

    int deleteByPrimaryKey(String id);

    int insert(UmsSubitemAuditWithBLOBs record);

    int insertSelective(UmsSubitemAuditWithBLOBs record);

    List<UmsSubitemAuditWithBLOBs> selectByExampleWithBLOBs(UmsSubitemAuditExample example);

    List<UmsSubitemAudit> selectByExample(UmsSubitemAuditExample example);

    UmsSubitemAuditWithBLOBs selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UmsSubitemAuditWithBLOBs record, @Param("example") UmsSubitemAuditExample example);

    int updateByExampleWithBLOBs(@Param("record") UmsSubitemAuditWithBLOBs record, @Param("example") UmsSubitemAuditExample example);

    int updateByExample(@Param("record") UmsSubitemAudit record, @Param("example") UmsSubitemAuditExample example);

    int updateByPrimaryKeySelective(UmsSubitemAuditWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(UmsSubitemAuditWithBLOBs record);

    int updateByPrimaryKey(UmsSubitemAudit record);
}