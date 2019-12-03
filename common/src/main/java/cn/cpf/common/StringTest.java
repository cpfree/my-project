package cn.cpf.common;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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


    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        CountDownLatch cdl = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            CountRunnable runnable = new CountRunnable(cdl);
            pool.execute(runnable);
        }
    }

    static class CountRunnable implements Runnable {

        private CountDownLatch countDownLatch;

        public CountRunnable(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        public void run() {
            try {
                synchronized (countDownLatch) {
                    /*** 每次减少一个容量*/
                    countDownLatch.countDown();
                    System.out.println("thread counts = " + (countDownLatch.getCount()));
                }
                countDownLatch.await();
                System.out.println("concurrency counts = " + (100 - countDownLatch.getCount()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
