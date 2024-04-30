package org.williams.project.component.concurrent;


import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.RunnableWrapper;
import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;

import java.util.Map;
import java.util.Optional;

/**
 * @author Williams
 * @date 2023-05-31-20:35
 * @Description
 */
@Slf4j
public class CustomTaskDecorator implements TaskDecorator {

    @Override
    public Runnable decorate(Runnable runnable) {
        try {
            Optional<Map<String, String>> contextMapOptional = Optional.ofNullable(MDC.getCopyOfContextMap());
            return () -> {
                try {
                    contextMapOptional.ifPresent(MDC::setContextMap);
                    RunnableWrapper.of(runnable).run();
                } catch (Exception e) {
                    log.error("async executor error:", e);
                } finally {
                    MDC.clear();
                }
            };
        } catch (Exception e) {
            return runnable;
        }
    }
}