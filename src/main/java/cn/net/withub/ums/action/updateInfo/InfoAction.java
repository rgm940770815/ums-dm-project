package cn.net.withub.ums.action.updateInfo;

import cn.net.withub.ums.entity.UmsJurorInfo;
import cn.net.withub.ums.entity.UmsUserInfo;
import cn.net.withub.ums.service.UmsJurorInfoService;
import cn.net.withub.ums.service.UmsUserInfoService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Diluka
 */
@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/updateInfo")
@Results({
        @Result(name = "json", type = "json", params = {"root", "data"})
})
public class InfoAction {

    @Autowired
    private UmsUserInfoService userInfoService;

    @Autowired
    private UmsJurorInfoService jurorInfoService;

    private UmsUserInfo userInfo;

    public UmsUserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UmsUserInfo userInfo) {
        this.userInfo = userInfo;
    }

    private UmsJurorInfo jurorInfo;

    public UmsJurorInfo getJurorInfo() {
        return jurorInfo;
    }

    public void setJurorInfo(UmsJurorInfo jurorInfo) {
        this.jurorInfo = jurorInfo;
    }

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Action("update")
    public String update() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String type = request.getParameter("type");
        String tableId = request.getParameter("tableId");
        int i = 0;
        if(type != null && type !=""){
            if (type.equals("juror")) {
                i = jurorInfoService.updateByPrimaryKeySelective(jurorInfo);
                data = i > 0 ? true:false;
                return "json";
            }else if(type.equals("political")){
                //更新政治面貌
                i = userInfoService.updatePolitical(userInfo);
                Map map = new HashMap();
                map.put("id",tableId);
                map.put("user_id",userInfo.getId());
                userInfoService.updatePresent_infoByuser_id(map);
                userInfoService.updatePresent_info(map);
                data = i > 0 ? true:false;
                return "json";
            }else if (type.equals("administration")){
                //更新行政职务
                i = userInfoService.updateAdministration(userInfo);
                Map map = new HashMap();
                map.put("id",tableId);
                map.put("user_id",userInfo.getId());
                userInfoService.updatePresent_info_adm_Byuser_id(map);
                userInfoService.updatePresent_info_adm(map);
                data = i > 0 ? true:false;
                return "json";
            }else if (type.equals("law")){
                //更新法律职务
                i = userInfoService.updateByPrimaryKeySelective(userInfo);
                Map map = new HashMap();
                map.put("id",tableId);
                map.put("user_id",userInfo.getId());
                userInfoService.updatePresent_info_law_Byuser_id(map);
                userInfoService.updatePresent_info_law(map);
                data = i > 0 ? true:false;
                return "json";

            }else if (type.equals("rank")){
                //更新职级信息
                i = userInfoService.updateRank(userInfo);
                Map map = new HashMap();
                map.put("id",tableId);
                map.put("user_id",userInfo.getId());
                userInfoService.updatePresent_info_rank_Byuser_id(map);
                userInfoService.updatePresent_info_rank(map);
                data = i > 0 ? true:false;
                return "json";

            }else if (type.equals("level")){
                int helper_level = request.getParameter("helper_level") == null ? 0 : 1;
                if (helper_level == 0) {
                    //更新等级信息
                    i = userInfoService.updateLevel(userInfo);
                } else i=1;
                Map map = new HashMap();
                map.put("id",tableId);
                map.put("user_id",userInfo.getId());
                map.put("helper_level",helper_level);
                userInfoService.updatePresent_info_level_Byuser_id(map);
                userInfoService.updatePresent_info_level(map);
                data = i > 0 ? true:false;
                return "json";

            }else if (type.equals("servant_level")){
                //公务员级别
                i = userInfoService.updateServant_level(userInfo);
                Map map = new HashMap();
                map.put("id",tableId);
                map.put("user_id",userInfo.getId());
                userInfoService.updatePresent_info_servant_level_Byuser_id(map);
                userInfoService.updatePresent_info_servant_level(map);
                data = i > 0 ? true:false;
                return "json";

            }else if (type.equals("juror_job")){

                i = userInfoService.updateAdministration(userInfo);
                data = i > 0 ? true:false;
                return "json";
            }else if(type.equals("education")){
                //学历
                i = userInfoService.updateEducation(userInfo);
                Map map = new HashMap();
                map.put("id",tableId);
                map.put("user_id",userInfo.getId());
                userInfoService.updatePresent_info_education_Byuser_id(map);
                userInfoService.updatePresent_info_education(map);
            }else if(type.equals("degree")){
                Map map = new HashMap();
                map.put("id",tableId);
                map.put("user_id",userInfo.getId());
                userInfoService.updatePresent_info_degree_Byuser_id(map);
                userInfoService.updatePresent_info_degree(map);
            }
        }

        i =userInfoService.updateByPrimaryKeySelective(userInfo) ;
        data = i > 0 ? true:false;
            return "json";
    }


}
