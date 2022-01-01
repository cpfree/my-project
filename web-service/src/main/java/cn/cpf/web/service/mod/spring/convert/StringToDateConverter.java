package cn.cpf.web.service.mod.spring.convert;

import cn.cpf.web.base.util.exception.PostMessageException;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.util.Date;

/**
 * <b>Description : </b> spring转换器, 主要适用于controller接收时间参数转换
 *
 * @author CPF
 * @date 2019/10/16 23:06
 **/
public class StringToDateConverter implements Converter<String, Date> {

    private static final String[] parsePatterns = {"yyyy-MM-dd hh:mm:ss", "yyyy-MM-dd"};

    @Override
    public Date convert(String string) {
        if (string == null || string.trim().isEmpty()) {
            return null;
        }
        if (NumberUtils.isDigits(string)) {
            return new Date(Long.parseLong(string));
        }
        try {
            return DateUtils.parseDate(string, parsePatterns);
        } catch (ParseException e) {
            throw new PostMessageException("日期参数转换异常", e);
        }
    }

}
