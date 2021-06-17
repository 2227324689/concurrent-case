package org.example;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class ForkJoinExample {
    private static final Integer MAX=400;

    static class CalculationTask extends RecursiveTask<Integer> {
        private Integer startValue; //子任务开始计算的值
        private Integer endValue; //子任务结束计算的值
        public CalculationTask(Integer startValue,Integer endValue){
            this.startValue=startValue;
            this.endValue=endValue;
        }
        //运算过程
        @Override
        protected Integer compute() {
            // 如果条件成立，说明这个任务所需要计算的数值拆分得足够小了，不需要再拆分可以正式进行累加计算了
            if(endValue-startValue<MAX){
                System.out.println(Thread.currentThread().getName()+"开始计算的部分：startValue = " + startValue + ";endValue = " + endValue);
                Integer totalValue=0;
                for(int index=this.startValue;index<=this.endValue;index++){
                    totalValue+=index;
                }
                return totalValue;
            }
            return createSubtasks(); //拆分多个子任务
        }
        private Integer createSubtasks(){
            CalculationTask subTask1=new CalculationTask(startValue,(startValue+endValue)/2);
            subTask1.fork();
            CalculationTask subTask2=new CalculationTask((startValue+endValue)/2+1,endValue);
            subTask2.fork();
            return subTask1.join()+subTask2.join();
        }
    }
    public static void main(String[] args) {
        ForkJoinPool pool=new ForkJoinPool();
        ForkJoinTask<Integer> taskFuture=pool.submit(new CalculationTask(1,2002));
        try {
            Integer result=taskFuture.get();
            System.out.println("result:"+result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
