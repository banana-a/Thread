package demo;

/**
 * @program: Thread
 * @description:
 * @author: miracle
 * @create: 2019-09-11 12:26
 **/


public class SingletonDemo {
    SingletonDemo instance;
    private SingletonDemo(){}
    //懒汉
    public SingletonDemo getInstance(){
        if (instance == null){
            instance = new SingletonDemo();
        }
        return instance;
    }
    //饿汉
    SingletonDemo instance1 = new SingletonDemo();
    public SingletonDemo getInstance1(){
        return instance1;
    }
    //懒汉线程安全
    public synchronized SingletonDemo getInstance2(){
        if (instance == null){
            instance = new SingletonDemo();
        }
        return instance;
    }
    //双检锁
    volatile SingletonDemo instance2;
    public SingletonDemo getInstance3(){
        if (instance2 == null){
            synchronized (SingletonDemo.class){
                if (instance2 == null) {
                    instance2 = new SingletonDemo();
                }
            }
        }
        return instance2;
    }
    //内部类
    private static class SingletonHolder {
        private static final SingletonDemo INSTANCE = new SingletonDemo();
    }
    public static final SingletonDemo getInstance4() {
        return SingletonHolder.INSTANCE;
    }
}
