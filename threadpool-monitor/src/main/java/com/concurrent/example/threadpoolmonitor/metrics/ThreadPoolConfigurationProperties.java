package com.concurrent.example.threadpoolmonitor.metrics;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@ConfigurationProperties(prefix = "monitor.threadpool")
@Data
public class ThreadPoolConfigurationProperties {

    private List<ThreadPoolProperties>  executors=new ArrayList<>();

}
