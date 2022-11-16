package cn.net.withub.ums.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by luoye on 2015/11/19.
 */
public class Md5PasswordEncoder {
    private final String algorithm;
    private int iterations;

    public Md5PasswordEncoder() {
        this.algorithm = "MD5";
        this.iterations = 1;
        this.setEncodeHashAsBase64(false);
        this.getMessageDigest();
    }


    private boolean encodeHashAsBase64 = false;

    public boolean getEncodeHashAsBase64() {
        return this.encodeHashAsBase64;
    }

    public void setEncodeHashAsBase64(boolean encodeHashAsBase64) {
        this.encodeHashAsBase64 = encodeHashAsBase64;
    }

    public String encodePassword(String rawPass, Object salt) {
        String saltedPass = this.mergePasswordAndSalt(rawPass, salt, false);
        MessageDigest messageDigest = this.getMessageDigest();
        byte[] digest = messageDigest.digest(Utf8.encode(saltedPass));

        for(int i = 1; i < this.iterations; ++i) {
            digest = messageDigest.digest(digest);
        }

        return this.getEncodeHashAsBase64()?Utf8.decode(Base64.encode(digest)):new String(Hex.encode(digest));
    }

    protected String mergePasswordAndSalt(String password, Object salt, boolean strict) {
        if(password == null) {
            password = "";
        }

        if(strict && salt != null && (salt.toString().lastIndexOf("{") != -1 || salt.toString().lastIndexOf("}") != -1)) {
            throw new IllegalArgumentException("Cannot use { or } in salt.toString()");
        } else {
            return salt != null && !"".equals(salt)?password + "{" + salt.toString() + "}":password;
        }
    }

    protected final MessageDigest getMessageDigest() throws IllegalArgumentException {
        try {
            return MessageDigest.getInstance(this.algorithm);
        } catch (NoSuchAlgorithmException var2) {
            throw new IllegalArgumentException("No such algorithm [" + this.algorithm + "]");
        }
    }

    public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
        String pass1 = "" + encPass;
        String pass2 = this.encodePassword(rawPass, salt);
        return PasswordEncoderUtils.equals(pass1, pass2);
    }
}
