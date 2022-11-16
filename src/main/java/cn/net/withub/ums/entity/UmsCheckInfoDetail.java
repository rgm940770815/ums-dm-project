package cn.net.withub.ums.entity;

public class UmsCheckInfoDetail {
    private String unionId;

    private String tableName;

    private String fieldName;

    private String fieldValue;

    private String fieldCode;

    private String fieldCodeValue;

    private String fieldCodeName;

    private Integer queryType;

    private Integer sortNo;

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public String getFieldCode() {
        return fieldCode;
    }

    public void setFieldCode(String fieldCode) {
        this.fieldCode = fieldCode;
    }

    public String getFieldCodeValue() {
        return fieldCodeValue;
    }

    public void setFieldCodeValue(String fieldCodeValue) {
        this.fieldCodeValue = fieldCodeValue;
    }

    public String getFieldCodeName() {
        return fieldCodeName;
    }

    public void setFieldCodeName(String fieldCodeName) {
        this.fieldCodeName = fieldCodeName;
    }

    public Integer getQueryType() {
        return queryType;
    }

    public void setQueryType(Integer queryType) {
        this.queryType = queryType;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }
}