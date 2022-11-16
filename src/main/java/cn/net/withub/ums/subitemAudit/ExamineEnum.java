package cn.net.withub.ums.subitemAudit;

public enum ExamineEnum {
    INSERT("insert","新增"),UPDATE("update","修改");

    ExamineEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
