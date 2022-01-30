package cn.cpf.web.boot.controller.testctrl;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <b>Description : </b> 用于测试的 Controller, 其中的方法与业务无关
 * <p>
 * <b>created in </b> 2019/8/15 10:07
 * </p>
 *
 * @author CPF
 **/
@Log4j2
@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping({"/", "/index"})
    public String index() {
        return "/static/page/base/index.html";
    }

    /**
     * 测试 仅仅将 shortName 简单处理后返回
     */
    @RequestMapping("/navigationStatic/{shortName}")
    public String navigationStatic(HttpServletRequest request, HttpSession session, @PathVariable String shortName) {
        return "/" + shortName.replace("-", "/");
    }

    @RequestMapping("/redirect/{shortName}")
    public String redirect(HttpServletRequest request, HttpSession session, @PathVariable String shortName) {
        return "redirect:/" + shortName.replace("-", "/");
    }

}
