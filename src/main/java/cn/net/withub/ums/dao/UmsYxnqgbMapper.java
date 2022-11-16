package cn.net.withub.ums.dao;

import cn.net.withub.ums.entity.UmsYxnqgb;

public interface UmsYxnqgbMapper {

    int updateByPrimaryKey(UmsYxnqgb umsYxnqgb);

    int insert(UmsYxnqgb umsYxnqgb);

    int deleteByPrimaryKey(String id);
}
