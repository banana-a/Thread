package demo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: Thread
 * @description:
 * @author: miracle
 * @create: 2019-09-11 12:55
 **/


public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        atomicInteger.compareAndSet(5,10);
        System.out.println(atomicInteger);
    }
}
