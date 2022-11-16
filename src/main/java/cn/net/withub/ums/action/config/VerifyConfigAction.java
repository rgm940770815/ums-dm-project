package cn.net.withub.ums.action.config;

import cn.net.withub.ums.action.config.dao.VerifyDao;
import cn.net.withub.ums.action.institution.UmsPartyOrganizationAction;
import cn.net.withub.ums.action.userinfo.UserInfoAttachedTableAction;
import cn.net.withub.ums.action.xml.StandaloneWriter;
import cn.net.withub.ums.action.xml.TableTranslateUtil;
import cn.net.withub.ums.action.xml.ZipUtils;
import cn.net.withub.ums.dao.UmsEducationInfoMapper;
import cn.net.withub.ums.dao.UmsPartyOrganizationMapper;
import cn.net.withub.ums.dao.UmsVerifyConfigMapper;
import cn.net.withub.ums.entity.*;
import cn.net.withub.ums.entity.factory.EntityFactory;
import cn.net.withub.ums.service.*;
import cn.net.withub.ums.util.ReflectionTools;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.ibatis.session.RowBounds;
import org.apache.struts2.convention.annotation.*;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2016/1/15.
 */
@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/verifyConfigAction")
@Results({
        @Result(name = "json", type = "json", params = {"root", "data"})
})
//验证配置
public class VerifyConfigAction {


    private Object data;
    private String scope;
    private String key;
    private String jsonStr;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Autowired
    UmsVerifyConfigMapper umsVerifyConfigMapper;

    @Autowired
    VerifyDao verifyDao;

    @Autowired
    private UmsCodeService codeService;

    //保存配置
    @Action("saveConfig")
    public String saveConfig(){
        if(!StringUtils.hasText(jsonStr) || !StringUtils.hasText(scope)){
            return "json";
        }
        try {
            Gson g = new Gson();
            List<Map<String, Object>> infoResult = g.fromJson(jsonStr, new TypeToken<List<Map<String, Object>>>() {
            }.getType());

            data = verifyDao.insertByBatch(infoResult, scope);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "json";

    }

    //获取配置
    @Action("getConfig")
    public String getConfig(){
        if(!StringUtils.hasText(scope)){
            return "json";
        }

        UmsVerifyConfigCriteria example = new UmsVerifyConfigCriteria();
        example.createCriteria().andCScopeEqualTo(scope);
        List<UmsVerifyConfig> c = verifyDao.selectByExample(example);

        ArrayList<UmsVerifyConfig> umsVerifyConfigs = new ArrayList<>();

        Set<String> keys = c.stream().map(UmsVerifyConfig::getcKey).collect(Collectors.toSet());
        Map<String, List<UmsVerifyConfig>> collect = c.stream().collect(Collectors.groupingBy(UmsVerifyConfig::getcKey));

        //法官等级要通过编码读出来
        if(scope.equals("level")){
            Integer typeId = 117;
            List<UmsCode> umsCodes = codeService.selectCodesByType(typeId);

            for (UmsCode umsCode : umsCodes) {
                String id = umsCode.getId();
                if(StringUtils.hasText(id) && keys.contains(id)){
                    umsVerifyConfigs.addAll(collect.get(id));
                    continue;
                }
                UmsVerifyConfig con = new UmsVerifyConfig();
                con.setcScope(scope);
                con.setcKey(id);
                con.setcValue(null);
                con.setcName(umsCode.getCodeName());
                umsVerifyConfigs.add(con);

            }

        }else {
            //获取到默认的配置
            List<UmsVerifyConfig> baseList = getBaseList(scope);
            for (UmsVerifyConfig u : baseList) {
                //没有的配置使用默认的配置
                String id = u.getcKey();
                if(StringUtils.hasText(id) && keys.contains(id)){
                    umsVerifyConfigs.addAll(collect.get(id));
                }else{
                    umsVerifyConfigs.add(u);
                }
            }

        }
        data = umsVerifyConfigs;

        return "json";
    }


    //删除
    @Action("deleteConfig")
    public String deleteConfig(){
        if(StringUtils.hasText(scope) && StringUtils.hasText(key)){
            UmsVerifyConfigKey dKey = new UmsVerifyConfigKey();
            dKey.setcKey(key);
            dKey.setcScope(scope);
            data = verifyDao.deleteByPrimaryKey(dKey);
        }
        return "json";
    }


    //获取到默认的配置
    private List<UmsVerifyConfig> getBaseList(String scope){
        List<UmsVerifyConfig> re = new ArrayList<>();
        if(scope.equals("retire")){
            UmsVerifyConfig l1 = new UmsVerifyConfig();
            l1.setcScope(scope);
            l1.setcKey("1"); //男性
            l1.setcValue(null);
            l1.setcName("男");
            UmsVerifyConfig l2 = new UmsVerifyConfig();
            l2.setcScope(scope);
            l2.setcKey("2"); //男性
            l2.setcValue(null);
            l2.setcName("女");
            re.add(l1);
            re.add(l2);
        }
        if(scope.equals("threshold")){
            UmsVerifyConfig l1 = new UmsVerifyConfig();
            l1.setcScope(scope);
            l1.setcKey("1"); //男性
            l1.setcValue(null);
            l1.setcName("余量");
            re.add(l1);
        }

        return re;
    }

}
