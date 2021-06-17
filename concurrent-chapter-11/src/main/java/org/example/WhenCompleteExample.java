package org.example;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
@Slf4j
public class WhenCompleteExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> cf=CompletableFuture.supplyAsync(()->{throw new RuntimeException("Occur Exception");});
        CompletableFuture<String> rx=cf.whenComplete((r,th)->{
            if(th!=null){
                log.error("前置任务出现异常");
            }else{
                log.error("前置任务正常");
            }
        });
        log.info(rx.get());
    }
}
