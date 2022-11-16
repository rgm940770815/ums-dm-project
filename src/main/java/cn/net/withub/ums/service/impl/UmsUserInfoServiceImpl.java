/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.dao.UmsUserInfoMapper;
import cn.net.withub.ums.dao.XtptTUserMapper;
import cn.net.withub.ums.dao.XtptUserMapper;
import cn.net.withub.ums.entity.*;
import cn.net.withub.ums.service.UmsUserInfoService;
import cn.net.withub.ums.service.UmsUserInfoViewService;
import cn.net.withub.ums.service.XtptTUserDuplicateService;
import cn.net.withub.ums.util.Md5PasswordEncoder;
import cn.net.withub.ums.util.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UmsUserInfoServiceImpl implements UmsUserInfoService {

    @Autowired
    private UmsUserInfoMapper userInfoMapper;
    @Autowired
    private XtptUserMapper xtptUserMapper;
    @Autowired
    private XtptTUserMapper xtptTUserMapper;

    @Autowired
    private XtptTUserDuplicateService xtptTUserDuplicateService;

    @Autowired
    private UmsUserInfoViewService umsUserInfoViewService;


    @Override
    public UmsUserInfo login(Integer courtStdNo, String username, String password) {
        UmsUserInfoCriteria criteria = new UmsUserInfoCriteria();
        criteria.createCriteria()
                .andUsernameEqualTo(username)
                .andPasswordEqualTo(password)
                .andCourtStdNoEqualTo(courtStdNo);

        List<UmsUserInfo> list = userInfoMapper.selectByExample(criteria);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    @Transactional
    public int insert(UmsUserInfo entity) {
        // insert成功的数量
        int result = 0;
        try {
            // insert表ums_user_info
            result = userInfoMapper.insertSelective(entity);
            // insert表xtpt_t_user
            XtptTUser user_ = changeUserTypeForInsert(entity);
            xtptTUserMapper.insert(user_);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//回滚
            throw new RuntimeException("插入数据失败");
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertNoAspect(UmsUserInfo entity) {
        int result = userInfoMapper.insertSelective(entity);
        if (result > 0) {
            try {

                boolean flag = false;
                if (entity.getUserType() == 3) {
                    flag = true;
                }
                XtptUserCriteria criteria = new XtptUserCriteria();
                criteria.createCriteria().andUsernameEqualTo(entity.getUsername())
                        .andEnabledEqualTo(true);
                //查找是否有同名账户存在
                List<XtptUser> u = xtptUserMapper.selectByExample(criteria);
                if (u.size() > 0 || flag) {
                    String idCard = entity.getIdcard().trim();
                    StringBuffer str = new StringBuffer(entity.getUsername());
                    switch (idCard.length()) {
                        case 18:
                            str.append(idCard.substring(12, 18));
                            break;
                        case 15:
                            str.append(idCard.substring(9, 15));
                            break;
                        default:
                            throw new Exception("idCard is error");
                    }
                    criteria.clear();
                    criteria.createCriteria().andUsernameEqualTo(str.toString())
                            .andEnabledEqualTo(true);
                    //是否存在 账户名 加身法证后6位的 账户名
                    List<XtptUser> l = xtptUserMapper.selectByExample(criteria);
                    if (l.size() > 0) {
                        //如果存在 另外存入一张表
                        XtptUser user_ = changeUserTypeForInsert_(entity);
                        xtptTUserDuplicateService.insertForXtptUser(user_);
                    } else {
                        entity.setUsername(str.toString());
                        XtptTUser user_ = changeUserTypeForInsert(entity);

                        xtptTUserMapper.insert(user_);
                    }
                } else {
                    XtptTUser user_ = changeUserTypeForInsert(entity);
                    xtptTUserMapper.insert(user_);
                }

            } catch (Exception e) {
                e.printStackTrace();
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//回滚
            }

        }
        return result;
    }


    private XtptUser changeUserTypeForInsert_(UmsUserInfo entity) {

        XtptUser user_ = new XtptUser();
        user_.setDeptOrgCode(entity.getDeptOrgCode());
        user_.setSalt(entity.getSalt());
        user_.setUuid(entity.getId());
        user_.setCourtCode(entity.getCourtCode());
        user_.setUsername(entity.getUsername());
        user_.setPassword(entity.getPassword());
        user_.setUserType(entity.getUserType());
        user_.setDeptNo(entity.getDepartment());
        user_.setEnabled(true);
        user_.setAccountNonExpired(true);
        user_.setAccountNonLocked(true);
        user_.setCredentialsNonExpired(true);
        user_.setSortNo(entity.getSortNo() == null ? 9999 : entity.getSortNo());
        user_.setFullname(entity.getFullname());
        user_.setGender(entity.getGender() == 1);
        user_.setNation(entity.getNation());
        user_.setBirthday(entity.getBirthday());
        user_.setCourtNo(entity.getCourtNo());
        user_.setIdcard(entity.getIdcard());
        user_.setHometown(entity.getHometown());
        user_.setBirthplace(entity.getBirthplace());
        user_.setCourtStdNo(entity.getCourtStdNo());
        user_.setEducationBackground(entity.getEducationBackground());
        user_.setFormerName(entity.getFormerName());
        user_.setPolitical(entity.getPolitical());
        //user_.setMaritalStatus(entity.getMaritalStatus());
        //user_.setPhysicalCondition(entity.getPhysicalCondition());
        user_.setUpdateTime(new Date());
        user_.setUpdateUser(entity.getUpdateUser());

        return user_;
    }

    private XtptTUser changeUserTypeForInsert(UmsUserInfo entity) {

        XtptTUser user_ = new XtptTUser();
        user_.setDeptOrgCode(entity.getDeptOrgCode());
        user_.setSalt(entity.getSalt());
        user_.setUuid(entity.getId());
        user_.setCourtCode(entity.getCourtCode());
        user_.setUsername(entity.getUsername());
        user_.setPassword(entity.getPassword());
        user_.setUserType(entity.getUserType());
        user_.setDeptNo(entity.getDepartment());
        user_.setEnabled(true);
        user_.setAccountNonExpired(true);
        user_.setAccountNonLocked(true);
        user_.setCredentialsNonExpired(true);
        user_.setSortNo(entity.getSortNo() == null ? 9999 : entity.getSortNo());
        user_.setFullname(entity.getFullname());
        if (entity.getGender() != null) {
            user_.setGender(entity.getGender() == 1);
        }
        user_.setNation(entity.getNation());
        user_.setBirthday(entity.getBirthday());
        user_.setCourtNo(entity.getCourtNo());
        user_.setIdcard(entity.getIdcard());
        user_.setHometown(entity.getHometown());
        user_.setBirthplace(entity.getBirthplace());
        user_.setCourtStdNo(entity.getCourtStdNo());
        user_.setEducationBackground(entity.getEducationBackground());
        user_.setFormerName(entity.getFormerName());
        user_.setPolitical(entity.getPolitical());
        //user_.setMaritalStatus(entity.getMaritalStatus());
        //user_.setPhysicalCondition(entity.getPhysicalCondition());
        user_.setUpdateTime(new Date());
        user_.setUpdateUser(entity.getUpdateUser());
        user_.setIsValid(entity.getIsValid());
        user_.setNationReport(entity.getNationReport());
        user_.setPoliticalReport(entity.getPoliticalReport());
        user_.setAdministrationPositionReport(entity.getAdministrationPositionReport());
        user_.setLawPositionReport(entity.getLawPositionReport());
        //电话号码 和 座机号码
        user_.setPhone(entity.getPhoneNumber());
        user_.setOfficePhone(entity.getExtOfficePhone());

        return user_;
    }


    @Override
    public int insertOnly(UmsUserInfo entity) {
        int result = userInfoMapper.insertSelective(entity);
        return result;
    }

    @Override
    public int updateEnabled(UmsUserInfo userInfo) {
        return userInfoMapper.updateEnabled(userInfo);
    }

    @Override
    public int updateAll(UmsUserInfo userInfo) {
        return userInfoMapper.updateByPrimaryKey(userInfo);
    }

    @Override
    public List<UmsUserInfo> selectByExample(UmsUserInfoCriteria example) {
        return userInfoMapper.selectByExample(example);
    }

    @Override
    public List<UmsUserInfo> selectByExample(UmsUserInfoCriteria example, RowBounds rowBounds) {
        return userInfoMapper.selectByExample(example, rowBounds);
    }

    @Override
    public List<UmsUserInfo> selectByExampleWithNoAspect(UmsUserInfoCriteria example) {
        return userInfoMapper.selectByExample(example);
    }


    @Override
    public int delete(UmsUserInfo entity) {
        return userInfoMapper.deleteByPrimaryKey(entity.getId());
    }

    public int updateForTypeOne(UmsUserInfo entity) {

        int result = 0;
        try {
            result = userInfoMapper.updateForTypeOne(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result > 0) {
            XtptUser user_ = xtptUserMapper.searchByUUID(entity.getId());
            if (user_ == null) {
                return result;
            }

            user_.setUuid(entity.getId());
            user_.setCourtCode(entity.getCourtCode());
            user_.setDeptNo(entity.getDepartment());
            user_.setFullname(entity.getFullname());
            user_.setGender(entity.getGender() == 1 ? true : false);
            user_.setNation(entity.getNation());
            user_.setBirthday(entity.getBirthday());
            user_.setCourtNo(entity.getCourtNo());
            user_.setIdcard(entity.getIdcard());
            user_.setHometown(entity.getHometown());
            user_.setBirthplace(entity.getBirthplace());
            user_.setCourtStdNo(entity.getCourtStdNo());
            user_.setEducationBackground(entity.getEducationBackground());
            user_.setFormerName(entity.getFormerName());
            user_.setPolitical(entity.getPolitical());
            //user_.setMaritalStatus(entity.getMaritalStatus());
            //user_.setPhysicalCondition(entity.getPhysicalCondition());
            user_.setUpdateTime(new Date());
            user_.setUpdateUser(entity.getUpdateUser());
            if (entity.getUserType() != 3) {
                xtptUserMapper.updateByPrimaryKeySelective(user_);
            }
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(UmsUserInfo entity) {


        int result = userInfoMapper.updateByPrimaryKey(entity);

        if (result > 0) {
            XtptTUserExample example = new XtptTUserExample();
            example.createCriteria().andUuidEqualTo(entity.getId());
            XtptTUser user_ = null;

            try {
                user_ = xtptTUserMapper.selectByExample(example).get(0);
            } catch (Exception e) {
                e.printStackTrace();
                return result;
            }

            user_.setUuid(entity.getId());
            user_.setCourtCode(entity.getCourtCode());
            user_.setDeptNo(entity.getDepartment());
            user_.setDeptOrgCode(entity.getDeptOrgCode());
            //不同步 过去
//                user_.setUsername(entity.getUsername());
//                user_.setPassword(entity.getPassword());
//                user_.setUserType(entity.getUserType());
//                user_.setEnabled(true);
//                user_.setAccountNonExpired(true);
//                user_.setAccountNonLocked(true);
//                user_.setCredentialsNonExpired(true);
            if(entity.getSortNo() != null){
                //如果不过滤空，再修改编外人员提交时，会被 sortNo非空限制
                user_.setSortNo(entity.getSortNo());
            }
            user_.setFullname(entity.getFullname());
            user_.setGender(entity.getGender() == 1 ? true : false);
            user_.setNation(entity.getNation());
            user_.setBirthday(entity.getBirthday());
            user_.setCourtNo(entity.getCourtNo());
            user_.setIdcard(entity.getIdcard());
            user_.setHometown(entity.getHometown());
            user_.setBirthplace(entity.getBirthplace());
            user_.setCourtStdNo(entity.getCourtStdNo());
            user_.setEducationBackground(entity.getEducationBackground());
            user_.setFormerName(entity.getFormerName());
            user_.setPolitical(entity.getPolitical());
            user_.setPoliticalDate(entity.getPoliticalDate());
            user_.setMaritalStatus(entity.getMaritalStatus());
            user_.setPhysicalCondition(entity.getPhysicalCondition());
            user_.setUpdateTime(new Date());
            user_.setUpdateUser(entity.getUpdateUser());
            user_.setIsValid(entity.getIsValid());
            user_.setEnabled(entity.getIsValid() == 1 ? true : false);
            user_.setUserType(entity.getUserType());
            user_.setAdministrationPosition(entity.getAdministrationPosition());
            user_.setAdministrationPositionDate(entity.getAdministrationPositionDate());
            user_.setLawPosition(entity.getLawPosition());
            user_.setLawPositionDate(entity.getLawPositionDate());
            user_.setNationReport(entity.getNationReport());
            user_.setPoliticalReport(entity.getPoliticalReport());
            user_.setAdministrationPositionReport(entity.getAdministrationPositionReport());
            user_.setLawPositionReport(entity.getLawPositionReport());
            //电话号码 和 座机号码
            user_.setPhone(entity.getPhoneNumber());
            user_.setOfficePhone(entity.getExtOfficePhone());

            int i = xtptTUserMapper.updateByPrimaryKey(user_);
        }
        return result;
    }

    @Override
    public int countAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UmsUserInfo selectById(Object id) {
        return userInfoMapper.selectByPrimaryKey((String) id);
    }

    @Override
    public List<UmsUserInfo> search(UmsUserInfoCriteria criteria, RowBounds rowBounds) {
        List<UmsUserInfo> list = userInfoMapper.selectByExample(criteria);
        return list;
    }

    @Override
    public List<UmsUserInfo> getUserList(String fydm, Map<String, Object> parameters) {
        UmsUserInfoCriteria criteria = new UmsUserInfoCriteria();

        if (StringUtils.isNotEmpty(fydm)) {
            criteria.createCriteria().andCourtCodeEqualTo(fydm);
        }

        List<UmsUserInfo> list = userInfoMapper.selectByExample(criteria);
        return list;
    }

    @Override
    public int count(UmsUserInfoCriteria criteria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteById(Object id) {
        return userInfoMapper.deleteByPrimaryKey((String) id);
    }

    @Override
    public boolean changePassword(String id, String oldPassword, String newPassword, StringBuilder msg) {
        UmsUserInfo ui = userInfoMapper.selectByPrimaryKey(id);
        if (ui != null) {
            Md5PasswordEncoder Md5Checker = new Md5PasswordEncoder();
            String salt = String.valueOf((int) (Math.random() * 1000000));
            String rnewPassword = Md5Checker.encodePassword(newPassword, salt);
            if (Md5Checker.isPasswordValid(ui.getPassword(), oldPassword, ui.getSalt())) {
                ui.setPassword(rnewPassword);
                ui.setSalt(salt);
                if (userInfoMapper.updateByPrimaryKeyForChangePassword(ui) > 0) {
                    msg.append("密码修改成功！");
                    return true;
                } else {
                    msg.append("密码修改失败！");
                    return false;
                }
            } else {
                msg.append("原密码错误！");
                return false;
            }
        } else {
            msg.append("用户不存在！");
            return false;
        }
    }

    @Override
    public boolean changePasswordForAdmin(String id, String oldPassword, String newPassword, StringBuilder msg) {
        Map<String, Object> ui = userInfoMapper.searchVirtualUserByID(id);
        if (ui != null) {
            Md5PasswordEncoder Md5Checker = new Md5PasswordEncoder();
            String roldPassword = Md5Checker.encodePassword(oldPassword, "");
            String rnewPassword = Md5Checker.encodePassword(newPassword, "");
            if (ui.get("VU_PASSWORD").toString().equalsIgnoreCase(roldPassword)) {
                int i = 0;
                try {
                    i = umsUserInfoViewService.updatePassword(id, rnewPassword);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (i > 0) {
                    msg.append("密码修改成功！");
                    return true;
                } else {
                    msg.append("密码修改失败！");
                    return false;
                }
            } else {
                msg.append("原密码错误！");
                return false;
            }
        } else {
            msg.append("用户不存在！");
            return false;
        }
    }

    @Override
    public int updateByPrimaryKeySelective(UmsUserInfo userInfo) {
        return userInfoMapper.updateByPrimaryKeySelective(userInfo);
    }

    @Override
    public int updatePolitical(UmsUserInfo userInfo) {
        return userInfoMapper.updatePolitical(userInfo);
    }

    @Override
    public int updatePresent_info(Map map) {
        return userInfoMapper.updatePresent_info(map);
    }

    @Override
    public int updatePresent_infoByuser_id(Map map) {
        return userInfoMapper.updatePresent_infoByuser_id(map);
    }

    @Override
    public int updateAdministration(UmsUserInfo userInfo) {
        return userInfoMapper.updateAdministration(userInfo);
    }

    @Override
    public int updatePresent_info_adm(Map map) {
        return userInfoMapper.updatePresent_info_adm(map);
    }

    @Override
    public int updatePresent_info_adm_Byuser_id(Map map) {
        return userInfoMapper.updatePresent_info_adm_Byuser_id(map);
    }

    @Override
    public int updatePresent_info_law(Map map) {
        return userInfoMapper.updatePresent_info_law(map);
    }

    @Override
    public int updatePresent_info_law_Byuser_id(Map map) {
        return userInfoMapper.updatePresent_info_law_Byuser_id(map);
    }

    @Override
    public int updateRank(UmsUserInfo userInfo) {
        return userInfoMapper.updateRank(userInfo);
    }

    @Override
    public int updatePresent_info_rank(Map map) {
        return userInfoMapper.updatePresent_info_rank(map);
    }

    @Override
    public int updatePresent_info_rank_Byuser_id(Map map) {
        return userInfoMapper.updatePresent_info_rank_Byuser_id(map);
    }

    @Override
    public int updateLevel(UmsUserInfo userInfo) {
        return userInfoMapper.updateLevel(userInfo);
    }

    @Override
    public int updatePresent_info_level(Map map) {
        return userInfoMapper.updatePresent_info_level(map);
    }

    @Override
    public int updatePresent_info_level_Byuser_id(Map map) {
        return userInfoMapper.updatePresent_info_level_Byuser_id(map);
    }

    @Override
    public int updateServant_level(UmsUserInfo userInfo) {
        return userInfoMapper.updateServant_level(userInfo);
    }

    @Override
    public int updatePresent_info_servant_level(Map map) {
        return userInfoMapper.updatePresent_info_servant_level(map);
    }

    @Override
    public int updatePresent_info_servant_level_Byuser_id(Map map) {
        return userInfoMapper.updatePresent_info_servant_level_Byuser_id(map);
    }


    @Override
    public int insertJobById(UmsChangeJob changeJob) {
        return userInfoMapper.insertJobById(changeJob);
    }

    @Override
    public int updateJobById(UmsChangeJob changeJob) {
        return userInfoMapper.updateJobById(changeJob);
    }

    @Transactional
    @Override
    public int updateXtptAndInfo(UmsChangeJob changeJob) {
//        return userInfoMapper.updateXtptAndInfo(changeJob);
        int result = userInfoMapper.updateUmsUserInfo(changeJob);
        return userInfoMapper.updateXtptInfo(changeJob);
    }

    @Override
    public List<UmsChangeJob> selectChangeJob(UmsChangeJob umsChangeJob, RowBounds rowBounds) {
        return userInfoMapper.selectChangeJob(umsChangeJob, rowBounds);
    }

    public int countChangeJob(UmsChangeJob umsChangeJob) {
        return userInfoMapper.countChangeJob(umsChangeJob);
    }

    @Override
    public int updateTime(UmsUserInfo info) {
        return userInfoMapper.updateTime(info);
    }

    @Override
    public List<UmsUserInfo> selectAll(Map param) {
        return userInfoMapper.selectAll(param);
    }

    @Override
    public int countByExample(UmsUserInfoCriteria example){
        return userInfoMapper.countByExample(example);
    }

    @Transactional
    @Override
    public int updateXtptAndUserDeptCode(Map map) {
//        return userInfoMapper.updateXtptAndUserDeptCode(map);
        int result = userInfoMapper.updateUserDeptCode(map);
        return userInfoMapper.updateXtptDeptCode(map);
    }

    @Override
    public List<Map> customSearch(Map param)
    {
        return userInfoMapper.customSearch(param);
    }

    @Override
    public List<Map> customSearchForBw(Map param) {
        return userInfoMapper.customSearchForBw(param);
    }

    @Override
    public List<Map> customSearchForPsy(Map param) {
        return userInfoMapper.customSearchForPsy(param);
    }

    @Override
    public List<Map> cunstomSearch_nnd(Map param)
    {
        return userInfoMapper.cunstomSearch_nnd(param);
    }

    @Override
    public List<Map> customSearchForAgeBw(Map param) {
        return userInfoMapper.customSearchForAgeBw(param);
    }

    @Override
    public List<Map> customSearchForAgePsy(Map param) {
        return userInfoMapper.customSearchForAgePsy(param);
    }

    @Override
    public List<Map> customSearch_avgnnd(Map param) {
        return userInfoMapper.customSearch_avgnnd(param);
    }

    @Override
    public int updateUserIndex1(Map param)
    {
        return userInfoMapper.updateUserIndexBatch1(param);
    }

    @Override
    public int updateUserIndex2(Map param)
    {
        return userInfoMapper.updateUserIndexBatch2(param);
    }

    @Override
    public List<UmsUserInfo> selectUserInWhere1(Map param)
    {
        return userInfoMapper.selectUserInWhere1(param);
    }

    @Override
    public List<UmsUserInfo> selectUserInWhere2(Map param)
    {
        return userInfoMapper.selectUserInWhere2(param);
    }

    @Override
    public List<Map> countYefgGroupbyDept(Map param) {
        String deptName = param.get("deptName") != null ? param.get("deptName").toString() : null;
        String authcourt = param.get("authcourt") != null ? param.get("authcourt").toString() : null;
        String groupBy = param.get("groupBy") != null ? param.get("groupBy").toString() : null;
        return userInfoMapper.countYefgGroupbyDept(deptName, authcourt, groupBy);
    }

    @Override
    public List<Map> countyefg(Map param) {
        return userInfoMapper.countyefg(param);
    }

    @Override
    public List<Map> counttefg(Map param) {
        return userInfoMapper.counttefg(param);
    }

    @Override
    public List<Map> countEveryFg(Map param) {
        return userInfoMapper.countEveryFg(param);
    }

    @Override
    public List<Map> countEveryTeFg(Map param) {
        return userInfoMapper.countEveryTeFg(param);
    }

    @Override
    public int updatePresent_info_education(Map map) {
        return userInfoMapper.updatePresent_info_education(map);
    }

    @Override
    public int updatePresent_info_education_Byuser_id(Map map) {
        return userInfoMapper.updatePresent_info_education_Byuser_id(map);
    }

    @Override
    public int updateEducation(UmsUserInfo record) {
        return userInfoMapper.updateEducation(record);
    }

    @Override
    public int updatePresent_info_degree(Map map) {
        return userInfoMapper.updatePresent_info_degree(map);
    }

    @Override
    public int updatePresent_info_degree_Byuser_id(Map map) {
        return userInfoMapper.updatePresent_info_degree_Byuser_id(map);
    }
}
