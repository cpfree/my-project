package cn.cpf.web.boot.conf.filter;

import cn.cpf.web.base.constant.postcode.ECommonPostCode;
import cn.cpf.web.base.lang.base.PostBean;
import cn.cpf.web.base.util.exception.PostException;
import cn.cpf.web.base.util.exception.SystemConfigException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * <b>Description : </b> 统一处理全局异常
 * <p>
 * <b>created in </b> 2022/1/1
 * </p>
 *
 * @author CPF
 * @since 1.0
 **/
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map<String, Object> handleException(HttpServletRequest request, HttpSession session, Exception ex){
        log.error("发生了系统异常", ex);
        PostBean postBean = new PostBean(ECommonPostCode.INTERNAL_ERROR);
        postBean.put("exceptionType", ex.getClass().getSimpleName());
        postBean.put("exception", ex.toString());
        return postBean.toResultMap();
    }


    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.OK)//使http返回的状态码是200
    @ResponseBody
    public Map<String, Object> handleRuntimeException(HttpServletRequest request, HttpSession session, Exception ex){
        log.error("发生了RuntimeException异常", ex);
        PostBean postBean = new PostBean(ECommonPostCode.INTERNAL_RUNTIME_ERROR);
        postBean.put("exceptionType", ex.getClass().getSimpleName());
        postBean.put("exception", ex.toString());
        return postBean.toResultMap();
    }

    @ExceptionHandler(PostException.class)
    @ResponseStatus(HttpStatus.OK)//使http返回的状态码是200
    @ResponseBody
    public Map<String, Object> handlePostException(HttpServletRequest request, HttpSession session, PostException ex){
        log.error("发生了PostException异常", ex);
        PostBean postBean = new PostBean(ex.getPostCode());
        postBean.put("exceptionType", ex.getClass().getSimpleName());
        postBean.put("exception", ex.toString());
        return postBean.toResultMap();
    }

    @ExceptionHandler(SystemConfigException.class)
    @ResponseStatus(HttpStatus.OK)//使http返回的状态码是200
    @ResponseBody
    public Map<String, Object> handleSystemConfigException(HttpServletRequest request, HttpSession session, Exception ex) {
        log.error("发生了SystemConfigException异常", ex);
        PostBean postBean = new PostBean(ECommonPostCode.SYSTEM_CONFIG_EXCEPTION);
        postBean.put("exceptionType", ex.getClass().getSimpleName());
        postBean.put("exception", ex.toString());
        return postBean.toResultMap();
    }


    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(HttpStatus.OK)//使http返回的状态码是200
    @ResponseBody
    public Map<String, Object> handleAuthorizationException(HttpServletRequest request, HttpSession session, AuthorizationException ex) throws AuthorizationException {
        log.error("没有权限, 发生权限异常");
        throw ex;
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.OK)//使http返回的状态码是200
    @ResponseBody
    public Map<String, Object> handleAuthenticationException(HttpServletRequest request, HttpSession session, AuthorizationException ex) throws AuthorizationException {
        log.error("没有权限, 发生权限异常");
        throw ex;
    }

}
