package cn.cpf.web.base.util.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/10/10 11:48
 **/
public class StringUtils {

    /**
     * 正则替换
     *
     * @param regex 正则表达式
     * @param content 文本
     * @param prefix 前缀
     * @param suffix 后缀
     * @return
     */
    public static String replaceJoinAll(String regex, String content, String prefix, String suffix){
        final Pattern p = Pattern.compile(regex);
        // 获取 matcher 对象
        final Matcher m = p.matcher(content);
        final StringBuffer sb = new StringBuffer();
        while(m.find()){
            final String group = m.group();
            m.appendReplacement(sb, prefix + group + suffix);
        }
        m.appendTail(sb);
        return sb.toString();
    }

}
