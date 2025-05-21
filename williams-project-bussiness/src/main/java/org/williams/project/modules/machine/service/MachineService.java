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
package org.williams.project.modules.machine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;
import org.williams.project.enums.OrderEvent;
import org.williams.project.enums.OrderStatus;

import javax.annotation.Resource;

/**
 * 类描述
 *
 * @author Williams
 * @since 2025-05-20
 */
@Service
public class MachineService {

    @Autowired
    private StateMachine<OrderStatus, OrderEvent> orderStateMachine;

    @Resource(name = "orderRedisPersister")
    private StateMachinePersister<OrderStatus, OrderEvent, String> persister;


    public void createOrder(String orderId) throws Exception {
        orderStateMachine.start();
        System.out.println(orderStateMachine.getState().getId());
        persister.persist(orderStateMachine, orderId);

    }

    public void payOrder(String orderId) throws Exception {
        orderStateMachine.getExtendedState().getVariables().put("orderId", orderId);
        persister.restore(orderStateMachine, orderId);
        orderStateMachine.sendEvent(MessageBuilder
                .withPayload(OrderEvent.PAY)
                .setHeader("orderId", orderId)
                .build());
        System.out.println(orderStateMachine.getState().getId());
        persister.persist(orderStateMachine, orderId);

    }

    public void handlePaymentResult(String orderId, boolean success) throws Exception {
        orderStateMachine.getExtendedState().getVariables().put("orderId", orderId);
        persister.restore(orderStateMachine, orderId);
        OrderEvent orderEvent = success ? OrderEvent.SUCCESS : OrderEvent.FAIL;
        orderStateMachine.sendEvent(MessageBuilder
                .withPayload(orderEvent)
                .setHeader("orderId", orderId)
                .build());
        System.out.println(orderStateMachine.getState().getId());
        persister.persist(orderStateMachine, orderId);
    }
}
