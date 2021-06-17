package org.example;

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
public class SimpleDateFormatExample {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static Date parse(String strDate) throws ParseException {
        return sdf.parse(strDate);
    }
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 9; i++) {
            executorService.execute(() -> {
                try {
                    System.out.println(parse("2021-06-15 16:35:20"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
