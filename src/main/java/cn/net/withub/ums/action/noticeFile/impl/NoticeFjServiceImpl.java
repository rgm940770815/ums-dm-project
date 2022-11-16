package cn.net.withub.ums.action.noticeFile.impl;

import cn.net.withub.ums.action.noticeFile.NoticeFjService;
import cn.net.withub.ums.dao.UmsNoticeFjMapper;
import cn.net.withub.ums.entity.UmsNoticeFj;
import cn.net.withub.ums.entity.UmsNoticeFjExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wj on 2016/12/2.
 */
@Service
public class NoticeFjServiceImpl implements NoticeFjService
{
    @Autowired
    UmsNoticeFjMapper mapper;
    @Override
    public int fjSave(UmsNoticeFj fj)
    {
        return mapper.insert(fj);
    }

    @Override
    public int fjdelete(String id)
    {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<UmsNoticeFj> fjFind(UmsNoticeFjExample umsNoticeFjExample)
    {
        return mapper.selectByExample(umsNoticeFjExample);
    }

    @Override
    public int countByExample(UmsNoticeFjExample umsNoticeFjExample)
    {
        return mapper.countByExample(umsNoticeFjExample);
    }

    @Override
    public UmsNoticeFj selectById(String id)
    {
        return mapper.selectByPrimaryKey(id);
    }
}
