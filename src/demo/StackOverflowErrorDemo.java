package demo;

/**
 * @program: Thread
 * @description:
 * @author: miracle
 * @create: 2019-09-18 10:21
 **/


public class StackOverflowErrorDemo {
    public static void main(String[] args) {
        stackOverflowError() ;
    }

    private static void stackOverflowError() {
        stackOverflowError();
    }
}
