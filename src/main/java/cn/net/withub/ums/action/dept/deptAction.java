package cn.net.withub.ums.action.dept;

/**
 * Created by Administrator on 2016/1/8.
 */

import cn.net.withub.ums.entity.*;
import cn.net.withub.ums.service.*;
import cn.net.withub.ums.util.StringTools;
import cn.net.withub.ums.util.StringUtils;
import com.google.gson.Gson;
import org.apache.ibatis.session.RowBounds;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.*;

/**
 * Created by Administrator on 2015/12/23.
 */
@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/deptAction")
@Results({
        @Result(name = "json", type = "json", params = {"root", "data"})
})
public class deptAction {

    @Autowired
    private UmsDepartmentService departmentService;

    @Autowired
    private UmsCourtService courtService;

    @Autowired
    private XtptBmDepartementService xtptBmDepartementService;

    @Autowired
    private UmsUserInfoService umsUserInfoService;

    @Autowired
    private UmsCourtFullService courtFullService;

    private Object data;

    private UmsDepartment department;

    private Integer start = 0;
    private Integer limit = 10;
    private String field;
    private String direction;

    //部门排序用
    private Integer startDeptSort;
    private Integer endDeptSort;

    @Action("insertDept")
    public String affiliatedDelete() {
        Map<String, Object> map = new HashMap<>();
        data = map;
        boolean flag = false;
        if (department.getCourtNo() == null || department.getOrgCode() == null || department.getDeptName() == null || department.getLevel() == null || department.getInstitutionCode() == null || department.getCourtLevel() == null || department.getDeptType() == null || department.getIsPeples() == null || department.getIsLeaders() == null) {
            map.put("success", flag);
            return "json";
        }
        try {
            String detN = department.getDeptName().trim();
            department.setDeptName(detN);
            UmsDepartmentCriteria criteria = new UmsDepartmentCriteria();
            // 先看法院名称是否重名了
            criteria.createCriteria().andCourtNoEqualTo(department.getCourtNo()).andDeptNameEqualTo(department.getDeptName());
            List<UmsDepartment> check_list = departmentService.selectByExample(criteria);
            if (check_list.size() > 0) {
                map.put("success", flag);
                map.put("reason", 1);
                return "json";
            }
            if (department.getDeptNo() == null) {// 部门编码为空的情况
                String courtCode = courtService.courtNo2CourtCode(department.getCourtNo());
                department.setCourtCode(courtCode);
                department.setCourtStdNo(courtService.courtNo2CourtStdNo(department.getCourtNo()));
                Integer i = 0;
                Map<String, Object> rmap = new HashMap<>();
                rmap.put("courtNo", department.getCourtNo());
                rmap.put("level", 1);
                i = departmentService.selectMaxDeptNo(rmap);
                if (i == null) {
                    // 之前没有部门
                    i = 0;
                } else if (i >= 999 ) {
                    // 部门最大值超过1000
                    i = makeDeptNo(1) - 1;
                }
                department.setDeptNo(i + 1);
                // 部门名  标准编码相关
                XtptBmDepartementExample example = new XtptBmDepartementExample();
                example.createCriteria().andOrgCodeEqualTo(department.getOrgCode());
                List<XtptBmDepartement> xtptBmDepts = xtptBmDepartementService.selectByExample(example);
                XtptBmDepartement xtptBmDept = xtptBmDepts.get(0);
                department.setOrgCode(xtptBmDept.getOrgCode());
                department.setOrgType(xtptBmDept.getOrgType());
                department.setDeptStName(xtptBmDept.getDeptName());
                // 第一级
                department.setLevel(1);
                // 拼接levelcode
                StringBuffer returnStr = new StringBuffer("");
                String deptStr = String.format("%04d", department.getDeptNo());
                returnStr.append(deptStr);
                returnStr.append("0000");
                returnStr.append("0000");
                if (returnStr.toString().length() != 12) {
                    map.put("success", flag);
                    return "json";
                }
                department.setLevelCode(returnStr.toString());
                department.setIsValid(1);
                // 排序
                Integer j = departmentService.selectMaxSortNo(rmap);
                if (j == null) {
                    j = 0;
                }
                department.setSortNo(j + 1);
                // 系统录入 data_type 设置为2
                department.setDataType(2);
                department.setCreateTime(new Date());
                // 这里将该部门的上级部门的id存入parentId 20190408 如果是第一级有父级id吗?有的,以ums_court_full表中的id为准
                department.setId(UUID.randomUUID().toString());
                UmsCourtFullCriteria umsCourtFullCriteria = new UmsCourtFullCriteria();
                umsCourtFullCriteria.createCriteria().andCourtCodeEqualTo(department.getCourtCode());
                List<UmsCourtFull> list_umsCourtFull = courtFullService.selectByExample(umsCourtFullCriteria);
                department.setParentId(list_umsCourtFull.get(0).getId());
                department.setCourtShortName(list_umsCourtFull.get(0).getCourtShortName());
                // --
                int g = departmentService.insert(department);
                flag = g > 0 ? true : false;
            } else {
                String courtCode = courtService.courtNo2CourtCode(department.getCourtNo());
                if (courtCode == null || courtCode.equals("")) {
                    map.put("success", flag);
                    return "json";
                }
                department.setCourtCode(courtCode);
                department.setCourtStdNo(courtService.courtNo2CourtStdNo(department.getCourtNo()));
                // 一级节点信息
                UmsDepartment lev_o = null;
                // 二级节点信心
                UmsDepartment lev_t = null;
                if (department.getLevel() == 1) {
                    criteria.clear();
                    criteria.createCriteria().andCourtNoEqualTo(department.getCourtNo()).andLevelEqualTo(1).andDeptNoEqualTo(department.getDeptNo());
                    lev_o = departmentService.selectByExample(criteria).get(0);
                } else if (department.getLevel() == 2) {
                    criteria.clear();
                    criteria.createCriteria().andCourtNoEqualTo(department.getCourtNo()).andLevelEqualTo(2).andDeptNoEqualTo(department.getDeptNo());
                    lev_t = departmentService.selectByExample(criteria).get(0);
                    criteria.clear();
                    criteria.createCriteria().andCourtNoEqualTo(department.getCourtNo()).andLevelEqualTo(1).andDeptNoEqualTo(Integer.parseInt(lev_t.getLevelCode().substring(2, 4)));
                    lev_o = departmentService.selectByExample(criteria).get(0);
                } else {
                    map.put("success", flag);
                    return "json";
                }
                StringBuffer returnStr = new StringBuffer("");
                String deptStr = String.format("%04d", lev_o.getDeptNo());
                returnStr.append(deptStr);
                if (department.getLevel() == 2) {
                    returnStr.append(String.valueOf(lev_t.getDeptNo()));
                }
                Integer i = 0;
                Map<String, Object> rmap = new HashMap<>();
                rmap.put("courtNo", department.getCourtNo());
                rmap.put("level", department.getLevel() + 1);
                i = departmentService.selectMaxDeptNo(rmap);
                if (department.getLevel() == 2) {
                    if (i == null) {
                        i = 2000;
                    }
                } else if (department.getLevel() == 1) {
                    if (i == null) {
                        i = 1000;
                    } else if (i >= 1999) {
                        i = makeDeptNo(2) - 1;
                    }
                }
                rmap.put("levelCode", returnStr.toString() + "%");
                department.setDeptNo(i + 1);
                // 部门名  标准编码相关
                XtptBmDepartementExample example = new XtptBmDepartementExample();
                example.createCriteria().andOrgCodeEqualTo(department.getOrgCode());
                List<XtptBmDepartement> xtptBmDepts = xtptBmDepartementService.selectByExample(example);
                XtptBmDepartement xtptBmDept = xtptBmDepts.get(0);
                department.setOrgCode(xtptBmDept.getOrgCode());
                department.setOrgType(xtptBmDept.getOrgType());
                department.setDeptStName(xtptBmDept.getDeptName());
                // 拼接levelcode
                if (department.getLevel() == 1) {
                    returnStr.append(department.getDeptNo());
                    returnStr.append("0000");
                    if (returnStr.toString().length() != 12) {
                        map.put("success", flag);
                        return "json";
                    }
                } else if (department.getLevel() == 2) {
                    returnStr.append(department.getDeptNo());
                    if (returnStr.toString().length() != 12) {
                        map.put("success", flag);
                        return "json";
                    }
                }
                department.setLevelCode(returnStr.toString());
                department.setIsValid(1);
                department.setLevel(department.getLevel() + 1);
                Integer j = departmentService.selectMaxSortNo(rmap);
                if (j == null) {
                    j = 0;
                }
                department.setSortNo(j + 1);
                // 系统录入 data_type 设置为2
                department.setDataType(2);
                department.setCreateTime(new Date());
                // 20190408 id, parent_id, court_short_name
                department.setId(UUID.randomUUID().toString());
                UmsDepartmentCriteria umsDepartmentCriteria = new UmsDepartmentCriteria();
                String partten = "";
                switch (department.getLevel() - 1) {
                    case 1:
                        partten = department.getLevelCode().substring(0, 4) + "________";
                        break;
                    case 2:
                    case 3:
                        partten = department.getLevelCode().substring(0, 8) + "____";
                        break;
                }
                umsDepartmentCriteria.createCriteria().andCourtNoEqualTo(department.getCourtNo()).andLevelEqualTo(department.getLevel() - 1).andLevelCodeLike(partten);
                List<UmsDepartment> parent_umsdepartment = departmentService.selectByExample(umsDepartmentCriteria);
                department.setParentId(parent_umsdepartment.get(0).getId());
                UmsCourtFullCriteria umsCourtFullCriteria = new UmsCourtFullCriteria();
                umsCourtFullCriteria.createCriteria().andCourtCodeEqualTo(department.getCourtCode());
                List<UmsCourtFull> list_umsCourtFull = courtFullService.selectByExample(umsCourtFullCriteria);
                department.setCourtShortName(list_umsCourtFull.get(0).getCourtShortName());
                // id, parent_id, court_short_name end
                int g = departmentService.insert(department);
                flag = g > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("success", flag);
        return "json";
    }

    @Action("getBmDept")
    public String getBmDept() {

        Map<String, Object> map = new HashMap<>();
        data = map;
        XtptBmDepartementExample example = new XtptBmDepartementExample();
        List<XtptBmDepartement> xtptBmDepts = xtptBmDepartementService.selectByExample(example);
        map.put("data", xtptBmDepts);
        return "json";

    }

    @Action("getDeptListInfo")
    public String deptDetailInfo() {
        List<UmsDepartment> dept = new ArrayList<>();
        RowBounds rowBounds = new RowBounds(start, limit);
        String orderStr = "case when " + StringTools.camelOrPascalToUnderline(field) + " is null then 1 else 0 end ," + StringTools.camelOrPascalToUnderline(field) + " " + direction;
        int total = 0;
        UmsDepartmentCriteria criteria = new UmsDepartmentCriteria();
        UmsDepartmentCriteria.Criteria c = criteria.createCriteria();
        if (department != null && department.getIsValid() != null) {
            c.andIsValidEqualTo(department.getIsValid());
        }
        try {
            if (department == null || department.getCourtNo() == null) {
                c.andLevelEqualTo(1); //部门级别
                criteria.setOrderByClause(orderStr);
                dept = departmentService.selectByExample(criteria, rowBounds);
            } else {
                UmsDepartmentKey key = new UmsDepartmentKey();
                key.setCourtNo(department.getCourtNo());
                key.setDeptNo(department.getDeptNo());
                //如果没有部门编码 出该法院第一级部门列表
                try {
                    if (key.getDeptNo() == null) {
                        // 法院编号 部门级别
                        c.andCourtNoEqualTo(key.getCourtNo()).andLevelEqualTo(1).andDeptNameNotEqualTo("人民陪审员庭");
                        criteria.setOrderByClause(orderStr);
                        dept = departmentService.selectByExample(criteria, rowBounds);
                    } else {
                        UmsDepartment parent = departmentService.selectByPrimaryKey(key);
                        if (parent == null) {
                            return null;
                        }
                        String partten = "";
                        switch (parent.getLevel()) {
                            case 1:
                                partten = parent.getLevelCode().substring(0, 4) + "________";
                                break;
                            case 2:
                                partten = parent.getLevelCode().substring(0, 8) + "____";
                                break;
                            case 3:
                                break;
                        }
                        // 法院编号 级别编码 非自己
                        c.andCourtNoEqualTo(parent.getCourtNo()).andLevelCodeLike(partten).andLevelCodeNotEqualTo(parent.getLevelCode()).andDeptNameNotEqualTo("人民陪审员庭").andLevelEqualTo(parent.getLevel() + 1);
                        criteria.setOrderByClause(orderStr);
                        dept = departmentService.selectByExample(criteria, rowBounds);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        total = departmentService.countByExample(criteria);
        Map<String, Object> map = new HashMap<>();
        map.put("results", total);
        map.put("rows", dept);
        data = map;
        return "json";
    }

    @Action("updateDeptInfo")
    public String updateDeptInfo() {
        int i = 0,j = 0;
        try {
            if (department.getCourtNo() != null && !department.getCourtNo().equals("") && department.getDeptNo() != null) {
                XtptBmDepartementExample example = new XtptBmDepartementExample();
                example.createCriteria().andOrgCodeEqualTo(department.getOrgCode());
                List<XtptBmDepartement> xtptBmDepts = xtptBmDepartementService.selectByExample(example);
                XtptBmDepartement xtptBmDept = xtptBmDepts.get(0);
                department.setOrgCode(xtptBmDept.getOrgCode());
                department.setOrgType(xtptBmDept.getOrgType());
                department.setDeptStName(xtptBmDept.getDeptName());
                department.setUpdateTime(new Date());
                i = departmentService.updateByPrimaryKeySelective(department);
                if (i > 0) {
                    UmsUserInfoCriteria uc = new UmsUserInfoCriteria();
                    uc.createCriteria().andCourtNoEqualTo(department.getCourtNo()).andDepartmentEqualTo(department.getDeptNo());
                    int updateNum = umsUserInfoService.countByExample(uc);
                    if (updateNum > 0) {
                        Map paramMap = new HashMap();
                        paramMap.put("newDept", xtptBmDept.getOrgCode());
                        paramMap.put("court_no", department.getCourtNo());
                        paramMap.put("dept_no", department.getDeptNo());
                        j = umsUserInfoService.updateXtptAndUserDeptCode(paramMap);
                    }else {
                        j = 1;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<>();
        map.put("success", j > 0);
        data = map;
        return "json";
    }

    @Action("updateDeptSort")
    public String updateDeptSort() {

        int i = 0,j = 0;

        try {
            if (department.getCourtNo() != null && !department.getCourtNo().equals("") && department.getDeptNo() != null) {

                Map paramMap = new HashMap();
                if (startDeptSort >= endDeptSort) {
                    paramMap.put("type", 1);
                    paramMap.put("startDeptSort", endDeptSort);
                    paramMap.put("endDeptSort", startDeptSort);
                } else {
                    paramMap.put("type", 2);
                    paramMap.put("startDeptSort", startDeptSort);
                    paramMap.put("endDeptSort", endDeptSort);
                }
                paramMap.put("courtNo", department.getCourtNo());
                paramMap.put("level", department.getLevel());
                j = departmentService.updateDeptSort(paramMap);
                if (j > 0) {
                    department.setSortNo(endDeptSort);
                    department.setUpdateTime(new Date());
                    i = departmentService.updateByPrimaryKeySelective(department);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<>();
        map.put("success", i > 0);

        data = map;
        return "json";
    }

    @Action("updateDeptSortNew")
    public String updateDeptSortNew() {
        Map<String, Object> result = new HashMap<>();
        int i = 0;
        try {
            Gson gson = new Gson();
            List<Map> list = gson.fromJson(field, List.class);
            long count = list.stream().filter(map -> StringUtils.isEmpty(map.get("courtno")) || StringUtils.isEmpty(map.get("deptno")) || StringUtils.isEmpty(map.get("newSort"))).count();
            if (count > 0) {
                result.put("msg", "参数错误");
            } else {
                for (Map map : list) {
                    UmsDepartment department = new UmsDepartment();
                    department.setCourtNo(Integer.parseInt(map.get("courtno").toString()));
                    department.setDeptNo(Integer.parseInt(map.get("deptno").toString()));
                    department.setSortNo(Integer.parseInt(map.get("newSort").toString()));
                    department.setUpdateTime(new Date());
                    i = departmentService.updateByPrimaryKeySelective(department);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.put("success", i > 0);
        data = result;
        return "json";
    }


    @Action("enableDeptInfo")
    public String enableDeptInfo() {

        int i = 0;
        Map<String, Object> map = new HashMap<>();
        data = map;
        try {

            if (department.getCourtNo() != null && !department.getCourtNo().equals("") && department.getDeptNo() != null) {
                //先验证部门下面有人没
                UmsUserInfoCriteria criteria = new UmsUserInfoCriteria();
                criteria.createCriteria().andCourtNoEqualTo(department.getCourtNo()).andDepartmentEqualTo(department.getDeptNo()).andIsValidEqualTo(1);
                List<UmsUserInfo> list = umsUserInfoService.selectByExample(criteria);
                if (list.size() > 0) {
                    map.put("success", false);
                    map.put("reason", 1);
                    return "json";
                }

                UmsDepartment u = new UmsDepartment();
                u.setCourtNo(department.getCourtNo());
                u.setDeptNo(department.getDeptNo());
                u.setIsValid(department.getIsValid());
                u.setUpdateTime(new Date());
                i = departmentService.updateByPrimaryKeySelective(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("success", i > 0 ? true : false);

        return "json";
    }


    //获取部门的信息 (组织机构页面用)
    @Action("getDeptByNo")
    public String getDeptByNo(){

        try {

            if(department.getCourtNo() != null && department.getDeptNo() != null){
                UmsDepartmentCriteria criteria = new UmsDepartmentCriteria();
                criteria.createCriteria().andCourtNoEqualTo(department.getCourtNo()).andDeptNoEqualTo(department.getDeptNo());
                List<UmsDepartment> umsDepartments = departmentService.selectByExample(criteria);

                if(umsDepartments.size() ==1){
                    Map<String,Object> returnMap = new HashMap<>();
                    UmsDepartment umsDepartment = umsDepartments.get(0);
                    returnMap.put("info",umsDepartment);

                    //获取父级机构
                    if(umsDepartment.getParentId() != null){
                        criteria.clear();
                        criteria.createCriteria().andCourtNoEqualTo(umsDepartment.getCourtNo()).andIdEqualTo(umsDepartment.getParentId());
                        List<UmsDepartment> umsDepartments2 = departmentService.selectByExample(criteria);
                        if(umsDepartments2.size() == 1){
                            returnMap.put("parentName" , umsDepartments2.get(0).getDeptName());
                        }


                    }else{
                        //父级id为空的话 那么父级只接是法院
                        UmsCourtFullCriteria courtFullCriteria = new UmsCourtFullCriteria();
                        courtFullCriteria.clear();
                        courtFullCriteria.createCriteria().andCourtNoEqualTo(department.getCourtNo());
                        List<UmsCourtFull> umsCourtFulls1 = courtFullService.selectByExample(courtFullCriteria);
                        if(umsCourtFulls1.size() == 1){
                            returnMap.put("parentName" , umsCourtFulls1.get(0).getCourtStdName());
                        }
                    }

                    data = returnMap;

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "json";
    }


    //保存部门信息
    @Action("saveDeptInfo")
    public String saveDeptInfo(){

        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("success" ,false);
        data = returnMap;
        if(department != null && department.getCourtNo() != null && department.getDeptNo() != null){


            int i = departmentService.updateByPrimaryKeySelective(department);
            if(i > 0){
                returnMap.put("success" ,true);
            }


        }

        return "json";
    }

    private int makeDeptNo(int level) {

        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("courtNo", department.getCourtNo());
        List<Integer> deptL = departmentService.getLevelDeptNo(queryMap);
        int i = 0;
        int j = 1000;
        switch (level) {
            case 1:
                i = 1;
                j = 1000;
                break;
            case 2:
                i = 1001;
                j = 2000;
                break;
            case 3:
                i = 2001;
                j = 3000;
                break;

        }
        for (; i <= j; i++) {
            if (!deptL.contains(i)) {
                return i;
            }
        }
        return 0;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public UmsDepartment getDepartment() {
        return department;
    }

    public void setDepartment(UmsDepartment department) {
        this.department = department;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getStartDeptSort() {
        return startDeptSort;
    }

    public void setStartDeptSort(Integer startDeptSort) {
        this.startDeptSort = startDeptSort;
    }

    public Integer getEndDeptSort() {
        return endDeptSort;
    }

    public void setEndDeptSort(Integer endDeptSort) {
        this.endDeptSort = endDeptSort;
    }
}

