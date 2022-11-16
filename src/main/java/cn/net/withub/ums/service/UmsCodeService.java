/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service;

import cn.net.withub.ums.entity.UmsCode;
import cn.net.withub.ums.entity.UmsCodeCriteria;

import java.util.List;
import java.util.Map;

/**
 * @author Diluka
 */
public interface UmsCodeService extends BaseService<UmsCode, UmsCodeCriteria> {

    List<UmsCode> selectCodesByType(Integer typeId);

    List<UmsCode> selectCodesByType(Integer typeId, String parentId);

    List<UmsCode> selectCodesByType(List<Integer> ids);

    List<Map<String, Object>> getProvince();

    List<Map<String, Object>> getCity(int provinceID);

    List<Map<String, Object>> getArea(int cityID);

    List<UmsCode> selectByExample(UmsCodeCriteria criteria);

    Map<Integer, List<Map<String, String>>> getReport();
}
