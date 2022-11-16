package cn.net.withub.ums.subitemAudit;

public enum AuditStatusEnum {
    S_1(0, "未审核"), S_2(1, "审核通过"), S_3(2, "审核未通过");

    private int status;
    private String describe;

    AuditStatusEnum(int status, String describe) {
        this.status = status;
        this.describe = describe;
    }

    public int getStatus() {
        return status;
    }

    public String getDescribe() {
        return describe;
    }
}
