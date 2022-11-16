package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.dao.UmsUploadCodeContrastMapper;
import cn.net.withub.ums.entity.UmsUploadCodeContrast;
import cn.net.withub.ums.entity.UmsUploadCodeContrastExample;
import cn.net.withub.ums.service.UmsUploadCodeContrastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/1/26.
 */
@Service
public class UmsUploadCodeContrastServiceImpl implements UmsUploadCodeContrastService {

    @Autowired
    UmsUploadCodeContrastMapper umsUploadCodeContrastMapper;

    @Override
    public List<UmsUploadCodeContrast> selectByExample(UmsUploadCodeContrastExample example) {
        return umsUploadCodeContrastMapper.selectByExample(example);
    }

    @Override
    public List<String> selectCodeType() {
        return umsUploadCodeContrastMapper.selectCodeType();
    }


}
