package cn.net.withub.ums.action.log;

import cn.net.withub.ums.entity.UmsUserOperationLog;
import cn.net.withub.ums.entity.UmsUserOperationLogExample;
import cn.net.withub.ums.service.UmsUserOperationLogService;
import org.apache.struts2.convention.annotation.*;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/umsUserOperationLog")
@Results({
        @Result(name = "json", type = "json", params = {"root", "data"})
})
public class UmsUserOperationLogAction {

    private Object data;
    private String id;

    @Autowired
    UmsUserOperationLogService umsUserOperationLogService;

    @Action("selectById")
    public String viewUserOperationLog() {
        Map map = new HashMap();
        UmsUserOperationLogExample umsUserOperationLogExample = new UmsUserOperationLogExample();
        umsUserOperationLogExample.createCriteria().andModifiedUseridEqualTo(id);
        umsUserOperationLogExample.setOrderByClause("operation_time desc");
        List<UmsUserOperationLog> list = umsUserOperationLogService.viewUserOperationLog(umsUserOperationLogExample);
        data = list;
        return "json";
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
