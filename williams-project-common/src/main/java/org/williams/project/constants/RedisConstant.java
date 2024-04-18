package org.williams.project.constants;

public class RedisConstant {

    public static final Integer THREE_DAY = 259200;


    /**
     * 全局前缀
     */
    public static final String PREFIX = "template:";
    /**
     * 雪花sequence datacenterId
     */
    public static final String SEQUENCE_DATACENTER_ID = "sequence:datacenterId";
    /**
     * 雪花sequence workerId
     */
    public static final String SEQUENCE_WORKER_ID = "sequence:workerId";
    /**
     * 雪花sequence dataId 锁
     */
    public static final String SEQUENCE_LOCK = PREFIX + "sequence-lock";

    /**
     * 公共MQ消息生产者失败重试
     */
    public static final String MQ_MESSAGE_PRODUCER_RETRY="mq-message:producer-retry";

    /**
     * 公共MQ消息消费者失败重试
     */
    public static final String MQ_MESSAGE_CUSTOMER_RETRY="mq-message:customer-retry";


    /**
     * mq消息状况列表缓存key
     */
    public static final String MESSAGE_LIST_KEY_USER_ID="message:list:key:";
}
