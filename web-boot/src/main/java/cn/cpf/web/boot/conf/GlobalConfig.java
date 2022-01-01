package cn.cpf.web.boot.conf;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/10/31 16:58
 **/
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GlobalConfig {

    /**
     * 登录错误几次需要填写验证码
     */
    private static final int MAX_LOGIN_WITH_NO_CAPTCHA_CODE = 3;
    /**
     * 登录错误几次账户会被锁定
     */
    private static final int TIME_OF_LOCK_WHEN_LOGIN_FAILURE = 10;

    /**
     * @return 登录错误几次需要填写验证码
     */
    public static int getMaxLoginWithNoCaptchaCode() {
        return MAX_LOGIN_WITH_NO_CAPTCHA_CODE;
    }

    /**
     * @return 登录错误几次需要填写验证码
     */
    public static int getTimeOfLockWhenLoginFailure() {
        return TIME_OF_LOCK_WHEN_LOGIN_FAILURE;
    }


}
