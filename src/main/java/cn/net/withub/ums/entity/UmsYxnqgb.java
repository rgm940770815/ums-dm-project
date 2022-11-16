package cn.net.withub.ums.entity;

import java.util.Objects;

/**
 * 优秀年轻干部信息集实体
 */
public class UmsYxnqgb {

    /**
     * 用户id
     */
    private String userId;
    /**
     * 主键
     */
    private String id;
    /**
     * 是否优秀年轻干部
     */
    private Integer nSfyxnqgb;
    /**
     * 进入条件
     */
    private Integer nJrtj;
    /**
     * 是否当前信息
     */
    private Integer nDqxx;

    /**
     * 用户编码
     */
    private Integer userNo;
    /**
     * 法院编码
     */
    private Integer courtNo;
    /**
     * 法院代码
     */
    private String courtCode;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getnSfyxnqgb() {
        return nSfyxnqgb;
    }

    public void setnSfyxnqgb(Integer nSfyxnqgb) {
        this.nSfyxnqgb = nSfyxnqgb;
    }

    public Integer getnJrtj() {
        return nJrtj;
    }

    public void setnJrtj(Integer nJrtj) {
        this.nJrtj = nJrtj;
    }

    public Integer getnDqxx() {
        return nDqxx;
    }

    public void setnDqxx(Integer nDqxx) {
        this.nDqxx = nDqxx;
    }

    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    public Integer getCourtNo() {
        return courtNo;
    }

    public void setCourtNo(Integer courtNo) {
        this.courtNo = courtNo;
    }

    public String getCourtCode() {
        return courtCode;
    }

    public void setCourtCode(String courtCode) {
        this.courtCode = courtCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UmsYxnqgb umsYxnqgb = (UmsYxnqgb) o;
        return Objects.equals(userId, umsYxnqgb.userId) && Objects.equals(id, umsYxnqgb.id) && Objects.equals(nSfyxnqgb, umsYxnqgb.nSfyxnqgb) && Objects.equals(nJrtj, umsYxnqgb.nJrtj) && Objects.equals(nDqxx, umsYxnqgb.nDqxx) && Objects.equals(userNo, umsYxnqgb.userNo) && Objects.equals(courtNo, umsYxnqgb.courtNo) && Objects.equals(courtCode, umsYxnqgb.courtCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, id, nSfyxnqgb, nJrtj, nDqxx, userNo, courtNo, courtCode);
    }

    @Override
    public String toString() {
        return "UmsYxnqgb{" +
                "userId='" + userId + '\'' +
                ", id='" + id + '\'' +
                ", nSfyxnqgb=" + nSfyxnqgb +
                ", nJrtj=" + nJrtj +
                ", nDqxx=" + nDqxx +
                ", userNo=" + userNo +
                ", courtNo=" + courtNo +
                ", courtCode='" + courtCode + '\'' +
                '}';
    }
}
