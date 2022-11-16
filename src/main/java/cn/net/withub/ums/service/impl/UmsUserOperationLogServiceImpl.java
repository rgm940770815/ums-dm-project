package cn.net.withub.ums.service.impl;

import cn.net.withub.ums.entity.UmsUserOperationLog;
import cn.net.withub.ums.entity.UmsUserOperationLogExample;
import cn.net.withub.ums.service.UmsUserOperationLogService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UmsUserOperationLogServiceImpl implements UmsUserOperationLogService {

    @Autowired
    SqlSession sqlSession;

    /**
     * 记录用户操作日志
     *
     * @param umsUserOperationLog
     */
    public void logUserOperation(UmsUserOperationLog umsUserOperationLog) {
        try {
            sqlSession.insert("UmsUserOperationLogMapper.insertSelective", umsUserOperationLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询用户操作日志
     * @param umsUserOperationLogExample
     * @return
     */
    public List<UmsUserOperationLog> viewUserOperationLog(UmsUserOperationLogExample umsUserOperationLogExample) {
        List<UmsUserOperationLog> list = sqlSession.selectList("UmsUserOperationLogMapper.selectByExample", umsUserOperationLogExample);
        return list;
    }

    public int logNum(UmsUserOperationLogExample umsUserOperationLogExample) {
        int num = sqlSession.selectOne("UmsUserOperationLogMapper.selectCountByExample", umsUserOperationLogExample);
        return num;
    }
}
