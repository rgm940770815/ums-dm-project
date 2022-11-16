package cn.net.withub.ums.action.config.dao.impl;

import cn.net.withub.ums.action.config.dao.VerifyDao;
import cn.net.withub.ums.dao.UmsVerifyConfigMapper;
import cn.net.withub.ums.entity.UmsVerifyConfig;
import cn.net.withub.ums.entity.UmsVerifyConfigCriteria;
import cn.net.withub.ums.entity.UmsVerifyConfigKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VerifyDaoImpl implements VerifyDao {

    @Autowired
    UmsVerifyConfigMapper umsVerifyConfigMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertByBatch(List<Map<String, Object>> insert, String scope) {
        //先删再添加
        UmsVerifyConfigCriteria example = new UmsVerifyConfigCriteria();
        example.createCriteria().andCScopeEqualTo(scope);
        umsVerifyConfigMapper.deleteByExample(example);
        List<Map<String, Object>> collect = insert.stream().filter(map -> map.get("cValue") != null && !map.get("cValue").toString().equals("")).collect(Collectors.toList());
        //如果值都是空的 直接返回
        if(collect.size() == 0){
            return 1;
        }
        //再批量添加
        return umsVerifyConfigMapper.insertByBatch(collect);
    }

    @Override
    public List<UmsVerifyConfig> selectByExample(UmsVerifyConfigCriteria example) {
        return umsVerifyConfigMapper.selectByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(UmsVerifyConfigKey key) {
        return umsVerifyConfigMapper.deleteByPrimaryKey(key);
    }
}
