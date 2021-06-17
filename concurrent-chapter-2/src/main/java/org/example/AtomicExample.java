package org.example;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class AtomicExample {
    volatile int i = 0;
    public synchronized void incr(){
        i++;
    }
    public static void main(String[] args) throws InterruptedException {
        AtomicExample atomicExample = new AtomicExample();
        Thread[] threads=new Thread[2];
        for (int j = 0;j<2;j++) {
            threads[j]=new Thread(() -> {
                for (int k=0;k<10000;k++) {
                    atomicExample.incr();
                }
            });
            threads[j].start();
        }
        threads[0].join();
        threads[1].join();
        System.out.println(atomicExample.i);
    }
}
