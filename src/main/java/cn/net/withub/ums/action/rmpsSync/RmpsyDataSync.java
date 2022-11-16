package cn.net.withub.ums.action.rmpsSync;


import cn.net.withub.ums.dao.UmsEducationInfoMapper;
import cn.net.withub.ums.dao.UmsUserInfoViewMapper;
import cn.net.withub.ums.entity.*;
import cn.net.withub.ums.service.UmsPhotoService;
import cn.net.withub.ums.service.UmsUserInfoService;
import com.google.gson.Gson;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.constants.Style;
import org.apache.axis.constants.Use;
import org.apache.axis.description.OperationDesc;
import org.apache.axis.description.ParameterDesc;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.xml.namespace.QName;
import java.util.List;
import java.util.Map;

/**
 * Created by wj on 2016/10/20.
 */

@org.springframework.stereotype.Service
public class RmpsyDataSync
{
    @Value("#{config['isSynchronization']}")
    public String isSynchronization;
    @Value("#{config['serverAddr']}")
    private String serverAddr;

    @Autowired
    private UmsUserInfoViewMapper userInfoViewMapper;
    @Autowired
    private UmsPhotoService photoService;
    @Autowired
    private UmsEducationInfoMapper educationInfoMapper;
    @Autowired
    UmsUserInfoService userInfoService;
    /**
     * 陪审人员同步
     * czr   当前登陆用户，作为修改人
     */
    public void userSync(String id,UmsUserInfoView czr)
    {

        if(id != null && !"".equals(id))
        {
            UmsUserInfo isRmpsy_user = userInfoService.selectById(id);
            if ("3".equals(isRmpsy_user.getUserType().toString()))
            {
                Map basicMap = userInfoViewMapper.selectBasicUserinfoById(id);//基本信息查询
                basicMap.put("userid_czr",czr.getId());
                basicMap.put("username_czr",czr.getUsername());
                basicMap.put("fydm_czr",czr.getCourtCode());
                UmsPhoto photo = photoService.selectById(id);//图片
                //List list = attachedTableService.selectView("education_info",id,"",RowBounds.DEFAULT);//学历信息
                List list = educationInfoMapper.selectOneById(id);
                Base64 base64 = new Base64();
                Object photoData = null;
                if(photo!= null && photo.getPhoto() != null)
                {
                    photoData = base64.encodeAsString(photo.getPhoto());
                }
                Gson gson = new Gson();
                String basicInfo = "";
                String photoStr = "";
                String xlxl = "";
                if (basicMap!= null)
                {
                    basicInfo = gson.toJson(basicMap);
                }
                if(photoData!=null)
                {
                    photoStr = gson.toJson(photoData);
                }
                if(list.size()>0)
                {
                    xlxl = gson.toJson(list);
                }
                if(!"".equals(basicInfo))
                {
                    sendMessage(basicInfo,xlxl,photoStr);
                }
            }
        }
    }

    private void sendMessage(String basicInfo, String xlxl, String photoStr)
    {
        /*try
        {
            InsertAjxxSoapBindingStub stub =(InsertAjxxSoapBindingStub) serviceLocator.getAjxxWebServiceImplPort();
            String ss = stub.jurorToRs(basicInfo,xlxl,photoStr);
            System.out.print("数据同步结果为："+ss);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }*/

        try
        {
            OperationDesc oper = new OperationDesc();
            ParameterDesc param = new ParameterDesc();
            oper.setName("jurorToRs");
            param = new ParameterDesc(new QName("", "arg0"), ParameterDesc.IN, new QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
            param.setOmittable(true);
            oper.addParameter(param);
            param = new ParameterDesc(new QName("", "arg1"), ParameterDesc.IN, new QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
            param.setOmittable(true);
            oper.addParameter(param);
            param = new ParameterDesc(new QName("", "arg2"), ParameterDesc.IN, new QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
            param.setOmittable(true);
            oper.addParameter(param);
            oper.setReturnType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
            oper.setReturnClass(String.class);
            oper.setReturnQName(new QName("", "return"));
            oper.setStyle(Style.WRAPPED);
            oper.setUse(Use.LITERAL);
            Service service = new Service();
            Call call = (Call)service.createCall();
            call.setOperationName(new QName("http://webservice.withub.com/","jurorToRs"));
            call.setOperation(oper);
            call.setTimeout(2000);//设置超时时间
            call.setTargetEndpointAddress(serverAddr);
            String obj =(String)call.invoke(new Object[]{basicInfo,xlxl,photoStr});
            System.out.print("数据同步结果为："+obj);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    public static void main(String[] arg)
    {
        /*String url="http://149.0.13.235:8080/juror/cxf/ajxx";
        OperationDesc oper = new OperationDesc();
        ParameterDesc param = new ParameterDesc();
        oper.setName("jurorToRs");
        param = new ParameterDesc(new QName("", "arg0"), ParameterDesc.IN, new QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new ParameterDesc(new QName("", "arg1"), ParameterDesc.IN, new QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new ParameterDesc(new QName("", "arg2"), ParameterDesc.IN, new QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(String.class);
        oper.setReturnQName(new QName("", "return"));
        oper.setStyle(Style.WRAPPED);
        oper.setUse(Use.LITERAL);
        Service service = new Service();
        try
        {
            Call call = (Call)service.createCall();
            call.setOperationName(new QName("http://webservice.withub.com/","jurorToRs"));
            call.setOperation(oper);
            call.setTargetEndpointAddress(url);
            String obj =(String)call.invoke(new Object[]{"11","11","11"});
            System.out.print(obj);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }*/



    }



}
