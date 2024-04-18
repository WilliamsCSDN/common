package org.williams.project.component.mq.domain;

import lombok.Data;

/**
 * @author LiuJieBang
 * @date 2024-03-08-18:46
 * @Description
 */
@Data
public class RocketMQMessage {

    private String topic;

    private String tag;

    private Object message;

    private String messageKey;
}
