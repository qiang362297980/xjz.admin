package com.tool.basic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by XCA on 2017/5/23.
 */
public class MD5Servlet {

    public static String md5(String password) {
        MessageDigest md;
        String md5pwd = "";
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] pwdByte = password.getBytes();
            md.update(pwdByte);
            md5pwd = bytes2Hex(md.digest(pwdByte));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5pwd;
    }
    

    /**
     * 将字节数组转成 16 进制的字符串来表示，每个字节采用两个字符表表示
     *
     * @param \bs 需要转换成 16 进制的字节数组
     * @return
     */
    private final static char[] HEX = "0123456789abcdef".toCharArray();
    public static String bytes2Hex(byte[] bys) {
        char[] chs = new char[bys.length * 2];
        for(int i = 0, offset = 0; i < bys.length; i++) {
            chs[offset++] = HEX[bys[i] >> 4 & 0xf];
            chs[offset++] = HEX[bys[i] & 0xf];
        }
        return new String(chs);
    }

    public static void main(String[] args){
        String a = "xjzadmin@2017";
        String pwd = md5(a);
        System.out.print(pwd);
    }
}
