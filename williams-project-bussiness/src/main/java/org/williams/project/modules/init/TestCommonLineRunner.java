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
package org.williams.project.modules.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 类描述
 *
 * @author Williams
 * @since 2025-05-26
 */

@Slf4j
@Component
public class TestCommonLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.info("init 2");
    }
}
