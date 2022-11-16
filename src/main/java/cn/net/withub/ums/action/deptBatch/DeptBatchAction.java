package cn.net.withub.ums.action.deptBatch;

import cn.net.withub.ums.entity.*;
import cn.net.withub.ums.service.UmsDepartmentService;
import cn.net.withub.ums.service.UmsUserInfoService;
import cn.net.withub.ums.service.UmsUserInfoViewService;
import cn.net.withub.ums.service.XtptTUserService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.apache.ibatis.session.RowBounds;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.json.JsonObject;
import java.util.*;

/**
 * Created by admin on 2017/2/20.
 */

@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/deptBatch")
@Results({@Result(name = "json", type = "json", params = {"root", "data"})})
public class DeptBatchAction
{
    @Autowired
    UmsDepartmentService departmentService;

    @Autowired
    UmsUserInfoService umsUserInfoService;

    @Autowired
    UmsUserInfoViewService umsUserInfoViewService;

    @Autowired
    XtptTUserService xtptTUserService;


    String fydm;
    private Object data;

    String userStr;
    String deptStr;

    String selectUserId;
    String otherUserId;
    Integer courtNo;
    Integer deptNo;
    Integer selectUserSortNo;
    Integer otherUserSortNo;
    Integer upAndDown;
    Integer userType;

    @Action("getAllUserMap")
    public String getAllUserByFydm()
    {
        Map<String,Object> resultMap = new HashMap<>();
        if (fydm!= null && !"".equals(fydm))
        {
            UmsDepartmentCriteria criteria = new UmsDepartmentCriteria();
            UmsDepartmentCriteria.Criteria c = criteria.createCriteria();
            c.andCourtCodeEqualTo(fydm);
            String orderBy = "sort_no asc";
            criteria.setOrderByClause(orderBy);
            List<UmsDepartment> departments = departmentService.selectByExample(criteria);
            List<UmsDepartment> deptList = new ArrayList<>();
            for (UmsDepartment dept:departments)
            {
                List<UmsUserInfoView> userinfoList = null;
                UmsUserInfoViewCriteria userCriteria = new UmsUserInfoViewCriteria();
                UmsUserInfoViewCriteria.Criteria userC = userCriteria.createCriteria();
                userC.andCourtCodeEqualTo(fydm);
                userC.andDepartmentEqualTo(dept.getDeptNo());
                userC.andIsValidEqualTo(1);
                userC.andLeaveReasonIsNull();
                userC.andUserTypeEqualTo(1);
                userC.andIsInfoCompleteNotEqualTo(1);
                userCriteria.setOrderByClause(orderBy);
                RowBounds rowBounds = new RowBounds(0, 99999);
                userinfoList = umsUserInfoViewService.searchBySort(userCriteria,rowBounds);
                if(userinfoList!= null && userinfoList.size()>0)
                {
                    deptList.add(dept);
                    resultMap.put(dept.getDeptNo()+"",userinfoList);
                }
            }
            resultMap.put("allDept",deptList);
            data = resultMap;
        }

        return "json";
    }

    @Action("updateUserIndex")
    public String updateUserIndex()
    {
        Map<String,Object> result = new HashMap<>();
        data = result;
        if(userStr != null)
        {
            Gson g = new Gson();
            List<Map> userList = g.fromJson(userStr,List.class);
            try
            {
                int success = 0;
                for (Map m:userList)
                {
                    UmsUserInfo u = new UmsUserInfo();
                    u.setId(m.get("id").toString());
                    u.setSortNo(Integer.parseInt(m.get("sortNo").toString()));
                    success += umsUserInfoService.updateByPrimaryKeySelective(u);
                    int i = syncXtptUserSortNo(u);
                }
                result.put("success",success);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                result.put("error","失败！");
            }
        }
        return "json";
    }

    @Action("updateDeptIndex")
    public String updateDeptIndex(){
        Map<String,Object> result = new HashMap<>();
        data = result;
        if(deptStr != null)
        {
            Gson g = new Gson();
            List<Map> userList = g.fromJson(deptStr,List.class);
            try
            {
                int success = 0;
                for (Map m:userList)
                {
                    UmsDepartment department = new UmsDepartment();
                    department.setDeptNo(Integer.parseInt(m.get("deptNo").toString()));
                    department.setSortNo(Integer.parseInt(m.get("sortNo").toString()));
                    department.setCourtNo(Integer.parseInt(m.get("courtNo").toString()));
                    success += departmentService.updateByPrimaryKeySelective(department);
                }
                result.put("success",success);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                result.put("error","失败！");
            }
        }
        return "json";
    }

