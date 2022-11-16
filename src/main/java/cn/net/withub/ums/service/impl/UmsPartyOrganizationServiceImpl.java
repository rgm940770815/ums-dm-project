package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.dao.UmsPartyOrganizationMapper;
import cn.net.withub.ums.entity.UmsPartyOrganization;
import cn.net.withub.ums.entity.UmsPartyOrganizationCriteria;
import cn.net.withub.ums.service.UmsPartyOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UmsPartyOrganizationServiceImpl implements UmsPartyOrganizationService {

    @Autowired
    UmsPartyOrganizationMapper mapper;

    @Override
    public int countByExample(UmsPartyOrganizationCriteria example) {
        return mapper.countByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(UmsPartyOrganization record) {
        return mapper.insert(record);
    }

    @Override
    public List<UmsPartyOrganization> selectByExample(UmsPartyOrganizationCriteria example) {
        return mapper.selectByExample(example);
    }

    @Override
    public UmsPartyOrganization selectByPrimaryKey(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(UmsPartyOrganization record) {
        return mapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeySelective(UmsPartyOrganization record) {
        return mapper.updateByPrimaryKeySelective(record);
    }
}
