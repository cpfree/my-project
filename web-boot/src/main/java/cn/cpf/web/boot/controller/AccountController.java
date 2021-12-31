package cn.cpf.web.boot.controller;

import cn.cpf.web.service.base.api.IAccUser;
import cn.cpf.web.service.mod.sms.ISms;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/11/27 16:38
 **/
@Controller
@Log4j2
@RequestMapping("/account")
public class AccountController {

    private final IAccUser iAccUser;
    private final ISms iSms;

    @Autowired
    public AccountController(IAccUser iAccUser, ISms iSms) {
        this.iAccUser = iAccUser;
        this.iSms = iSms;
    }




//    /**
//     * 更新密码
//     */
//    @PostMapping("/resetPassword")
//    @ResponseBody
//    public Map<String, Object> resetPassword(HttpServletRequest request, @RequestParam("phone") String phone, @RequestParam("pwd1") String pwd1, @RequestParam("pwd2") String pwd2, @RequestParam("captcha") String captcha, @RequestParam("verifyCode") String verifyCode) {
//        // check
//        IPostCode postCode = doRegisterBefore(request, phone, pwd1, pwd2, RESET_PWD_SMS_CODE, captcha, verifyCode);
//        if (postCode.isNotSuccess()) {
//            return PostBean.genePostMap(postCode);
//        }
//        // 更改密码
//        // 检查手机号码是否已经存在
//        AccUser user = iAccUser.findByPhone(phone);
//        if (user == null) {
//            return PostBean.genePostMap(ELoginPostCode.ACCOUNT_IS_EXIST);
//        }
//        user.setPassword(pwd1);
//        iAccUser.updateByPrimaryKey(user);
//        return PostBean.genePostMap(ECommonPostCode.SUCCESS);
//    }




}
