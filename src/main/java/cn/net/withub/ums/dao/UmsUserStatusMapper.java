package cn.net.withub.ums.dao;

import cn.net.withub.ums.entity.UmsUserStatus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsUserStatusMapper {

   List<UmsUserStatus> selectByUserId(String userId);

    void deleteByUserId(String userId);

    /**
     * 根据用户id删除身份编码不等于CodeList的其他身份信息
     */
    void deleteByUserIdAndNotCodeList(@Param("userId") String userId,@Param("codeList") List<String> codeList);

    void insertList(@Param("insertList") List<UmsUserStatus> insertList);
}
