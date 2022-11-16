package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.dao.UmsInstitutionUploadFileMapper;
import cn.net.withub.ums.entity.UmsInstitutionUploadFile;
import cn.net.withub.ums.entity.UmsInstitutionUploadFileCriteria;
import cn.net.withub.ums.service.UmsInstitutionUploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UmsInstitutionUploadFileServiceImpl implements UmsInstitutionUploadFileService {

    @Autowired
    UmsInstitutionUploadFileMapper mapper;

    @Override
    public int countByExample(UmsInstitutionUploadFileCriteria example) {
        return mapper.countByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(UmsInstitutionUploadFile record) {
        return mapper.insert(record);
    }

    @Override
    public List<UmsInstitutionUploadFile> selectByExample(UmsInstitutionUploadFileCriteria example) {
        return mapper.selectByExample(example);
    }

    @Override
    public UmsInstitutionUploadFile selectByPrimaryKey(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(UmsInstitutionUploadFile record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UmsInstitutionUploadFile record) {
        return mapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateByExampleSelective(UmsInstitutionUploadFile record, UmsInstitutionUploadFileCriteria example) {
        return mapper.updateByExampleSelective(record, example);
    }
}
