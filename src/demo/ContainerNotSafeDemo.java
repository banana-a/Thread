package demo;

import java.util.*;

/**
 * @program: Thread
 * @description:
 * @author: miracle
 * @create: 2019-09-12 12:16
 **/


public class ContainerNotSafeDemo {
    public static void main(String[] args) {

    }

    private static void mapNotSafe() {
        Map<String,String> map = new HashMap<>();
        for (int i = 1;i <= 300;i++){
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }

    private static void setNotSafe() {
        Set<String> set = new HashSet<>();
        for (int i = 1;i <= 30;i++){
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0,7));
                System.out.println(set);
            }).start();
        }
    }

    private static void listNotSafe() {
        List<String> list = new ArrayList<>();
        for (int i = 1;i <= 30;i++){
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            }).start();
        }
    }
}
