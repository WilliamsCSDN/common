package org.williams.project.component.concurrent;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>
 *     数据同步线程池
 * </p>
 *
 * @author Shexy
 * @date 2023-08-15
 */
@Data
@Component
@ConfigurationProperties(prefix = "async.executor.task")
public class TaskExecutorProperties extends AbstractExecutorPool {
}
