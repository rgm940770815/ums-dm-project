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
 * 大面板
 *
 * @author Diluka
 */
public class BoardItem {

    public String menuId;

    private String id;
    private String homePage;
    private String name;

    private List<Menu> menu;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public List<Menu> getMenu() {
        return menu;
    }

    public void setMenu(List<Menu> menu) {
        this.menu = menu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 一级菜单
     *
     * @param menus
     * @return
     */
    public static List<BoardItem> fromMenuList(List<UmsMenu> menus) {
        List<BoardItem> list = new ArrayList<>();

        for (UmsMenu um : menus) {
            BoardItem item = new BoardItem();
            item.id = um.getPageName();
            item.menuId = um.getId();
            item.name = um.getMenuName();

            list.add(item);
        }

        return list;
    }

}
