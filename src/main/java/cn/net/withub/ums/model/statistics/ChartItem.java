/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.model.statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Diluka
 */
public class ChartItem {

    private String name;
    private Number x;
    private Number y;

    public ChartItem(String name, Number x, Number y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public ChartItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Number getX() {
        return x;
    }

    public void setX(Number x) {
        this.x = x;
    }

    public Number getY() {
        return y;
    }

    public void setY(Number y) {
        this.y = y;
    }

    /**
     *
     * @param list
     * @return
     */
    public static List<ChartItem> fromMapList(List<Map<String, Object>> list) {
        return fromMapList(list, "name", "x", "y");
    }

    /**
     *
     * @param list
     * @param nameField
     * @param xField
     * @param yField
     * @return
     */
    public static List<ChartItem> fromMapList(List<Map<String, Object>> list, String nameField, String xField, String yField) {

        List<ChartItem> chartItems = new ArrayList<>();

        for (Map<String, Object> item : list) {
            String name = (String) item.get(nameField);
            Number x = (Number) (item.get(xField));
            Number y = (Number) (item.get(yField));
            chartItems.add(new ChartItem(name, x, y));
        }

        return chartItems;
    }

}
