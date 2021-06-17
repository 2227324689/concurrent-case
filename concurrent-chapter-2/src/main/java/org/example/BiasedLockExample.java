package org.example;

import org.openjdk.jol.info.ClassLayout;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class BiasedLockExample {
    public static void main(String[] args) throws InterruptedException {
        BiasedLockExample example=new BiasedLockExample();
        System.out.println("加锁之前");
        System.out.println(ClassLayout.parseInstance(example).toPrintable());

        synchronized (example){
            System.out.println("加锁之后");
            System.out.println(ClassLayout.parseInstance(example).toPrintable());
        }
    }
}
