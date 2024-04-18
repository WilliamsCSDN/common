package org.williams.project.component.concurrent;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author LiuJieBang
 * @date 2023-05-31-20:35
 * @Description
 */
@Data
@Component
@ConfigurationProperties(prefix = "async.executor.query")
public class QueryExecutorProperties extends AbstractExecutorPool {

}
