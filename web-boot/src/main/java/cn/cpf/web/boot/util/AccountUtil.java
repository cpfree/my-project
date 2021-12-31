package cn.cpf.web.boot.util;

import org.springframework.util.DigestUtils;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * Date: 2020/6/15 16:43
 */
public class AccountUtil {

    /**
     * 直接使用密码加密不安全, 在此使用 加盐加密
     *
     * @param password 密码加密
     * @param salt     盐
     * @return 加密后的密文
     */
    public static String passwordEncode(String password, String salt) {
        final byte[] passwordBytes = password.getBytes();
        final byte[] saltBytes = salt.getBytes();
        final int len = passwordBytes.length + saltBytes.length;
        byte[] bytes = new byte[len];
        System.arraycopy(passwordBytes, 0, bytes, 0, passwordBytes.length);
        System.arraycopy(passwordBytes, 0, bytes, passwordBytes.length, len);
        return DigestUtils.md5DigestAsHex(bytes);
    }



}
