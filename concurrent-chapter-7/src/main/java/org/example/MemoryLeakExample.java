package org.example;

import java.util.concurrent.*;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class MemoryLeakExample {
    static class LocalVariable {
        private Long[] data=new Long[1024*1024];
    }
    static ThreadPoolExecutor service=new ThreadPoolExecutor(5,5,60,TimeUnit.SECONDS,new LinkedBlockingQueue<>());
    static ThreadLocal local=new ThreadLocal();
    public static void main(String[] args) throws InterruptedException {
        TimeUnit.SECONDS.sleep(8); //延迟启动，方便jconsole进行数据监视
        CountDownLatch countDownLatch=new CountDownLatch(50);
        for (int i = 0; i <  50; i++) {
            service.execute(()->{
                local.set(new LocalVariable());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                local.remove();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        local=null;
        System.gc();
        System.out.println(local);
    }
}
