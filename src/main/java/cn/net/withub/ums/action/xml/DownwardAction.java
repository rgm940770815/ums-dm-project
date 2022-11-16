package cn.net.withub.ums.action.xml;

import cn.net.withub.ums.entity.*;
import cn.net.withub.ums.entity.factory.EntityFactory;
import cn.net.withub.ums.service.*;
import cn.net.withub.ums.util.StringTools;
import cn.net.withub.ums.webService.ServiceLocator;
import cn.net.withub.ums.webService.ServiceSoapBindingStub;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.convention.annotation.*;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 数据下行接口，需要配置json，文件在 /resources/downconfig.json，可访问页面 webapp\dwConfig.jsp 生成后复制进json文件
 * Created by cuizhibin on 2018/5/22.
 */
@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/downward")
@Results({
        @Result(name = "json", type = "json", params = {"root", "data"})
})
public class DownwardAction {

    private static Logger logger = LogManager.getLogger(DownwardAction.class);

    private Object data;

    @Autowired
    public UmsFieldContrastService umsFieldContrastService;

    @Autowired
    public UmsCourtFullService umsCourtFullService;

    @Autowired
    private EntityFactory entityFactory;

    @Autowired
    private UmsAttachedTableService attachedTableService;
    @Autowired
    private UmsUserInfoService umsUserInfoService;
    @Autowired
    UmsCodeService umsCodeService;

    private String webservice = "http://149.0.0.41/webservice/services?wsdl";
    private List<UmsCourtFull> umsCourtFulls;
    private Map<String, String> courtMap;
    private List<UmsFieldContrast> allField;
    private Map<String, UmsFieldContrast> stringFieldMap = new HashMap<>();
    private Map<Integer, List<Map<String, String>>> umscodeList = new HashMap<>();

    private List<String> errorMsg = new ArrayList<>();
    private List<UmsUserInfo> userList = new ArrayList<>();

