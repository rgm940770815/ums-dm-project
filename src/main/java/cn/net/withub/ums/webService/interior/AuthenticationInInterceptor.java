package cn.net.withub.ums.webService.interior;

import cn.net.withub.ums.dao.FtpAuthUserMapper;
import cn.net.withub.ums.dao.UmsUserInfoViewMapper;
import cn.net.withub.ums.dao.UmsUserRoleMapper;
import cn.net.withub.ums.entity.UmsUserInfoView;
import cn.net.withub.ums.entity.UmsUserRoleKey;
import cn.net.withub.ums.util.Md5PasswordEncoder;
import cn.net.withub.ums.webService.UnsupportedEncodingException;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.apache.xmlbeans.impl.soap.SOAPException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.xml.namespace.QName;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.Base64;
import java.util.List;

@Component
public class AuthenticationInInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

    @Autowired
    private UmsUserInfoViewMapper userInfoViewMapper;

    @Autowired
    private UmsUserRoleMapper umsUserRoleMapper;

    @Autowired
    private FtpAuthUserMapper ftpAuthUserMapper;

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationInInterceptor.class);

    private static final String BASIC_PREFIX = "Basic ";
    private static final String USERNAME = "name";
    private static final String PASSWORD = "pwd";

    public AuthenticationInInterceptor() {
        super(Phase.PRE_INVOKE);
    }

    @Override
    public void handleMessage(SoapMessage message) {

        List<Header> headers = message.getHeaders();
        if (headers == null || headers.isEmpty()) {
            throw new Fault(new Exception("???????????????????????????[authorization] HEADER????????????????????????!"));
        }

        Element auth = null;
        // ????????????????????????
        for (Header header : headers) {
            QName qname = header.getName();
            String tagName = qname.getLocalPart();
            if (tagName != null && tagName.equals("authorization")) {
                auth = (Element) header.getObject();
                break;
            }
        }

        // ????????????????????????????????????????????????
        if (auth == null) {
            throw new Fault(new Exception("???????????????????????????[authorization] HEADER????????????????????????!"));
        }

        NodeList nameList = auth.getElementsByTagName("username");
        NodeList pwdList = auth.getElementsByTagName("password");
        if (nameList.getLength() != 1 || pwdList.getLength() != 1) {
            throw new Fault(new Exception("????????????????????????"));
        }

        String name = nameList.item(0).getTextContent();
        String password = pwdList.item(0).getTextContent();
        Md5PasswordEncoder Md5Checker = new Md5PasswordEncoder();

        List<FtpAuthUser> users = ftpAuthUserMapper.selectByUserName(name);
        FtpAuthUser ftpAuthUser = null;

        for (FtpAuthUser user : users) {
            if(Md5Checker.isPasswordValid(user.getPassword(), password, user.getSalt())){
                ftpAuthUser = user;
            }
        }
        if (ftpAuthUser == null){
            throw new Fault(new Exception("?????????????????????????????????"));
        }
        if(ftpAuthUser.getIsStart()!=1){
            throw new Fault(new Exception("????????????????????????"));
        }

        message.put("userInfo",ftpAuthUser);
    }


    /**
     * ???????????????request????????????
     * ??????????????????????????????
     */
//    @Override
//    public void handleMessage(SoapMessage message) throws Fault {
//
//        HttpServletRequest request = (HttpServletRequest) message.get(AbstractHTTPDestination.HTTP_REQUEST);
//        String auth = request.getHeader("Authorization");
//        if (auth == null) {
////            SOAPException exception = new SOAPException("auth failed, header [Authorization] not exists");
//            SOAPException exception = new SOAPException("???????????????????????????[Authorization] HEADER????????????????????????!");
//            throw new Fault(exception);
//        }
//        if (!auth.startsWith(BASIC_PREFIX)) {
//            SOAPException exception = new SOAPException("[Authorization] ????????????????????????Basic??????hash = -1632313343???");
//            throw new Fault(exception);
//        }
//        String plaintext = null;
//        try {
//            plaintext = new String(Base64.getDecoder().decode(auth.substring(BASIC_PREFIX.length())),"utf-8");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (StringUtils.isEmpty(plaintext) || !plaintext.contains(":")) {
////            SOAPException exception = new SOAPException("auth failed, header [Authorization] is illegal");
//            SOAPException exception = new SOAPException("?????????????????????");
//            throw new Fault(exception);
//        }
//        String[] userAndPass = plaintext.split(":");
//        String username = userAndPass[0];
//        String password = userAndPass[1];
//
//        List<UmsUserInfoView> list = userInfoViewMapper.selectByUserName(username);
//        UmsUserInfoView userInfoView = null;
//        Md5PasswordEncoder Md5Checker = new Md5PasswordEncoder();
//        for (UmsUserInfoView user : list) {
//            if(Md5Checker.isPasswordValid(user.getPassword(), password, user.getSalt())){
//                userInfoView = user;
//            }
//        }
//        if(userInfoView == null){
////            SOAPException exception = new SOAPException("auth failed, username or password is incorrect");
//            SOAPException exception = new SOAPException("??????????????????????????????????????????");
//            throw new Fault(exception);
//        }
//
//        //????????????id
//        List<UmsUserRoleKey> userRoleKeys = umsUserRoleMapper.selectByUserId(userInfoView.getId());
//        if(userRoleKeys.size()<1){
//            SOAPException exception = new SOAPException("???????????????");
//            throw new Fault(exception);
//        }
//
//        message.put("userInfo",userInfoView);
//        message.put("role",userRoleKeys);
//////        if (!USERNAME.equals(username) || !PASSWORD.equals(password)) {
//////            SOAPException exception = new SOAPException("auth failed, username or password is incorrect");
//////            throw new Fault(exception);
//////        }
//    }


}