package cn.net.withub.ums.action.config.dao.impl;

import cn.net.withub.ums.action.config.dao.courtVerifyDao;
import cn.net.withub.ums.common.UmsConstant;
import cn.net.withub.ums.dao.UmsCourtVerifyConfigMapper;
import cn.net.withub.ums.entity.UmsCourtVerifyConfig;
import cn.net.withub.ums.entity.UmsCourtVerifyConfigCriteria;
import cn.net.withub.ums.entity.UmsUserInfoView;
import cn.net.withub.ums.entity.UmsVerifyConfigCriteria;
import cn.net.withub.ums.service.UmsCourtService;
import cn.net.withub.ums.util.SessionUtils;
import org.apache.http.client.methods.HttpExecutionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class courtVerifyDaoImpl implements courtVerifyDao {

    @Autowired
    UmsCourtVerifyConfigMapper mapper;

    @Autowired
    HttpSession httpSession;

    @Autowired
    private UmsCourtService courtService;


    @Override
    public List<UmsCourtVerifyConfig> selectByExample(UmsCourtVerifyConfigCriteria example) {
        return mapper.selectByExample(example);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertByBatch(List<Map<String, Object>> insert, String scope,Integer courtNo) {
        //先删再添加
        UmsCourtVerifyConfigCriteria example = new UmsCourtVerifyConfigCriteria();
        example.createCriteria().andCScopeEqualTo(scope).andCourtNoEqualTo(courtNo);
        mapper.deleteByExample(example);
        List<Map<String, Object>> collect = insert.stream().filter(map -> map.get("cValue") != null && !map.get("cValue").toString().equals("")).collect(Collectors.toList());
        //如果值都是空的 直接返回
        if(collect.size() == 0){
            return 1;
        }
        UmsUserInfoView user_ = SessionUtils.currentUser();
//        UmsUserInfoView user_ = (UmsUserInfoView) httpSession.getAttribute(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
        //还要对数据进行加工
        for (Map<String, Object> c : collect) {

            c.put("updateUserName",user_.getFullname());
            c.put("updateUserNo",user_.getId());
            c.put("updateTime",new Date());

            c.put("courtCode",courtService.courtNo2CourtCode(courtNo));
            c.put("courtStdNo",courtService.courtNo2CourtStdNo(courtNo));
            c.put("courtNo",  courtNo);


        }

        //再批量添加
        return mapper.insertByBatch(collect);
    }
}
