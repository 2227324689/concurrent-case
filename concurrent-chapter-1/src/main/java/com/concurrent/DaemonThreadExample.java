package com.concurrent;

import org.omg.SendingContext.RunTime;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class DaemonThreadExample {

    public static void main(String[] args) throws InterruptedException {
       Runtime.getRuntime().addShutdownHook(new Thread(()-> System.out.println("JVM进程已结束")));
       Thread thread=new Thread(()->{
           while(true){
               try {
                   System.out.println("用户线程运行中...");
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       });
       thread.setDaemon(true);
       thread.start();
       Thread.sleep(100);
       System.out.println("主线程执行完毕...");
    }
}
