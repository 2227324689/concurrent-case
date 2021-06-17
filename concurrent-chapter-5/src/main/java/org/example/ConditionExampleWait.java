package org.example;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ConditionExampleWait implements  Runnable{

    private Lock lock;
    private Condition condition;
    public ConditionExampleWait(Lock lock, Condition condition){
        this.lock=lock;
        this.condition=condition;
    }

    @Override
    public void run() {
        System.out.println("begin -ConditionExampleWait");
        try {
            lock.lock();
            condition.await();
            System.out.println("end - ConditionExampleWait");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}