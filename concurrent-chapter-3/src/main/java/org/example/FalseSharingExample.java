package org.example;

import sun.misc.Contended;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class FalseSharingExample implements Runnable{
    public final static long ITERATIONS = 500L * 1000L * 100L;
    private int arrayIndex = 0;

    private static ValueNoPadding[] longs;
    public FalseSharingExample(final int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }
    public static void main(final String[] args) throws Exception {
        for(int i = 1; i < 10; i++){
            System.gc();
            final long start = System.currentTimeMillis();
            runTest(i);
            System.out.println(i + " Threads, duration = " + (System.currentTimeMillis() - start));
        }
    }
    private static void runTest(int NUM_THREADS) throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];
        longs = new ValueNoPadding[NUM_THREADS];
        for (int i = 0; i < longs.length; i++) {
            longs[i] = new ValueNoPadding();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new FalseSharingExample(i));
        }
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
    }
    @Override
    public void run() {
        long i = ITERATIONS + 1;
        while (0 != --i) {
            longs[arrayIndex].value = 0L;
        }
    }
    public final static class ValuePadding {
        protected long p1, p2, p3, p4, p5, p6, p7;
        protected volatile long value = 0L;
        protected long p9, p10, p11, p12, p13, p14,p15;
    }

    public final static class ValueNoPadding {
        @Contended("group0")
        protected volatile long value = 0L;
        @Contended("group0")
        protected volatile long value1=0L;
        protected volatile long value2=0L;
    }
}
