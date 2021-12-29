package cn.cpf.web.boot.controller;

import cn.cpf.web.boot.constant.PageTree;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * <b>Description : </b> 根 Controller
 * <p>
 * <b>created in </b> 2021/12/27
 * </p>
 *
 * @author CPF
 * @since 1.0
 **/
@Log4j2
@Controller
public class BootController {

    @PostConstruct
    public void start() throws IOException {
    }

    @GetMapping({"/", "/index"})
    public String index() {
        return PageTree.Base.INDEX;
    }

}
