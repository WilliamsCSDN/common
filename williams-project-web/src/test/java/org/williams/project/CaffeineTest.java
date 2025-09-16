package org.williams.project;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ProjectWebApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class CaffeineTest {

    @Test
    public void xxtest() throws InterruptedException {
        log.info("caffeine test");

        Cache<String, Map> a = Caffeine.newBuilder()
                .maximumSize(10)
                .expireAfterWrite(5, TimeUnit.SECONDS)
                .recordStats()
                .build();

        a.put("1", new HashMap(){{put("1", "1");}});
        a.put("2", new HashMap(){{put("2", "2");}});
        a.put("3", new HashMap(){{put("3", "3");}});
        a.put("4", new HashMap(){{put("4", "4");}});
        a.put("5", new HashMap(){{put("5", "5");}});
        a.put("6", new HashMap(){{put("6", "6");}});
        a.put("7", new HashMap(){{put("7", "7");}});
        a.put("8", new HashMap(){{put("8", "8");}});
        a.put("9", new HashMap(){{put("9", "9");}});
        a.put("10", new HashMap(){{put("10", "10");}});
        log.info("size: {}", a.estimatedSize());

        a.put("11", new HashMap(){{put("11", "11");}});
        log.info("size: {}", a.estimatedSize());
        a.asMap().forEach((k, v) -> log.info("key: {}, value: {}", k, v));


        Thread.sleep(2000);
//        log.info("size: {}", a.estimatedSize());
        a.asMap().forEach((k, v) -> log.info("key: {}, value: {}", k, v));

        a.put("12", new HashMap(){{put("12", "12");}});
        a.get("10", k -> new HashMap(){{put("10", "10");}});
        log.info("size: {}", a.estimatedSize());
        a.asMap().forEach((k, v) -> log.info("key: {}, value: {}", k, v));

    }
}
