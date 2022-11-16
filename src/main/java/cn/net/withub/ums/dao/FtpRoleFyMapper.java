package cn.net.withub.ums.dao;

import org.apache.ibatis.annotations.Param;

public interface FtpRoleFyMapper {

    Integer selectByRoleIdAndFydm(@Param("userId") String userId, @Param("fydm") String fydm);

    Integer insert(@Param("userId") String userId, @Param("fydm") String fydm);

}
