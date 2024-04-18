package org.williams.project.modules.student.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.williams.project.component.redis.service.RedisService;
import org.williams.project.constants.RedisConstant;
import org.williams.project.constants.RocketConstant;

import java.util.Objects;

@Slf4j
@Component
@RocketMQMessageListener(topic = RocketConstant.DEAD_TOPIC_COMMON_MESSAGE, consumerGroup = RocketConstant.DEAD_GROUP_WEICUN_OFFICIAL_MESSAGE)
public class CommonMessageDeadConsumer implements RocketMQListener<MessageExt> {

    @Autowired
    private RedisService redisService;

    @Override
    public void onMessage(MessageExt messageExt) {
        log.info("CommonMessageDeadConsumer onMessage message: {}", messageExt);
        log.info("CommonMessageDeadConsumer onMessage message: {}", new String(messageExt.getBody()));
        customerFailRetry(new String(messageExt.getBody()));
    }

    /**
     * 死信后直接投递redis 缓存3d
     */
    private void customerFailRetry(String body){
        redisService.listRightPush(RedisConstant.MQ_MESSAGE_CUSTOMER_RETRY, body);
        Long expire = redisService.getExpire(RedisConstant.MQ_MESSAGE_CUSTOMER_RETRY);
        if (Objects.isNull(expire) || expire < 0){
            redisService.expire(RedisConstant.MQ_MESSAGE_CUSTOMER_RETRY, RedisConstant.THREE_DAY);
        }
    }
}
