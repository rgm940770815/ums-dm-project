package cn.net.withub.ums.action.noticeFile;


import cn.net.withub.ums.entity.UmsNoticeFj;
import cn.net.withub.ums.entity.UmsNoticeFjExample;

import java.util.List;

/**
 * Created by admin on 2016/12/2.
 */
public interface NoticeFjService
{
     int fjSave(UmsNoticeFj fj);

     int fjdelete(String id);

     List<UmsNoticeFj> fjFind(UmsNoticeFjExample umsNoticeFjExample);

     int countByExample(UmsNoticeFjExample umsNoticeFjExample);
     UmsNoticeFj selectById(String id);
}
