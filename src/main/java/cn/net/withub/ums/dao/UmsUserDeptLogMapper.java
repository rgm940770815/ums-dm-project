package cn.net.withub.ums.dao;

import cn.net.withub.ums.entity.UmsUserDeptLog;
import cn.net.withub.ums.entity.UmsUserDeptLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsUserDeptLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_user_dept_log
     *
     * @mbggenerated Mon Feb 01 16:49:29 CST 2016
     */
    int countByExample(UmsUserDeptLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_user_dept_log
     *
     * @mbggenerated Mon Feb 01 16:49:29 CST 2016
     */
    int deleteByExample(UmsUserDeptLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_user_dept_log
     *
     * @mbggenerated Mon Feb 01 16:49:29 CST 2016
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_user_dept_log
     *
     * @mbggenerated Mon Feb 01 16:49:29 CST 2016
     */
    int insert(UmsUserDeptLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_user_dept_log
     *
     * @mbggenerated Mon Feb 01 16:49:29 CST 2016
     */
    int insertSelective(UmsUserDeptLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_user_dept_log
     *
     * @mbggenerated Mon Feb 01 16:49:29 CST 2016
     */
    List<UmsUserDeptLog> selectByExample(UmsUserDeptLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_user_dept_log
     *
     * @mbggenerated Mon Feb 01 16:49:29 CST 2016
     */
    UmsUserDeptLog selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_user_dept_log
     *
     * @mbggenerated Mon Feb 01 16:49:29 CST 2016
     */
    int updateByExampleSelective(@Param("record") UmsUserDeptLog record, @Param("example") UmsUserDeptLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_user_dept_log
     *
     * @mbggenerated Mon Feb 01 16:49:29 CST 2016
     */
    int updateByExample(@Param("record") UmsUserDeptLog record, @Param("example") UmsUserDeptLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_user_dept_log
     *
     * @mbggenerated Mon Feb 01 16:49:29 CST 2016
     */
    int updateByPrimaryKeySelective(UmsUserDeptLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_user_dept_log
     *
     * @mbggenerated Mon Feb 01 16:49:29 CST 2016
     */
    int updateByPrimaryKey(UmsUserDeptLog record);
}
