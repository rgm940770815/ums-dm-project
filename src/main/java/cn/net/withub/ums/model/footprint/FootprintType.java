/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.model.footprint;

import cn.net.withub.ums.dao.UmsAbroadInfoViewMapper;
import cn.net.withub.ums.dao.UmsAdministrativeJobViewMapper;
import cn.net.withub.ums.dao.UmsAssessInfoViewMapper;
import cn.net.withub.ums.dao.UmsDegreeInfoViewMapper;
import cn.net.withub.ums.dao.UmsLegalJobViewMapper;
import cn.net.withub.ums.dao.UmsRankInfoViewMapper;
import cn.net.withub.ums.dao.UmsTrainingInfoViewMapper;
import cn.net.withub.ums.dao.UmsUserInfoViewMapper;

/**
 *
 * @author Diluka
 */
public enum FootprintType {

    ////////////////////////////////////////基本表
    ENTER_TIME("进院时间", UmsUserInfoViewMapper.class),
    ////////////////////////////////////////附表
    ADMIN_JOB("行政职务", UmsAdministrativeJobViewMapper.class),
    LEGAL_JOB("法律职务", UmsLegalJobViewMapper.class),
    RANK("职级", UmsRankInfoViewMapper.class),
    TRAINING("培训", UmsTrainingInfoViewMapper.class),
    ASSESS("考核", UmsAssessInfoViewMapper.class),
    DEGREE("学位", UmsDegreeInfoViewMapper.class),
    ABROAD("出国", UmsAbroadInfoViewMapper.class), ////////////////////////////////////////
    ;

    public final String name;
    public final Class<?> mapperClass;

    private FootprintType(String name, Class<?> mapperClass) {
        this.name = name;
        this.mapperClass = mapperClass;
    }

    @Override
    public String toString() {
        return name;
    }

}
