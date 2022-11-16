package cn.net.withub.ums.action.dept;

import cn.net.withub.ums.common.UmsConstant;
import cn.net.withub.ums.entity.*;
import cn.net.withub.ums.service.*;
import cn.net.withub.ums.util.IpTools;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/personOfDept")
@Results({@Result(name = "json", type = "json", params = {"root", "data"})})
public class PersonOfDeptAction {

    private Object data;

    @Autowired
    private UmsUserInfoService userInfoService;

    @Autowired
    private UmsUserInfoViewService userInfoViewService;

    @Autowired
    private UmsDepartmentService umsDepartmentService;

    @Autowired
    private UmsCourtService courtService;

    @Autowired
    private UmsAttachedTableService attachedTableService;

    @Autowired
    UmsUserOperationLogService umsUserOperationLogService; // 记录用户操作日志

    @Autowired
    private SqlSession sqlSession;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // 获取人员列表
    @Action("getPersonList")
    public String getPersonList() {
        HttpServletRequest httpServletRequest = ServletActionContext.getRequest();
        Integer courtNo = Integer.valueOf(httpServletRequest.getParameter("courtNo"));
        Integer deptNo = Integer.valueOf(httpServletRequest.getParameter("deptNo"));
        Integer startNum = Integer.valueOf(httpServletRequest.getParameter("start"));
        Integer pageSize = Integer.valueOf(httpServletRequest.getParameter("limit"));
        UmsUserInfoViewCriteria criteria = new UmsUserInfoViewCriteria();
        criteria.createCriteria().andCourtNoEqualTo(courtNo).andDepartmentEqualTo(deptNo).andIsValidEqualTo(1);
        RowBounds rowBounds = new RowBounds(startNum, pageSize);
        List<UmsUserInfoView> list = userInfoViewService.searchBySort(criteria, rowBounds);
        int total = userInfoViewService.count(criteria);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("results", total);
        map.put("rows", list);
        data = map;
        return "json";
    }

    // 获取该部门内的所有人员
    @Action("getPersonList_all")
    public String getPersonList_all() {
        HttpServletRequest httpServletRequest = ServletActionContext.getRequest();
        Integer courtNo = Integer.valueOf(httpServletRequest.getParameter("courtNo"));
        Integer deptNo = httpServletRequest.getParameter("deptNo") == null ? null : Integer.valueOf(httpServletRequest.getParameter("deptNo"));
        UmsUserInfoViewCriteria criteria = new UmsUserInfoViewCriteria();
        criteria.createCriteria().andCourtNoEqualTo(courtNo).andDepartmentEqualTo(deptNo).andIsValidEqualTo(1);
        RowBounds rowBounds = new RowBounds(0, 500);
        List<UmsUserInfoView> list = userInfoViewService.searchBySort(criteria, rowBounds);
        int total = userInfoViewService.count(criteria);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("results", total);
        map.put("rows", list);
        data = map;
        return "json";
    }

    @Action("saveTargetDepart")
    public String saveTargetDepart() {
        HttpServletRequest httpServletRequest = ServletActionContext.getRequest();
        // 更改的新部门
        Integer new_dept_no = Integer.valueOf(httpServletRequest.getParameter("new_dept_no"));
        // 当前法院的courtcode
        String courtcode_current = httpServletRequest.getParameter("courtcode_current");
        // 需要更改部门的人员id
        String[] ids = httpServletRequest.getParameter("ids").replaceFirst(",", "").split(",");

        //查询标准部门名称
        UmsDepartmentCriteria criteria = new UmsDepartmentCriteria();
        criteria.createCriteria().andCourtCodeEqualTo(courtcode_current).andDeptNoEqualTo(new_dept_no);
        List<UmsDepartment> umsDepartments = umsDepartmentService.selectByExample(criteria);
        String dept_org_code = umsDepartments.get(0).getOrgCode();
        // 用于更新部门的sql的map参数
        Map map = new HashMap();
        map.put("new_dept_no", new_dept_no);
        map.put("dept_org_code", dept_org_code);
        for (String id : ids) {
            map.put("id", id);
            // 查询未更改之前的该人员的部门
            List<Map<String, Object>> list_depart = sqlSession.selectList("UmsChangeDeptMapper.selectDeptByUserId", map);
            String old_department = String.valueOf(list_depart.get(0).get("department"));
            String old_dept_no = String.valueOf(list_depart.get(0).get("dept_org_code"));
            // 更新新部门
            sqlSession.update("UmsChangeDeptMapper.updateDeptByUserId_umsuserinfo", map);
            sqlSession.update("UmsChangeDeptMapper.updateDeptByUserId_xtpt", map);
            // 记录用户操作记录
            try {
                String ip = IpTools.getIpAddress(httpServletRequest);
                UmsUserInfoView user_ = (UmsUserInfoView) httpServletRequest.getSession().getAttribute(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
                UmsUserOperationLog umsUserOperationLog = new UmsUserOperationLog();
                umsUserOperationLog.setId(UUID.randomUUID().toString());
                umsUserOperationLog.setOperationUsername(user_.getFullname());
                umsUserOperationLog.setModifiedUserid(id);
                umsUserOperationLog.setOperationTypecode(99);
                umsUserOperationLog.setOperationTypedetail("更改人员部门");
                umsUserOperationLog.setOperationTime(simpleDateFormat.parse(simpleDateFormat.format(new Date())));
                umsUserOperationLog.setOperationIp(ip);
                umsUserOperationLog.setOperationContent("从部门old_department:" + old_department + " || old_dept_no:" + old_dept_no + "更改到new_dept_no:" + new_dept_no + " || dept_org_code:" + dept_org_code);
                umsUserOperationLogService.logUserOperation(umsUserOperationLog);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Map map_return = new HashMap();
        map_return.put("success", true);
        map_return.put("msg", "保存成功！");
        data = map_return;
        return "json";
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
