package cn.net.withub.ums.action.sbrmpsy;

import cn.net.withub.ums.action.institution.UmsPartyOrganizationAction;
import cn.net.withub.ums.action.userinfo.UserInfoAttachedTableAction;
import cn.net.withub.ums.action.xml.StandaloneWriter;
import cn.net.withub.ums.action.xml.TableTranslateUtil;
import cn.net.withub.ums.action.xml.ZipUtils;
import cn.net.withub.ums.dao.UmsEducationInfoMapper;
import cn.net.withub.ums.dao.UmsPartyOrganizationMapper;
import cn.net.withub.ums.entity.*;
import cn.net.withub.ums.entity.factory.EntityFactory;
import cn.net.withub.ums.service.*;
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
import java.util.*;
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
public class temp {

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

    static String unitType = "3";
    static String url = "192.0.81.183";
    static String port = "21";
    static String userName = "update";
    static String password = "update";
    static String remotePath = "99-updateFiles";

    private Object data;
    private boolean Gtype;
    private int bzcyDeptNo = 3000;
    private int fbzcyDeptNo = 3001;
    private int sortNoPrefix = 3040126;
    private int level0SortNoPrefix = 3040126;
    private int level1SortNoPrefix = 3040126;

    // ????????????
    @Action("generatePhoto")
    public void generatePhoto() {
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
                    //?????????????????????
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
    }

