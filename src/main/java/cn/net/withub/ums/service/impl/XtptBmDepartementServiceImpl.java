package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.dao.XtptBmDepartementMapper;
import cn.net.withub.ums.entity.UmsCode;
import cn.net.withub.ums.entity.XtptBmDepartement;
import cn.net.withub.ums.entity.XtptBmDepartementExample;
import cn.net.withub.ums.service.XtptBmDepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/1/8.
 */
@Service
public class XtptBmDepartementServiceImpl implements XtptBmDepartementService {

    @Autowired
    XtptBmDepartementMapper xtptBmDepartementMapper;

    @Override
    public List<XtptBmDepartement> selectByExample(XtptBmDepartementExample example) {
        return xtptBmDepartementMapper.selectByExample(example);
    }

    @Override
    public List<XtptBmDepartement> selectDistinctAllUser(Map param)
    {
        return xtptBmDepartementMapper.selectDistinctAllUser(param);
    }

    @Override
    public List<UmsCode> selectDistinctAllUserCode(Map param)
    {
        List<XtptBmDepartement> xtptBmDepartements = selectDistinctAllUser(param);
        List<UmsCode> umsCodes = new ArrayList<>();
        for (XtptBmDepartement xtptBmDepartement : xtptBmDepartements) {
            UmsCode umsCode = new UmsCode();
            umsCode.setId(xtptBmDepartement.getOrgCode());
            umsCode.setCodeName(xtptBmDepartement.getDeptName());
            umsCodes.add(umsCode);
        }
        return umsCodes;
    }
}
