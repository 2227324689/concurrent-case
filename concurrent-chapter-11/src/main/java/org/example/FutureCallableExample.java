package org.example;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/

public class FutureCallableExample {
    static class CalculationCallable implements Callable<Integer>{
        private int x;
        private int y;

        public CalculationCallable(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public Integer call() throws Exception {
            System.out.println("begin call:"+new Date());
            TimeUnit.SECONDS.sleep(2); //模拟任务执行的耗时
            return x+y;
        }
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CalculationCallable calculationCallable=new CalculationCallable(1,2);
        FutureTask<Integer> futureTask=new FutureTask<>(calculationCallable);
        new Thread(futureTask).start();
        System.out.println("begin execute futuretask:"+new Date());
        Integer rs=futureTask.get();
        System.out.println("result:"+rs+"");
        System.out.println("end execute futuretask:"+new Date());
    }
}
