package cn.net.withub.ums.action.rmpsy;

import cn.net.withub.ums.common.UmsConstant;
import cn.net.withub.ums.dao.UmsRmpsyJbxxMapper;
import cn.net.withub.ums.dao.UmsUserInfoMapper;
import cn.net.withub.ums.dao.XtptTUserMapper;
import cn.net.withub.ums.entity.*;
import cn.net.withub.ums.service.UmsCodeService;
import cn.net.withub.ums.service.UmsCourtFullService;
import cn.net.withub.ums.service.UmsCourtService;
import cn.net.withub.ums.service.UmsUserInfoService;
import cn.net.withub.ums.util.EncodeDecodeDataInfo;
import cn.net.withub.ums.util.StringUtils;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/rmpsyAction")
@Results({
        @Result(name = "json", type = "json", params = {"root", "data"}),
        @Result(name = "stream", type = "stream", params = {"inputName", "inputStream", "contentDisposition", "attachment;filename=${filename}.xls",})
})
public class RmpsyAction {

    @Autowired
    private UmsRmpsyJbxxMapper umsRmpsyJbxxMapper;

    @Autowired
    private UmsCodeService codeService;

    @Autowired
    public UmsCourtFullService umsCourtFullService;

    @Autowired
    private XtptTUserMapper xtptTUserMapper;

    @Autowired
    private UmsUserInfoService userInfoService;

    @Autowired
    private UmsUserInfoMapper userInfoMapper;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UmsCourtService umsCourtService;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private ByteArrayInputStream inputStream;

    private Object data;

    private String filename;

    // ????????????????????????
    private UmsRmpsyJbxx umsRmpsyJbxx;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public UmsRmpsyJbxx getUmsRmpsyJbxx() {
        return umsRmpsyJbxx;
    }

    public void setUmsRmpsyJbxx(UmsRmpsyJbxx umsRmpsyJbxx) {
        this.umsRmpsyJbxx = umsRmpsyJbxx;
    }

