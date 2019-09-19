package demo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: Thread
 * @description:
 * @author: miracle
 * @create: 2019-09-10 10:56
 **/

class Mydata{
    //这里有无volatile关键字验证可见性
    volatile int number = 0;
    AtomicInteger atomicInteger = new AtomicInteger();
    public void addTo60(){
        this.number = 60;
    }
    public void add(){
        this.number++;
    }
}
public class VolatileDemo {
    public static void main(String[] args) {

    }

    private static void atomicIntegerDemo() {
        Mydata mydata = new Mydata();
        for (int i = 0;i < 20;i++){
            new Thread(() -> {
                for (int j = 0;j < 1000;j++){
                    mydata.atomicInteger.getAndAdd(1);
                }
            },"thread" + i).start();
        }
        while (Thread.activeCount() > 2){
            Thread.yield();
        }
        System.out.println(mydata.atomicInteger.get());
    }

    private static void atoNoByVolatile() {
        Mydata mydata = new Mydata();
        for (int i = 0;i < 20;i++){
            new Thread(() -> {
                for (int j = 0;j < 1000;j++){
                    mydata.add();
                }
            },"thread" + i).start();
        }
        while (Thread.activeCount() > 2){
            Thread.yield();
        }
        System.out.println(mydata.number);
    }


    private static void seeOkByVolatile() {
        Mydata mydata = new Mydata();
        new Thread(() ->{
            System.out.println(Thread.currentThread().getName() + "  come in");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mydata.addTo60();
            System.out.println("data = " + mydata.number);
        },"AAA").start();
        while (mydata.number == 0){

        }
    }
}
