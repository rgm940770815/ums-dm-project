package cn.net.withub.ums.action.changeuser;

import cn.net.withub.ums.action.userinfo.affiliatedInfoAction;
import cn.net.withub.ums.dao.UmsCodeMapper;
import cn.net.withub.ums.dao.UmsCourtFullMapper;
import cn.net.withub.ums.entity.*;
import cn.net.withub.ums.service.UmsCourtService;
import cn.net.withub.ums.service.UmsDepartmentService;
import cn.net.withub.ums.service.UmsUserInfoService;
import cn.net.withub.ums.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChangeUserDaoImpl implements ChangeUserDao {


    @Autowired
    private UmsUserInfoService userInfoService;

    @Autowired
    private UmsCourtService courtService;

    @Autowired
    private UmsDepartmentService umsDepartmentService;

    @Autowired
    affiliatedInfoAction affiliatedInfoAction;

    @Override
    @Transactional
    public Map<String,Object> setUser(String uuid,
                                      Integer courtNo,Integer department,Integer administrationPosition,Integer lawPosition,
                                      Integer otherCourtNo,Integer otherDepartment,Integer otherAdministrationPosition,Integer otherLawPosition) throws Exception {
        Map<String,Object> re = null;
//        try {
            re = new HashMap<>();
            re.put("success",false);

        if(!StringUtils.hasText(uuid) || courtNo == null || department == null
                //|| administrationPosition == null || lawPosition == null
        ){
                re.put("msg","缺失必要的参数");
                return re;
            }

            UmsUserInfo umsUserInfo = userInfoService.selectById(uuid);
            if(umsUserInfo == null){
                re.put("msg","没找到用户");
                return re;
            }

            //旧数据
            String courtCode1 = umsUserInfo.getCourtCode();
            Integer department1 = umsUserInfo.getDepartment();
            Integer administrationPosition1 = umsUserInfo.getAdministrationPosition();
            Integer lawPosition1 = umsUserInfo.getLawPosition();

            //更新法院 部门 法律职务 行政职务
            umsUserInfo.setCourtNo(courtNo);
            umsUserInfo.setDepartment(department);
            umsUserInfo.setAdministrationPosition(administrationPosition);
            umsUserInfo.setLawPosition(lawPosition);

            //额外的关联
            umsUserInfo.setCourtStdNo(courtService.courtNo2CourtStdNo(umsUserInfo.getCourtNo()));
            umsUserInfo.setCourtCode(courtService.courtNo2CourtCode(umsUserInfo.getCourtNo()));
            //查询标准部门名称
            UmsDepartmentCriteria criteria = new UmsDepartmentCriteria();
            criteria.createCriteria().andCourtCodeEqualTo(umsUserInfo.getCourtCode()).andDeptNoEqualTo(umsUserInfo.getDepartment());
            List<UmsDepartment> umsDepartments = umsDepartmentService.selectByExample(criteria);
            UmsDepartment umsDepartment = umsDepartments.get(0);
            umsUserInfo.setDeptOrgCode(umsDepartment.getOrgCode());

            UmsUserInfoView user = SessionUtils.currentUser();

            umsUserInfo.setUpdateUser(user.getFullname() + "@" + user.getCourtNoText());
            umsUserInfo.setUpdateTime(new Date());


            int update = userInfoService.update(umsUserInfo);
            if(update > 0){

                //进行挂靠新 法院
                UmsTemporaryPosition umsTemporaryPosition = new UmsTemporaryPosition();
                umsTemporaryPosition.setUuid(uuid);
                umsTemporaryPosition.setCourtCode(courtCode1);
                umsTemporaryPosition.setDepartment(department1);
                umsTemporaryPosition.setAdministrationPosition(administrationPosition1);
                umsTemporaryPosition.setLawPosition(lawPosition1);

                affiliatedInfoAction.setUmsTemporaryPosition(umsTemporaryPosition);
                affiliatedInfoAction.affiliatedInsertInfo();
                Map<String, Object> data = (Map<String, Object>)affiliatedInfoAction.getData();
                boolean success = (boolean)data.get("success");
                if(!success){
                    throw new RuntimeException("挂靠1出现错误");
                }

            if( otherCourtNo != null && otherDepartment != null && otherAdministrationPosition != null && otherLawPosition != null){

                    //进行挂靠其他法院
                    UmsTemporaryPosition umsTemporaryPosition2 = new UmsTemporaryPosition();
                    umsTemporaryPosition2.setUuid(uuid);
                    umsTemporaryPosition2.setCourtCode(courtService.courtNo2CourtCode(otherCourtNo));
                    umsTemporaryPosition2.setDepartment(otherDepartment);
                    umsTemporaryPosition2.setAdministrationPosition(otherAdministrationPosition);
                    umsTemporaryPosition2.setLawPosition(otherLawPosition);

                    affiliatedInfoAction.setUmsTemporaryPosition(umsTemporaryPosition2);
                    affiliatedInfoAction.affiliatedInsertInfo();
                    Map<String, Object> data2 = (Map<String, Object>)affiliatedInfoAction.getData();
                    boolean success2 = (boolean)data2.get("success");
                    if(!success2){
                        throw new RuntimeException("挂靠2出现错误");
                    }

                }
                re.put("success",true);

            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//        }


        return re;

    }


}
