/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service.menu.impl;

import cn.net.withub.ums.dao.UmsMenuMapper;
import cn.net.withub.ums.entity.UmsMenu;
import cn.net.withub.ums.entity.UmsMenuCriteria;
import cn.net.withub.ums.service.menu.UmsMenuService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UmsMenuServiceImpl implements UmsMenuService {

    @Autowired
    private UmsMenuMapper menuMapper;

    @Override
    public List<UmsMenu> menusByLevel(int level) {
        String like = "";
        for (int i = 0; i < level; i++) {
            like += "_";
        }

        UmsMenuCriteria criteria = new UmsMenuCriteria();
        criteria.createCriteria().andIdLike(like);

        criteria.setOrderByClause("sort_no");

        return menuMapper.selectByExample(criteria);
    }

    @Override
    public List<UmsMenu> menusByParentId(String pid) {
        UmsMenuCriteria criteria = new UmsMenuCriteria();
        criteria.createCriteria().andIdLike(pid + "_");
        if ("11".equals(pid)) {
            criteria.or().andIdLike(pid + "__");
        }
        criteria.setOrderByClause("sort_no");

        return menuMapper.selectByExample(criteria);
    }

    @Override
    public UmsMenu menuById(String id) {
        return menuMapper.selectByPrimaryKey(id);
    }

}
