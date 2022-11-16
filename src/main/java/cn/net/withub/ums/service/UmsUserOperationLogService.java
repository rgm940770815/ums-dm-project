package cn.net.withub.ums.service;

import cn.net.withub.ums.entity.UmsUserOperationLog;
import cn.net.withub.ums.entity.UmsUserOperationLogExample;

import java.util.List;


public interface UmsUserOperationLogService {

    /**
     * 记录用户操作日志
     *
     * @param umsUserOperationLog
     */
    void logUserOperation(UmsUserOperationLog umsUserOperationLog);

    /**
     * 查询用户操作日志
     * @param umsUserOperationLogExample
     * @return
     */
    List<UmsUserOperationLog> viewUserOperationLog(UmsUserOperationLogExample umsUserOperationLogExample);

    /**
     * 查询用户操作日志总数
     * @param umsUserOperationLogExample
     * @return
     */
    int logNum(UmsUserOperationLogExample umsUserOperationLogExample);
}
