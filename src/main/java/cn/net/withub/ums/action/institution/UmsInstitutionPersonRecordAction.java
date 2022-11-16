package cn.net.withub.ums.action.institution;

import cn.net.withub.ums.action.userinfo.UserInfoViewAction;
import cn.net.withub.ums.entity.*;
import cn.net.withub.ums.service.UmsCourtFullService;
import cn.net.withub.ums.service.UmsDepartmentService;
import cn.net.withub.ums.service.UmsInstitutionPersonRecordService;
import cn.net.withub.ums.service.UmsUserInfoViewService;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/*
 机构编制
 */
@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/personRecord")
@Results({
        @Result(name = "json", type = "json", params = {"root", "data"})
})
public class UmsInstitutionPersonRecordAction {

    @Autowired
    UmsInstitutionPersonRecordService service;

    @Autowired
    private UmsCourtFullService courtFullService;

    @Autowired
    private UmsDepartmentService departmentService;

    @Autowired
    private UmsUserInfoViewService userInfoViewService;

    private UmsInstitutionPersonRecord information;

    private Object data;

    public Integer start;

    public Integer limit;

    @Action("query")
    public String select() {
        try {
            if (information != null) {
                UmsInstitutionPersonRecordCriteria example = new UmsInstitutionPersonRecordCriteria();
                UmsInstitutionPersonRecordCriteria.Criteria criteria = example.createCriteria();
                boolean flag = false;
                if (information.getCourtNo() != null) {
                    criteria.andCourtNoEqualTo(information.getCourtNo());
                    flag = true;
                }
                if (information.getDeptNo() != null) {
                    criteria.andDeptNoEqualTo(information.getDeptNo());
                    flag = true;
                }else{
                    criteria.andDeptNoIsNull();
                }
                if (!flag) {
                    return "json";
                }
                if (start != null) {
                    example.setOffset(start);
                }
                if (limit != null) {
                    example.setOffset(limit);
                }
                List<UmsInstitutionPersonRecord> umsInstitutionPersonRecords = service.selectByExample(example);
                //初始化信息
                if (umsInstitutionPersonRecords.size() == 0) {
                    insert();
                    umsInstitutionPersonRecords = service.selectByExample(example);
                }
                if (umsInstitutionPersonRecords.size() == 0) {
                    return "json";
                }
                UmsInstitutionPersonRecord umsInstitutionPersonRecord = umsInstitutionPersonRecords.get(0);
                data = umsInstitutionPersonRecord;
                //统计
                //统计审判业务机构数
                UmsDepartmentCriteria departExample = new UmsDepartmentCriteria();
                UmsDepartmentCriteria.Criteria departCriteria = departExample.createCriteria();
                if (information.getCourtNo() != null) {
                    departCriteria.andCourtNoEqualTo(information.getCourtNo());
                }
                if (information.getDeptNo() != null) {
                    departCriteria.andDeptNoEqualTo(information.getDeptNo());
                }
                departCriteria.andDeptTypeEqualTo("1");
                int spywjgs = departmentService.countByExample(departExample);
                umsInstitutionPersonRecord.setSpywjgs(spywjgs);
                // 行政后勤机构数 = 非审判部门
                UmsDepartmentCriteria departExample_xzhqjgs = new UmsDepartmentCriteria();
                UmsDepartmentCriteria.Criteria departCriteria_xzhqjgs = departExample_xzhqjgs.createCriteria();
                if (information.getCourtNo() != null) {
                    departCriteria_xzhqjgs.andCourtNoEqualTo(information.getCourtNo());
                }
                if (information.getDeptNo() != null) {
                    departCriteria_xzhqjgs.andDeptNoEqualTo(information.getDeptNo());
                }
                departCriteria_xzhqjgs.andDeptTypeEqualTo("2");
                int xzhqjgs = departmentService.countByExample(departExample_xzhqjgs);
                umsInstitutionPersonRecord.setXzhqjgs(xzhqjgs);
                //中央编制人员
                UmsUserInfoViewCriteria userExample = new UmsUserInfoViewCriteria();
                UmsUserInfoViewCriteria.Criteria c = userExample.createCriteria();
                String orgCode = "1";
                List<Integer> codeList = UserInfoViewAction.orgCodeMap.get(orgCode);
                List<Integer> codeList2 = new ArrayList<>();
                codeList.forEach(integer -> {
                    codeList2.add(integer);
                });
                c.andPreparationIn(codeList2);
                c.andIsValidEqualTo(1).andUserTypeEqualTo(1).andLeaveReasonIsNull();
                if (information.getCourtNo() != null) {
                    c.andCourtNoEqualTo(information.getCourtNo());
                }
                if (information.getDeptNo() != null) {
                    c.andDepartmentEqualTo(information.getDeptNo());
                }
                int zybzxys = userInfoViewService.count(userExample);
                umsInstitutionPersonRecord.setZybzxys(zybzxys);
                //地方编制人员
                orgCode = "2";
                codeList = UserInfoViewAction.orgCodeMap.get(orgCode);
                codeList2.clear();
                codeList.forEach(integer -> {
                    codeList2.add(integer);
                });
                userExample.clear();
                UmsUserInfoViewCriteria.Criteria d = userExample.createCriteria();
                d.orPreparationIn(codeList2);
                d.andIsValidEqualTo(1).andUserTypeEqualTo(1).andLeaveReasonIsNull();
                if (information.getCourtNo() != null) {
                    d.andCourtNoEqualTo(information.getCourtNo());
                }
                if (information.getDeptNo() != null) {
                    d.andDepartmentEqualTo(information.getDeptNo());
                }
                int dfbzxys = userInfoViewService.count(userExample);
                umsInstitutionPersonRecord.setDfbzxys(dfbzxys);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "json";
    }

    @Action("insert")
    public String insert() {
        //所以信息不能都为空
        Map<String, Object> returnMap = new HashMap<>();

        try {

            if (information != null && information.getCourtNo() != null) {

                UmsCourtFullCriteria criteria = new UmsCourtFullCriteria();
                criteria.createCriteria().andCourtNoEqualTo(information.getCourtNo());
                List<UmsCourtFull> umsCourtFulls = courtFullService.selectByExample(criteria);

                if (umsCourtFulls.size() == 1) {

                    UmsCourtFull umsCourtFull = umsCourtFulls.get(0);
                    information.setId(UUID.randomUUID().toString());
                    information.setCourtNo(umsCourtFull.getCourtNo());
                    information.setCourtStdNo(umsCourtFull.getCourtStdNo());
                    information.setCourtCode(umsCourtFull.getCourtCode());

                    //如果没有部门是法院
                    Integer deptNo = information.getDeptNo();
                    int insert = 0;
                    if (deptNo == null) {
                        //法院
                        information.setInstitutionId(umsCourtFull.getId());
                        System.out.println("\n\n\n" + information.toString());
                        insert = service.insert(information);

                    } else {
                        //部门
                        UmsDepartmentCriteria departmentCriteria = new UmsDepartmentCriteria();
                        departmentCriteria.createCriteria().andCourtNoEqualTo(umsCourtFull.getCourtNo()).andDeptNoEqualTo(deptNo);
                        List<UmsDepartment> umsDepartments = departmentService.selectByExample(departmentCriteria);

                        if (umsDepartments.size() > 0) {
                            information.setInstitutionId(umsDepartments.get(0).getId());
                            insert = service.insert(information);
                        }
                    }

                    if (insert > 0) {
                        returnMap.put("success", true);
                    }

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        data = returnMap;
        return "json";
    }


    @Action("update")
    public String update() {

        Map<String, Object> returnMap = new HashMap<>();

        try {
            if (information != null && StringUtils.hasText(information.getId())) {
                int i = service.updateByPrimaryKeySelective(information);
                if (i > 0) {
                    returnMap.put("success", true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        data = returnMap;
        return "json";
    }

    @Action("delete")
    public String delete() {

        Map<String, Object> returnMap = new HashMap<>();

        try {
            if (information != null && StringUtils.hasText(information.getId())) {
                int i = service.deleteByPrimaryKey(information.getId());
                if (i > 0) {
                    returnMap.put("success", true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        data = returnMap;
        return "json";
    }


    public UmsInstitutionPersonRecord getInformation() {
        return information;
    }

    public void setInformation(UmsInstitutionPersonRecord information) {
        this.information = information;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
