/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.action.code;

import cn.net.withub.ums.action.userinfo.UserInfoViewAction;
import cn.net.withub.ums.auth.AuthorityHelper;
import cn.net.withub.ums.common.UmsConstant;
import cn.net.withub.ums.entity.UmsCourtFull;
import cn.net.withub.ums.entity.UmsDepartment;
import cn.net.withub.ums.entity.UmsDepartmentKey;
import cn.net.withub.ums.entity.UmsUserInfoView;
import cn.net.withub.ums.service.UmsCourtFullService;
import cn.net.withub.ums.service.UmsCourtService;
import cn.net.withub.ums.service.UmsDepartmentService;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Diluka
 */
@Controller
@ParentPackage("codeBase")
@Scope("prototype")
@Namespace("/code/tree")
@Results({
        @Result(name = "json", type = "json", params = {"root", "data"})
})
public class CourtDeptTreeAction {

    @Autowired
    private UmsDepartmentService departmentService;
    @Autowired
    private UmsCourtFullService courtFullService;
    @Autowired
    private UmsCourtService courtService;
    @Autowired
    private AuthorityHelper authorityHelper;

    private String id;

    private Object data;

    private String type;

    private String orgCode;

    @Action("children2")
    public String childrenNode2() {
        List<TreeNode> list = new ArrayList<>();
        data = list;

        TreeNode node;
        try {
            String[] s = id.split(",");
            node = new TreeNode();
            try {
                node.courtNo = Integer.parseInt(s[0]);
            } catch (Exception e) {
            }
            try {
                node.deptNo = Integer.parseInt(s[1]);
            } catch (Exception e) {
            }
        } catch (Exception e) {
            node = null;
        }

        if (node != null) {
            if (node.getDeptNo() != null) {
                UmsDepartmentKey dkey = new UmsDepartmentKey();
                dkey.setCourtNo(node.getCourtNo());
                dkey.setDeptNo(node.getDeptNo());

                list.addAll(nodesByDeptKey(dkey));
            } else if (node.getCourtNo() != null) {
                //list.addAll(nodesByCourtNo(node.getCourtNo()));
                list.addAll(
                        nodesByDeptList(
                                departmentService.selectByCourtNoAndDeptLevel(node.getCourtNo(), 1)));
            }
        } else { //root
            List<UmsCourtFull> courtAll = courtFullService.listAllCourts();

            List<Integer> accessibleCourtNoList = authorityHelper.accessibleCourtNoList("查看");
            for (UmsCourtFull c : courtAll) {

                if (!accessibleCourtNoList.contains(-9999)//超级权限-9999
                        && !accessibleCourtNoList.contains(c.getCourtNo())) {//没权限的跳过
                    continue;
                }

                TreeNode n = new TreeNode();

                n.setText(c.getCourtStdName());
                n.setCourtNo(c.getCourtNo());
                n.id = n.courtNo + "," + n.deptNo;
                n.leaf = false;
                n.cls = "icon-court";

                list.add(n);
            }
        }

        return "json";
    }

    @Action("children")
    public String childrenNode() {
        List<TreeNode> list = new ArrayList<>();
        data = list;

        TreeNode node;
        try {
            String[] s = id.split(",");
            node = new TreeNode();
            try {
                node.courtNo = Integer.parseInt(s[0]);
            } catch (Exception e) {
            }
            try {
                node.deptNo = Integer.parseInt(s[1]);
            } catch (Exception e) {
            }
        } catch (Exception e) {
            node = null;
        }

        if (node != null) {
            if (node.getDeptNo() != null) {
                UmsDepartmentKey dkey = new UmsDepartmentKey();
                dkey.setCourtNo(node.getCourtNo());
                dkey.setDeptNo(node.getDeptNo());

                list.addAll(nodesByDeptKey(dkey));
            } else if (node.getCourtNo() != null) {
                list.addAll(nodesByCourtNo(node.getCourtNo()));
            }
        } else { //root
            List<UmsCourtFull> courtG0 = courtFullService.listByGradation(0);
            for (UmsCourtFull c : courtG0) {
                TreeNode n = new TreeNode();

                n.setText(c.getCourtStdName());
                n.setCourtNo(c.getCourtNo());
                n.id = n.courtNo + "," + n.deptNo;
                n.leaf = false;
                n.cls = "icon-court";

                list.add(n);
            }
        }

        return "json";
    }

