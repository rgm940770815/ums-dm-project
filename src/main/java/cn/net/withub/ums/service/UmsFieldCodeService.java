package cn.net.withub.ums.service;

import cn.net.withub.ums.entity.UmsFieldCode;
import cn.net.withub.ums.entity.UmsFieldCodeExample;

import java.util.List;

public interface UmsFieldCodeService {

    List<UmsFieldCode> selectByExample(UmsFieldCodeExample example);

}
