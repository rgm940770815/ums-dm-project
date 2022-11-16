package cn.net.withub.ums.dao;

import cn.net.withub.ums.entity.UmsUserInfoExternalCriteria;
import cn.net.withub.ums.entity.UmsUserInfoView;
import cn.net.withub.ums.entity.UmsUserInfoViewCriteria;
import java.util.List;
import java.util.Map;

import cn.net.withub.ums.webService.interior.PersonInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UmsUserInfoViewMapper {
    int countByExample(UmsUserInfoViewCriteria example);

    // 待完善资料的人员数量
    int countByExample_dwszl(UmsUserInfoViewCriteria example);

    int deleteByExample(UmsUserInfoViewCriteria example);

    int insert(UmsUserInfoView record);

    int insertSelective(UmsUserInfoView record);

    List<UmsUserInfoView> selectByExample(UmsUserInfoViewCriteria example);

    List<UmsUserInfoView> selectByExample(UmsUserInfoViewCriteria example, RowBounds rowBounds);

    // 反查法官列表
    List<UmsUserInfoView> selectFgList_Fc(Map<String, Object> param);
    // 反查法官数量
    Integer selectFgCount_Fc(Map<String, Object> param);

    int updateByExampleSelective(@Param("record") UmsUserInfoView record, @Param("example") UmsUserInfoViewCriteria example);

    int updateByExample(@Param("record") UmsUserInfoView record, @Param("example") UmsUserInfoViewCriteria example);

    List<UmsUserInfoView> selectCustom(@Param("info")String info,RowBounds rowBounds);

    Integer countCustom(@Param("info")String info);

    List<UmsUserInfoView> selectCustomN(@Param("info")String info,RowBounds rowBounds);

    List<UmsUserInfoView> selectCustomN_(@Param("info")String info,RowBounds rowBounds);

    Integer countCustomN(@Param("info")String info);

    List<UmsUserInfoView> selectCustomM(@Param("info")String info,RowBounds rowBounds);

    List<Map<String, Object>> selectCustomF(@Param("info")String info);

    List<Map<String, Object>> selectCustomG(@Param("info")String info);

    Integer countCustomM(@Param("info")String info);

    List<Map> seleteJurorInfo(UmsUserInfoViewCriteria example);

    List<Map> seleteOffStaffInfo(UmsUserInfoViewCriteria example);

    List<UmsUserInfoView> searchBySort(UmsUserInfoViewCriteria example, RowBounds rowBounds);

    List<UmsUserInfoView> simpleSearchBySort(UmsUserInfoViewCriteria example, RowBounds rowBounds);

    List<UmsUserInfoView> searchBySort_dwszl(UmsUserInfoViewCriteria example, RowBounds rowBounds);

    List<UmsUserInfoView> simpleSearchBySort_dwszl(UmsUserInfoViewCriteria example, RowBounds rowBounds);

    List<Map> selectBatchById(List ids);

    Map selectBasicUserinfoById(String id);

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

    int countByExampleLeftJoinExternal(UmsUserInfoExternalCriteria example);

    List<UmsUserInfoView> selectByExampleLeftJoinExternal(UmsUserInfoExternalCriteria example);

    List<UmsUserInfoView> selectByExampleLeftJoinPsy(UmsUserInfoExternalCriteria example);

    int countByExampleLeftJoinPsy(UmsUserInfoExternalCriteria umsUserInfoExternalCriteria);

    int countCustomK(@Param("info")String info);

    List<UmsUserInfoView> selectCustomK(@Param("info")String info, RowBounds rowBounds);

    List<Map<String,Object>> selectRyList(Map<String,Object> map);

    List<UmsUserInfoView> selectByUserName(String username);

    Integer count(@Param("fydm") String fydm,@Param("condition") String condition,@Param("org_code") String org_code);

    List<PersonInfo> selectByFydmAndCondition(@Param("pageSize") Integer pageSize, @Param("pageNum") Integer pageNum, @Param("fydm") String fydm, @Param("condition") String condition,@Param("org_code") String org_code);

    List<Map> selectLeaveList(@Param("fydm") String fydm, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize );

    PersonInfo selectById(@Param("id") String id, @Param("xtptId") String xtptId);

    List<PersonInfo> selectAllList(
            @Param("pageNum") Integer pageNum,
            @Param("pageSize") Integer pageSize,
            @Param("fydm") String fydm,
            @Param("courtNo") String courtNo,
            @Param("orgCode") String orgCode,
            @Param("deptNo") String deptNo,
            @Param("administrationPosition") String administrationPosition,
            @Param("personnelClassification") String personnelClassification,
            @Param("yefg") String yefg
    );

    List<Map> getDeptList(@Param("fydm") String fydm,@Param("orgCode") String orgCode, @Param("deptNo") String deptNo,@Param("orgType") String orgType);

    List<Map> getPersonAndChangeJobList(
            @Param("fydm") String fydm,
            @Param("orgCode") String orgCode,
            @Param("deptNo") String deptNo,
            @Param("personnelClassification") String personnelClassification,
            @Param("startTime") String startTime,
            @Param("endTime") String endTime
    );

    List<Map> selectXzzw(String xtptId);

    List<Map> selectFgdjxx(String xtptId);

    List<Map> selectFgzldjxx(String xtptId);

    List<Map> selectSjyDjxx(String xtptId);

    List<Map> countByCourtNo(@Param("courtNo") Integer courtNo);
}