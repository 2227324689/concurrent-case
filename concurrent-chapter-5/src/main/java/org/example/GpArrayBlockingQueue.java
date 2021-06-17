package org.example;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class GpArrayBlockingQueue<E> {
    //重入锁
    private ReentrantLock lock=new ReentrantLock();
    //阻塞生产者线程
    private Condition notFull=lock.newCondition();
    //阻塞消费者线程
    private Condition notEmpty=lock.newCondition();

    private E[] elements; //存储元素的数组

    private int count; //统计当前总的元素个数

    private int headIndex; //队列头索引
    private int tailIndex; //队列尾索引

    public GpArrayBlockingQueue(int capcity){
        if(capcity<=0){
            throw new IllegalArgumentException("capcity cannot be less than zero");
        }
        this.elements=(E[])new Object[capcity];
    }

    public void put(E e) throws InterruptedException {
        lock.lock();
        try{
            while(count==elements.length){
                notFull.await(); //阻塞生产者线程。
            }
            elements[tailIndex]=e;
            if(++tailIndex==elements.length){
                tailIndex=0;
            }
            ++count;
            notEmpty.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public E take() throws InterruptedException {
        lock.lock();
        try{
            while(count==0){
                notEmpty.await();
            }
            E e=elements[headIndex];
            if(++headIndex==elements.length){
                headIndex=0;
            }
            --count;
            notFull.signalAll();
            return e;
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        GpArrayBlockingQueue<String> abq=new GpArrayBlockingQueue<>(3);
        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                try {
                    String element="element"+i;
                    abq.put(element);
                    System.out.println("生产一个消息："+element);
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(()->{
            for (;;) {
                try {
                    String element=abq.take();
                    System.out.println("消费一个消息："+element);
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
