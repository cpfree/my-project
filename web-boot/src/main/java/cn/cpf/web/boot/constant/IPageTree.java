package cn.cpf.web.boot.constant;

/**
 * <b>Description : </b> 存放一些项目文件结构相关常量
 *
 * @author CPF
 * @date 2019/10/31 14:25
 **/
public interface IPageTree {

    /**
     * 基础页面
     */
    interface Base {

        /**
         * 首页
         */
        String INDEX = "/static/page/base/index.html";

        /**
         * 注册页面
         **/
        String REGISTER = "/static/page/base/register.html";

        /**
         * 登录页面
         **/
        String LOGIN = "/static/page/base/login.html";

    }


}