    @Action("/trans")
    public void trans() {

        /*
         a15 表 N_KHJG 单独处理，映射表 target_field为 A1517，对应 source_field 为 n_result
         A14Z2 表 ZDYXA14Z204, ZDYXA14Z205, ZDYXA14Z206, BZLY 新增，对应source_table 为 ums_reward_info，对应source_field 为 c_approval_unit, c_approval_unit_add, approval_name, approval_type
         T_RYSX_LDBZ 表 LDBZA00 单独处理，映射表 target_field 为 T_RYSX_LDBZA00，对应 source_field 为 id
         T_RYSX_GWY 表 N_ZQDZYJ 单独处理，映射表 target_field 为 N_GZDC，对应 source_field 为 n_wage_grade
         */
        try {

            errorMsg.clear();
            UmsFieldContrastDownconfig config = getConfig();
            if (config == null) throw new Exception("读取配置文件为空");
            // 获取是否需要同步附加信息
            List<String> strings = config.getConfigList().stream().map(UmsFieldContrastDownconfig.Config::getTable).collect(Collectors.toList());
            boolean allxx = false;
            for (String string : strings) {
                if (!string.equals("A01") && !string.equals("B01") && !string.equals("KZB06")) {
                    allxx = true;
                    break;
                }
            }
            //初始化法院
            initGlobal();
            String content;
            if (config.getFymc().get(0).equals("ALL")) {
                config.getFymc().clear();
                config.getFymc().add("高院");
            }
            //处理机构            // todo 机构手动同步
            Iterator<UmsFieldContrastDownconfig.Config> iterator = config.getConfigList().iterator();
            while (iterator.hasNext()) {
                UmsFieldContrastDownconfig.Config temp = iterator.next();
                if (temp.getTable().equals("B01")) {
                    iterator.remove();
                    break;
                }
            }
//           循环法院
            for (String fyjc : config.getFymc()) {
                long t1 = System.currentTimeMillis();
//              判断使用的接口并获取xml数据
                content = getContent(fyjc, config.getTime(), allxx, config.getTime().equals("ALL"), config.getType() == 1);
                long t2 = System.currentTimeMillis();
                System.out.println("......." + (t2 - t1));
//              将xml数据转为document文档
                Document document = DocumentHelper.parseText(content);
                for (UmsFieldContrastDownconfig.Config config1 : config.getConfigList()) {
//                    如果字段是ALL，将所有字段获取并加入field list
                    if (config1.getField().get(0).equals("ALL")) {
                        config1.getField().clear();
                        config1.getField().addAll(allField.stream().filter(u -> u.getTargetTable().equals(config1.getTable())).map(UmsFieldContrast::getTargetField).collect(Collectors.toList()));
                    }
//                    区分时间段和不分区时间段获取到的xml节点属性不同
                    if (config.getTime().equals("ALL")) {
                        handle(document, config1, "", fyjc, config.isOnlyaddnew());
                    } else {
                        handle(document, config1, "_HIS", fyjc, config.isOnlyaddnew());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\error.txt")))) {
            for (String s:errorMsg) {
                bufferedWriter.write(s);
                bufferedWriter.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //String json = "{\"positionType\":\"职务类别\",\"nation\":\"民族\",\"political\":\"政治面貌\",\"enterWay\":\"进入途径\",\"enterSource\":\"进入来源\",\"preparation\":\"所属编制\",\"proCert\":\"专业证书\",\"partyOffice\":\"党组职务\",\"major\":\"专业\",\"leaveDestination\":\"调离去向\",\"rank\":\"职级类型\",\"formerName\":\"曾用名\",\"physicalCondition\":\"健康状况\",\"educationBackground\":\"学历\",\"servantLevel\":\"公务员级别\",\"level\":\"等级\",\"personnelClassification\":\"人员分类\",\"degree\":\"学位\",\"officialNo\":\"执行公务证编号\",\"additionalDuration\":\"应加学制\",\"positionNature\":\"岗位性质\",\"phoneNumber\":\"手机号码\",\"positionTypeDate\":\"职务类别日期\",\"beforeCourtWorkYear\":\"入院前工作年限\",\"formerPost\":\"原职务\",\"birthplace\":\"出生地\",\"courtNo\":\"法院\",\"politicalReport\":\"政治面貌(最高院)\",\"maritalStatus\":\"婚姻状况\",\"formerRank\":\"原职级\",\"lawPositionReport\":\"法律职务(最高院)\",\"ukNo\":\"UK编号\",\"administrationPosition\":\"行政职务\",\"gender\":\"性别\",\"educationBackgroundReport\":\"学历(最高院)\",\"extraSeniority\":\"补充工龄\",\"nationReport\":\"民族(最高院)\",\"userCode\":\"编号\",\"rankReport\":\"职级类型(最高院)\",\"totalCourtYear\":\"法院工作合计年限 \",\"workNo\":\"工作证编号\",\"department\":\"部门\",\"helper_level\":\"辅助人员等级\",\"lawPosition\":\"法律职务\",\"hometown\":\"籍贯\",\"administrationPositionReport\":\"行政职务(最高院)\",\"lawyerCert\":\"法官资格证书类型\",\"sortNo\":\"排序\",\"bzxx\":\"备注信息\",\"leaveReason\":\"调离原因\",\"idcard\":\"身份证号\",\"deductionSeniority\":\"扣减工龄\",\"fullname\":\"姓名\",\"age\":\"年龄\",\"totalSeniority\":\"连续工龄 \",\"username\":\"用户名\",\"assign\":\"职务任用方式\"}";
        //Gson gson = new Gson();
        //LinkedHashMap map = gson.fromJson(json, LinkedHashMap.class);
        //
        //try (OutputStream os = new FileOutputStream("d:\\all.xls")) {
        //    List<LinkedHashMap> linkedHashMaps = CommonUtil.convertBeanList(userList);
        //    ExcelUtil.listMapToExcel(linkedHashMaps, map, "Sheet", userList.size(), os);
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}
    }

    /**
     * 获取记录行并更新对应table和对应的field
     * @param document  xml文档
     * @param onlyaddnew
     * @throws Exception
     */
    private void handle(Document document, UmsFieldContrastDownconfig.Config config1, String table_extend, String fyjc, boolean onlyaddnew) throws Exception {
        String table = config1.getTable();  //最高院表名
        List<String> fieldList = config1.getField();    //最高院字段列表
        boolean tempTable = config1.isTempTable();  //是否存入缓存表
        List<String> compareFields = config1.getCompareFields();    //对比字段
        List<Element> list = document.selectNodes("/dataset/table[@name='" + table + table_extend + "']/column");   //使用xpath方式去查找节点，获取xml文档的列

//        取出id字段的对应field名，并将xml中列的顺序存入fieldMap中
        String id = "id";
        String s = allField.stream().filter(u -> u.getSourceField().equals(id) && u.getTargetTable().equals(table)).map(UmsFieldContrast::getTargetField).collect(Collectors.toList()).get(0);
        Map<String, Integer> fieldMap = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            if (s.equals(list.get(i).getText())) {
                fieldMap.put(s, i);
            } else if (fieldList.contains(list.get(i).getText())) {
                fieldMap.put(list.get(i).getText(), i);
            }
        }
        List<Element> rows = document.selectNodes("/dataset/table[@name='" + table + table_extend + "']/row");
        if (rows.size() == 0) {
            errorMsg.add(fyjc + " | " + table + " | 列表为空，不更新");
        }
        for (Element row : rows) {
            UmsFieldContrast umsFieldContrast = stringFieldMap.get(table + "_" + s);
//            如果是法警，将本地表名换成 ums_level_info
            String sourceTable = umsFieldContrast.getSourceTable().equalsIgnoreCase("ums_level_info_fj") ? "ums_level_info" : umsFieldContrast.getSourceTable();
            if (!table.equals("B01") && !table.equals("KZB06")) {   //不处理机构信息、党组织情况信息
                // 基本信息、附加信息
                Map<String, String> p = new HashMap<>();
                final String[] update_id = new String[1];
                fieldMap.forEach((k, v) -> {
                    UmsFieldContrast umsk = stringFieldMap.get(table + "_" + k);
                    List<Element> temp = row.selectNodes("./value[" + (v + 1) + "]");
                    String text = temp.get(0).getText();
                    if (text != null && text.equals("null")) text = null;
                    if (s.equals(k)) update_id[0] = text;
                    String sourceField = umsk.getSourceField();
                    if (table.equals("A01") && sourceField.equals("court_no")) {//法院的直接获取，高院是字符串
                        p.put(sourceField, courtMap.get(fyjc));
                    } else if (!StringTools.isNullOrEmpty(sourceField)
                            && !sourceField.equals("department")    //排除部门
                            && !sourceField.equals("unicode")    //排除部门
                            ) {
                        p.put(sourceField, text);
                        if (sourceField.endsWith("_report")) {
                            String report = sourceField.substring(0, sourceField.lastIndexOf("_report"));
                            updateReport(p, sourceTable, report, text);
                        }
                    }
                });
                SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
                p.put("update_time", sd.format(new Date()));
                //获取userNo,在最高院没有这个字段
                if (!table.equals("A01")) {
                    UmsUserInfo userInfo = umsUserInfoService.selectById(p.get("user_id"));
                    if (userInfo == null) {
                        errorMsg.add(fyjc + " | " + p.get("user_id") + " | 基本信息null，不更新");
                        continue;
                    }
                    p.put("user_no", userInfo.getUserNo()+"");
                    p.put("court_code", userInfo.getCourtCode());
                    p.put("court_no", userInfo.getCourtNo() + "");
                } else {
                    UmsUserInfo userInfo = umsUserInfoService.selectById(p.get("id"));
                    if (userInfo == null) {
                        errorMsg.add(fyjc + " | " + p.get("id") + " | 基本信息null，不更新");
                        continue;
                    }
                }
                //List<String> l = Arrays.asList("0b4060eb-ed61-440b-b6e0-c06a22ad0967","3c355abb-2653-4a26-a5b0-ec585831bbb0","7d6f89f7-d003-478e-93bb-41f37c0f0948","51fa0003-4700-446e-bee1-a2c1cbfa328b","54ae9fbb-e990-4d8f-8153-826bb457c33c","65cf85f9-d368-4794-9c8e-0395fc669c22","80abc773-0383-453d-8fb2-a548e57b73a8","483c1e01-12f9-48e2-84c9-d7d8b86d2020","781c3c29-bbbf-4868-a6ea-34005f3f43ec","5102e340-a678-464f-8432-77483bf16136","7580ba3a-91a5-4b0f-a69d-42d4e7f9357a","7766c503-66ad-4f9f-8aeb-9058b7bd6347","a1b52788-db78-43a3-b2aa-a5ba51c25334","a6dd447f-b76c-412e-92db-0dfd7f79f627","a12e72b2-da70-44dc-8ce8-e7a3d89bc47d","b8a25178-0383-4ff8-8ff6-e9d6d51506b2","b895f735-ffd7-4d4d-b3b5-e526cd4f0c05","bbe4b6f3-38af-48ff-a119-203d8bf6f465","c8ae8286-c2fd-43ee-b439-d7f08722a3fb","c37efeb9-5f7c-4e49-b88b-83850790dc12","c697da1d-5577-4ac5-80b3-24f8815aca30","cbf30523-ab72-4072-8eba-8f302f25bade","ce84b3b9-0bc7-4230-8cea-b9c0394de4e1","e032b4c0-1688-4797-af35-f9bf6e0f8b7f","e98b83d2-41d7-41d0-aa01-7f08931aaa31");
                //if (l.contains(p.get("id"))) {
                //    Object entity = entityFactory.createEntity2(StringTools.underlineToCamel2(umsFieldContrast.getSourceTable()), p);
                //    userList.add((UmsUserInfo) entity);
                //    //int result = attachedTableService.updateSelective(entity, p.get(id));
                //    //logger.info(fyjc + "update === " + table + " ===id=== " + update_id[0] + (result > 0 ? " success" : " error") + " 当前：" + (rows.indexOf(row) + 1) + "/" + rows.size());
                //}

                //todo 法警信息集未完全修改完
                Object entity = entityFactory.createEntity2(StringTools.underlineToCamel2(sourceTable), p);
                int result = attachedTableService.updateSelective(entity, p.get(id), onlyaddnew, compareFields, tempTable);
                logger.info(fyjc + "update === " + table + " ===id=== " + update_id[0] + (result > 0 ? " success" : " error") + " 当前：" + (rows.indexOf(row) + 1) + "/" + rows.size());
            }
        }
    }

    //获取xml
    private String getContent(String fyjc, String time, boolean p1, boolean p2, boolean p3) throws Exception {

        UmsCourtFull fy = umsCourtFulls.stream().filter(u -> u.getCourtShortName().equals(fyjc)).collect(Collectors.toList()).get(0);
        List<UmsCourtFull> pfyList = umsCourtFulls.stream().filter(u -> u.getCourtCode().equals(fy.getParentId())).collect(Collectors.toList());
        String pfy = "最高人民法院";
        if (pfyList.size() > 0) {
            pfy = pfyList.get(0).getCourtStdName();
        }
        String fymc = fy.getCourtStdName();
        List<String> list = Arrays.asList("开州区", "梁平区", "武隆区");
        if (list.contains(fyjc)) {
            fymc = "重庆市" + fymc;
        } else if (fyjc.equals("石柱县")) {
            fymc = "石柱土家族自治县人民法院";
        } else if (fyjc.equals("秀山县")) {
            fymc = "秀山土家族苗族自治县人民法院";
        } else if (fyjc.equals("酉阳县")) {
            fymc = "酉阳土家族苗族自治县人民法院";
        } else if (fyjc.equals("彭水县")) {
            fymc = "彭水苗族土家族自治县人民法院";
        } else if (fyjc.equals("铁路法院")) {
            pfy = "重庆市高级人民法院";
        }

        ServiceLocator locator = new ServiceLocator();
        ServiceSoapBindingStub sub = new ServiceSoapBindingStub(new URL(webservice), locator);
        String xml;
        if (p1) {
            if (p2) {
                if (p3) {
                    xml = sub.getUserAllInfoForChildDeptService(fymc, pfy);
                } else {
                    xml = sub.getUserAllInfoForChildOrgService(fymc, pfy);
                }
            } else {
                if (p3) {
                    xml = sub.getByTimeUserAllInfoForChildDeptService(fymc, pfy, time);
                } else {
                    xml = sub.getByTimeUserAllInfoForChildOrgService(fymc, pfy, time);
                }
            }
        } else {
            if (p2) {
                if (p3) {
                    xml = sub.getUserInfoForChildDeptService(fymc, pfy);
                } else {
                    xml = sub.getUserInfoForChildOrgService(fymc, pfy);
                }
            } else {
                if (p3) {
                    xml = sub.getByTimeUserInfoForChildDeptService(fymc, pfy, time);
                } else {
                    xml = sub.getByTimeUserInfoForChildOrgService(fymc, pfy, time);
                }
            }
        }
        return xml;
    }

    private static UmsFieldContrastDownconfig getConfig() {
        try (InputStream is = DownwardAction.class.getClassLoader()
                .getResourceAsStream("downconfig.json");
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            byte[] bytes = new byte[1024];
            int length;
            while ((length = is.read(bytes)) != -1) {
                baos.write(bytes, 0, length);
            }
            return new Gson().fromJson(new String(baos.toByteArray(), "UTF-8"), UmsFieldContrastDownconfig.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void addExtendFieldList(List<UmsFieldContrast> allField) {
        UmsFieldContrast u1 = new UmsFieldContrast();
        u1.setTargetField("ZDYXA14Z204");
        u1.setSourceTable("ums_reward_info");
        u1.setSourceField("c_approval_unit_code");
        u1.setFieldDescribe("授予单位");
        allField.add(u1);
        UmsFieldContrast u2 = new UmsFieldContrast();
        u2.setTargetField("ZDYXA14Z205");
        u2.setSourceTable("ums_reward_info");
        u2.setSourceField("c_approval_unit_add");
        u2.setFieldDescribe("授予单位名称补充");
        allField.add(u2);
        UmsFieldContrast u3 = new UmsFieldContrast();
        u3.setTargetField("BZLY");
        u3.setSourceTable("ums_reward_info");
        u3.setSourceField("approval_type");
        u3.setFieldDescribe("表彰领域");
        allField.add(u3);

        int i = 0;
        for (UmsFieldContrast umsFieldContrast : allField) {
            if (umsFieldContrast.getTargetField().equals("A1517")
                    && umsFieldContrast.getSourceField().equals("n_result")
                    && umsFieldContrast.getSourceTable().equals("ums_assess_info")) {
                umsFieldContrast.setTargetField("N_KHJG");
                i++;
                if (i == 3) {
                    break;
                }
            }
            if (umsFieldContrast.getTargetField().equals("T_RYSX_LDBZA00")
                    && umsFieldContrast.getSourceField().equals("id")
                    && umsFieldContrast.getSourceTable().equals("ums_leadership")) {
                umsFieldContrast.setTargetField("LDBZA00");
                i++;
                if (i == 3) {
                    break;
                }
            }
            if (umsFieldContrast.getTargetField().equals("N_GZDC")
                    && umsFieldContrast.getSourceField().equals("n_wage_grade")
                    && umsFieldContrast.getSourceTable().equals("ums_civil_servant_level")) {
                umsFieldContrast.setTargetField("N_ZQDZYJ");
                i++;
                if (i == 3) {
                    break;
                }
            }
        }
    }

    public void initGlobal() {
        umsCourtFulls = umsCourtFullService.listAllCourts();
        courtMap = new HashMap<>();
//    初始化法院map
        for (UmsCourtFull umsCourtFull : umsCourtFulls) {
            courtMap.put(umsCourtFull.getCourtShortName(), umsCourtFull.getCourtNo() + "");
        }
//        将所有配置读进来，并转换为 targetTable_targetField: UmsFieldContrast 的map格式
        UmsFieldContrastExample example = new UmsFieldContrastExample();
        example.setOrderByClause(" source_table, sort_no ");
        allField = umsFieldContrastService.selectByExample(example);
        //先把需要单独处理的加进list，这里是特殊的一些对应关系
        addExtendFieldList(allField);
        allField.forEach(u -> {
            if (u.getSourceTable().equals("ums_casualty_info")) {
                u.setTargetTable("T_RYSX_ZYJSZW");
            } else {
                u.setTargetTable(XmlAction.getXmlName(u.getSourceTable()));
            }
            stringFieldMap.put(u.getTargetTable() + "_" + u.getTargetField(), u);
        });
    }

//    读取所有配置并返回到页面 webapp\dwConfig.jsp
    @Action("/all")
    public String getALl() {
        Map map = new HashMap();
        UmsFieldContrastExample example = new UmsFieldContrastExample();
        example.createCriteria().andSourceTableNotEqualTo("ums_court_full");
        example.setOrderByClause(" source_table, sort_no ");
        List<UmsFieldContrast> umsFieldContrasts = umsFieldContrastService.selectByExample(example);
        //先把需要单独处理的加进list
        addExtendFieldList(umsFieldContrasts);
        map.put("data", umsFieldContrasts);
        map.put("config", getConfig());
        map.put("courts", umsCourtFullService.listAllCourts().stream().map(UmsCourtFull::getCourtShortName).collect(Collectors.toList()));
        data = map;
        return "json";
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    //设置上报字段对应的字段的值
    private void updateReport(Map<String, String> p, String table, String key, String value) {

        String tk = table + "." + key;
        String s5[] = new String[]{"ums_user_info.nation"};
        String s11[] = new String[]{"ums_user_info.education_background", "ums_study_info.n_education_background", "ums_education_info.n_education_background"};
        String s13[] = new String[]{"ums_user_info.political", "ums_family_info.n_political", "ums_family_info.n_political", "ums_political_info.n_political"};
        String s15[] = new String[]{"ums_user_info.administration_position", "ums_administrative_job.n_job"};
        String s16[] = new String[]{"ums_legal_job.n_job", "ums_user_info.law_position"};
        String s17[] = new String[]{"ums_user_info.rank", "ums_rank_info.n_rank"};

        if (umscodeList.size() == 0) {
            umscodeList = umsCodeService.getReport();
        }

        List<Map<String, String>> maps;
        if (Arrays.asList(s5).contains(tk)) {
            maps = umscodeList.get(5);
        } else if (Arrays.asList(s11).contains(tk)) {
            maps = umscodeList.get(11);
        } else if (Arrays.asList(s13).contains(tk)) {
            maps = umscodeList.get(13);
        } else if (Arrays.asList(s15).contains(tk)) {
            maps = umscodeList.get(15);
        } else if (Arrays.asList(s16).contains(tk)) {
            maps = umscodeList.get(16);
        } else if (Arrays.asList(s17).contains(tk)) {
            maps = umscodeList.get(17);
        } else {
            return;
        }

        for (Map<String, String> map : maps) {
            if (map.get("t_id").equals(value)) {    //找到上报字段匹配的map
                p.put(key, map.get("s_id"));
                break;
            }
        }
    }
}
