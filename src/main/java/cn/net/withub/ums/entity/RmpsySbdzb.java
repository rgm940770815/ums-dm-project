package cn.net.withub.ums.entity;

public class RmpsySbdzb {
    private Integer id;

    private String ourform;

    private String zgyform;

    private String zgyformname;

    private String primarykey;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOurform() {
        return ourform;
    }

    public void setOurform(String ourform) {
        this.ourform = ourform == null ? null : ourform.trim();
    }

    public String getZgyform() {
        return zgyform;
    }

    public void setZgyform(String zgyform) {
        this.zgyform = zgyform == null ? null : zgyform.trim();
    }

    public String getZgyformname() {
        return zgyformname;
    }

    public void setZgyformname(String zgyformname) {
        this.zgyformname = zgyformname == null ? null : zgyformname.trim();
    }

    public String getPrimarykey() {
        return primarykey;
    }

    public void setPrimarykey(String primarykey) {
        this.primarykey = primarykey == null ? null : primarykey.trim();
    }
}