    public ByteArrayInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(ByteArrayInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getFilename() throws UnsupportedEncodingException {
        return URLEncoder.encode(filename, "UTF-8");
    }

    public void setFilename(String filename) throws UnsupportedEncodingException {
        this.filename = new String(filename.getBytes("ISO8859-1"), "utf-8");
    }

    // ???????????????????????????
    @Action("/getList")
    public String getList() {

        ActionContext actionContext = ActionContext.getContext();
        HttpServletRequest httpServletRequest = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
        // ????????????
        UmsRmpsyJbxxExample umsRmpsyJbxxExample = zzcsUmsRmpsyJbxxExample(httpServletRequest);
        List<UmsRmpsyJbxx> umsRmpsyJbxxList = umsRmpsyJbxxMapper.selectByExample(umsRmpsyJbxxExample);
        int count = umsRmpsyJbxxMapper.countByExample(umsRmpsyJbxxExample);

        //????????????????????????????????????????????????????????????????????????????????????????????????
        if(umsRmpsyJbxxList != null && umsRmpsyJbxxList.size() > 0){
            for (UmsRmpsyJbxx rmpsyJbxx : umsRmpsyJbxxList) {
                EncodeDecodeDataInfo.deCodeDataForUmsRmpsy(rmpsyJbxx);
            }
        }

        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("success", true);
        returnMap.put("rows", umsRmpsyJbxxList);
        returnMap.put("results", count);
        data = returnMap;
        return "json";
    }

    @Action("/saveRmpsyInfo")
    public String saveRmpsyInfo() {

        try {
            ActionContext actionContext = ActionContext.getContext();
            HttpServletRequest httpServletRequest = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);

            // ????????????????????????????????????
            String saveType = httpServletRequest.getParameter("saveType");
            umsRmpsyJbxx.setUserno((int) (Math.random() * 1000));
            // sortNo?????????999
            umsRmpsyJbxx.setSortno(999);
            //?????????????????? courtNo
            if(umsRmpsyJbxx.getCourtno() == null){
                data = 0;
                return "json";
            }
            //???????????????????????????
            if(umsRmpsyJbxx.getCourtStdNo() == null){
                umsRmpsyJbxx.setCourtStdNo(umsCourtService.courtNo2CourtStdNo(umsRmpsyJbxx.getCourtno()));
            }
            if(StringUtils.isEmpty(umsRmpsyJbxx.getCourtcode())){
                umsRmpsyJbxx.setCourtcode(umsCourtService.courtNo2CourtCode(umsRmpsyJbxx.getCourtno()));
            }


            // ????????????scbj
            if (null == umsRmpsyJbxx.getScbj() || umsRmpsyJbxx.getScbj() == 0) {
                umsRmpsyJbxx.setScbj(0);
                umsRmpsyJbxx.setIsvalid(1);
            }

            //?????????????????????????????????????????????
            umsRmpsyJbxx.setIdcard(umsRmpsyJbxx.getIdcard().toUpperCase());
            //????????????????????????????????????????????????????????????????????????????????????????????????
            EncodeDecodeDataInfo.enCodeDataForUmsRmpsy(umsRmpsyJbxx);

            // ???????????????????????????????????????xtpt_t_user??? ????????????
            XtptTUser xtptTUser = tpXtptTUserForRmpsy(umsRmpsyJbxx);
            UmsUserInfo umsUserInfo = makeUmsUserInfoEntity(umsRmpsyJbxx);

            int count = 0;
            //sav1 ?????? sav2??????
            if ("sav1".equals(saveType)) {
                umsRmpsyJbxx.setAddtime(new Date());
                count = umsRmpsyJbxxMapper.insertSelective(umsRmpsyJbxx);
                // ??????xtpt_t_user???
                xtptTUserMapper.insertSelective(xtptTUser);
                // ????????? ums_user_info
                userInfoMapper.insertSelective(umsUserInfo);

            } else if ("sav2".equals(saveType)) {
                umsRmpsyJbxx.setUpdatetime(new Date());
                Integer scbj = umsRmpsyJbxx.getScbj();
                if (null != scbj && scbj == 1) {
                    // ???????????????,????????????
                    umsRmpsyJbxx.setIsvalid(0);
                    xtptTUser.setEnabled(false);
                }
                count = umsRmpsyJbxxMapper.updateByPrimaryKey(umsRmpsyJbxx);
                // ??????xtpt_t_user???
                XtptTUserExample xtptTUserExample = new XtptTUserExample();
                xtptTUserExample.createCriteria().andUuidEqualTo(umsRmpsyJbxx.getId());
                List<XtptTUser> xtptTUserList = xtptTUserMapper.selectByExample(xtptTUserExample);
                if (null != xtptTUserList && xtptTUserList.size() > 0) {
                    // ??????????????????
                    xtptTUser.setId(xtptTUserList.get(0).getId());
                    xtptTUserMapper.updateByPrimaryKeySelective(xtptTUser);
                } else {
                    // ??????????????????
                    xtptTUserMapper.insertSelective(xtptTUser);
                }
                // ????????? ums_user_info
                UmsUserInfo umsUserInfo_old = userInfoMapper.selectByPrimaryKey(umsRmpsyJbxx.getId());
                if(umsUserInfo_old != null){
                    // ??????????????????
                    umsUserInfo.setUserNo(umsUserInfo_old.getUserNo());
                    userInfoMapper.updateByPrimaryKey(umsUserInfo);
                }else{
                    // ??????????????????
                    userInfoMapper.insertSelective(umsUserInfo);
                }

            }

            data = count;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "json";
    }

    // ????????????????????????
    @Action("/tqy")
    public String tyq() {
        ActionContext actionContext = ActionContext.getContext();
        HttpServletRequest httpServletRequest = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
        String id = httpServletRequest.getParameter("id");
        String isvalid = httpServletRequest.getParameter("isvalid");
        String scbj = httpServletRequest.getParameter("scbj");
        UmsRmpsyJbxx umsRmpsyJbxx = new UmsRmpsyJbxx();
        umsRmpsyJbxx.setIsvalid(Integer.valueOf(isvalid));
        umsRmpsyJbxx.setScbj(Integer.valueOf(scbj));
        UmsRmpsyJbxxExample umsRmpsyJbxxExample = new UmsRmpsyJbxxExample();
        umsRmpsyJbxxExample.createCriteria().andIdEqualTo(id);
        int count = umsRmpsyJbxxMapper.updateByExampleSelective(umsRmpsyJbxx, umsRmpsyJbxxExample);

        // ??????xtpt_t_user???
        XtptTUserExample xtptTUserExample = new XtptTUserExample();
        xtptTUserExample.createCriteria().andUuidEqualTo(id);
        List<XtptTUser> xtptTUserList = xtptTUserMapper.selectByExample(xtptTUserExample);

        // ????????????????????????
        XtptTUser xtptTUser = new XtptTUser();
        if (isvalid.equals("1")) {
            xtptTUser.setEnabled(true);
            xtptTUser.setIsValid(1);
        } else {
            xtptTUser.setEnabled(false);
            xtptTUser.setIsValid(0);
        }
        xtptTUser.setUuid(id);
        xtptTUser.setId(xtptTUserList.get(0).getId());

        xtptTUserMapper.updateByPrimaryKeySelective(xtptTUser);

        //ums_user_info ??????
        try {
            UmsUserInfo umsUserInfo_old = userInfoMapper.selectByPrimaryKey(id);
//            UmsUserInfo info = new UmsUserInfo();
            if(umsUserInfo_old != null){
                // ??????????????????
//                info.setId(id);
                if (isvalid.equals("1")) {
                    umsUserInfo_old.setIsValid(1);
                } else {
                    umsUserInfo_old.setIsValid(0);
                }
                userInfoMapper.updateByPrimaryKey(umsUserInfo_old);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        data = count;
        return "json";
    }

    @Action("getWorkAreaList")
    public String getWorkAreaList() {


        return "json";
    }

    // ?????????????????????????????????Excel??????
    @Action("downloadExcelForRmpsy")
    public String downloadExcelForRmpsy() {
        ActionContext actionContext = ActionContext.getContext();
        HttpServletRequest httpServletRequest = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
        // ????????????
        UmsRmpsyJbxxExample umsRmpsyJbxxExample = zzcsUmsRmpsyJbxxExample(httpServletRequest);

        // ????????????????????????????????????Excel
        umsRmpsyJbxxExample.setOrderByClause(" addtime desc");
        // ????????????
        List<Map> mapList = umsRmpsyJbxxMapper.selectByExampleForMap(umsRmpsyJbxxExample);

        // ??????Excel
        byte[] bytes = generateExcel(mapList);

        this.inputStream = new ByteArrayInputStream(bytes);
        filename = "?????????????????????";
        return "stream";
    }

    // ?????????????????????
    @Action("deleteRmpsyAction")
    public String deleteRmpsyAction() {
        ActionContext actionContext = ActionContext.getContext();
        HttpServletRequest httpServletRequest = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
        String id = httpServletRequest.getParameter("id");
        UmsRmpsyJbxx umsRmpsyJbxxForDelete = new UmsRmpsyJbxx();
        umsRmpsyJbxxForDelete.setId(id);
        umsRmpsyJbxxForDelete.setScbj(1);
        umsRmpsyJbxxForDelete.setIsvalid(0);// ???????????????????????????
        int count = umsRmpsyJbxxMapper.updateByPrimaryKeySelective(umsRmpsyJbxxForDelete);
        data = count;
        return "json";
    }

    // ??????????????????????????????
    @Action("checkIdCard")
    public String checkIdCard() {
        ActionContext actionContext = ActionContext.getContext();
        HttpServletRequest httpServletRequest = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
        String id = httpServletRequest.getParameter("id"); // ??????id
        String idcard = httpServletRequest.getParameter("idcard");
        String saveType = httpServletRequest.getParameter("saveType");// ???????????????
        UmsRmpsyJbxxExample umsRmpsyJbxxExample = new UmsRmpsyJbxxExample();
        umsRmpsyJbxxExample.createCriteria().andIdcardEqualTo(idcard);
        List<UmsRmpsyJbxx> umsRmpsyJbxxList = umsRmpsyJbxxMapper.selectByExample(umsRmpsyJbxxExample);
        // ???????????????,?????????????????? //sav1 ?????? sav2??????
        if ("sav1".equalsIgnoreCase(saveType)) {

        } else if ("sav2".equalsIgnoreCase(saveType)) {
            if (null == umsRmpsyJbxxList || umsRmpsyJbxxList.size() == 0) {

            } else {
                if (id.equals(umsRmpsyJbxxList.get(0).getId())) {
                    umsRmpsyJbxxList = null;
                }
            }
        }
        data = umsRmpsyJbxxList;
        return "json";
    }

    public UmsRmpsyJbxxExample zzcsUmsRmpsyJbxxExample(HttpServletRequest httpServletRequest) {
        UmsRmpsyJbxxExample umsRmpsyJbxxExample = new UmsRmpsyJbxxExample();
        UmsRmpsyJbxxExample.Criteria criteria = umsRmpsyJbxxExample.createCriteria();
        String id = httpServletRequest.getParameter("id");
        String name = httpServletRequest.getParameter("name");
        String gender = httpServletRequest.getParameter("gender");
        String courtno = httpServletRequest.getParameter("courtno");
        Date birthday = null;
        try {
            birthday = httpServletRequest.getParameter("birthday") == null ? null : simpleDateFormat.parse(httpServletRequest.getParameter("birthday").toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String idcard = httpServletRequest.getParameter("idcard");
        String nation = httpServletRequest.getParameter("nation");
        String education = httpServletRequest.getParameter("education");
        String political = httpServletRequest.getParameter("political");
        String homeadress = httpServletRequest.getParameter("homeadress");
        String fixedtel = httpServletRequest.getParameter("fixedtel");
        String phonenum = httpServletRequest.getParameter("phonenum");
        String email = httpServletRequest.getParameter("email =");
        String photo = httpServletRequest.getParameter("photo");
        String areadistribution = httpServletRequest.getParameter("areaDistribution");
        String occupation = httpServletRequest.getParameter("occupation");
        String workcompany = httpServletRequest.getParameter("workcompany");
        String workarea = httpServletRequest.getParameter("workarea");
        String zytc = httpServletRequest.getParameter("zytc");
        Date rqkssj = null;
        try {
            rqkssj = httpServletRequest.getParameter("rqkssj") == null ? null : simpleDateFormat.parse(httpServletRequest.getParameter("rqkssj"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String sfxz = httpServletRequest.getParameter("sfxz");
        String js = httpServletRequest.getParameter("js =");
        String cyajxz = httpServletRequest.getParameter("cyajxz");
        String cyajxza = httpServletRequest.getParameter("cyajxza");
        String cyajxzb = httpServletRequest.getParameter("cyajxzb");
        String ndcscs = httpServletRequest.getParameter("ndcscs");
        String psyzt = httpServletRequest.getParameter("psyzt");
        String tcfs = httpServletRequest.getParameter("tcfs = httpServletRequest");
        String sfzlx = httpServletRequest.getParameter("sfzlx");
        String jgid = httpServletRequest.getParameter("jgid");
        Integer scbj = httpServletRequest.getParameter("scbj") == null ? null : Integer.valueOf(httpServletRequest.getParameter("scbj"));
        Integer sfdjcsxx = httpServletRequest.getParameter("sfdjcsxx") == null ? null : Integer.valueOf(httpServletRequest.getParameter("sfdjcsxx"));
        String start = httpServletRequest.getParameter("start");
        String limit = httpServletRequest.getParameter("limit");
        String isvalid = httpServletRequest.getParameter("isvalid");
        String sfty = httpServletRequest.getParameter("sfty");

        if (StringUtils.isNotEmpty(id)) {

        }
        if (StringUtils.isNotEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        if (StringUtils.isNotEmpty(gender)) {
            criteria.andGenderEqualTo(gender);
        }
        if (null != birthday) {

        }
        if (StringUtils.isNotEmpty(idcard)) {

        }
        if (StringUtils.isNotEmpty(nation)) {
            criteria.andNationEqualTo(nation);
        }
        if (StringUtils.isNotEmpty(education)) {
            criteria.andEducationEqualTo(education);
        }
        if (StringUtils.isNotEmpty(political)) {
            criteria.andPoliticalEqualTo(political);
        }
        if (StringUtils.isNotEmpty(homeadress)) {

        }
        if (StringUtils.isNotEmpty(fixedtel)) {

        }
        if (StringUtils.isNotEmpty(phonenum)) {

        }
        if (StringUtils.isNotEmpty(email)) {

        }
        if (StringUtils.isNotEmpty(photo)) {

        }
        if (StringUtils.isNotEmpty(areadistribution)) {
            criteria.andAreadistributionEqualTo(areadistribution);
        }
        if (StringUtils.isNotEmpty(occupation)) {
            criteria.andOccupationEqualTo(occupation);
        }
        if (StringUtils.isNotEmpty(workcompany)) {

        }
        if (StringUtils.isNotEmpty(workarea)) {

        }
        if (StringUtils.isNotEmpty(zytc)) {

        }
        if (null != rqkssj) {

        }
        if (StringUtils.isNotEmpty(sfxz)) {

        }
        if (StringUtils.isNotEmpty(js)) {

        }
        if (StringUtils.isNotEmpty(cyajxz)) {

        }
        if (StringUtils.isNotEmpty(cyajxza)) {

        }
        if (StringUtils.isNotEmpty(cyajxzb)) {

        }
        if (StringUtils.isNotEmpty(ndcscs)) {

        }
        if (StringUtils.isNotEmpty(psyzt)) {
            criteria.andPsyztEqualTo(psyzt);
        }
        if (StringUtils.isNotEmpty(tcfs)) {

        }
        if (StringUtils.isNotEmpty(sfzlx)) {

        }
        if (StringUtils.isNotEmpty(jgid)) {

        }
        if (StringUtils.isNotEmpty(scbj)) {

        }
        if (StringUtils.isNotEmpty(sfdjcsxx)) {

        }
        if (StringUtils.isNotEmpty(courtno)) {
            criteria.andCourtnoEqualTo(Integer.valueOf(courtno));
        }
        // ????????????0??????????????? 0???????????? 1????????????
//        criteria.andScbjEqualTo(0);
//        if (StringUtils.isNotEmpty(isvalid)) {
//            criteria.andIsvalidEqualTo(Integer.valueOf(isvalid));
//        }
        if (StringUtils.isNotEmpty(sfty)) {
            if (sfty.equalsIgnoreCase("1")) {
                criteria.andIsvalidEqualTo(0);
            } else if (sfty.equalsIgnoreCase("0")) {
                criteria.andIsvalidEqualTo(1);
            }
        }

        if (StringUtils.isNotEmpty(start) && StringUtils.isNotEmpty(limit)) {
            umsRmpsyJbxxExample.setOrderByClause(" id limit " + start + ", " + limit);
        }

        return umsRmpsyJbxxExample;
    }

    /**
     * ?????????????????????Excel??????
     *
     * @param mapList
     * @return
     */
    private byte[] generateExcel(List<Map> mapList) {

        // gender????????????typeId
        List<UmsCourtFull> umsCourtFullList = umsCourtFullService.selectByListAll();
        List<UmsCode> typeId_gender = codeService.selectCodesByType(3);
        List<UmsCode> typeId_nation = codeService.selectCodesByType(1005);
        List<UmsCode> typeId_education = codeService.selectCodesByType(119);
        List<UmsCode> typeId_political = codeService.selectCodesByType(120);
        List<UmsCode> typeId_areadistribution = codeService.selectCodesByType(122);
        List<UmsCode> typeId_occupation = codeService.selectCodesByType(121);
        List<UmsCode> typeId_psyzt = codeService.selectCodesByType(111);

        // ??????
        String title_entity[] = {"name", "courtNo", "idcard", "gender", "nation", "education", "political", "areaDistribution", "occupation", "psyzt"};
        String title[] = {"??????", "????????????", "????????????", "??????", "??????", "??????", "????????????", "????????????", "??????", "???????????????"};

        // ??????Worksheet????????????sheet????????????xls????????????????????????)
        HSSFWorkbook workbook = new HSSFWorkbook();
        String sheetName = "?????????????????????";

        // Excel??????
        HSSFSheet sheet = workbook.createSheet(sheetName);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //????????????
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //????????????
        style.setWrapText(true); //????????????
        HSSFCellStyle dateStyle = workbook.createCellStyle();
        dateStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        dateStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFDataFormat format = workbook.createDataFormat();
        dateStyle.setDataFormat(format.getFormat("yyyy-MM-dd"));
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFDataFormat format_ = workbook.createDataFormat();
        cellStyle.setWrapText(true); //????????????
        cellStyle.setDataFormat(format_.getFormat("@"));
        sheet.setDefaultColumnWidth(22);
        sheet.setDefaultRowHeight((short) (68 * 20));

        // ????????????
        int row_id = 0;
        byte[] bytes = null;
        FileOutputStream fileOutputStream;
        try {
            // ???????????????
            HSSFRow row_title = workbook.getSheet(sheetName).createRow(row_id++);
            row_title.setHeightInPoints(25);
            for (int i = 0; i < title.length; i++) {
                HSSFCell cell = row_title.createCell(i);
                cell.setCellValue(title[i]);
                cell.setCellStyle(style);
            }

            // ?????????????????????
            for (Map map : mapList) {

                // Excel??????
                HSSFRow row = workbook.getSheet(sheetName).createRow(row_id++);
                row.setHeightInPoints(20);

                // ????????????
                for (int i = 0; i < title_entity.length; i++) {
                    HSSFCell cell_ = row.createCell(i);
                    // ??????
                    String zd = title_entity[i];
                    Object obj = map.get(zd);
                    switch (zd) {
                        case "courtNo":
                            for (UmsCourtFull umsCourtFull : umsCourtFullList) {
                                if (null != obj && obj.toString().equals(umsCourtFull.getCourtNo().toString())) {
                                    obj = umsCourtFull.getCourtStdName();
                                    break;
                                }
                            }
                            break;
                        case "gender":
                            for (UmsCode umsCode : typeId_gender) {
                                if (null != obj && obj.toString().equals(umsCode.getId())) {
                                    obj = umsCode.getCodeName();
                                    break;
                                }
                            }
                            break;
                        case "nation":
                            for (UmsCode umsCode : typeId_nation) {
                                if (null != obj && obj.toString().equals(umsCode.getId())) {
                                    obj = umsCode.getCodeName();
                                    break;
                                }
                            }
                            break;
                        case "education":
                            for (UmsCode umsCode : typeId_education) {
                                if (null != obj && obj.toString().equals(umsCode.getId())) {
                                    obj = umsCode.getCodeName();
                                    break;
                                }
                            }
                            break;
                        case "political":
                            for (UmsCode umsCode : typeId_political) {
                                if (null != obj && obj.toString().equals(umsCode.getId())) {
                                    obj = umsCode.getCodeName();
                                    break;
                                }
                            }
                            break;
                        case "areaDistribution":
                            for (UmsCode umsCode : typeId_areadistribution) {
                                if (null != obj && obj.toString().equals(umsCode.getId())) {
                                    obj = umsCode.getCodeName();
                                    break;
                                }
                            }
                            break;
                        case "occupation":
                            for (UmsCode umsCode : typeId_occupation) {
                                if (null != obj && obj.toString().equals(umsCode.getId())) {
                                    obj = umsCode.getCodeName();
                                    break;
                                }
                            }
                            break;
                        case "psyzt":
                            for (UmsCode umsCode : typeId_psyzt) {
                                if (null != obj && obj.toString().equals(umsCode.getId())) {
                                    obj = umsCode.getCodeName();
                                    break;
                                }
                            }
                            break;
                        default:
                            break;
                    }
                    if (obj instanceof String) {
                        cell_.setCellValue((String) obj);
                        cell_.setCellStyle(cellStyle);
                    } else if (obj instanceof Integer) {
                        cell_.setCellValue((Integer) obj);
                        cell_.setCellStyle(style);
                    } else if (obj instanceof Date) {
                        cell_.setCellValue((Date) obj);
                        cell_.setCellStyle(dateStyle);
                    }
                }
            }

            // ?????????????????????
            File tempFile = File.createTempFile(UUID.randomUUID().toString(), ".xls");
            fileOutputStream = new FileOutputStream(tempFile);
            workbook.write(fileOutputStream);
            bytes = FileUtils.readFileToByteArray(tempFile);

            // ??????????????????
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            tempFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }

    // ?????????????????????xtpt_t_user
    public XtptTUser tpXtptTUserForRmpsy(UmsRmpsyJbxx umsRmpsyJbxx) {

        XtptTUser user_ = new XtptTUser();

        // xtpt_t_user?????????uuid??????ums_rmpsy_jbxx??????id
        user_.setUuid(umsRmpsyJbxx.getId());
        // ????????????
        user_.setUserType(3);
        // ??????
        user_.setFullname(umsRmpsyJbxx.getName());
        // ??????
        if (umsRmpsyJbxx.getGender() != null) {
            user_.setGender(Integer.valueOf(umsRmpsyJbxx.getGender()) == 1);
        }
        user_.setBirthday(umsRmpsyJbxx.getBirthday());
        user_.setCourtCode(umsRmpsyJbxx.getCourtcode());
        user_.setCourtNo(umsRmpsyJbxx.getCourtno());
        user_.setIdcard(umsRmpsyJbxx.getIdcard());
        user_.setEducationBackground(Integer.valueOf(umsRmpsyJbxx.getEducation()));
        user_.setUpdateTime(new Date());
        user_.setNationReport(umsRmpsyJbxx.getNation());
        user_.setPoliticalReport(umsRmpsyJbxx.getPolitical());
        // ????????????
        user_.setPhone(umsRmpsyJbxx.getPhonenum());
        // ????????????
        user_.setOfficePhone(umsRmpsyJbxx.getFixedtel());

        // ?????????????????????, ??????????????????????????????9999
        user_.setSortNo(9999);

        // ???????????????????????? ???????????????????????????,?????????????????????????????????
        user_.setDeptOrgCode("998");
        // ?????????????????????
        user_.setDeptNo(999);

        // ????????? ??????????????????????????????????????????????????????+??????????????????
        user_.setUsername(umsRmpsyJbxx.getName() + umsRmpsyJbxx.getIdcard().substring((umsRmpsyJbxx.getIdcard().length() - 6)));
        // ?????? ??????????????????????????? admin
        user_.setPassword("21232f297a57a5a743894a0e4a801fc3");
        // ??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
        user_.setSalt(null);

        // ?????????????????????
        UmsUserInfoView umsUserInfoView = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
        // ???????????? ???id
        user_.setUpdateUser(umsUserInfoView.getId());
        // ????????????
        user_.setIsValid(umsRmpsyJbxx.getIsvalid());

        // ??????
        user_.setHometown(null);
        // ?????????
        user_.setBirthplace(null);
        // ??????????????????
        user_.setCourtStdNo(umsRmpsyJbxx.getCourtStdNo());
        // ?????????
        user_.setFormerName(null);
        // ????????????
        user_.setAdministrationPositionReport(null);
        // ????????????
        user_.setLawPositionReport(null);

        // ???????????????
        Integer isvalid = umsRmpsyJbxx.getIsvalid();
        if(isvalid != null && isvalid == 1){
            user_.setEnabled(true);
            user_.setIsValid(1);
        }else{
            user_.setEnabled(false);
            user_.setIsValid(0);
        }

        user_.setAccountNonExpired(true);
        user_.setAccountNonLocked(true);
        user_.setCredentialsNonExpired(true);

        return user_;
    }

    // ????????? ums_user_info
    public UmsUserInfo makeUmsUserInfoEntity(UmsRmpsyJbxx jbxx) {

        UmsUserInfo info = new UmsUserInfo();

        info.setId(jbxx.getId());

        info.setIsValid(jbxx.getIsvalid());
        info.setPhoneNumber(jbxx.getPhonenum());
        info.setExtOfficePhone(jbxx.getFixedtel());

        info.setExtAddress(jbxx.getHomeadress());
        info.setFullname(jbxx.getName());
        try {
            info.setGender(Integer.parseInt(jbxx.getGender()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        info.setIdcard(jbxx.getIdcard());
        //  ?????????????????? UserNo???????????????
        info.setUserNo((int) (Math.random() * -1000));
        info.setCourtNo(jbxx.getCourtno() != null ? jbxx.getCourtno() : - 1);
        //?????????
        info.setUserType(3);
        info.setUpdateTime(new Date());
        info.setCourtStdNo(jbxx.getCourtStdNo());
        info.setCourtCode(jbxx.getCourtcode());

        return info;
    }

    //????????????
    @Action("/fixData")
    public String fixData() {

        try {

            String sql = "  SELECT id from ums_rmpsy_jbxx a WHERE\n" +
                    "\t\tnot EXISTS (\n" +
                    "\tSELECT 1 from ums_user_info_view b WHERE\n" +
                    "\ta.id = b.id\n" +
                    "\t)  " ;

            int c = 0;

            List<String> list = jdbcTemplate.queryForList(sql, String.class);
            if(list != null && list.size() > 0){
                for (String id : list) {
                    if(org.springframework.util.StringUtils.isEmpty(id)){
                        continue;
                    }

                    UmsRmpsyJbxx umsRmpsyJbxx = umsRmpsyJbxxMapper.selectByPrimaryKey(id);
                    UmsUserInfo umsUserInfo = makeUmsUserInfoEntity(umsRmpsyJbxx);

                    // ????????? ums_user_info
                    UmsUserInfo umsUserInfo_old = userInfoMapper.selectByPrimaryKey(id);
                    if(umsUserInfo_old != null){
                        // ??????????????????
                        umsUserInfo.setUserNo(umsUserInfo_old.getUserNo());
                        userInfoMapper.updateByPrimaryKey(umsUserInfo);
                    }else{
                        // ??????????????????
                        userInfoMapper.insertSelective(umsUserInfo);
                    }
                    c++;
                }


            }

            data = c;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "json";
    }

}