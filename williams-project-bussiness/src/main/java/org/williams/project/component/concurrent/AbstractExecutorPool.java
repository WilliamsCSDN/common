package org.williams.project.component.concurrent;

import lombok.Data;


@Data
public abstract class AbstractExecutorPool {

    private int corePoolSize;

    private int maxPoolSize;

    private int queueCapacity;

    private int keepAliveSeconds;

    private String namePrefix;

}