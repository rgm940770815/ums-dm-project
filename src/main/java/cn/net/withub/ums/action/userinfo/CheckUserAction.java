package cn.net.withub.ums.action.userinfo;


import cn.net.withub.ums.common.UmsConstant;
import cn.net.withub.ums.dao.UmsCodeMapper;
import cn.net.withub.ums.entity.*;
import cn.net.withub.ums.service.*;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/check")
@Results({
        @Result(name = "json", type = "json", params = {"root", "data"})
})
public class CheckUserAction {

    private static final Map<Integer, String> check_query_type = new HashMap<Integer, String>() {
        {
            put(1, "基本信息检查");
            put(2, "子集信息检查");
            put(3, "基本信息有信息,子集信息没有对应的信息");
            put(4, "子集信息有信息,基本信息对应的信息点为空");
        }
    };

    private static final Map<Integer, String> generate_sql_type = new HashMap<Integer, String>() {
        {
            put(1, "为空");
            put(2, "有值");
            put(3, "等于");
            put(4, "包含于");
            put(5, "不在编码中");
        }
    };


    @Autowired
    UmsCodeMapper umsCodeMapper;

    @Autowired
    UmsCodeService umsCodeService;

    @Autowired
    UmsFieldContrastService fieldContrastService;

    @Autowired
    UmsFieldCodeService fieldCodeService;

    @Autowired
    UmsCheckInfoDetailService detailService;

    @Autowired
    UmsCheckInfoConfigService configService;

    private Object data;

    private UmsCheckInfoConfig infoConfig;

    private UmsCheckInfoDetail infoDetail;

    private List<UmsCheckInfoDetail> details = new ArrayList<>();

    private Integer start;
    private Integer limit;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public UmsCheckInfoConfig getInfoConfig() {
        return infoConfig;
    }

    public void setInfoConfig(UmsCheckInfoConfig infoConfig) {
        this.infoConfig = infoConfig;
    }

    public UmsCheckInfoDetail getInfoDetail() {
        return infoDetail;
    }

    public void setInfoDetail(UmsCheckInfoDetail infoDetail) {
        this.infoDetail = infoDetail;
    }

    public List<UmsCheckInfoDetail> getDetails() {
        return details;
    }

