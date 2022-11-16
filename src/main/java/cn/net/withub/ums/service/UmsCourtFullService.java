/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service;

import cn.net.withub.ums.entity.UmsCode;
import cn.net.withub.ums.entity.UmsCourtFull;
import cn.net.withub.ums.entity.UmsCourtFullCriteria;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Diluka
 */
public interface UmsCourtFullService extends BaseService<UmsCourtFull, UmsCourtFullCriteria> {

    List<UmsCourtFull> listAllCourts();

    List<UmsCourtFull> listArea();

    List<UmsCourtFull> listAll();

    /**
     * 按法院层次查询
     *
     * @param gradation 层次 0,1,2
     * @return
     */
    List<UmsCourtFull> listByGradation(Integer gradation);

    /**
     * 根据用户的权限获取可访问的法院
     * @param userID
     * @return
     */
    List<Map<String,Object>> listByAuthUserID(String userID);
    /**
     * 按地区查询
     *
     * @param areaNo
     * @return
     */
    List<UmsCourtFull> listByArea(Integer areaNo);

    /**
     * 查询某法院子级
     *
     * @param parent
     * @return
     */
    List<UmsCourtFull> listSubByCourt(UmsCourtFull parent);

    /**
     * 查询某法院子级
     *
     * @param courtStdNo
     * @return
     */
    List<UmsCourtFull> listChildrenByCourtStdNo(Integer courtStdNo);

    /**
     * 查询某法院本身
     *
     * @param courtStdNo
     * @return
     */
    UmsCourtFull getCourtByCourtStdNo(Integer courtStdNo);

    /**
     * 查询某法院子级
     *
     * @param courtNo
     * @return
     */
    List<UmsCourtFull> listChildrenByCourtNo(Integer courtNo);


    List<UmsCourtFull> selectByList(List courtList);

    //查询全部法院
    List<UmsCourtFull> selectByListAll();

    UmsCourtFull getCourtByCourtCode(String courtCode);

    //根据地方编制和中央编制和权限来查询法院
    List<UmsCourtFull> getCourtByOrganization(List<String> courtList, List<Integer> codeList);

    List<UmsCourtFull> selectByExample(UmsCourtFullCriteria criteria);

    int countByExample(UmsCourtFullCriteria criteria);

    List<UmsCourtFull> selectCourtForNOLeaderDept(Map<String,Object> map);

    int countCourtForNOLeaderDept(Map<String,Object> map);

    int updateByPrimaryKeySelective(UmsCourtFull record);

    List<UmsCode> getUmsCodeFy();
    List<UmsCode> getUmsCodeFy_2(List<Integer> court_no_list);
}
