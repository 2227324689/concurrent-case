package com.concurrent.example;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

public class CountDownLatchExample {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(2);
        new Thread(new RelationService(countDownLatch)).start();
        new Thread(new RelationService(countDownLatch)).start();
        countDownLatch.await();
    }
    static class RelationService implements Runnable{
        private CountDownLatch countDownLatch;
        public RelationService(CountDownLatch countDownLatch){
            this.countDownLatch=countDownLatch;
        }

        @Override
        public void run(){
            //doSomething
            System.out.println(Thread.currentThread().getName()+"->done");
            countDownLatch.countDown(); //当前线程执行结束后进行计数器递减
            //continue other operation
        }
    }
}
