/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.action.userinfo;

import cn.net.withub.ums.entity.UmsUserInfoView;
import cn.net.withub.ums.service.UmsAttachedTableService;
import cn.net.withub.ums.service.UmsUserInfoAttachedViewsService;
import cn.net.withub.ums.service.UmsUserInfoViewService;
import org.apache.ibatis.session.RowBounds;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.net.withub.ums.util.StringTools.isNullOrEmpty;

/**
 * @author Diluka
 */
@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/view/userinfo")
@Results({
        @Result(name = "json", type = "json", params = {"root", "data"}),
        @Result(name = "empty", location = "/board/personnel/info/empty_data.jsp"),
        @Result(name = "page", location = "/board/personnel/info/userinfo/${viewName}.jsp")
})
public class UserInfoAttachedViewsAction {

    @Autowired
    private UmsUserInfoAttachedViewsService attachedViewsService;

    @Autowired
    private UmsAttachedTableService attachedTableService;

    @Autowired
    private UmsUserInfoViewService userInfoViewService;

    private String viewName;
    private String userId;

    private Integer start;
    private Integer limit;

    private String field;
    private String direction;

    private Object data;

    private String where;

    private Map<String, String> otherParam = new HashMap<>();

    @Action("attachment")
    @Deprecated
    public String attachedView() {
        Map<String, Object> map = new HashMap<>();
        data = map;

        RowBounds rowBounds;
        if (start != null && limit != null) {
            rowBounds = new RowBounds(start, limit);
        } else {
            rowBounds = RowBounds.DEFAULT;
        }

        List list = attachedViewsService.attachedView(viewName, userId, rowBounds);
        int total = attachedViewsService.attachedCount(viewName, userId);

        if (!isNullOrEmpty(viewName) && !isNullOrEmpty(userId)) {
            map.put("success", list != null);
            map.put("rows", list);
            map.put("results", total);
        }

        return "json";
    }

    @Action("attachment2")
    public String attachedView2() {
        Map<String, Object> map = new HashMap<>();
        data = map;
        System.out.println(viewName + "-------------");
        System.out.println(userId + "-------------");
        System.out.println(field + "-------------");
        System.out.println(direction + "-------------");
        RowBounds rowBounds;
        if (start != null && limit != null) {
            rowBounds = new RowBounds(start, limit);
        } else {
            rowBounds = RowBounds.DEFAULT;
        }

        if (!isNullOrEmpty(viewName) && !isNullOrEmpty(userId)) {
            String order = "";
            if (!isNullOrEmpty(field)) {
                order += field;
            }
            if (!isNullOrEmpty(direction)) {
                order += "." + direction;
            }
            List list = attachedTableService.selectView1(viewName, userId, order, otherParam, rowBounds);
            int total = attachedTableService.countView1(viewName, userId, otherParam);
            map.put("success", list != null);
            map.put("rows", list);
            map.put("results", total);
        } else {
            map.put("success", false);
        }
        return "json";
    }

    @Action("attachmentBatch")
    public String attachedViewBatch() {

        Map<String, Object> map = new HashMap<>();
        data = map;
        RowBounds rowBounds;
        if (start != null && limit != null) {
            rowBounds = new RowBounds(start, limit);
        } else {
            rowBounds = RowBounds.DEFAULT;
        }

        if (!isNullOrEmpty(viewName)) {
            viewName = viewName.substring(0, viewName.length() - 6);
            String order = "";
            if (!isNullOrEmpty(field)) {
                order += field;
            }
            if (!isNullOrEmpty(direction)) {
                order += "." + direction;
            }
            List list = attachedTableService.selectViewBatch(viewName, order, rowBounds);
            int total = attachedTableService.countViewBatch(viewName);

            map.put("success", list != null);
            map.put("rows", list);
            map.put("results", total);
        } else {
            map.put("success", false);
        }
        return "json";
    }

    @Action("attachmentAllBatch")
    public String attachmentAllBatch() {
        Map<String, Object> map = new HashMap<>();
        data = map;
        viewName = viewName.substring(0, viewName.length() - 6);
        if (!isNullOrEmpty(viewName)) {
            String order = "";
            if (!isNullOrEmpty(field)) {
                order += field;
            }
            if (!isNullOrEmpty(direction)) {
                order += "." + direction;
            }
            List list = attachedTableService.selectViewAllBatch(viewName, order, where);
            map.put("success", list != null);
            map.put("rows", list);
        } else {
            map.put("success", false);
        }
        return "json";
    }

    @Action("page")
    public String attachedViewPage() {
        UmsUserInfoView umsUserInfoView = userInfoViewService.selectById(userId);
        if(umsUserInfoView == null){
            return "empty";
        }

        return "page";
    }

    //    ums_political_info //????????????
//            ums_administrative_job //????????????
//    ums_legal_job //????????????
//            ums_rank_info //????????????
//    ums_parttime_position //????????????
//            ums_level_info //????????????
//    ums_civil_servant_level //???????????????
//            ums_education_info //????????????
//    ums_degree_info //????????????
//            ums_study_info //????????????
//    ums_training_info //????????????
//            ums_resume_info //????????????
//    ums_family_info  //????????????
//            ums_assess_info //????????????
//    ums_reward_info //????????????
//            ums_punish_info  //????????????
//    ums_abroad_info //????????????
//            ums_foreign_lang //????????????
//    ums_exchange_info //????????????
//            ums_judicial_exam  //????????????
//    ums_pro_technical_position //??????????????????
//            ums_casualty_info //????????????
//    ums_remark  //????????????
//            ums_audio_video //????????????
//    ums_wage_info  //????????????
//            ums_reserve_cadre //????????????
//    ums_contact ????????????
//    ums_leadership ????????????
    public static final List<String> AttachmentTable = Arrays.asList("ums_political_info", "ums_administrative_job",
            "ums_legal_job", "ums_rank_info", "ums_parttime_position", "ums_level_info", "ums_civil_servant_level", "ums_education_info", "ums_degree_info",
            "ums_study_info", "ums_training_info", "ums_resume_info", "ums_family_info", "ums_assess_info", "ums_reward_info", "ums_punish_info", "ums_abroad_info",
            "ums_foreign_lang", "ums_exchange_info", "ums_judicial_exam", "ums_pro_technical_position", "ums_casualty_info", "ums_remark", "ums_audio_video", "ums_wage_info",
            "ums_reserve_cadre", "ums_contact", "ums_leadership");

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public UmsUserInfoAttachedViewsService getAttachedViewsService() {
        return attachedViewsService;
    }

    public void setAttachedViewsService(UmsUserInfoAttachedViewsService attachedViewsService) {
        this.attachedViewsService = attachedViewsService;
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

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public Map<String, String> getOtherParam() {
        return otherParam;
    }

    public void setOtherParam(Map<String, String> otherParam) {
        this.otherParam = otherParam;
    }
}
