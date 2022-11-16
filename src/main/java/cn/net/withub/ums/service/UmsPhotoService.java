/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service;

import cn.net.withub.ums.entity.UmsPhoto;
import cn.net.withub.ums.entity.UmsPhotoCriteria;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Diluka
 */
public interface UmsPhotoService extends BaseService<UmsPhoto, UmsPhotoCriteria> {

    public List<UmsPhoto> selectByExample(UmsPhotoCriteria example,RowBounds rowBounds);

    public List<String> selectIsValidUser(RowBounds rowBounds);

    public UmsPhoto selectByIdWithNOAspect(String id);

    public List<Map> selectByDept(Map map);

}
