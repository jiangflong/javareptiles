package org.feilong.reptiles.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

@Configuration
public class ExcutorConfig {
    @Bean(value = "downloadService")
    public ExecutorService downloadService() {
        ThreadFactory factory = new ThreadFactoryBuilder()
                .setNameFormat("download-pool-%d").build();
        ExecutorService executorService = new ThreadPoolExecutor(
                30, 50, 0L, TimeUnit.MICROSECONDS,
                new ArrayBlockingQueue<Runnable>(100), factory
        );
        return executorService;
    }
}
