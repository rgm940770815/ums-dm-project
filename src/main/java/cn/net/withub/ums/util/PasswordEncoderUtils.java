package cn.net.withub.ums.util;

/**
 * Created by luoye on 2015/11/19.
 */
public class PasswordEncoderUtils {
    static boolean equals(String expected, String actual) {
        byte[] expectedBytes = bytesUtf8(expected);
        byte[] actualBytes = bytesUtf8(actual);
        int expectedLength = expectedBytes == null?-1:expectedBytes.length;
        int actualLength = actualBytes == null?-1:actualBytes.length;
        if(expectedLength != actualLength) {
            return false;
        } else {
            int result = 0;

            for(int i = 0; i < expectedLength; ++i) {
                result |= expectedBytes[i] ^ actualBytes[i];
            }

            return result == 0;
        }
    }

    private static byte[] bytesUtf8(String s) {
        return s == null?null:Utf8.encode(s);
    }

    private PasswordEncoderUtils() {
    }
}
