package cn.net.withub.ums.dao.attach.experimental;


import cn.net.withub.ums.util.StringTools;
import org.apache.ibatis.jdbc.SQL;

import javax.print.DocFlavor;

import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;

/**
 * Created by Cypress on 2016/5/24.
 */
public class MabitsProvider {

    public String insertUpdateTime() {

        return "INSERT INTO ums_upload_record_info SELECT UUID(),NOW(),COUNT(1) + 1 ,COUNT(1)+ 1 from ums_upload_record_info";

    }

}
