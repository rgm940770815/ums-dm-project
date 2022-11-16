package cn.net.withub.ums.entity;

public class UmsVerifyConfig extends UmsVerifyConfigKey {
    private Short cValue;

    private String cName;

    public Short getcValue() {
        return cValue;
    }

    public void setcValue(Short cValue) {
        this.cValue = cValue;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }
}