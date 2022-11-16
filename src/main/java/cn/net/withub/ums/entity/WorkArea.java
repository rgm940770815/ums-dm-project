package cn.net.withub.ums.entity;

public class WorkArea {
    private Integer id;

    private String aa;

    private String bb;

    private String cc;

    private String dd;

    private String ee;

    private String name;

    private String isParent;

    private String csjg;

    public String getCsjg() {
        return csjg;
    }

    public void setCsjg(String csjg) {
        this.csjg = csjg;
    }

    public String getIsParent() {
        return isParent;
    }

    public void setIsParent(String isParent) {
        this.isParent = isParent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAa() {
        return aa;
    }

    public void setAa(String aa) {
        this.aa = aa == null ? null : aa.trim();
    }

    public String getBb() {
        return bb;
    }

    public void setBb(String bb) {
        this.bb = bb == null ? null : bb.trim();
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc == null ? null : cc.trim();
    }

    public String getDd() {
        return dd;
    }

    public void setDd(String dd) {
        this.dd = dd == null ? null : dd.trim();
    }

    public String getEe() {
        return ee;
    }

    public void setEe(String ee) {
        this.ee = ee == null ? null : ee.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}