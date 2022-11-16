/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.action;

import cn.net.withub.common.util.http.RemoteException;
import cn.net.withub.sso.client.request.SSORequest;
import cn.net.withub.ums.action.auth.RsaAction;
import cn.net.withub.ums.auth.AuthType;
import cn.net.withub.ums.auth.AuthorityHelper;
import cn.net.withub.ums.common.UmsConstant;
import cn.net.withub.ums.dao.XtptUserMapper;
import cn.net.withub.ums.entity.*;
import cn.net.withub.ums.log.UmsLogger;
import cn.net.withub.ums.service.UmsAuthorityService;
import cn.net.withub.ums.service.UmsExternalCompanyInfoService;
import cn.net.withub.ums.service.UmsUserInfoViewService;
import cn.net.withub.ums.util.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.collections.MapUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import java.security.interfaces.RSAPrivateKey;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 登录
 *
 * @author Diluka
 */
@Controller
@ParentPackage("codeBase")
@Scope("prototype")
@Namespace("/")
@Results({
        @Result(name = "success", type = "redirect", location = "/index.jsp"),
        @Result(name = "success2", type = "redirect", location = "/view/detail_new", params = {"id", "${id}"}),
        @Result(name = "input", location = "/login.jsp"),
        @Result(name = "countSta",type = "redirect", location = "/board/statistics/countSta1.jsp"),
        @Result(name = "json", type = "json", params = {"root", "data"})
//
})
public class LoginAction {
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private final Logger logger = LogManager.getLogger(LoginAction.class);

    @Autowired
    private UmsLogger umsLogger;

    @Autowired
    private UmsUserInfoViewService userInfoViewService;

    @Autowired
    private UmsAuthorityService authorityService;

    @Autowired
    private AuthorityHelper authorityHelper;

    @Autowired
    private UmsExternalCompanyInfoService companyInfoService;

    private String username;
    private String password;
    private int courtStdNo;
    private long pd;

    public long getPd() {
        return pd;
    }

