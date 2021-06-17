package org.example;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class SynchronizedForClassExample {

    public void m1() {
        synchronized(SynchronizedForClassExample.class) {
            while (true) {
                System.out.println("当前访问的线程：" + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
        SynchronizedForClassExample se1=new SynchronizedForClassExample();
        SynchronizedForClassExample se2=new SynchronizedForClassExample();
        new Thread(()->se1.m1(),"t1").start();
        new Thread(()->se2.m1(),"t2").start();
    }
}
