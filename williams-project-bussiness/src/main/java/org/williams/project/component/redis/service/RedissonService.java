package org.williams.project.component.redis.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import org.williams.project.utils.Handle;
import org.williams.project.utils.ReturnHandle;

import java.util.concurrent.TimeUnit;

/**
 * @author LiuJieBang
 * @date 2023-04-26-10:11
 * @Description redisson工具类
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RedissonService {

    private final RedissonClient redissonClient;

    /**
     * 加锁-阻塞
     *
     * @param key
     * @return
     */
    public RLock lock(String key) {
        RLock lock = redissonClient.getLock(key);
        lock.lock();
        return lock;
    }

    /**
     * 带超时的锁-阻塞
     *
     * @param key
     * @param timeout 超时时间 单位：秒
     */
    public RLock lock(String key, int timeout) {
        RLock lock = redissonClient.getLock(key);
        lock.lock(timeout, TimeUnit.SECONDS);
        return lock;
    }

    /**
     * 带超时的锁-阻塞
     *
     * @param key
     * @param unit    时间单位
     * @param timeout 超时时间
     */
    public RLock lock(String key, TimeUnit unit, int timeout) {
        RLock lock = redissonClient.getLock(key);
        lock.lock(timeout, unit);
        return lock;
    }

    /**
     * 尝试获取锁-非阻塞
     *
     * @param key
     * @param waitTime 最多等待时间
     * @return
     */
    public boolean tryLock(String key, int waitTime) {
        RLock lock = redissonClient.getLock(key);
        try {
            return lock.tryLock(waitTime, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            return false;
        }
    }

    /**
     * 尝试获取锁-非阻塞
     *
     * @param key
     * @param waitTime  最多等待时间
     * @param leaseTime 上锁后自动释放锁时间
     * @return
     */
    public boolean tryLock(String key, int waitTime, int leaseTime) {
        RLock lock = redissonClient.getLock(key);
        try {
            return lock.tryLock(waitTime, leaseTime, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            return false;
        }
    }

    /**
     * 尝试获取锁-非阻塞
     *
     * @param key
     * @param waitTime  最多等待时间
     * @param leaseTime 上锁后自动释放锁时间
     * @param unit      时间单位
     * @return
     */
    public boolean tryLock(String key, int waitTime, int leaseTime, TimeUnit unit) {
        RLock lock = redissonClient.getLock(key);
        try {
            return lock.tryLock(waitTime, leaseTime, unit);
        } catch (InterruptedException e) {
            return false;
        }
    }

    /**
     * 释放锁
     *
     * @param key
     */
    public void unlock(String key) {
        RLock lock = redissonClient.getLock(key);
        lock.unlock();
    }

    /**
     * 加锁并执行有返回值方法
     *
     * @param key
     * @param waitTime
     * @param leaseTime
     * @param unit
     * @param handle
     * @param <T>
     * @return
     */
    public <T> T tryLockReturnHandle(String key, int waitTime, int leaseTime, TimeUnit unit, ReturnHandle<T> handle) {
        RLock lock = redissonClient.getLock(key);
        boolean tryLock = false;
        try {
            tryLock = lock.tryLock(waitTime, leaseTime, unit);
            if (tryLock) {
                return handle.execute();
            }
        } catch (InterruptedException e) {
            log.error("tryLockReturnHandle error:", e);
        } finally {
            if (tryLock && lock.isHeldByCurrentThread() &&  lock.isLocked()) {
                lock.unlock();
            }
        }
        return null;
    }

    /**
     * 加锁并执行有无返回值方法
     *
     * @param key
     * @param waitTime
     * @param leaseTime
     * @param unit
     * @param handle
     * @return
     */
    public void tryLockHandle(String key, int waitTime, int leaseTime, TimeUnit unit, Handle handle) {
        RLock lock = redissonClient.getLock(key);
        boolean tryLock = false;
        try {
            tryLock = lock.tryLock(waitTime, leaseTime, unit);
            if (tryLock) {
                handle.execute();
            }
        } catch (InterruptedException e) {
            log.error("tryLockHandle error:", e);
        } finally {
            if (tryLock && lock.isHeldByCurrentThread() &&  lock.isLocked()) {
                lock.unlock();
            }
        }
    }

}
