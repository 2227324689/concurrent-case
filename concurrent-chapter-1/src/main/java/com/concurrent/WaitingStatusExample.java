package com.concurrent;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class WaitingStatusExample {

    public static void main(String[] args) {
        new Thread(()->{
            synchronized (WaitingStatusExample.class){
                try {
                    WaitingStatusExample.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"WAITING").start();
    }
}
