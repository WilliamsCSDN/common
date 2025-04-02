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
package org.williams.project.netty.pojo;

import java.util.Date;

/**
 * 类描述
 *
 * @author Williams
 * @since 2025-03-05
 */

public class UnixTime {

    private final long value;

    public UnixTime() {
        this.value = (System.currentTimeMillis() / 1000L) + 2208988800L;
    }

    public UnixTime(long value) {
        this.value = value;
    }

    public long value() {
        return value;
    }


    public Date convert(){
        return new Date((value - 2208988800L) * 1000L);
    }
}
