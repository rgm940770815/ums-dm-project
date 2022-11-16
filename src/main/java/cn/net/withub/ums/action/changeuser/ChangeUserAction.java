package cn.net.withub.ums.action.changeuser;

import cn.net.withub.ums.action.userinfo.affiliatedInfoAction;
import cn.net.withub.ums.dao.UmsCodeMapper;
import cn.net.withub.ums.dao.UmsCourtFullMapper;
import cn.net.withub.ums.entity.*;
import cn.net.withub.ums.service.UmsCourtService;
import cn.net.withub.ums.service.UmsDepartmentService;
import cn.net.withub.ums.service.UmsUserInfoService;
import cn.net.withub.ums.util.SessionUtils;
import com.aspose.cells.Cells;
import com.aspose.cells.Row;
import com.aspose.cells.RowCollection;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/change1")
@Results({
        @Result(name = "json", type = "json", params = {"root", "data"})
})
public class ChangeUserAction {

    @Autowired
    private UmsUserInfoService userInfoService;

    @Autowired
    private UmsCourtFullMapper courtMapper;

    @Autowired
    private UmsCodeMapper umsCodeMapper;

    @Autowired
    private UmsDepartmentService umsDepartmentService;

    @Autowired
    affiliatedInfoAction affiliatedInfoAction;

    @Autowired
    ChangeUserDao changeUserDao;


    private Object data;

    private UpdateUser updateUser;

