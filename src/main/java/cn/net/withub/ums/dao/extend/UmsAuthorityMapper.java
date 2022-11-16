/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.dao.extend;

import cn.net.withub.ums.entity.UmsAuth;
import cn.net.withub.ums.entity.UmsRole;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 *
 * @author Diluka
 */
public interface UmsAuthorityMapper {

    @Select("select * from ums_role where id in (select role_id from ums_user_role where user_id = #{userId})")
    @ResultMap("cn.net.withub.ums.dao.UmsRoleMapper.BaseResultMap")
    List<UmsRole> userRoles(String userId);

    @Select("insert into ums_user_role (role_id,user_id) values (#{roleId},#{userId})")
    @ResultMap("cn.net.withub.ums.dao.UmsUserRoleMapper.BaseResultMap")
    int addUserRoles(Integer roleId, String userId);

    @Select("update ums_user_role set role_id=#{roleId} where user_id=#{userId}")
    @ResultMap("cn.net.withub.ums.dao.UmsUserRoleMapper.BaseResultMap")
    int updateUserRoles(Integer roleId, String userId);

    @Select("select * from ums_auth")
    @ResultMap("cn.net.withub.ums.dao.UmsAuthMapper.BaseResultMap")
    List<UmsAuth> allAuths();

    @Select("select * from ums_auth where auth_name = #{authName}")
    @ResultMap("cn.net.withub.ums.dao.UmsAuthMapper.BaseResultMap")
    UmsAuth selectAuthByName(String authName);

//    @Select("select id from ums_user_info where court_no = #{courtNo}")
//    List<String> userIdInCourtNo(Integer courtNo);
//
//    @Select("select id from ums_user_info where court_no = #{courtNo} and department = #{deptNo}")
//    List<String> userIdInDeptNo(Integer courtNo, Integer deptNo);
//
//    List<String> userIdInAreaNo(Integer areaNo);
}
