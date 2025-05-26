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
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.williams.project.component.redis.service.RedisService;

/**
 * 类描述
 *
 * @author Williams
 * @since 2025-05-26
 */

@Component
@Slf4j
public class InitBean implements InitializingBean {
    @Autowired
    private RedisService redisService;

    @Override
    public void afterPropertiesSet() throws Exception {
        redisService.set("williams","haah2025526");
        log.info("redis set williams haah2025526");
    }
}
