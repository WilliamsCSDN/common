package org.williams.project.constants;

public class RocketConstant {
    // \\\\\\\\\\\\\\\\\\\\\\\\\ 公用 \\\\\\\\\\\\\\\\\\\\\\\\\
    /**
     * 死信前缀
     */
    public static final String DLQ_PREFIX = "%DLQ%";

    /**
     * 重试次数
     */
    public static final int RETRY_TIMES = 2;


    /**
     * 消息代办
     */
    public static final String TOPIC_COMMON_MESSAGE = "TOPIC_COMMON_MESSAGE";
    public static final String GROUP_COMMON_MESSAGE = "CG_WEICUN_OFFICIAL_MESSAGE";
    public static final String TAG_COMMON_MESSAGE = "TAG_CREATE";
    public static final String DEAD_TOPIC_COMMON_MESSAGE = DLQ_PREFIX + GROUP_COMMON_MESSAGE;
    public static final String DEAD_GROUP_WEICUN_OFFICIAL_MESSAGE = "CG_WEICUN_OFFICIAL_DEAD_MESSAGE";





}
