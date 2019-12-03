package cn.cpf.web.base.util;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.Validate;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/11/27 17:32
 **/
public interface GeneUtils {

    /**
     * 生成指定位数的数字随机数, 最高不超过 9 位
     *
     * @param length 生成随机数长度
     * @return 生成的随机数
     */
    static String getRandomCode(int length) {
        Validate.isTrue(length <= 9 && length > 0, "生成数字随机数长度范围应该在 1~9 内, 参数 length : %s", length);
        int floor = (int) Math.pow(10, length - 1.0);
        int codeNum = RandomUtils.nextInt(floor, floor * 10);
        return Integer.toString(codeNum);
    }


}
