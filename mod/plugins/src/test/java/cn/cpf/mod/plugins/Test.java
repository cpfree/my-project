package cn.cpf.mod.plugins;

import cn.cpf.web.base.constant.dic.DicCommon;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/12/13 14:03
 **/
@Slf4j
public class Test {

    public static void main(String[] args) {

        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 10000000; i++) {
            final String key = String.valueOf(i);
            final String key0 = key + "-500";
            map.put(key, key0);
        }
        final List<String> collect = map.keySet().stream().collect(Collectors.toList());
        final long l = System.nanoTime();
        for (String s : collect) {
            log.debug(map.get(s));
        }
        final long e = System.nanoTime();
        System.out.println(l);
        System.out.println(e);
        System.out.println(e - l);
        // 312209100
        // 318042100
    }

}
