/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.dao.UmsUserInfoViewMapper;
import cn.net.withub.ums.entity.UmsUserInfoExternalCriteria;
import cn.net.withub.ums.entity.UmsUserInfoView;
import cn.net.withub.ums.entity.UmsUserInfoViewCriteria;
import cn.net.withub.ums.service.UmsCourtService;
import cn.net.withub.ums.service.UmsUserInfoViewService;
import cn.net.withub.ums.util.CriteriaTools;
import cn.net.withub.ums.util.Md5PasswordEncoder;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UmsUserInfoViewServiceImpl implements UmsUserInfoViewService {
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private UmsUserInfoViewMapper userInfoViewMapper;

    @Autowired
    private UmsCourtService courtService;

    @Override
    public int insert(UmsUserInfoView entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(UmsUserInfoView entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(UmsUserInfoView entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int countAll() {
        UmsUserInfoViewCriteria criteria = new UmsUserInfoViewCriteria();
        criteria.createCriteria().getAllCriteria();

        return userInfoViewMapper.countByExample(criteria);
    }

    @Override
    public List<UmsUserInfoView> search(Map<String, Object[]> conditions, int page, int pageSize) {
        UmsUserInfoViewCriteria criteria = new UmsUserInfoViewCriteria();

        CriteriaTools.loadMap(criteria, conditions);

        return userInfoViewMapper.selectByExample(criteria, new RowBounds((page - 1) * pageSize, pageSize));
    }

    @Override
    public int count(Map<String, Object[]> conditions) {
        UmsUserInfoViewCriteria criteria = new UmsUserInfoViewCriteria();
        CriteriaTools.loadMap(criteria.createCriteria(), conditions);
        return userInfoViewMapper.countByExample(criteria);
    }

    @Override
    public UmsUserInfoView selectById(Object id) {
        UmsUserInfoViewCriteria criteria = new UmsUserInfoViewCriteria();
        criteria.createCriteria().andIdEqualTo((String) id);

        //
        List<UmsUserInfoView> list = userInfoViewMapper.selectByExample(criteria);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<UmsUserInfoView> search(UmsUserInfoViewCriteria criteria, RowBounds rowBounds) {
        return userInfoViewMapper.selectByExample(criteria, rowBounds);
    }

    @Override
    public int count(UmsUserInfoViewCriteria criteria) {
        return userInfoViewMapper.countByExample(criteria);
    }

    // 待完善资料的人员数量
    @Override
    public int count_dwszl(UmsUserInfoViewCriteria criteria) {
        return userInfoViewMapper.countByExample_dwszl(criteria);
    }

    @Override
    public int deleteById(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UmsUserInfoView login(Integer courtStdNo, String username, String password) {
        UmsUserInfoViewCriteria criteria = new UmsUserInfoViewCriteria();
        criteria.createCriteria().andUsernameEqualTo(username).andCourtStdNoEqualTo(courtStdNo).andIsValidEqualTo(1);
        // 启用状态
        List<UmsUserInfoView> list = userInfoViewMapper.selectByExample(criteria);
        Md5PasswordEncoder Md5Checker = new Md5PasswordEncoder();
        if (list.size() > 0) {
            return Md5Checker.isPasswordValid(list.get(0).getPassword(), password, list.get(0).getSalt()) ? list.get(0) : null;
        } else {
            return null;
        }
    }

    /**
     * 管理员登录
     *
     * @param courtStdNo 承办法院编号
     * @param username   用户名
     * @param password   密码
     * @return
     */
    @Override
    public UmsUserInfoView login4Admin(Integer courtStdNo, String username, String password) {
        UmsUserInfoView userInfoView = null;
        try {
            Md5PasswordEncoder Md5Checker = new Md5PasswordEncoder();
            String rpassword = Md5Checker.encodePassword(password,"");

            Map<String,Object> parameters = new HashMap<>();
            parameters.put("VU_USERCODE",username);
            parameters.put("VU_PASSWORD",rpassword);
            parameters.put("COURT_STD_CODE",courtStdNo);

            List<Map<String,Object>> list = sqlSession.selectList("cn.net.withub.ums.dao.UmsUserInfoMapper.search_virtualUser", parameters);

            if(list != null && list.size() > 0){
                Map<String,Object> map  = list.get(0);
                userInfoView = new UmsUserInfoView();
                userInfoView.setId(map.get("ID").toString());
                userInfoView.setFullname(map.get("VU_NAME").toString());
                userInfoView.setCourtStdNo(Integer.valueOf(map.get("COURT_STD_CODE").toString()));
                userInfoView.setCourtNo(courtService.courtNoForCourtStdNo(Integer.valueOf(map.get("COURT_STD_CODE").toString())));
                userInfoView.setCourtNoText(courtService.courtTexForCourtStdNo(Integer.valueOf(map.get("COURT_STD_CODE").toString())));
                userInfoView.setUsername(map.get("VU_USERCODE").toString());
                userInfoView.setCourtCode(map.get("fydm").toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return userInfoView;
    }

    @Override
    public UmsUserInfoView loginInterface(Integer courtStdNo, String user_id, String password) {
        UmsUserInfoViewCriteria criteria = new UmsUserInfoViewCriteria();
        criteria.createCriteria()
                .andCourtStdNoEqualTo(courtStdNo)
                .andUserIdEqualTo(user_id)
        .andIsValidEqualTo(1);
        //启用状态
        List<UmsUserInfoView> list = userInfoViewMapper.selectByExample(criteria);
        return list.size() > 0 ? list.get(0) : null;

    }

    @Override
    public List<UmsUserInfoView> newEnterPersonnelList(int size,Integer courtNo) {
        UmsUserInfoViewCriteria criteria = new UmsUserInfoViewCriteria();
        criteria.createCriteria().getAllCriteria();
        criteria.setCourtNo(courtNo);
        criteria.setOrderByClause("enter_date desc");
        RowBounds rowBounds = new RowBounds(0, size);

        return userInfoViewMapper.selectByExample(criteria, rowBounds);
    }


    @Override
    public int updatePassword(String userCode, String password) throws Exception {

        try {
            Map<String,Object> parameters = new HashMap<>();
            parameters.put("ID",userCode);
            parameters.put("VU_PASSWORD",password);

            return sqlSession.update("cn.net.withub.ums.dao.UmsUserInfoMapper.update_virtualUser_password", parameters);
        } catch (Exception e) {
            e.printStackTrace();
            return  0;
        }
    }

    /**
     * 通过登录名和法院信息获取用户
     *
     * @param courtStdNo
     * @param username
     * @return
     */
    @Override
    public Map<String, Object> searchByLoginInfo(Integer courtStdNo, String username,Integer valid) {
        Map<String, Object> map = null;
        try {
            Map<String,Object> parameters = new HashMap<>();
            parameters.put("USERNAME",username);
            parameters.put("COURT_STD_NO",courtStdNo);
            if(valid != null){
                parameters.put("ISVALID",valid);
            }

            List<Map<String,Object>> list = sqlSession.selectList("cn.net.withub.ums.dao.UmsUserInfoMapper.search_user_by_loginInfo", parameters);


            if(list != null && list.size() > 0){
                map = list.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    /**
     * 判断user_info表username是否重复
     * @param courtStdNo
     * @param username
     * @param valid
     * @return
     */
    @Override
    public Map<String, Object> searchByLoginInfo_2(Integer courtStdNo, String username,Integer valid) {
        Map<String, Object> map = null;
        try {
            Map<String,Object> parameters = new HashMap<>();
            parameters.put("USERNAME",username);
            parameters.put("COURT_STD_NO",courtStdNo);
            if(valid != null){
                parameters.put("ISVALID",valid);
            }

            List<Map<String,Object>> list = sqlSession.selectList("cn.net.withub.ums.dao.UmsUserInfoMapper.search_user_by_loginInfo_2", parameters);


            if(list != null && list.size() > 0){
                map = list.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    /**
     * userinfo平台username是否重复, 编辑时用
     * @param username
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> searchByLoginInfo_umsinfo(String username ,String id) {
        Map<String, Object> map = null;
        try {
            Map<String,Object> parameters = new HashMap<>();
            parameters.put("USERNAME",username);
            parameters.put("id", id);

            List<Map<String,Object>> list = sqlSession.selectList("cn.net.withub.ums.dao.UmsUserInfoMapper.search_user_by_loginInfo_userinfo", parameters);


            if(list != null && list.size() > 0){
                map = list.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    /**
     * 编辑时,查询该username,是否等于自己
     * @param username
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> searchByLoginInfo_umsinfo_2(String username ,String id) {
        Map<String, Object> map = null;
        try {
            Map<String,Object> parameters = new HashMap<>();
            parameters.put("USERNAME",username);
            parameters.put("id", id);

            List<Map<String,Object>> list = sqlSession.selectList("cn.net.withub.ums.dao.UmsUserInfoMapper.search_user_by_loginInfo_userinfo_2", parameters);


            if(list != null && list.size() > 0){
                map = list.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    @Override
    public List<UmsUserInfoView> selectViewByExample(UmsUserInfoViewCriteria criteria) {
        return userInfoViewMapper.selectByExample(criteria);
    }

    @Override
    public List<UmsUserInfoView> selectCustom(String info,RowBounds rowBounds){
        return userInfoViewMapper.selectCustom(info,rowBounds);
    }
    @Override
    public int countCustom(String info){
        return userInfoViewMapper.countCustom(info);
    }

    @Override
    public List<UmsUserInfoView> selectCustomN(String info,RowBounds rowBounds){
        return userInfoViewMapper.selectCustomN(info,rowBounds);
    }

    @Override
    public List<UmsUserInfoView> selectCustomN_(String info, RowBounds rowBounds) {
        return userInfoViewMapper.selectCustomN_(info,rowBounds);
    }

    @Override
    public int countCustomN(String info){
        return userInfoViewMapper.countCustomN(info);
    }

    @Override
    public List<UmsUserInfoView> selectCustomM(String info,RowBounds rowBounds){
        return userInfoViewMapper.selectCustomM(info,rowBounds);
    }

    @Override
    public List<Map<String, Object>> selectCustomF(String info) {
        return userInfoViewMapper.selectCustomF(info);
    }

    @Override
    public List<Map<String, Object>> selectCustomG(String info) {
        return userInfoViewMapper.selectCustomG(info);
    }

    @Override
    public List<UmsUserInfoView> selectCustomK(String info, RowBounds rowBounds) {
        return userInfoViewMapper.selectCustomK(info,rowBounds);
    }

    @Override
    public int countCustomK(String info) {
        return userInfoViewMapper.countCustomK(info);
    }

    @Override
    public int countCustomM(String info){
        return userInfoViewMapper.countCustomM(info);
    }

    @Override
    public List<Map> seleteJurorInfo(UmsUserInfoViewCriteria example) {
        return userInfoViewMapper.seleteJurorInfo(example);
    }

    @Override
    public List<Map> seleteOffStaffInfo(UmsUserInfoViewCriteria example) {
        return userInfoViewMapper.seleteOffStaffInfo(example);
    }

    @Override
    public List<UmsUserInfoView> searchBySort(UmsUserInfoViewCriteria example, RowBounds rowBounds) {
        return userInfoViewMapper.searchBySort(example,rowBounds);
    }

    @Override
    public List<UmsUserInfoView> simpleSearchBySort(UmsUserInfoViewCriteria example, RowBounds rowBounds) {
        return userInfoViewMapper.simpleSearchBySort(example,rowBounds);
    }

    @Override
    public List<UmsUserInfoView> searchBySort_dwszl(UmsUserInfoViewCriteria example, RowBounds rowBounds) {
        return userInfoViewMapper.searchBySort_dwszl(example,rowBounds);
    }

    @Override
    public List<UmsUserInfoView> simpleSearchBySort_dwszl(UmsUserInfoViewCriteria example, RowBounds rowBounds) {
        return userInfoViewMapper.simpleSearchBySort_dwszl(example,rowBounds);
    }

    @Override
    public List<Map> selectBatchById(List ids) {
        return userInfoViewMapper.selectBatchById(ids);
    }

    @Override
    public Integer unionCount(Map<String, Object> map) {
        return userInfoViewMapper.unionCount(map);
    }

    @Override
    public List<UmsUserInfoView> unionQuery(Map<String, Object> map) {
        return userInfoViewMapper.unionQuery(map);
    }

    @Override
    public Integer incompleteCountForLegal(Map<String, Object> map) {
        return userInfoViewMapper.incompleteCountForLegal(map);
    }

    @Override
    public List<UmsUserInfoView> incompleteQueryForLegal(Map<String, Object> map) {
        return userInfoViewMapper.incompleteQueryForLegal(map);
    }

    @Override
    public Integer incompleteCountForPolitical(Map<String, Object> map) {
        return userInfoViewMapper.incompleteCountForPolitical(map);
    }

    @Override
    public List<UmsUserInfoView> incompleteQueryForPolitical(Map<String, Object> map) {
        return userInfoViewMapper.incompleteQueryForPolitical(map);
    }

    @Override
    public Integer incompleteCountForFamily(Map<String, Object> map) {
        return userInfoViewMapper.incompleteCountForFamily(map);
    }

    @Override
    public List<UmsUserInfoView> incompleteQueryForFamily(Map<String, Object> map) {
        return userInfoViewMapper.incompleteQueryForFamily(map);
    }

    @Override
    public Integer incompleteCountForYefg(Map<String, Object> map) {
        return userInfoViewMapper.incompleteCountForYefg(map);
    }

    @Override
    public List<UmsUserInfoView> incompleteQueryForYefg(Map<String, Object> map) {
        return userInfoViewMapper.incompleteQueryForYefg(map);
    }

    @Override
    public int countForAspect(UmsUserInfoViewCriteria criteria) {
        return userInfoViewMapper.countByExample(criteria);
    }

    @Override
    public List<UmsUserInfoView> getInfoByExample(UmsUserInfoViewCriteria example, RowBounds rowBounds) {
        return userInfoViewMapper.selectByExample(example,rowBounds);
    }

    // 反查法官列表
    @Override
    public List<UmsUserInfoView> selectFgList_Fc(Map<String, Object> param) {
        return userInfoViewMapper.selectFgList_Fc(param);
    }

    // 反查法官数量
    @Override
    public Integer selectFgCount_Fc(Map<String, Object> param) {
        return userInfoViewMapper.selectFgCount_Fc(param);
    }

    @Override
    public int countByExampleLeftJoinExternal(UmsUserInfoExternalCriteria example) {
        return userInfoViewMapper.countByExampleLeftJoinExternal(example);
    }

    @Override
    public List<UmsUserInfoView> selectByExampleLeftJoinExternal(UmsUserInfoExternalCriteria example) {
        return userInfoViewMapper.selectByExampleLeftJoinExternal(example);
    }

    @Override
    public int countByExampleLeftJoinPsy(UmsUserInfoExternalCriteria example) {
        return userInfoViewMapper.countByExampleLeftJoinPsy(example);
    }

    @Override
    public List<UmsUserInfoView> selectByExampleLeftJoinPsy(UmsUserInfoExternalCriteria example) {
        return userInfoViewMapper.selectByExampleLeftJoinPsy(example);
    }

    @Override
    public List<Map<String, Object>> selectRyList(Map<String, Object> map) {
        return userInfoViewMapper.selectRyList(map);
    }

    @Override
    public List<Map> countByCourtNo(Integer courtNo) {
        return userInfoViewMapper.countByCourtNo(courtNo);
    }
}
