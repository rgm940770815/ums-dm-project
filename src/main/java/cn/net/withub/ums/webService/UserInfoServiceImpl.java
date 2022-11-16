package cn.net.withub.ums.webService;

import cn.net.withub.ums.entity.UmsJurorInfo;
import cn.net.withub.ums.entity.UmsUserInfo;
import cn.net.withub.ums.entity.UmsUserInfoView;
import cn.net.withub.ums.entity.UmsUserInfoViewCriteria;
import cn.net.withub.ums.service.UmsCourtService;
import cn.net.withub.ums.service.UmsJurorInfoService;
import cn.net.withub.ums.service.UmsUserInfoService;
import cn.net.withub.ums.service.UmsUserInfoViewService;
import cn.net.withub.ums.util.Md5PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.jws.WebService;
import java.util.*;

/**
 * Created by Administrator on 2016/4/11.
 */
@WebService(name = "userinfo")
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UmsJurorInfoService jurorInfoService;

    @Autowired
    private UmsUserInfoService userInfoService;

    @Autowired
    private UmsCourtService courtService;

    @Autowired
    private UmsUserInfoViewService userInfoViewService;


    @Override
    public ResMessage saveUser(UmsJurorInfo jurorInfo, UmsUserInfo userInfo, String UpdateUser) {

        ResMessage response = new ResMessage();
        response.setResult(false);


        try {

            Assert.notNull(userInfo);
            Assert.notNull(userInfo.getCourtNo());
            Assert.hasText(userInfo.getUsername());
            Assert.hasText(userInfo.getIdcard());
            Assert.hasText(userInfo.getFullname());
            Assert.hasText(UpdateUser);
        } catch (Exception e) {
            e.printStackTrace();
            response.setErrorMsg(e.getMessage());
            return response;
        }

        userInfo.setDataType(9);
        userInfo.setUserType(3);//人民陪审员
        userInfo.setUpdateUser(UpdateUser);
        userInfo.setUpdateTime(new Date());
        jurorInfo.setUpdateUser(UpdateUser);
        jurorInfo.setUpdateDate(new Date());
        userInfo.setPassword("admin");

        int result = 0;

        try {
            //进行简单的身份证校验
            switch (userInfo.getIdcard().length()) {
                case 18:
                    break;
                case 15:
                    break;
                default:
                    throw new Exception("idCard is error");
            }


            String fydm = courtService.courtNo2CourtCode(userInfo.getCourtNo());
            if (fydm == null) {
                throw new Exception("courtNo is incorrect");
            }

            //idcard验证
            Map<String, Object> map = checkIdCard(userInfo.getIdcard(), "");
            if (!(map.get("idCardCheck") != null && (boolean) map.get("idCardCheck"))) {
                throw new Exception("the ID card already exists");
            }

            //用户名验证
            boolean b = checkUserName(userInfo.getCourtNo(), userInfo.getUsername(), "");
            if (!b) {
                throw new Exception("userName already exists");
            }

            userInfo.setCourtCode(fydm);
            userInfo.setCourtStdNo(courtService.courtNo2CourtStdNo(userInfo.getCourtNo()));


            /////////////////////////////////////////////////////////////////////////////////////////
            userInfo.setUserNo((int) (Math.random() * 1000));//测试UserNo统统为负数
            /////////////////////////////////////////////////////////////////////////////////////////
            String userid = UUID.randomUUID().toString();
            userInfo.setId(userid);
            //TODO 陪审员信息未保存用户登录信息
            userInfo.setIsValid(1);
            if (userInfo.getSortNo() == null) {
                userInfo.setSortNo(9999);
            }

            if (userInfo.getPassword() != null) {
                Md5PasswordEncoder Md5Checker = new Md5PasswordEncoder();
                String salt = null;
                userInfo.setSalt(salt);
                String rPassword = Md5Checker.encodePassword(userInfo.getPassword(), salt);
                userInfo.setPassword(rPassword);
            }

            if (userInfo.getCPsMzyy() != null) {
                //停用
                userInfo.setIsValid(0);
            }

            result = userInfoService.insertNoAspect(userInfo);

            jurorInfo.setUserId(userInfo.getId());

            result += jurorInfoService.insertNoAspect(jurorInfo);

            if(result >= 2 ){
                response.setResult(true);
            }


        } catch (Exception e) {

            e.printStackTrace();
            response.setErrorMsg(e.getMessage());
            return response;

        }

        return response;
    }

    private Map<String, Object> checkIdCard(String idcard, String userid) {

        Map<String, Object> returnMap = new HashMap<>();
        UmsUserInfoViewCriteria criteria = new UmsUserInfoViewCriteria();
        UmsUserInfoViewCriteria.Criteria c = criteria.createCriteria();
        c.andIdcardEqualTo(idcard);

        Boolean returnFlag = false;
        String userId = "";
        int userType = 0;
        int isValid = -1;
        try {

            List<UmsUserInfoView> userInfoViews = userInfoViewService.selectViewByExample(criteria);

            if (userInfoViews.size() == 0) {
                returnFlag = true;
            } else {
                if (userInfoViews.get(0).getId().equals(userid)) {
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

        return returnMap;
    }

    private boolean checkUserName(Integer courtNo, String username, String id) {


        Map<String, Object> returnMap = new HashMap<>();
        Integer valid = 1; // 启用

        boolean returnFlag = false;


        try {

            Map<String, Object> map = userInfoViewService.searchByLoginInfo(courtNo, username, valid);
            if (map == null || map.size() == 0) {
                returnFlag = true;
            }

            if (map != null && map.get("id").equals(id)) {
                returnFlag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return returnFlag;
    }

}
