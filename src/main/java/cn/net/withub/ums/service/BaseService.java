/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.service;

import java.util.List;
import org.apache.ibatis.session.RowBounds;

/**
 *
 * @author Diluka
 * @param <E> 某实体类
 * @param <C> 与某实体类对应的Criteria
 */
public interface BaseService<E, C> {

    int insert(E entity);

    int delete(E entity);

    int deleteById(Object id);

    int update(E entity);

    E selectById(Object id);

    int countAll();

    /**
     * 搜索
     *
     * @param criteria
     * @param rowBounds
     * @return
     */
    List<E> search(C criteria, RowBounds rowBounds);

    /**
     *
     * @param criteria
     * @return
     */
    int count(C criteria);

}
