/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service.menu;

import cn.net.withub.ums.entity.UmsMenu;
import java.util.List;

/**
 *
 * @author Diluka
 */
public interface UmsMenuService {

    List<UmsMenu> menusByLevel(int level);

    List<UmsMenu> menusByParentId(String pid);

    UmsMenu menuById(String id);

}
