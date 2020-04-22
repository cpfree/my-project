package cn.cpf.test;

import org.apache.logging.log4j.core.util.JsonUtils;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.Properties;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * Date: 2020/4/8 16:35
 */
public class MainTest {

    public static void main(String[] args) {
        Properties properties = System.getProperties();
        Enumeration<?> x = properties.propertyNames();
        while (x.hasMoreElements()) {
            Object o = x.nextElement();
            System.out.println(o + " --> " + properties.getProperty(o.toString()));
        }
        System.out.println(Arrays.toString(args));
        System.getSecurityManager();
        System.nanoTime();

        if (args.length > 1) {
            System.out.println(2);
        } else {
            System.out.println(3);
        }

        {
            /*  h **/
            //
            int i = 0;
        }
        {
            /* fd **/
        }




        if (args.length > 4) System.out.println(2);
        else System.out.println(3);

    }

}
