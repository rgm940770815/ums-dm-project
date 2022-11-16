package cn.net.withub.ums.action.auth;

import cn.net.withub.ums.common.UmsConstant;
import cn.net.withub.ums.entity.UmsRole;
import cn.net.withub.ums.entity.UmsUserInfoView;
import cn.net.withub.ums.util.RSAUtils;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.collections.MapUtils;
import org.apache.struts2.convention.annotation.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/rsa")
@Results({
        @Result(name = "json", type = "json", params = {"root", "data"})
})
public class RsaAction {

    private Object data;
    //加密私钥
    private static String privateKey = null;
    //加密公钥
    private static String publicKey = null;
    //加密模块
    private static String rasModule = null;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    @Action("getRsaModule")
    public String getModule() {
        try {
            if(privateKey == null || rasModule == null || publicKey == null){
                Map modulus = RSAUtils.getModulus();
                publicKey = MapUtils.getString(modulus,"g");
                privateKey = MapUtils.getString(modulus,"m");
                rasModule = MapUtils.getString(modulus,"modu");
            }
            Map<String,Object> res = new HashMap<>();
            res.put("key", publicKey);
            res.put("module", rasModule);
            data = res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "json";
    }

    public static String getPrivateKey() {
        return privateKey;
    }

    public static String getPublicKey() {
        return publicKey;
    }

    public static String getRasModule() {
        return rasModule;
    }
}
