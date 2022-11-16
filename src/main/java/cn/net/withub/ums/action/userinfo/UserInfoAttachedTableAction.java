/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.action.userinfo;

import cn.net.withub.ums.action.rmpsSync.RmpsyDataSync;
import cn.net.withub.ums.common.UmsConstant;
import cn.net.withub.ums.entity.UmsLog;
import cn.net.withub.ums.entity.UmsUserInfo;
import cn.net.withub.ums.entity.UmsUserInfoView;
import cn.net.withub.ums.entity.factory.EntityFactory;
import cn.net.withub.ums.log.UmsLogger;
import cn.net.withub.ums.service.UmsAttachedTableService;
import cn.net.withub.ums.service.UmsUserInfoService;
import cn.net.withub.ums.util.ReflectionTools;
import cn.net.withub.ums.util.SessionUtils;
import cn.net.withub.ums.util.StringTools;
import cn.net.withub.ums.util.StringUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * @author Diluka
 */
@Controller
@ParentPackage("subItemAudit")
@Scope("prototype")
@Namespace("/userinfo/attach")
@Results({
        @Result(name = "json", type = "json", params = {"root", "data"}),
        @Result(name = "page", location = "/board/personnel/info/userinfo/${viewName}.jsp")
})
public class UserInfoAttachedTableAction {

    @Autowired
    private UmsAttachedTableService attachedTableService;

    @Autowired
    private EntityFactory entityFactory;

    @Autowired
    private UmsLogger umsLogger;

    @Autowired
    private UmsUserInfoService userInfoService;
    @Autowired
    private RmpsyDataSync rmpsyDataSync;

    private final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    /**
     * 单独的id
     */
    private String id;
    /**
     * 删除学历时，同步数据用
     */
    private String user_id;

    /**
     *  id
     */
    private String allId;
    private String allUUID;

    private String sae;
    private String update_time;
    private String userNo;
    /**
     * 表名
     */
    private String t;
    /**
     * 属性
     */
    private Map<String, String> p = new HashMap<>();
    private Object data;



