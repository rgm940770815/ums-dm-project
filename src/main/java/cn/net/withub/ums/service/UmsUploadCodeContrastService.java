package cn.net.withub.ums.service;

import cn.net.withub.ums.entity.UmsUploadCodeContrast;
import cn.net.withub.ums.entity.UmsUploadCodeContrastExample;

import java.util.List;

/**
 * Created by Administrator on 2016/1/26.
 */
public interface UmsUploadCodeContrastService {

    List<UmsUploadCodeContrast> selectByExample(UmsUploadCodeContrastExample example);

    List<String> selectCodeType();
}
