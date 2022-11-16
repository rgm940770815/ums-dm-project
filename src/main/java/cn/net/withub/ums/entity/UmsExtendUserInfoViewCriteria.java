package cn.net.withub.ums.entity;

/**
 * Created by Cypress on 2017/2/24.
 */
public class UmsExtendUserInfoViewCriteria    {

    private String fieldSql;
    private String tableName;
    private String orderBySql;

    public String getFieldSql() {
        return fieldSql;
    }

    public void setFieldSql(String fieldSql) {
        this.fieldSql = fieldSql;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getOrderBySql() {
        return orderBySql;
    }

    public void setOrderBySql(String orderBySql) {
        this.orderBySql = orderBySql;
    }
}
