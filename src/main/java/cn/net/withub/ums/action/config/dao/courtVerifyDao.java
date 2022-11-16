package cn.net.withub.ums.action.config.dao;

import cn.net.withub.ums.entity.UmsCourtVerifyConfig;
import cn.net.withub.ums.entity.UmsCourtVerifyConfigCriteria;

import java.util.List;
import java.util.Map;

public interface courtVerifyDao {

    List<UmsCourtVerifyConfig> selectByExample(UmsCourtVerifyConfigCriteria example);

    int insertByBatch(List<Map<String, Object>> insert, String scope,Integer courtNo);

}
