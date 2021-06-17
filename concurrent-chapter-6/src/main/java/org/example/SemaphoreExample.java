package com.concurrent.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class SemaphoreExample {

    public static void main(String[] args) {
        Semaphore semaphore=new Semaphore(2);
        ExecutorService service= Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            service.execute(new SomeTask(semaphore));
        }
        service.shutdown();
    }
    static class SomeTask implements Runnable{
        private Semaphore semaphore;
        public SomeTask(Semaphore semaphore){
            this.semaphore=semaphore;
        }
        @Override
        public void run(){
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName()+" 获得一个令牌");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println(Thread.currentThread().getName()+" 释放一个令牌");
                semaphore.release(1000);
            }
        }
    }
}
