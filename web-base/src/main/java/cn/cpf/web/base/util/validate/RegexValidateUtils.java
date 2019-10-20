package cn.cpf.web.base.util.validate;

import java.util.regex.Pattern;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/8/2 15:39
 **/
public interface RegexValidateUtils {

    /**
     * yyyy-MM-dd
     */
    String REGEX_DATE = "\\d{4}-(0?[1-9]|1[0-2])-((0?[1-9])|((1|2)[0-9])|30|31)";

    /**
     * hh:mm:ss
     */
    String REGEX_TIME = "((0?[1-9])|(1[0-9])|(2[0-3]))(:((0?[1-9])|([0-5][0-9]))){2}";

    /**
     * yyyy-MM-dd hh:mm:ss
     */
    String REGEX_DATE_TIME = REGEX_DATE + " " + REGEX_TIME;

    String REGEX_EMAIL = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";

    String REGEX_PHONE = "^1([345789])\\d{9}$";

    static boolean isEamil(String email){
        return Pattern.matches(REGEX_EMAIL, email);
    }

    static boolean isPhone(String phone) {
        return Pattern.matches(REGEX_PHONE, phone);
    }

    static boolean isDigit(String string) {
        for (int i = 0, len = string.length(); i < len; i++) {
            if (!Character.isDigit(string.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}
