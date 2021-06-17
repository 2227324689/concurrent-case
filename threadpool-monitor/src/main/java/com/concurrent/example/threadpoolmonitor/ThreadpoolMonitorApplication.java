package com.concurrent.example.threadpoolmonitor;

import com.concurrent.example.threadpoolmonitor.metrics.ThreadPoolConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(ThreadPoolConfigurationProperties.class)
@SpringBootApplication
public class ThreadpoolMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThreadpoolMonitorApplication.class, args);
    }

}
