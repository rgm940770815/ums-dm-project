package cn.net.withub.ums.action.userinfo;

import cn.net.withub.ums.entity.*;
import cn.net.withub.ums.service.UmsTemporaryPositionService;
import cn.net.withub.ums.service.statistics.XtptUserService;
import cn.net.withub.ums.util.SessionUtils;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/12/23.
 */
@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/affiliatedInfo")
@Results({
        @Result(name = "json", type = "json", params = {"root", "data"})
})
public class affiliatedInfoAction {

    @Autowired
    private UmsTemporaryPositionService umsTemporaryPositionService;

    @Autowired
    private XtptUserService xtptUserService;
    private Object data;

    private UmsTemporaryPosition umsTemporaryPosition;

    @Action("insert")
    public String affiliatedInsertInfo() {

        Map<String, Object> map = new HashMap<>();

        data = map;
        try {

            if (umsTemporaryPosition.getDepartment() != null &&
                    umsTemporaryPosition.getCourtCode() != null &&
                    umsTemporaryPosition.getUuid() != null) {

                UmsTemporaryPositionExample example = new UmsTemporaryPositionExample();
                example.createCriteria().andUuidEqualTo(umsTemporaryPosition.getUuid())
                        .andCourtCodeEqualTo(umsTemporaryPosition.getCourtCode())
                        .andDepartmentEqualTo(umsTemporaryPosition.getDepartment());
                List<Map> list = umsTemporaryPositionService.selectViewByExample(example);
                if (list.size() > 0) {
                    //当前用户在该法院已有挂靠记录
                    map.put("reason", 1);
                    throw new Exception("当前用户在该法院部门已有挂靠记录");
                }
                UserForXtpt XtptUser = xtptUserService.searchByUUIDForXtpt(umsTemporaryPosition.getUuid());
                if (XtptUser != null) {
                    //用户本身同法院同部门下不能挂靠部门(不查xtpt用户 页面验证ums_user_info)
//                    if (XtptUser.getCourtCode().equals(umsTemporaryPosition.getCourtCode())
//                            && XtptUser.getDeptNo().equals(umsTemporaryPosition.getDepartment())) {
//                        map.put("reason", 5);
//                        throw new Exception("不能在用户本院本部门下挂靠法院");
//
//                    }
                    umsTemporaryPosition.setIsPartTimeJob(0);
                    umsTemporaryPosition.setId(XtptUser.getId());
                    //用户本法院下 挂靠要打上兼职标记
                    if (XtptUser.getCourtCode().equals(umsTemporaryPosition.getCourtCode())) {
                        umsTemporaryPosition.setIsPartTimeJob(1);
                    }

                } else {
                    //系统平台对应信息不存在
                    map.put("reason", 2);
                    throw new Exception("系统平台对应信息不存在");
                }
                UmsUserInfoView user = SessionUtils.currentUser();
                umsTemporaryPosition.setCreator(user.getFullname() + "@" + user.getCourtNoText());
                umsTemporaryPosition.setCreateDate(new Date());
                if (umsTemporaryPosition.getSortNo() == null) {
                    umsTemporaryPosition.setSortNo(99); //默认为99
                }
                umsTemporaryPositionService.insert(umsTemporaryPosition);
                map.put("success", true);

            } else {
                //参数不全
                map.put("reason", 3);
                map.put("success", false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //数据库操作或其他错误
            if (map.get("reason") == null) {
                map.put("reason", 4);
            }
            map.put("success", false);
        }


        return "json";
    }

    @Action("select")
    public String affiliatedSelect() {
        Map<String, Object> map = new HashMap<>();
        data = map;
        try {

            if (umsTemporaryPosition.getUuid() != null) {
                UmsTemporaryPositionExample example = new UmsTemporaryPositionExample();
                example.createCriteria().andUuidEqualTo(umsTemporaryPosition.getUuid());
                List<Map> list = umsTemporaryPositionService.selectViewByExample(example);
                map.put("list", list);
            } else {
                throw new Exception("uuid为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return "json";
    }

    @Action("delete")
    public String affiliatedDelete() {
        Map<String, Object> map = new HashMap<>();
        data = map;
        try {
            if (umsTemporaryPosition.getUuid() != null &&
                    umsTemporaryPosition.getCourtCode() != null &&
                    umsTemporaryPosition.getDepartment() != null) {
                int i = umsTemporaryPositionService.deleteByUUID(umsTemporaryPosition);
                map.put("success", i > 0 ? true : false);
            } else {
                throw new Exception("uuid为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
        }


        return "json";
    }

    @Action("update")
    public String update() {
        Map<String, Object> map = new HashMap<>();
        data = map;
        try {
            if (umsTemporaryPosition.getId() != null &&
                    umsTemporaryPosition.getCourtCode() != null &&
                    umsTemporaryPosition.getDepartment() != null &&
                    umsTemporaryPosition.getSortNo() != null) {
                UmsTemporaryPositionExample example = new UmsTemporaryPositionExample();
                example.createCriteria().andIdEqualTo(umsTemporaryPosition.getId()).andCourtCodeEqualTo( umsTemporaryPosition.getCourtCode())
                        .andDepartmentEqualTo( umsTemporaryPosition.getDepartment());
                UmsTemporaryPosition l = umsTemporaryPositionService.selectByExample(example).get(0);
                l.setSortNo(umsTemporaryPosition.getSortNo());
                int i = umsTemporaryPositionService.update(l);
                map.put("success", i > 0 ? true : false);
            } else {
                throw new Exception("参数错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
        }


        return "json";
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public UmsTemporaryPosition getUmsTemporaryPosition() {
        return umsTemporaryPosition;
    }

    public void setUmsTemporaryPosition(UmsTemporaryPosition umsTemporaryPosition) {
        this.umsTemporaryPosition = umsTemporaryPosition;
    }
}
