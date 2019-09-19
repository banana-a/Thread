package demo;

import java.util.concurrent.*;

/**
 * @program: Thread
 * @description:
 * @author: miracle
 * @create: 2019-09-16 22:37
 **/


public class MyThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),new ThreadPoolExecutor.CallerRunsPolicy());
        try {
            for (int i = 1;i <= 10;i++){
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
