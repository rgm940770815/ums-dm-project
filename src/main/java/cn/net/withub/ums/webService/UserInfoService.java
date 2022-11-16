package cn.net.withub.ums.webService;

import cn.net.withub.ums.entity.UmsJurorInfo;
import cn.net.withub.ums.entity.UmsUserInfo;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by Administrator on 2016/4/11.
 */
@WebService
public interface UserInfoService {

    @WebMethod
    public ResMessage saveUser(UmsJurorInfo jurorInfo, UmsUserInfo userInfo, String UpdateUser);



}
