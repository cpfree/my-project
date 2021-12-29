package cn.cpf.web.boot.constant;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <b>Description : </b> 存放一些项目文件结构相关常量
 *
 * @author CPF
 * @date 2019/10/31 14:25
 **/
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PageTree {

    /**
     * 基础页面
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Base {

        /**
         * 首页
         */
        @Getter
        public static final String INDEX = "/static/page/base/index.html";

        /**
         * 注册页面
         **/
        public static final String REGISTER = "/static/page/base/register.html";

        /**
         * 登录页面
         **/
        public static final String LOGIN = "/static/page/base/login.html";

    }

}
