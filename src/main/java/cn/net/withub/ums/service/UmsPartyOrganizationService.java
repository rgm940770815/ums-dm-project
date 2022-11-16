package cn.net.withub.ums.service;

import cn.net.withub.ums.entity.UmsPartyOrganization;
import cn.net.withub.ums.entity.UmsPartyOrganizationCriteria;

import java.util.List;

public interface UmsPartyOrganizationService {

    int countByExample(UmsPartyOrganizationCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(UmsPartyOrganization record);

    List<UmsPartyOrganization> selectByExample(UmsPartyOrganizationCriteria example);

    UmsPartyOrganization selectByPrimaryKey(String id);

    int updateByPrimaryKey(UmsPartyOrganization record);

    int updateByPrimaryKeySelective(UmsPartyOrganization record);

}
