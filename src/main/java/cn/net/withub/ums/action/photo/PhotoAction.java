/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.action.photo;

import cn.net.withub.ums.action.rmpsSync.RmpsyDataSync;
import cn.net.withub.ums.action.xml.ZipUtils;
import cn.net.withub.ums.common.UmsConstant;
import cn.net.withub.ums.dao.UmsRmpsyJbxxMapper;
import cn.net.withub.ums.entity.*;
import cn.net.withub.ums.service.UmsCourtFullService;
import cn.net.withub.ums.service.UmsDepartmentService;
import cn.net.withub.ums.service.UmsPhotoService;
import cn.net.withub.ums.service.UmsUserInfoService;
import cn.net.withub.ums.util.StringTools;
import cn.net.withub.ums.util.StringUtils;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Diluka
 */
@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/photo")
@Results({
        @Result(name = "json", type = "json", params = {"root", "data"})
})
public class PhotoAction {

    private final Logger logger = LogManager.getLogger(PhotoAction.class);

    @Autowired
    private UmsPhotoService photoService;

    @Autowired
    private UmsUserInfoService userInfoService;

    @Autowired
    private UmsDepartmentService umsDepartmentService;

    @Autowired
    private UmsRmpsyJbxxMapper umsRmpsyJbxxMapper;

    @Autowired
    private UmsCourtFullService umsCourtFullService;
    @Autowired
    RmpsyDataSync rmpsyDataSync;

    private String userId;

    private UmsPhoto photo;

    private Object data;

    private String photoBase = "/photo";

    @Action("getPhotoById")
    public String getPhotoById() {
        logger.debug(userId);
        photo = photoService.selectById(userId);
        logger.debug(photo);

        Base64 base64 = new Base64();
        //data = "data:image;base64," + base64.encodeAsString(photo.getPhoto());
        data = "data:image/png;base64," + base64.encodeAsString(photo.getPhoto());

        return "json";
    }

    @Action("save")
    public String savePhoto() {
        int result = 0;
        Map<String, Object> map = new HashMap<>();
        data = map;
        if (!StringTools.isNullOrEmpty(userId)) {
            photo = photoService.selectById(userId);
            byte[] p = null;
            if (ActionContext.getContext().getSession().containsKey(UmsConstant.TEMP_PHOTO.toString())) {
                p = ((byte[]) ActionContext.getContext().getSession().get(UmsConstant.TEMP_PHOTO.toString()));
                ActionContext.getContext().getSession().remove(UmsConstant.TEMP_PHOTO.toString());
                ActionContext.getContext().getSession().remove(UmsConstant.TEMP_PHOTO_NAME.toString());
            }
            if (p == null) {
                // 是否完成删除,0为魏珊初,1为删除
                int a = photoService.deleteById(userId);
                map.put("success", true);
                map.put("msg", "头像已移除");
                return "json";
            }
            if (photo == null) {
                photo = new UmsPhoto();
                photo.setUserId(userId);
                ActionContext actionContext = ActionContext.getContext();
                HttpServletRequest httpServletRequest = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
                String userType = httpServletRequest.getParameter("userType") == null ? null : httpServletRequest.getParameter("userType");
                if (StringUtils.isNotEmpty(userType) && "rmpsyr_3".equals(userType)) {
                    // 针对根据3.0平台的人民陪审员
                    UmsRmpsyJbxx umsRmpsyJbxx = umsRmpsyJbxxMapper.selectByPrimaryKey(userId);
                    photo.setCourtCode(UmsConstant.COURT_CODE.toString());
                    photo.setCourtNo(umsRmpsyJbxx.getCourtno());
                    photo.setUserNo(umsRmpsyJbxx.getUserno());
                    photo.setPhoto(p);
                } else {
                    UmsUserInfo userInfo = userInfoService.selectById(userId);
                    photo.setCourtCode(UmsConstant.COURT_CODE.toString());
                    photo.setCourtNo(userInfo.getCourtNo());
                    photo.setUserNo(userInfo.getUserNo());
                    photo.setPhoto(p);
                }
                photo.setLastModified(new Date());
                result = photoService.insert(photo);
            } else {
                UmsUserInfo userInfo = userInfoService.selectById(userId);
                photo.setPhoto(p);
                photo.setCourtNo(userInfo.getCourtNo());
                photo.setCourtCode(userInfo.getCourtCode());
                photo.setLastModified(new Date());
                try {
                    result = photoService.update(photo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // 人民陪审员信息同步
            if ("1".equals(rmpsyDataSync.isSynchronization)) {
                ActionContext context = ActionContext.getContext();
                Map session = context.getSession();
                UmsUserInfoView czr = (UmsUserInfoView) session.get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
                rmpsyDataSync.userSync(userId, czr);
            }
        }
        map.put("success", result > 0);
        return "json";
    }


    @Action("exportPhoto")
    public void exportPhoto() {

        String realPath = ServletActionContext.getServletContext().getRealPath(photoBase);

        try {
            //法院
            List<UmsCourtFull> court_list = umsCourtFullService.selectByListAll();

            for (UmsCourtFull courtFull : court_list) {

                UmsDepartmentCriteria umsDepartmentCriteria = new UmsDepartmentCriteria();
                umsDepartmentCriteria.createCriteria().andCourtCodeEqualTo(courtFull.getCourtCode())
                        .andDeptStNameEqualTo("执行庭");

                List<UmsDepartment> umsDepartments = umsDepartmentService.selectByExample(umsDepartmentCriteria);

                for (UmsDepartment department : umsDepartments) {
                    String path = "/" + courtFull.getCourtStdName() + "/" + department.getDeptName() + "/";

                    Map<String, Object> map = new HashMap<>();
                    map.put("courtNo", courtFull.getCourtNo());
                    map.put("department", department.getDeptNo());


                    List<Map> umsPhotos = photoService.selectByDept(map);


                    for (Map photo : umsPhotos) {

                        String FileName = photo.get("name") + ".jpg";
                        File targetFile = new File(realPath + path, FileName);

                        if (!targetFile.exists()) {

                            if (!targetFile.getParentFile().exists()) {
                                targetFile.getParentFile().mkdirs();
                            }
                            targetFile.createNewFile();

                            FileOutputStream outer = new FileOutputStream(targetFile);

                            byte[] photo_byte = (byte[]) photo.get("photo");
                            ByteArrayInputStream inputStream = new ByteArrayInputStream(photo_byte);
                            BufferedImage bufferedImage = ImageIO.read(inputStream);

                            byte[] zipImageFile = ZipUtils.zipImageFile(0.4f, bufferedImage);
                            outer.write(zipImageFile);
                            inputStream.close();
                            outer.close();

                        }

                    }

                }

            }
        } catch (Exception e) {

            e.printStackTrace();
        }


    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UmsPhoto getPhoto() {
        return photo;
    }

    public void setPhoto(UmsPhoto photo) {
        this.photo = photo;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
