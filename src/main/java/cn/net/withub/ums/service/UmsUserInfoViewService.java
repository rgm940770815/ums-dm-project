/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service;

import cn.net.withub.ums.entity.UmsUserInfoExternalCriteria;
import cn.net.withub.ums.entity.UmsUserInfoView;
import cn.net.withub.ums.entity.UmsUserInfoViewCriteria;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Diluka
 */
public interface UmsUserInfoViewService extends BaseService<UmsUserInfoView, UmsUserInfoViewCriteria> {

    /**
     * 新功能不再使用Map组成查询条件
     *
     * @param conditions
     * @param page
     * @param pageSize
     * @return
     * @deprecated
     */
    @Deprecated
    List<UmsUserInfoView> search(Map<String, Object[]> conditions, int page, int pageSize);

    /**
     * 新功能不再使用Map组成查询条件
     *
     * @param conditions
     * @return
     * @deprecated
     */
    @Deprecated
    int count(Map<String, Object[]> conditions);

    /**
     * 用户登录
     *
     * @param courtStdNo 承办法院编号
     * @param username 用户名
     * @param password 密码
     * @return
     */
    UmsUserInfoView login(Integer courtStdNo, String username, String password);

    /**
     * 管理员登录
     * @param courtStdNo 承办法院编号
     * @param username 用户名
     * @param password 密码
     * @return
     */
    UmsUserInfoView login4Admin(Integer courtStdNo, String username, String password);


    /**新加
     * 用户登录
     *
     * @param courtStdNo 承办法院编号
     * @param user_id 用户id
     * @param password 密码
     * @return
     */
    UmsUserInfoView loginInterface(Integer courtStdNo, String user_id, String password);

    //更新管理员密码
    int updatePassword(String userCode,  String password)throws Exception;

    List<UmsUserInfoView> newEnterPersonnelList(int size,Integer courtNo);

    /**
     * 通过登录名和法院信息获取用户
     * @param courtStdNo
     * @param username
     * @return
     */
    Map<String,Object> searchByLoginInfo(Integer courtStdNo, String username ,Integer valid);

    /**
     * 判断user_info表username是否重复
     * @param courtStdNo
     * @param username
     * @param valid
     * @return
     */
    Map<String,Object> searchByLoginInfo_2(Integer courtStdNo, String username ,Integer valid);

    /**
     * 编辑时,判断username是否重复
     * @param username
     * @param id
     * @return
     */
    Map<String,Object> searchByLoginInfo_umsinfo(String username ,String id);

    /**
     * 编辑时,查询该username,是否等于自己
     * @param username
     * @param id
     * @return
     */
    Map<String,Object> searchByLoginInfo_umsinfo_2(String username ,String id);

    List<UmsUserInfoView> selectViewByExample(UmsUserInfoViewCriteria criteria);

    List<UmsUserInfoView> selectCustom(String info, RowBounds rowBounds);
    int countCustom(String info);

    List<UmsUserInfoView> selectCustomN(String info, RowBounds rowBounds);
    List<UmsUserInfoView> selectCustomN_(String info, RowBounds rowBounds);
    int countCustomN(String info);


    List<UmsUserInfoView> selectCustomM(String info, RowBounds rowBounds);

    List<Map<String,Object>> selectCustomF(String info);

    List<Map<String,Object>> selectCustomG(String info);

    List<UmsUserInfoView> selectCustomK(String info, RowBounds rowBounds);

    int countCustomK(String info);

    int countCustomM(String info);
    /**
     * 陪审员联查信息
     * @param example UmsUserInfoViewCriteria
     * @return
     */
    List<Map> seleteJurorInfo(UmsUserInfoViewCriteria example);

    /**
     * 编外人员联查信息
     * @param example UmsUserInfoViewCriteria
     * @return
     */
    List<Map> seleteOffStaffInfo(UmsUserInfoViewCriteria example);

    List<UmsUserInfoView>  searchBySort(UmsUserInfoViewCriteria example,RowBounds rowBounds);

    List<UmsUserInfoView>  simpleSearchBySort(UmsUserInfoViewCriteria example,RowBounds rowBounds);

    // 待完善资料的人员列表
    List<UmsUserInfoView>  searchBySort_dwszl(UmsUserInfoViewCriteria example,RowBounds rowBounds);

    List<UmsUserInfoView>  simpleSearchBySort_dwszl(UmsUserInfoViewCriteria example,RowBounds rowBounds);

    // 待完善资料的人员数量
    int count_dwszl(UmsUserInfoViewCriteria criteria);

    List<Map> selectBatchById(List ids);

    Integer unionCount(Map<String,Object> map);

    List<UmsUserInfoView> unionQuery(Map<String,Object> map);

    Integer incompleteCountForLegal(Map<String,Object> map);

    List<UmsUserInfoView> incompleteQueryForLegal(Map<String,Object> map);

    Integer incompleteCountForPolitical(Map<String,Object> map);

    List<UmsUserInfoView> incompleteQueryForPolitical(Map<String,Object> map);

    Integer incompleteCountForFamily(Map<String,Object> map);

    List<UmsUserInfoView> incompleteQueryForFamily(Map<String,Object> map);

    Integer incompleteCountForYefg(Map<String,Object> map);

    List<UmsUserInfoView> incompleteQueryForYefg(Map<String,Object> map);

    int countForAspect(UmsUserInfoViewCriteria criteria);

    List<UmsUserInfoView> getInfoByExample(UmsUserInfoViewCriteria example,RowBounds rowBounds);

    // 反查法官列表
    List<UmsUserInfoView> selectFgList_Fc(Map<String, Object> param);
    // 反查法官数量
    Integer selectFgCount_Fc(Map<String, Object> param);

    int countByExampleLeftJoinExternal(UmsUserInfoExternalCriteria example);

    List<UmsUserInfoView> selectByExampleLeftJoinExternal(UmsUserInfoExternalCriteria example);

    int countByExampleLeftJoinPsy(UmsUserInfoExternalCriteria example);

    List<UmsUserInfoView> selectByExampleLeftJoinPsy(UmsUserInfoExternalCriteria example);

    List<Map<String,Object>> selectRyList(Map<String,Object> map);

    List<Map> countByCourtNo(Integer courtNo);
}
