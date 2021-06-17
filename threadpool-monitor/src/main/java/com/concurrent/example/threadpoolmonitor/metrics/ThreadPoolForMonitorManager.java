package com.concurrent.example.threadpoolmonitor.metrics;

import com.concurrent.example.threadpoolmonitor.ResizeLinkedBlockingQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class ThreadPoolForMonitorManager {

    @Autowired
    ThreadPoolConfigurationProperties poolConfigurationProperties;

    private final ConcurrentMap<String,ThreadPoolExecutorForMonitor> threadPoolExecutorForMonitorConcurrentMap=new ConcurrentHashMap<>();

    @PostConstruct
    public void init(){
        poolConfigurationProperties.getExecutors().forEach(threadPoolProperties -> {
            if(!threadPoolExecutorForMonitorConcurrentMap.containsKey(threadPoolProperties.getPoolName())){
                ThreadPoolExecutorForMonitor executorForMonitor=new ThreadPoolExecutorForMonitor(
                        threadPoolProperties.getCorePoolSize(),
                        threadPoolProperties.getMaxmumPoolSize(),
                        threadPoolProperties.getKeepAliveTime(),
                        threadPoolProperties.getUnit(),
                        new ResizeLinkedBlockingQueue<>(threadPoolProperties.getQueueCapacity()),
                        threadPoolProperties.getPoolName());
                threadPoolExecutorForMonitorConcurrentMap.put(threadPoolProperties.getPoolName(),executorForMonitor);
            }
        });
    }

    public ThreadPoolExecutorForMonitor getThreadPoolExecutor(String poolName){
        ThreadPoolExecutorForMonitor threadPoolExecutorForMonitor=threadPoolExecutorForMonitorConcurrentMap.get(poolName);
        if(threadPoolExecutorForMonitor==null){
            throw new RuntimeException("找不到名字为"+poolName+"的线程池");
        }
        return threadPoolExecutorForMonitor;
    }

    public ConcurrentMap<String,ThreadPoolExecutorForMonitor> getThreadPoolExecutorForMonitorConcurrentMap(){
        return this.threadPoolExecutorForMonitorConcurrentMap;
    }
}
