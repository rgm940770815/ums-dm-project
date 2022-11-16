package cn.net.withub.ums.action.rmpsy;


import cn.net.withub.ums.dao.WorkAreaMapper;
import cn.net.withub.ums.entity.WorkArea;
import cn.net.withub.ums.entity.WorkAreaExample;
import cn.net.withub.ums.util.StringUtils;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/workAreaAction")
@Results({@Result(name = "json", type = "json", params = {"root", "data"})})
public class WorkAreaAction {

    @Autowired
    WorkAreaMapper workAreaMapper;

    private Object data;

    private WorkArea workArea;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public WorkArea getWorkArea() {
        return workArea;
    }

    public void setWorkArea(WorkArea workArea) {
        this.workArea = workArea;
    }

    // 获取3.0版本的工作区域
    @Action("getWorkAreaTree")
    public String getWorkAreaTree() {
        ActionContext actionContext = ActionContext.getContext();
        HttpServletRequest httpServletRequest = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);

        // 搜索类型
        String type = httpServletRequest.getParameter("type");

        WorkAreaExample workAreaExample = zzWorkAreaExample(httpServletRequest);

        List<WorkArea> workAreaList = workAreaMapper.selectByExample(workAreaExample);
        int count = workAreaMapper.countByExample(workAreaExample);

        if ("workAreaSearch".equals(type)) {
            // 处理成grid需要的数据格式
            List<WorkArea> workAreaList4 = new ArrayList<>();
            WorkArea workAreaForSearch;
            for (WorkArea workArea : workAreaList) {
                if ("4".equals(workArea.getEe())) {
                    workAreaForSearch = new WorkArea();

                    WorkAreaExample workAreaExample1 = new WorkAreaExample();
                    workAreaExample1.createCriteria().andAaEqualTo(workArea.getAa()).andBbIsNull().andCcIsNull().andDdIsNull();
                    List<WorkArea> workAreaList1 = workAreaMapper.selectByExample(workAreaExample1);
                    String aaName = workAreaList1.get(0).getName();

                    WorkAreaExample workAreaExample2 = new WorkAreaExample();
                    workAreaExample2.createCriteria().andAaEqualTo(workArea.getAa()).andBbEqualTo(workArea.getBb()).andCcIsNull().andDdIsNull();
                    List<WorkArea> workAreaList2 = workAreaMapper.selectByExample(workAreaExample2);
                    String bbName = workAreaList2.get(0).getName();

                    WorkAreaExample workAreaExample3 = new WorkAreaExample();
                    workAreaExample3.createCriteria().andAaEqualTo(workArea.getAa()).andBbEqualTo(workArea.getBb()).andCcEqualTo(workArea.getCc()).andDdIsNull();
                    List<WorkArea> workAreaList3 = workAreaMapper.selectByExample(workAreaExample3);
                    String ccName = workAreaList3.get(0).getName();

                    String csjg = aaName + "-" + bbName + "-" + ccName;

                    workAreaForSearch.setCsjg(csjg);
                    workAreaForSearch.setDd(workArea.getDd());
                    workAreaForSearch.setName(workArea.getName());

                    // 返回前端的list
                    workAreaList4.add(workAreaForSearch);
                }
            }
            Map<String, Object> returnMap = new HashMap<>();
            returnMap.put("rows", workAreaList4);
            returnMap.put("results", count);
            data = returnMap;
        } else if ("ztree".equals(type)) {
            // 处理成ztree需要的数据格式
            for (WorkArea workArea : workAreaList) {
                String cc = workArea.getCc();
                String ee = workArea.getEe();
                if ("4".equals(ee)) {

                } else if ("3".equals(ee)) {
                    // 查找下一等级
                    WorkAreaExample workAreaExample1 = new WorkAreaExample();
                    workAreaExample1.createCriteria().andCcEqualTo(cc).andEeEqualTo("4");
                    List<WorkArea> workAreaList1 = workAreaMapper.selectByExample(workAreaExample1);
                    if (null == workAreaList1 && workAreaList1.size() == 0) {

                    } else {
                        workArea.setIsParent("true");
                    }
                } else {
                    workArea.setIsParent("true");
                }
            }
            data = workAreaList;
        }
        return "json";
    }

    // 组装workAreaExample
    public WorkAreaExample zzWorkAreaExample(HttpServletRequest httpServletRequest) {

        WorkAreaExample workAreaExample = new WorkAreaExample();
        WorkAreaExample.Criteria criteria = workAreaExample.createCriteria();

        String aa = httpServletRequest.getParameter("aa");
        String bb = httpServletRequest.getParameter("bb");
        String cc = httpServletRequest.getParameter("cc");
        String dd = httpServletRequest.getParameter("dd");
        String ee = httpServletRequest.getParameter("ee");
        String start = httpServletRequest.getParameter("start");
        String type = httpServletRequest.getParameter("type");

        if ("workAreaSearch".equals(type)) {
            criteria.andEeEqualTo("4");
        }

        if (StringUtils.isNotEmpty(start)) {
            String limit = httpServletRequest.getParameter("limit");
            workAreaExample.setOrderByClause(" id limit " + start + ", " + limit);
        }
        if (StringUtils.isEmpty(ee)) {

        } else {
            ee = "" + (Integer.valueOf(ee) + 1);
        }
        String name = httpServletRequest.getParameter("name");

        if (StringUtils.isNotEmpty(aa)) {
            criteria.andAaEqualTo(aa);
        }
        if (StringUtils.isNotEmpty(bb)) {
            criteria.andBbEqualTo(bb);
        }
        if (StringUtils.isNotEmpty(cc)) {
            criteria.andCcEqualTo(cc);
        }
        if (StringUtils.isNotEmpty(dd)) {
            criteria.andDdEqualTo(dd);
        }
        if (StringUtils.isNotEmpty(ee)) {
            criteria.andEeEqualTo(ee);
        }
        if (StringUtils.isNotEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        return workAreaExample;
    }
}
