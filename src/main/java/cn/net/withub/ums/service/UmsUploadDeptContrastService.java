package cn.net.withub.ums.service;

import cn.net.withub.ums.entity.UmsUploadDeptContrast;
import cn.net.withub.ums.entity.UmsUploadDeptContrastExample;

import java.util.List;

/**
 * Created by Administrator on 2016/1/29.
 */
public interface UmsUploadDeptContrastService {

    List<UmsUploadDeptContrast> selectByExample(UmsUploadDeptContrastExample example);

    int insert(UmsUploadDeptContrast umsUploadDeptContrast);

}
