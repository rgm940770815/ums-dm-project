/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.util.formatter;

/**
 *
 * @author Diluka
 */
public class DataPack {

    private String id;
    private int page;
    private int total;
    private Object data;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public DataPack() {
    }

    public DataPack(Object data) {
        this.data = data;
    }

    public DataPack(int page, int total, Object data) {
        this.page = page;
        this.total = total;
        this.data = data;
    }

    public DataPack(String id, int page, int total, Object data) {
        this.id = id;
        this.page = page;
        this.total = total;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
