package cn.cpf.web.boot.controller;

import cn.cpf.web.base.lang.base.PostBean;
import cn.cpf.web.boot.constant.PageTree;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * <b>Description : </b>
 * <p>
 * <b>created in </b> 2022/1/1
 * </p>
 *
 * @author CPF
 * @since 1.0
 **/
@RequestMapping("/authc")
@Controller
public class AuthcController {

    /**
     * @param type 字典类型
     */
    @GetMapping("/test/{type}")
    @ResponseBody
    public Map<String, Object> querySysDictItemByDictType(@PathVariable("type") String type) {
        PostBean p = new PostBean();
        p.put("key", type);
        return p.toResultMap();
    }

    /**
     * @param type 字典类型
     */
    @GetMapping("/test2/{type}")
    public String test(@PathVariable("type") String type) {
        return PageTree.Base.P503;
    }
}
