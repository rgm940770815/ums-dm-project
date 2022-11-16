package cn.net.withub.ums.webService.interior;

import java.io.Serializable;
import java.util.Objects;

/**
 * 外部接口授权用户实体类
 */
public class FtpAuthUser implements Serializable {

    private static final long serialVersionUID = 8648461840050379036L;
    private String id;          //id
    private String username;    //用户名
    private String password;    //密码
    private String salt;        //盐值
    private Integer isStart;    //是否启用（1 启用， 0 停用）
    private String createTime;  //创建时间


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getIsStart() {
        return isStart;
    }

    public void setIsStart(Integer isStart) {
        this.isStart = isStart;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FtpAuthUser that = (FtpAuthUser) o;
        return Objects.equals(id, that.id) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(salt, that.salt) && Objects.equals(isStart, that.isStart) && Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, salt, isStart, createTime);
    }
}
