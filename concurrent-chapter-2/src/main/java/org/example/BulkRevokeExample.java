package org.example;

import org.openjdk.jol.info.ClassLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class BulkRevokeExample {

    public static void main(String[] args) throws InterruptedException, IOException {
        //创造100个偏向线程t1的偏向锁
        List<BulkRevokeExample> bulks = new ArrayList<>();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i <100 ; i++) {
                BulkRevokeExample a = new BulkRevokeExample();
                synchronized (a){
                    bulks.add(a);
                }
            }
        });
        t1.start();
        t1.join();
        System.out.println("打印t1线程，bulks中第20个对象的对象头：");
        System.out.println((ClassLayout.parseInstance(bulks.get(19)).toPrintable()));
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 29; i++) {
                BulkRevokeExample a = bulks.get(i);
                synchronized (a) {
                    //分别打印第19次和第20次偏向锁重偏向结果
                    if (i==25||i == 18 || i == 19) {
                        System.out.println("第" + (i + 1) + "次偏向结果");
                        System.out.println((ClassLayout.parseInstance(a).toPrintable()));
                    }
                }
            }
        });
        t2.start();
        System.in.read();
    }
}
