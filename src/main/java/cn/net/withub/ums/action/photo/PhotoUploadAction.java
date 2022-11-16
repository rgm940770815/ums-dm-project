/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template photo, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.action.photo;

import cn.net.withub.ums.common.UmsConstant;
import cn.net.withub.ums.util.FileTools;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.codec.binary.Base64;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.*;

/**
 * @author Diluka
 */
@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/photo")
@Results({
        @Result(name = "json", type = "json", params = {"root", "data"}),
        @Result(name = "iframe", location = "/fragment/pic.jsp")
})
public class PhotoUploadAction extends ActionSupport {

    private File photo;
    private String type;
    private String photoContentType;
    private String photoFileName;

    private Object data;

    @Action("upload")
    public String upload() {
        Map<String, Object> map = new HashMap<>();
        try {
            if (photo != null) {
                byte[] b = FileTools.file2Bytes(photo);
                //限制文件名称
                String filenameExtension = StringUtils.getFilenameExtension(photoFileName);
                List<String> allowList = Arrays.asList("png", "jpg", "jpeg", "gif", "bmp");
                if(StringUtils.isEmpty(filenameExtension) || !allowList.contains(filenameExtension.toLowerCase())
                || b.length > 1024 * 1024 * 5
                ){
                    //文件后缀不符合要求 或者文件过大
                    map.put("success", false);
                }else{
                    ActionContext.getContext().getSession().put(UmsConstant.TEMP_PHOTO.toString(), b);
                    ActionContext.getContext().getSession().put(UmsConstant.TEMP_PHOTO_NAME.toString(), photoFileName);
                    map.put("success", true);
                    map.put("url", getTempPhoto(b));
                    map.put("name", UUID.randomUUID().toString() + "." + filenameExtension);
                    map.put("ext", photoFileName.substring(photoFileName.lastIndexOf(".")));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (type.equalsIgnoreCase("iframe")) {
            data = map;
            ServletActionContext.getRequest().setAttribute("map", map);
            return "iframe";
        }
        data = map;
        return "json";
    }

    @Action("clear")
    public void clearTempPhoto() {
        ActionContext.getContext().getSession().remove(UmsConstant.TEMP_PHOTO.toString());
    }

    private String getTempPhoto(byte[] bs) {
        Base64 base64 = new Base64();
        try {
            return "data:image/png;base64,"
                    + base64.encodeAsString(bs);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public File getPhoto() {
        return photo;
    }

    public void setPhoto(File photo) {
        this.photo = photo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhotoContentType() {
        return photoContentType;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }

    public String getPhotoFileName() {
        return photoFileName;
    }

    public void setPhotoFileName(String photoFileName) {
        this.photoFileName = photoFileName;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
