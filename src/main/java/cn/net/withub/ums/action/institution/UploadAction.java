package cn.net.withub.ums.action.institution;


import java.io.File;
import java.io.IOException;
import java.util.*;

import cn.net.withub.ums.common.UmsConstant;
import cn.net.withub.ums.entity.UmsInstitutionUploadFile;
import cn.net.withub.ums.entity.UmsUserInfoView;
import cn.net.withub.ums.service.UmsInstitutionUploadFileService;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.io.FileUtils;

import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;


public class UploadAction extends ActionSupport {

    private File uploadImage; //得到上传的文件
    private String uploadImageContentType; //得到文件的类型
    private String uploadImageFileName; //得到文件的名称

    private UmsInstitutionUploadFile information;
    private Object data;

    @Autowired
    UmsInstitutionUploadFileService service;

    @Value("#{config2['insSavePath']}")
    public String path;


    public String execute() {

        Map<String, Object> re = new HashMap<>();
        re.put("success", false);
        data = re;

        try {
            if (!StringUtils.hasText(information.getRelationId()) || !StringUtils.hasText(information.getRelationType())) {
                return "json";
            }

            System.out.println("fileName:" + this.getUploadImageFileName());
            System.out.println("contentType:" + this.getUploadImageContentType());
            System.out.println("File:" + this.getUploadImage());

            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);

            //获取要保存文件夹的物理路径(绝对路径)
            File file = new File(path + File.separator + year + File.separator + month);

            //测试此抽象路径名表示的文件或目录是否存在。若不存在，创建此抽象路径名指定的目录，包括所有必需但不存在的父目录。
            if (!file.exists()) file.mkdirs();

            File newFile = new File(file, UUID.randomUUID().toString() + "." + StringUtils.getFilenameExtension(uploadImageFileName));
            //保存文件
            FileUtils.copyFile(uploadImage, newFile);


            information.setId(UUID.randomUUID().toString());
            information.setFileName(uploadImageFileName);
            information.setFileType(uploadImageContentType);
            information.setIsValid(true);
            information.setRecordTime(new Date());
            information.setSavePath(newFile.getAbsolutePath());

            try {
                UmsUserInfoView u = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
                information.setUploadUserId(u.getId());
                information.setUploadUserName(u.getFullname());
            } catch (Exception e) {
                e.printStackTrace();
            }

            int insert = service.insert(information);

            if(insert > 0){
                re.put("success", true);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        re.put("success", true);

        return "json";
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public File getUploadImage() {
        return uploadImage;
    }

    public void setUploadImage(File uploadImage) {
        this.uploadImage = uploadImage;
    }

    public String getUploadImageContentType() {
        return uploadImageContentType;
    }

    public void setUploadImageContentType(String uploadImageContentType) {
        this.uploadImageContentType = uploadImageContentType;
    }

    public String getUploadImageFileName() {
        return uploadImageFileName;
    }

    public void setUploadImageFileName(String uploadImageFileName) {
        this.uploadImageFileName = uploadImageFileName;
    }

    public UmsInstitutionUploadFile getInformation() {
        return information;
    }

    public void setInformation(UmsInstitutionUploadFile information) {
        this.information = information;
    }

}
