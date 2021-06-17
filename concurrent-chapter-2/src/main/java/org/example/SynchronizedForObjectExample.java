package org.example;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class SynchronizedForObjectExample {
    static Object lock=new Object();
    public void m1(){
        synchronized (lock){
            while(true){
                System.out.println("当前获得锁的线程："+Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        SynchronizedForObjectExample se1=new SynchronizedForObjectExample();
        SynchronizedForObjectExample se2=new SynchronizedForObjectExample();
        new Thread(()->se1.m1(),"t1").start();
        new Thread(()->se2.m1(),"t2").start();
    }

}
