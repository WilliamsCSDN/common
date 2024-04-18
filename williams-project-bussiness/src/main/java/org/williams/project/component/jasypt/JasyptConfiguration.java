package org.williams.project.component.jasypt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Williams
 * @date 2023-04-26-15:03
 * @Description
 */
@Slf4j
@Configuration
public class JasyptConfiguration {


    @Value("${jasypt.encryptor.password}")
    private String password;
    /**
     * 配置StringEncryptor
     */
    @Bean("customStringEncryptor")
    public CustomStringEncryptor customStringEncryptor() {
        return new CustomStringEncryptor(password);
    }
}
