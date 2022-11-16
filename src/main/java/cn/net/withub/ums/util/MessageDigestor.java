/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.util;

import java.security.MessageDigest;

/**
 * 消息摘要计算器
 *
 * @author Diluka
 * @version 1.1
 */
public class MessageDigestor {

    /**
     * 十六进制数字字符
     */
    private static final char[] hexChars = {'0', '1', '2', '3', '4', '5', '6',
        '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 计算摘要
     *
     * @param algorithm 算法
     * @param data 数据
     * @return 摘要字节数组
     */
    private static byte[] digest(String algorithm, String data, String mask) {
        byte[] bytes = null;
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            bytes = md.digest((data + mask).getBytes());
            md.reset();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }

    /**
     * 字节数组转字符串
     *
     * @param bytes 字节数组
     * @return 字符串
     */
    private static String bytesToString(byte[] bytes) {
        // return Utility.toHexString(bytes).replaceAll(" ", "");
        char[] str = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; i++) {
            str[i * 2] = hexChars[bytes[i] >> 4 & 0xf];
            str[i * 2 + 1] = hexChars[bytes[i] & 0xf];
        }
        return new String(str);
    }

    /**
     * 计算SHA1
     *
     * @param s 数据
     * @param mask 掩码
     * @return 结果
     */
    public static String getSHA1String(String s, String mask) {
        return bytesToString(digest("SHA1", s, mask));
    }

    /**
     * 计算MD5
     *
     * @param s 数据
     * @param mask 掩码
     * @return 结果
     */
    public static String getMD5String(String s, String mask) {
        return bytesToString(digest("MD5", s, mask));
    }

    /**
     * 计算MD5
     *
     * @param s 数据
     * @return
     */
    public static String getMD5String(String s) {
        return bytesToString(digest("MD5", s, ""));
    }

}
