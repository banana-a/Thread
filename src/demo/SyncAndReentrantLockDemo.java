package demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: Thread
 * @description:
 * @author: miracle
 * @create: 2019-09-16 21:17
 **/

class ShareResource{
    private int number = 1;//A:1 B:2 C:3
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();
    public void print5() throws InterruptedException {
        lock.lock();
        try {
            //1.判断
            while (number != 1){
                c1.await();
            }
            //2.干活
            for (int i = 1;i <= 5;i++){
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //3.通知
            number = 2;
            c2.signal();
        }finally {
            lock.unlock();
        }
    }
    public void print10() throws InterruptedException {
        lock.lock();
        try {
            //1.判断
            while (number != 2){
                c2.await();
            }
            //2.干活
            for (int i = 1;i <= 10;i++){
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //3.通知
            number = 3;
            c3.signal();
        }finally {
            lock.unlock();
        }
    }
    public void print15() throws InterruptedException {
        lock.lock();
        try {
            //1.判断
            while (number != 3){
                c3.await();
            }
            //2.干活
            for (int i = 1;i <= 15;i++){
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //3.通知
            number = 1;
            c1.signal();
        }finally {
            lock.unlock();
        }
    }
}
public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() -> {
            for (int i = 1;i <= 10;i++){
                try {
                    shareResource.print5();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(() -> {
            for (int i = 1;i <= 10;i++){
                try {
                    shareResource.print10();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(() -> {
            for (int i = 1;i <= 10;i++){
                try {
                    shareResource.print15();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
    }
}
