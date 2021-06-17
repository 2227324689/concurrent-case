package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class ShutdownExample {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService= Executors.newSingleThreadExecutor();
        executorService.shutdown();
        //如果等待60s之后，线程池还没有关闭，则强制中断。
        if(!executorService.awaitTermination(60, TimeUnit.SECONDS)){
            executorService.shutdownNow();
        }
    }
}
