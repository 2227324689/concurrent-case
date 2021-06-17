package org.example;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Hello world!
 *
 */
public class ThreadPoolExample
{
    public static void main( String[] args ){
        ExecutorService executorService= Executors.newFixedThreadPool(4);
        for (int i = 0; i < 10; i++) {
            //把一个实现了Runnable接口的任务给到线程池执行
            executorService.execute(new Task());
        }
        executorService.shutdown(); //关闭线程池
    }

    static class Task implements Runnable{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+" - 开始执行任务");
            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" - 执行完成");
        }
    }
}
