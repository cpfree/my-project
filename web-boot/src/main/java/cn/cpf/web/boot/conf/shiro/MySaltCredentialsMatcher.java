package cn.cpf.web.boot.conf.shiro;

import cn.cpf.web.base.model.entity.AccUser;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.springframework.util.DigestUtils;

/**
 * <b>Description : </b>
 * <p>
 * <b>created in </b> 2021/12/31
 * </p>
 *
 * @author CPF
 * @since 1.0
 **/
public class MySaltCredentialsMatcher implements CredentialsMatcher {

    /**
     * @param token 前台传来的用户密码信息
     * @param info 从数据库取出来的用户信息
     * @return 用户密码是否匹配
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        final AccUser user = (AccUser)info.getPrincipals().getPrimaryPrincipal();
        final char[] password = (char[]) token.getCredentials();
        final String passwordEncode = passwordEncode(new String(password), user.getSalt());
        return passwordEncode.equals(info.getCredentials());
    }

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
        final int passwordLength = passwordBytes.length;
        byte[] bytes = new byte[passwordLength + saltBytes.length];
        System.arraycopy(passwordBytes, 0, bytes, 0, passwordLength);
        System.arraycopy(saltBytes, 0, bytes, passwordLength, saltBytes.length);
        return DigestUtils.md5DigestAsHex(bytes);
    }

}
