package cn.cpf.web.boot.testctrl;

import cn.cpf.web.boot.conf.RocketConfig;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    private RocketConfig rocketConfig;

    @PostConstruct
    public void start() {
        log.warn(rocketConfig);
    }

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
