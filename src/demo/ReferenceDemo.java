package demo;


import java.util.concurrent.atomic.AtomicReference;

/**
 * @program: Thread
 * @description:
 * @author: miracle
 * @create: 2019-09-12 11:42
 **/

class User{
    String username;
    int age;

    public User(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
public class ReferenceDemo {
    public static void main(String[] args) {
        User user1 = new User("user1",22);
        User user2 = new User("user2", 25);
        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(user1);
        System.out.println(atomicReference.compareAndSet(user1, user2));
    }
}
