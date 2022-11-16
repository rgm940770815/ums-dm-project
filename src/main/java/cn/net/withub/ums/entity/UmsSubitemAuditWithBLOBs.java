package cn.net.withub.ums.entity;

public class UmsSubitemAuditWithBLOBs extends UmsSubitemAudit {
    private String serializeContent;

    private String showContent;

    private String changeContent;

    private String changeContentDescribe;

    // 审核类型
    private String shlx;

    public String getShlx() {
        return shlx;
    }

    public void setShlx(String shlx) {
        this.shlx = shlx;
    }

    public String getSerializeContent() {
        return serializeContent;
    }

    public void setSerializeContent(String serializeContent) {
        this.serializeContent = serializeContent == null ? null : serializeContent.trim();
    }

    public String getShowContent() {
        return showContent;
    }

    public void setShowContent(String showContent) {
        this.showContent = showContent == null ? null : showContent.trim();
    }

    public String getChangeContent() {
        return changeContent;
    }

    public void setChangeContent(String changeContent) {
        this.changeContent = changeContent == null ? null : changeContent.trim();
    }

    public String getChangeContentDescribe() {
        return changeContentDescribe;
    }

    public void setChangeContentDescribe(String changeContentDescribe) {
        this.changeContentDescribe = changeContentDescribe == null ? null : changeContentDescribe.trim();
    }
}