    public UpdateUser getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(UpdateUser updateUser) {
        this.updateUser = updateUser;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Action("changeUser")
    public String changeUser(){

        System.out.println("in=====");
        Map<String,Object> re = new HashMap<>();
        re.put("success",false);

        data = re;
        try {

            UmsUserInfoCriteria criteria = new UmsUserInfoCriteria();
            UmsUserInfoCriteria.Criteria criteria1 = criteria.createCriteria().andIsValidEqualTo(1);
            criteria1.andFullnameEqualTo(updateUser.getName());

            if(StringUtils.hasText(updateUser.getOldCourt())){
                Integer courtNoByNameLike = getCourtNoByNameLike(updateUser.getOldCourt());
                if (StringUtils.hasText(updateUser.getOldDept())) {
                    Integer deptNoByNameLike = getDepartment(courtNoByNameLike, updateUser.getOldDept());
                    criteria1.andDepartmentEqualTo(deptNoByNameLike);
                }
                criteria1.andCourtNoEqualTo(courtNoByNameLike);
            }

            //查找人员
            List<UmsUserInfo> umsUserInfos = userInfoService.selectByExampleWithNoAspect(criteria);
            if(umsUserInfos.size() == 0){
                throw new Exception("人员查询结果为空");
            }

            if(umsUserInfos.size() > 1){
                throw new Exception("人员查询结果不唯一");
            }
            UmsUserInfo umsUserInfo = umsUserInfos.get(0);

            //查找各种信息
            Integer courtNo = null;
            if(StringUtils.hasText(updateUser.getCourt())){
                courtNo = getCourtNoByNameLike(updateUser.getCourt());
            }
            Integer department = null;
            if(StringUtils.hasText(updateUser.getDepartment())){
                department = getDepartment(courtNo,updateUser.getDepartment());
            }
            Integer admin = null;
            if(StringUtils.hasText(updateUser.getAdmin())){
                admin = getCodeByType(16,updateUser.getAdmin());
            }
            Integer law = null;
            if(StringUtils.hasText(updateUser.getLaw())){
                law = getCodeByType(15,updateUser.getLaw());
            }

            Integer courtNo2 = null;
            if(StringUtils.hasText(updateUser.getCourt2())){
                courtNo2 = getCourtNoByNameLike(updateUser.getCourt2());
            }
            Integer department2 = null;
            if(StringUtils.hasText(updateUser.getDepartment2())){
                department2 = getDepartment(courtNo2,updateUser.getDepartment2());
            }
            Integer admin2 = null;
            if(StringUtils.hasText(updateUser.getAdmin2())){
                admin2 = getCodeByType(16,updateUser.getAdmin2());
            }
            Integer law2 = null;
            if(StringUtils.hasText(updateUser.getLaw2())){
                law2 = getCodeByType(15,updateUser.getLaw2());
            }

            Map<String, Object> map = changeUserDao.setUser(umsUserInfo.getId(), courtNo, department, admin, law, courtNo2, department2, admin2, law2);
            boolean success = (boolean) map.get("success");
            re.put("success",success);
            if(!success){
                re.put("msg",map.get("msg"));
            }


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            re.put("msg",e.getMessage());

        }

        return "json";

    }




    public Integer getCourtNoByNameLike(String courtName) throws Exception {

        UmsCourtFullCriteria example = new UmsCourtFullCriteria();
        example.createCriteria().andCourtStdNameLike("%"+ courtName +"%");


        List<UmsCourtFull> umsCourtFulls = courtMapper.selectByExample(example);
        if(umsCourtFulls.size() == 0){
            throw new Exception("法院查询结果为空");
        }

        if(umsCourtFulls.size() > 1){
            throw new Exception("法院查询结果不唯一");
        }

        return umsCourtFulls.get(0).getCourtNo();
    }

    public Integer getDepartment(Integer courtNo,String depart) throws Exception {

        UmsDepartmentCriteria criteria = new UmsDepartmentCriteria();
        criteria.createCriteria().andCourtNoEqualTo(courtNo).andDeptNameLike("%"+ depart +"%");
        List<UmsDepartment> umsDepartments = umsDepartmentService.selectByExample(criteria);
        if(umsDepartments.size() == 0){
            throw new Exception("部门查询结果为空");
        }

        if(umsDepartments.size() > 1){
            throw new Exception("部门查询结果不唯一");
        }

        return umsDepartments.get(0).getDeptNo();

    }

    public Integer getCodeByType(Integer typeId,String codeName) throws Exception {

        UmsCodeCriteria example = new UmsCodeCriteria();
        example.createCriteria().andTypeIdEqualTo(typeId).andCodeNameEqualTo(codeName);
//                .andCodeNameLike("%"+ codeName +"%");
        List<UmsCode> umsCodes = umsCodeMapper.selectByExample(example);
        if(umsCodes.size() == 0){
            throw new Exception("typeID :" + typeId +"  查询结果为空");
        }

        if(umsCodes.size() > 1){
            throw new Exception("typeId : "+ typeId + "查询结果不唯一");
        }

        return Integer.parseInt(umsCodes.get(0).getId());

    }

    @Action("/run")
    public void run() {

        List<UpdateUser> users = new ArrayList<>();
        //try (FileInputStream fstream = new FileInputStream("D:\\Program\\文档\\NWT\\杨建奎\\新建 Microsoft Excel 工作表.xlsx")) {
        //    Workbook workbook = new Workbook(fstream);
        //
        //    Worksheet worksheet = workbook.getWorksheets().get("Sheet1");
        //    Cells cells = worksheet.getCells();
        //    RowCollection rows = cells.getRows();
        //    String deptname = "";
        //    for (Row row : (Iterable<Row>) rows) {
        //        String row3text = row.get(3).getStringValue();
        //        String row4text = row.get(4).getStringValue();
        //        String row5text = row.get(5).getStringValue();
        //        String row6text = row.get(6).getStringValue();
        //        UpdateUser user = new UpdateUser();
        //        if (row3text.contains("初步")) {
        //            row3text = row3text.split("初步")[0];
        //            deptname = row3text;
        //        }
        //        if (row4text.equalsIgnoreCase("姓名") || StringUtils.isEmpty(row4text)) {
        //            continue;
        //        }
        //        user.setName(row4text);
        //        String endStr = "";
        //        if (row6text.contains("县法院")) {
        //            String[] s = row6text.split("县法院");
        //            user.setOldCourt(s[0]);
        //            endStr = s[1];
        //        } else if (row6text.contains("区法院")) {
        //            String[] s = row6text.split("区法院");
        //            user.setOldCourt(s[0]);
        //            endStr = s[1];
        //        } else if (row6text.contains("中法院")) {
        //            String[] temp = row6text.split("法院");
        //            String temp1 = temp[0].replace("市", "");
        //            temp1 = "第" + temp1;
        //            user.setOldCourt(temp1);
        //            endStr = temp[1];
        //        }
        //        String[] s1 = new String[]{"局", "庭", "室", "办", "处", "队"};
        //        for (String s : s1) {
        //            if (endStr.contains(s)) {
        //                user.setOldDept(endStr.substring(0, endStr.indexOf(s) + 1));
        //                break;
        //            }
        //        }
        //        user.setCourt("自由贸易");
        //        user.setCourt2("两江");
        //        user.setDepartment(deptname);
        //        user.setDepartment2(deptname);
        //        users.add(user);
        //    }
        //
        //    for (UpdateUser user : users) {
        //        this.updateUser = user;
        //        String oldDept = updateUser.getOldDept();
        //        updateUser.setOldDept(null);
        //        try {
        //            changeUser();
        //            if (((Map) data).get("success").equals(true)) {
        //                System.out.println(user.getName() + " -- success");
        //            } else {
        //                System.err.println(user.getName() + " -- error -- " + ((Map) data).get("msg"));
        //            }
        //        } catch (Exception e) {
        //            e.printStackTrace();
        //        }
        //    }
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}

        try {
            UpdateUser user1 = new UpdateUser();
            user1.setName("裘晓音");
            user1.setDepartment("院领导");
            user1.setDepartment2("院领导");
            users.add(user1);

            UpdateUser user2 = new UpdateUser();
            user2.setName("贾科");
            user2.setDepartment("院领导");
            user2.setDepartment2("院领导");
            users.add(user2);

            UpdateUser user3 = new UpdateUser();
            user3.setName("曹柯");
            user3.setDepartment("院领导");
            user3.setDepartment2("院领导");
            users.add(user3);

            UpdateUser user4 = new UpdateUser();
            user4.setName("张小波");
            user4.setDepartment("院领导");
            user4.setDepartment2("院领导");
            users.add(user4);

            UpdateUser user5 = new UpdateUser();
            user5.setName("焦健");
            user5.setDepartment("政治处");
            user5.setDepartment2("政治处");
            users.add(user5);

            UpdateUser user6 = new UpdateUser();
            user6.setName("李涛");
            user6.setOldCourt("第一中");
            user6.setDepartment("执行局");
            user6.setDepartment2("执行局");
            users.add(user6);

            UpdateUser user7 = new UpdateUser();
            user7.setName("邓道军");
            user7.setDepartment("纪检");
            user7.setDepartment2("纪检");
            users.add(user7);

            UpdateUser user8 = new UpdateUser();
            user8.setName("杨丽霞");
            user8.setDepartment("审判委员会");
            user8.setDepartment2("审判委员会");
            users.add(user8);

            for (UpdateUser user : users) {
                user.setCourt("自由贸易");
                user.setCourt2("两江");
                this.updateUser = user;
                String oldDept = updateUser.getOldDept();
                updateUser.setOldDept(null);
                try {
                    changeUser();
                    if (((Map) data).get("success").equals(true)) {
                        System.out.println(user.getName() + " -- success");
                    } else {
                        System.err.println(user.getName() + " -- error -- " + ((Map) data).get("msg"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
