package org.williams.project.component.mq;

import com.alibaba.fastjson.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.williams.project.component.mq.domain.RocketMQMessage;
import org.williams.project.component.redis.service.RedisService;
import org.williams.project.constants.RedisConstant;
import org.williams.project.exceptions.ServiceException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author LiuJieBang
 * @date 2024-03-08-14:58
 * @Description
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class RocketMQService {

    private final RocketMQTemplate rocketMQTemplate;

    private final RedisService redisService;

    /**
     * 发送消息
     * 发送方只负责发送消息，不等待服务端返回响应且没有回调函数触发，即只发送请求不等待应答。
     * 此方式发送消息的过程耗时非常短，一般在微秒级别。适用于某些耗时非常短，但对可靠性要求并不高的场景，例如日志收集。
     *
     * @param rocketMQMessage
     */
    public void sendMessage(RocketMQMessage rocketMQMessage) {
        checkMessageParams(rocketMQMessage);
        Map<String, Object> map = new HashMap<>();
        map.put(RocketMQHeaders.KEYS, rocketMQMessage.getMessageKey());
        rocketMQTemplate.convertAndSend(rocketMQMessage.getTopic() + ":" + rocketMQMessage.getTag(), JSON.toJSONString(rocketMQMessage.getMessage()), map);
    }

    /**
     * 发送同步消息
     * 等待服务端响应，保证生产者端收到应答，保证消息不丢失，适用于某些对消息丢失要求非常严格的场景，例如交易系统。
     *
     * @param rocketMQMessage
     */
    public boolean sendSyncMessage(RocketMQMessage rocketMQMessage) {
        checkMessageParams(rocketMQMessage);
        try {
            SendResult sendResult = rocketMQTemplate.syncSend(rocketMQMessage.getTopic() + ":" + rocketMQMessage.getTag(), MessageBuilder.withPayload(rocketMQMessage.getMessage()).setHeader(RocketMQHeaders.KEYS, rocketMQMessage.getMessageKey()).build());
            log.info("sendSyncMessage sendResult:{}", sendResult);
            if (Objects.nonNull(sendResult) && sendResult.getSendStatus() == SendStatus.SEND_OK) {
                return true;
            }
        }catch (Exception e) {
            log.error("sendSyncMessage error:", e);
            producerFailRetry(rocketMQMessage);
            throw new ServiceException("sendSyncMessage error");
        }
        producerFailRetry(rocketMQMessage);
        return false;
    }

    /**
     * 发生异步消息
     * 发送消息，异步获取服务端响应，保证消息不丢失，和最终一致性，适用于某些对强一致性要求不高的场景，例如消息推送。
     *
     * @param rocketMQMessage
     * @return
     */
    public void sendAsyncMessage(RocketMQMessage rocketMQMessage) {
        checkMessageParams(rocketMQMessage);
        rocketMQTemplate.asyncSend(rocketMQMessage.getTopic() + ":" + rocketMQMessage.getTag(), MessageBuilder.withPayload(rocketMQMessage.getMessage()).setHeader(RocketMQHeaders.KEYS, rocketMQMessage.getMessageKey()).build(), new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("sendAsyncMessage onSuccess:{}", sendResult);
                if (sendResult.getSendStatus() != SendStatus.SEND_OK) {
                    producerFailRetry(rocketMQMessage);
                }
            }

            @Override
            public void onException(Throwable throwable) {
                log.error("sendAsyncMessage onException:", throwable);
                producerFailRetry(rocketMQMessage);
            }
        });
    }

    private void checkMessageParams(RocketMQMessage rocketMQMessage) {
        log.info("checkMessageParams :{}", JSON.toJSONString(rocketMQMessage));
        if (Objects.isNull(rocketMQMessage)) {
            throw new ServiceException("rocketMQMessage is null");
        }
        if (StringUtils.isBlank(rocketMQMessage.getTopic())) {
            throw new ServiceException("rocketMQMessage topic is null");
        }
        if (StringUtils.isBlank(rocketMQMessage.getTag())) {
            throw new ServiceException("rocketMQMessage tag is null");
        }
        if (Objects.isNull(rocketMQMessage.getMessage())) {
            throw new ServiceException("rocketMQMessage message is null");
        }
        if (Objects.isNull(rocketMQMessage.getMessageKey())) {
            rocketMQMessage.setMessageKey(String.valueOf(System.currentTimeMillis()));
        }
    }

    private void producerFailRetry(RocketMQMessage rocketMQMessage){
        redisService.listRightPush(RedisConstant.MQ_MESSAGE_PRODUCER_RETRY, JSON.toJSONString(rocketMQMessage));
        Long expire = redisService.getExpire(RedisConstant.MQ_MESSAGE_PRODUCER_RETRY);
        if (Objects.isNull(expire) || expire < 0){
            redisService.expire(RedisConstant.MQ_MESSAGE_PRODUCER_RETRY, RedisConstant.THREE_DAY);
        }
    }
}
