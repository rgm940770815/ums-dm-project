package cn.net.withub.ums.action.userinfo;

import cn.net.withub.ums.action.noticeFile.NoticeFjService;
import cn.net.withub.ums.common.UmsConstant;
import cn.net.withub.ums.entity.*;
import cn.net.withub.ums.service.UmsNoticeService;
import cn.net.withub.ums.util.StringTools;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by Cypress on 2016/11/28.
 */
@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/notice")
@Results({
        @Result(name = "json", type = "json", params = {"root", "data"}),
        @Result(name = "stream", type = "stream",
                params = {"inputName", "inputStream", "contentDisposition", "attachment;filename=${filename}",})
})
public class UmsNoticeAction {

    private Object data;
    private Integer start;
    private Integer limit;
    private String field;
    private String direction;
    private UmsNotice umsNotice;
    private Integer court;
    private String titleText;


    @Autowired
    UmsNoticeService umsNoticeService;
    @Autowired
    private NoticeFjService fjService;

    @Action("showAllInfo")
    public String showAllInfo() {

        UmsNoticeExample umsNoticeExample = new UmsNoticeExample();

        String order = "";
        if (field != null && direction != null) {
            order = " " + StringTools.camelOrPascalToUnderline(field) + " " + direction;
        } else {
            order = " flag desc,create_time desc ";
        }

        if (start != null && limit != null) {
            order += " limit " + start + "," + limit;
        } else {
            order += " limit 0,20";
        }
        umsNoticeExample.setOrderByClause(order);
        List<UmsNotice> umsNotices = new ArrayList<>();
        int i = 0;
        try {
            if (StringUtils.hasText(titleText)) {
                umsNoticeExample.createCriteria().andTitleLike("%" + titleText + "%");
            }
            umsNotices = umsNoticeService.selectByExampleOrderByTime(umsNoticeExample);
            i = umsNoticeService.countByExample(umsNoticeExample);
        } catch (Exception e) {
            e.printStackTrace();
        }


        Map<String, Object> map = new HashMap<>();
        map.put("rows", umsNotices);
        map.put("results", i);
        data = map;
        return "json";
    }

    @Action("publish")
    public String publish() {
        boolean flag = false;
        Map<String, Object> map = new HashMap<>();
        try {
            if (umsNotice != null && umsNotice.getShowEnd() != null && umsNotice.getShowStart() != null
                    && umsNotice.getTitle() != null && umsNotice.getContent() != null) {
                //获取用户信息
                UmsUserInfoView u = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
                int i = 0;
                if(umsNotice.getId() == null || "".equals(umsNotice.getId()))
                {

                    umsNotice.setCreateTime(new Date());
                    umsNotice.setPublisher(u.getFullname());
                    umsNotice.setPublisherId(u.getId());
                    umsNotice.setId(UUID.randomUUID().toString());
                    i = umsNoticeService.infoInsert(umsNotice);
                }
                else
                {
                    umsNotice.setUpdateTime(new Date());
                    umsNotice.setUpdateUserid(u.getId());
                    umsNotice.setUpdateUsername(u.getFullname());
                    i = umsNoticeService.updateByPrimaryKeySelective(umsNotice);
                }
                if (i > 0) {
                    flag = true;
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("success", flag);
        map.put("noticeId",umsNotice.getId());
        data = map;
        return "json";
    }

    @Action("delete")
    public String delete() {
        boolean flag = false;
        Map<String, Object> map = new HashMap<>();
        if (umsNotice != null && umsNotice.getId() != null) {
            int i = umsNoticeService.InfoDeleteByPrimaryKey(umsNotice.getId());
            if (i > 0) {
                flag = true;
            }
        }
        map.put("success", flag);
        data = map;
        return "json";
    }

    @Action("showInfoForCourt")
    public String showInfoForCourt() {
        Map<String, Object> map = new HashMap<>();
        //根据法院代码查询所有符和当前时间要求的信息
        List<String> titleList = new ArrayList<>();
        List<String> contentList = new ArrayList<>();
        List<String> publisherList = new ArrayList<>();
        List<Long> timeList = new ArrayList<>();
        List<List<String>>  fjList = new ArrayList<>();
        try {
            List<UmsNotice> umsNotices = umsNoticeService.selectByCourt(court);
            umsNotices.stream().forEach(umsNotice1 -> {
                        titleList.add(umsNotice1.getTitle() == null ? "" : umsNotice1.getTitle());
                        contentList.add(umsNotice1.getContent() == null ? "" : umsNotice1.getContent());
                        publisherList.add(umsNotice1.getPublisher() == null ? "" : umsNotice1.getPublisher());
                        fjList.add(selectFjListByNoticeId(umsNotice1.getId()));
                        if (umsNotice1.getCreateTime() != null) {
                            timeList.add(umsNotice1.getCreateTime().getTime());
                        } else {
                            timeList.add(0l);
                        }
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("title", titleList);
        map.put("content", contentList);
        map.put("publisher", publisherList);
        map.put("time", timeList);
        map.put("noticeFjList",fjList);
        data = map;

        return "json";
    }

    private List<String> selectFjListByNoticeId(String noticeId)
    {
        UmsNoticeFjExample example = new UmsNoticeFjExample();
        List<UmsNoticeFj> umsNoticeFjs = new ArrayList<>();
        String order = " recode_time desc limit 0,20 ";
        example.setOrderByClause(order);
        example.createCriteria().andNoticeIdEqualTo(noticeId);
        umsNoticeFjs = fjService.fjFind(example);
        List<String> fjs = new ArrayList<>();
        if(umsNoticeFjs != null)
        {
            for (UmsNoticeFj u:umsNoticeFjs)
            {
                String fj = u.getNoticeId() + "," + u.getFjId() + "," + u.getFileName();
                fjs.add(fj);
            }
            return fjs;
        }
        fjs.add("");
        return fjs;
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

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public UmsNotice getUmsNotice() {
        return umsNotice;
    }

    public void setUmsNotice(UmsNotice umsNotice) {
        this.umsNotice = umsNotice;
    }

    public Integer getCourt() {
        return court;
    }

    public void setCourt(Integer court) {
        this.court = court;
    }

    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }


}
