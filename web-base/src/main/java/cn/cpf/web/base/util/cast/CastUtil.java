package cn.cpf.web.base.util.cast;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
public class CastUtil {

    public static Integer parseInt(Object obj, Integer defaultValue) {
        int value = 0;
        if (obj != null) {
            String str = String.valueOf(obj);
            if (StringUtils.isNotBlank(str)) {
                try {
                    value = Integer.parseInt(str);
                } catch (NumberFormatException e) {
                    value = defaultValue;
                }
            }
        }
        return value;
    }


    public static int parseInt(Object obj) {
        return parseInt(obj, 0);
    }

    public static Long parseLong(Object longObj) {
        return Long.parseLong(longObj.toString());
    }

    public static Integer parseInteger(Object obj) {
        return parseInt(obj, null);
    }


    public static Date parseDate(Object obj) {
        return parse(obj, Date.class);
    }


    public static Boolean parseBoolean(Object obj) {
        return parse(obj, Boolean.class);
    }

    public static <T> T parse(Object obj, Class<T> primaryClass) {
        if (primaryClass.isInstance(obj)) {
            return (T) obj;
        }
        return null;
    }


    public static Date localDatetimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate dateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * 首字母转小写
     */
    public static String initialLetterToLowerCase(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        }
        return Character.toLowerCase(s.charAt(0)) + s.substring(1);
    }

    /**
     * 首字母转小写
     */
    public static String initialLetterToUppercase(String s) {
        if (Character.isUpperCase(s.charAt(0))) {
            return s;
        }
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

    public static <T> Object valueOf(String string, @NonNull Class<T> tClass){
        try {
            Method valueOf = tClass.getMethod("valueOf");
            return valueOf.invoke(null, string);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new ClassCastException(e.getMessage());
        }
    }


}
