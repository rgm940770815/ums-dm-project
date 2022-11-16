package cn.net.withub.ums.action.userinfo;


import cn.net.withub.ums.dao.UmsAssignAdminDeptMapper;
import cn.net.withub.ums.dao.UmsCourtMapper;
import cn.net.withub.ums.entity.*;
import cn.net.withub.ums.service.UmsDepartmentService;
import cn.net.withub.ums.service.statistics.XtptUserService;
import cn.net.withub.ums.util.SessionUtils;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import java.util.*;

/*
分管部门
 */
@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/adminDept")
@Results({
        @Result(name = "json", type = "json", params = {"root", "data"})
})
public class UmsAssignAdminDeptAction {

    @Autowired
    UmsAssignAdminDeptMapper umsAssignAdminDeptMapper;
    @Autowired
    private XtptUserService xtptUserService;
    @Autowired
    UmsCourtMapper umsCourtMapper;
    @Autowired
    UmsDepartmentService umsDepartmentService;

    private Object data;
    private UmsAssignAdminDept umsAssignAdminDept;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public UmsAssignAdminDept getUmsAssignAdminDept() {
        return umsAssignAdminDept;
    }

    public void setUmsAssignAdminDept(UmsAssignAdminDept umsAssignAdminDept) {
        this.umsAssignAdminDept = umsAssignAdminDept;
    }

    @Action("select")
    public String selectData() {
        Map<String, Object> map = new HashMap<>();
        data = map;
        map.put("success",false);
        try {

            if (umsAssignAdminDept.getUserUuid() != null) {
                UmsAssignAdminDeptExample example = new UmsAssignAdminDeptExample();
                //未删除
                example.createCriteria().andUserUuidEqualTo(umsAssignAdminDept.getUserUuid()).andIsDeleteEqualTo(0);
                example.setOrderByClause(" update_time desc ");
                List<UmsAssignAdminDept> umsAssignAdminDepts = umsAssignAdminDeptMapper.selectByExample(example);
                map.put("list", umsAssignAdminDepts);
                map.put("success",true);
            } else {
                throw new Exception("uuid为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "json";
    }


    /*
    保存信息
     */
    @Action("insert")
    public String insertData() {
        Map<String, Object> map = new HashMap<>();
        data = map;
        map.put("success",false);
        try {

            if(umsAssignAdminDept.getUserUuid() != null && umsAssignAdminDept.getAdminCourtCode() != null &&   umsAssignAdminDept.getAdminDeptId()
            != null){
                //是否已经存在
                UmsAssignAdminDeptExample example = new UmsAssignAdminDeptExample();
                example.createCriteria().andIsDeleteEqualTo(0).andAdminCourtCodeEqualTo(umsAssignAdminDept.getAdminCourtCode())
                                .andAdminDeptIdEqualTo(umsAssignAdminDept.getAdminDeptId()).andUserUuidEqualTo(umsAssignAdminDept.getUserUuid());
                List<UmsAssignAdminDept> lists = umsAssignAdminDeptMapper.selectByExample(example);
                if(lists != null && lists.size() > 0){
                    map.put("msg","已经存在分管部门");
                    return "json";
                }

                UserForXtpt XtptUser = xtptUserService.searchByUUIDForXtpt(umsAssignAdminDept.getUserUuid());
                if(XtptUser == null){
                    //系统平台对应信息不存在
                    map.put("msg", "系统平台对应信息不存在");
                    return "json";
                }
                umsAssignAdminDept.setUserId(XtptUser.getId());
                umsAssignAdminDept.setUserName(XtptUser.getFullname());

                Date now = new Date();
                UmsUserInfoView creator = SessionUtils.currentUser();
                umsAssignAdminDept.setCreateUserName(creator.getFullname() );
                umsAssignAdminDept.setCreateTime(now);
                umsAssignAdminDept.setCreateUserUuid(creator.getId());
                umsAssignAdminDept.setUpdateUserId(creator.getId());
                umsAssignAdminDept.setUpdateTime(now);

                umsAssignAdminDept.setId(UUID.randomUUID().toString());
                //未删除
                umsAssignAdminDept.setIsDelete(0);
                //法院名称
                UmsCourtCriteria umsCourtCriteria = new UmsCourtCriteria();
                umsCourtCriteria.createCriteria().andCourtCodeEqualTo(umsAssignAdminDept.getAdminCourtCode());
                List<UmsCourt> umsCourts = umsCourtMapper.selectByExample(umsCourtCriteria);
                Integer courtNo;
                if(umsCourts != null && umsCourts.size() > 0){
                    umsAssignAdminDept.setAdminCourtName(umsCourts.get(0).getCourtStdName());
                    courtNo = umsCourts.get(0).getCourtNo();
                }else{
                    map.put("msg", "法院编码不正确");
                    return "json";
                }
                //部门
                UmsDepartment umsDepartment = umsDepartmentService.selectByCourtNoAndDeptNo(courtNo, Integer.valueOf(umsAssignAdminDept.getAdminDeptId()));
                if(umsDepartment != null){
                    umsAssignAdminDept.setAdminDeptName(umsDepartment.getDeptName());
                }else{
                    map.put("msg", "部门编码不正确");
                    return "json";
                }
                int insert = umsAssignAdminDeptMapper.insert(umsAssignAdminDept);
                if(insert > 0){
                    map.put("success",true);
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return "json";
    }

    @Action("delete")
    public String deleteData() {
        Map<String, Object> map = new HashMap<>();
        data = map;
        try {
            //删除是更新状态
            if (StringUtils.hasText(umsAssignAdminDept.getId())) {
                UmsAssignAdminDept updateInfo = new UmsAssignAdminDept();
                updateInfo.setId(umsAssignAdminDept.getId());
                updateInfo.setIsDelete(1);

                Date now = new Date();
                UmsUserInfoView creator = SessionUtils.currentUser();
                updateInfo.setUpdateUserId(creator.getId());
                updateInfo.setUpdateTime(now);

                int i = umsAssignAdminDeptMapper.updateByPrimaryKeySelective(updateInfo);
                map.put("success", i > 0);
            } else {
                throw new Exception("uuid为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
        }

        return "json";
    }

}
