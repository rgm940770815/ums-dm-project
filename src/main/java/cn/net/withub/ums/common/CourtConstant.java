package cn.net.withub.ums.common;

import cn.net.withub.ums.dao.UmsCourtFullMapper;
import cn.net.withub.ums.entity.UmsCourtFull;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*
法院常量
 */
@Component
public class CourtConstant implements InitializingBean {

    private  final Map<String,String> court = new LinkedHashMap<>();

    @Autowired
    UmsCourtFullMapper umsCourtFullMapper;

    @Override
    public void afterPropertiesSet() throws Exception {

        List<UmsCourtFull> umsCourtFulls = umsCourtFullMapper.selectAllCourt();
        for (UmsCourtFull umsCourtFull : umsCourtFulls) {
            court.put(umsCourtFull.getCourtCode(),umsCourtFull.getCourtStdName());
        }

    }

    public Map<String, String> getCourt() {
        return court;
    }
}
