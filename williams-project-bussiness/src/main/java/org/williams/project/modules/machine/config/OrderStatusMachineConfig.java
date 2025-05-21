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
package org.williams.project.modules.machine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.williams.project.enums.OrderEvent;
import org.williams.project.enums.OrderStatus;

import java.util.EnumSet;

/**
 * 类描述
 *
 * @author Williams
 * @since 2025-05-20
 */
@Configuration
@EnableStateMachine(name = "orderStateMachine")
public class OrderStatusMachineConfig extends EnumStateMachineConfigurerAdapter<OrderStatus, OrderEvent> {


    @Override
    public void configure(StateMachineStateConfigurer<OrderStatus, OrderEvent> states) throws Exception {
        states.withStates()
                .initial(OrderStatus.CREATED)
                .states(EnumSet.allOf(OrderStatus.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStatus, OrderEvent> transitions) throws Exception {
        transitions
                .withExternal()
                .source(OrderStatus.CREATED).target(OrderStatus.PAYING)
                .event(OrderEvent.PAY)
                .action(payAction())
                .and()
                .withExternal()
                .source(OrderStatus.PAYING).target(OrderStatus.PAID)
                .event(OrderEvent.SUCCESS)
                .action(successAction())
                .and()
                .withExternal()
                .source(OrderStatus.PAYING).target(OrderStatus.CANCELLED)
                .event(OrderEvent.FAIL)
                .action(failAction());
    }

    @Bean
    public Action<OrderStatus, OrderEvent> payAction() {
        return context -> {
            System.out.println("订单开始支付: " + context.getMessageHeader("orderId"));
        };
    }

    @Bean
    public Action<OrderStatus, OrderEvent> successAction() {
        return context -> {
            System.out.println("订单支付成功，准备发货");
        };
    }

    @Bean
    public Action<OrderStatus, OrderEvent> failAction() {
        return context -> {
            System.out.println("订单支付失败，已取消");
        };
    }
}
