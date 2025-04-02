/*
 * Copyright (C) 2011-present ShenZhen iBOXCHAIN Information Technology Co.,Ltd.
 *
 * All right reserved.
 *
 * This software is the confidential and proprietary
 * information of iBOXCHAIN Company of China.
 * ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only
 * in accordance with the terms of the contract agreement
 * you entered into with iBOXCHAIN inc.
 */
package org.williams.project;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.williams.project.modules.student.service.StudentService;

/**
 * 类描述
 *
 * @author Williams
 * @since 2025-04-02
 */

public class JmhTest {



    public static void main(String[] args) throws RunnerException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ProjectWebApplication.class);
        StudentService bean = applicationContext.getBean(StudentService.class);

        Options o = new OptionsBuilder()
                .include(StudentService.class.getSimpleName())
                .forks(1)
                .warmupIterations(1)
                .warmupBatchSize(2)
                .measurementIterations(3)
                .measurementBatchSize(4)
                .build();

        new Runner(o).run();

        applicationContext.close();

    }
}
