package cn.cpf.web.boot.conf.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/8/15 10:30
 **/
public class MyFilter implements Filter{

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
        System.out.println("过滤器的销毁");
    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain filterChain)
            throws IOException, ServletException {
        // TODO Auto-generated method stub
        HttpServletRequest request = (HttpServletRequest) arg0;
        HttpServletResponse response = (HttpServletResponse)arg1;
        System.out.println("this is MyFilter,url :"+request.getRequestURI());
        filterChain.doFilter(request, response);


    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub
        System.out.println("过滤器的初始化");
    }

}