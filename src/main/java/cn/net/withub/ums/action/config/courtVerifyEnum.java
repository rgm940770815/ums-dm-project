package cn.net.withub.ums.action.config;

public enum courtVerifyEnum {
    ZZZX("zzzx", "1", "政法专项编制数"),
    YEFG("yefg", "1", "法官员额职数"),
    LEVEL("level", null, "法官等级");

    courtVerifyEnum(String cScope, String cKey, String name) {
        this.cScope = cScope;
        this.cKey = cKey;
        this.name = name;
    }

    private String cScope;
    private String cKey;
    private String name;


    public String getcScope() {
        return cScope;
    }

    public void setcScope(String cScope) {
        this.cScope = cScope;
    }

    public String getcKey() {
        return cKey;
    }

    public void setcKey(String cKey) {
        this.cKey = cKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