    public void setPd(long pd) {
        this.pd = pd;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    private String user_id;

    private int companyInfoId;
    private String companyName;

    private String idcard;

    private String msg;

    private String id;

    private Integer valid;

    // userType判断前端传来的是1,2,3
    private Integer userType;
    // isEdit判断前端传来的是否是编辑状态
    private String isEdit;

    // 根据前台参数跳转页面
    private String toPage;

    public String getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(String isEdit) {
        this.isEdit = isEdit;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    @Autowired
    private XtptUserMapper xtptUserMapper;

    //登录错误存储登录信息 防止多次登录
    private static Map<String,Map<String,Object>> loginErrorMap = new HashMap<>();

    /**
     * 明文密码
     *
     * @return
     */
    //@Action("login")
    @Deprecated
    public String login() {

        logger.debug(String.format("c: %d, u: %s, p: %s", courtStdNo, username, password));

        if (!StringTools.isNullOrEmpty(username) && password != null) {

            String md5Pwd = MessageDigestor.getMD5String(password);
            logger.debug(md5Pwd);

            UmsUserInfoView userinfo = userInfoViewService.login(courtStdNo, username, md5Pwd);
            try {
                if (userinfo != null) {
                    ActionContext.getContext().getSession().put(UmsConstant.LOGIN_USER_SESSION_KEY.toString(), userinfo);
                    Map<String, Integer> auths = authorityService.userAuth(userinfo.getId());
                    ActionContext.getContext().getSession().put(UmsConstant.CURRENT_USER_AUTH.toString(), auths);
                    umsLogger.login();
                    logger.debug(" login success");
                    return "success";
                } else {
                    logger.debug(" login failed");
                    return "input";
                }
            } catch (Exception e) {
                return "input";
            }
        } else {
            return "input";
        }

    }

    /**
     * MD5 密码
     *
     * @return
     */
    //@Action("login2")
    @Deprecated
    public String login2() {

        logger.debug(String.format("c: %d, u: %s, p: %s", courtStdNo, username, password));

        if (!StringTools.isNullOrEmpty(username) && password != null) {

            logger.debug(password);

            UmsUserInfoView userinfo = userInfoViewService.login(courtStdNo, username, password);
            try {
                if (userinfo != null) {
                    ActionContext.getContext().getSession().put(UmsConstant.LOGIN_USER_SESSION_KEY.toString(), userinfo);
                    umsLogger.login();
                    logger.debug(" login success");
                    return "success";
                } else {
                    logger.debug(" login failed");
                    return "input";
                }
            } catch (Exception e) {
                return "input";
            }
        } else {
            return "input";
        }

    }


    /**
     * 是否是超级管理员
     */
    @Action("IsSuperUser")
    public String IsSuperUser() {

//        request.getSession("");

        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        try {
            UmsUserInfoView u = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());

            List<UmsRole> roles = authorityService.userRoles(u.getId());
            //管理员权限
            if(roles != null){
                for (UmsRole role : roles) {
                    boolean adminRole = authorityService.isAdminRole(role);
                    if(adminRole){
                        flag = true;
                        break;
                    }
                }
            }

//            if (u.getUsername().equals("superuser")) {
//                flag = true;
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("res", flag);
        data = map;

        return "json";

    }

    /**
     * MD5->Base64密码
     *
     * @return
     */
    @Action("login3")
    public String login3() {
        try {

            //解码
            //使用模和指数生成私钥
            RSAPrivateKey priKey = RSAUtils.getPrivateKey(RsaAction.getRasModule(), RsaAction.getPrivateKey());
            System.out.println("开始解密");
            //私钥解密后的明文
             username = RSAUtils.decryptByPrivateKey(HexUtil.hexStringToBytes(username), priKey);
             password = RSAUtils.decryptByPrivateKey(HexUtil.hexStringToBytes(password), priKey);


            logger.debug(String.format("c: %d, u: %s, p: %s", courtStdNo, username, password));

            //限制多次错误登录
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //最大可以输错密码的次数
            Integer maxErrorCount = 5;
            Map<String, Object> errorMap = loginErrorMap.get(username);
            Integer nowErrorCount = 0;
            if(errorMap != null){
                nowErrorCount = MapUtils.getInteger(errorMap,"count",0);
                //时间不同不拦截
                String time = MapUtils.getString(errorMap, "time", "");

                Calendar instance = Calendar.getInstance();
                instance.setTime(new Date());
                instance.add(Calendar.MINUTE, -30);
                Date startTime = instance.getTime();

                if( nowErrorCount >= maxErrorCount && startTime.before(sdf.parse(time))
                ){
                    msg = "多次登录错误，账号被锁定";
                    return "input";
                }

            }

            if (!StringTools.isNullOrEmpty(username)) {
                logger.debug(password);
                UmsUserInfoView userinfo;
                // 如果用户用管理员账号登录时通过另一个表的信息进行验证
                if ("admin".equals(username) || "superuser".equals(username)) {
                    userinfo = userInfoViewService.login4Admin(courtStdNo, username, password);
                } else {
                    userinfo = userInfoViewService.login(courtStdNo, username, password);
                }
                try {
                    String result = "success";
                    AuthType authType = checkAuth(userinfo);
                    // 同时赋值给实体类
                    if (null != authType && authType != AuthType.ERROR) {
                        userinfo.setAuthType(authType);
                    }

                    switch (authType) {
                        case PERSON:
                            // 只能看自己
                            id = userinfo.getId();
                            //移除登录错误
                            if(errorMap != null){
                                loginErrorMap.remove(username);
                            }
                            result = "success2";
                        case ADMIN:
                        case COURT:
                            ActionContext.getContext().getSession().put(UmsConstant.LOGIN_USER_SESSION_KEY.toString(), userinfo);
                            // 记录登录日志
                            umsLogger.login();
                            //移除登录错误
                            if(errorMap != null){
                                loginErrorMap.remove(username);
                            }
                            logger.debug("------------------------------------------------------------- login success--");
                            return result;
                        case ERROR:
                            logger.debug("--------------------------------------------------------------- login failed");
                            umsLogger.loginFailed(courtStdNo, username, password);

                            //用户密码错误时 防止多次测试
                            if(errorMap == null){
                                errorMap = new HashMap<>();
                                errorMap.put("count",1);
                                errorMap.put("time", sdf.format(new Date()));
                                loginErrorMap.put(username,errorMap);
                                msg = "登录名或密码错误，再输错"+ maxErrorCount +"次将被锁定";
                            }else{
                                msg = "登录名或密码错误，再输错"+ (maxErrorCount - nowErrorCount )  +"次将被锁定";
                                errorMap.put("count", nowErrorCount+ 1);
                                errorMap.put("time", sdf.format(new Date()));
                                loginErrorMap.put(username,errorMap);
                            }
                            return "input";
                        default:
                            break;
                    }
                } catch (Exception e) {
                    msg = "系统繁忙，请稍后重试！";
                    return "input";
                }
            } else {
                msg = "用户名不能为空！";
                return "input";
            }
            return "input";
        } catch (Exception e) {
            e.printStackTrace();
        }
        msg = "登录过程中遇见错误";
        return "input";
    }

    /**
     * MD5->Base64密码
     *
     * @return
     */
    @Action("loginInterface")
    public String loginInterface() {

        logger.debug(String.format("c: %d, u: %s, p: %s", courtStdNo, user_id, pd));
        Long receive = System.currentTimeMillis() + 100;

//        if ((receive-pd)/1000<60) {
        if (1 == 1) {
            try {
                UmsUserInfoView userinfo = userInfoViewService.loginInterface(courtStdNo, user_id, password);
                String result = "success";
                switch (checkAuth(userinfo)) {
                    case PERSON:
                        //只能看自己
                        id = userinfo.getId();
                        result = "success2";
                    case ADMIN:
                    case COURT:
                        //id = userinfo.getId();
                        ActionContext.getContext().getSession().put(UmsConstant.LOGIN_USER_SESSION_KEY.toString(), userinfo);
                        umsLogger.login();
                        logger.debug("------------------------------------------------------------- login success--");
                        if (id != null) {
                            result = "success2";
                            return result;
                        }
                        return result;
                    case ERROR:
                        logger.debug("--------------------------------------------------------------- login failed");
                        umsLogger.loginFailed(courtStdNo, username, password);
                        msg = "用户名或密码错误！";
                        return "input";
                    default:
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
                msg = "系统繁忙，请稍后重试！";
                return "input";
            }
        } else {
//            msg = "加载超时！";
            msg = "不是一个有效的安全链接！";
            return "input";
        }

        return "input";
    }

    @Action("loginByTicket")
    public String loginByTicket() throws RemoteException {
        String tmpTicket = ServletActionContext.getRequest().getParameter("tmpTicket");
        try {
            String login = SSORequest.login(tmpTicket);
            Gson gson = new Gson();
            Map<String, Object> ssoMap = gson.fromJson(login, new TypeToken<Map<String, Object>>() {
            }.getType());
            String code = ssoMap.get("code").toString();
            if ("000000".equals(code)) {
                Map<String, Object> info = (Map<String, Object>) ssoMap.get("user");

                String courtCode = info.get("courtCode") == null ? "" : info.get("courtCode").toString();
                String uuid = info.get("uuid") == null ? "" : info.get("uuid").toString();

                List<String> toPageList = Arrays.asList("success", "success2", "countSta", "input");
                if (StringUtils.hasText(uuid) && StringUtils.hasText(courtCode)) {
                    UmsUserInfoViewCriteria criteria = new UmsUserInfoViewCriteria();
                    criteria.createCriteria().andIdEqualTo(uuid).andCourtCodeEqualTo(courtCode);
                    List<UmsUserInfoView> umsUserInfoViews = userInfoViewService.selectViewByExample(criteria);
                    if (umsUserInfoViews.size() == 1) {
                        UmsUserInfoView userinfo = umsUserInfoViews.get(0);

                        //跳转页面
                        if(StringUtils.hasText(toPage) && toPageList.contains(toPage)){
                            ActionContext.getContext().getSession().put(UmsConstant.LOGIN_USER_SESSION_KEY.toString(), userinfo);
                            id = userinfo.getId();
                            return toPage;
                        }
                        String result = "success";
                        switch (checkAuth(userinfo)) {
                            case PERSON:
                                //只能看自己
                                id = userinfo.getId();
                                result = "success2";
                            case ADMIN:
                            case COURT:
                                //id = userinfo.getId();
                                ActionContext.getContext().getSession().put(UmsConstant.LOGIN_USER_SESSION_KEY.toString(), userinfo);
                                umsLogger.login();
                                logger.debug("------------------------------------------------------------- login success--");
                                if (id != null) {
                                    result = "success2";
                                    return result;
                                }
                                return result;
                            case ERROR:
                                logger.debug("--------------------------------------------------------------- login failed");
                                umsLogger.loginFailed(courtStdNo, username, password);
                                msg = "用户名或密码错误！";
                                return "input";
                            default:
                                break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "input";
    }


    /**
     * 检查用户权限
     *
     * @param u
     * @return
     */
    private AuthType checkAuth(UmsUserInfoView u) {
        if (u == null) {
            return AuthType.ERROR;
        }
        AuthType type = authorityHelper.getAuthType(u.getId());
        logger.debug("------------------------------------------------------------- authtype" + type);
        ActionContext.getContext().getSession().put(UmsConstant.CURRENT_USER_AUTH.toString(), type);
        return type;
    }

    @Action("logout")
    public String logout() {
        ServletActionContext.getRequest().getSession().invalidate();
        return "login";
    }

    /**
     * userType= 1和3的情况时，重复了则返回新的username
     *
     * @return
     */
    @Action("checkUserName")
    public String checkUserName() {
        String returnFlag = "false", newUserName = "";
        Map<String, Object> map, map_2, returnMap = new HashMap<>();
        UmsUserInfo entity = new UmsUserInfo();
        int count, num;
        if(username.endsWith("/")){
            username = username.replace("/","");
        }
        if ("true".equals(isEdit)) {
            // 编辑状态:1判断该名字是否存在于数据库中,要排除自己,查询ums_user_info是否存在该username,验证user_info和xtpt这张表
            map = userInfoViewService.searchByLoginInfo_umsinfo(username, id);
            if (map != null) {
                if (map.get("id").equals(id)) {
                    returnFlag = "false";
                } else {
                    returnFlag = "true";
                }
            } else {
                entity.setUsername(username);
                entity.setIdcard(id);
                count = xtptUserMapper.selectNumOfSameName_2(entity);
                if (count > 0) {
                    returnFlag = "true";
                } else {
                    returnFlag = "false";
                }
            }
        } else {
            // 查询ums_user_info是否存在该username
            map = userInfoViewService.searchByLoginInfo_2(courtStdNo, username, valid);
//            Map<String, Object> map = userInfoViewService.searchByLoginInfo(courtStdNo, username, valid);
            // 如果存在
            if (map != null) {
                if (map.get("id").equals(id)) {
                    returnFlag = "false";
                } else {
                    returnFlag = "true";
                }
            } else {
                //再判断xtpt_t_user是否有重复
                XtptUserCriteria criteria = new XtptUserCriteria();
//                criteria.createCriteria().andUsernameEqualTo(username).andEnabledEqualTo(true);
                criteria.createCriteria().andUsernameEqualTo(username);
                List<XtptUser> u = xtptUserMapper.selectByExample(criteria);
                if (u.size() > 0) {
                    if (u.get(0).getId().equals(id)) {
                        returnFlag = "false";
                    } else {
                        returnFlag = "true";
                    }
                }
            }
        }

        // 如果用户名已存在了，则加+1再进行判断，适用于1/3
        if ("true".equals(returnFlag)) {
            entity.setUsername(username);
            // 在ums_user_info 和 xtpt_t_user 两张表中，判断username+id后六位是否已经存在
            count = xtptUserMapper.selectNumOfSameName(entity);
            num = count;
            // 如果存在，在最后一位加1,再判断，直到不重复为止
            String init_username = username;
            while (count > 0 || map != null) {
                ++num;
                entity.setUsername(init_username + num);
                newUserName = init_username + num;
                // 根据新的username去数据库的两张表中查询是否有相同的
                count = xtptUserMapper.selectNumOfSameName(entity);
                map = userInfoViewService.searchByLoginInfo_2(courtStdNo, newUserName, valid);
                if ("true".equals(isEdit)) {
                    map_2 = userInfoViewService.searchByLoginInfo_umsinfo_2(newUserName, id);
                    if (null != map_2) {
                        break;
                    }
                }
            }
            returnFlag = "true";
        }

        // 将生成的不重复的用户名返回给前端
        returnMap.put("UserNameCheck", returnFlag);
        returnMap.put("username", newUserName);
        data = returnMap;
        return "json";
    }

    @Action("checkIdCard")
    public String checkIdCard() {

        Map<String, Object> returnMap = new HashMap<>();
        UmsUserInfoViewCriteria criteria = new UmsUserInfoViewCriteria();
        UmsUserInfoViewCriteria.Criteria c = criteria.createCriteria();
        c.andIdcardEqualTo(idcard);

        if (valid != null) {
            c.andIsValidEqualTo(valid);
        }
        Boolean returnFlag = false;
        String userId = "";
        int userType = 0;
        int isValid = -1;
        try {
            List<UmsUserInfoView> userInfoViews = userInfoViewService.selectViewByExample(criteria);

            if (userInfoViews.size() == 0) {
                returnFlag = true;
            } else {
                if (userInfoViews.get(0).getId().equals(id) || "true".equals(isEdit)) {
                    returnFlag = true;
                } else {
                    returnFlag = false;
                    userId = userInfoViews.get(0).getId();
                    userType = userInfoViews.get(0).getUserType();
                    isValid = userInfoViews.get(0).getIsValid();
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        returnMap.put("idCardCheck", returnFlag);
        returnMap.put("userId", userId);
        returnMap.put("userType", userType);
        returnMap.put("enabled", isValid);

        data = returnMap;

        return "json";
    }

    /**
     * userType= 2的情况时，重复了则返回新的username
     *
     * @return
     */
    @Action("checkCompanyAndUserName")
    public String checkCompanyAndUserName() {
        String returnFlag = "true", newUserName = "";
        UmsUserInfo entity = new UmsUserInfo();
        Map<String, Object> map, map_2, returnMap = new HashMap<>();
        int count, num;

        if ("true".equals(isEdit)) {
            // 编辑状态:1判断该名字是否存在于数据库中,排除自己,查询ums_user_info是否存在该username,验证user_info和xtpt这张表
            map = userInfoViewService.searchByLoginInfo_umsinfo(username, id);
            if (map != null) {
                if (map.get("id").equals(id)) {
                    returnFlag = "true";
                } else {
                    returnFlag = "false";
                }
            } else {
                entity.setUsername(username);
                entity.setIdcard(id);
                count = xtptUserMapper.selectNumOfSameName_2(entity);
                if (count > 0) {
                    returnFlag = "false";
                } else {
                    returnFlag = "true";
                }
            }
        } else {
            // 查询ums_user_info是否存在该username
            map = userInfoViewService.searchByLoginInfo_2(courtStdNo, username, valid);
            // 如果存在
            if (map != null) {
                if (map.get("id").equals(id)) {
                    returnFlag = "true";
                } else {
                    returnFlag = "false";
                }
            } else {
                //再判断xtpt_t_user是否有重复
                XtptUserCriteria criteria = new XtptUserCriteria();
//                criteria.createCriteria().andUsernameEqualTo(username).andEnabledEqualTo(true);
                criteria.createCriteria().andUsernameEqualTo(username);// 不加enabled的限制条件
                List<XtptUser> u = xtptUserMapper.selectByExample(criteria);
                if (u.size() > 0) {
                    if (u.get(0).getId().equals(id)) {
                        returnFlag = "true";
                    } else {
                        returnFlag = "false";
                    }
                }
            }
        }

        // 如果用户名已存在了，则加+1再进行判断，适用于2
        if ("false".equals(returnFlag)) {
            entity.setUsername(username);
            // 在ums_user_info 和 xtpt_t_user 两张表中，判断username+id后六位是否已经存在
            count = xtptUserMapper.selectNumOfSameName(entity);
            num = count;
            // 如果存在，在最后一位加1,再判断，直到不重复为止
            String init_username = username;
            while (count > 0 || map != null) {
                ++num;
                entity.setUsername(init_username + num);
                newUserName = init_username + num;
                // 根据新的username去数据库的两张表中查询是否有相同的
                count = xtptUserMapper.selectNumOfSameName(entity);
                map = userInfoViewService.searchByLoginInfo_2(courtStdNo, newUserName, valid);
                if ("true".equals(isEdit)) {
                    // 编辑状态时,更改了姓名和身份证号,此时用户名也改变了.如果改变之后的用户名和之前用的一样,则,还用这个用户名,否则改为其他
                    map_2 = userInfoViewService.searchByLoginInfo_umsinfo_2(newUserName, id);
                    if (null != map_2) {
                        break;
                    }
                }
            }
            returnFlag = "false";
        }

        // 将生成的不重复的用户名返回给前端
        returnMap.put("UserNameCheck", returnFlag);
        returnMap.put("username", newUserName);
        returnMap.put("CompanyInfoCheck", "true");
        if (companyInfoId == 0 && companyName != null && companyName != "") {
            List<UmsExternalCompanyInfo> list = companyInfoService.findByName(companyName);
            if (list.size() > 0) {
                returnMap.put("CompanyInfoCheck", "false");
            } else {
                returnMap.put("CompanyInfoCheck", "true");
            }
        } else if (companyInfoId != 0 && companyName != null && companyName != "") {
            returnMap.put("CompanyInfoCheck", "true");
        } else {
            returnMap.put("CompanyInfoCheck", "false");
        }
        data = returnMap;
        return "json";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCourtStdNo() {
        return courtStdNo;
    }

    public void setCourtStdNo(int courtStdNo) {
        this.courtStdNo = courtStdNo;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCompanyInfoId() {
        return companyInfoId;
    }

    public void setCompanyInfoId(int companyInfoId) {
        this.companyInfoId = companyInfoId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public String getToPage() {
        return toPage;
    }

    public void setToPage(String toPage) {
        this.toPage = toPage;
    }
}
