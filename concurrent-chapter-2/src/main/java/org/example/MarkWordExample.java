package org.example;

import org.openjdk.jol.info.ClassLayout;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class MarkWordExample {
    private int id;
    private String name;
    public static void main(String[] args) {
        MarkWordExample example=new MarkWordExample();
        System.out.println(ClassLayout.parseInstance(example).toPrintable());
    }
}
