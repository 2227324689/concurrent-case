package org.example;

import lombok.extern.slf4j.Slf4j;
import sun.misc.VM;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
@Slf4j
public class CompletionStackExample {
    private static final sun.misc.Unsafe UNSAFE;
    private static final long SEED;
    private static final long PROBE;
    private static final long SECONDARY;
    static {
        try {
            UNSAFE = sun.misc.Unsafe.getUnsafe();
            Class<?> tk = Thread.class;
            SEED = UNSAFE.objectFieldOffset
                    (tk.getDeclaredField("threadLocalRandomSeed"));
            PROBE = UNSAFE.objectFieldOffset
                    (tk.getDeclaredField("threadLocalRandomProbe"));
            SECONDARY = UNSAFE.objectFieldOffset
                    (tk.getDeclaredField("threadLocalRandomSecondarySeed"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*CompletableFuture<String> baseFuture=CompletableFuture.completedFuture("Base Future");
        CompletableFuture cf=baseFuture.thenApply(r->null);
       *//* baseFuture.thenAccept(r->log.info(r)).thenAccept(Void->log.info("void"));
        baseFuture.thenApply(r->"Apply Message").thenAccept(r->log.info("result:"+r));
        baseFuture.whenComplete((r,th)->log.info("finish"));*//*
        System.out.println(cf.get());
        baseFuture.join()*/
        System.out.println(nextSecondarySeed());
    }
    static final int nextSecondarySeed() {
        int r;
        Thread t = Thread.currentThread();
        if ((r = UNSAFE.getInt(t, SECONDARY)) != 0) {
            r ^= r << 13;   // xorshift
            r ^= r >>> 17;
            r ^= r << 5;
        }
        else {
            localInit();
            if ((r = (int)UNSAFE.getLong(t, SEED)) == 0) {
                r = 1; // avoid zero
            }
        }
        UNSAFE.putInt(t, SECONDARY, r);
        return r;
    }
    private static final int PROBE_INCREMENT = 0x9e3779b9;
    private static final long SEEDER_INCREMENT = 0xbb67ae8584caa73bL;
    private static final AtomicLong seeder = new AtomicLong(initialSeed());

    private static final AtomicInteger probeGenerator =
            new AtomicInteger();
    static final void localInit() {
        int p = probeGenerator.addAndGet(PROBE_INCREMENT);
        int probe = (p == 0) ? 1 : p; // skip 0
        long seed = mix64(seeder.getAndAdd(SEEDER_INCREMENT));
        Thread t = Thread.currentThread();
        UNSAFE.putLong(t, SEED, seed);
        UNSAFE.putInt(t, PROBE, probe);
    }
    private static long mix64(long z) {
        z = (z ^ (z >>> 33)) * 0xff51afd7ed558ccdL;
        z = (z ^ (z >>> 33)) * 0xc4ceb9fe1a85ec53L;
        return z ^ (z >>> 33);
    }
    private static long initialSeed() {
        String sec = VM.getSavedProperty("java.util.secureRandomSeed");
        if (Boolean.parseBoolean(sec)) {
            byte[] seedBytes = java.security.SecureRandom.getSeed(8);
            long s = (long)(seedBytes[0]) & 0xffL;
            for (int i = 1; i < 8; ++i) {
                s = (s << 8) | ((long)(seedBytes[i]) & 0xffL);
            }
            return s;
        }
        return (mix64(System.currentTimeMillis()) ^
                mix64(System.nanoTime()));
    }
}

