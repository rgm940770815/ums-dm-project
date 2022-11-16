package cn.net.withub.ums.entity;

public class RmpsySbdzzd {
    private Integer id;

    private String glid;

    private String ourzd;

    private String ourzdlx;

    private String ourzdmc;

    private String sbzd;

    private String sbzdlx;

    private String sbzdmc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGlid() {
        return glid;
    }

    public void setGlid(String glid) {
        this.glid = glid == null ? null : glid.trim();
    }

    public String getOurzd() {
        return ourzd;
    }

    public void setOurzd(String ourzd) {
        this.ourzd = ourzd == null ? null : ourzd.trim();
    }

    public String getOurzdlx() {
        return ourzdlx;
    }

    public void setOurzdlx(String ourzdlx) {
        this.ourzdlx = ourzdlx == null ? null : ourzdlx.trim();
    }

    public String getOurzdmc() {
        return ourzdmc;
    }

    public void setOurzdmc(String ourzdmc) {
        this.ourzdmc = ourzdmc == null ? null : ourzdmc.trim();
    }

    public String getSbzd() {
        return sbzd;
    }

    public void setSbzd(String sbzd) {
        this.sbzd = sbzd == null ? null : sbzd.trim();
    }

    public String getSbzdlx() {
        return sbzdlx;
    }

    public void setSbzdlx(String sbzdlx) {
        this.sbzdlx = sbzdlx == null ? null : sbzdlx.trim();
    }

    public String getSbzdmc() {
        return sbzdmc;
    }

    public void setSbzdmc(String sbzdmc) {
        this.sbzdmc = sbzdmc == null ? null : sbzdmc.trim();
    }
}