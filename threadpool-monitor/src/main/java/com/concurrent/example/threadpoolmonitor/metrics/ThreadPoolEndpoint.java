package com.concurrent.example.threadpoolmonitor.metrics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@Endpoint(id="thread-pool")
public class ThreadPoolEndpoint {
    @Autowired
    private ThreadPoolForMonitorManager threadPoolForMonitorManager;

    @ReadOperation
    public Map<String,Object> threadPoolsMetric(){
        Map<String,Object> metricMap=new HashMap<>();
        List<Map> threadPools=new ArrayList<>();
        threadPoolForMonitorManager.getThreadPoolExecutorForMonitorConcurrentMap().forEach((k,v)->{
            ThreadPoolExecutorForMonitor tpe=(ThreadPoolExecutorForMonitor) v;
            Map<String,Object> poolInfo=new HashMap<>();
            poolInfo.put("thread.pool.name",k);
            poolInfo.put("thread.pool.core.size",tpe.getCorePoolSize());
            poolInfo.put("thread.pool.largest.size",tpe.getLargestPoolSize());
            poolInfo.put("thread.pool.max.size",tpe.getMaximumPoolSize());
            poolInfo.put("thread.pool.thread.count",tpe.getPoolSize());
            poolInfo.put("thread.pool.max.costTime",tpe.getMaxCostTime());
            poolInfo.put("thread.pool.average.costTime",tpe.getAverageCostTime());
            poolInfo.put("thread.pool.min.costTime",tpe.getMinCostTime());
            poolInfo.put("thread.pool.active.count",tpe.getActiveCount());
            poolInfo.put("thread.pool.completed.taskCount",tpe.getCompletedTaskCount());
            poolInfo.put("thread.pool.queue.name",tpe.getQueue().getClass().getName());
            poolInfo.put("thread.pool.rejected.name",tpe.getRejectedExecutionHandler().getClass().getName());
            poolInfo.put("thread.pool.task.count",tpe.getTaskCount());
            threadPools.add(poolInfo);
        });
        metricMap.put("threadPools",threadPools);
        return metricMap;
    }
}