    public String save() {
        Map<String, Object> map = new HashMap<>();
        data = map;
        int result = 0;
        Object entity = entityFactory.createEntity(t, p);
        if (entity == null) {
            umsLogger.log(UmsLogger.LogType.Suspicious, t + ": " + gson.toJson(p));
        } else {

            if (!(entity instanceof UmsLog) && ReflectionTools.hasField(entity, "id") && ReflectionTools.hasField(entity, "userId")) {//判断操作是否正常
                try {
                    /////////添加基本信息//////////////
                    String userId = p.get("userId");
                    UmsUserInfo userInfo = userInfoService.selectById(userId);

                    ReflectionTools.setField(entity, "userNo", userInfo.getUserNo());
                    ReflectionTools.setField(entity, "courtNo", userInfo.getCourtNo());
                    ReflectionTools.setField(entity, "courtCode", userInfo.getCourtCode());
                    ////////////////////////////////////

                    //////////////////////记录最后修改人/////////////////
                    UmsUserInfoView user = SessionUtils.currentUser();
                    if (ReflectionTools.hasField(entity, "updateUser")) {
                        ReflectionTools.setField(entity, "updateUser", user.getFullname() + "@" + user.getCourtNoText());
                    }
                    if (ReflectionTools.hasField(entity, "updateTime")) {
                        ReflectionTools.setField(entity, "updateTime", new Date());
                    }
                    ///////////////////////////////////////////////////////
                    if (entity.getClass().getSimpleName().equals("UmsLevelInfo")) { //处理前判断leveltype是否有值
                        if (StringUtils.isEmpty(ReflectionTools.getField(entity, "nLevelType"))) {
                            throw new Exception("UmsLevelInfo check fail, nLevelType empty");
                        }
                        // 如果是ums_level_info
//                        ReflectionTools.setField(entity, "yefgEndTime", ServletActionContext.getRequest().getParameter("yefg_end_time"));
                    }
                    if (!StringTools.isNullOrEmpty(id)) {//有id就新增，否则就修改
                        try {
                            ReflectionTools.setField(entity, "id", id);
                        } catch (Exception ex) {
                            Logger.getLogger(UserInfoAttachedTableAction.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        result = attachedTableService.insert(entity);


                    } else {
                        result = attachedTableService.update(entity);
                    }
                    //人事系统的增量更新 要求记录数据的更新时间 统一放入ums_user_info当中
                    userInfo.setUpdateTime(new Date());
                    userInfoService.updateTime(userInfo);
                    //人民陪审员信息同步
                    if("1".equals(rmpsyDataSync.isSynchronization))
                    {
                        ActionContext context = ActionContext.getContext();
                        Map session = context.getSession();
                        UmsUserInfoView czr = (UmsUserInfoView) session.get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
                        rmpsyDataSync.userSync(userId,czr);
                    }

                } catch (Exception ex) {
                    Logger.getLogger(UserInfoAttachedTableAction.class.getName()).log(Level.SEVERE, null, ex);

                }
            } else {
                umsLogger.log(UmsLogger.LogType.Suspicious, t + ": " + gson.toJson(p));
            }
        }

        map.put("success", result > 0);
        map.put("result", result);
        return "json";
    }

    /**
     * 保存实体类<br/>
     * 反射的实体必须有字段id和userId（不是参数Map），并且不是UmsLog才会保存，否则记录操作日志
     *
     * @return
     */
    @Action("save")
    public String saveForAction(){
        return save();
    }

    @Action("delete")
    public String delete() {
        Map<String, Object> map = new HashMap<>();
        data = map;
        int result = 0;

        try {
            Object entity = entityFactory.createEntity(t, p);

            if (entity != null) {
                result = attachedTableService.delete(entity);
                //人民陪审员信息同步
                if("1".equals(rmpsyDataSync.isSynchronization))
                {
                    ActionContext context = ActionContext.getContext();
                    Map session = context.getSession();
                    UmsUserInfoView czr = (UmsUserInfoView) session.get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
                    rmpsyDataSync.userSync(user_id,czr);
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(UserInfoAttachedTableAction.class.getName()).log(Level.SEVERE, null, ex);
            umsLogger.log(UmsLogger.LogType.Suspicious, t + ": " + gson.toJson(p));
        }

        map.put("success", result > 0);
        map.put("result", result);
        return "json";
    }

    /** 批量增加
     * 保存实体类<br/>
     * 反射的实体必须有字段id和userId（不是参数Map），并且不是UmsLog才会保存，否则记录操作日志
     *
     * @return
     */
    @Action("saveBatch")
    public String saveBatch() {
        Map<String, Object> map = new HashMap<>();
        data = map;
        List<String> list = new ArrayList<>();
        Collections.addAll(list, allId.split(","));
        List<String> list2 = new ArrayList<>();
        Collections.addAll(list2, allUUID.split(","));
        List<String> list3 = new ArrayList<>();
        Collections.addAll(list3, userNo.split(","));
        int result = 0;
        t = t.substring(0,t.length()-6);
        if ("levelInfo".equals(t)) {
            String nLevelName = p.get("nLevelName");
            String nLevelType = p.get("nLevelType");
            switch(nLevelType) {
                case "1":
                    p.put("judgeLevel", nLevelName);
                    break;
                case "2":
                    p.put("marshalLevel", nLevelName);
                    break;
                case "3":
                    p.put("helperLevel", nLevelName);
                    break;
                case "4":
                    p.put("clerkLevel", nLevelName);
                    break;
            }
            p.remove("nLevelName");
        }
        Object entity = entityFactory.createEntity(t, p);
        if (entity == null) {
            umsLogger.log(UmsLogger.LogType.Suspicious, t + ": " + gson.toJson(p));
        } else {
            if (!(entity instanceof UmsLog) && ReflectionTools.hasField(entity, "id") && ReflectionTools.hasField(entity, "userId")) {//判断操作是否正常
                try {
                    /////////添加基本信息//////////////
                    String userId = list.get(0);
                    UmsUserInfo userInfo = userInfoService.selectById(userId);

                    ReflectionTools.setField(entity, "userNo", userInfo.getUserNo());
                    ReflectionTools.setField(entity, "courtNo", userInfo.getCourtNo());
                    ReflectionTools.setField(entity, "courtCode", userInfo.getCourtCode());
                    ////////////////////////////////////

                    //////////////////////记录最后修改人/////////////////
                    UmsUserInfoView user = SessionUtils.currentUser();

                    Date date = new Date();
                    long miller = date.getTime();
                    ///////////////////////////////////////////////////////
                    if (sae.equals("1") ) {//有id就新增，否则就修改
                        for (int i = 0; i<list.size();i++) {
                            String uuid = UUID.randomUUID().toString();

                            try {
                                ReflectionTools.setField(entity, "id", uuid);
                                ReflectionTools.setField(entity, "userNo", list3.get(i));
                                ReflectionTools.setField(entity, "userId", list.get(i));
                                ReflectionTools.setField(entity, "updateTime", miller);
                                //记录最后修改人
                                if (ReflectionTools.hasField(entity, "updateUser")) {
                                    ReflectionTools.setField(entity, "updateUser", user.getFullname() + "@" + user.getCourtNoText());
                                }
                            } catch (Exception ex) {
                                Logger.getLogger(UserInfoAttachedTableAction.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            result = attachedTableService.insertBatch(entity);

//                            //人事系统的增量更新 要求记录数据的更新时间 统一放入ums_user_info当中
//                            UmsUserInfo userInfo2 = new UmsUserInfo();
//                            userInfo2.setId(list.get(i));
//                            userInfo2.setUpdateTime(date);
//                            userInfoService.updateTime(userInfo2);
                        }
                    } else {
                        for (int i =0;i<list.size();i++) {
                            ReflectionTools.setField(entity, "id", list2.get(i));
                            ReflectionTools.setField(entity, "userId", list.get(i));
                            ReflectionTools.setField(entity, "userNo", list3.get(i));
                            ReflectionTools.setField(entity, "updateTime", update_time);
                            //记录最后修改人
                            if (ReflectionTools.hasField(entity, "updateUser")) {
                                ReflectionTools.setField(entity, "updateUser", user.getFullname() + "@" + user.getCourtNoText());
                            }

                            result = attachedTableService.updateBatch(entity);
//                            //人事系统的增量更新 要求记录数据的更新时间 统一放入ums_user_info当中
//                            UmsUserInfo userInfo2 = new UmsUserInfo();
//                            userInfo2.setId(list.get(i));
//                            userInfo2.setUpdateTime(date);
//                            userInfoService.updateTime(userInfo2);
                        }
                    }

                } catch (Exception ex) {
                    Logger.getLogger(UserInfoAttachedTableAction.class.getName()).log(Level.SEVERE, null, ex);

                }
            } else {
                umsLogger.log(UmsLogger.LogType.Suspicious, t + ": " + gson.toJson(p));
            }
        }

        map.put("success", result > 0);
        map.put("result", result);
        return "json";
    }

    @Action("deleteBatch")
    public String deleteBatch() {

        Map<String, Object> map = new HashMap<>();
        data = map;
        int result = 0;

        try {
            t = t.substring(0,t.length()-6);
            Object entity = entityFactory.createEntity(t, p);

            List<String> list = new ArrayList<>();
            Collections.addAll(list, allUUID.split(","));
            for (String iid :list) {
                ReflectionTools.setField(entity, "id", iid);
                if (entity != null) {
                    result = attachedTableService.delete(entity);
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(UserInfoAttachedTableAction.class.getName()).log(Level.SEVERE, null, ex);
            umsLogger.log(UmsLogger.LogType.Suspicious, t + ": " + gson.toJson(p));
        }

        map.put("success", result > 0);
        map.put("result", result);
        return "json";
    }

    public Map<String, String> getP() {
        return p;
    }

    public void setP(Map<String, String> p) {
        this.p = p;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAllId() {
        return allId;
    }

    public void setAllId(String allId) {
        this.allId = allId;
    }

    public String getSae() {
        return sae;
    }

    public void setSae(String sae) {
        this.sae = sae;
    }

    public String getAllUUID() {
        return allUUID;
    }

    public void setAllUUID(String allUUID) {
        this.allUUID = allUUID;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUser_id()
    {
        return user_id;
    }

    public void setUser_id(String user_id)
    {
        this.user_id = user_id;
    }
}
