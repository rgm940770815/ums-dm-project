package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.dao.UmsInstitutionPersonRecordMapper;
import cn.net.withub.ums.entity.UmsInstitutionPersonRecord;
import cn.net.withub.ums.entity.UmsInstitutionPersonRecordCriteria;
import cn.net.withub.ums.service.UmsInstitutionPersonRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UmsInstitutionPersonRecordServiceImpl implements UmsInstitutionPersonRecordService {

    @Autowired
    UmsInstitutionPersonRecordMapper mapper;

    @Override
    public int countByExample(UmsInstitutionPersonRecordCriteria example) {
        return mapper.countByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(UmsInstitutionPersonRecord record) {
        return mapper.insert(record);
    }

    @Override
    public int insertSelective(UmsInstitutionPersonRecord record) {
        return mapper.insertSelective(record);
    }

    @Override
    public List<UmsInstitutionPersonRecord> selectByExample(UmsInstitutionPersonRecordCriteria example) {
        return mapper.selectByExample(example);
    }

    @Override
    public UmsInstitutionPersonRecord selectByPrimaryKey(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(UmsInstitutionPersonRecord record) {
        return mapper.updateByPrimaryKeySelective(record);
    }
}
