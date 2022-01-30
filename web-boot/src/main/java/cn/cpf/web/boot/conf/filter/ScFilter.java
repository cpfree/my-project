//package cn.cpf.web.boot.conf.filter;
//
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
///**
// * <b>Description : </b>
// *
// * @author CPF
// * @date 2019/8/15 10:30
// **/
//@Slf4j
//@Component
//public class ScFilter extends OncePerRequestFilter {
//
//    @Override
//    public void destroy() {
//        log.debug("过滤器的销毁");
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        log.debug("[ScFilter] => [{}] : url : {}", request.getMethod(), request.getRequestURI());
//    }
//
//}