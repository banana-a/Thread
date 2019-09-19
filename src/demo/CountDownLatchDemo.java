package demo;

import java.util.concurrent.CountDownLatch;

/**
 * @program: Thread
 * @description:
 * @author: miracle
 * @create: 2019-09-15 20:22
 **/


public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for(int i = 1;i <= 6;i++){
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t上完自习，离开教室");
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "***********班长关门走人");
    }
}
