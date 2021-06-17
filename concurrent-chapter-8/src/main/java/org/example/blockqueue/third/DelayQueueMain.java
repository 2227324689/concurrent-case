package org.example.blockqueue.third;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
@Slf4j
public class DelayQueueMain {

    private static DelayQueue delayQueue  = new DelayQueue();

    public static void main(String[] args) {
        delayQueue.offer(new OrderDelayedTask("1001",1000));
        delayQueue.offer(new OrderDelayedTask("1002",3000));
        delayQueue.offer(new OrderDelayedTask("1003",4000));
        delayQueue.offer(new OrderDelayedTask("1004",2000));
        delayQueue.offer(new OrderDelayedTask("1005",6000));
        delayQueue.offer(new OrderDelayedTask("1006",5000));

        while(true){
            try {
                Delayed task=delayQueue.take();
                log.info("{}",task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
