package com.github.sinjar.common.util.common;

import java.util.Arrays;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * Date: 2020/3/27 14:42
 */
public class ArrUtils {

    public static void main(String[] args) {
        int[] arr2 = { 8,  2, 22, 97, 38, 15,  0, 40,  0, 75,  4,  5,  7, 78, 52, 12, 50, 77, 91,  8};

        System.out.println(Arrays.stream(arr2).reduce((a, b) -> {
            int su = a + b;
            System.out.println(String.format("%s %s %s", a, b, su));
            return su;
        }));

        int ii = getMaxProductInArr(arr2, 4);
        System.out.println(ii);
    }

    /**
     * 获取一维数组中相邻 number 个数的最大乘积
     *
     * @param arr
     * @param number
     * @return
     * throw new RuntimeException (arr.length < number)
     */
    public static int getMaxProductInArr(int[] arr, int number){
        int len = arr.length;
        if (len < number){
            throw new RuntimeException("arr太小!");
        }
        int[] num = new int[number];
        int i = 0;
        for (; i < number; i++) {
            num[i] = arr[i];
        }
        int product = Arrays.stream(num).reduce((a, b) -> a*b).getAsInt();
        int cur = 0;
        int tmpProdect;
        for (; i < len; i ++) {
            num[cur] = arr[i];
            tmpProdect = Arrays.stream(num).reduce((a, b) -> a*b).getAsInt();
            if (tmpProdect > product) {
                product = tmpProdect;
            }
            cur ++;
            cur = cur % number;
        }
        return product;
    }

    /**
     * 转置矩阵, 矩阵必须是方形矩阵
     * @param matrix
     */
    public static void transposeMatrix(Object[][] matrix){
        if (matrix.length != matrix[0].length){
            throw new RuntimeException("matrix.length != matrix[0].length");
        }
        int length = matrix.length;
        Object tmp;
        for (int i = 0; i < length; i ++){
            for (int j = 0; j < i; j++){
                tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
    }

    /**
     * 转置矩阵, 矩阵必须是方形矩阵
     * @param matrix
     */
    public static <T> T[][] deepClone(T[][] matrix){
        int len = matrix.length;
        T[][] arr = matrix.clone();
        for (int i = 0; i<len;i ++){
            arr[i] = matrix[i].clone();
        }
        return arr;
    }


    public static int[][] deepClone(int[][] matrix){
        int len = matrix.length;
        int[][] arr = matrix.clone();
        for (int i = 0; i<len;i ++){
            arr[i] = matrix[i].clone();
        }
        return arr;
    }


    /**
     * 转置矩阵
     *
     * @param matrix
     */
    public static void transposeMatrix(int[][] matrix){
        if (matrix.length != matrix[0].length){
            throw new RuntimeException("matrix.length != matrix[0].length");
        }
        int length = matrix.length;
        int tmp;
        for (int i = 0; i < length; i ++){
            for (int j = 0; j < i; j++){
                tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
    }


    /**
     * 将一个字符串数组转换为int数组
     * @param strarr
     * @return
     */
    public static int[] transStrArrToIntArr(String[] strarr){
        int len = strarr.length;
        int[] intarr = new int[len];
        for (int i = 0; i < len; i++){
            intarr[i] = Integer.parseInt(strarr[i]);
        }
        return intarr;
    }

    public static void fillDeep2(Object[][] arrs, Object object) {
        for (int i = arrs.length - 1 ; i >= 0; i--){
            Arrays.fill(arrs[i], object);
        }
    }

    public static void fillDeep2(int[][] arrs, int num) {
        for (int i = arrs.length - 1 ; i >= 0; i--){
            Arrays.fill(arrs[i], num);
        }
    }

    public static int indexOf(int[] arr, int n) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == n) {
                return i;
            }
        }
        return -1;
    }

}
