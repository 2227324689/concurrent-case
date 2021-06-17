package com.concurrent.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class ReadWriteLockExample {
    private final ReadWriteLock readWriteLock=new ReentrantReadWriteLock();
    private final Lock readLock=readWriteLock.readLock();
    private final Lock writeLock=readWriteLock.writeLock();
    private List<String> dataList=new ArrayList<>();
    public void add(String data)  {
        writeLock.lock();
        try {
            dataList.add(data);
        }finally {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            writeLock.unlock();
        }
    }
    public String get(int idx){
        readLock.lock();
        try{
            return dataList.get(idx);
        }finally {
//            readLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReadWriteLockExample example=new ReadWriteLockExample();
        new Thread(()->{
            example.add("xxx");
        },"tw1").start();
        Thread.sleep(1000);
        new Thread(()->{
            example.get(0);

        },"tr1").start();
        Thread.sleep(1000);

        new Thread(()->{
            example.add("xxxxxxyyyy");

        },"tw2").start();
        Thread.sleep(1000);

        new Thread(()->{
            example.get(0);
        },"tr2").start();

    }
}
