package cn.cpf.web.boot.generate;

import cn.cpf.web.boot.util.MyClassLoader;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/12/6 16:01
 **/
public class JsServiceGenerator {

    public static final String CONTROLLER_PATH = "P:/git/my-project/web-boot/target/classes/";


    public static void main(String[] args) {
        MyClassLoader classLoader = new MyClassLoader(CONTROLLER_PATH);
        final Class<?> aClass = classLoader.getClass("cn.cpf.web.boot.controller.AccountController");
        geneJsServiceCode(aClass);
        System.out.println(aClass);
    }

    @ToString
    static class JsServiceBean {
        @Setter
        private String url;
        private Set<RequestMethod> methodSet;

        public void addMethodType(RequestMethod method) {
            if (method == null) {
                methodSet = new HashSet<>(1);
            }
            methodSet.add(method);
        }
    }


    private static void geneJsServiceCode(Class<?> aClass) {
        if (!aClass.isAnnotationPresent(Controller.class)) {
            return;
        }
        // 类映射路径
        final String classMapping;
        // TO DO 考虑GetMapping PostMapping, 以及多个路径情况
        if (aClass.isAnnotationPresent(RequestMapping.class)) {
            final RequestMapping annotation = aClass.getAnnotation(RequestMapping.class);
            classMapping = annotation.value()[0];
        } else {
            classMapping = "";
        }

        final Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            JsServiceBean serviceBean = new JsServiceBean();
            final Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
            final Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            for (Annotation methodAtn : declaredAnnotations) {
                if (RequestMapping.class.equals(methodAtn.annotationType())) {
                    final String[] value = ((RequestMapping) declaredAnnotations[0]).value();
                    serviceBean.setUrl(classMapping + value[0]);
                }
                if (PostMapping.class.equals(methodAtn.annotationType())) {
                    final String[] value = ((PostMapping) declaredAnnotations[0]).value();
                    serviceBean.setUrl(classMapping + value[0]);
                    serviceBean.addMethodType(RequestMethod.POST);
                }
                if (DeleteMapping.class.equals(methodAtn.annotationType())) {
                    final String[] value = ((DeleteMapping) declaredAnnotations[0]).value();
                    serviceBean.setUrl(classMapping + value[0]);
                    serviceBean.addMethodType(RequestMethod.DELETE);
                }
                if (PutMapping.class.equals(methodAtn.annotationType())) {
                    final String[] value = ((PutMapping) declaredAnnotations[0]).value();
                    serviceBean.setUrl(classMapping + value[0]);
                    serviceBean.addMethodType(RequestMethod.PUT);
                }
                if (GetMapping.class.equals(methodAtn.annotationType())) {
                    final String[] value = ((GetMapping) declaredAnnotations[0]).value();
                    serviceBean.setUrl(classMapping + value[0]);
                    serviceBean.addMethodType(RequestMethod.GET);
                }
            }

        }


    }


}
