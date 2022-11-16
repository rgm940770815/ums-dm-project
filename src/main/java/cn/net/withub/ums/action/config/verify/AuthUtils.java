package cn.net.withub.ums.action.config.verify;

import cn.net.withub.ums.auth.AuthorityHelper;
import cn.net.withub.ums.common.UmsConstant;
import cn.net.withub.ums.entity.UmsUserInfoView;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthUtils {

    @Autowired
    private AuthorityHelper authorityHelper;

    //获取该用户下 拥有权限的法院列表
    public List<Integer> getAuthCourt() {
        UmsUserInfoView u = (UmsUserInfoView) ActionContext.getContext().getSession().get(UmsConstant.LOGIN_USER_SESSION_KEY.toString());
        List<Integer> courtNoList = authorityHelper.accessibleCourtNoList("查看");
        if (courtNoList.contains(-9999)) {//管理员权限 GOGOGO
            return null;
        }
        return courtNoList;
    }

    public void appendCourt(StringBuilder sql, Integer courtNo) throws Exception {
        //没有法院就限制法院
        if (courtNo == null) {
            List<Integer> authCourt = getAuthCourt();
            if (authCourt != null && authCourt.size() > 0) {
                sql.append(" and a.court_no in ( ");
                for (int i = 0; i < authCourt.size(); i++) {
                    Integer integer = authCourt.get(i);
                    sql.append(" '").append(integer).append("' ");
                    if (i != authCourt.size() - 1) {
                        sql.append(",");
                    }
                }
                sql.append(" ) ");
            } else {
                throw new Exception("没有权限");
            }
        } else {
            sql.append(" and a.court_no = ").append(courtNo);
        }
    }

    public String appendCourtSql(Integer courtNo) throws Exception {
        StringBuilder sql = new StringBuilder();
        //没有法院就限制法院
        if (courtNo == null) {
            List<Integer> authCourt = getAuthCourt();
            if (authCourt != null && authCourt.size() > 0) {
                sql.append(" and a.court_no in ( ");
                for (int i = 0; i < authCourt.size(); i++) {
                    Integer integer = authCourt.get(i);
                    sql.append(" '").append(integer).append("' ");
                    if (i != authCourt.size() - 1) {
                        sql.append(",");
                    }
                }
                sql.append(" ) ");
            } else {
                throw new Exception("没有权限");
            }
        } else {
            sql.append(" and a.court_no = ").append(courtNo);
        }

        return sql.toString();
    }


}
