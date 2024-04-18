package org.williams.project.component.redis.config;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author LiuJieBang
 * @date 2023-04-25-20:53
 * @Description redisson配置类
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class RedissonConfiguration {

    private final RedisProperties redisProperties;


    /**
     * 单机模式自动装配
     * @return
     */
    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        //如果和小程序共用一个redis实例，需要注释掉序列化方式这里，否则会出现两边序列化方式不同，取值问题
        config.setCodec(new StringCodec());
        config.useSingleServer()
                .setDatabase(redisProperties.getDatabase())
                .setAddress("redis://"+redisProperties.getHost()+":"+redisProperties.getPort())
                .setPassword(redisProperties.getPassword())
                .setTimeout((int) redisProperties.getTimeout().toMillis());
        return Redisson.create(config);
    }
}
