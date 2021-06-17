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
public class RunAfterBothExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture cf=CompletableFuture.supplyAsync(()-> {
            throw new RuntimeException("occur exception");
        }).runAfterBoth(CompletableFuture.supplyAsync(()->"Message"),()-> log.info("Done"));
        log.info("result:"+cf.get());
    }
}
