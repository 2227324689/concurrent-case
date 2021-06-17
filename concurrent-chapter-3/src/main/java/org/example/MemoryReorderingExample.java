package org.example;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class MemoryReorderingExample {
    private static int x=0,y=0;
    private static int a=0,b=0;
    public static void main(String[] args) throws InterruptedException {
        int i=0;
        while(true){
            i++;
            x=0;y=0;
            a=0;b=0;
            Thread t1=new Thread(()->{
                a=1;
                x=b;
            });
            Thread t2=new Thread(()->{
                b=1;
                y=a;
            });
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            String result="第"+i+"次("+x+","+y+")";
            if(x==0&&y==0){
                System.out.println(result);
                break;
            }
        }
    }
}
