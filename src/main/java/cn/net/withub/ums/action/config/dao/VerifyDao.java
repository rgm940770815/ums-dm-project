package cn.net.withub.ums.action.config.dao;

import cn.net.withub.ums.entity.UmsVerifyConfig;
import cn.net.withub.ums.entity.UmsVerifyConfigCriteria;
import cn.net.withub.ums.entity.UmsVerifyConfigKey;

import java.util.List;
import java.util.Map;

public interface VerifyDao {

    int insertByBatch(List<Map<String, Object>> insert, String scope);

    List<UmsVerifyConfig> selectByExample(UmsVerifyConfigCriteria example);

    int deleteByPrimaryKey(UmsVerifyConfigKey key);

}
