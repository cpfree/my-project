package cn.cpf.test.base;

import static com.github.sinjar.common.util.common.StrUtils.replaceJoinAll;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/12/16 14:24
 **/
public class MainTest {

    public static void main(String[] args) {
        System.out.println(replaceJoinAll("\\d+", "sing34hj32kh42322jk", "$", ">"));
    }

}
