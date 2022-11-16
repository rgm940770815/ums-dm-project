/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.common;

/**
 * 常量
 *
 * @author Diluka
 */
public enum UmsConstant {

    LOGIN_USER_SESSION_KEY("loginUser"),
    CURRENT_USER_AUTH("currUserAuth"),
    TEMP_PHOTO("tempPhoto"),
    TEMP_PHOTO_NAME("tempPhotoName"),
    SELF("self"),
    ADMIN("admin"),
    COURT_CODE("M00");

    private final String s;

    UmsConstant(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return s;
    }

}
