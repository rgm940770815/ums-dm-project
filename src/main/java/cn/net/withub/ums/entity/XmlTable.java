package cn.net.withub.ums.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuizhibin on 2018/10/11.
 * 上报xml表
 */
public class XmlTable {
    private String table;   //表名
    private String tablename;   //表描述
    private List<Column> columnsList = new ArrayList<>();  //字段列表

    //字段
    public class Column {

        private String field;       //信息项
        private String fieldName;  //信息项名称
        private boolean required = false;//必填
        private String type;//字段类型
        private int typeLength; //字段长度
        private boolean hasCode = false;//是否有编码表
        private String codeTable;//编码表名
        private int index;

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public boolean isRequired() {
            return required;
        }

        public void setRequired(boolean required) {
            this.required = required;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getTypeLength() {
            return typeLength;
        }

        public void setTypeLength(int typeLength) {
            this.typeLength = typeLength;
        }

        public boolean isHasCode() {
            return hasCode;
        }

        public void setHasCode(boolean hasCode) {
            this.hasCode = hasCode;
        }

        public String getCodeTable() {
            return codeTable;
        }

        public void setCodeTable(String codeTable) {
            this.codeTable = codeTable;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public List<Column> getColumnsList() {
        return columnsList;
    }

    public void setColumnsList(List<Column> columnsList) {
        this.columnsList = columnsList;
    }
}
