package cn.net.withub.ums.action.userinfo;

import cn.net.withub.ums.entity.UmsRmpsyJbxx;
import cn.net.withub.ums.entity.UmsUserInfo;
import cn.net.withub.ums.util.EncodeDecodeDataInfo;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/*
select id,idcard,fullname,phone_number,ext_office_phone from ums_user_info where fullname like '%测试2011%';
select id,uuid,fullname,idcard,phone,office_phone from xtpt_t_user where uuid = '09251b6f-b81a-f0be-1d16-73380049cc69' limit 10;
2022.10.17 更新  加密字段
 */
@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/upAction")
@Results({@Result(name = "json", type = "json", params = {"root", "data"})})
public class UpdateDataAction {

    private Object data;

    @Autowired
    JdbcTemplate jdbcTemplate;

    // 更新  ums_user_info 表中 idcard 、phone_number 字段
    @Action("updateUms")
    public Object updateUms() {
        try {
            String tableName = "ums_user_info";
            String sql = "select id,idcard,fullname,phone_number from  " + tableName + " where is_valid = 1 and user_type = 1 ";
            List<UmsUserInfo> querys = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(UmsUserInfo.class));
            String updateSql = " update " + tableName + " set  idcard =  ? ,phone_number = ? where id = ?";
            List<Object[]>  update = new ArrayList<>();
            int i = 0 ;
            int all = 0;
            int updateCount = 0;
            for (UmsUserInfo query : querys) {
                EncodeDecodeDataInfo.enCodeDataForUms(query);
                update.add(new Object[]{ query.getIdcard(),query.getPhoneNumber(),query.getId() });
                if(i == 10 || all == (querys.size() - 1)){
                    i = 0;
                    //把之前的更新了
                    int[] ints = jdbcTemplate.batchUpdate(updateSql, update);
                    updateCount += ints.length;
                    update = new ArrayList<>();
                }
                i++;
                all++;
            }
            System.out.println(updateCount);
            System.out.println("out");


        } catch (Exception e) {
            e.printStackTrace();
        }
        return "json";
    }

    @Action("updateRmppsy")
    public Object updateRmppsy() {
        try {
            String tableName = "ums_rmpsy_jbxx";
            String sql = "select id,idcard,name,idcard,phonenum from  " + tableName + " where isvalid = 1 and courtNo = 2974  ";
            List<UmsRmpsyJbxx> querys = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(UmsRmpsyJbxx.class));
            String updateSql = " update " + tableName + " set  idcard =  ? ,phonenum = ? where id = ?";
            List<Object[]>  update = new ArrayList<>();
            int i = 0 ;
            int all = 0;
            int updateCount = 0;
            for (UmsRmpsyJbxx query : querys) {
                EncodeDecodeDataInfo.enCodeDataForUmsRmpsy(query);
                update.add(new Object[]{ query.getIdcard(),query.getPhonenum(),query.getId() });
                if(i == 10 || all == (querys.size() - 1)){
                    i = 0;
                    //把之前的更新了
                    int[] ints = jdbcTemplate.batchUpdate(updateSql, update);
                    updateCount += ints.length;
                    update = new ArrayList<>();
                }
                i++;
                all++;
            }
            System.out.println(updateCount);
            System.out.println("out");


        } catch (Exception e) {
            e.printStackTrace();
        }
        return "json";
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
