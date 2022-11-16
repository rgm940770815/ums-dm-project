/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.dao.extend.provider;

import org.apache.ibatis.jdbc.SQL;

/**
 *
 * @author Diluka
 */
public class CourtExtendProvider {

    public String areaCourtNoList(final Integer areaNo) {
        return new SQL() {
            {
                SELECT("court_no");
                FROM("ums_court_full");

                if (areaNo != null) {
                    int code1 = areaNo / 1000;
                    int code2 = (areaNo / 100) % 10;

                    switch (code1) {
                        case 7: //地方
                            switch (code2) {
                                case 0: //所有地方
                                    WHERE("court_level is not null");
                                    break;
                                default: //X类地方
                                    WHERE("court_level = " + code2);
                                    break;
                            }
                            break;
                        case 8: //中院
                            switch (code2) {
                                case 0: //所有中院
                                    WHERE("court_gradation = 1");
                                    break;
                                default: //X中院辖区
                                    WHERE("court_area = " + code2);
                                    break;
                            }
                            break;
                        case 9:
                            if (areaNo == 9999) { //全市法院
                                WHERE("court_no is not null");
                            }
                            break;
                        default:
                            WHERE("1=0");
                    }
                } else {
                    WHERE("1=0");
                }

            }
        }.toString();
    }

}
