package demo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @program: Thread
 * @description:
 * @author: miracle
 * @create: 2019-09-16 22:00
 **/

class MyThread implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        return 1024;
    }
}
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        Thread t1 = new Thread(futureTask);
        Thread t2 = new Thread(futureTask);
        t1.start();
        t2.start();//不进入方法，多个线程共用一个FutureTack，只进入一个
        int result1 = 100;
//        while (!futureTask.isDone()){
//
//        }
        int result2 = futureTask.get();//如果没算完，就会阻塞
        System.out.println(result1 + result2);
    }
}
