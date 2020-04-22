package com.github.sinjar.common.util.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * Date: 2020/4/13 9:28
 */
public class BaseUtils {

    public static void main(String[] args) {
        int[] i = new int[3];
        System.out.println(Arrays.toString(i));
    }

//    /**
//     * 生成指定位数的数字随机数, 最高不超过 9 位
//     *
//     * @param length
//     * @return
//     */
//    public static String getRandomCode(int length) {
//        Validate.isTrue(length <= 9 && length > 0, "生成数字随机数长度范围应该在 1~9 内, 参数 length : %s", length);
//        int floor = (int) Math.pow(10, length - 1);
//        int codeNum = RandomUtils.nextInt(floor, floor * 10);
//        return Integer.toString(codeNum);
//    }

    /**
     * 从 [0, range] 中获取cnt个不相同的随机数
     *
     * @param range 范围
     * @param cnt 随机数值
     * @return 从 0-range范围内的不相同的cnt个随机数值
     */
    public static List<Integer> getRandomsFromRange(int range, int cnt) {
        if (range < cnt) {
            throw new RuntimeException();
        }
        Random random = new Random();

        int pointer = 0;
        int[][] mapping = new int[2][cnt];
        Arrays.fill(mapping[0], -1);
        Arrays.fill(mapping[1], -1);

        List<Integer> list = new ArrayList<>(cnt);

        for (int x = cnt; x > 0; x--) {
            int i = random.nextInt(range);
            range--;
            if (i < range) {
                int index = ArrUtils.indexOf(mapping[0], i);
                if (index == -1) {
                    list.add(i);
                    index = ArrUtils.indexOf(mapping[0], range);
                    if (index == -1) {
                        mapping[0][pointer] = i;
                        mapping[1][pointer] = range;
                        pointer++;
                    } else {
                        mapping[0][index] = i;
                    }
                } else {
                    index = ArrUtils.indexOf(mapping[0], i);
                    assert index > -1;
                    list.add(mapping[1][index]);
                    int idx2 = ArrUtils.indexOf(mapping[0], range);
                    if (idx2 == -1) {
                        mapping[1][index] = range;
                    } else {
                        mapping[1][index] = mapping[1][idx2];
                        // --
                        mapping[0][idx2] = -1;
                        mapping[1][idx2] = -1;
                    }
                }
            } else if (i == range){
                int index = ArrUtils.indexOf(mapping[0], range);
                if (index == -1) {
                    list.add(i);
                } else {
                    list.add(mapping[1][index]);
                    // --
//                    mapping[0][index] = -1;
//                    mapping[1][index] = -1;
                }
            } else {
                throw new RuntimeException();
            }
        }
        return list;
    }


}
