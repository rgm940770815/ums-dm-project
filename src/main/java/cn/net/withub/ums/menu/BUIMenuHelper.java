/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.menu;

import cn.net.withub.ums.common.UmsConstant;
import cn.net.withub.ums.dao.UmsSubitemAuditMapper;
import cn.net.withub.ums.entity.*;
import cn.net.withub.ums.model.menu.BoardItem;
import cn.net.withub.ums.model.menu.Menu;
import cn.net.withub.ums.model.menu.MenuItem;
import cn.net.withub.ums.service.UmsApplyForUpdateService;
import cn.net.withub.ums.service.UmsAuthorityService;
import cn.net.withub.ums.service.UmsUserInfoService;
import cn.net.withub.ums.service.UmsUserInfoViewService;
import cn.net.withub.ums.service.menu.UmsMenuService;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * BUI框架菜单
 *
 * @author Diluka
 */
@Component
public class BUIMenuHelper {

    @Autowired
    private UmsAuthorityService authorityService;

    @Autowired
    private UmsMenuService menuService;

    @Autowired
    private UmsUserInfoViewService userInfoViewService;

    @Autowired
    UmsApplyForUpdateService umsApplyForUpdateService;

    @Autowired
    private UmsUserInfoService userInfoService;

    @Autowired
    UmsSubitemAuditMapper subitemAuditMapper;

    /**
     * 根据用户权限生成首页的BUI框架菜单
     *
     * @param userId
     * @return
     */
    public List<BoardItem> generateUserMenu(String userId) {
        //菜单权限表
        Map<String, Integer> menuAuth = authorityService.menuAuth(userId);

        //一级菜单，大面板
        List<BoardItem> boardItems = BoardItem.fromMenuList(menuService.menusByLevel(1));
        for (int i = boardItems.size() - 1; i >= 0; i--) {
            //去掉无权限菜单
            if (menuAuth.get(boardItems.get(i).menuId) == null) {
                boardItems.remove(i);
                continue;
            }

            BoardItem bi = boardItems.get(i);
            //二级菜单，功能分组
            List<Menu> menus = Menu.fromMenuList(menuService.menusByParentId(bi.menuId));
            bi.setMenu(menus);

            for (int j = menus.size() - 1; j >= 0; j--) {
                if (menuAuth.get(menus.get(j).menuId) == null) {
                    menus.remove(j);
                    continue;
                }

                Menu m = menus.get(j);
                //三级菜单，功能
                List<MenuItem> menuItems = MenuItem.fromMenuList(menuService.menusByParentId(m.menuId));
                m.setItems(menuItems);

                for (int k = menuItems.size() - 1; k >= 0; k--) {
                    if (menuAuth.get(menuItems.get(k).menuId) == null) {
                        menuItems.remove(k);
                        continue;
                    }

                    MenuItem mi = menuItems.get(k);
                    mi.setAuthCode(menuAuth.get(menuItems.get(k).menuId));
                    //待处理调职人员信息 等的处理显示问题
                    String id = mi.getMenuId();
                    if (StringUtils.hasText(id)) {
                        int count = 0;
                        try {
                            if (id.equals("1111")) {
                                //待处理人员信息
                                UmsUserInfoViewCriteria criteria = new UmsUserInfoViewCriteria();
                                UmsUserInfoView u = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
                                String username = u.getUsername();
                                // 根据权限显示列表旁的提示数字
                                if ("superuser".equals(username)) {
                                    criteria.createCriteria().andUserTypeGreaterThanOrEqualTo(11);
                                } else {
                                    // 每个管理员只显示自己法院的
                                    criteria.setCourtNo(u.getCourtNo());
                                    criteria.createCriteria().andUserTypeGreaterThanOrEqualTo(11).andIsInfoCompleteNotEqualTo(1).andCourtNoEqualTo(u.getCourtNo());
                                }
                                count = userInfoViewService.count_dwszl(criteria);
                                mi.setText(mi.getText() +"<span style='color:red' >" + "(" + count + ")" + "</span>");
                            }
                            if (id.equals("1196")) {
                                //待处理调职人员信息
                                UmsApplyForUpdateExample umsApplyForUpdateExample = new UmsApplyForUpdateExample();
                                umsApplyForUpdateExample.createCriteria().andTypeEqualTo(1).andHandleEqualTo(0);
                                count = umsApplyForUpdateService.countByExample(umsApplyForUpdateExample);
                                mi.setText(mi.getText() +"<span style='color:red' >" + "(" + count + ")" + "</span>");

                            }
                            if(id.equals("119")){
                                UmsChangeJob umsChangeJob  = new UmsChangeJob();
                                umsChangeJob.setState("申请中");
                                count = userInfoService.countChangeJob(umsChangeJob);
                                mi.setText(mi.getText() +"<span style='color:red' >" + "(" + count + ")" + "</span>");
                            }
                            // 法官信息审核
                            if(id.equals("146")){
                                //待处理人员信息
                                UmsSubitemAuditExample example = new UmsSubitemAuditExample();
                                UmsUserInfoView u = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
                                String authType = u.getAuthType().toString();
                                // 根据权限显示列表旁的提示数字
                                if ("ADMIN".equals(authType)) {
                                    // auditStatus = 0 未审核的
                                    example.createCriteria().andAuditStatusEqualTo(0);
                                } else if ("COURT".equals(authType)) {
                                    // 每个管理员只显示自己法院的
                                    // auditStatus = 0 未审核的
                                    example.createCriteria().andAuditStatusEqualTo(0).andOperatedUserCourtNoEqualTo(u.getCourtNo());
                                }
                                count = subitemAuditMapper.countByExample(example);
                                mi.setText(mi.getText() +"<span style='color:red' >" + "(" + count + ")" + "</span>");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }

                }
            }
        }

        //将每页第一个功能设置为默认打开
        for (BoardItem bi : boardItems) {
            if (bi.getMenu() != null && bi.getMenu().size() > 0) {
                Menu m = bi.getMenu().get(0);

                if (m.getItems() != null && m.getItems().size() > 0) {
                    bi.setHomePage(m.getItems().get(0).getId());
                }
            }
        }

        return boardItems;
    }
}
