package org.williams.project.component.concurrent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
@RequiredArgsConstructor
@Slf4j
public class ExecutorConfiguration {

    private final QueryExecutorProperties queryExecutorProperties;

    private final TaskExecutorProperties taskExecutorProperties;

    @Bean(name = "taskExecutor")
    public TaskExecutor taskExecutor() {
        TaskExecutor taskExecutor = initExecutor(taskExecutorProperties);
        log.info("---------- taskExecutor init success ----------");
        return taskExecutor;
    }

    @Bean(name = "queryExecutor")
    public TaskExecutor queryExecutor() {
        TaskExecutor taskExecutor = initExecutor(queryExecutorProperties);
        log.info("---------- queryExecutor init success ----------");
        return taskExecutor;
    }

    private TaskExecutor initExecutor(AbstractExecutorPool abstractExecutorPool) {
        VisibleThreadPoolTaskExecutor executor = new VisibleThreadPoolTaskExecutor();
        // 设置核心线程数
        executor.setCorePoolSize(abstractExecutorPool.getCorePoolSize());
        // 设置最大线程数
        executor.setMaxPoolSize(abstractExecutorPool.getMaxPoolSize());
        // 设置缓冲队列大小
        executor.setKeepAliveSeconds(abstractExecutorPool.getKeepAliveSeconds());
        // 设置线程的最大空闲时间
        executor.setQueueCapacity(abstractExecutorPool.getQueueCapacity());
        // 设置线程名字的前缀
        executor.setThreadNamePrefix(abstractExecutorPool.getNamePrefix());
        // 设置拒绝策略：当线程池达到最大线程数时，如何处理新任务
        // CALLER_RUNS：在添加到线程池失败时会由主线程自己来执行这个任务，默认AbortPolicy
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 线程池增强
        executor.setTaskDecorator(new CustomTaskDecorator());
        // 线程池初始化
        executor.initialize();
        return executor;
    }

}
