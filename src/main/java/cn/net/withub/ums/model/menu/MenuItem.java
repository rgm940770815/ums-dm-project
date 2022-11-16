/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.model.menu;

import cn.net.withub.ums.entity.UmsMenu;
import java.util.ArrayList;
import java.util.List;

/**
 * 边侧菜单项
 *
 * @author Diluka
 */
public class MenuItem {

    public String menuId;

    private String id;
    private String text;
    private String href;
    private Integer authCode;

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

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Integer getAuthCode() {
        return authCode;
    }

    public void setAuthCode(Integer authCode) {
        this.authCode = authCode;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    /**
     * 三级菜单
     *
     * @param menus
     * @return
     */
    public static List<MenuItem> fromMenuList(List<UmsMenu> menus) {
        List<MenuItem> list = new ArrayList<>();

        for (UmsMenu um : menus) {
            MenuItem item = new MenuItem();
            item.id = um.getPageName();
            item.menuId = um.getId();
            item.href = "/ums/" + um.getHref();
            item.text = um.getMenuName();

            list.add(item);
        }

        return list;
    }

}
