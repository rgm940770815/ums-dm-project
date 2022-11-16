package cn.net.withub.ums.service;

import cn.net.withub.ums.entity.UmsFieldContrast;
import cn.net.withub.ums.entity.UmsFieldContrastExample;

import java.util.List;

/**
 * Created by Administrator on 2016/1/15.
 */
public interface UmsFieldContrastService {

    public List<UmsFieldContrast> selectByExample(UmsFieldContrastExample example);

    /**
     * 获取所有的表名
     * @return
     */
    public List<String> selectTableName();

}
