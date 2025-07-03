package com.sky.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * ClassName:RedisConfiguration
 * Description:
 *
 * @Author:AlanJake
 * @Create:2025/7/3下午3:39
 * @Version:
 */

@Configuration
@Slf4j
public class RedisConfiguration {

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory){
        log.info("RedisTemplate初始化成功:{}", redisConnectionFactory);
        RedisTemplate redisTemplate = new RedisTemplate();
        //设置redis连接工厂对象
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //设置序列化器 不同序列化器效果不同，StringRedisSerializer序列化器可防止key乱码，但不能防止value乱码
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
