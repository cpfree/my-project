package cn.cpf.web.base.util.common;

import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/10/10 11:48
 **/
public interface StrUtils {

    /**
     * 正则替换
     *
     * @param regex 正则表达式
     * @param content 文本
     * @param prefix 前缀
     * @param suffix 后缀
     * @return
     */
    static String replaceJoinAll(String regex, String content, String prefix, String suffix){
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


    /**
     * 首字母变小写
     */
    static String firstCharToLowerCase(@NonNull String str) {
        str = str.trim();
        if (str.isEmpty()) {
            return str;
        }
        return Character.toLowerCase(str.charAt(0)) + str.substring(1);
    }

    /**
     * 首字母变大写
     */
    static String firstCharToUpperCase(@NonNull String str) {
        str = str.trim();
        if (str.isEmpty()) {
            return str;
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    /**
     * 返回待处理字符串的驼峰格式
     * @param string 待处理的字符串
     * @return 返回驼峰式字符串, 以'_'为分隔符
     */
    static String lowerCamelize(@NonNull String string){
        StringTokenizer tokenizer = new StringTokenizer(string);
        StringBuilder sb = null;
        while (tokenizer.hasMoreElements()) {
            String s = tokenizer.nextToken("_").toLowerCase();
            if (sb == null) {
                sb = new StringBuilder();
                sb.append(s);
            } else {
                sb.append(firstCharToUpperCase(s));
            }
        }
        if (sb == null) {
            return "";
        }
        return sb.toString();
    }

    /**
     * 返回待处理字符串的驼峰格式
     * @param string 待处理的字符串
     * @return 返回驼峰式字符串, 以'_'为分隔符
     */
    static String upperCamelize(@NonNull String string){
        return firstCharToUpperCase(lowerCamelize(string));
    }

}
