package cn.cpf.web.base.util.cast;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/3/21 16:30
 **/
public class DateUtil {

    public static final String ITY_SAVE_DATE = "yyyyMMdd";
    public static final String ITY_SAVE_DATE_TIME = "yyyyMMddhhmmss";
    public static final String FORMAT_DATE = "yyyy-MM-dd";
    public static final String FORMAT_DATE_TIME = "yyyy-MM-dd hh:mm:ss";

    /**
     * @return 获取第二天凌晨的日期
     */
    public static Date getMidnightDate() {
        return Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 是否是同一天
     */
    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 == null) {
            return date2 == null;
        }
        if (date2 == null) {
            return false;
        }
        return date1.getTime() / 86400_000 == date2.getTime() / 86400_000;
    }

    /**
     * 计算 date1 和 date2 的差值
     *
     * @param dateLarger  较大的日期
     * @param dateSmaller 较小的日期
     * @return (int) (date2.getTime() / 86400_000 - date1.getTime() / 86400_000);
     */
    public static int computeSubDay(Date dateLarger, Date dateSmaller) {
        if (dateLarger == null || dateSmaller == null) {
            throw new NullPointerException("时间差值计算空指针异常");
        }
        return (int) (dateLarger.getTime() / 86400_000 - dateSmaller.getTime() / 86400_000);
    }

    public static Date parseDate(String datetime, String dateFormatStr) throws ParseException {
        return org.apache.commons.lang3.time.DateUtils.parseDate(datetime, dateFormatStr);
    }

    public static Date parseDateOfNullable(String datetime, String dateFormatStr) throws ParseException {
        if (StringUtils.isBlank(datetime)) {
            return null;
        }
        return parseDate(datetime, dateFormatStr);
    }

    public static String today() {
        return new SimpleDateFormat(FORMAT_DATE).format(new Date());
    }

}
