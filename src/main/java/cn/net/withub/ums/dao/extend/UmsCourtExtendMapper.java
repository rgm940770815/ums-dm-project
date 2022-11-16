/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.dao.extend;

import cn.net.withub.ums.dao.UmsCourtMapper;
import cn.net.withub.ums.dao.extend.provider.CourtExtendProvider;
import cn.net.withub.ums.entity.UmsCourtFull;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

/**
 * @author Diluka
 */
public interface UmsCourtExtendMapper extends UmsCourtMapper {

    @Select("select court_std_no from ums_court where court_no=#{courtNo}")
    Integer courtNo2CourtStdNo(Integer courtNo);

    @Select("select court_code from ums_court where court_no=#{courtNo}")
    String courtNo2CourtCode(Integer courtNo);

    @Select("select court_std_name from ums_court where court_std_no=#{courtStdNo}")
    String courtTexForCourtStdNo(Integer courtStdNo);

    @Select("select court_no from ums_court where court_std_no=#{courtStdNo}")
    Integer courtNoForCourtStdNo(Integer courtStdNo);

    @Select("call childrenCourtNo(#{courtNo})")
    @Options(statementType = StatementType.CALLABLE)
    List<Integer> childrenCourtNoList(Integer courtNo);

    @Select("select * from ums_court_full where court_area_no = #{areaNo}")
    @ResultMap("cn.net.withub.ums.dao.UmsCourtFullMapper.BaseResultMap")
    List<UmsCourtFull> areaCourts(Integer areaNo);

    @SelectProvider(type = CourtExtendProvider.class, method = "areaCourtNoList")
    List<Integer> areaCourtNoList(Integer areaNo);

    @Select("select court_std_name from ums_court  where court_code = #{courtCode}")
    String courtTexForCourtCode(String courtCode);
}