    public void setDetails(List<UmsCheckInfoDetail> details) {
        this.details = details;
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

    @Action("checkQueryType")
    public String checkQueryType(){
        data = check_query_type;
        return "json";
    }

    @Action("checkField")
    public String checkField(){

        try {

            if(infoConfig == null || infoConfig.getQueryType() == null){
                return "json";
            }

            //要排除的字段
            List<String> ExcludeColumn = Arrays.asList("id","user_id");

            switch (infoConfig.getQueryType()){
                case 1:
                    String tableName = "ums_user_info" ;
                    UmsFieldContrastExample example = new UmsFieldContrastExample();
                    example.createCriteria().andSourceTableEqualTo(tableName);
                    example.setOrderByClause(" sort_no ");
                    // UmsFieldContrast里面的字段
                    List<UmsFieldContrast> umsFieldContrasts = fieldContrastService.selectByExample(example);

                    //表的字段
                    List<String> tableColumn = umsCodeMapper.getTableColumn(tableName);

                    tableColumn.removeAll(ExcludeColumn);


                    List<UmsFieldContrast> collect = umsFieldContrasts.stream().filter(umsFieldContrast -> StringUtils.hasText(umsFieldContrast.getSourceField())
                            && tableColumn.contains(umsFieldContrast.getSourceField())).collect(Collectors.toList());
                    data = collect;
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "json";
    }

    @Action("hasFieldCode")
    public String hasFieldCode(){

        Map<String ,Object> returnMap = new HashMap<>();
        data = returnMap;
        boolean flag = false;
        List<Integer> allow = Arrays.asList(1, 2);
        if(infoDetail == null || !StringUtils.hasText(infoDetail.getFieldName()) || !StringUtils.hasText(infoDetail.getTableName()) ){
            Map<Integer,String> re = new HashMap<>();
            for (Integer integer : generate_sql_type.keySet()) {
                if(allow.contains(integer)){
                    re.put(integer,generate_sql_type.get(integer));
                }
            }
            returnMap.put("sqlType" , re);
            return "json";
        }

        try {
            UmsFieldCodeExample umsFieldCodeExample = new UmsFieldCodeExample();
            umsFieldCodeExample.createCriteria().andTableNameEqualTo(infoDetail.getTableName()).andFieldValueEqualTo(infoDetail.getFieldName());
            List<UmsFieldCode> umsFieldCodes = fieldCodeService.selectByExample(umsFieldCodeExample);
            if(umsFieldCodes.size() == 1){

                UmsFieldCode umsFieldCode = umsFieldCodes.get(0);
                String fieldTypeCode = umsFieldCode.getFieldTypeCode();
                List<UmsCode> umsCodes = umsCodeService.selectCodesByType(Integer.parseInt(fieldTypeCode));
                if(umsCodes.size() > 0){
                    flag = true;
                    returnMap.put("code",umsCodes);
                    returnMap.put("codeType",fieldTypeCode);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(flag){

            returnMap.put("sqlType" , generate_sql_type);

        }else{
            Map<Integer,String> re = new HashMap<>();
            for (Integer integer : generate_sql_type.keySet()) {
                if(allow.contains(integer)){
                    re.put(integer,generate_sql_type.get(integer));
                }
            }
            returnMap.put("sqlType" , re);

        }


        return "json";
    }

    @Action("insert")
    public String insert(){

        Map<String,Object> returnMap = new HashMap<>();
        data = returnMap;
        returnMap.put("success",false);

        try {

            if(infoConfig == null || details == null || details.size() == 0){
                return "json";
            }

            String id = infoConfig.getId();

            //有id说明有记录要删除之前的信息
            boolean flag = false;
            if(StringUtils.hasText(id)){
                flag = true;
                //删除
                UmsCheckInfoDetailExample example = new UmsCheckInfoDetailExample();
                example.createCriteria().andUnionIdEqualTo(id);
                int i = detailService.deleteByExample(example);
                System.out.println(i);

            }else{
                id = UUID.randomUUID().toString();
                infoConfig.setId(id);
            }


            int sort = 0;
            for (UmsCheckInfoDetail detail : details) {
                detail.setUnionId(id);
                detail.setSortNo(sort++);
                detailService.insert(detail);

            }


            UmsUserInfoView user = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());

            infoConfig.setCreateTime(new Date());
            infoConfig.setCreator(user.getFullname());
            infoConfig.setCreatorId(user.getUserId());
            int insert = 0;

            if(flag){
                insert = configService.updateByPrimaryKeySelective(infoConfig);

            }else{
                insert = configService.insert(infoConfig);

            }


            if(insert > 0){
                returnMap.put("success",true);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "json";
    }


    @Action("query")
    public String query(){

        Map<String,Object> re = new HashMap<>();
        re.put("rows",new ArrayList<>());
        re.put("results",0);
        data = re;

        try {
            UmsCheckInfoConfigExample example = new UmsCheckInfoConfigExample();
            example.setOffset(start);
            example.setLimit(limit);
            example.setOrderByClause(" create_time desc");
            List<UmsCheckInfoConfig> umsCheckInfoConfigs = configService.selectByExample(example);
            int i = configService.countByExample(example);
            re.put("rows",umsCheckInfoConfigs);
            re.put("results",i);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "json";
    }

    @Action("queryCondition")
    public String queryCondition(){

        Map<String,Object> re = new HashMap<>();
        re.put("rows",new ArrayList<>());
        data = re;

        try {

            if(infoConfig == null || !StringUtils.hasText(infoConfig.getId()) ){
                return "json";
            }

            UmsCheckInfoDetailExample example = new UmsCheckInfoDetailExample();
            example.createCriteria().andUnionIdEqualTo(infoConfig.getId());
            example.setOrderByClause(" sort_no ");
            List<UmsCheckInfoDetail> umsCheckInfoDetails = detailService.selectByExample(example);
            re.put("rows",umsCheckInfoDetails);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "json";
    }

}
