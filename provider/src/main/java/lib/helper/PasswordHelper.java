package lib.helper;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class PasswordHelper {
    public static String getSalt() {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        String salt = "";
        for(int i=0; i<5; i++) {
            salt += base.charAt(random.nextInt(36));
        }
        return salt;
    }

    public static String generatePassword(String inputPassword, String salt){
        return getMd5(inputPassword + salt);
    }

    public static String getMd5(String str) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            result = new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }
}
