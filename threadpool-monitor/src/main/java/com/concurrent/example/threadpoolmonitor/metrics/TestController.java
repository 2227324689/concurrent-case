package com.concurrent.example.threadpoolmonitor.metrics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

//模拟任务的执行
@RestController
public class TestController {

    private final String poolName="first-monitor-thread-pool";
    @Autowired
    ThreadPoolForMonitorManager threadPoolForMonitorManager;

    @GetMapping("/execute")
    public String doExecute(){
        ThreadPoolExecutorForMonitor tpe=threadPoolForMonitorManager.getThreadPoolExecutor(poolName);
        for (int i = 0; i < 100; i++) {
            tpe.execute(()->{
                try {
                    Thread.sleep(new Random().nextInt(4000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        return "success";
    }
}
