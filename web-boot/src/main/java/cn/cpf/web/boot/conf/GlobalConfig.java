package cn.cpf.web.boot.conf;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/10/31 16:58
 **/
public class GlobalConfig {

    /**
     * 登录错误几次需要填写验证码
     */
    private static final int MAX_LOGIN_WITH_NO_CAPTCHA_CODE = 3;

    /**
     * @return 登录错误几次需要填写验证码
     */
    public static int getMaxLoginWithNoCaptchaCode() {
        return MAX_LOGIN_WITH_NO_CAPTCHA_CODE;
    }

}
