package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.dao.UmsUploadDeptContrastMapper;
import cn.net.withub.ums.entity.UmsUploadDeptContrast;
import cn.net.withub.ums.entity.UmsUploadDeptContrastExample;
import cn.net.withub.ums.service.UmsUploadDeptContrastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/1/31.
 */
@Service
public class UmsUploadDeptContrastServiceImpl implements UmsUploadDeptContrastService {

    @Autowired
    private UmsUploadDeptContrastMapper umsUploadDeptContrastMapper;

    @Override
    public List<UmsUploadDeptContrast> selectByExample(UmsUploadDeptContrastExample example) {
        return umsUploadDeptContrastMapper.selectByExample(example);
    }

    @Override
    public int insert(UmsUploadDeptContrast umsUploadDeptContrast) {
        return umsUploadDeptContrastMapper.insert(umsUploadDeptContrast);
    }
}
