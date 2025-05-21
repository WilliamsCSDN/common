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
package org.williams.project.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 类描述
 *
 * @author Williams
 * @since 2025-05-20
 */
@Getter
@AllArgsConstructor
public enum OrderStatus {
    CREATED,    // 已创建
    PAYING,     // 支付中
    PAID,       // 已支付
    CANCELLED   // 已取消
    ;

    public static OrderStatus enumByType(String type) {
        for (OrderStatus orderStatus : values()) {
            if (orderStatus.toString().equals(type)) {
                return orderStatus;
            }
        }
        return null;
    }
}
