package cn.cpf.web.boot.util;

import cn.cpf.web.base.lang.base.IPostCode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.servlet.ServletRequest;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * Date: 2020/6/17 13:39
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CpRequestUtils {

    public static final String POST_CODE = "postcode";
    public static final String NEED_CAPTCHA = "needCaptcha";

    public static void setNeedCaptcha(ServletRequest request, boolean needCaptcha) {
        request.setAttribute(NEED_CAPTCHA, needCaptcha);
    }

    public static Boolean isNeedCaptcha(ServletRequest request) {
        return (Boolean) request.getAttribute(NEED_CAPTCHA);
    }

    public static IPostCode getPostCode(ServletRequest request) {
        return (IPostCode) request.getAttribute(POST_CODE);
    }

    public static void setPostCode(ServletRequest request, IPostCode postCode) {
        request.setAttribute(POST_CODE, postCode);
    }
}
