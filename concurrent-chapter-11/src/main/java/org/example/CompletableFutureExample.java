package org.example;

import lombok.extern.slf4j.Slf4j;

import java.sql.Time;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
@Slf4j
public class CompletableFutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> v1=CompletableFuture.supplyAsync(()->{
            return "no return value for v1";
        });
        CompletableFuture<String> v2=CompletableFuture.supplyAsync(()->{
            return "no return value for v2";
        });
        /*CompletableFuture.anyOf(v1,v2).thenAccept(value->log.info(value.toString())).join();

        CompletableFuture df=CompletableFuture.completedFuture("I' default value");*/

     }
}
