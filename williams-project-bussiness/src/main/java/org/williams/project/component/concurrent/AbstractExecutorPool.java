package org.williams.project.component.concurrent;

import lombok.Data;


/**
 * @author Williams
 * @date 2023-05-31-20:35
 * @Description
 */
@Data
public abstract class AbstractExecutorPool {

    private int corePoolSize;

    private int maxPoolSize;

    private int queueCapacity;

    private int keepAliveSeconds;

    private String namePrefix;

}