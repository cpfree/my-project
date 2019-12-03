package cn.cpf.web.boot.util;

import cn.cpf.web.base.constant.postcode.ECommonPostCode;
import cn.cpf.web.base.constant.postcode.ELoginPostCode;
import cn.cpf.web.base.lang.base.IPostCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/6/13 11:53
 **/
@Slf4j
public class PluginUtils {

    /**
     * 检查 Kaptcha 验证码
     */
    public static IPostCode checkKaptchaCode(HttpServletRequest request, String checkCode, String user) {
        String kaptchaValue = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        if (StringUtils.isBlank(kaptchaValue)) {
            log.warn("User {} kaptcha code is invalid.", user);
            return ELoginPostCode.VERIFICATION_CODE_INVALIDATION;
        } else if (StringUtils.isBlank(checkCode)) {
            log.warn("User {} code is null.", user);
            return ELoginPostCode.VERIFICATION_CODE_NOT_FOUND;
        } else if (!kaptchaValue.equalsIgnoreCase(checkCode)) {
            log.warn("User {} code doesn't match.", user);
            return ELoginPostCode.VERIFICATION_CODE_ERROR;
        }
        return ECommonPostCode.NO_EXCEPTION;
    }

}
