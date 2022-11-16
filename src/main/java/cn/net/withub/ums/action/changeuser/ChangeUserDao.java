package cn.net.withub.ums.action.changeuser;

import java.util.Map;

public interface ChangeUserDao {

    Map<String, Object> setUser(String uuid,
                                Integer courtNo, Integer department, Integer administrationPosition, Integer lawPosition,
                                Integer otherCourtNo, Integer otherDepartment, Integer otherAdministrationPosition, Integer otherLawPosition) throws Exception;

}
