package org.williams.project.snowflake;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.williams.project.component.redis.service.RedisService;
import org.williams.project.component.redis.service.RedissonService;
import org.williams.project.constants.RedisConstant;
import org.williams.project.exceptions.SystemException;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author LiuJieBang
 * @date 2023-08-10-20:24
 * @Description
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SnowFlake {

    private final RedissonService redissonService;

    private final RedisService redisService;

    private static Sequence sequence;

    private static final Integer minDatacenterId = 0;

    private static final Integer maxDatacenterId = 31;

    private static final Integer minWorkerId = 0;

    private static final Integer maxWorkerId = 31;

    public static Long getId() {
        return sequence.nextId();
    }


    @PostConstruct
    public void init() {
        initSnowflake(0);
    }

    public void initSnowflake(int retryCount) {
        // 重试3次
        if (retryCount >= 3) {
            sequence = new Sequence();
            return;
        }
        sequence = redissonService.tryLockReturnHandle(RedisConstant.SEQUENCE_LOCK, 10, 5000, TimeUnit.MILLISECONDS, this::getSequence);
        if (Objects.isNull(sequence)) {
            // 延迟
            try {
                Thread.sleep(50L * ++retryCount);
                initSnowflake(retryCount);
            } catch (InterruptedException e) {
                log.error("initSnowflake error:", e);
                throw new SystemException(e);
            }
        }
    }


    public Sequence getSequence() {
        Integer datacenterId = redisService.get(RedisConstant.SEQUENCE_DATACENTER_ID);
        Integer workerId = minWorkerId;
        if (Objects.isNull(datacenterId)) {
            datacenterId = minDatacenterId;
            redisService.set(RedisConstant.SEQUENCE_DATACENTER_ID, minDatacenterId);
            redisService.set(RedisConstant.SEQUENCE_WORKER_ID, minWorkerId);
        } else {
            workerId = redisService.get(RedisConstant.SEQUENCE_WORKER_ID);
            if (++datacenterId > maxDatacenterId) {
                datacenterId = minDatacenterId;
                redisService.set(RedisConstant.SEQUENCE_DATACENTER_ID, datacenterId);
                redisService.incr(RedisConstant.SEQUENCE_WORKER_ID, 1L);
                if (++workerId > maxWorkerId) {
                    //最多支持:1024个实例 ,workerId达到上限雪花ID冲突
                    workerId = minWorkerId;
                    redisService.set(RedisConstant.SEQUENCE_WORKER_ID, workerId);
                }
            } else {
                redisService.incr(RedisConstant.SEQUENCE_DATACENTER_ID, 1L);
            }
        }
        log.info("getSequence workerId:{},datacenterId:{}", workerId, datacenterId);
        return new Sequence(workerId, datacenterId);
    }
}