    @Action("updateUserIndexUpAndDown")
    public String updateUserIndexUpAndDown()
    {
        Map result = new HashMap<>();
        data = result;
        try
        {
            if(selectUserSortNo!=otherUserSortNo)
            {
                UmsUserInfo u1 = new UmsUserInfo();
                u1.setId(selectUserId);
                u1.setSortNo(otherUserSortNo);
                UmsUserInfo u2 = new UmsUserInfo();
                u2.setId(otherUserId);
                u2.setSortNo(selectUserSortNo);
                int a = umsUserInfoService.updateByPrimaryKeySelective(u1);
                int b = umsUserInfoService.updateByPrimaryKeySelective(u2);
                int i= syncXtptUserSortNo(u1);
                int j= syncXtptUserSortNo(u2);
                if(a+b==2)
                    result.put("success","修改成功");
                else result.put("error","修改失败");
            }
            else
            {
                Map param = new HashMap<>();
                param.put("userType",userType);
                param.put("courtNo",courtNo);
                param.put("deptNo",deptNo);
                param.put("sortNo",selectUserSortNo);
                UmsUserInfo selectUser = new UmsUserInfo();
                selectUser.setId(selectUserId);
                int a = 0;int i = 0;
                List<UmsUserInfo> userInfos = null;
                if(upAndDown==1)
                {
                    selectUser.setSortNo(selectUserSortNo);
                    a = umsUserInfoService.updateUserIndex1(param);
                    userInfos = umsUserInfoService.selectUserInWhere1(param);
                }
                else
                {
                    selectUser.setSortNo(selectUserSortNo+1);
                    a = umsUserInfoService.updateUserIndex2(param);
                    userInfos = umsUserInfoService.selectUserInWhere2(param);
                }
                int b = umsUserInfoService.updateByPrimaryKeySelective(selectUser);
                for (UmsUserInfo user:userInfos)
                {
                    i += syncXtptUserSortNo(user);
                }
                int j = syncXtptUserSortNo(selectUser);
                if(a + b >= 2)
                    result.put("success","修改成功");
                else
                    result.put("error","修改失败");
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return "json";
    }

    /*将序号同步至系统平台表*/
    public int syncXtptUserSortNo(UmsUserInfo umsUserInfo){
        XtptTUserExample tUserExample = new XtptTUserExample();
        XtptTUserExample.Criteria c = tUserExample.createCriteria();
        c.andUuidEqualTo(umsUserInfo.getId());
        XtptTUser tUser = new XtptTUser();
        tUser.setSortNo(umsUserInfo.getSortNo());
        int i = xtptTUserService.updateByExampleSelective(tUser,tUserExample);
        return i;
    }


    public String getFydm()
    {
        return fydm;
    }

    public void setFydm(String fydm)
    {
        this.fydm = fydm;
    }

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }

    public String getUserStr()
    {
        return userStr;
    }

    public void setUserStr(String userStr)
    {
        this.userStr = userStr;
    }

    public String getDeptStr()
    {
        return deptStr;
    }

    public void setDeptStr(String deptStr)
    {
        this.deptStr = deptStr;
    }

    public UmsDepartmentService getDepartmentService()
    {
        return departmentService;
    }

    public void setDepartmentService(UmsDepartmentService departmentService)
    {
        this.departmentService = departmentService;
    }

    public Integer getOtherUserSortNo()
    {
        return otherUserSortNo;
    }

    public void setOtherUserSortNo(Integer otherUserSortNo)
    {
        this.otherUserSortNo = otherUserSortNo;
    }

    public Integer getSelectUserSortNo()
    {
        return selectUserSortNo;
    }

    public void setSelectUserSortNo(Integer selectUserSortNo)
    {
        this.selectUserSortNo = selectUserSortNo;
    }

    public Integer getDeptNo()
    {
        return deptNo;
    }

    public void setDeptNo(Integer deptNo)
    {
        this.deptNo = deptNo;
    }

    public Integer getCourtNo()
    {
        return courtNo;
    }

    public void setCourtNo(Integer courtNo)
    {
        this.courtNo = courtNo;
    }

    public String getOtherUserId()
    {
        return otherUserId;
    }

    public void setOtherUserId(String otherUserId)
    {
        this.otherUserId = otherUserId;
    }

    public String getSelectUserId()
    {
        return selectUserId;
    }

    public void setSelectUserId(String selectUserId)
    {
        this.selectUserId = selectUserId;
    }

    public Integer getUpAndDown()
    {
        return upAndDown;
    }

    public void setUpAndDown(Integer upAndDown)
    {
        this.upAndDown = upAndDown;
    }

    public Integer getUserType()
    {
        return userType;
    }

    public void setUserType(Integer userType)
    {
        this.userType = userType;
    }
}
