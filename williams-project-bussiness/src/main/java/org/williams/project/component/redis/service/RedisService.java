package org.williams.project.component.redis.service;


import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.williams.project.utils.ReturnHandle;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Williams
 * @Description: redis工具类
 * @Date: 2018/7/2 16:53
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String,Object> redisTemplate;

    //=============================common============================

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     */
    public void expire(String key, long time) {
        redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key 键
     * @return 时间(秒) 返回0代表为永久有效
     */
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(Lists.newArrayList(key));
            }
        }
    }

    /**
     * 移除指定key 的过期时间
     *
     * @param key 键
     * @return true 成功 false 失败
     */
    public Boolean persist(String key) {
        return redisTemplate.boundValueOps(key).persist();
    }

    //============================String=============================

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public <T> T get(String key) {
        ValueOperations<String, T> operations = (ValueOperations<String, T>) redisTemplate.opsForValue();
        return operations.get(key);
    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @param unit  时间单位
     */
    public void set(String key, Object value, long time, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, time, unit);
    }

    /**
     * 查询并设置缓存
     *
     * @param key          键
     * @param returnHandle 无缓存时执行方法
     * @param time         时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @param unit         时间单位
     * @return
     */
    public <T> T setReturnHandle(String key, ReturnHandle<T> returnHandle, long time, TimeUnit unit) {
        T o = get(key);
        if (o == null) {
            o = returnHandle.execute();
            if (o != null) {
                redisTemplate.opsForValue().set(key, o, time, unit);
            }
        }
        return o;
    }


    /**
     * 插入并返回key
     *
     * @param key   键
     * @param value 值
     * @return 当key存在时，返回旧key,否则返回null
     */
    public String getAndSet(String key, String value) {
        return (String) redisTemplate.opsForValue().getAndSet(key, value);
    }

    /**
     * 删除
     *
     * @param key 键
     */
    public void del(String key) {
        redisTemplate.opsForValue().getOperations().delete(key);
    }

    /**
     * 递增
     *
     * @param key   键
     * @param delta 要增加几(大于0)【递增因子必须大于0】
     * @return Long
     */
    public Long incr(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     *
     * @param key   键
     * @param delta 要减少几(小于0)【递减因子必须大于0】
     * @return Long
     */
    public Long decr(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    /**
     * 插入并判断（只有key不存在才会set ,否则什么都不做）
     *
     * @param key   键
     * @param value 值
     * @return Boolean
     */
    public Boolean setIfAbsent(String key, String value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    /**
     * 插入并判断（只有key不存在才会set ,否则什么都不做 ,带过期时间）
     *
     * @param key   键
     * @param value 值
     * @return Boolean
     */
    public Boolean setIfAbsent(String key, String value, long time) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, time, TimeUnit.SECONDS);
    }

    /**
     * 批量添加 key (重复的键会覆盖)
     *
     * @param keyAndValue 要批量添加的缓存键值
     */
    public void batchSet(Map<String, String> keyAndValue) {
        redisTemplate.opsForValue().multiSet(keyAndValue);
    }

    /**
     * 批量添加 key-value 只有在键不存在时,才添加
     * map 中只要有一个key存在,则全部不添加
     *
     * @param keyAndValue 要批量添加的缓存键值
     */
    public void batchSetIfAbsent(Map<String, String> keyAndValue) {
        redisTemplate.opsForValue().multiSetIfAbsent(keyAndValue);
    }

    //================================Map=================================

    /**
     * 获取hash表中指定的值
     *
     * @param key  键
     * @param item 项
     * @return 值
     */
    public Object hashGet(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hash表中所有的键
     *
     * @param key   键
     * @param items 项
     * @return List
     */
    public List<Object> hashMultiGet(String key, Collection<Object> items) {
        return redisTemplate.opsForHash().multiGet(key, items);
    }

    /**
     * 获取hashKey对应的所有键值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<Object, Object> hashGetEntries(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * HashSet
     *
     * @param key 键
     * @param map 对应多个键值
     */
    public <T> void hashPutAll(String key, Map<String, T> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * HashSet 并设置时间
     *
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     */
    public void hashPutAll(String key, Map<String, Object> map, long time) {
        redisTemplate.opsForHash().putAll(key, map);
        if (time > 0) {
            expire(key, time);
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     */
    public void hashPut(String key, String item, Object value) {
        redisTemplate.opsForHash().put(key, item, value);
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒)  注意:如果已存在的hash表有时间,这里将会替换原有的时间
     */
    public void hashPut(String key, String item, Object value, long time) {
        redisTemplate.opsForHash().put(key, item, value);
        if (time > 0) {
            expire(key, time);
        }
    }

    /**
     * 删除hash表中的值
     *
     * @param key  键
     * @param item 项
     */
    public void hashDel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键
     * @param item 项
     * @return true 存在 false不存在
     */
    public boolean hashHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * 获取hash表中所有的键
     *
     * @param key 键
     * @return Set
     */
    public Set<Object> hashKeys(String key) {
        return redisTemplate.opsForHash().keys(key);
    }

    /**
     * 获取hash表中所有的键
     *
     * @param key 键
     * @return Set
     */
    public Map<Object, Object> hashEntries(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 获取hash表中所有的值
     *
     * @param key 键
     * @return List
     */
    public List<Object> hashValues(String key) {
        return redisTemplate.opsForHash().values(key);
    }

    /**
     * 获取指定 hash 下面的 键值对 数量
     *
     * @param key 键
     * @return Long
     */
    public Long hashSize(String key) {
        return redisTemplate.opsForHash().size(key);
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     * @return long
     */
    public long hashIncr(String key, String item, long by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * hash递减
     *
     * @param key  键
     * @param item 项
     * @param by   要减少记(小于0)
     * @return
     */
    public long hashDecr(String key, String item, long by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    //============================set=============================

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     * @return Set中的所有值
     */
    public Set<Object> setMembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public Boolean setHasKey(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public Long setAdd(String key, Object... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    /**
     * 将set数据放入缓存
     *
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public Long setAdd(String key, long time, Object... values) {
        Long count = redisTemplate.opsForSet().add(key, values);
        if (time > 0) {
            expire(key, time);
        }
        return count;
    }

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     * @return 缓存长度
     */
    public Long setSize(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public Long setRemove(String key, Object... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }

    /**
     * 随机获取变量中的元素
     *
     * @param key 键
     * @return Object
     */
    public Object setRandomMember(String key) {
        return redisTemplate.opsForSet().randomMember(key);
    }

    /**
     * 随机获取变量中指定个数的元素
     *
     * @param key   键
     * @param count 值
     */
    public void setRandomMembers(String key, long count) {
        redisTemplate.opsForSet().randomMembers(key, count);
    }

    /**
     * 弹出变量中的元素
     *
     * @param key 键
     * @return Object
     */
    public Object setPop(String key) {
        return redisTemplate.opsForSet().pop(key);
    }

    /**
     * 检查给定的元素是否在变量中。
     *
     * @param key 键
     * @param obj 元素对象
     * @return true 存在 false不存在
     */
    public Boolean setIsMember(String key, Object obj) {
        return redisTemplate.opsForSet().isMember(key, obj);
    }

    /**
     * 转移变量的元素值到目的变量。
     *
     * @param key     键
     * @param value   元素对象
     * @param destKey 元素对象
     * @return true 成功 false 失败
     */
    public boolean setMove(String key, String value, String destKey) {
        return BooleanUtils.isTrue(redisTemplate.opsForSet().move(key, value, destKey));
    }

    /**
     * 通过给定的key求2个set变量的差值
     *
     * @param key     键
     * @param destKey 键
     * @return Set
     */
    public Set<Object> difference(String key, String destKey) {
        return redisTemplate.opsForSet().difference(key, destKey);
    }

    //===============================list=================================

    /**
     * 获取list指定区间内的数据
     *
     * @param key   键
     * @param start 开始
     * @param end   结束  0 到 -1代表所有值
     * @return List
     */
    public List<Object> listRange(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 删除list不在指定区间的所有数据
     *
     * @param key   键
     * @param start 开始
     * @param end   结束  0 到 -1代表所有值
     */
    public void listTrim(String key, long start, long end) {
        redisTemplate.opsForList().trim(key, start, end);
    }

    /**
     * 获取list缓存的长度
     *
     * @param key 键
     * @return 缓存的长度
     */
    public Long listSize(String key) {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 通过索引 获取list中的值
     *
     * @param key   键
     * @param index 索引  index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return list中的值
     */
    public Object listIndex(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * 把最后一个参数值放到指定集合的第一个出现中间参数的前面，
     * 如果中间参数值存在的话。
     *
     * @param key
     * @param pivot
     * @param value
     */
    public void listLeftPush(String key, Object pivot, Object value) {
        redisTemplate.opsForList().leftPush(key, pivot, value);
    }

    /**
     * 在list的左边添加元素值
     *
     * @param key   键
     * @param value 值
     */
    public void listLeftPush(String key, Object value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 在list的左边添加元素值
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     */
    public void listLeftPush(String key, Object value, long time) {
        redisTemplate.opsForList().leftPush(key, value);
        if (time > 0) expire(key, time);
    }

    /**
     * 在list的左边批量添加元素值
     *
     * @param key    键
     * @param values 值
     */
    public void listLeftPushAll(String key, List<Object> values) {
        redisTemplate.opsForList().leftPushAll(key, values);
    }

    /**
     * 在list的左边批量添加元素值
     *
     * @param key    键
     * @param values 值
     * @param time   时间(秒)
     */
    public void listLeftPushAll(String key, List<Object> values, long time) {
        redisTemplate.opsForList().leftPushAll(key, values);
        if (time > 0) expire(key, time);
    }

    /**
     * 在list的左边添加元素值（当且仅当key存在时，才会更新key的值。如果key不存在则不会对数据进行任何操作。）
     *
     * @param key   键
     * @param value 值
     */
    public void listLeftPushIfPresent(String key, Object value) {
        redisTemplate.opsForList().leftPushIfPresent(key, value);
    }

    /**
     * 在list的右边添加元素值
     *
     * @param key   键
     * @param value 值
     */
    public void listRightPush(String key, Object value) {
        redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 在list的右边添加元素值
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     */
    public void listRightPush(String key, Object value, long time) {
        redisTemplate.opsForList().rightPush(key, value);
        if (time > 0) expire(key, time);
    }

    /**
     * 在list的右边批量添加元素值
     *
     * @param key   键
     * @param value 值
     */
    public void listRightPushAll(String key, List<Object> value) {
        redisTemplate.opsForList().rightPushAll(key, value);
    }

    /**
     * 在list的右边批量添加元素值
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     */
    public void listRightPushAll(String key, List<Object> value, long time) {
        redisTemplate.opsForList().rightPushAll(key, value);
        if (time > 0) expire(key, time);
    }

    /**
     * 在list的右边添加元素值（当且仅当key存在时，才会更新key的值。如果key不存在则不会对数据进行任何操作。）
     *
     * @param key   键
     * @param value 值
     */
    public void listRightPushIfPresent(String key, Object value) {
        redisTemplate.opsForList().rightPushIfPresent(key, value);
    }

    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     */
    public void listSetByIndex(String key, long index, Object value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    /**
     * 移除N个值为value
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public Long listRemove(String key, long count, Object value) {
        return redisTemplate.opsForList().remove(key, count, value);
    }

    /**
     * 移除集合中的左边第一个元素。
     *
     * @param key 键
     */
    public void leftPop(String key) {
        redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 移除集合中左边的元素在等待的时间里，如果超过等待的时间仍没有元素则退出。
     *
     * @param key 键
     */
    public void leftPop(String key, long timeout, TimeUnit unit) {
        redisTemplate.opsForList().leftPop(key, timeout, unit);
    }

    /**
     * 移除集合中右边的元素。
     *
     * @param key 键
     */
    public void rightPop(String key) {
        redisTemplate.opsForList().rightPop(key);
    }

    /**
     * 移除集合中右边的元素在等待的时间里，如果超过等待的时间仍没有元素则退出。
     *
     * @param key 键
     */
    public void rightPop(String key, long timeout, TimeUnit unit) {
        redisTemplate.opsForList().rightPop(key, timeout, unit);
    }

    //===============================geo=================================

    /**
     * 添加经纬度地址
     *
     * @param key  键
     * @param name 当前经纬度标识
     * @param x    经度
     * @param y    纬度
     * @return Long
     */
    public Long geoAdd(String key, Object name, double x, double y) {
        RedisGeoCommands.GeoLocation<Object> geoLocation = new RedisGeoCommands.GeoLocation<>(name, new Point(x, y));
        return redisTemplate.opsForGeo().add(key, geoLocation);
    }

    /**
     * 获取指定地址范围内坐标集合
     *
     * @param key    键
     * @param name   当前经纬度标识
     * @param value  距离
     * @param metric 距离单位
     * @param count  查询条数
     * @return GeoResults
     */
    public GeoResults<RedisGeoCommands.GeoLocation<Object>> geoNearByPlace(String key, Object name, double value, Metrics metric, long count) {
        Distance distance = new Distance(value, metric);
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance().includeCoordinates().sortAscending();
        if (count > 0) args.limit(count);
        return redisTemplate.opsForGeo().radius(key, name, distance, args);
    }

    /**
     * 获取指定经纬度范围内的坐标集合
     *
     * @param key    键
     * @param x      当前经度
     * @param y      当前纬度
     * @param value  距离
     * @param metric 距离单位
     * @param count  查询条数
     * @return GeoResults
     */
    public GeoResults<RedisGeoCommands.GeoLocation<Object>> geoNearByXY(String key, double x, double y, double value, Metrics metric, long count) {
        Circle circle = new Circle(new Point(x, y), new Distance(value, metric));
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance().includeCoordinates().sortAscending();
        if (count > 0) args.limit(count);
        return redisTemplate.opsForGeo().radius(key, circle, args);
    }

    /**
     * 获取指定地址的经纬度
     *
     * @param key  键
     * @param name 当前经纬度标识
     * @return List
     */
    public List<Point> geoGet(String key, Object... name) {
        return redisTemplate.opsForGeo().position(key, name);
    }

    /**
     * 获取两个地址直接的距离
     *
     * @param key     键
     * @param member1 地址1
     * @param member2 地址2
     * @param metric  距离单位
     * @return Distance
     */
    public Distance geoDistance(String key, Object member1, Object member2, Metrics metric) {
        return redisTemplate.opsForGeo().distance(key, member1, member2, metric);
    }

}