package cn.cpf.web.service.mod.redis.base;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;
import java.util.function.Supplier;

/**
 * <b>Description : </b> 缓存失效策略
 *
 * @author CPF
 * @date 2019/9/30 15:33
 **/
public class CacheExpiredStrategy {

    public static final CacheExpiredStrategy oneHourInTheMorningStrategy = new CacheExpiredStrategy(DateSupplier.oneHourInTheMorning);

    /**
     * 日期提供
     */
    public static final class DateSupplier{

        public static final Supplier<Date> oneHourInTheMorning = DateSupplier::getMidnightDate;

        public static final Random random = new Random();

        public static Date getMidnightDate(){
            final int hour = random.nextInt(2) + 1;
            final int minute = random.nextInt(60);
            final LocalDateTime of = LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(hour, minute));
            return Date.from(of.atZone(ZoneId.systemDefault()).toInstant());
        }
    }

    private Supplier<Date> nextCheckSupplier;

    public CacheExpiredStrategy(Supplier<Date> nextCheckSupplier) {
        this.nextCheckSupplier = nextCheckSupplier;
    }

    public Date newDate() {
        return nextCheckSupplier.get();
    }

}
