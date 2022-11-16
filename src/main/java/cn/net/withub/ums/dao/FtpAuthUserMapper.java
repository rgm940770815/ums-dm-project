package cn.net.withub.ums.dao;

import cn.net.withub.ums.webService.interior.FtpAuthUser;

import java.util.List;

public interface FtpAuthUserMapper {

    Integer insert(FtpAuthUser ftpAuthUser);

    List<FtpAuthUser> selectByUserName(String username);
}
