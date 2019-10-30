package cn.cpf.web.base.util.io;


import cn.cpf.web.base.lang.base.ICodeItem;
import cn.cpf.web.base.util.cast.CastUtil;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/6/4 9:42
 **/
public class GeneCodeUtils {


    public static String convertJsInterFace(Class<?> inter) {
        if (!inter.isInterface()) {
            return "";
        }
        String simpleName = CastUtil.initialLetterToLowerCase(inter.getSimpleName());
        String collect = Arrays.stream(inter.getClasses())
                .filter(ICodeItem.class::isAssignableFrom)
                .map(it -> {
                    Class<? extends ICodeItem> aClass = it.asSubclass(ICodeItem.class);
                    return convertJsEnum(aClass);
                })
                .collect(Collectors.joining(",\n"));
        return String.format("let %s = {%n    %s%n};", simpleName, collect);
    }


    private static <T extends ICodeItem> String convertJsEnum(Class<T> enumClass) {
        if (!enumClass.isEnum()) {
            return "";
        }
        String codeItemName = CastUtil.initialLetterToLowerCase(enumClass.getSimpleName());
        String collect = Arrays.stream(enumClass.getEnumConstants()).map(each -> String.format("{code:'%s', text:'%s'}", each.getCode(), each.getText())).collect(Collectors.joining(",\n"));
        return String.format("%s : [%n    %s%n]", codeItemName, collect);
    }

}