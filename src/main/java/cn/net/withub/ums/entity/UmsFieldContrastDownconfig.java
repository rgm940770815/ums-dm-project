package cn.net.withub.ums.entity;

import java.util.ArrayList;
import java.util.List;

public class UmsFieldContrastDownconfig {

    private List<String> fymc;
    private String time;
    private Integer type;   //1.本院，2.包括下级法院
    private boolean onlyaddnew = false; //是否只入新数据
    private List<Config> configList;

    public List<String> getFymc() {
        return fymc;
    }

    public void setFymc(List<String> fymc) {
        this.fymc = fymc;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<Config> getConfigList() {
        return configList;
    }

    public void setConfigList(List<Config> configList) {
        this.configList = configList;
    }

    public boolean isOnlyaddnew() {
        return onlyaddnew;
    }

    public void setOnlyaddnew(boolean onlyaddnew) {
        this.onlyaddnew = onlyaddnew;
    }

    public class Config {
        private String table;
        private List<String> field;
        private List<String> compareFields = new ArrayList<>();     //对比字段，默认用id去找是否存在相应记录
        private boolean tempTable = false;  //是否不直接存入正式表，用缓存表保存，需要先创建缓存表，表名为 temp_源表

        public String getTable() {
            return table;
        }

        public void setTable(String table) {
            this.table = table;
        }

        public List<String> getField() {
            return field;
        }

        public void setField(List<String> field) {
            this.field = field;
        }

        public List<String> getCompareFields() {
            return compareFields;
        }

        public void setCompareFields(List<String> compareFields) {
            this.compareFields = compareFields;
        }

        public boolean isTempTable() {
            return tempTable;
        }

        public void setTempTable(boolean tempTable) {
            this.tempTable = tempTable;
        }
    }
}