    // ??????xml
    @Action("generateXml")
    public void generateXml() {

        List<String> s = umsFieldContrastService.selectTableName();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String format = sdf.format(new Date());

        //??????????????????????????????
        String path;
        if (!Gtype) {
            path = servletContext.getRealPath("") + "ResourceXml/QL_CQGY_" + format;
        } else {
            path = servletContext.getRealPath("") + "ResourceXml/ZL_CQGY_" + format;
        }
        //?????????????????????
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

        //?????????B01???
        //???????????????map ??? getCourtNo ???key  ?????????map ??? deptNo ???key  ??????????????????B00 ???value
        Map<String, Map<String, String>> map_query = new HashMap<>();
        // court_map ???ums_court_full ???court_no ???key ??? ??????????????????B00 ??? value
        Map<String, Object> court_map = new HashMap<>();
        //??????????????????????????????????????? ??? getCourtNo() + "_" + getDeptNo() ???key
        Map<String, UmsDepartment> umsDepartmentMap = new HashMap<>();

        try {
            List<UmsUploadDeptContrast> umsUploadDeptContrasts = generateB01(map_query, court_map, umsDepartmentMap);
            String path_g = path + "/B01";
            Document doc = DocumentHelper.createDocument().addComment("");
            Element dataset = doc.addElement("dataset");
            //?????? addElement() ????????????????????? <root> ??? addElement() ????????? XML ????????????????????????
            Element root = dataset.addElement("table").addAttribute("name", "B01");

            if (!Gtype) {
                writeXmlForB01(root, umsUploadDeptContrasts);
                String fileName = "B010.xml";
                generateFile(path_g, fileName, doc);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        List<UmsUserInfo> umsUserInfos;

        if (!Gtype) {

            //?????????????????????
            UmsUserInfoCriteria criteria = new UmsUserInfoCriteria();
            criteria.createCriteria().andUserTypeEqualTo(1);
            umsUserInfos = umsUserInfoService.selectByExampleWithNoAspect(criteria);

        } else {

            //????????????
            Date date = umsUploadRecordInfoService.selectLastUpdateTime();

            if (date == null) {
                int i = umsUploadRecordInfoService.UpdateTime();
            }
            //????????????????????????
            UmsUserInfoCriteria criteria = new UmsUserInfoCriteria();
            criteria.createCriteria().andUserTypeEqualTo(1).andUpdateTimeGreaterThanOrEqualTo(date);
            umsUserInfos = umsUserInfoService.selectByExampleWithNoAspect(criteria);

        }

        if (umsUserInfos == null || umsUserInfos.size() == 0) {
            return;
        }

        List<String> userId = umsUserInfos.stream().map(UmsUserInfo::getId).collect(Collectors.toList());

        //?????????????????? ums_level_info ?????????
        Map<Object, List<Map<String, Object>>> levelC = null;
        try {
            List<Map<String, Object>> levelList = attachedTableService.selectActiveLevelInfo(null);
            levelC = levelList.stream().filter(map -> map.get("user_id") != null).collect(Collectors.groupingBy(map -> map.get("user_id")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //????????????xml
        for (String l_s : s) {
            if (l_s.equals("ums_court_full")) {
                continue;
            }
            generateXml(l_s, path, map_query, court_map, userId, Gtype, umsDepartmentMap, levelC);
        }

        //??????
        String ZipFileName;
        if (!Gtype) {

            ZipFileName = "QL_CQGY_" + format + ".zip";
        } else {
            ZipFileName = "ZL_CQGY_" + format + ".zip";

        }
        ZipUtils.zip(path, zipPath + "/" + ZipFileName);
    }

    // levelC ums_level_info ?????? ???user_id ??? key
    private void generateXml(String table, String path, Map<String, Map<String, String>> map_query, Map<String, Object> court_map, List<String> userIds, boolean gtype, Map<String, UmsDepartment> umsDepartmentMap, Map<Object, List<Map<String, Object>>> levelC) {
        UmsFieldContrastExample example = new UmsFieldContrastExample();
        UmsFieldContrastExample.Criteria criteria = example.createCriteria();
        criteria.andSourceTableEqualTo(table);
        example.setOrderByClause(" sort_no asc");
        List<UmsFieldContrast> list;
        try {
            int j = 0;
            int start = 0;
            int limit = 5000;
            // ????????????????????????????????????????????????
            String f = getXmlName(table);
            if (f == null) {
                f = table;
            }
            String path_g = path + "/" + f;
            List<Map<String, Object>> l = null;
            // ????????????
            if (!gtype) {
                if (table.equals("ums_entry_info")) {
                    j = 1;
                } else {
                    Integer count = attachedTableService.countNoAspect(table);
                    if (count > 5000) {
                        j = 1;
                    }
                }
            } else {
                j = 1;
            }
            // ????????????
            list = umsFieldContrastService.selectByExample(example);
            if (list == null || list.size() == 0) {
                return;
            }
            if (!gtype) {
                Map<String, UmsUserInfo> collectUser = null;
                do {
                    //??????ums_user_info_view ???
                    if (table.equals("ums_user_info")) {
                        l = attachedTableService.selectAllView("ums_user_info_view", start, limit);
                    } else {
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
                            } else if (table.equals("ums_party_organization")) {
                                mapArrayList.add(listInfo);
                            }

                        }
                    } else {
                        mapArrayList = l;
                    }
                    //???????????????
                    Document doc = DocumentHelper.createDocument();
                    Element dataset = doc.addElement("dataset");
                    //?????? addElement() ????????????????????? <root> ??? addElement() ????????? XML ????????????????????????
                    Element root = dataset.addElement("table").addAttribute("name", f);
                    writeXml(root, mapArrayList, list, table, map_query, court_map, umsDepartmentMap, collectUser, levelC);
                    String fileName = f + String.valueOf(j) + ".xml";
                    generateFile(path_g, fileName, doc);
                    j++;
                    start += limit;
                } while (l != null && l.size() != 0);
            } else {
                List<Map<String, Object>> mapArrayList = new ArrayList<>();
                do {
                    //??????ums_user_info_view ???
                    if (table.equals("ums_user_info")) {
                        l = attachedTableService.selectAllView("ums_user_info_view", start, limit);
                    } else {
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
                //???????????????
                Document doc = DocumentHelper.createDocument();
                Element dataset = doc.addElement("dataset");
                //?????? addElement() ????????????????????? <root> ??? addElement() ????????? XML ????????????????????????
                Element root = dataset.addElement("table").addAttribute("name", f);
                writeXml(root, mapArrayList, list, table, map_query, court_map, umsDepartmentMap, null, levelC);
                String fileName = f + String.valueOf(j) + ".xml";
                generateFile(path_g, fileName, doc);
            }
        } catch (Exception e) {
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
        //?????????????????????xml???????????????GBK???????????????FileWriter?????????xml??????????????????????????????????????????
//            PrintWriter pw = new PrintWriter(path);
//            xw = new XMLWriter(pw, outputFormat);

        //?????????????????????xml???????????????utf-8???????????????FileOutputStream?????????xml??????????????????????????????????????????
        FileOutputStream fos = new FileOutputStream(file);
//        xw = new XMLWriter(fos, outputFormat);
        //<![CDATA[]]>?????????
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

    // ?????????????????????????????????
    private List<String> lawIndex = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "38");

    /**
     * @param root      ?????????xml????????????
     * @param l         ????????????????????????????????????List<Map>???????????????
     * @param list      ?????????????????????????????? ???????????????
     * @param tableName ???????????????????????????
     * @param map_query ?????????????????????id
     * @param court_map key????????????????????? ???2950 value???????????????id
     */
    private void writeXml(Element root, List<Map<String, Object>> l, List<UmsFieldContrast> list, String tableName, Map<String, Map<String, String>> map_query, Map<String, Object> court_map, Map<String, UmsDepartment> umsDepartmentMap, Map<String, UmsUserInfo> collectUser, Map<Object, List<Map<String, Object>>> levelC) {

        try {

            //?????????????????????
            for (UmsFieldContrast ums_l : list) {
                String filedType = ums_l.getTargetFieldType().toUpperCase();
                if (filedType.equals("STRING")) {
                    filedType = "VARCHAR2";
                }
                Element father = root.addElement("column").addAttribute("type", filedType);
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
//                            // ?????????Map ??? ???????????????????????????map ?????????map ??????????????????map
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

                    //??????????????????
                    if (tableName.equals("ums_user_info") && sourceField.equals("")) {

                        String photoN = "/ResourceFiles/rsgl/images/photos/" + m.get("id").toString() + ".jpg";
                        String p = servletContext.getRealPath("") + photoN;
                        File file = new File(p);
                        if (file.exists()) {
                            s = photoN;
                        }
                    }

                    //???????????????
                    if (tableName.equals("ums_user_info")) {
                        //??????????????????
                        if (sourceField.equals("ZDYXA0109")) {

                            if (m_r.get("is_valid") != null && m_r.get("is_valid").toString().equals("1")) {
                                s = "1";
                            } else {
                                if (m_r.get("leave_reason") != null) {
                                    if (m_r.get("leave_reason").toString().equals("5")) {
                                        //?????????
                                        s = "2";
                                    } else if (m_r.get("leave_reason").toString().equals("6")) {
                                        //??????
                                        s = "3";
                                    }
                                }
                            }

                            if (!StringUtils.hasText(s)) {
                                s = "2";
                            }
                        }

                        //??????
                        if (targetField.equals("N_DJ") || targetField.equals("D_DJRQ")) {

                            String user_id = m.getOrDefault("id", "").toString();
                            if (StringUtils.hasText(user_id)) {
                                List<Map<String, Object>> maps = levelC.get(user_id);
                                if (maps != null && maps.size() > 0) {
                                    Map<String, Object> stringObjectMap = maps.get(0);
                                    if (targetField.equals("N_DJ")) {
                                        String n_level_type = stringObjectMap.getOrDefault("n_level_type", "").toString();

                                        switch (n_level_type) {
                                            case "1":
                                                s = stringObjectMap.getOrDefault("judge_level", "").toString();
                                                break;
                                            case "2":
                                                s = stringObjectMap.getOrDefault("marshal_level", "").toString();
                                                break;
                                            case "3":
                                                s = stringObjectMap.getOrDefault("helper_level", "").toString();
                                                break;
                                            case "4":
                                                s = stringObjectMap.getOrDefault("clerk_level", "").toString();
                                                break;
                                            default:
                                                s = stringObjectMap.getOrDefault("judge_level", "").toString();
                                        }
                                    }
                                    if (targetField.equals("D_DJRQ")) {
                                        Object d_start_date = stringObjectMap.get("d_start_date");
                                        s = d_start_date != null ? d_start_date.toString() : "";

                                    }

                                }
                            }

                        }

                        //????????????
//                        if(sourceField.equals("N_RYFL")){
//                            //??????????????????
//                            String law_position = m_r.getOrDefault("law_position", "").toString();
//                            if(StringUtils.hasText(law_position)){
////                                ???????????????????????????????????????????????? ???  ???????????? ????????????????????? ??????????????????????????????
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
////                                    21	RYFL	?????????	8
////                                    22	RYFL	????????????	9
////                                    23	RYFL	?????????	10
////                                    24	RYFL	????????????	11
////                                    25	RYFL	??????????????????	12
////                                    29	RYFL	????????????????????????	99
////                                    3	RYFL	??????????????????	1001
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
//                            //??????????????? ????????????????????????
//                            if(!StringUtils.hasText(s)){
//                                s = "29";
//                            }
//
//                        }
//                        //????????????
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

//                    ??????????????????????????????????????????????????????????????????????????????
                    if (tableName.equals("ums_user_info") && sourceField.equals("department")) {
                        if (m_r.getOrDefault("dept_org_code", "").toString().equals("000")) {
                            String bzcy = m_r.getOrDefault("bzcy", "0").toString();
                            if (bzcy.equals("1")) {
                                m_r.put("department", bzcyDeptNo);
                            } else if (bzcy.equals("2")) {
                                m_r.put("department", fbzcyDeptNo);
                            }
                        }
                    }

                    //?????????????????????id
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

                    //?????????????????????id
                    if (tableName.equals("ums_user_info") && sourceField.equals("court_no")) {
                        String i = m_r.get("court_no").toString();
                        if (map_query.get(i) != null) {
                            s = court_map.get(i).toString();
                        } else {
                            s = "";
                        }
                    }

                    if (tableName.equals("ums_court_full") && sourceField.equals("")) {
                        s = unitType; //???????????? ???????????????????????? ?????????
                    }

                    //???????????????<> ?????????xml????????????  ??? : <2015>  ????????? (2015)

                    if (StringUtils.countOccurrencesOf(s, "<") > 0 && StringUtils.countOccurrencesOf(s, ">") > 0) {
                        String s1 = StringUtils.replace(s, "<", "(");
                        String s2 = StringUtils.replace(s1, ">", ")");
                        s = s2;
                    }

                    //A32???????????????????????????????????? ????????????????????? ????????????????????? ??????????????? 99 ????????????
                    if (tableName.equals("ums_wage_info") && sourceField.equals("c_pos_wage_grade")) {
                        s = "99";
                    }


                    //??????????????? ????????????????????? ?????????????????????'99'???????????????
                    if (tableName.equals("ums_level_info") && sourceField.equals("n_alt_reason")
                            && StringUtils.hasText(s) && s.trim().equals("99")) {
                        s = "";
                    }
                    //???????????? ????????????????????? ( 1-31  ?????????????????????????????? ???????????? ??????????????? )
//                    if (tableName.equals("ums_level_info") && sourceField.equals("is_yefg")) {
//                        Object defined = m.get("userdefined");
//                        if(defined != null && defined.toString().equals("true")){
//                            //???????????????????????????
//                            s = "1" ;
//                        }else{
//                            s = "2";
//                        }
//                    }
                    //???????????? ????????????????????? ( 1-31  ?????????????????????????????? ????????????????????? ??????????????? )
//                    if (tableName.equals("ums_level_info") && sourceField.equals("n_present_info")) {
//                        Object defined = m.get("userdefined");
//                        String user_id = m.getOrDefault("user_id", "").toString();
//                        if(defined != null && defined.toString().equals("true")){
//                            //???????????????????????????
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

    //?????????????????????
    @Action("updateIn")
    public void updateIncrementalInfo() {
        //
        System.out.println("in");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String format = sdf.format(new Date());
        //??????????????????????????????
        String path = servletContext.getRealPath("") + "ResourceXml/ZL_CQGY_" + format;

        //?????????????????????
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
        //????????????????????????
        UmsUserInfoCriteria criteria = new UmsUserInfoCriteria();
        criteria.createCriteria().andIsValidEqualTo(1).andUpdateTimeGreaterThanOrEqualTo(date);
        List<UmsUserInfo> umsUserInfos = umsUserInfoService.selectByExample(criteria);

        if (umsUserInfos == null || umsUserInfos.size() == 0) {
            return;
        }

        //??????????????????????????????
        List<String> s = umsFieldContrastService.selectTableName();

        //???????????????
        // ????????????????????????????????????????????????
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
        //???????????? ??????????????? ?????????????????????
        Map<String, Map<String, String>> map_query = new HashMap<>();
        Map<String, Object> court_map = new HashMap<>();
        Map<String, UmsDepartment> umsDepartmentMap = new HashMap<>();

        try {
            //????????????????????????
            List<UmsUploadDeptContrast> u_dept_list = new ArrayList<>();
            //????????????
            List<UmsCourtFull> court_list = umsCourtFullService.selectByListAll();

            UmsUploadDeptContrastExample example = new UmsUploadDeptContrastExample();

            //??????
            example.createCriteria().andZdyb0131EqualTo("7");
            List<UmsUploadDeptContrast> list_level_1 = umsUploadDeptContrastService.selectByExample(example);

            //??????
            example.clear();
            example.createCriteria().andZdyb0131EqualTo("13");
            List<UmsUploadDeptContrast> list_level_2 = umsUploadDeptContrastService.selectByExample(example);

            //????????????
            example.clear();
            example.createCriteria().andZdyb0131EqualTo("23");
            List<UmsUploadDeptContrast> list_level_3 = umsUploadDeptContrastService.selectByExample(example);

            //??????Map????????????
            Map<String, UmsCourtFull> map_dept = new HashMap<>();


            for (UmsCourtFull courtFull : court_list) {
                map_dept.put(courtFull.getCourtStdNo().toString(), courtFull);
            }

            //????????????????????????????????????
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
                dept_criteria.createCriteria().andCourtCodeEqualTo(j.getCourtCode()).andIsValidEqualTo(1); //??????

//                select * from  ums_department where   is_valid = 1  and court_code is not null
//                order by court_code,level,sort_no;
                //???????????????????????????
                dept_criteria.setOrderByClause(" court_code,level,sort_no ");
                List<UmsDepartment> l = umsDepartmentService.selectByExample(dept_criteria);
                if (u.getZdyb0131().equals("23")) {
                    example.clear();
                    example.createCriteria().andB0144bEqualTo(u.getB00());

                } else {
                    example.clear();
                    example.createCriteria().andB0144bEqualTo(u.getB00()).andB0101EqualTo("????????????");
                    String id = umsUploadDeptContrastService.selectByExample(example).get(0).getB00();
                    example.clear();
                    example.createCriteria().andB0144bEqualTo(id);
                }

                //???????????????????????????
                List<UmsUploadDeptContrast> con_list = umsUploadDeptContrastService.selectByExample(example);
                //?????????map
                Map<String, UmsUploadDeptContrast> map_dept_query = new HashMap<>();
                //????????????
                String zdyb0131 = null;
                if (u.getZdyb0131().equals("7")) {
                    //??????????????????
                    zdyb0131 = "9";
                } else if (u.getZdyb0131().equals("13")) {
                    //??????????????????
                    zdyb0131 = "15";
                } else if (u.getZdyb0131().equals("23")) {
                    //????????????????????????
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
                    umsDepartmentMap.put(d_l.getCourtNo() + "_" + d_l.getDeptNo(), d_l);
                    if (add_dept == null) {
                        UmsUploadDeptContrast new_dept = new UmsUploadDeptContrast();
                        String uuid = UUID.randomUUID().toString();
                        new_dept.setB00(uuid);
                        new_dept.setB0104(d_l.getDeptStName());
                        new_dept.setB0101(d_l.getDeptName());
                        new_dept.setZdyb0131(zdyb0131);
                        new_dept.setB0144b(parentId);
                        if (d_l.getOrgType() != null && d_l.getOrgType() == 1) {
                            //????????????
                            new_dept.setB0174(1);
                        } else {
                            //???????????????
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
                            //????????????
                            add_dept.setB0174(1);
                        } else {
                            //???????????????
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


        //????????????xml
        for (String table : s) {
            if (table.equals("ums_court_full")) {
                continue;
            }


            try {
                //??????
                String f = getXmlName(table);
                if (f == null) {
                    f = table;
                }
                String path_g = path + "/" + f;

                //????????????
                UmsFieldContrastExample example = new UmsFieldContrastExample();
                UmsFieldContrastExample.Criteria fieldCriteria = example.createCriteria();
                fieldCriteria.andSourceTableEqualTo(table);
                example.setOrderByClause(" sort_no asc");
                List<UmsFieldContrast> list = umsFieldContrastService.selectByExample(example);
                if (list == null || list.size() == 0) {
                    return;
                }
                List<Map<String, Object>> l = new ArrayList<>();

                //??????
                for (UmsUserInfo userInfo : umsUserInfos) {

                    List<Map<String, Object>> singleInfo = attachedTableService.selectSingleInfo(table, userInfo.getId());

                    if (singleInfo != null) {
                        l.addAll(singleInfo);
                    }

                }

                //???????????????
                Document doc = DocumentHelper.createDocument();
                Element dataset = doc.addElement("dataset");
                //?????? addElement() ????????????????????? <root> ??? addElement() ????????? XML ????????????????????????
                Element root = dataset.addElement("table").addAttribute("name", f);
//
                writeXml(root, l, list, table, map_query, court_map, umsDepartmentMap, null, null);
//
                String fileName = f + "0.xml";

                generateFile(path_g, fileName, doc);

            } catch (Exception e) {
                e.printStackTrace();
            }
            //??????
            String ZipFileName = "ZL_CQGY_" + format + ".zip";
            ZipUtils.zip(path, zipPath + "/" + ZipFileName);

        }

    }

    //??????Ftp?????????????????????
    private boolean ftpUpload(String url, String port, String username, String password, String remotePath, String fileNamePath, String fileName) {
        FTPClient ftpClient = new FTPClient();
        FileInputStream fis = null;
        boolean result = false;
        try {
            ftpClient.connect(url, Integer.parseInt(port));
            boolean loginResult = ftpClient.login(username, password);
            int returnCode = ftpClient.getReplyCode();
            if (loginResult && FTPReply.isPositiveCompletion(returnCode)) {// ??????????????????
                ftpClient.makeDirectory(remotePath);
// ??????????????????
                ftpClient.changeWorkingDirectory(remotePath);
                ftpClient.setBufferSize(1024);
                ftpClient.setControlEncoding("UTF-8");
                ftpClient.enterLocalPassiveMode();
                // ?????????????????????????????????
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.setDataTimeout(60 * 60 * 1000);
                ftpClient.setDefaultTimeout(60 * 60 * 1000);
                ftpClient.setConnectTimeout(60 * 60 * 1000);
                File file = new File(fileNamePath, fileName);
                fis = new FileInputStream(file);
                boolean b = ftpClient.storeFile(fileName, fis);


                result = b; // ????????????
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("FTP??????????????????", e);
        } finally {
// IOUtils.closeQuietly(fis);
            try {
                ftpClient.logout();
                ftpClient.disconnect();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("??????FTP?????????????????????", e);
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
     * ????????????xml????????????????????????
     *
     * @param tableName level_info?????????????????????4???
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
        map.put("ums_level_info_fj", "T_RYSX_FJDJ");//?????????
        map.put("ums_level_info_fgzl", "T_RYSX_FGZLDJ");//??????????????? //todo ??????????????????????????????
        map.put("ums_level_info_sjy", "T_RYSX_SJYDJ");//???????????? //todo ??????????????????????????????
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
//
//        ums_change_info
        return map.get(tableName);
    }

    //???????????? ????????????
    private String ywlb(String key) {
        String returnStr = "";
        Map<String, String> ywlbMap = new HashMap<>();
//        1	YWLB	?????????
//        2	YWLB	????????????
//        3	YWLB	????????????
//        4	YWLB	????????????
//        5	YWLB	??????
//        6	YWLB	??????
//        7	YWLB	??????
//        8	YWLB	??????
//        9	YWLB	????????????
//        10	YWLB	????????????
//        11	YWLB	????????????
//        12	YWLB	????????????
//        13	YWLB	????????????
//        14	YWLB	????????????
//        15	YWLB	????????????
//        16	YWLB	????????????
//        17	YWLB	????????????
//        255	YWLB	??????????????????

//        ????????? ??? ?????????
//        ?????????????????????????????????????????? ?????????
//        ?????? ??? ??????
//        ???????????? ??? ????????????
//        ?????? ?????????
//        ?????? ??? ??????
//        ?????? ??? ??????
//        ?????? ??? ??????
//        ???????????? ???????????????
//        ????????????????????????
//        ???????????????????????????
//        ????????????????????? ???????????????
//        ???????????????????????????????????? ????????????
//        ????????? ??? ????????????
//        ???????????????????????? ???????????????
//        ?????? ??? ????????????
//        ????????????????????????
//
//        ????????????????????? ??? ??????????????????

        ywlbMap.put("?????????", "1");
        ywlbMap.put("??????", "2");
        ywlbMap.put("??????", "3");
        ywlbMap.put("???????????????", "3");
        ywlbMap.put("??????", "3");
        ywlbMap.put("??????", "3");
        ywlbMap.put("????????????", "4");
        ywlbMap.put("??????", "5");
        ywlbMap.put("??????", "6");
        ywlbMap.put("??????", "7");
        ywlbMap.put("????????????", "7");
        ywlbMap.put("??????", "8");
        ywlbMap.put("????????????", "10");
        ywlbMap.put("?????????", "12");
        ywlbMap.put("????????????", "13");
        ywlbMap.put("??????", "14");
        ywlbMap.put("????????????", "14");
        ywlbMap.put("????????????", "9");
        ywlbMap.put("??????????????????", "9");
//        ywlbMap.put("?????????","14");
        ywlbMap.put("?????????", "15");
        ywlbMap.put("????????????", "15");
        ywlbMap.put("??????", "16");
        ywlbMap.put("?????????", "17");
        ywlbMap.put("??????????????????", "255");


        if (StringUtils.hasText(key)) {
            //?????? (?????????)
            if ("?????????".equals(key)) {
                returnStr = "14";
                return returnStr;
            }

            for (String s : ywlbMap.keySet()) {
                if (key.contains(s)) {
                    returnStr = ywlbMap.get(s);
                    break;
                }
            }
        }


        return returnStr;
    }

    public List<UmsUploadDeptContrast> generateB01(Map<String, Map<String, String>> map_query, Map<String, Object> court_map, Map<String, UmsDepartment> umsDepartmentMap) {
        //???????????????map ??? getCourtNo ???key  ?????????map ??? deptNo ???key  ??????????????????B00 ???value
//        Map<String, Map<String, String>> map_query = new HashMap<>();
//        court_map ???ums_court_full ???court_no ???key ??? ??????????????????B00 ??? value
//        Map<String, Object> court_map = new HashMap<>();
        //??????????????????????????????????????? ??? getCourtNo() + "_" + getDeptNo() ???key
//        Map<String,UmsDepartment> umsDepartmentMap = new HashMap<>();

        //????????????
        UmsCourtFullCriteria criteria = new UmsCourtFullCriteria();
        criteria.clear();
        criteria.createCriteria().andCourtGradationEqualTo(0);
        List<UmsCourtFull> level0 = umsCourtFullService.selectByExample(criteria);

        List<UmsUploadDeptContrast> re = new ArrayList<>();

        for (UmsCourtFull umsCourtFull : level0) {
            umsCourtFull.setSortNo(0);
            readDeptNode(umsCourtFull, map_query, court_map, umsDepartmentMap, re, 0);
        }

        return re;


    }

    // level 0 ?????? ???????????? ??? ????????????  level 1 ??????????????????  level 1+ ?????????????????????
    private void readDeptNode(UmsCourtFull umsCourtFull, Map<String, Map<String, String>> map_query, Map<String, Object> court_map, Map<String, UmsDepartment> umsDepartmentMap, List<UmsUploadDeptContrast> re, Integer level) {

        Map<String, String> innerMap = new HashMap<>();
        //?????????????????????
        UmsUploadDeptContrast umsUploadDeptContrast = changToDeptContrast(umsCourtFull);
        re.add(umsUploadDeptContrast);

        court_map.put(umsCourtFull.getCourtNo().toString(), umsCourtFull.getId());


        //??????????????????
        UmsCourtFullCriteria criteria = new UmsCourtFullCriteria();
        criteria.clear();
        criteria.createCriteria().andParentIdEqualTo(umsCourtFull.getId()).andCourtNoIsNotNull();
        List<UmsCourtFull> childCourtList = umsCourtFullService.selectByExample(criteria);

        //??????????????????
        String uuid = "";
        if (childCourtList != null && childCourtList.size() > 0) {
            uuid = getUUID(umsCourtFull.getCourtCode());
            UmsUploadDeptContrast contrast = new UmsUploadDeptContrast();
            contrast.setB00(uuid);
            contrast.setB0101("????????????");
            contrast.setB0104("????????????");
            contrast.setZdyb0131(level == 0 ? "8" : level == 1 ? "14" : "24");
            contrast.setB0144b(umsCourtFull.getId());
            contrast.setPindex(umsUploadDeptContrast.getPindex() + "01");
            re.add(contrast);
        }


        //??????????????????  ?????????????????????????????? ??????????????????
        String uuid_zsjg = "";
        if (level == 0) {
            uuid_zsjg = getUUID(umsCourtFull.getCourtCode() + "1");
            UmsUploadDeptContrast contrast_zsjg = new UmsUploadDeptContrast();
            contrast_zsjg.setB00(uuid_zsjg);
            contrast_zsjg.setB0101("????????????");
            contrast_zsjg.setB0104("????????????");
            contrast_zsjg.setZdyb0131("8");
            contrast_zsjg.setB0144b(umsCourtFull.getId());
            contrast_zsjg.setPindex(umsUploadDeptContrast.getPindex() + "02");
            re.add(contrast_zsjg);
        }


        //??????????????????
        UmsDepartmentCriteria dept_criteria = new UmsDepartmentCriteria();
        dept_criteria.clear();
        dept_criteria.createCriteria().andCourtCodeEqualTo(umsCourtFull.getCourtCode()).andIsValidEqualTo(1).andLevelEqualTo(1);
        dept_criteria.setOrderByClause(" court_code,level,sort_no ");
        List<UmsDepartment> l = umsDepartmentService.selectByExampleWithNoAspect(dept_criteria);

        int index = 2;
        if (level == 1) index = 1;
        if (level == 2) index = 0;
        for (UmsDepartment department : l) {
            index++;
            department.setSortNo(index);
            String sortNo = department.getSortNo() > 9 ? umsUploadDeptContrast.getPindex() + "" + department.getSortNo() : umsUploadDeptContrast.getPindex() + "0" + department.getSortNo();
            if (level == 0) {
                level0SortNoPrefix = department.getSortNo();
            } else if (level == 1) {
                level1SortNoPrefix = Integer.parseInt(sortNo.substring(sortNo.length() - 4));
            }

            umsDepartmentMap.put(department.getCourtNo() + "_" + department.getDeptNo(), department);
            innerMap.put(department.getDeptNo().toString(), department.getId());

            //??????????????????
            if (StringUtils.hasText(department.getDeptName()) && "??????????????????".equals(department.getDeptName()) && StringUtils.hasText(uuid_zsjg)) {
                re.add(changToDeptContrast(department, uuid_zsjg, umsUploadDeptContrast.getPindex() + "02"));
            } else {
                re.add(changToDeptContrast(department, StringUtils.hasText(uuid) ? uuid : umsCourtFull.getId(), umsUploadDeptContrast.getPindex() + "01"));
            }

            //?????????????????????????????????????????????????????????
            if (department.getDeptName().equals("?????????")) {
                UmsDepartment u1 = new UmsDepartment();
                //u1.setId(department.getCourtNo() + "" + bzcyDeptNo);
                u1.setId(getUUID(umsCourtFull.getCourtCode() + 2));
                u1.setDeptName("???????????????????????????");
                u1.setDeptStName("?????????");
                u1.setCourtLevel("9");
                u1.setDeptType("2");
                u1.setSortNo(1);
                u1.setIsLeaders("1");
                re.add(changToDeptContrast(u1, department.getId(), sortNo));

                UmsDepartment u2 = new UmsDepartment();
                //u2.setId(department.getCourtNo() + "" + fbzcyDeptNo);
                u2.setId(getUUID(umsCourtFull.getCourtCode() + 3));
                u2.setDeptName("??????????????????????????????");
                u2.setDeptStName("?????????");
                u2.setCourtLevel("9");
                u2.setDeptType("2");
                u2.setSortNo(2);
                re.add(changToDeptContrast(u2, department.getId(), sortNo));

                innerMap.put(bzcyDeptNo + "", u1.getId());
                innerMap.put(fbzcyDeptNo + "", u2.getId());
            }
            //????????????????????????
            addChild(re, innerMap, umsDepartmentMap, umsCourtFull.getCourtCode(), department.getId(), sortNo);

        }

        map_query.put(umsCourtFull.getCourtNo().toString(), innerMap);

        if (childCourtList != null && childCourtList.size() > 0) {
            level++;
            int courtIindex = 0;
            for (UmsCourtFull courtFull : childCourtList) {
                courtIindex++;
                courtFull.setSortNo(courtIindex);
                readDeptNode(courtFull, map_query, court_map, umsDepartmentMap, re, level);

            }

        }

    }

    private void addChild(List<UmsUploadDeptContrast> re, Map<String, String> innerMap, Map<String, UmsDepartment> umsDepartmentMap, String courtCode, String pid, String pindex) {

        if (!StringUtils.hasText(pid)) {
            return;
        }

        UmsDepartmentCriteria dept_criteria = new UmsDepartmentCriteria();
        dept_criteria.createCriteria().andCourtCodeEqualTo(courtCode).andIsValidEqualTo(1).andParentIdEqualTo(pid); //??????
        dept_criteria.setOrderByClause(" court_code,level,sort_no ");

        List<UmsDepartment> l = umsDepartmentService.selectByExampleWithNoAspect(dept_criteria);

        if (l == null || l.size() == 0) {
            return;
        }

        int index = 0;
        for (UmsDepartment department : l) {
            index++;
            department.setSortNo(index);
            umsDepartmentMap.put(department.getCourtNo() + "_" + department.getDeptNo(), department);
            innerMap.put(department.getDeptNo().toString(), department.getId());
            UmsUploadDeptContrast umsUploadDeptContrast = changToDeptContrast(department, pid, pindex);
            re.add(umsUploadDeptContrast);

            addChild(re, innerMap, umsDepartmentMap, courtCode, department.getId(), umsUploadDeptContrast.getPindex());

        }


    }

    private UmsUploadDeptContrast changToDeptContrast(UmsCourtFull courtFull) {

        if (courtFull == null) {
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
        String sortNo = sortNoPrefix + "";
        if (courtFull.getCourtGradation() == 1) {
            level0SortNoPrefix++;
            sortNo = sortNoPrefix + (level0SortNoPrefix <= 10 ? "0" + level0SortNoPrefix : "" + level0SortNoPrefix);
        } else if (courtFull.getCourtGradation() == 2) {
            level1SortNoPrefix++;
            sortNo = sortNoPrefix + (level1SortNoPrefix <= 1000 ? "0" + level1SortNoPrefix : "" + level1SortNoPrefix);
        }
        c.setPindex(sortNo);
        return c;
    }

    private UmsUploadDeptContrast changToDeptContrast(UmsDepartment department, String pid, String pindex) {

        if (department == null) {
            return null;
        }
        UmsUploadDeptContrast c = new UmsUploadDeptContrast();
        c.setB00(department.getId());
        c.setB0101(department.getDeptName());
        c.setB0104(department.getDeptStName());
        c.setB0114(department.getInstitutionCode());
        c.setZdyb0131(department.getCourtLevel());
        if (StringUtils.hasText(pid)) {
            c.setB0144b(pid);
        } else {
            c.setB0144b(department.getParentId());
        }
        c.setB0174(department.getDeptType() != null ? Integer.parseInt(department.getDeptType()) : 2); //?????????????????????
        String sortNo = department.getSortNo() != null ? (department.getSortNo() > 9 ? pindex + department.getSortNo() : pindex + "0" + department.getSortNo()) : null;
        c.setPindex(sortNo);
        c.setZdyxb0138(StringUtils.hasText(department.getIsPeples()) ? Integer.parseInt(department.getIsPeples()) : 2); //?????????????????????
        c.setZdyxb0139(StringUtils.hasText(department.getIsLeaders()) ? Integer.parseInt(department.getIsLeaders()) : 2); //?????????????????????

        return c;
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

    // ???????????????????????????????????????????????????????????????
    public void deleteFile(File file) {
        if (file.isFile()) {//??????????????????????????????
            file.delete();
        } else {
            //???????????????????????????
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

    // ????????????32??????UUid???????????????????????????id??????????????????
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

}
