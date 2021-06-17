package org.example;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class CasExample {

    public AtomicInteger atomicInteger=new AtomicInteger(0);
    public void add(){
        atomicInteger.getAndIncrement();
    }
}
