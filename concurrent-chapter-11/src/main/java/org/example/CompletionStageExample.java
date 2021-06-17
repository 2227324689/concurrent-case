package org.example;

import lombok.extern.slf4j.Slf4j;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
@Slf4j
public class CompletionStageExample {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        CompletableFuture<Void> cf=
                CompletableFuture.supplyAsync(()->{
                    try {
                        Thread.sleep(100000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "thenAccept message";
                }).thenAcceptAsync((result)->{

            log.info(Thread.currentThread().getName()+"第一个异步任务的返回值："+result);
        }).thenAccept(r->{
            log.info("bb");
        });
        cf.get();
    }
}
