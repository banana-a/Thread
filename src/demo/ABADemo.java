package demo;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @program: Thread
 * @description:
 * @author: miracle
 * @create: 2019-09-12 11:51
 **/


public class ABADemo {
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100,1);
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101, 100);
        },"t1");
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicReference.compareAndSet(100, 2019);
        },"t2");
        Thread.sleep(2000);
        System.out.println("===================以下是ABA问题的解决==================");

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(),atomicStampedReference.getStamp() +  1);
            atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
        });

        new Thread(() -> {
            //保证t3，t4拿到相同初始版本号，并保证t3完成一次ABA操作
            int stamp = atomicStampedReference.getStamp();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicStampedReference.compareAndSet(100, 2019, stamp, stamp + 1));
        });
    }
}
