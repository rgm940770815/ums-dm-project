package cn.net.withub.ums.service;

import cn.net.withub.ums.entity.UmsNotice;
import cn.net.withub.ums.entity.UmsNoticeExample;

import java.util.List;

/**
 * Created by Cypress on 2016/11/28.
 */
public interface UmsNoticeService {

    List<UmsNotice> selectByExample(UmsNoticeExample example);

    int countByExample(UmsNoticeExample example);

    int infoInsert(UmsNotice record);

    int InfoDeleteByPrimaryKey(String id);

    UmsNotice selectByPrimaryKey(String id);

    //和selectByExample相比 只是根据当前的时间来进行排序
    List<UmsNotice> selectByExampleOrderByTime(UmsNoticeExample example);

    //根据法院代码查询所有符和当前时间要求的信息
    List<UmsNotice> selectByCourt(Integer court);

    int updateByPrimaryKeySelective(UmsNotice record);
}
