package org.example;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ConditionExampleSignal  implements  Runnable{

    private Lock lock;
    private Condition condition;
    public ConditionExampleSignal(Lock lock, Condition condition){
        this.lock=lock;
        this.condition=condition;
    }

    @Override
    public void run() {
        System.out.println("begin -ConditionExampleSignal");
        try {
            lock.lock();
            condition.signal();
            System.out.println("end - ConditionExampleSignal");
        }finally {
            lock.unlock();
        }
    }
}
