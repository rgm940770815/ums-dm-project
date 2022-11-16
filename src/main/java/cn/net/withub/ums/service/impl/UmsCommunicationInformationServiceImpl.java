package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.dao.UmsCommunicationInformationMapper;
import cn.net.withub.ums.entity.UmsCommunicationInformation;
import cn.net.withub.ums.entity.UmsCommunicationInformationCriteria;
import cn.net.withub.ums.service.UmsCommunicationInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UmsCommunicationInformationServiceImpl implements UmsCommunicationInformationService {

    @Autowired
    UmsCommunicationInformationMapper mapper;


    @Override
    public int insert(UmsCommunicationInformation record) {
        return mapper.insert(record);
    }

    @Override
    public int insertSelective(UmsCommunicationInformation record) {
        return mapper.insertSelective(record);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(UmsCommunicationInformation record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<UmsCommunicationInformation> selectByExample(UmsCommunicationInformationCriteria example) {
        return mapper.selectByExample(example);
    }

    @Override
    public UmsCommunicationInformation selectByPrimaryKey(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int countByExample(UmsCommunicationInformationCriteria example) {
        return mapper.countByExample(example);
    }
}
