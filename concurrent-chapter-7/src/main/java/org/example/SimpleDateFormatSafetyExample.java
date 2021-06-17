package org.example;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class SimpleDateFormatSafetyExample {
    private static final String DATEFORMAT="yyyy-MM-dd HH:mm:ss";
    private static ThreadLocal<DateFormat> dateFormatThreadLocal=new ThreadLocal<>();
    private static DateFormat getDateFormat(){ //每次从threadlocal中获取SimpleDateFormat实例
        DateFormat df=dateFormatThreadLocal.get();
        if(df==null){
            df=new SimpleDateFormat(DATEFORMAT);
            dateFormatThreadLocal.set(df);
        }
        return df;
    }
    public static Date parse(String strDate) throws ParseException {
        return getDateFormat().parse(strDate);
    }
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 9; i++) {
            executorService.execute(() -> {
                try {
                    System.out.println(parse("2021-06-16 16:35:20"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
