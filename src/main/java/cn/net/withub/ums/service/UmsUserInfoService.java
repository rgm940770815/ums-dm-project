/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service;

import cn.net.withub.ums.entity.UmsChangeJob;
import cn.net.withub.ums.entity.UmsUserInfo;
import cn.net.withub.ums.entity.UmsUserInfoCriteria;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Diluka
 */
public interface UmsUserInfoService extends BaseService<UmsUserInfo, UmsUserInfoCriteria> {


    public List<UmsUserInfo> getUserList(String fydm, Map<String, Object> parameters);

    /**
     * 用户登录
     *
     * @param courtStdNo 承办法院编号
     * @param username 用户名
     * @param password 密码
     * @return
     */
    UmsUserInfo login(Integer courtStdNo, String username, String password);

    /**
     * 修改密码
     *
     * @param id
     * @param oldPassword
     * @param newPassword
     * @param msg
     * @return
     */
    boolean changePassword(String id, String oldPassword, String newPassword, StringBuilder msg);

    /**
     * 修改管理员密码
     *
     * @param id
     * @param oldPassword
     * @param newPassword
     * @param msg
     * @return
     */
    boolean changePasswordForAdmin(String id, String oldPassword, String newPassword, StringBuilder msg);

    int updateByPrimaryKeySelective(UmsUserInfo userInfo);

    int updatePolitical(UmsUserInfo userInfo);

    int updatePresent_info(Map map);

    int updatePresent_infoByuser_id(Map map);

    int updateAdministration(UmsUserInfo userInfo);

    int updatePresent_info_adm(Map map);

    int updatePresent_info_adm_Byuser_id(Map map);

    int updatePresent_info_law(Map map);

    int updatePresent_info_law_Byuser_id(Map map);

    int updateRank(UmsUserInfo userInfo);

    int updatePresent_info_rank(Map map);

    int updatePresent_info_rank_Byuser_id(Map map);

    int updateLevel(UmsUserInfo userInfo);

    int updatePresent_info_level(Map map);

    int updatePresent_info_level_Byuser_id(Map map);

    int updateServant_level(UmsUserInfo userInfo);

    int updatePresent_info_servant_level(Map map);

    int updatePresent_info_servant_level_Byuser_id(Map map);

    int insertOnly(UmsUserInfo entity);

    int updateEnabled(UmsUserInfo userInfo);

    int updateAll(UmsUserInfo userInfo);

    List<UmsUserInfo> selectByExample(UmsUserInfoCriteria example);

    List<UmsUserInfo> selectByExample(UmsUserInfoCriteria example,RowBounds rowBounds);

    List<UmsUserInfo> selectByExampleWithNoAspect(UmsUserInfoCriteria example);

    public int insertNoAspect(UmsUserInfo entity) ;

    //职位调离
    int insertJobById(UmsChangeJob changeJob);
    int updateJobById(UmsChangeJob changeJob);
    int updateXtptAndInfo(UmsChangeJob changeJob);


    List<UmsChangeJob> selectChangeJob(UmsChangeJob umsChangeJob,RowBounds rowBounds);
    int countChangeJob(UmsChangeJob umsChangeJob);

    int updateTime(UmsUserInfo info);

    List<UmsUserInfo> selectAll(Map param);

    int countByExample(UmsUserInfoCriteria example);

    int updateXtptAndUserDeptCode(Map map);
    //自定义查询
    List<Map> customSearch(Map param);
    //自定义查询编外
    List<Map> customSearchForBw(Map param);
    //自定义查询陪审员
    List<Map> customSearchForPsy(Map param);

    //需要根据年龄段的自定义查询
    List<Map> cunstomSearch_nnd(Map param);
    //需要根据年龄段的自定义查询_编外
    List<Map> customSearchForAgeBw(Map param);
    //需要根据年龄段的自定义查询_陪审员
    List<Map> customSearchForAgePsy(Map param);

    List<Map> customSearch_avgnnd(Map param);

    /**+
     * 修改某个法院下某个部门下的部分员工的序号 >=
     * @param param
     * @return
     */
    int updateUserIndex1(Map param);

    /**
     * 查询出某个法院某个部门下大于等于某个序号的所有人
     * @param param
     * @return
     */
    List<UmsUserInfo> selectUserInWhere1(Map param);

    /**+
     * 修改某个法院下某个部门下的部分员工的序号 >
     * @param param
     * @return
     */
    int updateUserIndex2(Map param);

    /**
     * 查询出某个法院某个部门下大于某个序号的所有人
     * @param param
     * @return
     */
    List<UmsUserInfo> selectUserInWhere2(Map param);

    // 刑事、民事、执行、立案、行政 获取5个部门的数据
    List<Map> countYefgGroupbyDept(Map param);

    // 根据时间条件查询入额法官数量
    List<Map> countyefg(Map param);

    // 根据时间条件查询退额法官数量
    List<Map> counttefg(Map param);

    // 根据时间条件查询每种法官数量
    List<Map> countEveryFg(Map param);

    // 根据时间条件查询每种退额法官数量
    List<Map> countEveryTeFg(Map param);

    int updatePresent_info_education(Map map);

    int updatePresent_info_education_Byuser_id(Map map);

    int updateEducation(UmsUserInfo record);

    int updatePresent_info_degree(Map map);

    int updatePresent_info_degree_Byuser_id(Map map);

}
