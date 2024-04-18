package org.williams.project.modules.student.consumer;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.williams.project.component.redis.service.RedisService;
import org.williams.project.constants.RocketConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Williams
 * @date 2024-03-08-14:22
 * @Description
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = RocketConstant.TOPIC_COMMON_MESSAGE, consumerGroup = RocketConstant.GROUP_COMMON_MESSAGE ,selectorExpression = RocketConstant.TAG_COMMON_MESSAGE, maxReconsumeTimes = RocketConstant.RETRY_TIMES)
public class CommonMessageConsumer implements RocketMQListener<MessageExt> {

    @Autowired
    private RedisService redisService;

    @Override
    public void onMessage(MessageExt messageExt) {
        log.info("CommonMessageConsumer origin messageExt:{}", messageExt);
        log.info("CommonMessageConsumer origin messageExtBody:{}", new String(messageExt.getBody()));

//        CommonMessageSaveReq message = JSON.parseObject(messageExt.getBody(), CommonMessageSaveReq.class);
//        log.info("CommonMessageConsumer convertToCommonMessage:{}", message);

//        if (message != null){
//            // 删除未读列表缓存
//            redisService.del(RedisConstant.MESSAGE_LIST_KEY_USER_ID + message.getUserId());
//            // 消费前置校验
//            if(preCheckAndExist(Lists.newArrayList(message))){
//                log.info("CommonMessageCommentConsumer 消费失败 messages:{}", message);
//                return;
//            };
//
//            // 保存用户已读 && 添加消息代办
//            commonMessageService.saveCommonMessageBatch(Lists.newArrayList(message));
//        }
    }


//    private boolean preCheckAndExist(List<CommonMessageSaveReq> messages){
//        List<CommonMessageOnlyReq> checkReq = new ArrayList<>();
//        for (CommonMessageSaveReq message : messages) {
//            if (SourceActionEnum.enumByType(message.getSourceAction()) == null){
//                log.info("CommonMessageCommentConsumer 消费来源 不存在错误");
//                return Boolean.TRUE;
//            }
//            switch (SourceActionEnum.enumByType(message.getSourceAction())){
//                case COMMIT:
//                case REPLY:
//                    checkReq.add(CommonMessageOnlyReq.builder()
//                            .eventType(message.getEventType())
//                            .sourceId(message.getSourceId())
//                            .sourceAction(message.getSourceAction()).build());
//                    break;
//                case THUMBS:
//                    checkReq.add(CommonMessageOnlyReq.builder()
//                            .eventType(message.getEventType())
//                            .targetId(message.getTargetId())
//                            .sourceUserId(message.getSourceUserId())
//                            .sourceAction(message.getSourceAction()).build());
//                    break;
//                default:
//                    log.info("CommonMessageCommentConsumer 消费来源 不存在错误");
//                    return Boolean.TRUE;
//            }
//        }
//        return commonMessageService.getExistMessageBatch(checkReq);
//    }
}
