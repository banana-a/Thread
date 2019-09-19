package demo;

/**
 * @program: Thread
 * @description:
 * @author: miracle
 * @create: 2019-09-17 19:37
 **/

class HoldLockThread implements Runnable{
    private String lockA;
    private String lockB;
    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }
    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName() + "\t自己持有：" + lockA + "\t尝试持有" + lockB);
            synchronized (lockB){

            }
        }
    }
}
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLockThread(lockA, lockB),"AAA").start();
        new Thread(new HoldLockThread(lockB, lockA),"BBB").start();
    }
}
