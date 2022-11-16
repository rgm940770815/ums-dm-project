package cn.net.withub.ums.action.config;

import cn.net.withub.ums.action.config.dao.courtVerifyDao;
import cn.net.withub.ums.entity.*;
import cn.net.withub.ums.service.UmsCodeService;
import cn.net.withub.ums.service.UmsCourtService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by Administrator on 2016/1/15.
 */
@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/courtVerifyAction")
@Results({
        @Result(name = "json", type = "json", params = {"root", "data"})
})
public class CourtVerifyAction {

    private Object data;
    private String jsonStr;
    private String scope;
    private String key;
    private Integer courtNo;

    private UmsCourtVerifyConfig courtConfig;

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

    public UmsCourtVerifyConfig getCourtConfig() {
        return courtConfig;
    }

    public void setCourtConfig(UmsCourtVerifyConfig courtConfig) {
        this.courtConfig = courtConfig;
    }

    public Integer getCourtNo() {
        return courtNo;
    }

    public void setCourtNo(Integer courtNo) {
        this.courtNo = courtNo;
    }

    @Autowired
    courtVerifyDao courtVerifyDao;

    @Autowired
    private UmsCourtService courtService;
    @Autowired
    private UmsCodeService codeService;

    //获取配置
    @Action("getConfig")
    public String getConfig() {

        if (!StringUtils.hasText(scope) ) {
            return "json";
        }


        ArrayList<UmsCourtVerifyConfig> courtVerifyConfigs = new ArrayList<>();

        Set<String> keys = new HashSet<>();
        Map<String, List<UmsCourtVerifyConfig>> collect = new HashMap<>();
        if(courtNo != null){
            UmsCourtVerifyConfigCriteria example = new UmsCourtVerifyConfigCriteria();
            example.createCriteria().andCourtNoEqualTo(courtNo).andCScopeEqualTo(scope);
            List<UmsCourtVerifyConfig> c = courtVerifyDao.selectByExample(example);


            keys = c.stream().map(UmsCourtVerifyConfig::getcKey).collect(Collectors.toSet());
            collect = c.stream().collect(Collectors.groupingBy(UmsCourtVerifyConfig::getcKey));
        }


        if(scope.equals(courtVerifyEnum.LEVEL.getcScope())){
            Integer typeId = 117;
            List<UmsCode> umsCodes = codeService.selectCodesByType(typeId);

            for (UmsCode umsCode : umsCodes) {
                String id = umsCode.getId();
                if(StringUtils.hasText(id) && keys.contains(id)){
                    courtVerifyConfigs.addAll(collect.get(id));
                    continue;
                }
                UmsCourtVerifyConfig con = new UmsCourtVerifyConfig();
                con.setcScope(scope);
                con.setcKey(id);
                con.setcValue(null);
                con.setcName(umsCode.getCodeName());
                courtVerifyConfigs.add(con);

            }

        }else {
            //获取到默认的配置
            List<UmsCourtVerifyConfig> baseList = getBaseList(scope);
            for (UmsCourtVerifyConfig u : baseList) {
                //没有的配置使用默认的配置
                String id = u.getcKey();
                if(StringUtils.hasText(id) && keys.contains(id)){
                    courtVerifyConfigs.addAll(collect.get(id));
                }else{
                    courtVerifyConfigs.add(u);
                }
            }

        }
        data = courtVerifyConfigs;

        return "json";
    }


    //保存配置
    @Action("saveConfig")
    public String saveConfig(){
        if(!StringUtils.hasText(jsonStr) || !StringUtils.hasText(scope) || courtNo == null){
            return "json";
        }
        try {
            Gson g = new Gson();
            List<Map<String, Object>> infoResult = g.fromJson(jsonStr, new TypeToken<List<Map<String, Object>>>() {
            }.getType());

            data = courtVerifyDao.insertByBatch(infoResult, scope,courtNo);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "json";

    }


    //获取到默认的配置
    private List<UmsCourtVerifyConfig> getBaseList(String scope) {
        List<UmsCourtVerifyConfig> re = new ArrayList<>();
        if (scope.equals(courtVerifyEnum.ZZZX.getcScope())) {
            UmsCourtVerifyConfig l1 = new UmsCourtVerifyConfig();
            l1.setcKey(courtVerifyEnum.ZZZX.getcKey());
            l1.setcScope(courtVerifyEnum.ZZZX.getcScope());
            l1.setcName(courtVerifyEnum.ZZZX.getName());
            l1.setcValue(null);
            re.add(l1);
        }

        if (scope.equals(courtVerifyEnum.YEFG.getcScope())) {
            UmsCourtVerifyConfig l1 = new UmsCourtVerifyConfig();
            l1.setcKey(courtVerifyEnum.YEFG.getcKey());
            l1.setcScope(courtVerifyEnum.YEFG.getcScope());
            l1.setcName(courtVerifyEnum.YEFG.getName());
            l1.setcValue(null);
            re.add(l1);
        }

        return re;
    }


}
