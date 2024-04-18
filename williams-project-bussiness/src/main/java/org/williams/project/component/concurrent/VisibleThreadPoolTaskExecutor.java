package org.williams.project.component.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
public class VisibleThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {

    public void info() {
        ThreadPoolExecutor executor = getThreadPoolExecutor();
        String info = "线程池" + this.getThreadNamePrefix() +
                "中，总任务数为 " + executor.getTaskCount() +
                " ，已处理完的任务数为 " + executor.getCompletedTaskCount() +
                " ，目前正在处理的任务数为 " + executor.getActiveCount() +
                " ，缓冲队列中任务数为 " + executor.getQueue().size();
        log.info(info);
    }

    @Override
    public void execute(Runnable task) {
        info();
        super.execute(task);
    }

    @Override
    public void execute(Runnable task, long startTimeout) {
        info();
        this.execute(task, startTimeout);
    }

    @Override
    public Future<?> submit(Runnable task) {
        info();
        return super.submit(task);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        info();
        return super.submit(task);
    }

    @Override
    public ListenableFuture<?> submitListenable(Runnable task) {
        info();
        return super.submitListenable(task);
    }

    @Override
    public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
        info();
        return super.submitListenable(task);
    }
}
