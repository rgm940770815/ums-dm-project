/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service;

import cn.net.withub.ums.entity.UmsCodeType;
import cn.net.withub.ums.entity.UmsCodeTypeCriteria;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Diluka
 */
public interface UmsCodeTypeService extends BaseService<UmsCodeType, UmsCodeTypeCriteria> {

    String codeTypeName(Integer typeId);

    List<Map> getAllCodeType();

    List<Map<String,Object>> getAllCodeTypeForBw();

    List<Map<String,Object>> selectCodeTypeByColumn(String column);

    List<Map<String, Object>> getCodeWithNotNullForPsy();
}
