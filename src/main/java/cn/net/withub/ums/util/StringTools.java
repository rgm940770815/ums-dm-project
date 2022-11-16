/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.util;

/**
 *
 * @author Diluka
 */
public class StringTools {

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }

    /**
     * 驼峰法和帕斯卡法转换成数据库的下划线表示
     *
     * @param s
     * @return
     */
    public static String camelOrPascalToUnderline(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isUpperCase(c)) {
                sb.append(("_" + c).toLowerCase());
            } else {
                sb.append(c);
            }
        }
        if (sb.charAt(0) == '_') {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }

    /**
     * 下划线转驼峰
     *
     * @param s
     * @return
     */
    public static String underlineToCamel(String s) {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        while (index < s.length()) {
            if (s.charAt(index) == '_') {
                sb.append(Character.toUpperCase(s.charAt(index + 1)));
                index += 2;
            } else {
                sb.append(Character.toLowerCase(s.charAt(index)));
                index++;
            }
        }
        return sb.toString();
    }

    /**
     * 下划线转驼峰（首字母大写）
     *
     * @param s
     * @return
     */
    public static String underlineToCamel2(String s) {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        while (index < s.length()) {
            if (s.charAt(index) == '_') {
                sb.append(Character.toUpperCase(s.charAt(index + 1)));
                index += 2;
            } else if (index == 0) {
                sb.append(Character.toUpperCase(s.charAt(index)));
                index++;
            } else {
                sb.append(Character.toLowerCase(s.charAt(index)));
                index++;
            }
        }
        return sb.toString();
    }

    public static String underlineToPascal(String s) {
        return org.springframework.util.StringUtils.capitalize(underlineToCamel(s));
    }
}
