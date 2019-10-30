package cn.cpf.common;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/10/22 21:54
 **/
public class StringTest {

    /**
     * 字符串里面
     */
    public void test() {
        String s1 = new StringBuilder("12").append("ab").toString();
        System.out.println(s1.intern() == s1);
        String s2 = new StringBuilder("12").append("ab").toString();
        System.out.println(s2.intern() == s2);
        String s3 = new StringBuilder("ja").append("va").toString();
        System.out.println(s3 == s3.intern());
    }

}
