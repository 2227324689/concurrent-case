package org.example.blockqueue.first;

import lombok.extern.slf4j.Slf4j;
import org.example.blockqueue.third.DelayQueueMain;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
@Slf4j
public class ProducerAndConsumerProgram {


    static Logger logger= LoggerFactory.getLogger(ProducerAndConsumerProgram.class);

    static class Producer implements Runnable {
        BlockingQueue<String> blockingQueue;

        public Producer(BlockingQueue<String> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    blockingQueue.put("element:"+i);
                    logger.info("{},生产者生产数据，目前总共的元素有:{}",Thread.currentThread().getName(),blockingQueue.size());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    static class Consumer implements Runnable{
        BlockingQueue<String> blockingQueue;

        public Consumer(BlockingQueue<String> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    String element=blockingQueue.take(); //通过阻塞特性的获取元素方法取出数据
                    logger.info("{},消费者消费数据，目前还剩下的元素个数：{}",Thread.currentThread().getName(),blockingQueue.size());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue=new LinkedBlockingQueue<>(10);
        Producer producer=new Producer(blockingQueue);
        Consumer consumer=new Consumer(blockingQueue);
        new Thread(producer).start();
        Thread.sleep(10);
        new Thread(consumer).start();
    }
    /*@Test
    public void testProducerAndConsumer() throws InterruptedException {
        BlockingQueue<String> blockingQueue=new LinkedBlockingQueue<>(10);
        Producer producer=new Producer(blockingQueue);
        Consumer consumer=new Consumer(blockingQueue);
        new Thread(producer).start();
        Thread.sleep(10);
        new Thread(consumer).start();
    }*/

}
