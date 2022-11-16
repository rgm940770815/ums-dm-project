package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.dao.UmsFieldCodeMapper;
import cn.net.withub.ums.entity.UmsFieldCode;
import cn.net.withub.ums.entity.UmsFieldCodeExample;
import cn.net.withub.ums.service.UmsFieldCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UmsFieldCodeServiceImpl implements UmsFieldCodeService {

    @Autowired
    UmsFieldCodeMapper mapper;


    @Override
    public List<UmsFieldCode> selectByExample(UmsFieldCodeExample example) {
        return mapper.selectByExample(example);
    }
}
