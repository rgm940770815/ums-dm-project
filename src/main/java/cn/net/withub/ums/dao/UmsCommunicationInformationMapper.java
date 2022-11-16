package cn.net.withub.ums.dao;

import cn.net.withub.ums.entity.UmsCommunicationInformation;
import cn.net.withub.ums.entity.UmsCommunicationInformationCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsCommunicationInformationMapper {
    int countByExample(UmsCommunicationInformationCriteria example);

    int deleteByExample(UmsCommunicationInformationCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(UmsCommunicationInformation record);

    int insertSelective(UmsCommunicationInformation record);

    List<UmsCommunicationInformation> selectByExample(UmsCommunicationInformationCriteria example);

    UmsCommunicationInformation selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UmsCommunicationInformation record, @Param("example") UmsCommunicationInformationCriteria example);

    int updateByExample(@Param("record") UmsCommunicationInformation record, @Param("example") UmsCommunicationInformationCriteria example);

    int updateByPrimaryKeySelective(UmsCommunicationInformation record);

    int updateByPrimaryKey(UmsCommunicationInformation record);
}