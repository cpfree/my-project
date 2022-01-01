package cn.cpf.web.boot.util;

import cn.cpf.web.base.constant.postcode.ECommonPostCode;
import cn.cpf.web.base.constant.postcode.ELoginPostCode;
import cn.cpf.web.base.lang.base.IPostCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/6/13 11:53
 **/
@Slf4j
public class CaptchaUtils {

    private CaptchaUtils() {
    }

    /**
     * 检查 Kaptcha 验证码
     * @param request   用于获取session中保存的验证码
     * @param checkCode 需要比对的验证码
     * @param messageTag 用于显示日志信息
     * @return 检查结果 {@link ELoginPostCode#VERIFICATION_CODE_INVALIDATION},
     *                  {@link ELoginPostCode#VERIFICATION_CODE_NOT_FOUND}
     *                  {@link ELoginPostCode#VERIFICATION_CODE_NOT_MATCH}
     *                  {@link ECommonPostCode#NO_EXCEPTION}
     */
    public static IPostCode checkKaptchaCode(HttpServletRequest request, String checkCode, Object messageTag) {
        if (StringUtils.isBlank(checkCode)) {
            if (messageTag != null) {
                log.warn("[kaptcha] code is null. => {}", messageTag);
            }
            return ELoginPostCode.VERIFICATION_CODE_NOT_FOUND;
        }
        // 获取生成的验证码
        String kaptchaValue = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        if (StringUtils.isBlank(kaptchaValue)) {
            if (messageTag != null) {
                log.warn("[kaptcha] code is invalid. => {}", messageTag);
            }
            return ELoginPostCode.VERIFICATION_CODE_INVALIDATION;
        } else if (!kaptchaValue.equalsIgnoreCase(checkCode)) {
            if (messageTag != null) {
                log.warn("[kaptcha] code doesn't match. => {}", messageTag);
            }
            return ELoginPostCode.VERIFICATION_CODE_NOT_MATCH;
        }
        return ECommonPostCode.NO_EXCEPTION;
    }

    /**
     * 检查 Kaptcha 验证码
     * @param request   用于获取session中保存的验证码
     * @param messageTag 用于显示日志信息
     * @return 检查结果 {@link ELoginPostCode#VERIFICATION_CODE_INVALIDATION},
     *                  {@link ELoginPostCode#VERIFICATION_CODE_NOT_FOUND}
     *                  {@link ELoginPostCode#VERIFICATION_CODE_NOT_MATCH}
     *                  {@link ECommonPostCode#NO_EXCEPTION}
     */
    public static IPostCode checkKaptchaCode(HttpServletRequest request, Object messageTag) {
        final String captcha = WebUtils.getCleanParam(request, "captcha");
        return checkKaptchaCode(request, captcha, messageTag);
    }

}
