package cn.net.withub.ums.action.noticeFile;

import cn.net.withub.ums.common.UmsConstant;
import cn.net.withub.ums.entity.UmsNoticeFj;
import cn.net.withub.ums.entity.UmsNoticeFjExample;
import cn.net.withub.ums.entity.UmsUserInfoView;
import cn.net.withub.ums.util.StringTools;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by wj on 2016/12/1.
 */
@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/noticeFileUpload")
@Results({
        @Result(name = "json", type = "json", params = {"root", "data"}),
        @Result(name = "iframe", location = "/fragment/pic.jsp"),
        @Result(name = "stream", type = "stream",
                params = {"inputName", "inputStream", "contentDisposition", "attachment;filename=${filename}",})
})
public class NoticeFileUploadAction extends ActionSupport
{
    private Object data;
    private List<File> fjs;
    private List<String> fjsContentType;
    private List<String> fjsFileName;
    private String noticeId;
    private String fjId;
    private ByteArrayInputStream inputStream;
    private String filename;
    private Integer start;
    private Integer limit;
    private String field;
    private String direction;


    @Value("#{config2['savePath']}")
    public String path;


    @Autowired
    private NoticeFjService fjService;

    @Action("upload")
    public String uploda()
    {
        Map<String,Object> map = new HashMap<>();
        //String savePath1 = ServletActionContext.getServletContext().getRealPath(path);
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        String savePath = path+"/"+year+"年/"+month+"月";

        File rootFile = new File(savePath);
        if(!rootFile.exists()){
            rootFile.mkdirs();
        }
        UmsUserInfoView u = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
        try {
            int count = 0;
            for(int i = 0; i < fjs.size(); i++)
            {
                byte[] bytes = FileUtils.readFileToByteArray(fjs.get(i));
                File newFile = new File(savePath,fjsFileName.get(i));
                FileUtils.writeByteArrayToFile(newFile,bytes);

                UmsNoticeFj fj = new UmsNoticeFj();
                fj.setFjId(UUID.randomUUID().toString());
                fj.setFileName(fjsFileName.get(i));
                fj.setFjType(fjsContentType.get(i));
                fj.setSavePath(savePath+"/"+fjsFileName.get(i));
                fj.setNoticeId(noticeId);
                fj.setRecodeTime(new Date());
                fj.setRecodeUserId(u.getId());
                fj.setRecodeUserFullname(u.getFullname());
                int s = fjService.fjSave(fj);
                if(s>0)
                    count += s;
            }
            if(count > 0)
                map.put("success",count);
            else
                map.put("success",false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        data = map;
        return "json";
    }
    @Action("selectList")
    public String selectList()
    {
        int i = 0;
        List<UmsNoticeFj> umsNoticeFjs = new ArrayList<>();
        try
        {
            if(noticeId != null && !"".equals(noticeId))
            {
                UmsNoticeFjExample example = new UmsNoticeFjExample();
                String order = "";
                if (field != null && direction != null) {
                    order = " " + StringTools.camelOrPascalToUnderline(field) + " " + direction;
                } else {
                    order = " recode_time desc ";
                }

                if (start != null && limit != null) {
                    order += " limit " + start + "," + limit;
                } else {
                    order += " limit 0,20";
                }
                example.setOrderByClause(order);
                example.createCriteria().andNoticeIdEqualTo(noticeId);
                umsNoticeFjs = fjService.fjFind(example);
                i = fjService.countByExample(example);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<>();
        map.put("rows", umsNoticeFjs);
        map.put("results", i);
        data = map;
        return "json";
    }
    @Action("fjDelete")
    public String deleteOne()
    {
        int i = 0;
        if(fjId != null && !"".equals(fjId))
        {
            i = fjService.fjdelete(fjId);
        }
        Map<String, Object> map = new HashMap<>();
        if(i>0)
            map.put("success",true);
        else
            map.put("success",false);
        data = map;
        return "json";
    }
    @Action("downLoadFj")
    public String downLoad()
    {
        Map<String,Object> map = new HashMap<>();
        try {
            //String path = ServletActionContext.getServletContext().getRealPath("") + "/templet.docx";
            if(fjId != null && !"".equals(fjId))
            {
                UmsNoticeFj fj = fjService.selectById(fjId);
                if(fj!= null && fj.getSavePath() != null)
                {
                    byte[] bytes = FileUtils.readFileToByteArray(new File(fj.getSavePath()));
                    this.inputStream = new ByteArrayInputStream(bytes);
                    filename = fj.getFileName();
                    return "stream";
                }
                else
                {
                    map.put("msg","找不到这个附件或附件已删！");
                    data = map;
                    return "json";
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            map.put("msg","找不到这个附件或附件已删！");
            data = map;
        }
        return "json";
    }
    public List<File> getFjs()
    {
        return fjs;
    }

    public void setFjs(List<File> fjs)
    {
        this.fjs = fjs;
    }

    public List<String> getFjsContentType()
    {
        return fjsContentType;
    }

    public void setFjsContentType(List<String> fjsContentType)
    {
        this.fjsContentType = fjsContentType;
    }

    public List<String> getFjsFileName()
    {
        return fjsFileName;
    }

    public void setFjsFileName(List<String> fjsFileName)
    {
        this.fjsFileName = fjsFileName;
    }

    public String getNoticeId()
    {
        return noticeId;
    }

    public void setNoticeId(String noticeId)
    {
        this.noticeId = noticeId;
    }

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }

    public String getFjId()
    {
        return fjId;
    }

    public void setFjId(String fjId)
    {
        this.fjId = fjId;
    }

    public String getFilename() throws UnsupportedEncodingException
    {
        return URLEncoder.encode(filename, "UTF-8");
    }

    public void setFilename(String filename) throws UnsupportedEncodingException {
        this.filename = new String(filename.getBytes("ISO8859-1"), "utf-8");
    }

    public ByteArrayInputStream getInputStream()
    {
        return inputStream;
    }

    public void setInputStream(ByteArrayInputStream inputStream)
    {
        this.inputStream = inputStream;
    }

    public Integer getStart()
    {
        return start;
    }

    public void setStart(Integer start)
    {
        this.start = start;
    }

    public Integer getLimit()
    {
        return limit;
    }

    public void setLimit(Integer limit)
    {
        this.limit = limit;
    }

    public String getField()
    {
        return field;
    }

    public void setField(String field)
    {
        this.field = field;
    }

    public String getDirection()
    {
        return direction;
    }

    public void setDirection(String direction)
    {
        this.direction = direction;
    }
}
