package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.dao.UmsUserInfoOldMapper;
import cn.net.withub.ums.entity.UmsUserInfo;
import cn.net.withub.ums.entity.UmsUserInfoOld;
import cn.net.withub.ums.entity.UmsUserInfoOldExample;
import cn.net.withub.ums.service.UmsUserInfoOldService;
import cn.net.withub.ums.util.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by jacky on 2016/1/14.
 */
@Service
public class UmsUserInfoOldServiceImpl implements UmsUserInfoOldService{

    @Autowired
    UmsUserInfoOldMapper umsUserInfoOldMapper;

    /**
     * 获取法院旧时人员
     *
     * @param fydm
     * @param parameters
     * @return
     */
    @Override
    public List<UmsUserInfoOld> getUserOldList(String fydm, Map<String, Object> parameters) {
        UmsUserInfoOldExample example = new UmsUserInfoOldExample();

        if(StringUtils.isNotEmpty(fydm)){
            example.createCriteria().andFydmEqualTo(fydm);
        }

        example.setOrderByClause(" fydm,org_code");

        List<UmsUserInfoOld> list = umsUserInfoOldMapper.selectByExample(example);

        return list;
    }

    @Override
    public int insert(UmsUserInfoOld entity) {
        return 0;
    }

    @Override
    public int delete(UmsUserInfoOld entity) {
        return 0;
    }

    @Override
    public int deleteById(Object id) {
        return 0;
    }

    @Override
    public int update(UmsUserInfoOld entity) {
        UmsUserInfoOldExample example = new UmsUserInfoOldExample();
        example.createCriteria().andIdEqualTo(entity.getId());

        return umsUserInfoOldMapper.updateByExampleSelective(entity,example);
    }

    @Override
    public UmsUserInfoOld selectById(Object id) {
        return null;
    }

    @Override
    public int countAll() {
        return 0;
    }

    /**
     * 搜索
     *
     * @param criteria
     * @param rowBounds
     * @return
     */
    @Override
    public List<UmsUserInfoOld> search(UmsUserInfoOldExample criteria, RowBounds rowBounds) {
        return null;
    }

    /**
     * @param criteria
     * @return
     */
    @Override
    public int count(UmsUserInfoOldExample criteria) {
        return 0;
    }
}
