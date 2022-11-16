package cn.net.withub.ums.action.institution;

import cn.net.withub.ums.entity.*;
import cn.net.withub.ums.service.*;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URLEncoder;
import java.util.*;

/*
单位部门受奖惩情况
 */

@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/rewardPunish")
@Results({
        @Result(name = "json", type = "json", params = {"root", "data"}),
        @Result(name = "stream", type = "stream",
                params = {"inputName", "inputStream", "contentDisposition", "attachment;filename=${filename}",})
})
public class UmsInstitutionRewardPunishAction {

    @Autowired
    UmsInstitutionRewardPunishService rewardPunishService;


    @Autowired
    private UmsCourtFullService courtFullService;

    @Autowired
    private UmsDepartmentService departmentService;

    private UmsInstitutionRewardPunish information;

    private UmsInstitutionUploadFile uploadFile;

    @Autowired
    private UmsInstitutionUploadFileService uploadFileService;

    private Object data;

    private Integer start;

    private Integer limit;

    private ByteArrayInputStream inputStream;
    private String filename;

    @Action("query")
    public String select() {

        try {
            if (information != null) {

                UmsInstitutionRewardPunishCriteria example = new UmsInstitutionRewardPunishCriteria();
                UmsInstitutionRewardPunishCriteria.Criteria criteria = example.createCriteria();
                example.setOrderByClause(" insert_time asc");

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

                Map<String, Object> returnMap = new HashMap<>();

                returnMap.put("rows", rewardPunishService.selectByExample(example));
                returnMap.put("results", rewardPunishService.countByExample(example));

                data = returnMap;

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

                //所有信息点不能全部为空
                boolean flag = false;
                Field[] declaredFields = UmsInstitutionRewardPunish.class.getDeclaredFields();
                for (Field declaredField : declaredFields) {
                    if (Modifier.isStatic(declaredField.getModifiers())) {
                        continue;
                    }

                    declaredField.setAccessible(true);

                    if ("institutionId".equals(declaredField.getName()) || "courtCode".equals(declaredField.getName())) {
                        continue;
                    }

                    Object o = declaredField.get(information);
                    if (o instanceof String && StringUtils.hasText(o.toString())) {
                        flag = true;
                        break;
                    }
                }

                if (flag) {

                    UmsCourtFullCriteria criteria = new UmsCourtFullCriteria();
                    criteria.createCriteria().andCourtNoEqualTo(information.getCourtNo());
                    List<UmsCourtFull> umsCourtFulls = courtFullService.selectByExample(criteria);

                    if (umsCourtFulls.size() == 1) {
                        UmsCourtFull umsCourtFull = umsCourtFulls.get(0);
                        if(!StringUtils.hasText(information.getId())){
                            information.setId(UUID.randomUUID().toString());
                        }
                        information.setCourtNo(umsCourtFull.getCourtNo());
                        information.setCourtStdNo(umsCourtFull.getCourtStdNo());
                        information.setCourtCode(umsCourtFull.getCourtCode());
                        information.setInsertTime(new Date());

                        //如果没有部门是法院
                        Integer deptNo = information.getDeptNo();
                        int insert = 0;
                        if (deptNo == null) {
                            //法院
                            information.setInstitutionId(umsCourtFull.getId());
                            insert = rewardPunishService.insert(information);

                        } else {
                            //部门
                            UmsDepartmentCriteria departmentCriteria = new UmsDepartmentCriteria();
                            departmentCriteria.createCriteria().andCourtNoEqualTo(umsCourtFull.getCourtNo()).andDeptNoEqualTo(deptNo);
                            List<UmsDepartment> umsDepartments = departmentService.selectByExample(departmentCriteria);

                            if (umsDepartments.size() > 0) {
                                information.setInstitutionId(umsDepartments.get(0).getId());
                                insert = rewardPunishService.insert(information);
                            }

                        }

                        if (insert > 0) {
                            returnMap.put("success", true);
                        }

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
                int i = rewardPunishService.updateByPrimaryKeySelective(information);
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
                int i = rewardPunishService.deleteByPrimaryKey(information.getId());
                if (i > 0) {
                    //同时把附件的停启用置为停用
                    UmsInstitutionUploadFile uploadFile = new UmsInstitutionUploadFile();
                    uploadFile.setIsValid(false);
                    UmsInstitutionUploadFileCriteria example = new UmsInstitutionUploadFileCriteria();
                    example.createCriteria().andRelationIdEqualTo(information.getId());
                    uploadFileService.updateByExampleSelective(uploadFile,example);

                    returnMap.put("success", true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        data = returnMap;
        return "json";
    }


    @Action("fjQuery")
    public String fjQuery() {

        List<UmsInstitutionUploadFile> re = new ArrayList<>();

        if (!StringUtils.hasText(uploadFile.getRelationId()) || !StringUtils.hasText(uploadFile.getRelationId())) {
            return "json";
        }

        UmsInstitutionUploadFileCriteria example = new UmsInstitutionUploadFileCriteria();
        example.setOrderByClause(" record_time asc");
        example.createCriteria().andRelationIdEqualTo(uploadFile.getRelationId()).andRelationTypeEqualTo(uploadFile.getRelationType()).andIsValidEqualTo(true);

        re = uploadFileService.selectByExample(example);

        data = re;

        return "json";
    }


    //附件删除把停启用标准置为false;
    @Action("fjDelete")
    public String fjDelete() {

        Map<String, Object> re = new HashMap<>();
        re.put("success", false);
        data = re;
        try {
            if (!StringUtils.hasText(uploadFile.getId())) {
                return "json";
            }

            UmsInstitutionUploadFile record = new UmsInstitutionUploadFile();
            record.setId(uploadFile.getId());
            record.setIsValid(false);

            int i = uploadFileService.updateByPrimaryKeySelective(record);
            if (i > 0) {
                re.put("success", true);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "json";
    }


    //下载附件
    @Action("downLoadFj")
    public String downLoad()
    {
        Map<String,Object> map = new HashMap<>();
        try {
            if(uploadFile != null && StringUtils.hasText(uploadFile.getId()))
            {
                UmsInstitutionUploadFile uploadFile = uploadFileService.selectByPrimaryKey(this.uploadFile.getId());
                if(uploadFile!= null && StringUtils.hasText(uploadFile.getSavePath()))
                {
                    byte[] bytes = FileUtils.readFileToByteArray(new File(uploadFile.getSavePath()));
                    this.inputStream = new ByteArrayInputStream(bytes);
                    filename = uploadFile.getFileName();
                    return "stream";
                }
                else
                {
                    map.put("msg","找不到这个附件或附件已删！");
                    data = map;
                    return "json";
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            map.put("msg","找不到这个附件或附件已删！");
            data = map;
        }
        return "json";
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

    public UmsInstitutionRewardPunish getInformation() {
        return information;
    }

    public void setInformation(UmsInstitutionRewardPunish information) {
        this.information = information;
    }

    public UmsInstitutionUploadFile getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(UmsInstitutionUploadFile uploadFile) {
        this.uploadFile = uploadFile;
    }

    public ByteArrayInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(ByteArrayInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getFilename() throws UnsupportedEncodingException
    {
        return URLEncoder.encode(filename, "UTF-8");
//        return new String(filename.getBytes("utf-8"),"utf-8");
    }

    public void setFilename(String filename) throws UnsupportedEncodingException {
        this.filename = new String(filename.getBytes("utf-8"), "utf-8");
    }
}
