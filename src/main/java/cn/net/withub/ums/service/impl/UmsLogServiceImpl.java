package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.dao.UmsLogMapper;
import cn.net.withub.ums.entity.UmsLog;
import cn.net.withub.ums.entity.UmsLogCriteria;
import cn.net.withub.ums.service.UmsLogService;
import cn.net.withub.ums.util.CriteriaTools;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 日志逻辑接口实现类 Created by D.Yang on 2014/12/24 0024.
 */
@Service
public class UmsLogServiceImpl implements UmsLogService {

    @Autowired
    private UmsLogMapper umsLogMapper;

    /**
     * 插入日志
     *
     * @param entity
     * @return
     */
    @Override
    public int insert(UmsLog entity) {
        return umsLogMapper.insert(entity);
    }

    /**
     * 删除日志
     *
     * @param entity
     * @return
     */
    @Override
    public int delete(UmsLog entity) {
        return umsLogMapper.deleteByPrimaryKey(entity.getId());
    }

    /**
     * 修改日志
     *
     * @param entity
     * @return
     */
    @Override
    public int update(UmsLog entity) {
        return umsLogMapper.updateByPrimaryKey(entity);
    }

    @Override
    public int countAll() {
        UmsLogCriteria criteria = new UmsLogCriteria();
        criteria.createCriteria().getAllCriteria();
        return umsLogMapper.countByExample(criteria);
    }

    public List<UmsLog> search(Map<String, Object[]> conditions, int page, int pageSize) {
        UmsLogCriteria criteria = new UmsLogCriteria();
        CriteriaTools.loadMap(criteria, conditions);
        return umsLogMapper.selectByExample(criteria);
    }

    public int count(Map<String, Object[]> conditions) {
        UmsLogCriteria criteria = new UmsLogCriteria();
        CriteriaTools.loadMap(criteria.createCriteria(), conditions);
        return umsLogMapper.countByExample(criteria);
    }

    @Override
    public UmsLog selectById(Object id) {
        return umsLogMapper.selectByPrimaryKey((String) id);
    }

    @Override
    public List<UmsLog> search(UmsLogCriteria criteria, RowBounds rowBounds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int count(UmsLogCriteria criteria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteById(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
