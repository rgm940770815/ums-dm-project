package cn.net.withub.ums.dao;

import cn.net.withub.ums.entity.UmsPartyOrganization;
import cn.net.withub.ums.entity.UmsPartyOrganizationCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsPartyOrganizationMapper {
    int countByExample(UmsPartyOrganizationCriteria example);

    int deleteByExample(UmsPartyOrganizationCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(UmsPartyOrganization record);

    int insertSelective(UmsPartyOrganization record);

    List<UmsPartyOrganization> selectByExample(UmsPartyOrganizationCriteria example);

    UmsPartyOrganization selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UmsPartyOrganization record, @Param("example") UmsPartyOrganizationCriteria example);

    int updateByExample(@Param("record") UmsPartyOrganization record, @Param("example") UmsPartyOrganizationCriteria example);

    int updateByPrimaryKeySelective(UmsPartyOrganization record);

    int updateByPrimaryKey(UmsPartyOrganization record);
}