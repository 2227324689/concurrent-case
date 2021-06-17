package org.example;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class ThreadLocalExample {
    public final static ThreadLocal<String> STRING_THREAD_LOCAL=ThreadLocal.withInitial(()->"DEFAULT VALUE");

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+":INITIAL_VALUE->"+STRING_THREAD_LOCAL.get());
        STRING_THREAD_LOCAL.set("Main Thread Value");
        System.out.println(Thread.currentThread().getName()+":BEFORE->"+STRING_THREAD_LOCAL.get());
        Thread t1=new Thread(()->{
            String value=STRING_THREAD_LOCAL.get();
            if(value==null){
                STRING_THREAD_LOCAL.set("T1 Thread Value");
            }
            System.out.println(Thread.currentThread().getName()+":T1->"+STRING_THREAD_LOCAL.get());
        },"t1");
        Thread t2=new Thread(()->{
            String value=STRING_THREAD_LOCAL.get();
            if(value==null){
                STRING_THREAD_LOCAL.set("T2 Thread Value");
            }
            System.out.println(Thread.currentThread().getName()+":T2->"+STRING_THREAD_LOCAL.get());
        },"t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(Thread.currentThread().getName()+":AFTER->"+STRING_THREAD_LOCAL.get());
    }
}
