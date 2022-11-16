/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service.footprint.impl;

import cn.net.withub.ums.dao.UmsAbroadInfoViewMapper;
import cn.net.withub.ums.dao.UmsAdministrativeJobViewMapper;
import cn.net.withub.ums.dao.UmsAssessInfoViewMapper;
import cn.net.withub.ums.dao.UmsDegreeInfoViewMapper;
import cn.net.withub.ums.dao.UmsLegalJobViewMapper;
import cn.net.withub.ums.dao.UmsRankInfoViewMapper;
import cn.net.withub.ums.dao.UmsTrainingInfoViewMapper;
import cn.net.withub.ums.entity.UmsAbroadInfoView;
import cn.net.withub.ums.entity.UmsAbroadInfoViewCriteria;
import cn.net.withub.ums.entity.UmsAdministrativeJobView;
import cn.net.withub.ums.entity.UmsAdministrativeJobViewCriteria;
import cn.net.withub.ums.entity.UmsAssessInfoView;
import cn.net.withub.ums.entity.UmsAssessInfoViewCriteria;
import cn.net.withub.ums.entity.UmsDegreeInfoView;
import cn.net.withub.ums.entity.UmsDegreeInfoViewCriteria;
import cn.net.withub.ums.entity.UmsLegalJobView;
import cn.net.withub.ums.entity.UmsLegalJobViewCriteria;
import cn.net.withub.ums.entity.UmsRankInfoView;
import cn.net.withub.ums.entity.UmsRankInfoViewCriteria;
import cn.net.withub.ums.entity.UmsTrainingInfoView;
import cn.net.withub.ums.entity.UmsTrainingInfoViewCriteria;
import cn.net.withub.ums.entity.UmsUserInfo;
import cn.net.withub.ums.entity.UmsUserInfoView;
import cn.net.withub.ums.service.UmsAttachedTableService;
import cn.net.withub.ums.service.UmsUserInfoViewService;
import cn.net.withub.ums.service.footprint.FootprintService;
import cn.net.withub.ums.model.footprint.Footprint;
import cn.net.withub.ums.model.footprint.FootprintType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FootprintServiceImpl implements FootprintService {

    @Autowired
    private UmsUserInfoViewService userInfoViewService;

    @Autowired
    private UmsAttachedTableService attachedTableService;

    //<editor-fold defaultstate="collapsed" desc="附表Mapper">
    //↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓附表Mapper↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
    @Autowired
    private UmsAbroadInfoViewMapper abroadInfoViewMapper;

    @Autowired
    private UmsAdministrativeJobViewMapper administrativeJobViewMapper;

    @Autowired
    private UmsLegalJobViewMapper legalJobViewMapper;

    @Autowired
    private UmsAssessInfoViewMapper assessInfoViewMapper;

    @Autowired
    private UmsDegreeInfoViewMapper degreeInfoViewMapper;

    @Autowired
    private UmsRankInfoViewMapper rankInfoViewMapper;

    @Autowired
    private UmsTrainingInfoViewMapper trainingInfoViewMapper;

    //↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑附表Mapper↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
    //</editor-fold>
    @Override
    public List<Footprint> userFootprints(String userId) {
        return userFootprints(userId, null);
    }

    @Override
    public List<Footprint> userFootprints(UmsUserInfo userInfo) {
        List<Footprint> footprints = userFootprints(userInfo.getId(), null);
        return footprints;
    }

    @Override
    public List<Footprint> userFootprints(UmsUserInfoView userInfo) {
        List<Footprint> footprints = userFootprints(userInfo.getId(), userInfo);
        return footprints;
    }

    //<editor-fold defaultstate="collapsed" desc="不再使用的">
    /**
     * 附表信息
     *
     * @param userId
     * @param footprints
     */
    @Deprecated
    private void loadAttachedInfo(String userId, List<Footprint> footprints) {
        for (FootprintType type : FootprintType.values()) {
            //跳过主表内容
            switch (type) {
                case ENTER_TIME:
                    continue;
                case ABROAD:
                    List<UmsAbroadInfoView> abroadInfoViews = attachedTableService.selectView(type.mapperClass, userId);
                    if (abroadInfoViews == null) {
                        continue;
                    }
                    for (UmsAbroadInfoView view : abroadInfoViews) {
                        Footprint fp = new Footprint();
                        fp.setType(type);
                        fp.setTimePoint(view.getdStartDate());
                        fp.setContent(String.format("出国去了%s",
                                view.getnCountryText()));

                        footprints.add(fp);

                        fp = new Footprint();
                        fp.setType(type);
                        fp.setTimePoint(view.getdEndDate());
                        fp.setContent(String.format("从%s归来",
                                view.getnCountryText()));

                        footprints.add(fp);
                    }
                    break;
                case ADMIN_JOB:
                    List<UmsAdministrativeJobView> administrativeJobViews = attachedTableService.selectView(type.mapperClass, userId);
                    if (administrativeJobViews == null) {
                        continue;
                    }
                    for (UmsAdministrativeJobView view : administrativeJobViews) {
                        //跳过免职- -
                        if (view.getnAssignTypeText().equals("免")
                                || view.getnJobText().startsWith("无")) {
                            continue;
                        }

                        Footprint fp = new Footprint();
                        fp.setType(type);
                        fp.setTimePoint(view.getdAssignDate());
                        fp.setContent(String.format("开始担任%s",
                                view.getnJobText()));

                        footprints.add(fp);
                    }
                    break;
                case LEGAL_JOB:
                    List<UmsLegalJobView> legalJobViews = attachedTableService.selectView(type.mapperClass, userId);
                    if (legalJobViews == null) {
                        continue;
                    }
                    for (UmsLegalJobView view : legalJobViews) {
                        //跳过免职- -
                        //小角色过过过
                        if (view.getnAssignTypeText().equals("免")
                                || view.getnJobText().startsWith("无")) {
                            continue;
                        }

                        Footprint fp = new Footprint();
                        fp.setType(type);
                        fp.setTimePoint(view.getdAssignDate());
                        fp.setContent(String.format("开始担任%s",
                                view.getnJobText()));

                        footprints.add(fp);
                    }
                    break;
                case ASSESS:
                    List<UmsAssessInfoView> assessInfoViews = attachedTableService.selectView(type.mapperClass, userId);
                    if (assessInfoViews == null) {
                        continue;
                    }
                    for (UmsAssessInfoView view : assessInfoViews) {

                        if (!view.getnResult().equals("1")) {//只要优秀，呵呵
                            continue;
                        }

                        Footprint fp = new Footprint();
                        fp.setType(type);
                        try {
                            fp.setTimePoint(new SimpleDateFormat("yyyy").parse(view.getnYear().toString()));
                        } catch (ParseException ex) {
                            Logger.getLogger(FootprintServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                            continue;//时间不对就不要了
                        }
                        fp.setContent(String.format("通过年度考核，考核结果：%s",
                                view.getnResultText()));

                        footprints.add(fp);
                    }
                    break;
                case DEGREE:
                    List<UmsDegreeInfoView> degreeInfoViews = attachedTableService.selectView(type.mapperClass, userId);
                    if (degreeInfoViews == null) {
                        continue;
                    }
                    for (UmsDegreeInfoView view : degreeInfoViews) {
                        Footprint fp = new Footprint();
                        fp.setType(type);
                        fp.setTimePoint(view.getdGraduateDate());
                        fp.setContent(String.format("从%s毕业了，获得了%s专业的%s学位",
                                view.getcCollege(), view.getnMajorText(), view.getnDegreeText()));

                        footprints.add(fp);

                        fp = new Footprint();
                        fp.setType(type);
                        fp.setTimePoint(view.getdEntryDate());
                        fp.setContent(String.format("进入%s开始了对%s专业的学习",
                                view.getcCollege(), view.getnMajorText(), view.getnDegreeText()));

                        footprints.add(fp);
                    }
                    break;

                case RANK:
                    List<UmsRankInfoView> rankInfoViews = attachedTableService.selectView(type.mapperClass, userId);
                    if (rankInfoViews == null) {
                        continue;
                    }
                    for (UmsRankInfoView view : rankInfoViews) {
                        //跳过免职- -
                        if (view.getnAssignTypeText().equals("免")) {
                            continue;
                        }

                        Footprint fp = new Footprint();
                        fp.setType(type);
                        fp.setTimePoint(view.getdAssignDate());
                        fp.setContent(String.format("职级变更为%s",
                                view.getnRankText()));

                        footprints.add(fp);
                    }
                    break;

                case TRAINING:
                    List<UmsTrainingInfoView> trainingInfoViews = attachedTableService.selectView(type.mapperClass, userId);
                    if (trainingInfoViews == null) {
                        continue;
                    }
                    for (UmsTrainingInfoView view : trainingInfoViews) {
                        Footprint fp = new Footprint();
                        fp.setType(type);
                        fp.setTimePoint(view.getdEndDate());
                        fp.setContent(String.format("完成了%s的培训",
                                view.getnSpecialtyText()));

                        footprints.add(fp);
                    }
                    break;
            }

        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="各个附表加载方法">
    /**
     * 加载出国信息
     *
     * @param userInfoView
     * @param footprints
     */
    private void loadAbroadInfo(UmsUserInfoView userInfoView, List<Footprint> footprints) {
        UmsAbroadInfoViewCriteria criteria = new UmsAbroadInfoViewCriteria();

        criteria.createCriteria()
                .andUserIdEqualTo(userInfoView.getId())
                .andDStartDateGreaterThan(userInfoView.getEnterDate());

        List<UmsAbroadInfoView> list = abroadInfoViewMapper.selectByExample(criteria);

        for (UmsAbroadInfoView view : list) {
            Footprint fp = new Footprint();
            fp.setType(FootprintType.ABROAD);
            fp.setTimePoint(view.getdStartDate());
            fp.setContent(String.format("出国去了%s",
                    view.getnCountryText()));

            fp.setEntity(view);
            footprints.add(fp);

            fp = new Footprint();
            fp.setType(FootprintType.ABROAD);
            fp.setTimePoint(view.getdEndDate());
            fp.setContent(String.format("从%s归来",
                    view.getnCountryText()));

            fp.setEntity(view);
            footprints.add(fp);
        }
    }

    /**
     * 加载行政职务
     *
     * @param userInfoView
     * @param footprints
     */
    private void loadAdminJob(UmsUserInfoView userInfoView, List<Footprint> footprints) {
        UmsAdministrativeJobViewCriteria criteria = new UmsAdministrativeJobViewCriteria();

        criteria.createCriteria()
                .andUserIdEqualTo(userInfoView.getId())
                .andNAssignTypeTextEqualTo("任")
                .andNJobTextNotLike("无%")
                .andDAssignDateGreaterThan(userInfoView.getEnterDate());

        List<UmsAdministrativeJobView> list = administrativeJobViewMapper.selectByExample(criteria);

        for (UmsAdministrativeJobView view : list) {
            Footprint fp = new Footprint();
            fp.setType(FootprintType.ADMIN_JOB);
            fp.setTimePoint(view.getdAssignDate());
            fp.setContent(String.format("开始担任%s",
                    view.getnJobText()));

            fp.setEntity(view);
            footprints.add(fp);
        }
    }

    /**
     * 加载法律职务
     *
     * @param userInfoView
     * @param footprints
     */
    private void loadLegalJob(UmsUserInfoView userInfoView, List<Footprint> footprints) {
        UmsLegalJobViewCriteria criteria = new UmsLegalJobViewCriteria();

        criteria.createCriteria()
                .andUserIdEqualTo(userInfoView.getId())
                .andNAssignTypeTextEqualTo("任")
                .andNJobTextNotLike("无%")
                .andDAssignDateGreaterThan(userInfoView.getEnterDate());

        List<UmsLegalJobView> list = legalJobViewMapper.selectByExample(criteria);

        for (UmsLegalJobView view : list) {
            Footprint fp = new Footprint();
            fp.setType(FootprintType.LEGAL_JOB);
            fp.setTimePoint(view.getdAssignDate());
            fp.setContent(String.format("开始担任%s",
                    view.getnJobText()));

            fp.setEntity(view);
            footprints.add(fp);
        }
    }

    /**
     * 加载考核
     *
     * @param userInfoView
     * @param footprints
     */
    private void loadAssess(UmsUserInfoView userInfoView, List<Footprint> footprints) {
        UmsAssessInfoViewCriteria criteria = new UmsAssessInfoViewCriteria();

        criteria.createCriteria()
                .andUserIdEqualTo(userInfoView.getId())
                .andNResultEqualTo("1");

        List<UmsAssessInfoView> list = assessInfoViewMapper.selectByExample(criteria);

        for (UmsAssessInfoView view : list) {
            Footprint fp = new Footprint();
            fp.setType(FootprintType.ASSESS);
            try {
                fp.setTimePoint(new SimpleDateFormat("yyyy").parse(view.getnYear().toString()));
            } catch (ParseException ex) {
                Logger.getLogger(FootprintServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                continue;//时间不对就不要了
            }
            fp.setContent(String.format("通过年度考核，考核结果：%s",
                    view.getnResultText()));

            fp.setEntity(view);
            footprints.add(fp);
        }
    }

    /**
     * 加载学位
     *
     * @param userInfoView
     * @param footprints
     */
    private void loadDegree(UmsUserInfoView userInfoView, List<Footprint> footprints) {
        UmsDegreeInfoViewCriteria criteria = new UmsDegreeInfoViewCriteria();

        criteria.createCriteria()
                .andUserIdEqualTo(userInfoView.getId())
                .andDEntryDateGreaterThan(userInfoView.getEnterDate());

        List<UmsDegreeInfoView> list = degreeInfoViewMapper.selectByExample(criteria);

        for (UmsDegreeInfoView view : list) {
            Footprint fp = new Footprint();
            fp.setType(FootprintType.DEGREE);
            fp.setTimePoint(view.getdGraduateDate());
            fp.setContent(String.format("从%s毕业了，获得了%s专业的%s学位",
                    view.getcCollege(), view.getnMajorText(), view.getnDegreeText()));

            fp.setEntity(view);
            footprints.add(fp);

            fp = new Footprint();
            fp.setType(FootprintType.DEGREE);
            fp.setTimePoint(view.getdEntryDate());
            fp.setContent(String.format("进入%s开始了对%s专业的学习",
                    view.getcCollege(), view.getnMajorText(), view.getnDegreeText()));

            fp.setEntity(view);
            footprints.add(fp);
        }
    }

    /**
     * 加载职级
     *
     * @param userInfoView
     * @param footprints
     */
    private void loadRank(UmsUserInfoView userInfoView, List<Footprint> footprints) {
        UmsRankInfoViewCriteria criteria = new UmsRankInfoViewCriteria();

        criteria.createCriteria()
                .andUserIdEqualTo(userInfoView.getId())
                .andNAssignTypeTextEqualTo("任")
                .andDAssignDateGreaterThan(userInfoView.getEnterDate());

        List<UmsRankInfoView> list = rankInfoViewMapper.selectByExample(criteria);

        for (UmsRankInfoView view : list) {
            Footprint fp = new Footprint();
            fp.setType(FootprintType.RANK);
            fp.setTimePoint(view.getdAssignDate());
            fp.setContent(String.format("职级变更为%s",
                    view.getnRankText()));

            fp.setEntity(view);
            footprints.add(fp);
        }
    }

    /**
     * 加载培训
     *
     * @param userInfoView
     * @param footprints
     */
    private void loadTraining(UmsUserInfoView userInfoView, List<Footprint> footprints) {
        UmsTrainingInfoViewCriteria criteria = new UmsTrainingInfoViewCriteria();

        criteria.createCriteria()
                .andUserIdEqualTo(userInfoView.getId())
                .andDEndDateGreaterThan(userInfoView.getEnterDate());

        List<UmsTrainingInfoView> list = trainingInfoViewMapper.selectByExample(criteria);

        for (UmsTrainingInfoView view : list) {
            Footprint fp = new Footprint();
            fp.setType(FootprintType.TRAINING);
            fp.setTimePoint(view.getdEndDate());
            fp.setContent(String.format("完成了%s的培训",
                    view.getnSpecialtyText()));

            fp.setEntity(view);
            footprints.add(fp);
        }
    }
    //</editor-fold>

    /**
     * 加载附表
     *
     * @param userInfoView
     * @param footprints
     */
    private void loadAttachedInfo(UmsUserInfoView userInfoView, List<Footprint> footprints) {
        loadAbroadInfo(userInfoView, footprints);
        loadAdminJob(userInfoView, footprints);
        loadLegalJob(userInfoView, footprints);
        loadAssess(userInfoView, footprints);
        loadDegree(userInfoView, footprints);
        loadRank(userInfoView, footprints);
        loadTraining(userInfoView, footprints);
    }

    /**
     * 生成足迹信息
     *
     * @param userId
     * @param userInfo
     * @return
     */
    private List<Footprint> userFootprints(String userId, UmsUserInfoView userInfo) {
        List<Footprint> footprints = new ArrayList<>();

        //主表信息
        if (userInfo == null) {
            userInfo = userInfoViewService.selectById(userId);
        }
        addBasicInfo(footprints, userInfo);

        //附表信息
        //loadAttachedInfo(userId, footprints);
        loadAttachedInfo(userInfo, footprints);

        //排序
        sortFootprints(footprints);

        //移除
        //removeItemsBeforeEnterCourt(footprints, userInfo.getEnterDate());
        return footprints;
    }

    private void addBasicInfo(List<Footprint> footprints, UmsUserInfoView userInfo) {
        Footprint f = new Footprint();

        ///////////////////进院时间///////////////////////
        f.setTimePoint(userInfo.getEnterDate());
        f.setContent(String.format("%s以%s方式从%s进入法院工作",
                userInfo.getFullname(), userInfo.getEnterWayText(), userInfo.getEnterSourceText()));
        f.setType(FootprintType.ENTER_TIME);
        //////////////////////////////////////////////////

        footprints.add(f);
    }

    /**
     * 时间排序
     *
     * @param footprints
     */
    private void sortFootprints(List<Footprint> footprints) {
        sortFootprints(footprints, false);
    }

    /**
     * 时间排序
     *
     * @param footprints
     * @param desc 是否倒序
     */
    private void sortFootprints(List<Footprint> footprints, final boolean desc) {
        Collections.sort(footprints, new Comparator<Footprint>() {

            @Override
            public int compare(Footprint o1, Footprint o2) {
                return (desc ? -1 : 1) * o1.getTimePoint().compareTo(o2.getTimePoint());
            }
        });
    }

    /**
     * 移除进院前的足迹
     *
     * @param footprints
     * @param enterDate
     */
    private void removeItemsBeforeEnterCourt(List<Footprint> footprints, Date enterDate) {
        for (int i = footprints.size() - 1; i >= 0; i--) {
            if (footprints.get(i).getTimePoint().before(enterDate)) {
                footprints.remove(i);
            }
        }
    }

}
