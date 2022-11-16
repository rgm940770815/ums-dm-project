package cn.net.withub.ums.dao;

import cn.net.withub.ums.entity.UmsUserInfo;
import cn.net.withub.ums.entity.UmsUserInfoCriteria;
import cn.net.withub.ums.entity.UmsChangeJob;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

public interface UmsUserInfoMapper {
    int countByExample(UmsUserInfoCriteria example);

    int deleteByExample(UmsUserInfoCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(UmsUserInfo record);

    int insertSelective(UmsUserInfo record);

    List<UmsUserInfo> selectByExample(UmsUserInfoCriteria example);

    List<UmsUserInfo> selectByExample(UmsUserInfoCriteria example, RowBounds rowBounds);

    UmsUserInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UmsUserInfo record, @Param("example") UmsUserInfoCriteria example);

    int updateByExample(@Param("record") UmsUserInfo record, @Param("example") UmsUserInfoCriteria example);

    int updateByPrimaryKeySelective(UmsUserInfo record);

    int updateByPrimaryKey(UmsUserInfo record);

    // 该条sql专门为修改密码的
    int updateByPrimaryKeyForChangePassword(UmsUserInfo record);

    int updatePolitical(UmsUserInfo record);

    int updatePresent_info(Map map);

    int updatePresent_infoByuser_id(Map map);

    int updateAdministration(UmsUserInfo record);

    int updatePresent_info_adm(Map map);

    int updatePresent_info_adm_Byuser_id(Map map);

    int updatePresent_info_law(Map map);

    int updatePresent_info_law_Byuser_id(Map map);

    int updateRank(UmsUserInfo record);

    int updatePresent_info_rank(Map map);

    int updatePresent_info_rank_Byuser_id(Map map);

    int updateLevel(UmsUserInfo record);

    int updatePresent_info_level(Map map);

    int updatePresent_info_level_Byuser_id(Map map);

    int updateServant_level(UmsUserInfo record);

    int updatePresent_info_servant_level(Map map);

    int updatePresent_info_servant_level_Byuser_id(Map map);

    Map<String, Object> searchVirtualUserByID(String ID);

    int updateForTypeOne(UmsUserInfo record);

    int updateEnabled(UmsUserInfo userInfo);

    //职位调离
    int insertJobById(UmsChangeJob changeJob);
    int updateJobById(UmsChangeJob changeJob);
//    int updateXtptAndInfo(UmsChangeJob changeJob);

    int updateUmsUserInfo(UmsChangeJob changeJob);

    int updateXtptInfo(UmsChangeJob changeJob);

    List<UmsChangeJob> selectChangeJob(UmsChangeJob umsChangeJob, RowBounds rowBounds);
    int countChangeJob(UmsChangeJob umsChangeJob);

    int updateUserInfo(UmsUserInfo umsuserinfo);
    int updateTime(UmsUserInfo info);

    int updatePresentInfoByUserId(Map map);

    int updatePresentInfoById(Map map);

    List<UmsUserInfo> selectAll(Map param);

    List<UmsUserInfo> selectBatchById(List ids);

//    int updateXtptAndUserDeptCode(Map map);

    int updateUserDeptCode(Map map);

    int updateXtptDeptCode(Map map);

    List<Map> customSearch(Map param);

    List<Map> cunstomSearch_nnd(Map param);
    /**
     * 修改某个法院某个部门下大于等于某个序号的所有人的序号=当前序号+1
     * @param param
     * @return
     */
    int updateUserIndexBatch1(Map param);

    /**
     * 查询出某个法院某个部门下大于等于某个序号的所有人
     * @param param
     * @return
     */
    List<UmsUserInfo> selectUserInWhere1(Map param);
    /**
     * 修改某个法院某个部门下大于某个序号的所有人的序号=当前序号+1
     * @param param
     * @return
     */
    int updateUserIndexBatch2(Map param);

    /**
     * 查询出某个法院某个部门下大于某个序号的所有人
     * @param param
     * @return
     */
    List<UmsUserInfo> selectUserInWhere2(Map param);

    List<Map> customSearchForBw(Map param);
    List<Map> customSearchForAgeBw(Map param);

    List<Map> customSearchForPsy(Map param);
    List<Map> customSearchForAgePsy(Map param);

    List<Map> customSearch_avgnnd(Map map);

    // 部门按标准部门编码进行分组，报表按法院进行分组，分别统计刑事、民事、执行、立案、行政 部门下各院法官人数
//    List<Map> countYefgGroupbyDept(Map map);

    List<Map> countYefgGroupbyDept(@Param("deptName") String deptName, @Param("authcourt") String authcourt, @Param("groupBy") String groupBy);

    // 根据时间条件查询入额法官数量
    List<Map> countyefg(Map map);

    // 根据时间条件查询退额法官数量
    List<Map> counttefg(Map map);

    // 根据时间条件查询每种法官数量
    List<Map> countEveryFg(Map map);

    // 根据时间条件查询每种退额法官数量
    List<Map> countEveryTeFg(Map map);

    int updatePresent_info_education(Map map);

    int updatePresent_info_education_Byuser_id(Map map);

    int updateEducation(UmsUserInfo record);

    int updatePresent_info_degree(Map map);

    int updatePresent_info_degree_Byuser_id(Map map);

}