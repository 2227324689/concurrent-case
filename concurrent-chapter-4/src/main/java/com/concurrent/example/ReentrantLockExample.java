package com.concurrent.example;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class ReentrantLockExample {
    static Lock lock=new ReentrantLock();
    private int count = 0;
    public void incr(){
        lock.lock();
        try {
            count++;
        }finally {
            lock.unlock();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        ReentrantLockExample atomicExample = new ReentrantLockExample();
        Thread[] threads=new Thread[2];
        for (int j = 0;j<2;j++) {
            threads[j]=new Thread(() -> {
                for (int k=0;k<10000;k++) {
                    atomicExample.incr();
                }
            });
            threads[j].start();
        }
        threads[0].join();//保证线程执行结束
        threads[1].join();
        System.out.println(atomicExample.count);
    }
}
