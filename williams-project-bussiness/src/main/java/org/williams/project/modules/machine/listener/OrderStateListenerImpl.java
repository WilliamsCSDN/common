package org.williams.project.modules.machine.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;
import org.williams.project.enums.OrderEvent;

import javax.annotation.Resource;

@Component("orderStateListener")
@WithStateMachine(name = "orderStateMachine")
@Slf4j
public class OrderStateListenerImpl {
//    @Resource
//    private OrderMapper orderMapper;

    @OnTransition(source = "CREATED", target = "PAYING")
    public void payTransition(Message<OrderEvent> message) {
//        Order order = (Order) message.getHeaders().get("order");
        log.info("支付，状态机反馈信息：{}",  message.getHeaders().toString());
//        //更新订单
//        order.setStatus(OrderStatus.WAIT_DELIVER.getKey());
//        orderMapper.updateById(order);
        //TODO 其他业务
    }
    @OnTransition(source = "PAYING", target = "PAID")
    public void deliverTransition(Message<OrderEvent> message) {
//        Order order = (Order) message.getHeaders().get("order");
        log.info("准备发货，状态机反馈信息：{}",  message.getHeaders().toString());
        //更新订单
//        order.setStatus(OrderStatus.WAIT_RECEIVE.getKey());
//        orderMapper.updateById(order);
        //TODO 其他业务
    }
    @OnTransition(source = "PAYING", target = "CANCELLED")
    public void receiveTransition(Message<OrderEvent> message) {
//        Order order = (Order) message.getHeaders().get("order");
        log.info("取消支付，状态机反馈信息：{}",  message.getHeaders().toString());
        //更新订单
//        order.setStatus(OrderStatus.FINISH.getKey());
//        orderMapper.updateById(order);
        //TODO 其他业务
    }
}
