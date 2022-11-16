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
 * 边侧菜单分组
 *
 * @author Diluka
 */
public class Menu {

    public String menuId;

    private String text;
    private List<MenuItem> items = new ArrayList<>();

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }

    /**
     * 二级菜单
     *
     * @param menus
     * @return
     */
    public static List<Menu> fromMenuList(List<UmsMenu> menus) {
        List<Menu> list = new ArrayList<>();

        for (UmsMenu um : menus) {
            Menu item = new Menu();
            item.menuId = um.getId();
            item.text = um.getMenuName();

            list.add(item);
        }

        return list;
    }

}
