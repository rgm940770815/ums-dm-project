package cn.net.withub.ums.webService.interior;


import cn.net.withub.ums.dao.BmFyNMapper;
import cn.net.withub.ums.dao.FtpAuthUserMapper;
import cn.net.withub.ums.dao.FtpRoleFyMapper;
import cn.net.withub.ums.util.Md5PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 创建“人员统计”接口调用授权账号
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class CreateFtpAuthUser {

    @Autowired
    private FtpAuthUserMapper ftpAuthUserMapper;

    @Autowired
    private FtpRoleFyMapper ftpRoleFyMapper;

    @Autowired
    private BmFyNMapper bmFyNMapper;

    @WebMethod
    public String createFtpAuthUser(
          @WebParam(name = "username") String username,
           @WebParam(name = "password") String password
    ){
        if(!StringUtils.hasText(username) || !StringUtils.hasText(password)){
            return "参数用户名和密码不能为空！";
        }
        FtpAuthUser ftpAuthUser = new FtpAuthUser();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Md5PasswordEncoder Md5Checker = new Md5PasswordEncoder();
        String salt = UUID.randomUUID().toString();
        String pwd = Md5Checker.encodePassword(password,salt);
        ftpAuthUser.setId(UUID.randomUUID().toString());
        ftpAuthUser.setUsername(username);
        ftpAuthUser.setPassword(pwd);
        ftpAuthUser.setSalt(salt);
        ftpAuthUser.setIsStart(1);
        ftpAuthUser.setCreateTime(format.format(new Date()));
        Integer row = ftpAuthUserMapper.insert(ftpAuthUser);

        if (row == 1){
            return "添加授权用户成功！";
        }else{
            return "数据入库时出现未知错误！";
        }
    }

    /**
     * 用户绑定法院
     * @param username 用户名
     * @param fydm 需要绑定的法院代码(多个法院代码请使用 , 隔开 )
     * @return 绑定成功的条数
     */
    @Transactional
    @WebMethod
    public String authUserBindFy(
            @WebParam(name = "username") String username,
            @WebParam(name = "fydm")String fydm
    ){
        if(!StringUtils.hasText(username) || !StringUtils.hasText(fydm)){
            return "参数用户名和法院代码不能为空！";
        }
        List<FtpAuthUser> ftpAuthUsers = ftpAuthUserMapper.selectByUserName(username);
        if(ftpAuthUsers!=null && ftpAuthUsers.size()<1){
            return "用户“"+username+"”不存在，请添加用户后重试！";
        }
        Map<String, Map> fyInfo = PersonServiceImpl.fyInfo;
        if (fyInfo == null){
            fyInfo = new HashMap<>();
            List<Map> fys = bmFyNMapper.selectAll();
            for (Map m : fys ) {
                fyInfo.put(m.getOrDefault("court_code","").toString(),m);
            }
        }
        String[] fydms = fydm.split(",");
        int num = 0;
        for ( String fy : fydms) {
            if(fyInfo.getOrDefault(fy,null)==null){
                return "未知法院代码”"+fy+"“";
            };
            int row = ftpRoleFyMapper.insert(ftpAuthUsers.get(0).getId(),fy);
            num+=row;
        }

        if(num!=fydms.length){
            throw  new RuntimeException("数据入库时出现未知错误！");
        }else{
            return "成功绑定"+num+"条数据！";
        }

    }
}
