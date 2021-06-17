package org.example.blockqueue.third;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class OrderDelayedTask implements Delayed {

    private String orderId;
    private long start=System.currentTimeMillis();
    private long time;

    public OrderDelayedTask(String orderId, long time) {
        this.orderId = orderId;
        this.time = time;
    }

    /**
     * 获得延迟时间(用过期时间-当前时间)
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert((start+time)-System.currentTimeMillis(),TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return (int)(this.getDelay(TimeUnit.MILLISECONDS)-o.getDelay(TimeUnit.MILLISECONDS));
    }

    

    @Override
    public String toString() {
        return "OrderDelayedTask{" +
                "orderId='" + orderId + '\'' +
                ", start=" + start +
                ", time=" + time +
                '}';
    }
}
