package cn.cpf.web.base.util.common;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Random;


public class ControllerUtils {

    /**
     * 拼接的list 返回json格式
     *
     * @param name 循环list名
     * @param list list return jsonlist组合
     */
    @SuppressWarnings("rawtypes")
    public static String getJSONStrList(String name, List list) {
        String jsonStr = "{\"" + name + "\"" + ":" + list + "}";
        return jsonStr;
    }

    /**
     * 拼接的list 返回json格式
     *
     * @param name 循环list名
     */
    public static String getJSONStrString(String name, String str) {
        String jsonStr = "{\"" + name + "\"" + ":\"" + str + "\"}";
        return jsonStr;
    }

    /**
     * 把null转化为空字符串
     *
     * @return
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String converNull2Str(Object obj) {
        if (obj == null) {
            return "";
        } else {
            return String.valueOf(obj);
        }
    }

    /**
     * 生成验证码
     *
     * @param length 验证码长度
     * @return
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String bulidCheckCode(int length) {
        int min = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append("9");
        }
        int max = Integer.parseInt(builder.toString());
        Random r = new Random();
        int result = min + r.nextInt(max - min);
        return String.format("%0" + length + "d", result);
    }


    /**
     * 获取访问接口的请求地址 [一句话功能简述] [功能详细描述]
     *
     * @param request
     * @return
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String getRootUrl(HttpServletRequest request) {
        // 获取请求地址
        String urlroot = request.getRequestURL().toString();
        urlroot = urlroot.substring(0, urlroot.indexOf("rest"));
        return urlroot;
    }

}
