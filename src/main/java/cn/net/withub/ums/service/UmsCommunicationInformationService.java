package cn.net.withub.ums.service;

import cn.net.withub.ums.entity.UmsCommunicationInformation;
import cn.net.withub.ums.entity.UmsCommunicationInformationCriteria;

import java.util.List;

public interface UmsCommunicationInformationService   {

    int insert(UmsCommunicationInformation record);

    int insertSelective(UmsCommunicationInformation record);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UmsCommunicationInformation record);

    List<UmsCommunicationInformation> selectByExample(UmsCommunicationInformationCriteria example);

    UmsCommunicationInformation selectByPrimaryKey(String id);

    int countByExample(UmsCommunicationInformationCriteria example);

}
