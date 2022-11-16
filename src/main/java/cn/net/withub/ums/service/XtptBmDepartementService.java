package cn.net.withub.ums.service;

import cn.net.withub.ums.entity.UmsCode;
import cn.net.withub.ums.entity.XtptBmDepartement;
import cn.net.withub.ums.entity.XtptBmDepartementExample;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/1/8.
 */
public interface XtptBmDepartementService {

    public List<XtptBmDepartement> selectByExample(XtptBmDepartementExample example);

    public List<XtptBmDepartement> selectDistinctAllUser(Map param);

    List<UmsCode> selectDistinctAllUserCode(Map param);
}
