package cn.cpf.web.boot.conf.filter;

import cn.cpf.web.base.util.cast.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * <b>Description : </b> 打印参数 和 返回值的 AOP 切面
 * <p>
 * <b>created in </b> 2021/12/29
 * </p>
 *
 * @author CPF
 * @since 1.0
 **/
@Slf4j
@Component
@Aspect
public class LogAspectConfiguration {

    /**
     * execution：改成自己要打印的控制器路径
     *
     * @param proceedingJoinPoint 切点
     * @return
     * @throws Throwable
     */
    @Around("execution(* cn.cpf.web.boot.controller.*.*(..)) ")
    public Object handleControllerMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        final boolean debugEnabled = log.isDebugEnabled();
        if (!debugEnabled) {
            return proceedingJoinPoint.proceed();
        }

        //原始的HTTP请求和响应的信息
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null : "attributes is null";
        HttpServletRequest request = attributes.getRequest();

        Signature signature = proceedingJoinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        //获取当前执行的方法
        Method targetMethod = methodSignature.getMethod();
        //获取参数
        Object[] objects = proceedingJoinPoint.getArgs();
        //获取参数
        Object[] arguments = new Object[objects.length];
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] instanceof ServletRequest || objects[i] instanceof ServletResponse || objects[i] instanceof MultipartFile) {
                //过滤掉不能序列化的参数
                continue;
            }
            arguments[i] = objects[i];
        }

        final String name = Thread.currentThread().getName();
        log.debug("\n [{} call start] ==> {} : {}" +
                        "\n[DEBUG] Controller: {} ==> {}" +
                        "\n[DEBUG] Params    : {}",
                name, request.getRequestURL(), targetMethod.getDeclaringClass().getName()
                , targetMethod.getName(), request.getMethod(), JsonUtils.toJson(arguments));
        //获取返回对象
        Object object = proceedingJoinPoint.proceed();
        log.debug("\n [{} call end] ==> {}", name, object);
        return object;
    }

}