    @Action("children3")
    public String childrenNode3() {
        List<TreeNode> list = new ArrayList<>();
        data = list;
        TreeNode node;
        try {
            String[] s = id.split(",");
            node = new TreeNode();
            try {
                node.courtNo = Integer.parseInt(s[0]);
            } catch (Exception e) {
            }
            try {
                node.deptNo = Integer.parseInt(s[1]);
            } catch (Exception e) {
            }
        } catch (Exception e) {
            node = null;
        }
        // node不为空,代表查询的法院不为空
        if (node != null) {
            if (node.getDeptNo() != null) {
                UmsDepartmentKey dkey = new UmsDepartmentKey();
                dkey.setCourtNo(node.getCourtNo());
                dkey.setDeptNo(node.getDeptNo());
                list.addAll(nodesByDeptKey(dkey));
            } else if (node.getCourtNo() != null && node.getCourtNo() != 2951) {
                // 存在type字段时 1 为不显示部门信息
                if (type != null && type.equals("1")) {
                    list.addAll(nodesByCourtNo2(node.getCourtNo()));
                } else if (type != null && type.equals("onlyGetDept")) {
                    list.addAll(nodesByCourtNo_2(node.getCourtNo()));
                } else {
                    list.addAll(nodesByCourtNo(node.getCourtNo()));
                }
            } else if (node.getCourtNo() == 2951) {
                // 存在type字段时 1 为不显示部门信息
                if (type != null && type.equals("1")) {
                } else {
                    List<UmsDepartment> cdlist = departmentService.selectByCourtNoAndDeptLevel(2951, 1); //只要第一级
                    list.addAll(nodesByDeptList(cdlist));
                }
            }
        } else { //root
            try {
                UmsUserInfoView u = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
                //获取权限可以访问的法院
                List<Map<String, Object>> clist = courtFullService.listByAuthUserID(u.getId());
                if (clist != null && clist.size() > 0) {
                    for (Map<String, Object> rlist : clist) {
                        //超级管理员 显示高院 和 所有中级法院
                        if (rlist.get("rcourt_no").toString().equals("9999")) {
                            list.removeAll(list);
                            List<UmsCourtFull> courtG0 = courtFullService.listByGradation(0);
                            for (UmsCourtFull c : courtG0) {
                                TreeNode n = new TreeNode();
                                n.setCourtAreaNo(c.getCourtAreaNo());
                                n.setCourtStdNo(c.getCourtStdNo());
                                n.setCourtGradation(c.getCourtGradation());
                                n.setText(c.getCourtStdName());
                                n.setCourtNo(c.getCourtNo());
                                n.setSortNo(c.getSortNo());
                                n.id = n.courtNo + "," + n.deptNo;
                                n.leaf = false;
                                n.cls = "icon-court";
                                list.add(n);
                            }
                            List<UmsCourtFull> courtG1 = courtFullService.listByGradation(1);
                            for (UmsCourtFull c : courtG1) {
                                TreeNode n = new TreeNode();
                                n.setCourtAreaNo(c.getCourtAreaNo());
                                n.setCourtStdNo(c.getCourtStdNo());
                                n.setCourtGradation(c.getCourtGradation());
                                n.setText(c.getCourtStdName());
                                n.setCourtNo(c.getCourtNo());
                                n.setSortNo(c.getSortNo());
                                n.id = n.courtNo + "," + n.deptNo;
                                n.leaf = false;
                                n.cls = "icon-court";
                                list.add(n);
                            }
                            break;
                        }
                        TreeNode n = new TreeNode();
                        n.setText(rlist.get("court_std_name").toString());
                        n.setCourtNo(Integer.parseInt(rlist.get("court_no").toString()));
                        n.setCourtAreaNo(Integer.parseInt(rlist.get("court_area_no").toString()));
                        n.setCourtStdNo(Integer.parseInt(rlist.get("court_std_no").toString()));
                        n.setCourtGradation(Integer.parseInt(rlist.get("court_gradation").toString()));
                        n.setSortNo(Integer.parseInt(rlist.get("sort_no").toString()));
                        n.id = n.courtNo + "," + n.deptNo;
                        n.leaf = false;
                        n.cls = "icon-court";
                        list.add(n);
                    }
                }
                StringBuffer CourtAreaNoStr = new StringBuffer();
                List<TreeNode> CourtAreaNo = new ArrayList();
                for (TreeNode flist : list) {
                    if (flist.getCourtGradation() == 1) {
                        CourtAreaNoStr.append(flist.getCourtStdNo().toString());
                    } else if (flist.getCourtGradation() == 2) {
                        CourtAreaNo.add(flist);
                    }
                }
                for (TreeNode flist : CourtAreaNo) {
                    if (CourtAreaNoStr.indexOf(flist.getCourtAreaNo().toString()) > -1) {
                        list.remove(flist);
                    }
                }


//            boolean isSuperAdmin = false;
//
//            for(Map<String,Object> m : clist){
//                if("9999".equals(m.get("rcourt_no").toString())){
//                    isSuperAdmin = true;
//                }
//            }

//            if("admin".equals(u.getUsername()) || isSuperAdmin){

//                List<UmsCourtFull> courtG0 = courtFullService.listByGradation(0);
//                for (UmsCourtFull c : courtG0) {
//                    TreeNode n = new TreeNode();
//
//                    n.setText(c.getCourtStdName());
//                    n.setCourtNo(c.getCourtNo());
//                    n.id = n.courtNo + "," + n.deptNo;
//                    n.leaf = false;
//                    n.cls = "icon-court";
//
//                    list.add(n);
//                }
//
//                List<UmsCourtFull> courtG1 = courtFullService.listByGradation(1);
//                for (UmsCourtFull c : courtG1) {
//                    TreeNode n = new TreeNode();
//
//                    n.setText(c.getCourtStdName());
//                    n.setCourtNo(c.getCourtNo());
//                    n.id = n.courtNo + "," + n.deptNo;
//                    n.leaf = false;
//                    n.cls = "icon-court";
//
//                    list.add(n);
//                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

//            }else{
//                UmsCourtFull c = courtFullService.getCourtByCourtStdNo(u.getCourtStdNo());
//
//                //如果是中院就可以看到下级法院
//                if(c.getCourtGradation() == 1){
//                    List<UmsCourtFull> courtG0 = courtFullService.listChildrenByCourtStdNo(c.getCourtStdNo());
//                    for (UmsCourtFull c2 : courtG0) {
//                        TreeNode n = new TreeNode();
//
//                        n.setText(c2.getCourtStdName());
//                        n.setCourtNo(c2.getCourtNo());
//                        n.id = n.courtNo + "," + n.deptNo;
//                        n.leaf = false;
//                        n.cls = "icon-court";
//
//                        list.add(n);
//                    }
//                }else{
//                    TreeNode n = new TreeNode();
//                    n.setText(c.getCourtStdName());
//                    n.setCourtNo(c.getCourtNo());
//                    n.id = n.courtNo + "," + n.deptNo;
//                    n.leaf = false;
//                    n.cls = "icon-court";
//
//                    list.add(n);
//                }

//                    for(Map<String,Object> m : clist){
//                        if("1".equals(m.get("court_gradation").toString())){
//                            List<UmsCourtFull> courtG0 = courtFullService.listChildrenByCourtStdNo(Integer.valueOf(m.get("court_std_no").toString()));
//                            for (UmsCourtFull c2 : courtG0) {
//                                TreeNode n = new TreeNode();
//
//                                n.setText(c2.getCourtStdName());
//                                n.setCourtNo(c2.getCourtNo());
//                                n.id = n.courtNo + "," + n.deptNo;
//                                n.leaf = false;
//                                n.cls = "icon-court";
//
//                                list.add(n);
//                            }
//                        }else{
//                            TreeNode n = new TreeNode();
//                            n.setText(m.get("court_std_name").toString());
//                            n.setCourtNo(Integer.valueOf(m.get("court_no").toString()));
//                            n.id = n.courtNo + "," + n.deptNo;
//                            n.leaf = false;
//                            n.cls = "icon-court";
//
//
//
//                            list.add(n);
//                        }
//                    }

//            }

        }

//        if(type.equals("2")){
//            for (TreeNode tn : list) {
//                if(tn.text.indexOf("领导") > 0){
////                    list.remove(tn);
//                }
//            }
//        }

        //存在type字段时 2 为不显示包含 领导部门信息
        if (type != null && type.equals("2")) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).text.indexOf("领导") > 0) {
                    list.remove(i);
                    i--;
                }
            }
        }
        // 不显示人民陪审员庭 20190327
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).text.equals("人民陪审员庭")) {
                list.remove(i);
                i--;
            }
        }


        for (TreeNode tn : list) {
            tn.cls = null;
        }
        return "json";
    }

    @Action("children33")
    public String childrenNode33() {
        List<TreeNode> list = new ArrayList<>();
        data = list;
        TreeNode node;
        try {
            String[] s = id.split(",");
            node = new TreeNode();
            try {
                node.courtNo = Integer.parseInt(s[0]);
            } catch (Exception e) {
            }
            try {
                node.deptNo = Integer.parseInt(s[1]);
            } catch (Exception e) {
            }
        } catch (Exception e) {
            node = null;
        }
        // node不为空,代表查询的法院不为空
        if (node != null) {
            if (node.getDeptNo() != null) {
                UmsDepartmentKey dkey = new UmsDepartmentKey();
                dkey.setCourtNo(node.getCourtNo());
                dkey.setDeptNo(node.getDeptNo());
                list.addAll(nodesByDeptKey(dkey));
            } else if (node.getCourtNo() != null && node.getCourtNo() != 2951) {
                // 存在type字段时 1 为不显示部门信息
                if (type != null && type.equals("1")) {
                    list.addAll(nodesByCourtNo2(node.getCourtNo()));
                } else if (type != null && type.equals("onlyGetDept")) {
                    list.addAll(nodesByCourtNo_2(node.getCourtNo()));
                } else {
                    list.addAll(nodesByCourtNo(node.getCourtNo()));
                }
            } else if (node.getCourtNo() == 2951) {
                // 存在type字段时 1 为不显示部门信息
                if (type != null && type.equals("1")) {
                } else {
                    List<UmsDepartment> cdlist = departmentService.selectByCourtNoAndDeptLevel(2951, 1); //只要第一级
                    list.addAll(nodesByDeptList(cdlist));
                }
            }
        } else { //root
            try {
                UmsUserInfoView u = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
                //获取权限可以访问的法院
                List<Map<String, Object>> clist = courtFullService.listByAuthUserID(u.getId());
                if (clist != null && clist.size() > 0) {
                    for (Map<String, Object> rlist : clist) {
                        //超级管理员 显示高院 和 所有中级法院
                        if (rlist.get("rcourt_no").toString().equals("9999")) {
                            list.removeAll(list);
                            List<UmsCourtFull> courtG0 = courtFullService.listByGradation(0);
                            for (UmsCourtFull c : courtG0) {
                                TreeNode n = new TreeNode();
                                n.setCourtAreaNo(c.getCourtAreaNo());
                                n.setCourtStdNo(c.getCourtStdNo());
                                n.setCourtGradation(c.getCourtGradation());
                                n.setText(c.getCourtStdName());
                                n.setCourtNo(c.getCourtNo());
                                n.setSortNo(c.getSortNo());
                                n.id = n.courtNo + "," + n.deptNo;
                                n.leaf = false;
                                n.cls = "icon-court";
                                list.add(n);
                            }
                            List<UmsCourtFull> courtG1 = courtFullService.listByGradation(1);
                            for (UmsCourtFull c : courtG1) {
                                TreeNode n = new TreeNode();
                                n.setCourtAreaNo(c.getCourtAreaNo());
                                n.setCourtStdNo(c.getCourtStdNo());
                                n.setCourtGradation(c.getCourtGradation());
                                n.setText(c.getCourtStdName());
                                n.setCourtNo(c.getCourtNo());
                                n.setSortNo(c.getSortNo());
                                n.id = n.courtNo + "," + n.deptNo;
                                n.leaf = false;
                                n.cls = "icon-court";
                                list.add(n);
                            }
                            break;
                        }
                        TreeNode n = new TreeNode();
                        n.setText(rlist.get("court_std_name").toString());
                        n.setCourtNo(Integer.parseInt(rlist.get("court_no").toString()));
                        n.setCourtAreaNo(Integer.parseInt(rlist.get("court_area_no").toString()));
                        n.setCourtStdNo(Integer.parseInt(rlist.get("court_std_no").toString()));
                        n.setCourtGradation(Integer.parseInt(rlist.get("court_gradation").toString()));
                        n.setSortNo(Integer.parseInt(rlist.get("sort_no").toString()));
                        n.id = n.courtNo + "," + n.deptNo;
                        n.leaf = false;
                        n.cls = "icon-court";
                        list.add(n);
                    }
                }
                StringBuffer CourtAreaNoStr = new StringBuffer();
                List<TreeNode> CourtAreaNo = new ArrayList();
                for (TreeNode flist : list) {
                    if (flist.getCourtGradation() == 1) {
                        CourtAreaNoStr.append(flist.getCourtStdNo().toString());
                    } else if (flist.getCourtGradation() == 2) {
                        CourtAreaNo.add(flist);
                    }
                }
                for (TreeNode flist : CourtAreaNo) {
                    if (CourtAreaNoStr.indexOf(flist.getCourtAreaNo().toString()) > -1) {
                        list.remove(flist);
                    }
                }

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }


        }

        //存在type字段时 2 为不显示包含 领导部门信息
        if (type != null && type.equals("2")) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).text.indexOf("领导") > 0) {
                    list.remove(i);
                    i--;
                }
            }
        }
        // 不显示人民陪审员庭 20190327
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).text.equals("人民陪审员庭")) {
                list.remove(i);
                i--;
            }
        }

        Iterator<TreeNode> iterable = list.iterator();

        while (iterable.hasNext()){
            TreeNode treeNode = iterable.next();
            if(StringUtils.hasText(treeNode.getOrgCode())){
                iterable.remove();
            }
        }


        for (TreeNode tn : list) {
            tn.cls = null;
        }
        return "json";
    }

    //根据是否存在人员来查询
    @Action("children4")
    public String childrenNode4() {

        List<Integer> codeList = UserInfoViewAction.orgCodeMap.get(orgCode);
        if (codeList == null) {
            return "json";
        }

        List<TreeNode> list = new ArrayList<>();
        data = list;

        TreeNode node;
        try {
            String[] s = id.split(",");
            node = new TreeNode();
            try {
                node.courtNo = Integer.parseInt(s[0]);
            } catch (Exception e) {
            }
            try {
                node.deptNo = Integer.parseInt(s[1]);
            } catch (Exception e) {
            }
        } catch (Exception e) {
            node = null;
        }

        if (node != null) {
            if (node.getDeptNo() != null) {
                UmsDepartmentKey dkey = new UmsDepartmentKey();
                dkey.setCourtNo(node.getCourtNo());
                dkey.setDeptNo(node.getDeptNo());

                list.addAll(nodesByDeptKey(dkey));
            } else if (node.getCourtNo() != null) {
                List<UmsDepartment> cdlist = departmentService.selectByCourtNoAndDeptLevel(node.getCourtNo(), 1); //只要第一级
                list.addAll(nodesByDeptList(cdlist));
            }
        } else {
            try {
                UmsUserInfoView u = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());

                //获取权限可以访问的法院
                List<Map<String, Object>> clist = courtFullService.listByAuthUserID(u.getId());

                //自身权限可以查询的法院列表
                List<String> courtList;
                boolean flag = false;
                for (Map<String, Object> rlist : clist) {
                    //超级管理员 显示高院 和 所有中级法院
                    if (rlist.get("rcourt_no").toString().equals("9999")) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    List<UmsCourtFull> umsCourtFulls = courtFullService.selectByListAll();
                    courtList = umsCourtFulls.stream().map(UmsCourtFull::getCourtCode).collect(Collectors.toList());
                } else {
                    courtList = clist.stream().map(stringObjectMap -> {
                        Object court_code = stringObjectMap.get("court_code");
                        if (court_code == null) {
                            return "";
                        }
                        return court_code.toString();
                    }).collect(Collectors.toList());
                }

                //根据地方编制和中央编制和权限来查询法院
                List<UmsCourtFull> courtByOrganization = courtFullService.getCourtByOrganization(courtList, codeList);

                for (UmsCourtFull c : courtByOrganization) {

                    TreeNode n = new TreeNode();
                    n.setCourtAreaNo(c.getCourtAreaNo());
                    n.setCourtStdNo(c.getCourtStdNo());
                    n.setCourtGradation(c.getCourtGradation());
                    n.setText(c.getCourtStdName());
                    n.setCourtNo(c.getCourtNo());
                    n.setSortNo(c.getSortNo());
                    n.id = n.courtNo + "," + n.deptNo;
                    n.leaf = false;
                    n.cls = null;
                    list.add(n);
                }


            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

        }

        for (TreeNode tn : list) {
            tn.cls = null;
        }

        return "json";
    }

    //把部门放到内设机构里面
    @Action("children5")
    public String childrenNode5() {
        List<TreeNode> list = new ArrayList<>();
        data = list;
        TreeNode node;
        try {
            String[] s = id.split(",");
            node = new TreeNode();
            try {
                if (s[0] != null && !"null".equals(s[0])) {
                    node.courtNo = Integer.parseInt(s[0]);
                }
            } catch (Exception e) {
            }
            try {
                if (s[1] != null && !"null".equals(s[1])) {
                    node.deptNo = Integer.parseInt(s[1]);
                }
            } catch (Exception e) {
            }
        } catch (Exception e) {
            node = null;
        }
        if (node != null) {
            //查找部门
            if (node.getDeptNo() != null) {
                //内设机构
                if (101093324 == node.getDeptNo()) {
                    list.addAll(nodesByCourtNo3(node.getCourtNo()));
                } else {
                    UmsDepartmentKey dkey = new UmsDepartmentKey();
                    dkey.setCourtNo(node.getCourtNo());
                    dkey.setDeptNo(node.getDeptNo());
                    list.addAll(nodesByDeptKey(dkey));
                }
            } else if (node.getCourtNo() != null && node.getCourtNo() != 2951) {
                list.addAll(nodesByCourtNo4(node.getCourtNo()));
            } else if (node.getCourtNo() == 2951) {
                //最先把内设机构加上 id写死为 101093324
                TreeNode nsjg = new TreeNode();
                nsjg.setText("内设机构");
                nsjg.setCourtNo(node.getCourtNo());
                nsjg.id = nsjg.courtNo + "," + "101093324";
                nsjg.leaf = false;
                nsjg.cls = "icon-court";
                nsjg.expanded = true;
                List<UmsDepartment> cdlist = departmentService.selectByCourtNoAndDeptLevel(2951, 1); //只要第一级
                List<TreeNode> treeNodes = nodesByDeptList(cdlist);
                for (TreeNode tn : treeNodes) {
                    tn.cls = null;
                }
                nsjg.setChildren(treeNodes);
                list.add(nsjg);
            }
        } else { //root
            try {
                UmsUserInfoView u = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
                //获取权限可以访问的法院
                List<Map<String, Object>> clist = courtFullService.listByAuthUserID(u.getId());
                if (clist != null && clist.size() > 0) {
                    for (Map<String, Object> rlist : clist) {
                        //超级管理员 显示高院 和 所有中级法院
                        if (rlist.get("rcourt_no").toString().equals("9999")) {
                            list.removeAll(list);
                            List<UmsCourtFull> courtG0 = courtFullService.listByGradation(0);
                            for (UmsCourtFull c : courtG0) {
                                TreeNode n = new TreeNode();
                                n.setCourtAreaNo(c.getCourtAreaNo());
                                n.setCourtStdNo(c.getCourtStdNo());
                                n.setCourtGradation(c.getCourtGradation());
                                n.setText(c.getCourtStdName());
                                n.setCourtNo(c.getCourtNo());
                                n.setSortNo(c.getSortNo());
                                n.id = n.courtNo + "," + n.deptNo;
                                n.leaf = false;
                                n.cls = "icon-court";
                                list.add(n);
                            }
                            List<UmsCourtFull> courtG1 = courtFullService.listByGradation(1);
                            for (UmsCourtFull c : courtG1) {
                                TreeNode n = new TreeNode();
                                n.setCourtAreaNo(c.getCourtAreaNo());
                                n.setCourtStdNo(c.getCourtStdNo());
                                n.setCourtGradation(c.getCourtGradation());
                                n.setText(c.getCourtStdName());
                                n.setCourtNo(c.getCourtNo());
                                n.setSortNo(c.getSortNo());
                                n.id = n.courtNo + "," + n.deptNo;
                                n.leaf = false;
                                n.cls = "icon-court";
                                list.add(n);
                            }
                            break;
                        }
                        TreeNode n = new TreeNode();
                        n.setText(rlist.get("court_std_name").toString());
                        n.setCourtNo(Integer.parseInt(rlist.get("court_no").toString()));
                        n.setCourtAreaNo(Integer.parseInt(rlist.get("court_area_no").toString()));
                        n.setCourtStdNo(Integer.parseInt(rlist.get("court_std_no").toString()));
                        n.setCourtGradation(Integer.parseInt(rlist.get("court_gradation").toString()));
                        n.setSortNo(Integer.parseInt(rlist.get("sort_no").toString()));
                        n.id = n.courtNo + "," + n.deptNo;
                        n.leaf = false;
                        n.cls = "icon-court";
                        list.add(n);
                    }
                }
                StringBuffer CourtAreaNoStr = new StringBuffer();
                List<TreeNode> CourtAreaNo = new ArrayList();
                for (TreeNode flist : list) {
                    if (flist.getCourtGradation() == 1) {
                        CourtAreaNoStr.append(flist.getCourtStdNo().toString());
                    } else if (flist.getCourtGradation() == 2) {
                        CourtAreaNo.add(flist);
                    }
                }
                for (TreeNode flist : CourtAreaNo) {
                    if (CourtAreaNoStr.indexOf(flist.getCourtAreaNo().toString()) > -1) {
                        list.remove(flist);
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        for (TreeNode tn : list) {
            tn.cls = null;
        }
        return "json";
    }


//    //根据编制类型来进行法院的groupby
//    @Action("getCourtByOrg")
//    public String getCourtByOrg() {
//
//        if (orgCode == null) {
//            return "json";
//        }
//        List<Integer> codeList = orgCodeMap.get(orgCode);
//        if(codeList == null ){
//            return "json";
//        }
//        try {
//
//            UmsUserInfoView u = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
//
//            //获取权限可以访问的法院
//            List<Map<String, Object>> clist = courtFullService.listByAuthUserID(u.getId());
//
//            //自身权限可以查询的法院列表
//            List<String> courtList ;
//            boolean flag = false;
//            for (Map<String, Object> rlist : clist) {
//                //超级管理员 显示高院 和 所有中级法院
//                if (rlist.get("rcourt_no").toString().equals("9999")) {
//                    flag = true;
//                    break;
//                }
//            }
//            if (flag) {
//                List<UmsCourtFull> umsCourtFulls = courtFullService.selectByListAll();
//                courtList = umsCourtFulls.stream().map(UmsCourtFull::getCourtCode).collect(Collectors.toList());
//            } else {
//                courtList = clist.stream().map(stringObjectMap -> {
//                    Object court_code = stringObjectMap.get("court_code");
//                    if (court_code == null) {
//                        return "";
//                    }
//                    return court_code.toString();
//                }).collect(Collectors.toList());
//            }
//
//            //根据地方编制和中央编制和权限来查询法院
//
//            data = courtFullService.getCourtByOrganization(courtList, codeList);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return "json";
//    }

    private List<TreeNode> nodesByDeptKey(UmsDepartmentKey key) {
        List<UmsDepartment> dlist = departmentService.listChildrenByKey(key);

        return nodesByDeptList(dlist);
    }

    private List<TreeNode> nodesByDeptList(List<UmsDepartment> dlist) {
        List<TreeNode> list = new ArrayList<>();
        for (UmsDepartment d : dlist) {
            TreeNode n = new TreeNode();
            n.setText(d.getDeptName());
            n.setDeptNo(d.getDeptNo());
            n.setCourtNo(d.getCourtNo());
            n.setSortNo(d.getSortNo());
            n.setOrgCode(d.getOrgCode());
            n.id = n.courtNo + "," + n.deptNo;
            UmsDepartmentKey dk = new UmsDepartmentKey();
            dk.setCourtNo(d.getCourtNo());
            dk.setDeptNo(d.getDeptNo());
            if (departmentService.isLeaf(dk)) {
                n.leaf = true;
                n.cls = "icon-dept";
            } else {
                n.leaf = false;
                n.cls = "icon-dept-leaf";
            }
            list.add(n);
        }
        return list;
    }

    private List<TreeNode> nodesByCourtNo(Integer courtNo) {
        List<TreeNode> list = new ArrayList<>();
        List<UmsCourtFull> clist = courtFullService.listChildrenByCourtNo(courtNo);
        List<UmsDepartment> cdlist = departmentService.selectByCourtNoAndDeptLevel(courtNo, 1); //只要第一级
        list.addAll(nodesByDeptList(cdlist));
        for (UmsCourtFull c : clist) {
            TreeNode n = new TreeNode();
            n.setText(c.getCourtStdName());
            n.setCourtNo(c.getCourtNo());
            n.id = n.courtNo + "," + n.deptNo;
            n.leaf = false;
            n.cls = "icon-court";
            list.add(n);
        }
        return list;
    }

    // 根据传来的court_no查询该法院下面的部门,不查询其他法院及其部门 20190409
    private List<TreeNode> nodesByCourtNo_2(Integer courtNo) {
        List<TreeNode> list = new ArrayList<>();
        List<UmsDepartment> cdlist = departmentService.selectByCourtNoAndDeptLevel(courtNo, 1); //只要第一级
        list.addAll(nodesByDeptList(cdlist));
        return list;
    }

    //把部门放到内设机构里面
    private List<TreeNode> nodesByCourtNo3(Integer courtNo) {

        List<UmsDepartment> cdlist = departmentService.selectByCourtNoAndDeptLevel(courtNo, 1); //只要第一级

        return new ArrayList<>(nodesByDeptList(cdlist));
    }

    private List<TreeNode> nodesByCourtNo4(Integer courtNo) {
        List<TreeNode> list = new ArrayList<>();
        List<UmsCourtFull> clist = courtFullService.listChildrenByCourtNo(courtNo);
        if (clist.size() > 0) {
            //最先把内设机构加上 id写死为 101093324
            TreeNode nsjg = new TreeNode();
            nsjg.setText("内设机构");
            nsjg.setCourtNo(courtNo);
            nsjg.id = nsjg.courtNo + "," + "101093324";
            nsjg.leaf = false;
            nsjg.cls = "icon-court";
            list.add(nsjg);
            for (UmsCourtFull c : clist) {
                TreeNode n = new TreeNode();
                n.setText(c.getCourtStdName());
                n.setCourtNo(c.getCourtNo());
                n.id = n.courtNo + "," + n.deptNo;
                n.leaf = false;
                n.cls = "icon-court";
                list.add(n);
            }
        } else {
            //加入部门
            list.addAll(nodesByCourtNo3(courtNo));
        }
        return list;
    }

    private List<TreeNode> nodesByCourtNo2(Integer courtNo) {
        List<TreeNode> list = new ArrayList<>();
        List<UmsCourtFull> clist = courtFullService.listChildrenByCourtNo(courtNo);
        for (UmsCourtFull c : clist) {
            TreeNode n = new TreeNode();
            n.setText(c.getCourtStdName());
            n.setCourtNo(c.getCourtNo());
            n.id = n.courtNo + "," + n.deptNo;
            n.leaf = false;
            n.cls = "icon-court";
            list.add(n);
        }
        return list;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    /**
     * 树的节点
     */
    public class TreeNode {

        private String id;
        private String text;
        private Integer courtNo;
        private Integer deptNo;
        private Boolean leaf;
        private String cls;
        //判断用 和树节点无关
        private Integer courtAreaNo;
        private Integer courtStdNo;
        private Integer courtGradation;
        private Integer sortNo;
        private String orgCode;
        private List<TreeNode> children;
        private boolean expanded;

        public Integer getCourtGradation() {
            return courtGradation;
        }

        public void setCourtGradation(Integer courtGradation) {
            this.courtGradation = courtGradation;
        }

        public Integer getCourtStdNo() {
            return courtStdNo;
        }

        public void setCourtStdNo(Integer courtStdNo) {
            this.courtStdNo = courtStdNo;
        }

        public Integer getCourtAreaNo() {
            return courtAreaNo;
        }

        public void setCourtAreaNo(Integer courtAreaNo) {
            this.courtAreaNo = courtAreaNo;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Integer getCourtNo() {
            return courtNo;
        }

        public void setCourtNo(Integer courtNo) {
            this.courtNo = courtNo;
        }

        public Integer getDeptNo() {
            return deptNo;
        }

        public void setDeptNo(Integer deptNo) {
            this.deptNo = deptNo;
        }

        public Boolean getLeaf() {
            return leaf;
        }

        public void setLeaf(Boolean leaf) {
            this.leaf = leaf;
        }

        public String getCls() {
            return cls;
        }

        public void setCls(String cls) {
            this.cls = cls;
        }

        public Integer getSortNo() {
            return sortNo;
        }

        public void setSortNo(Integer sortNo) {
            this.sortNo = sortNo;
        }

        public String getOrgCode() {
            return orgCode;
        }

        public void setOrgCode(String orgCode) {
            this.orgCode = orgCode;
        }

        public List<TreeNode> getChildren() {
            return children;
        }

        public void setChildren(List<TreeNode> children) {
            this.children = children;
        }

        public boolean isExpanded() {
            return expanded;
        }

        public void setExpanded(boolean expanded) {
            this.expanded = expanded;
        }
    }

}
