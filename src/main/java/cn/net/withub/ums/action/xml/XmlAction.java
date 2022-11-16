package cn.net.withub.ums.action.xml;

import cn.net.withub.ums.action.institution.UmsPartyOrganizationAction;
import cn.net.withub.ums.action.userinfo.UserInfoAttachedTableAction;
import cn.net.withub.ums.common.UmsConstant;
import cn.net.withub.ums.dao.UmsEducationInfoMapper;
import cn.net.withub.ums.dao.UmsPartyOrganizationMapper;
import cn.net.withub.ums.entity.*;
import cn.net.withub.ums.entity.factory.EntityFactory;
import cn.net.withub.ums.service.*;
import cn.net.withub.ums.util.ReflectionTools;
import cn.net.withub.ums.util.SessionUtils;
import cn.net.withub.ums.util.StringTools;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.ibatis.session.RowBounds;
import org.apache.struts2.convention.annotation.*;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2016/1/15.
 */
@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/xmlgenerater")
@Results({
        @Result(name = "json", type = "json", params = {"root", "data"})
})
public class XmlAction {

    static String unitType = "3";
    static String url = "192.0.81.183";
    static String port = "21";
    static String userName = "update";
    static String password = "update";
    static String remotePath = "99-updateFiles";

    @Autowired
    public UmsPhotoService umsPhotoService;

    @Autowired
    public UmsUserInfoService umsUserInfoService;

    @Autowired
    public UmsFieldContrastService umsFieldContrastService;

    @Autowired
    private UmsAttachedTableService attachedTableService;

    @Autowired
    private UmsCourtFullService umsCourtFullService;

    @Autowired
    private UmsUploadCodeContrastService umsUploadCodeContrastService;

    @Autowired
    private UmsUploadDeptContrastService umsUploadDeptContrastService;

    @Autowired
    private UmsDepartmentService umsDepartmentService;

    @Autowired
    private UmsUploadRecordInfoService umsUploadRecordInfoService;

    @Autowired
    private XtptBmDepartementService xtptBmDepartementService;

    @Autowired
    ServletContext servletContext;

    @Autowired
    UserInfoAttachedTableAction attachedTableAction;

    @Autowired
    private EntityFactory entityFactory;

    @Autowired
    UmsPartyOrganizationMapper umsPartyOrganizationMapper;

    @Autowired
    UmsPartyOrganizationAction umsPartyOrganizationAction;

    @Autowired
    UmsEducationInfoMapper educationInfoMapper;
    private Object data;

    private boolean Gtype;

    private int bzcyDeptNo = 3000;
    private int fbzcyDeptNo = 3001;
    private int sortNoPrefix = 3040126;
    private int level0SortNoPrefix = 3040126;
    private int level1SortNoPrefix = 3040126;

