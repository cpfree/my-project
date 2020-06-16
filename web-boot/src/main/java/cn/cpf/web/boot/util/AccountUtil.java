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
     * @param password 密码加密
     * @return 加密后的密文
     */
    public static String passwordEncode(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }

}
