package cn.net.withub.ums.webService.interior;


import cn.net.withub.ums.dao.BmFyNMapper;
import cn.net.withub.ums.dao.FtpRoleFyMapper;
import cn.net.withub.ums.dao.UmsUserInfoViewMapper;
import cn.net.withub.ums.entity.UmsUserInfoView;
import cn.net.withub.ums.entity.UmsUserRoleKey;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResultUtils;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.ws.rs.DefaultValue;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.text.SimpleDateFormat;
import java.util.*;

@WebService(name = "PersonService",endpointInterface = "cn.net.withub.ums.webService.interior.PersonService")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class PersonServiceImpl implements PersonService{

    @Resource
    private WebServiceContext context;

    @Autowired
    private FtpRoleFyMapper ftpRoleFyMapper;

    @Autowired
    private BmFyNMapper bmFyNMapper;

    @Autowired
    private UmsUserInfoViewMapper umsUserInfoViewMapper;

    public static Map<String,Map> fyInfo;


    /**
     * 统计员额法官人数
     * @param fydm 法院代码
     * @param org_code 部门
     * @return
     */
    @Override
    public Result yefgCount(String fydm, String org_code) {
        return authorityVerify(fydm,org_code,"AND user_type = 1 AND yefg = 1");
    }

    /**
     * 反查员额法官列表
     * @param pageSize 页大小
     * @param pageNum 页数
     * @param fydm 法院代码
     * @param org_code 部门
     * @return
     */
    @Override
    public Result yefgList(Integer pageSize, Integer pageNum, String fydm, String org_code) {
        return peggingList(pageSize,pageNum,fydm,org_code,"AND user_type = 1 AND yefg = 1");
    }

    /**
     * 统计法官助理人数
     * @param fydm 法院代码
     * @param org_code 部门
     * @return
     */
    @Override
    public Result fgzlCount(String fydm, String org_code) {
        return authorityVerify(fydm,org_code,"AND user_type = 1 AND personnel_classification = 22");
    }

    /**
     * 反查法官助理列表
     * @param pageSize 页大小
     * @param pageNum 页数
     * @param fydm 法院代码
     * @param org_code 部门
     * @return
     */
    @Override
    public Result fgzlList(Integer pageSize, Integer pageNum, String fydm, String org_code) {
        return peggingList(pageSize,pageNum,fydm,org_code,"AND user_type = 1 AND personnel_classification = 22");
    }

    /**
     * 统计书记员人数
     * @param fydm 法院代码
     * @param org_code 部门
     * @return
     */
    @Override
    public Result sjyCount(String fydm, String org_code) {
        return authorityVerify(fydm,org_code,"AND user_type = 1 AND personnel_classification = 23");
    }

    /**
     * 反查书记员列表
     * @param pageSize 页大小
     * @param pageNum 页数
     * @param fydm 法院代码
     * @param org_code 部门
     * @return
     */
    @Override
    public Result sjyList(Integer pageSize, Integer pageNum, String fydm, String org_code) {
        return peggingList(pageSize,pageNum,fydm,org_code,"AND user_type = 1 AND personnel_classification = 23");
    }

    /**
     * 统计审判员人数
     * @param fydm 法院代码
     * @param org_code 部门
     * @return
     */
    @Override
    public Result spyCount(String fydm, String org_code) {
        return authorityVerify(fydm,org_code,"AND user_type = 1 AND personnel_classification = 16");
    }

    /**
     * 反查审判员列表
     * @param pageSize 页大小
     * @param pageNum 页数
     * @param fydm 法院代码
     * @param org_code 部门
     * @return
     */
    @Override
    public Result spyList(Integer pageSize, Integer pageNum, String fydm, String org_code) {
        return peggingList(pageSize,pageNum,fydm,org_code,"AND user_type = 1 AND personnel_classification = 16");
    }

    /**
     * 统计助理审批员人数
     * @param fydm 法院代码
     * @param org_code 部门
     * @return
     */
    @Override
    public Result zlspyCount(String fydm, String org_code) {
        return authorityVerify(fydm,org_code,"AND user_type = 1 AND personnel_classification = 17 ");
    }

    /**
     * 反查助理审批员列表
     * @param pageSize 页大小
     * @param pageNum 页数
     * @param fydm 法院代码
     * @param org_code 部门
     * @return
     */
    @Override
    public Result zlspyList(Integer pageSize, Integer pageNum, String fydm, String org_code) {
        return peggingList(pageSize,pageNum,fydm,org_code,"AND user_type = 1 AND personnel_classification = 17 ");
    }

    /**
     * 统计司法行政人员人数
     * @param fydm 法院代码
     * @param org_code 部门
     * @return
     */
    @Override
    public Result sfxzryCount(String fydm, String org_code) {
        return authorityVerify(fydm,org_code,"AND user_type = 1 AND personnel_classification = 3  ");
    }

    /**
     * 反查司法行政人员列表
     * @param pageSize 页大小
     * @param pageNum 页数
     * @param fydm 法院代码
     * @param org_code 部门
     * @return
     */
    @Override
    public Result sfxzryList(Integer pageSize, Integer pageNum, String fydm, String org_code) {
        return peggingList(pageSize,pageNum,fydm,org_code,"AND user_type = 1 AND personnel_classification = 3  ");
    }

    /**
     * 统计临聘人员人数
     * @param fydm 法院代码
     * @param org_code 部门
     * @return
     */
    @Override
    public Result lpryCount(String fydm, String org_code) {
        return authorityVerify(fydm,org_code,"AND user_type = 2");
    }

    /**
     * 反查临聘人员列表
     * @param pageSize 页大小
     * @param pageNum 页数
     * @param fydm 法院代码
     * @param org_code 部门
     * @return
     */
    @Override
    public Result lpryList(Integer pageSize, Integer pageNum, String fydm, String org_code) {
        return peggingList(pageSize,pageNum,fydm,org_code,"AND user_type = 2");
    }

    /**
     * 根据人员id查详情
     * @param id 人员id
     * @return 查询结果
     */
    @Override
    public Result selectById(String id,String xtptId) {
        Result result = new Result();
        try{
            if(StringUtils.hasText(id) || StringUtils.hasText(xtptId)){
                PersonInfo personInfo = umsUserInfoViewMapper.selectById(id,xtptId);
                result.setCode(200);
                result.setData(JSON.toJSONString(personInfo));
            }else{
                result.setCode(500);
                result.setMessage("操作失败，请至少传入一个参数！");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setCode(500);
            result.setMessage("服务器出现未知错误，请联系系统管理员处理！");
        }
        return result;
    }

    /**
     * 查询所有人员列表
     * @param pageSize 页大小
     * @param pageNum 页数(从0开始)
     * @param fydm 法院代码
     * @param courtNo 法院编码
     * @param orgCode 部门
     * @param administrationPosition  行政职务编码
     * @param personnelClassification 人员分类编码
     * @return
     */
    @Override
    public Result selectAllList(Integer pageSize, Integer pageNum, String fydm,String courtNo, String orgCode,String deptNo, String administrationPosition, String personnelClassification, String yefg) {
        pageSize = pageSize == 0 ? 9999 : pageSize;
        //返回对象
        Result result  = new Result();
        try{
            List<PersonInfo> userInfoList = umsUserInfoViewMapper.selectAllList(pageNum*pageSize,pageSize,fydm,courtNo,orgCode,deptNo,administrationPosition,personnelClassification,yefg);
            if(userInfoList.size()>0){
                result.setCode(200);
                result.setData(JSON.toJSONString(userInfoList));
            }else{
                result.setCode(200);
                result.setData("");
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setCode(500);
            result.setMessage("服务器出现未知错误，请联系系统管理员处理！");
            return result;
        }
    }


    /**
     * 获取离职人员列表
     * @param fydm 法院代码 (默认所有)
     * @param startDate 开始时间(格式为: 年-月-日 的字符串 如:2021-01-01)
     * @param endDate 结束时间(格式为: 年-月-日 的字符串 如:2021-01-01)
     * @param pageNum 页数(开始页数为1)
     * @param pageSize 页大小
     * @return 查询结果
     */
    @Override
    public Result getLeaveList(String fydm, String startDate, String endDate, Integer pageNum, Integer pageSize) {
        pageSize = pageSize == 0 ? 9999 : pageSize;
        Result result = new Result();
        try{
            List<Map> list = umsUserInfoViewMapper.selectLeaveList(fydm,startDate,endDate,(pageNum-1)*pageSize,pageSize);
            result.setCode(200);
            result.setData(JSON.toJSONString(list));
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setCode(500);
            result.setMessage("服务器出现未知错误，请联系系统管理员处理！");
            return result;
        }
    }

    @Override
    public Result getDeptList(String fydm, String orgCode, String deptNo,String orgType) {
        Result result = new Result();
        try{
            List<Map> list = umsUserInfoViewMapper.getDeptList(fydm,orgCode,deptNo,orgType);
            result.setCode(200);
            result.setData(JSON.toJSONString(list));
        }catch (Exception e){
            e.printStackTrace();
            result.setCode(500);
            result.setMessage("服务器出现未知错误，请联系系统管理员处理！");
        }

        return result;

    }

    @Override
    public Result getPersonAndChangeJobList(Boolean isGetJob,String fydm, String orgCode, String deptNo, String personnelClassification, String startTime, String endTime) {
        Result result = new Result();
        isGetJob = isGetJob == null ? false : isGetJob;
        try{
            if(!isGetJob){
                return selectAllList(0,0,fydm,"",orgCode,deptNo,"",personnelClassification,"");
            }

            Map<String,Map> temporaryMap = new HashMap();
            List resultList = new ArrayList();
            List<Map> list = umsUserInfoViewMapper.getPersonAndChangeJobList(fydm,orgCode,deptNo,personnelClassification,startTime,endTime);
            for (Map map : list ) {
                Map jobMap = new HashMap();
                jobMap.put("old_court_text",map.get("old_court_text"));
                jobMap.put("new_court_code",map.get("new_court_code"));
                jobMap.put("new_court_text",map.get("new_court_text"));
                jobMap.put("old_dep_no",map.get("old_dep_no"));
                jobMap.put("old_dep_text",map.get("old_dep_text"));
                jobMap.put("new_dept_no",map.get("new_dept_no"));
                jobMap.put("new_dept_text",map.get("new_dept_text"));
                jobMap.put("new_dept_org_code",map.get("new_dept_org_code"));
                jobMap.put("old_court_code",map.get("old_court_code"));
                jobMap.put("old_org_code",map.get("old_org_code"));
                jobMap.put("update_time",map.get("update_time") != null ? new SimpleDateFormat("yyyy-MM-dd").format(map.get("update_time")) : null);
                if(temporaryMap.containsKey(map.get("id"))){
                    List jobList = (List) temporaryMap.get(map.get("id")).get("jobList");
                    jobList.add(jobMap);
                }else {
                    List jobList = new ArrayList();
                    jobList.add(jobMap);
                    map.put("jobList",jobList);
                    map.remove("old_court_text");
                    map.remove("new_court_code");
                    map.remove("new_court_text");
                    map.remove("old_dep_no");
                    map.remove("old_dep_text");
                    map.remove("new_dept_no");
                    map.remove("new_dept_text");
                    map.remove("new_dept_org_code");
                    map.remove("update_time");
                    map.remove("old_court_code");
                    map.remove("old_org_code");
                    temporaryMap.put(map.get("id").toString(),map);
                    resultList.add(map);
                }
            }
            result.setCode(200);
            result.setData(JSON.toJSONString(resultList));
        }catch (Exception e){
            e.printStackTrace();
            result.setCode(500);
            result.setMessage("服务器出现未知错误，请联系系统管理员处理！");
        }

        return result;
    }

    @Override
    public Result selectXzzw(String xtptId) {
        Result result = new Result();
        try {
            List<Map> list = umsUserInfoViewMapper.selectXzzw(xtptId);
            result.setCode(200);
            result.setData(JSON.toJSONString(list));
        }catch (Exception e){
            e.printStackTrace();
            result.setCode(500);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @Override
    public Result selectFgdjxx(String xtptId) {
        Result result = new Result();
        try {
            List<Map> list = umsUserInfoViewMapper.selectFgdjxx(xtptId);
            result.setCode(200);
            result.setData(JSON.toJSONString(list));
        }catch (Exception e){
            e.printStackTrace();
            result.setCode(500);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @Override
    public Result selectFgzldjxx(String xtptId) {
        Result result = new Result();
        try {
            List<Map> list = umsUserInfoViewMapper.selectFgzldjxx(xtptId);
            result.setCode(200);
            result.setData(JSON.toJSONString(list));
        }catch (Exception e){
            e.printStackTrace();
            result.setCode(500);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @Override
    public Result selectSjyDjxx(String xtptId) {
        Result result = new Result();
        try {
            List<Map> list = umsUserInfoViewMapper.selectSjyDjxx(xtptId);
            result.setCode(200);
            result.setData(JSON.toJSONString(list));
        }catch (Exception e){
            e.printStackTrace();
            result.setCode(500);
            result.setMessage(e.getMessage());
        }
        return result;
    }


    /**
     * 验证权限并统计人数
     * @param fydm 法院代码（支持多个，以逗号隔开）
     * @param org_code 部门
     * @param condition 查询条件
     * @return
     */
    public Result authorityVerify(String fydm,String org_code,String condition){
        //返回对象
        Result result  = new Result();
        try{
            //查询结果列表
            List<Map> list = new ArrayList<>();
            if(fyInfo==null){
                fyInfo = new HashMap<>();
                List<Map> fys = bmFyNMapper.selectAll();
                for (Map m : fys ) {
                    fyInfo.put(m.getOrDefault("court_code","").toString().toUpperCase(),m);
                }
            }

            MessageContext ctx = context.getMessageContext();

            FtpAuthUser ftpAuthUser = (FtpAuthUser) ctx.get("userInfo");


            String[] fydms = fydm.split(",");
            for (String fy : fydms ) {
                Map info = fyInfo.getOrDefault(fy.toUpperCase(),null);
                if(info == null){
                    result.setCode(500);
                    result.setMessage("未知法院代码”"+fy+"“");
                    return result;
                };
                int row = ftpRoleFyMapper.selectByRoleIdAndFydm(ftpAuthUser.getId(),fy);
                if(row<1){
                    result.setCode(500);
                    String dmms = info.getOrDefault("court_std_name","").toString();
                    result.setMessage("您没有权限访问"+ (StringUtils.hasText(dmms) ? dmms : fy) +"的数据！");
                    return result;
                }

                Integer count = umsUserInfoViewMapper.count(fy,condition,org_code);
                Map map = new HashMap();
                map.put("fydm",fy);
                map.put("count",count);
                list.add(map);
            }

            result.setCode(200);
            result.setData(JSON.toJSONString(list));
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setCode(500);
            result.setMessage("服务器出现未知错误，请联系系统管理员处理！");
            return result;
        }

    }

    /**
     * 封装反查
     * @param pageSize  页大小
     * @param pageNum   页数
     * @param fydm      法院代码
     * @param org_code  部门
     * @param condition 查询条件
     * @return 查询结果
     */
    private Result peggingList(Integer pageSize,Integer pageNum,String fydm,String org_code,String condition){
        pageSize = pageSize == 0 ? 9999 : pageSize;
        //返回对象
        Result result  = new Result();
        try{
            if(fyInfo==null){
                fyInfo = new HashMap<>();
                List<Map> fys = bmFyNMapper.selectAll();
                for (Map m : fys ) {
                    fyInfo.put(m.getOrDefault("court_code","").toString().toUpperCase(),m);
                }
            }

            if(fyInfo.getOrDefault(fydm.toUpperCase(),null)==null){
                result.setCode(500);
                result.setMessage("未知法院代码”"+fydm+"“");
                return result;
            };

            MessageContext ctx = context.getMessageContext();
            FtpAuthUser ftpAuthUser = (FtpAuthUser) ctx.get("userInfo");
            int row = ftpRoleFyMapper.selectByRoleIdAndFydm(ftpAuthUser.getId(),fydm);
            if(row<1){
                result.setCode(500);
                String dmms = fyInfo.getOrDefault(fydm,new HashMap()).getOrDefault("DMMS","").toString();
                result.setMessage("您没有权限访问"+dmms+"的数据！");
                return result;
            }
            List<PersonInfo> userInfoList = umsUserInfoViewMapper.selectByFydmAndCondition(pageSize,pageNum*pageSize,fydm,condition,org_code);
            if(userInfoList.size()>0){
                result.setCode(200);
                result.setData(JSON.toJSONString(userInfoList));
            }else{
                result.setCode(200);
                result.setData("");
            }


            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setCode(500);
            result.setMessage("服务器出现未知错误，请联系系统管理员处理！");
            return result;
        }

    }
}
