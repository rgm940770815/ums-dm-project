/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.util.formatter.impl;

import cn.net.withub.ums.util.formatter.DataPack;
import cn.net.withub.ums.util.formatter.Formatter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class FlexiGridJsonFormatterImpl implements Formatter {

    @Override
    public Object format(DataPack data) {
        FlexiGrid grid = new FlexiGrid();
        grid.setPage(data.getPage());
        grid.setTotal(data.getTotal());

        if (data.getData() instanceof Collection) {
            Collection c = (Collection) data.getData();

            for (Object o : c) {
                Object id = null;
                try {
                    Field f = o.getClass().getDeclaredField(data.getId());
                    f.setAccessible(true);
                    id = f.get(o);
                } catch (Exception e) {
                }

                FlexiGridRow row = new FlexiGridRow(id, o);
                grid.rows.add(row);
            }
        } else if (data.getData() instanceof Map) {
            Map m = (Map) data.getData();

            for (Object key : m.keySet()) {
                FlexiGridRow row = new FlexiGridRow(key, m.get(key));
                grid.rows.add(row);
            }
        }

        return grid;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public class FlexiGrid {

        private int page;
        private int total;
        private List<FlexiGridRow> rows = new ArrayList<>();

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

        public List<FlexiGridRow> getRows() {
            return rows;
        }

        public void setRows(List<FlexiGridRow> rows) {
            this.rows = rows;
        }

    }

    public class FlexiGridRow {

        private Object id;
        private Object cell;

        public FlexiGridRow() {
        }

        public FlexiGridRow(Object id, Object cell) {
            this.id = id;
            this.cell = cell;
        }

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
        }

        public Object getCell() {
            return cell;
        }

        public void setCell(Object cell) {
            this.cell = cell;
        }

    }

}