    /**
     * 生成照片
     */
    @Action("generatePhoto")
    public void generatePhoto() {

        long startTime = System.currentTimeMillis();

        RowBounds rowBounds = new RowBounds();

        List<String> list = umsPhotoService.selectIsValidUser(rowBounds);


        String path = servletContext.getRealPath("") + "/ResourceFiles/rsgl/images/photos";

        for (String r : list) {
            UmsPhoto l = umsPhotoService.selectByIdWithNOAspect(r);


            String FileName = l.getUserId() + ".jpg";
            File targetFile = new File(path, FileName);

            if (!targetFile.exists()) {

                try {

                    if (!targetFile.getParentFile().exists()) {
                        targetFile.getParentFile().mkdirs();
                        targetFile.createNewFile();
                    }

                    FileOutputStream outer = new FileOutputStream(targetFile);

                    //要进行压缩图片
                    byte[] photo = l.getPhoto();
                    ByteArrayInputStream inputStream = new ByteArrayInputStream(photo);
                    BufferedImage bufferedImage = ImageIO.read(inputStream);

                    byte[] zipImageFile = ZipUtils.zipImageFile(0.4f, bufferedImage);
                    outer.write(zipImageFile);
                    inputStream.close();
                    outer.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        String zipPath = servletContext.getRealPath("") + "generater";
        String Zpath = servletContext.getRealPath("") + "/ResourceFiles/rsgl/images";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String format = sdf.format(new Date());

        File file = new File(zipPath);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.mkdirs();
        }

        String zipName = "QL_CQGY_" + format + "_ZP.zip";
        ZipUtils.zip(Zpath, zipPath + "/" + zipName);
        long endTime = System.currentTimeMillis();

        Duration duration = Duration.ofMillis(endTime - startTime);

        System.out.println("花费时间------" + duration.getSeconds());


    }


    /**
     * 生成xml
     */
    @Action("generateXml")
    public void generateXml() {

        long start = System.currentTimeMillis();

//        if(1== 1){
//            return;
//        }
        //查找所有的字段对应表
        List<String> s = umsFieldContrastService.selectTableName();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String format = sdf.format(new Date());

        //需要压缩的文件夹路径
        String path;
        if (!Gtype) {
            path = servletContext.getRealPath("") + "ResourceXml/QL_CQGY_" + format;
        } else {
            path = servletContext.getRealPath("") + "ResourceXml/ZL_CQGY_" + format;
        }
        //压缩到的文件夹
        String zipPath;


        if (!Gtype) {
            zipPath = servletContext.getRealPath("") + "generater/QL_CQGY_" + format;
        } else {
            zipPath = servletContext.getRealPath("") + "generater/ZL_CQGY_" + format;
        }
        File file = new File(zipPath);
        if (file.exists()) {
            deleteFile(file);
        }
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        file.mkdirs();
        File file_ = new File(path);
        if (file_.exists()) {
            deleteFile(file_);
        }
        if (!file_.getParentFile().exists()) {
            file_.getParentFile().mkdirs();
        }
        file_.mkdirs();
        //代码表修正
        //现在不需要了，全用的最高院的编码
        //List<String> codeId = umsUploadCodeContrastService.selectCodeType();

        //UmsUploadCodeContrastExample example1 = new UmsUploadCodeContrastExample();
//        Map<String, Map>  key : ums_code的type_id 为key value : 以原编码位key 以转换后的编码为value的map
//        Map<String, Map> map = new HashMap<>();
//        for (String s_ : codeId) {
//            try {
//
//                example1.clear();
//                example1.createCriteria().andSourceTypeIdEqualTo(Integer.parseInt(s_));
//                List<UmsUploadCodeContrast> code_list = umsUploadCodeContrastService.selectByExample(example1);
//
//                Map<String, Object> m = new HashMap<>();
//
//                for (UmsUploadCodeContrast r : code_list) {
//                    m.put(r.getSourceField().toString(), r.getTargetField().toString());
//                }
//                map.put(s_, m);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        //先生成B01表
        //用于查询的map 以 getCourtNo 为key  里面的map 以 deptNo 为key  转换后部门的B00 为value
        Map<String, Map<String, String>> map_query = new HashMap<>();
//        court_map 以ums_court_full 的court_no 为key 以 转换后法院的B00 为 value
        Map<String, Object> court_map = new HashMap<>();
        //用于快捷的查询人员所属部门 以 getCourtNo() + "_" + getDeptNo() 为key
        Map<String,UmsDepartment> umsDepartmentMap = new HashMap<>();

//        //判断 org_code 人民法庭 和 院领导(院领导目前写死 用000) 用
//        XtptBmDepartementExample xtptBmDepartementExample = new XtptBmDepartementExample();
//        xtptBmDepartementExample.createCriteria().andDeptNameLike("%人民法庭%");
//        List<XtptBmDepartement> xtptBmDepartements = xtptBmDepartementService.selectByExample(xtptBmDepartementExample);
//        //装 人民法庭的 org_code
//        List<String> orgCodeList = new ArrayList<>();
//        for (XtptBmDepartement xtptBmDepartement : xtptBmDepartements) {
//            if(StringUtils.hasLength(xtptBmDepartement.getOrgCode()) ){
//                orgCodeList.add(xtptBmDepartement.getOrgCode());
//            }
//        }
//
//
//        try {
//            //对应表的法院集合
//            List<UmsUploadDeptContrast> u_dept_list = new ArrayList<>();
//            //法院集合
//            List<UmsCourtFull> court_list = umsCourtFullService.selectByListAll();
//
//            UmsUploadDeptContrastExample example = new UmsUploadDeptContrastExample();
//
//            //高院
//            example.createCriteria().andZdyb0131EqualTo("7");
//            example.setOrderByClause(" PINDEX  asc");
//            List<UmsUploadDeptContrast> list_level_1 = umsUploadDeptContrastService.selectByExample(example);
//
//            //中院
//            example.clear();
//            example.createCriteria().andZdyb0131EqualTo("13");
//            List<UmsUploadDeptContrast> list_level_2 = umsUploadDeptContrastService.selectByExample(example);
//
//            //基层法院
//            example.clear();
//            example.createCriteria().andZdyb0131EqualTo("23");
//            List<UmsUploadDeptContrast> list_level_3 = umsUploadDeptContrastService.selectByExample(example);
//
//            //放入Map中好查找
//            Map<String, UmsCourtFull> map_dept = new HashMap<>();
//
//
//            for (UmsCourtFull courtFull : court_list) {
//                map_dept.put(courtFull.getCourtStdNo().toString(), courtFull);
//            }
//
//            //是一类的可以合并一起查询
//            List<UmsUploadDeptContrast> list_level = new ArrayList<>();
//            list_level.addAll(list_level_1);
//            list_level.addAll(list_level_2);
//            list_level.addAll(list_level_3);
//            UmsDepartmentCriteria dept_criteria = new UmsDepartmentCriteria();
//            // 2-1 新增 在高院下新增一层 (直属机构) 部门 这个是直属部门的id  目前仅放 法官进修学院
//            example.clear();
//            example.createCriteria().andB0101EqualTo("直属机构");
//            List<UmsUploadDeptContrast> zsjg_list = umsUploadDeptContrastService.selectByExample(example);
//            String zsjgId ;
//            if(zsjg_list != null && zsjg_list.size() > 0){
//                zsjgId = zsjg_list.get(0).getB00();
//            }else{
//                zsjgId = UUID.randomUUID().toString();
//            }
//            for (UmsUploadDeptContrast u : list_level) {
//                String i = u.getInfoCode().toString();
//                UmsCourtFull j = map_dept.get(i);
//                court_map.put(j.getCourtNo().toString(), u.getB00().toString());
//                if (j == null) {
//                    throw new Exception();
//                }
////                if (j.getCourtLevel() == null) {
////                    u.setB0127("");
////                } else {
////                    u.setB0127(j.getCourtLevel().toString());
////                }
//
//                u_dept_list.add(u);
//                dept_criteria.clear();
//                dept_criteria.createCriteria().andCourtCodeEqualTo(j.getCourtCode()).andIsValidEqualTo(1); //启用
//                //法院下面原有的部门
//                dept_criteria.setOrderByClause(" court_code,level,sort_no ");
//                List<UmsDepartment> l = umsDepartmentService.selectByExampleWithNoAspect(dept_criteria);
//                if (u.getZdyb0131().equals("23")) {
////                    23 是基院
//                    example.clear();
//                    example.createCriteria().andB0144bEqualTo(u.getB00());
//
//                } else {
//                    example.clear();
//                    example.createCriteria().andB0144bEqualTo(u.getB00()).andB0101EqualTo("内设机构");
//                    String id = umsUploadDeptContrastService.selectByExample(example).get(0).getB00();
//                    example.clear();
//                    example.createCriteria().andB0144bEqualTo(id);
//                }
//
//
//                //上传查询出来的结果
//                List<UmsUploadDeptContrast> con_list = umsUploadDeptContrastService.selectByExample(example);
//                //查找的map
//                Map<String, UmsUploadDeptContrast> map_dept_query = new HashMap<>();
//                //单位类型
//                String zdyb0131 = null;
//                if (u.getZdyb0131().equals("7")) {
//                    //高院内设机构
//                    zdyb0131 = "9";
//                } else if (u.getZdyb0131().equals("13")) {
//                    //中院内设机构
//                    zdyb0131 = "15";
//                } else if (u.getZdyb0131().equals("23")) {
//                    //基层法院内设机构
//                    zdyb0131 = "25";
//                }
//                String parentId = null;
//                for (UmsUploadDeptContrast l_ : con_list) {
//                    map_dept_query.put(l_.getB0101(), l_);
//                    if (parentId == null) {
//                        parentId = l_.getB0144b();
//                    }
//                }
//                Map<String, String> innerMap = new HashMap<>();
//                //先放第一级目录
//                int sort_1 = 0;
//                Map<Integer, Object> pindex_map = new HashMap<>(); //这个map 是用来放部门的排序
//
//                for (UmsDepartment d_l : l) {
//                    if (d_l.getLevel() != null && !d_l.getLevel().equals(1)) {
//                        continue;
//                    }
//
//                    umsDepartmentMap.put(d_l.getCourtNo() + "_" + d_l.getDeptNo() , d_l);
//                    sort_1++;
//                    String dept_name = d_l.getDeptName();
//                    UmsUploadDeptContrast add_dept = map_dept_query.get(dept_name);
//                    if (add_dept == null) {
//                        UmsUploadDeptContrast new_dept = new UmsUploadDeptContrast();
//                        String uuid = UUID.randomUUID().toString();
//                        new_dept.setB00(uuid);
//                        new_dept.setB0104(d_l.getDeptStName());
//                        new_dept.setB0101(d_l.getDeptName());
//                        new_dept.setZdyb0131(zdyb0131);
//                        new_dept.setB0144b(parentId);
//                        if (d_l.getOrgType() != null && d_l.getOrgType() == 1) {
//                            //审判部门
//                            new_dept.setB0174(1);
//                        } else {
//                            //非审判部门
//                            new_dept.setB0174(2);
//                        }
//                        //人民法庭
//                        if(StringUtils.hasText(d_l.getOrgCode()) && orgCodeList.contains(d_l.getOrgCode())){
//                            new_dept.setZdyxb0138(1);
//                        }else{
//                            new_dept.setZdyxb0138(2);
//                        }
//                        //院领导
//                        if(StringUtils.hasText(d_l.getOrgCode()) && "000".equals(d_l.getOrgCode()) ){
//                            new_dept.setZdyxb0139(1);
//                        }else{
//                            new_dept.setZdyxb0139(2);
//                        }
//                        //法官进修学院
//                        if(StringUtils.hasText(d_l.getDeptName()) && "法官进修学院".equals(d_l.getDeptName())){
//                            new_dept.setB0144b(zsjgId);
//                        }
//                        new_dept.setIsNew(true);
//                        String pindex = u.getPindex() != null ? u.getPindex() : "";
//                        String pindex_ = pindex + String.format("%02d", sort_1);
//                        new_dept.setPindex(pindex_);
//                        pindex_map.put(d_l.getDeptNo(), pindex_);
//                        try {
//                            umsUploadDeptContrastService.insert(new_dept);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
////                        new_dept.setB0127(d_l.getLevel().toString());
//                        u_dept_list.add(new_dept);
//                        innerMap.put(d_l.getDeptNo().toString(), uuid);
//                    } else {
//                        if (d_l.getOrgType() != null && d_l.getOrgType() == 1) {
//                            //审判部门
//                            add_dept.setB0174(1);
//                        } else {
//                            //非审判部门
//                            add_dept.setB0174(2);
//                        }
//                        //人民法庭
//                        if(StringUtils.hasText(d_l.getOrgCode()) && orgCodeList.contains(d_l.getOrgCode())){
//                            add_dept.setZdyxb0138(1);
//                        }else{
//                            add_dept.setZdyxb0138(2);
//                        }
//                        //院领导
//                        if(StringUtils.hasText(d_l.getOrgCode()) && "000".equals(d_l.getOrgCode()) ){
//                            add_dept.setZdyxb0139(1);
//                        }else{
//                            add_dept.setZdyxb0139(2);
//                        }
//                        //法官进修学院
//                        if(StringUtils.hasText(d_l.getDeptName()) && "法官进修学院".equals(d_l.getDeptName())){
//                            add_dept.setB0144b(zsjgId);
//                        }
////                        add_dept.setB0127(d_l.getLevel() != null ? d_l.getLevel().toString() : "");
////                        add_dept.setPindex(d_l.getSortNo() != null ? d_l.getSortNo().toString() : "");
//                        String pindex = u.getPindex() != null ? u.getPindex() : "";
//                        String pindex_ = pindex + String.format("%02d", sort_1);
//                        add_dept.setPindex(pindex_);
//                        pindex_map.put(d_l.getDeptNo(), pindex_);
//                        u_dept_list.add(add_dept);
//                        innerMap.put(d_l.getDeptNo().toString(), add_dept.getB00());
//                    }
//                }
//                //第二级
//                int sort_2 = 0;
//                for (UmsDepartment d_l : l) {
//                    if (d_l.getLevel() != null && !d_l.getLevel().equals(2)) {
//                        continue;
//                    }
//                    umsDepartmentMap.put(d_l.getCourtNo() + "_" + d_l.getDeptNo() , d_l);
//                    sort_2++;
//                    String dept_name = d_l.getDeptName();
//                    UmsUploadDeptContrast add_dept = map_dept_query.get(dept_name);
//                    if (add_dept == null) {
//                        UmsUploadDeptContrast new_dept = new UmsUploadDeptContrast();
//                        String uuid = UUID.randomUUID().toString();
//                        new_dept.setB00(uuid);
//                        new_dept.setB0104(d_l.getDeptStName());
//                        new_dept.setB0101(d_l.getDeptName());
//                        new_dept.setZdyb0131(zdyb0131);
//                        new_dept.setB0144b(parentId);
//                        if (d_l.getOrgType() != null && d_l.getOrgType() == 1) {
//                            //审判部门
//                            new_dept.setB0174(1);
//                        } else {
//                            //非审判部门
//                            new_dept.setB0174(2);
//                        }
//                        //人民法庭
//                        if(StringUtils.hasText(d_l.getOrgCode()) && orgCodeList.contains(d_l.getOrgCode())){
//                            new_dept.setZdyxb0138(1);
//                        }else{
//                            new_dept.setZdyxb0138(2);
//                        }
//                        //院领导
//                        if(StringUtils.hasText(d_l.getOrgCode()) && "000".equals(d_l.getOrgCode()) ){
//                            new_dept.setZdyxb0139(1);
//                        }else{
//                            new_dept.setZdyxb0139(2);
//                        }
//                        new_dept.setIsNew(true);
//                        String pindex = "";
//                        Integer Pid = 0;
//                        try {
//                            Pid = Integer.parseInt(d_l.getLevelCode().substring(0, 4));
//                        } catch (NumberFormatException e) {
//                            e.printStackTrace();
//                        }
//                        Object o = pindex_map.get(Pid);
//                        if (o != null) {
//                            pindex = (String) o;
//                        }
//                        String pindex_ = pindex + String.format("%02d", sort_2);
//                        new_dept.setPindex(pindex_);
//                        pindex_map.put(d_l.getSortNo(), pindex_);
//                        try {
//                            umsUploadDeptContrastService.insert(new_dept);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                        u_dept_list.add(new_dept);
//                        innerMap.put(d_l.getDeptNo().toString(), uuid);
//                    } else {
//                        if (d_l.getOrgType() != null && d_l.getOrgType() == 1) {
//                            //审判部门
//                            add_dept.setB0174(1);
//                        } else {
//                            //非审判部门
//                            add_dept.setB0174(2);
//                        }
//                        //人民法庭
//                        if(StringUtils.hasText(d_l.getOrgCode()) && orgCodeList.contains(d_l.getOrgCode())){
//                            add_dept.setZdyxb0138(1);
//                        }else{
//                            add_dept.setZdyxb0138(2);
//                        }
//                        //院领导
//                        if(StringUtils.hasText(d_l.getOrgCode()) && "000".equals(d_l.getOrgCode()) ){
//                            add_dept.setZdyxb0139(1);
//                        }else{
//                            add_dept.setZdyxb0139(2);
//                        }
//                        String pindex = "";
//                        Integer Pid = 0;
//                        try {
//                            Pid = Integer.parseInt(d_l.getLevelCode().substring(0, 4));
//                        } catch (NumberFormatException e) {
//                            e.printStackTrace();
//                        }
//                        Object o = pindex_map.get(Pid);
//                        if (o != null) {
//                            pindex = (String) o;
//                        }
//                        String pindex_ = pindex + String.format("%02d", sort_2);
//                        add_dept.setPindex(pindex_);
//                        pindex_map.put(d_l.getSortNo(), pindex_);
//                        u_dept_list.add(add_dept);
//                        innerMap.put(d_l.getDeptNo().toString(), add_dept.getB00());
//                    }
//                }
//
//                //第三级
//                int sort_3 = 0;
//                for (UmsDepartment d_l : l) {
//                    if (d_l.getLevel() != null && !d_l.getLevel().equals(3)) {
//                        continue;
//                    }
//                    umsDepartmentMap.put(d_l.getCourtNo() + "_" + d_l.getDeptNo() , d_l);
//                    sort_3++;
//                    String dept_name = d_l.getDeptName();
//                    UmsUploadDeptContrast add_dept = map_dept_query.get(dept_name);
//                    if (add_dept == null) {
//                        UmsUploadDeptContrast new_dept = new UmsUploadDeptContrast();
//                        String uuid = UUID.randomUUID().toString();
//                        new_dept.setB00(uuid);
//                        new_dept.setB0104(d_l.getDeptStName());
//                        new_dept.setB0101(d_l.getDeptName());
//                        new_dept.setZdyb0131(zdyb0131);
//                        new_dept.setB0144b(parentId);
//                        if (d_l.getOrgType() != null && d_l.getOrgType() == 1) {
//                            //审判部门
//                            new_dept.setB0174(1);
//                        } else {
//                            //非审判部门
//                            new_dept.setB0174(2);
//                        }
//                        //人民法庭
//                        if(StringUtils.hasText(d_l.getOrgCode()) && orgCodeList.contains(d_l.getOrgCode())){
//                            new_dept.setZdyxb0138(1);
//                        }else{
//                            new_dept.setZdyxb0138(2);
//                        }
//                        //院领导
//                        if(StringUtils.hasText(d_l.getOrgCode()) && "000".equals(d_l.getOrgCode()) ){
//                            new_dept.setZdyxb0139(1);
//                        }else{
//                            new_dept.setZdyxb0139(2);
//                        }
//                        new_dept.setIsNew(true);
//                        String pindex = "";
//                        Integer Pid = 0;
//                        try {
//                            Pid = Integer.parseInt(d_l.getLevelCode().substring(4, 8));
//                        } catch (NumberFormatException e) {
//                            e.printStackTrace();
//                        }
//                        Object o = pindex_map.get(Pid);
//                        if (o != null) {
//                            pindex = (String) o;
//                        }
//                        String pindex_ = pindex + String.format("%02d", sort_3);
//                        new_dept.setPindex(pindex_);
//                        pindex_map.put(d_l.getSortNo(), pindex_);
//                        try {
//                            umsUploadDeptContrastService.insert(new_dept);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                        u_dept_list.add(new_dept);
//                        innerMap.put(d_l.getDeptNo().toString(), uuid);
//                    } else {
//                        if (d_l.getOrgType() != null && d_l.getOrgType() == 1) {
//                            //审判部门
//                            add_dept.setB0174(1);
//                        } else {
//                            //非审判部门
//                            add_dept.setB0174(2);
//                        }
//                        //人民法庭
//                        if(StringUtils.hasText(d_l.getOrgCode()) && orgCodeList.contains(d_l.getOrgCode())){
//                            add_dept.setZdyxb0138(1);
//                        }else{
//                            add_dept.setZdyxb0138(2);
//                        }
//                        //院领导
//                        if(StringUtils.hasText(d_l.getOrgCode()) && "000".equals(d_l.getOrgCode()) ){
//                            add_dept.setZdyxb0139(1);
//                        }else{
//                            add_dept.setZdyxb0139(2);
//                        }
//                        String pindex = "";
//                        Integer Pid = 0;
//                        try {
//                            Pid = Integer.parseInt(d_l.getLevelCode().substring(4, 8));
//                        } catch (NumberFormatException e) {
//                            e.printStackTrace();
//                        }
//                        Object o = pindex_map.get(Pid);
//                        if (o != null) {
//                            pindex = (String) o;
//                        }
//                        String pindex_ = pindex + String.format("%02d", sort_3);
//                        add_dept.setPindex(pindex_);
//                        pindex_map.put(d_l.getSortNo(), pindex_);
//                        u_dept_list.add(add_dept);
//                        innerMap.put(d_l.getDeptNo().toString(), add_dept.getB00());
//                    }
//                }
//                map_query.put(j.getCourtNo().toString(), innerMap);
//            }
//
//            //最后记得把内设机构加进去
//            example.clear();
//            example.createCriteria().andB0101EqualTo("内设机构");
//            List<UmsUploadDeptContrast> de_list = umsUploadDeptContrastService.selectByExample(example);
//            u_dept_list.addAll(de_list);
//            u_dept_list.addAll(zsjg_list);
//            System.out.println(u_dept_list.size()+"----------");
//
//
//            //写xml
//            String path_g = path + "/B01";
//            Document doc = DocumentHelper.createDocument().addComment("");
//            Element dataset = doc.addElement("dataset");
//            //使用 addElement() 方法创建根元素 <root> 。 addElement() 用于向 XML 文档中增加元素。
//            Element root = dataset.addElement("table").addAttribute("name", "B01");
//
//            if (!Gtype) {
//
//                writeXmlForB01(root, u_dept_list);
//                String fileName = "B010.xml";
//                generateFile(path_g, fileName, doc);
//
//            }
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        try {

            List<UmsUploadDeptContrast> umsUploadDeptContrasts = generateB01(map_query, court_map, umsDepartmentMap);
            String path_g = path + "/B01";
            Document doc = DocumentHelper.createDocument().addComment("");
            Element dataset = doc.addElement("dataset");
            //使用 addElement() 方法创建根元素 <root> 。 addElement() 用于向 XML 文档中增加元素。
            Element root = dataset.addElement("table").addAttribute("name", "B01");

            if (!Gtype) {

                writeXmlForB01(root, umsUploadDeptContrasts);
                String fileName = "B010.xml";
                generateFile(path_g, fileName, doc);

            }



        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(map_query.size());
        System.out.println(court_map.size());
        System.out.println(umsDepartmentMap.size());

        List<UmsUserInfo> umsUserInfos;


        if (!Gtype) {

            //这是全量的信息
            UmsUserInfoCriteria criteria = new UmsUserInfoCriteria();
            criteria.createCriteria().andUserTypeEqualTo(1);
            umsUserInfos = umsUserInfoService.selectByExampleWithNoAspect(criteria);

        } else {

            //这是增量
            Date date = umsUploadRecordInfoService.selectLastUpdateTime();

            if (date == null) {
                int i = umsUploadRecordInfoService.UpdateTime();
            }
            //查找要更新的信息
            UmsUserInfoCriteria criteria = new UmsUserInfoCriteria();
            criteria.createCriteria().andUserTypeEqualTo(1).andUpdateTimeGreaterThanOrEqualTo(date);
            umsUserInfos = umsUserInfoService.selectByExampleWithNoAspect(criteria);

        }

        if (umsUserInfos == null || umsUserInfos.size() == 0) {
            return;
        }

        List<String> userId = umsUserInfos.stream().map(UmsUserInfo::getId).collect(Collectors.toList());

        //同时也要生成 ums_level_info 的信息
        Map<Object, List<Map<String, Object>>> levelC = null;
        try {
            List<Map<String, Object>> levelList = attachedTableService.selectActiveLevelInfo(null);
            levelC = levelList.stream().filter(map -> map.get("user_id") != null).collect(Collectors.groupingBy(map -> map.get("user_id")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //循环生成xml
        for (String l_s : s) {
            if (l_s.equals("ums_court_full")) {
                continue;
            }
//            if(!l_s.equals("ums_yxnqgb")){
//                continue;
//            }
            generateXml(l_s, path, map_query, court_map, userId, Gtype , umsDepartmentMap,levelC);

        }


        //压缩
        String ZipFileName;
        if (!Gtype) {

            ZipFileName = "QL_CQGY_" + format + ".zip";
        } else {
            ZipFileName = "ZL_CQGY_" + format + ".zip";

        }
        ZipUtils.zip(path, zipPath + "/" + ZipFileName);


        //最后要进行上传
//        boolean b = ftpUpload(url, port, userName, password, remotePath, zipPath, ZipFileName);
//        if(b){
//        //这是要更新 上传增量的时间
//        int updateTime = umsUploadRecordInfoService.UpdateTime();
//        }

        long end = System.currentTimeMillis();
        Duration duration = Duration.ofMillis(end - start);
        System.out.println("花费的时间为" + duration.getSeconds());

        System.out.println("out");

    }


    /*
    levelC ums_level_info 信息 以user_id 为 key
     */
    private void generateXml(String table, String path, Map<String, Map<String, String>> map_query, Map<String, Object> court_map, List<String> userIds, boolean gtype,Map<String,UmsDepartment> umsDepartmentMap,Map<Object, List<Map<String, Object>>> levelC) {

        UmsFieldContrastExample example = new UmsFieldContrastExample();
        UmsFieldContrastExample.Criteria criteria = example.createCriteria();
        criteria.andSourceTableEqualTo(table);
        example.setOrderByClause(" sort_no asc");

        List<UmsFieldContrast> list = null;
        try {

            int j = 0;

            int start = 0;

            int limit = 5000;

            //对应名称
            String f = getXmlName(table);
            if (f == null) {
                f = table;
            }
            String path_g = path + "/" + f;

            List<Map<String, Object>> l = new ArrayList<>();

            //业务要求
            if (!gtype) {
                if(table.equals("ums_entry_info")){
                    j = 1;
                }else{
                    Integer count = attachedTableService.countNoAspect(table);
                    if (count > 5000) {
                        j = 1;
                    }
                }
            } else {
                j = 1;
            }


            //字段对应
            list = umsFieldContrastService.selectByExample(example);
            if (list == null || list.size() == 0) {
                return;
            }


            if (!gtype) {



                Map<String, UmsUserInfo> collectUser = null;
                //ums_entry_info  进入信息 并没有这张表 都是程序生成的数据
//                if(table.equals("ums_entry_info")){
//
//                    //这是全量的信息
//                    UmsUserInfoCriteria criteria2 = new UmsUserInfoCriteria();
//                    criteria2.createCriteria().andUserTypeEqualTo(1);
//                    List<UmsUserInfo> umsUserInfos = umsUserInfoService.selectByExampleWithNoAspect(criteria2);
//
//                    Integer useSize = umsUserInfos.size();
//                    do {
//                        List<UmsUserInfo> collect = umsUserInfos.stream().skip(start).limit(limit).collect(Collectors.toList());
//
//                        List<Map<String, Object>> mapArrayList = new ArrayList<>();
//                        //为每一个在编的人员生成进入的信息
//                        for (UmsUserInfo umsUserInfo : collect) {
//                            Map<String,Object> innerMap = new HashMap<>();
//                            innerMap.put("id",UUID.randomUUID().toString());
//                            innerMap.put("jyrq", umsUserInfo.getEnterDate());
//                            innerMap.put("a00",umsUserInfo.getId());
//                            innerMap.put("n_dqxx","1");
//                            Integer isValid = umsUserInfo.getIsValid();
//                            //不是在编的如果有 调离日期 把调离日期加上
//                            if(isValid != null && isValid == 0 && umsUserInfo.getLeaveDate() != null ){
//                                innerMap.put("lzsj", umsUserInfo.getLeaveDate());
//                            }
//                            mapArrayList.add(innerMap);
//                        }
//
//
//                        //生成根节点
//                        Document doc = DocumentHelper.createDocument();
//                        Element dataset = doc.addElement("dataset");
//                        //使用 addElement() 方法创建根元素 <root> 。 addElement() 用于向 XML 文档中增加元素。
//                        Element root = dataset.addElement("table").addAttribute("name", f);
//
//                        writeXml(root, mapArrayList, list, table, map, map_query, court_map , umsDepartmentMap , null);
//
//                        String fileName = f + String.valueOf(j) + ".xml";
//
//                        generateFile(path_g, fileName, doc);
//
//                        j++;
//                        start += limit;
//                        useSize -= limit;
//
//                    }while (useSize > 0);
//
//                    return;
//                }

                //ums_level_info 自己生成的数据
//                if(table.equals("ums_level_info")){
//                    //查找人员信息
//
//                    List<Map<String, Object>> mapArrayList = new ArrayList<>();
//                    //这是全量的信息
//                    UmsUserInfoCriteria criteria2 = new UmsUserInfoCriteria();
//                    criteria2.createCriteria().andUserTypeEqualTo(1);
//                    List<UmsUserInfo> umsUserInfos = umsUserInfoService.selectByExampleWithNoAspect(criteria2);
//
//                    List<UmsUserInfo> collect = umsUserInfos.stream().filter(umsUserInfo -> umsUserInfo.getYefg() != null && umsUserInfo.getYefg() == 1).collect(Collectors.toList());
//
//                    collectUser = new HashMap<>();
//                    for (UmsUserInfo umsUserInfo : collect) {
//                        collectUser.put(umsUserInfo.getId(),umsUserInfo);
//                    }
//
//                    //为每个人生成 ums_level_info 的一条数据
//                    for (UmsUserInfo umsUserInfo : collect) {
//                        Map<String,Object> map1 = new HashMap<>();
//                        map1.put("id",UUID.randomUUID().toString());
//                        map1.put("user_id", umsUserInfo.getId());
//                        map1.put("sort_no" , 0);
//                        map1.put("court_no" , umsUserInfo.getCourtNo());
//                        map1.put("n_level_type" , 1);
//                        map1.put("n_level_name" , umsUserInfo.getLevel());
//                        map1.put("is_yefg" , umsUserInfo.getYefg());
//                        //置为当前信息
//                        map1.put("n_present_info" , 1);
//                        map1.put("userdefined" ,"true");
//                        mapArrayList.add(map1);
//                    }
//
//                    //生成根节点
//                    Document doc = DocumentHelper.createDocument();
//                    Element dataset = doc.addElement("dataset");
//                    //使用 addElement() 方法创建根元素 <root> 。 addElement() 用于向 XML 文档中增加元素。
//                    Element root = dataset.addElement("table").addAttribute("name", f);
//
//                    writeXml(root, mapArrayList, list, table, map, map_query, court_map , umsDepartmentMap , null);
//
//                    String fileName = f + String.valueOf(j) + ".xml";
//
//                    generateFile(path_g, fileName, doc);
//
//                    j++;
//
//                }


                do {

                    //转查ums_user_info_view 表
                    if(table.equals("ums_user_info")){
                        l = attachedTableService.selectAllView("ums_user_info_view", start, limit);
                    }else{
                        l = attachedTableService.selectAllView(table, start, limit);
                    }

                    if (l == null || l.size() == 0) {
                        break;
                    }

                    List<Map<String, Object>> mapArrayList = new ArrayList<>();
                    if (!table.equals("ums_user_info")) {

                        for (Map<String, Object> listInfo : l) {

                            Object user_id = listInfo.get("user_id");
                            if (user_id != null && userIds.contains(user_id)) {
                                mapArrayList.add(listInfo);
                            }else if(table.equals("ums_party_organization")){
                                mapArrayList.add(listInfo);
                            }

                        }
                    } else {
                        mapArrayList = l;
                    }


                    //生成根节点
                    Document doc = DocumentHelper.createDocument();
                    Element dataset = doc.addElement("dataset");
                    //使用 addElement() 方法创建根元素 <root> 。 addElement() 用于向 XML 文档中增加元素。
                    Element root = dataset.addElement("table").addAttribute("name", f);

                    writeXml(root, mapArrayList, list, table, map_query, court_map , umsDepartmentMap ,collectUser,levelC);

                    String fileName = f + String.valueOf(j) + ".xml";

                    generateFile(path_g, fileName, doc);
                    j++;
                    start += limit;

                } while (l != null && l.size() != 0);



            } else {

                List<Map<String, Object>> mapArrayList = new ArrayList<>();
                do {

                    //转查ums_user_info_view 表
                    if(table.equals("ums_user_info")){
                        l = attachedTableService.selectAllView("ums_user_info_view", start, limit);
                    }else{
                        l = attachedTableService.selectAllView(table, start, limit);
                    }
                    if (l == null || l.size() == 0) {
                        break;
                    }


                    if (!table.equals("ums_user_info")) {
                        for (Map<String, Object> listInfo : l) {
                            Object user_id = listInfo.get("user_id");
                            if (user_id != null && userIds.contains(user_id)) {
                                mapArrayList.add(listInfo);
                            }
                        }
                    } else {
                        for (Map<String, Object> listInfo : l) {
                            Object user_id = listInfo.get("id");
                            if (user_id != null && userIds.contains(user_id)) {
                                mapArrayList.add(listInfo);
                            }
                        }

                    }

                    start += limit;

                } while (l != null && l.size() != 0);

                //生成根节点
                Document doc = DocumentHelper.createDocument();
                Element dataset = doc.addElement("dataset");
                //使用 addElement() 方法创建根元素 <root> 。 addElement() 用于向 XML 文档中增加元素。
                Element root = dataset.addElement("table").addAttribute("name", f);

                writeXml(root, mapArrayList, list, table, map_query, court_map ,umsDepartmentMap ,null,levelC);

                String fileName = f + String.valueOf(j) + ".xml";

                generateFile(path_g, fileName, doc);


            }


            //循环去取数据
//            while (l != null && l.size() != 0) {
//                start += limit;
//                l = null;
//                l = attachedTableService.selectAll(table, start, limit);
//                //生成根节点
//                Document doc_ = DocumentHelper.createDocument();
//                //使用 addElement() 方法创建根元素 <root> 。 addElement() 用于向 XML 文档中增加元素。
//                Element root_ = doc_.addElement("root");
//
//                writeXml(root_, l, list, table);
//
//                String fileName_ = f + String.valueOf(j) + ".xml";
//
//                generateFile(path_g, fileName_, doc_);
//
//            }

        } catch (
                Exception e
                )

        {
            e.printStackTrace();
        }

    }

    private void generateFile(String path, String fileName, Document doc) throws IOException {

        File file = new File(path, fileName);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
        }

        OutputFormat outputFormat = OutputFormat.createPrettyPrint();
        outputFormat.setEncoding("UTF-8");
//        XMLWriter xw = null;
        //如果上面设置的xml编码类型为GBK，则应当用FileWriter来构建xml文件，否则会出现中文连码问题
//            PrintWriter pw = new PrintWriter(path);
//            xw = new XMLWriter(pw, outputFormat);

        //如果上面设置的xml编码类型为utf-8，则应当用FileOutputStream来构建xml文件，否则还是会出现乱码问题
        FileOutputStream fos = new FileOutputStream(file);
//        xw = new XMLWriter(fos, outputFormat);
        //<![CDATA[]]>的处理
//        xw.setEscapeText(false);

        StandaloneWriter writer = new StandaloneWriter(fos, outputFormat);
        writer.setEscapeText(false);
        writer.write(doc);
        writer.flush();
        writer.close();

//        xw.write(doc);
//        xw.flush();
//        xw.close();
        fos.close();

    }

    //法律职务来判断人员分类
    private List<String> lawIndex =  Arrays.asList("1","2","3","4","5","6","7","38");

    /**
     * @param root      要写入xml的根节点
     * @param l         这个是要写入的信息转换成List<Map>好进行读取
     * @param list      这个是要写入的信息项 相当于表头
     * @param tableName 对应原表的表名信息
     // * @param map       编码转换表 我们这边对应不上的编码要同map转换一次
     * @param map_query 方便查找部门的id
     * @param court_map key是法院标准编码 如2950 value放的是法院id
     */
    private void writeXml(Element root, List<Map<String, Object>> l, List<UmsFieldContrast> list, String tableName, Map<String, Map<String, String>> map_query, Map<String, Object> court_map,Map<String,UmsDepartment> umsDepartmentMap,Map<String, UmsUserInfo> collectUser,Map<Object, List<Map<String, Object>>> levelC) {

        try {

            //先生成字段列表
            for (UmsFieldContrast ums_l : list) {
                String filedType = ums_l.getTargetFieldType().toUpperCase();
                if (filedType.equals("STRING")) {
                    filedType = "VARCHAR2";
                }
                Element father = root.addElement("column")
                        .addAttribute("type", filedType);
                father.setText(ums_l.getTargetField());
            }

            for (Map<String, Object> m_r : l) {
                Element father = root.addElement("row");

                Class<TableTranslateUtil> utilClass = TableTranslateUtil.class;
                TableTranslateUtil util = utilClass.newInstance();
                Method[] methods = utilClass.getMethods();
                Map<String, Object> m = null;
//                for (Method method : methods) {
//                    if (method.getName().equals(underlineToCamel(tableName) + "Change")) {
//                        try {
//                            // 第一个Map 是 实体类转换为的参数map 第二个map 是编码转换表map
//                            m = (Map<String, Object>) method.invoke(util, m_r, map);
//
//                        } catch (IllegalAccessException e) {
//                            e.printStackTrace();
//                        } catch (IllegalArgumentException e) {
//                            e.printStackTrace();
//                        } catch (InvocationTargetException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
                if (m == null) {
                    m = m_r;
                }

                for (UmsFieldContrast ums_l : list) {
                    String targetField = ums_l.getTargetField();
                    String sourceField = ums_l.getSourceField();
                    Object o = m.get(sourceField);
                    String s = null;

                    if (o != null) {

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        if (o instanceof Date) {
                            s = sdf.format(o);
                        } else {
                            s = String.valueOf(o);
                        }

                    }

                    //图片路径处理
                    if (tableName.equals("ums_user_info") && sourceField.equals("")) {

                        String photoN = "/ResourceFiles/rsgl/images/photos/" + m.get("id").toString() + ".jpg";
                        String p = servletContext.getRealPath("") + photoN;
                        File file = new File(p);
                        if (file.exists()) {
                            s = photoN;
                        }
                    }

                    //人员的处理
                    if (tableName.equals("ums_user_info") ) {
                        //任职状态处理
                        if(sourceField.equals("ZDYXA0109")){

                            if (m_r.get("is_valid") != null && m_r.get("is_valid").toString().equals("1") && StringUtils.isEmpty(m_r.get("leave_reason"))) {
//                            if (m_r.get("is_valid") != null && m_r.get("is_valid").toString().equals("1")) {
                                s = "1";
                            } else {
                                if (m_r.get("leave_reason") != null) {
                                    if (m_r.get("leave_reason").toString().equals("5")) {
                                        //离退休
                                        s = "2";
                                    } else if (m_r.get("leave_reason").toString().equals("6")) {
                                        //死亡
                                        s = "3";
                                    }
                                }
                            }
                            //默认设置为 4 离岗
                            if(!StringUtils.hasText(s)){
                                s = "4";
                            }
                        }

                        //等级
                        if(targetField.equals("N_DJ") || targetField.equals("D_DJRQ") ){

                            String user_id = m.getOrDefault("id","").toString();
                            if(StringUtils.hasText(user_id)){
                                List<Map<String, Object>> maps = levelC.get(user_id);
                                if(maps != null && maps.size() > 0){
                                    Map<String, Object> stringObjectMap = maps.get(0);
                                    if(targetField.equals("N_DJ")){
                                         String n_level_type = stringObjectMap.getOrDefault("n_level_type","").toString();

                                         switch (n_level_type){
                                             case "1":
                                                 s = stringObjectMap.getOrDefault("judge_level","").toString();
                                                 break;
                                             case "2":
                                                 s = stringObjectMap.getOrDefault("marshal_level","").toString();
                                                 break;
                                             case "3":
                                                 s = stringObjectMap.getOrDefault("helper_level","").toString();
                                                 break;
                                             case "4":
                                                 s = stringObjectMap.getOrDefault("clerk_level","").toString();
                                                 break;
                                             default:
                                                 s = stringObjectMap.getOrDefault("judge_level","").toString();
                                         }
                                    }
                                    if(targetField.equals("D_DJRQ")){
                                        Object d_start_date = stringObjectMap.get("d_start_date");
                                        s = d_start_date != null ? d_start_date.toString() : "";

                                    }

                                }
                            }

                        }

                        //人员分类
//                        if(sourceField.equals("N_RYFL")){
//                            //获取法律职务
//                            String law_position = m_r.getOrDefault("law_position", "").toString();
//                            if(StringUtils.hasText(law_position)){
////                                这批职务如果是员额法官就对应类型 为  “法官” ，否则辅助人员 “其他审判辅助人员”
//                                if(lawIndex.contains(law_position) ){
//                                    String yefg = m_r.getOrDefault("yefg", "").toString();
//                                    if(StringUtils.hasText(yefg) && yefg.equals("1")){
//                                        switch (law_position){
//                                            case "1":
//                                                s = "11";
//                                                break;
//                                            case "2":
//                                                s = "12";
//                                                break;
//                                            case "3":
//                                                s = "13";
//                                                break;
//                                            case "4":
//                                                s = "14";
//                                                break;
//                                            case "5":
//                                                s = "15";
//                                                break;
//                                            case "6":
//                                                s = "16";
//                                                break;
//                                            case "7":
//                                                s = "17";
//                                                break;
//                                            case "38":
//                                                s = "11";
//                                                break;
//                                        }
//
//                                    }else {
//                                        s = "29";
//                                    }
//                                }else {
//
////                                    21	RYFL	执行员	8
////                                    22	RYFL	法官助理	9
////                                    23	RYFL	书记员	10
////                                    24	RYFL	司法警察	11
////                                    25	RYFL	司法技术人员	12
////                                    29	RYFL	其他审判辅助人员	99
////                                    3	RYFL	司法行政人员	1001
//                                    switch (law_position){
//                                        case "8" :
//                                            s = "21";
//                                            break;
//                                        case "9" :
//                                            s = "22";
//                                            break;
//                                        case "10" :
//                                            s = "23";
//                                            break;
//                                        case "11" :
//                                            s = "24";
//                                            break;
//                                        case "12" :
//                                            s = "25";
//                                            break;
//                                        case "99" :
//                                            s = "29";
//                                            break;
//                                        case "1001" :
//                                            s = "3";
//                                            break;
//                                    }
//
//                                }
//
//                            }
//
//                            //没有默认为 其他审判辅助人员
//                            if(!StringUtils.hasText(s)){
//                                s = "29";
//                            }
//
//                        }
//                        //业务类别
//                        if(sourceField.equals("N_YWLB")){
//
//                            String i = m_r.get("court_no").toString();
//                            String j = m_r.get("department").toString();
//                            UmsDepartment department = umsDepartmentMap.get(i + "_" + j);
//                            if (StringUtils.hasText(i) && StringUtils.hasText(j) && department != null) {
//                                String deptName = department.getDeptName();
//                                s  = ywlb(deptName);
//                            }
//
//                            if(!StringUtils.hasText(s)){
//                                s = "255";
//                            }
//                        }

                    }

////                    先把班子成员处理下，把班子成员的部门改为虚拟班子部门
//                    if (tableName.equals("ums_user_info") && sourceField.equals("department")) {
//                        if (m_r.getOrDefault("dept_org_code", "").toString().equals("000")) {
//                            String bzcy = m_r.getOrDefault("bzcy", "0").toString();
//                            if (bzcy.equals("1")) {
//                                m_r.put("department", bzcyDeptNo);
//                            } else if (bzcy.equals("2")) {
//                                m_r.put("department", fbzcyDeptNo);
//                            }
//                        }
//                    }

                    //人员对应的部门id
                    if (tableName.equals("ums_user_info") && (sourceField.equals("department") || sourceField.equals("unicode"))
                            && m_r.get("department") != null && m_r.get("court_no") != null
                            ) {
                        String i = m_r.get("court_no").toString();
                        String j = m_r.get("department").toString();
                        if (map_query.get(i) != null && map_query.get(i).get(j) != null) {
                            s = map_query.get(i).get(j).toString();
                        } else {
                            s = "";
                        }
                    }

                    //人员对应的法院id
                    if (tableName.equals("ums_user_info") && sourceField.equals("court_no")) {
                        String i = m_r.get("court_no").toString();
                        if (map_query.get(i) != null) {
                            s = court_map.get(i).toString();
                        } else {
                            s = "";
                        }
                    }

                    if (tableName.equals("ums_court_full") && sourceField.equals("")) {
                        s = unitType; //单位类型 都是地方行政类型 大概吧
                    }

                    //数据中出现<> 会导致xml读取错误  如 : <2015>  要变成 (2015)

                    if (StringUtils.countOccurrencesOf(s, "<") > 0 && StringUtils.countOccurrencesOf(s, ">") > 0) {
                        String s1 = StringUtils.replace(s, "<", "(");
                        String s2 = StringUtils.replace(s1, ">", ")");
                        s = s2;
                    }

                    //A32工资信息表，职务工资档次 我们这边是文本 不能转换成编码 现在写死为 99 未定档次
                    if (tableName.equals("ums_wage_info") && sourceField.equals("c_pos_wage_grade")) {
                        s = "99";
                    }


                    //这个问题是 法官等级信息的 变动原因会出现'99'的错误数据
                    if (tableName.equals("ums_level_info") && sourceField.equals("n_alt_reason")
                            && StringUtils.hasText(s) && s.trim().equals("99")) {
                        s = "";
                    }
                    //等级信息 员额法官的处理 ( 1-31  数据库里面的数据关于 员额法官 全部置为否 )
//                    if (tableName.equals("ums_level_info") && sourceField.equals("is_yefg")) {
//                        Object defined = m.get("userdefined");
//                        if(defined != null && defined.toString().equals("true")){
//                            //这是自己生成的数据
//                            s = "1" ;
//                        }else{
//                            s = "2";
//                        }
//                    }
                    //等级信息 员额法官的处理 ( 1-31  数据库里面的数据关于 是否是当前信息 全部置为否 )
//                    if (tableName.equals("ums_level_info") && sourceField.equals("n_present_info")) {
//                        Object defined = m.get("userdefined");
//                        String user_id = m.getOrDefault("user_id", "").toString();
//                        if(defined != null && defined.toString().equals("true")){
//                            //这是自己生成的数据
//                            s = "1" ;
//                        }else{
//                            if(collectUser != null && StringUtils.hasText(user_id)){
//                                UmsUserInfo umsUserInfo = collectUser.get(user_id);
//                                if(umsUserInfo != null){
//                                    s = "2";
//                                }
//                            }
//                        }
//                        if(!StringUtils.hasText(s)){
//                            s = "2";
//                        }
//                    }

                    Element node = father.addElement("value");
//                            .addAttribute("fieldType", ums_l.getTargetFieldType());
                    if (StringUtils.hasText(s)) {

                        node.setText(s.replace("&", "-"));

                    }


                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void writeXmlForB01(Element root, List<UmsUploadDeptContrast> l) {

        try {

            Field[] fields = UmsUploadDeptContrast.class.getDeclaredFields();
            List<String> keyList = new ArrayList<>();

            for (Field field : fields) {
                try {
                    if (Modifier.isStatic(field.getModifiers())) {
                        continue;
                    }

                    if (field.getName().equals("infoCode") || field.getName().equals("isNew") || field.getName().equals("pindex1") || field.getName().equals("isUpload") || field.getName().equals("b0127")) {
                        continue;
                    }

                    Element father = root.addElement("column");
                    if (field.getName().equals("pindex")) {
                        father.addAttribute("type", "NUMBER");
                    } else {
                        father.addAttribute("type", "VARCHAR2");
                    }
                    father.setText(field.getName().toUpperCase());
                    keyList.add(field.getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            for (UmsUploadDeptContrast m_r : l) {


                Element father = root.addElement("row");

                Map<String, Object> m = entityToMap(m_r);

                for (String s : keyList) {


                    Element node = father.addElement("value");
                    String s_ = "";
                    if (m.get(s) != null) {
                        s_ = m.get(s).toString();
                        if (StringUtils.hasText(s_)) {
                            node.setText(s_);
                        }

                    }

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //增量信息的更新
    @Action("updateIn")
    public void updateIncrementalInfo() {
        //
        System.out.println("in");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String format = sdf.format(new Date());
        //需要压缩的文件夹路径
        String path = servletContext.getRealPath("") + "ResourceXml/ZL_CQGY_" + format;

        //压缩到的文件夹
        String zipPath = servletContext.getRealPath("") + "generater/ZL_CQGY_" + format;
        File file = new File(zipPath);
        if (file.exists()) {
            deleteFile(file);
        }
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        file.mkdirs();
        File file_ = new File(path);
        if (file_.exists()) {
            deleteFile(file_);
        }
        if (!file_.getParentFile().exists()) {
            file_.getParentFile().mkdirs();
        }
        file_.mkdirs();
        Date date = umsUploadRecordInfoService.selectLastUpdateTime();

        if (date == null) {
            return;
        }
        //查找要更新的信息
        UmsUserInfoCriteria criteria = new UmsUserInfoCriteria();
        criteria.createCriteria().andIsValidEqualTo(1).andUpdateTimeGreaterThanOrEqualTo(date);
        List<UmsUserInfo> umsUserInfos = umsUserInfoService.selectByExample(criteria);

        if (umsUserInfos == null || umsUserInfos.size() == 0) {
            return;
        }

        //查找所有的字段对应表
        List<String> s = umsFieldContrastService.selectTableName();

        //代码表修正
        // 不用再查，现在都是用的最高院编码
        //List<String> codeId = umsUploadCodeContrastService.selectCodeType();
        //
        //UmsUploadCodeContrastExample example1 = new UmsUploadCodeContrastExample();
        //Map<String, Map> map = new HashMap<>();
        //for (String s_ : codeId) {
        //    try {
        //        example1.clear();
        //        example1.createCriteria().andSourceTypeIdEqualTo(Integer.parseInt(s_));
        //        List<UmsUploadCodeContrast> code_list = umsUploadCodeContrastService.selectByExample(example1);
        //
        //        Map<String, Object> m = new HashMap<>();
        //
        //        for (UmsUploadCodeContrast r : code_list) {
        //            m.put(r.getSourceField().toString(), r.getTargetField().toString());
        //        }
        //        map.put(s_, m);
        //
        //    } catch (Exception e) {
        //        e.printStackTrace();
        //    }
        //}
        //部门相关 虽然很麻烦 但是还是要去查
        Map<String, Map<String, String>> map_query = new HashMap<>();
        Map<String, Object> court_map = new HashMap<>();
        Map<String,UmsDepartment> umsDepartmentMap = new HashMap<>();

        try {
            //对应表的法院集合
            List<UmsUploadDeptContrast> u_dept_list = new ArrayList<>();
            //法院集合
            List<UmsCourtFull> court_list = umsCourtFullService.selectByListAll();

            UmsUploadDeptContrastExample example = new UmsUploadDeptContrastExample();

            //高院
            example.createCriteria().andZdyb0131EqualTo("7");
            List<UmsUploadDeptContrast> list_level_1 = umsUploadDeptContrastService.selectByExample(example);

            //中院
            example.clear();
            example.createCriteria().andZdyb0131EqualTo("13");
            List<UmsUploadDeptContrast> list_level_2 = umsUploadDeptContrastService.selectByExample(example);

            //基层法院
            example.clear();
            example.createCriteria().andZdyb0131EqualTo("23");
            List<UmsUploadDeptContrast> list_level_3 = umsUploadDeptContrastService.selectByExample(example);

            //放入Map中好查找
            Map<String, UmsCourtFull> map_dept = new HashMap<>();


            for (UmsCourtFull courtFull : court_list) {
                map_dept.put(courtFull.getCourtStdNo().toString(), courtFull);
            }

            //是一类的可以合并一起查询
            List<UmsUploadDeptContrast> list_level = new ArrayList<>();
            list_level.addAll(list_level_1);
            list_level.addAll(list_level_2);
            list_level.addAll(list_level_3);
            UmsDepartmentCriteria dept_criteria = new UmsDepartmentCriteria();
            for (UmsUploadDeptContrast u : list_level) {
                String i = u.getInfoCode().toString();
                UmsCourtFull j = map_dept.get(i);
                court_map.put(j.getCourtNo().toString(), u.getB00().toString());
                if (j == null) {
                    throw new Exception();
                }
//                if (j.getCourtLevel() == null) {
//                    u.setB0127("");
//                } else {
//                    u.setB0127(j.getCourtLevel().toString());
//                }

                u_dept_list.add(u);
                dept_criteria.clear();
                dept_criteria.createCriteria().andCourtCodeEqualTo(j.getCourtCode()).andIsValidEqualTo(1); //启用

//                select * from  ums_department where   is_valid = 1  and court_code is not null
//                order by court_code,level,sort_no;
                //法院下面原有的部门
                dept_criteria.setOrderByClause(" court_code,level,sort_no ");
                List<UmsDepartment> l = umsDepartmentService.selectByExample(dept_criteria);
                if (u.getZdyb0131().equals("23")) {
                    example.clear();
                    example.createCriteria().andB0144bEqualTo(u.getB00());

                } else {
                    example.clear();
                    example.createCriteria().andB0144bEqualTo(u.getB00()).andB0101EqualTo("内设机构");
                    String id = umsUploadDeptContrastService.selectByExample(example).get(0).getB00();
                    example.clear();
                    example.createCriteria().andB0144bEqualTo(id);
                }

                //上传查询出来的结果
                List<UmsUploadDeptContrast> con_list = umsUploadDeptContrastService.selectByExample(example);
                //查找的map
                Map<String, UmsUploadDeptContrast> map_dept_query = new HashMap<>();
                //单位类型
                String zdyb0131 = null;
                if (u.getZdyb0131().equals("7")) {
                    //高院内设机构
                    zdyb0131 = "9";
                } else if (u.getZdyb0131().equals("13")) {
                    //中院内设机构
                    zdyb0131 = "15";
                } else if (u.getZdyb0131().equals("23")) {
                    //基层法院内设机构
                    zdyb0131 = "25";
                }
                String parentId = null;
                for (UmsUploadDeptContrast l_ : con_list) {
                    map_dept_query.put(l_.getB0101(), l_);
                    if (parentId == null) {
                        parentId = l_.getB0144b();
                    }
                }
                Map<String, String> innerMap = new HashMap<>();
                for (UmsDepartment d_l : l) {
                    String dept_name = d_l.getDeptName();
                    UmsUploadDeptContrast add_dept = map_dept_query.get(dept_name);
                    umsDepartmentMap.put(d_l.getCourtNo() + "_" + d_l.getDeptNo() , d_l);
                    if (add_dept == null) {
                        UmsUploadDeptContrast new_dept = new UmsUploadDeptContrast();
                        String uuid = UUID.randomUUID().toString();
                        new_dept.setB00(uuid);
                        new_dept.setB0104(d_l.getDeptStName());
                        new_dept.setB0101(d_l.getDeptName());
                        new_dept.setZdyb0131(zdyb0131);
                        new_dept.setB0144b(parentId);
                        if (d_l.getOrgType() != null && d_l.getOrgType() == 1) {
                            //审判部门
                            new_dept.setB0174(1);
                        } else {
                            //非审判部门
                            new_dept.setB0174(2);
                        }
                        new_dept.setIsNew(true);
                        try {
                            umsUploadDeptContrastService.insert(new_dept);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
//                        new_dept.setB0127(d_l.getLevel().toString());
                        u_dept_list.add(new_dept);
                        innerMap.put(d_l.getDeptNo().toString(), uuid);
                    } else {
                        if (d_l.getOrgType() != null && d_l.getOrgType() == 1) {
                            //审判部门
                            add_dept.setB0174(1);
                        } else {
                            //非审判部门
                            add_dept.setB0174(2);
                        }
//                        add_dept.setB0127(d_l.getLevel() != null ? d_l.getLevel().toString() : "");
                        add_dept.setPindex(d_l.getSortNo() != null ? d_l.getSortNo().toString() : "");
                        u_dept_list.add(add_dept);
                        innerMap.put(d_l.getDeptNo().toString(), add_dept.getB00());
                    }
                }
                map_query.put(j.getCourtNo().toString(), innerMap);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        //循环生成xml
        for (String table : s) {
            if (table.equals("ums_court_full")) {
                continue;
            }


            try {
                //表名
                String f = getXmlName(table);
                if (f == null) {
                    f = table;
                }
                String path_g = path + "/" + f;

                //字段对应
                UmsFieldContrastExample example = new UmsFieldContrastExample();
                UmsFieldContrastExample.Criteria fieldCriteria = example.createCriteria();
                fieldCriteria.andSourceTableEqualTo(table);
                example.setOrderByClause(" sort_no asc");
                List<UmsFieldContrast> list = umsFieldContrastService.selectByExample(example);
                if (list == null || list.size() == 0) {
                    return;
                }
                List<Map<String, Object>> l = new ArrayList<>();

                //信息
                for (UmsUserInfo userInfo : umsUserInfos) {

                    List<Map<String, Object>> singleInfo = attachedTableService.selectSingleInfo(table, userInfo.getId());

                    if (singleInfo != null) {
                        l.addAll(singleInfo);
                    }

                }

                //生成根节点
                Document doc = DocumentHelper.createDocument();
                Element dataset = doc.addElement("dataset");
                //使用 addElement() 方法创建根元素 <root> 。 addElement() 用于向 XML 文档中增加元素。
                Element root = dataset.addElement("table").addAttribute("name", f);
//
                writeXml(root, l, list, table, map_query, court_map ,umsDepartmentMap, null,null);
//
                String fileName = f + "0.xml";

                generateFile(path_g, fileName, doc);

            } catch (Exception e) {
                e.printStackTrace();
            }
            //压缩
            String ZipFileName = "ZL_CQGY_" + format + ".zip";
            ZipUtils.zip(path, zipPath + "/" + ZipFileName);

        }

    }


    //通过Ftp服务器进行上传
    private boolean ftpUpload(String url, String port, String username,
                              String password, String remotePath, String fileNamePath,
                              String fileName) {
        FTPClient ftpClient = new FTPClient();
        FileInputStream fis = null;
        boolean result = false;
        try {
            ftpClient.connect(url, Integer.parseInt(port));
            boolean loginResult = ftpClient.login(username, password);
            int returnCode = ftpClient.getReplyCode();
            if (loginResult && FTPReply.isPositiveCompletion(returnCode)) {// 如果登录成功
                ftpClient.makeDirectory(remotePath);
// 设置上传目录
                ftpClient.changeWorkingDirectory(remotePath);
                ftpClient.setBufferSize(1024);
                ftpClient.setControlEncoding("UTF-8");
                ftpClient.enterLocalPassiveMode();
                // 设置文件类型（二进制）
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.setDataTimeout(60 * 60 * 1000);
                ftpClient.setDefaultTimeout(60 * 60 * 1000);
                ftpClient.setConnectTimeout(60 * 60 * 1000);
                File file = new File(fileNamePath, fileName);
                fis = new FileInputStream(file);
                boolean b = ftpClient.storeFile(fileName, fis);


                result = b; // 上传成功
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("FTP客户端出错！", e);
        } finally {
// IOUtils.closeQuietly(fis);
            try {
                ftpClient.logout();
                ftpClient.disconnect();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("关闭FTP连接发生异常！", e);
            }
        }
        return result;
    }


    private Map<String, Object> entityToMap(Object o) {
        Map<String, Object> map = new HashMap<>();
        if (o == null) {
            return map;
        }

        Field[] fields = o.getClass().getDeclaredFields();

        for (Field field : fields) {
            try {
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }

                if (field.getName().equals("infoCode") || field.getName().equals("isNew") || field.getName().equals("pindex1") || field.getName().equals("isUpload")) {
                    continue;
                }
                field.setAccessible(true);

                map.put(field.getName(), field.get(o));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return map;
    }

    /**
     * 获取生成xml文件对应的文件名
     * @param tableName level_info的表需要配置为4种
     */
    public static String getXmlName(String tableName) {

        Map<String, String> map = new HashMap<>();
        map.put("ums_court_full", "B01");
        map.put("ums_user_info", "A01");
        map.put("ums_political_info", "T_RYSX_ZZMM");
        map.put("ums_administrative_job", "A02");
        map.put("ums_legal_job", "T_RYSX_FLZW");
        map.put("ums_rank_info", "A05");
        map.put("ums_parttime_position", "T_RYSX_JZZW");
        map.put("ums_level_info_fg", "T_RYSX_DJXX");
        map.put("ums_level_info_fj", "T_RYSX_FJDJ");//法警的
        map.put("ums_level_info_fgzl", "T_RYSX_FGZLDJ");//法官助理的 //todo 文档下来后替换编码表
        map.put("ums_level_info_sjy", "T_RYSX_SJYDJ");//书记员的 //todo 文档下来后替换编码表
        map.put("ums_education_info", "A08");
        map.put("ums_degree_info", "A09");
        map.put("ums_study_info", "T_RYSX_ZDXX");
        map.put("ums_training_info", "A11");
        map.put("ums_resume_info", "A17");
        map.put("ums_family_info", "A36");
        map.put("ums_assess_info", "A15");
        map.put("ums_punish_info", "A14Z3");
        map.put("ums_abroad_info", "A12");
        map.put("ums_foreign_lang", "A10");
        map.put("ums_exchange_info", "A49");
        map.put("ums_judicial_exam", "T_RYSX_SFKS");
        map.put("ums_pro_technical_position", "A06");
        map.put("ums_casualty_info", "T_RYSX_SWXX");
        map.put("ums_remark", "T_RYSX_BZXX");
        map.put("ums_audio_video", "A57");
        map.put("ums_wage_info", "A32");
        map.put("ums_reserve_cadre", "A23");
        map.put("ums_contact", "A37");
        map.put("ums_leadership", "T_RYSX_LDBZ");
        map.put("ums_civil_servant_level", "T_RYSX_GWY");
        map.put("ums_reward_info", "A14Z2");
//        map.put("ums_entry_info", "T_RYSX_RYBD");
        map.put("ums_change_info", "T_RYSX_RYBD");
        map.put("ums_party_organization", "KZB06");
        map.put("ums_yxnqgb", "T_RYSX_YXNQGB");
//
//        ums_change_info
        return map.get(tableName);
    }

    //用于生产 业务类别
    private String ywlb(String key){
        String returnStr = "";
        Map<String,String> ywlbMap = new HashMap<>();
//        1	YWLB	院领导
//        2	YWLB	刑事审判
//        3	YWLB	民事审判
//        4	YWLB	行政审判
//        5	YWLB	执行
//        6	YWLB	立案
//        7	YWLB	审监
//        8	YWLB	赔偿
//        9	YWLB	综合审判
//        10	YWLB	人民法庭
//        11	YWLB	审判辅助
//        12	YWLB	司法研究
//        13	YWLB	审判管理
//        14	YWLB	综合行政
//        15	YWLB	政工党务
//        16	YWLB	纪检监察
//        17	YWLB	装备财务
//        255	YWLB	其他行政后勤

//        院领导  院领导
//        民事、家事审判庭、小额、速裁 民事
//        刑事  刑事
//        行政审判  行政审判
//        执行 执行
//        立案  立案
//        审监  审监
//        赔偿  赔偿
//        人民法庭 人民法庭
//        研究室司法研究
//        审判管理审判管理
//        行装、司法行政 综合行政
//        未成年人、环境资源保护 综合审判
//        办公室  综合行政
//        政治部、机关党委 政工党务
//        纪检  纪检监察
//        财务处装备财务
//
//        其它对不上全部  其他行政后勤

        ywlbMap.put("院领导","1");
        ywlbMap.put("刑事","2");
        ywlbMap.put("民事","3");
        ywlbMap.put("家事审判庭","3");
        ywlbMap.put("小额","3");
        ywlbMap.put("速裁","3");
        ywlbMap.put("行政审判","4");
        ywlbMap.put("执行","5");
        ywlbMap.put("立案","6");
        ywlbMap.put("审监","7");
        ywlbMap.put("审判监督","7");
        ywlbMap.put("赔偿","8");
        ywlbMap.put("人民法庭","10");
        ywlbMap.put("研究室","12");
        ywlbMap.put("审判管理","13");
        ywlbMap.put("行装","14");
        ywlbMap.put("司法行政","14");
        ywlbMap.put("未成年人","9");
        ywlbMap.put("环境资源保护","9");
//        ywlbMap.put("办公室","14");
        ywlbMap.put("政治部","15");
        ywlbMap.put("机关党委","15");
        ywlbMap.put("纪检","16");
        ywlbMap.put("财务处","17");
        ywlbMap.put("其他行政后勤","255");




        if(StringUtils.hasText(key)){
            //全等 (办公室)
            if("办公室".equals(key)){
                returnStr = "14";
                return returnStr;
            }

            for (String s : ywlbMap.keySet()) {
                if(key.contains(s)){
                    returnStr = ywlbMap.get(s);
                    break;
                }
            }
        }


        return returnStr;
    }


    public List<UmsUploadDeptContrast> generateB01( Map<String, Map<String, String>> map_query , Map<String, Object> court_map  , Map<String,UmsDepartment> umsDepartmentMap ){
        //用于查询的map 以 getCourtNo 为key  里面的map 以 deptNo 为key  转换后部门的B00 为value
//        Map<String, Map<String, String>> map_query = new HashMap<>();
//        court_map 以ums_court_full 的court_no 为key 以 转换后法院的B00 为 value
//        Map<String, Object> court_map = new HashMap<>();
        //用于快捷的查询人员所属部门 以 getCourtNo() + "_" + getDeptNo() 为key
//        Map<String,UmsDepartment> umsDepartmentMap = new HashMap<>();

        //法院集合
        UmsCourtFullCriteria criteria = new UmsCourtFullCriteria();
        criteria.clear();
        criteria.createCriteria().andCourtGradationEqualTo(0);
        List<UmsCourtFull> level0 = umsCourtFullService.selectByExample(criteria);

        List<UmsUploadDeptContrast> re = new ArrayList<>();

        for (UmsCourtFull umsCourtFull : level0) {
//            umsCourtFull.setSortNo(0);
            //没得其他字符串的字段借用一下放排序号
            umsCourtFull.setDescription(sortNoPrefix+"");
            readDeptNode(umsCourtFull,map_query , court_map  , umsDepartmentMap , re , 0);
        }

        return re;


    }


    //level 0 生成 内设机构 和 直属机构  level 1 生成内设机构  level 1+ 不生成内设机构
    private void readDeptNode(UmsCourtFull umsCourtFull,Map<String, Map<String, String>> map_query , Map<String, Object> court_map  , Map<String,UmsDepartment> umsDepartmentMap,List<UmsUploadDeptContrast> re,Integer level){

        Map<String,String> innerMap = new HashMap<>();
        //添加本身到集合
        UmsUploadDeptContrast umsUploadDeptContrast = changToDeptContrastCourt(umsCourtFull);
        re.add(umsUploadDeptContrast);

        court_map.put(umsCourtFull.getCourtNo().toString(),umsCourtFull.getId());


        //查询下属法院
        UmsCourtFullCriteria criteria = new UmsCourtFullCriteria();
        criteria.clear();
        criteria.createCriteria().andParentIdEqualTo(umsCourtFull.getId()).andCourtNoIsNotNull();
        List<UmsCourtFull> childCourtList = umsCourtFullService.selectByExample(criteria);

        //法院下机构排序
        Integer courtLevelSort = 0;
        //添加内设机构
        String uuid = "";
        if(childCourtList != null && childCourtList.size() > 0){
            uuid = getUUID(umsCourtFull.getCourtCode());
            UmsUploadDeptContrast contrast = new UmsUploadDeptContrast();
            contrast.setB00(uuid);
            contrast.setB0101("内设机构");
            contrast.setB0104("内设机构");
            contrast.setZdyb0131(level == 0 ? "8" : level == 1 ? "14" : "24");
            contrast.setB0144b(umsCourtFull.getId());
            courtLevelSort++;
            contrast.setPindex(umsUploadDeptContrast.getPindex() + ( courtLevelSort > 9 ? courtLevelSort : "0" + courtLevelSort) );
            re.add(contrast);
        }


        //添加直属机构  目前只有法官进修学院 法官进修学院
        String uuid_zsjg = "";
        if(level == 0){
            uuid_zsjg = getUUID(umsCourtFull.getCourtCode() + "1");
            UmsUploadDeptContrast contrast_zsjg = new UmsUploadDeptContrast();
            contrast_zsjg.setB00(uuid_zsjg);
            contrast_zsjg.setB0101("直属机构");
            contrast_zsjg.setB0104("直属机构");
            contrast_zsjg.setZdyb0131("8");
            contrast_zsjg.setB0144b(umsCourtFull.getId());
//            contrast_zsjg.setPindex(umsUploadDeptContrast.getPindex() + "02");
            courtLevelSort++;
            contrast_zsjg.setPindex(umsUploadDeptContrast.getPindex() + ( courtLevelSort > 9 ? courtLevelSort : "0" + courtLevelSort) );
            re.add(contrast_zsjg);
        }

        //添加自身部门
        UmsDepartmentCriteria dept_criteria = new UmsDepartmentCriteria();
        dept_criteria.clear();
        dept_criteria.createCriteria().andCourtCodeEqualTo(umsCourtFull.getCourtCode()).andIsValidEqualTo(1).andLevelEqualTo(1);
        dept_criteria.setOrderByClause(" court_code,level,sort_no ");
        List<UmsDepartment> l = umsDepartmentService.selectByExampleWithNoAspect(dept_criteria);

        int index = 0;
        //父级  高院和中院下面是内设机构  基院是基院本身
        String baseParentSort = courtLevelSort == 0 ? umsUploadDeptContrast.getPindex()  : umsUploadDeptContrast.getPindex() + "01";
        for (UmsDepartment department : l) {
            index ++;
            department.setSortNo(index);
//            String sortNo = department.getSortNo() > 9 ? umsUploadDeptContrast.getPindex() + "" + department.getSortNo() : umsUploadDeptContrast.getPindex() + "0" + department.getSortNo();
//           if (level == 1) {
//                level1SortNoPrefix = Integer.parseInt(sortNo.substring(sortNo.length() - 4));
//            }

            umsDepartmentMap.put(department.getCourtNo() + "_" + department.getDeptNo() , department);
            innerMap.put(department.getDeptNo().toString(), department.getId());

            //法官进修学院
            String deptparent = null;
            if(StringUtils.hasText(department.getDeptName()) && "法官进修学院".equals(department.getDeptName())  && StringUtils.hasText(uuid_zsjg)){
                UmsUploadDeptContrast dept = changToDeptContrast(department, uuid_zsjg, umsUploadDeptContrast.getPindex() + "02");
                re.add(dept);
                deptparent = dept.getPindex();
            }else{
                UmsUploadDeptContrast dept = changToDeptContrast(department, StringUtils.hasText(uuid) ? uuid : umsCourtFull.getId(), baseParentSort);
                re.add(dept);
                deptparent = dept.getPindex();
            }

//            //院领导增加领导班子和非领导班子的子部门
//            if (department.getDeptName().equals("院领导")) {
//                UmsDepartment u1 = new UmsDepartment();
//                //u1.setId(department.getCourtNo() + "" + bzcyDeptNo);
//                u1.setId(getUUID(umsCourtFull.getCourtCode()+2));
//                u1.setDeptName("院领导（班子成员）");
//                u1.setDeptStName("院领导");
//                u1.setCourtLevel("9");
//                u1.setDeptType("2");
//                u1.setSortNo(1);
//                u1.setIsLeaders("1");
//                re.add(changToDeptContrast(u1, department.getId(), sortNo));
//
//                UmsDepartment u2 = new UmsDepartment();
//                //u2.setId(department.getCourtNo() + "" + fbzcyDeptNo);
//                u2.setId(getUUID(umsCourtFull.getCourtCode()+3));
//                u2.setDeptName("院领导（非班子成员）");
//                u2.setDeptStName("院领导");
//                u2.setCourtLevel("9");
//                u2.setDeptType("2");
//                u2.setSortNo(2);
//                re.add(changToDeptContrast(u2, department.getId(), sortNo));
//
//                innerMap.put(bzcyDeptNo + "", u1.getId());
//                innerMap.put(fbzcyDeptNo + "", u2.getId());
//            }
            //查找是否有子部门
            addChild(re,innerMap,umsDepartmentMap,umsCourtFull.getCourtCode(), department.getId(), deptparent);

        }

        map_query.put(umsCourtFull.getCourtNo().toString() , innerMap);

        if(childCourtList != null && childCourtList.size() > 0){
            level ++;
//            int courtIindex = 0;
            for (UmsCourtFull courtFull : childCourtList) {
//                courtIindex ++;
                courtLevelSort++;
                courtFull.setDescription(umsUploadDeptContrast.getPindex() + ( courtLevelSort > 9 ? courtLevelSort : "0" + courtLevelSort));
                readDeptNode(courtFull,map_query ,court_map  , umsDepartmentMap , re, level);

            }

        }

    }

    private void addChild(List<UmsUploadDeptContrast> re, Map<String, String> innerMap, Map<String, UmsDepartment> umsDepartmentMap, String courtCode, String pid, String pindex){

        if(!StringUtils.hasText(pid)){
            return;
        }

        UmsDepartmentCriteria dept_criteria = new UmsDepartmentCriteria();
        dept_criteria.createCriteria().andCourtCodeEqualTo(courtCode).andIsValidEqualTo(1).andParentIdEqualTo(pid); //启用
        dept_criteria.setOrderByClause(" court_code,level,sort_no ");

        List<UmsDepartment> l = umsDepartmentService.selectByExampleWithNoAspect(dept_criteria);

        if(l == null || l.size() == 0){
            return;
        }

        int index = 0;
        for (UmsDepartment department : l) {
            index ++;
            department.setSortNo(index);
            umsDepartmentMap.put(department.getCourtNo() + "_" + department.getDeptNo() , department);
            innerMap.put(department.getDeptNo().toString(), department.getId());
            UmsUploadDeptContrast umsUploadDeptContrast = changToDeptContrast(department, pid, pindex);
            re.add(umsUploadDeptContrast);

            addChild(re , innerMap, umsDepartmentMap, courtCode , department.getId(), umsUploadDeptContrast.getPindex());

        }


    }

    private UmsUploadDeptContrast changToDeptContrastCourt(UmsCourtFull courtFull){

        if(courtFull == null){
            return null;
        }
        UmsUploadDeptContrast c = new UmsUploadDeptContrast();
        c.setB00(courtFull.getId());
        c.setB0101(courtFull.getCourtStdName());
        c.setB0104(courtFull.getCourtShortName());
        c.setB0114(courtFull.getInstitutionCode());
        c.setZdyb0131(courtFull.getCourtGrade());
        c.setB0131(courtFull.getCourtType());
        c.setB0144b(courtFull.getParentId());
//        String sortNo = sortNoPrefix + "";
//        if (courtFull.getCourtGradation() == 1) {
//            //中院
//            level0SortNoPrefix ++;
//            sortNo = sortNoPrefix + (level0SortNoPrefix <= 10 ? "0" + level0SortNoPrefix : "" + level0SortNoPrefix);
//        } else if (courtFull.getCourtGradation() == 2) {
//            //基础法院
//            level1SortNoPrefix ++;
//            sortNo = sortNoPrefix + (level1SortNoPrefix <= 1000 ? "0" + level1SortNoPrefix : "" + level1SortNoPrefix);
//        }
        // 没得其他字符串的字段借用description字段一下放排序号
        c.setPindex(courtFull.getDescription()+ "");
        return c ;
    }

    private UmsUploadDeptContrast changToDeptContrast(UmsDepartment department, String pid, String pindex){

        if(department == null){
            return null;
        }
        UmsUploadDeptContrast c = new UmsUploadDeptContrast();
        c.setB00(department.getId());
        c.setB0101(department.getDeptName());
        c.setB0104(department.getDeptStName());
        c.setB0114(department.getInstitutionCode());
        c.setZdyb0131(department.getCourtLevel());
        if(StringUtils.hasText(pid)){
            c.setB0144b(pid);
        }else{
            c.setB0144b(department.getParentId());
        }
        c.setB0174(department.getDeptType() != null ? Integer.parseInt(department.getDeptType()) : 2 ); //默认非审判部门
        String sortNo = department.getSortNo() != null ? (department.getSortNo() > 9 ? pindex + department.getSortNo() : pindex + "0" + department.getSortNo()) : null;
        c.setPindex(sortNo);
        c.setZdyxb0138(StringUtils.hasText(department.getIsPeples()) ? Integer.parseInt(department.getIsPeples()) : 2); //默认非人民法庭
        c.setZdyxb0139(StringUtils.hasText(department.getIsLeaders()) ? Integer.parseInt(department.getIsLeaders()) : 2); //默认非领导部门

        return c ;
    }



    private String underlineToCamel(String s) {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        while (index < s.length()) {
            if (s.charAt(index) == '_') {
                sb.append(Character.toUpperCase(s.charAt(index + 1)));
                index += 2;
            } else {
                sb.append(Character.toLowerCase(s.charAt(index)));
                index++;
            }
        }
        return sb.toString();
    }

    /**
     * 通过递归调用删除一个文件夹及下面的所有文件
     *
     * @param file
     */
    public void deleteFile(File file) {
        if (file.isFile()) {//表示该文件不是文件夹
            file.delete();
        } else {
            //首先得到当前的路径
            String[] childFilePaths = file.list();
            for (String childFilePath : childFilePaths) {
                File childFile = new File(file.getAbsolutePath() + "\\" + childFilePath);
                deleteFile(childFile);
            }
            file.delete();
        }
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isGtype() {
        return Gtype;
    }

    public void setGtype(boolean gtype) {
        Gtype = gtype;
    }

    /**
     * 自动生成32位的UUid，对应数据库的主键id进行插入用。
     * todo 写死的，不要修改！！
     * @return
     */
    public String getUUID(String courtcode) {
        Map<String, String> map = new HashMap<>();
        map.put("M00", "d74f31c0-143e-4155-a136-123456789123");
        map.put("M001", "d93b5c4d-0720-11e8-8241-40f2e95c8684");
        map.put("M10", "b270a0d3-8c2b-42b1-975c-123456789123");
        map.put("M20", "b586360d-9b88-46c5-81a7-123456789123");
        map.put("M30", "c49dc55b-f070-4e56-9894-123456789123");
        map.put("M40", "99aa4015-3371-492a-b3e7-123456789123");
        map.put("M50", "034d548a-992a-4ecc-b411-123456789123");
        return map.getOrDefault(courtcode, "435329e4-66d8-41d9-86b4-3b78762a" + courtcode);
    }


    /*
   更新进入来源
    */
    @Action("update")
    public void updateEnterSource(){


        //查找要更新的信息
        //这是全量的信息
        UmsUserInfoCriteria criteria = new UmsUserInfoCriteria();
        criteria.createCriteria().andUserTypeEqualTo(1);
        List<UmsUserInfo> umsUserInfos = umsUserInfoService.selectByExampleWithNoAspect(criteria);


        Map<Integer,String> matchMap = new HashMap<Integer, String>(){{
            put(1,"26");
            put(2,"21");
            put(3,"111");
            put(4,"28");
            put(5,"27");
            put(6,"114");
            put(7,"21");
            put(8,"22");
            put(9,"26");
            put(10,"113");
            put(11,"26");
            put(12,"21");
            put(255,"26");

        }};

        String table = "ums_change_info";
        Integer limit = Integer.MAX_VALUE;
        List<Map<String, Object>> maps = attachedTableService.selectAllView(table, 0, limit);

        Map<Object, List<Map<String, Object>>> collect = maps.stream().filter(
                map -> map.get("user_id") != null && map.getOrDefault("n_present_info","").toString().equals("1"))
                .collect(Collectors.groupingBy(map -> map.get("user_id")));


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

//        UmsUserInfo umsUserInfo = umsUserInfos.get(22);
        for (UmsUserInfo umsUserInfo : umsUserInfos) {

            String id = umsUserInfo.getId();

        System.out.println("=================");
        System.out.println(id);

            List<Map<String, Object>> cl = collect.get(id);
            Map<String,String> saveP = new HashMap<>();
            Map<String, Object> tx = null ;
            String nid = "";
            if(cl != null && cl.size() > 0){

                tx = cl.get(0);
                nid = tx.getOrDefault("id", "").toString();


            }

            if(StringUtils.hasText(nid)){
                saveP.put("id",nid);
            }

            saveP.put("userId",id);
            Date enterDate = umsUserInfo.getEnterDate();
            if(enterDate != null){
                String s1 = sdf.format(enterDate);
                saveP.put("enterDate",s1);
            }
            Integer enterSource = umsUserInfo.getEnterSource();
            String s2 = null;
            if(enterSource != null){

                s2 = matchMap.get(enterSource);
                if(StringUtils.hasText(s2)){
                    saveP.put("enterSource",s2);
                }else{
                    saveP.put("enterSource",enterSource.toString());
                }
                saveP.put("nPresentInfo","1");

            }

//        Date leaveDate = umsUserInfo.getLeaveDate();
//
//        if(leaveDate != null){
//            String s1 = sdf.format(leaveDate);
//            saveP.put("leaveDate",s1);
//        }

//            attachedTableAction.setP(saveP);
//            attachedTableAction.setT();


            //========================进行更新操作===============================

            Object entity = entityFactory.createEntity("change_info", saveP);


            try {

                ReflectionTools.setField(entity, "userNo", umsUserInfo.getUserNo());
                ReflectionTools.setField(entity, "courtNo", umsUserInfo.getCourtNo());
                ReflectionTools.setField(entity, "courtCode", umsUserInfo.getCourtCode());

                int rest = 0;
                if (!StringUtils.hasText(nid)) {//有id就新增，否则就修改

                    String s = UUID.randomUUID().toString();

                    try {
                        ReflectionTools.setField(entity, "id", s);
                    } catch (Exception ex) {
                        Logger.getLogger(UserInfoAttachedTableAction.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    rest = attachedTableService.insert(entity);

                } else {
                    rest = attachedTableService.update(entity);
                }

                if(rest > 0 && StringUtils.hasText(s2)){

                    UmsUserInfo u = new UmsUserInfo();
                    u.setId(id);
                    u.setEnterSource(Integer.valueOf(s2));
                    umsUserInfoService.updateByPrimaryKeySelective(u);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

            //========================更新结束=======================================

        }


        System.out.println(" out ===");


    }

    @Action("update1")
    public void update1(){

        //先生成B01表
        //用于查询的map 以 getCourtNo 为key  里面的map 以 deptNo 为key  转换后部门的B00 为value
        Map<String, Map<String, String>> map_query = new HashMap<>();
//        court_map 以ums_court_full 的court_no 为key 以 转换后法院的B00 为 value
        Map<String, Object> court_map = new HashMap<>();
        //用于快捷的查询人员所属部门 以 getCourtNo() + "_" + getDeptNo() 为key
        Map<String,UmsDepartment> umsDepartmentMap = new HashMap<>();

        List<UmsUploadDeptContrast> umsUploadDeptContrasts = generateB01(map_query, court_map, umsDepartmentMap);

        UmsPartyOrganizationCriteria example = new UmsPartyOrganizationCriteria();
        List<UmsPartyOrganization> umsPartyOrganizations = umsPartyOrganizationMapper.selectByExample(example);

        List<String> partys = umsPartyOrganizations.stream().map(UmsPartyOrganization::getInstitutionId).collect(Collectors.toList());

        List<String> ok = new ArrayList<>();
        //法院
        UmsCourtFullCriteria criteria = new UmsCourtFullCriteria();
        criteria.clear();
        criteria.createCriteria();
        List<UmsCourtFull> umsCourtFulls = umsCourtFullService.selectByExample(criteria);

        try {
            for (UmsCourtFull umsCourtFull : umsCourtFulls) {
                Integer courtNo = umsCourtFull.getCourtNo();
                if(courtNo == null){
                    continue;
                }
                String b00 = court_map.getOrDefault(courtNo.toString(), "").toString();
                if(StringUtils.hasText(b00)  ) {
                    if (partys.contains(b00)) {
                        ok.add(b00);
                    } else{
                        //更新
                        UmsPartyOrganization information = new UmsPartyOrganization();
                        String o = "0";
                        information.setIsBuild(o);
                        information.setCourtNo(courtNo);
                        umsPartyOrganizationAction.setInformation(information);
                        umsPartyOrganizationAction.insert();

                    }
                }
            }

            //部门
            UmsDepartmentCriteria dept_criteria = new UmsDepartmentCriteria();
            dept_criteria.clear();
            dept_criteria.createCriteria().andIsValidEqualTo(1);
            dept_criteria.setOrderByClause(" court_code,level,sort_no ");
            List<UmsDepartment> umsDepartments = umsDepartmentService.selectByExampleWithNoAspect(dept_criteria);

            for (UmsDepartment umsDepartment : umsDepartments) {
                String courtCode = umsDepartment.getCourtCode();
                Integer courtNo = umsDepartment.getCourtNo();
                Integer deptNo = umsDepartment.getDeptNo();
                if(courtNo == null || deptNo == null){
                    continue;
                }
                Map<String, String> stringStringMap = map_query.get(courtNo.toString());
                if(stringStringMap != null){

                    String b00 = stringStringMap.getOrDefault(deptNo.toString(), "").toString();
                    if(StringUtils.hasText(b00)  ) {
                        if (partys.contains(b00)) {
                            ok.add(b00);
                        } else{
                            //更新
                            UmsPartyOrganization information = new UmsPartyOrganization();
                            String o = "0";
                            information.setIsBuild(o);
                            information.setCourtNo(courtNo);
                            information.setDeptNo(deptNo);
                            umsPartyOrganizationAction.setInformation(information);
                            umsPartyOrganizationAction.insert();
                        }
                    }

                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }


//        boolean flag = false;
//        for (UmsUploadDeptContrast umsUploadDeptContrast : umsUploadDeptContrasts) {
//
//            String b00 = umsUploadDeptContrast.getB00();
//            if(StringUtils.hasText(b00)  ) {
//                if (partys.contains(b00)) {
//                    ok.add(b00);
//                } else{
//                    //更新
//                    UmsPartyOrganization information = new UmsPartyOrganization();
//                    String o = "0";
//                    information.setIsBuild(o);
//                    information.setCourtNo();
//
//                    umsPartyOrganizationAction.insert();
//
//                }
//
//            }
//
//        }

        System.out.println(ok);




    }

    //修正学历逻辑
    @Action("update2")
    public void update2(){
        //查找要更新的信息
        //这是全量的信息
        UmsUserInfoCriteria criteria = new UmsUserInfoCriteria();
        criteria.createCriteria().andUserTypeEqualTo(1);
        List<UmsUserInfo> umsUserInfos = umsUserInfoService.selectByExampleWithNoAspect(criteria);


        try {
            String table = "ums_education_info";
        Integer limit = Integer.MAX_VALUE;
//            Integer limit = 10;
            List<Map<String, Object>> maps = attachedTableService.selectAllView(table, 0, limit);

            Map<Object, List<Map<String, Object>>> collect = maps.stream().filter(
                    map -> map.get("user_id") != null )
                    .collect(Collectors.groupingBy(map -> map.get("user_id")));

//            int i = 0;
            for (UmsUserInfo umsUserInfo : umsUserInfos) {

//                i++;
//                if(i > 10){
//                    break;
//                }
                String id = umsUserInfo.getId();
                List<Map<String, Object>> maps1 = collect.get(id);
                if(maps1 != null && maps1.size() > 0){

                    if(maps1.size() == 1){
                            Map<String, Object> stringObjectMap = maps1.get(0);
                            Object n_present_info = stringObjectMap.get("n_present_info");
                        if(n_present_info == null || !n_present_info.toString().equals("1")){
                            UmsEducationInfo umsEducationInfo = new UmsEducationInfo();
                            umsEducationInfo.setId(stringObjectMap.get("id").toString());
                            umsEducationInfo.setnPresentInfo(1);
                            educationInfoMapper.updateByPrimaryKeySelective(umsEducationInfo);
                        }
                    }else{


                        Map<Object, List<Map<String, Object>>> x2 = maps1.stream().collect(Collectors.groupingBy(map -> map.getOrDefault("n_education_background_report","")));
                        String educationBackgroundReport = umsUserInfo.getEducationBackgroundReport();
                        List<Map<String, Object>> maps2 = x2.get(educationBackgroundReport);
                        if(maps2 != null && maps2.size() > 0){
                            Map<String, Object> stringObjectMap = maps2.get(0);
                            Object n_present_info = stringObjectMap.get("n_present_info");
                            if(n_present_info == null || !n_present_info.toString().equals("1")){
                                UmsEducationInfo umsEducationInfo = new UmsEducationInfo();
                                umsEducationInfo.setId(stringObjectMap.get("id").toString());
                                umsEducationInfo.setnPresentInfo(1);
                                educationInfoMapper.updateByPrimaryKeySelective(umsEducationInfo);
                            }
                        }

                    }


//                    UmsEducationInfoMapper

//                    Map<String, Object> stringObjectMap1 = listx.get(0);
//                    Object n_present_info = stringObjectMap1.get("n_present_info");
//                    if(n_present_info != null && n_present_info.toString().equals("1")){
//                        System.out.println("ok");
//                    }
//
//                    System.out.println("ok2");
    //                for (Map<String, Object> stringObjectMap : listx) {
    //                    Object n_present_info = stringObjectMap.get("n_present_info");
    //                    if(n_present_info == null){
    //
    //                    }
    //
    //                }


                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("ok2");


    }

    @Action("test1")
    public void test2(){

        //先生成B01表
        //用于查询的map 以 getCourtNo 为key  里面的map 以 deptNo 为key  转换后部门的B00 为value
        Map<String, Map<String, String>> map_query = new HashMap<>();
//        court_map 以ums_court_full 的court_no 为key 以 转换后法院的B00 为 value
        Map<String, Object> court_map = new HashMap<>();
        //用于快捷的查询人员所属部门 以 getCourtNo() + "_" + getDeptNo() 为key
        Map<String,UmsDepartment> umsDepartmentMap = new HashMap<>();

        List<UmsUploadDeptContrast> umsUploadDeptContrasts = generateB01(map_query, court_map, umsDepartmentMap);

        System.out.println(umsUploadDeptContrasts.size());
        System.out.println("ok");

    }

}