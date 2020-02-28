package com.campusactivity.common.config;

import jodd.util.StringUtil;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Properties;

/**
 * @Author q1hang
 * @Time: 2020-02-26
 * @Description: RedissonClient配置
 */
@Configuration
public class RedissonClientConfig {

    @Bean
    @Autowired
    public RedissonClient initRedissonClient(Environment env){
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();
        Properties prop = build(env, "spring.redis.");
        //设置地址
        StringBuilder addressBuild = new StringBuilder("redis://").append(prop.getProperty("host")).append(":").append(prop.getProperty("port"));
        singleServerConfig.setAddress(addressBuild.toString());
        //设置密码
        if(StringUtil.isNotEmpty(prop.getProperty("password"))) {
            singleServerConfig.setPassword(prop.getProperty("password"));
        }
        //最小空闲连接数
        singleServerConfig.setConnectionMinimumIdleSize(1);
        //连接池大小
        singleServerConfig.setConnectionPoolSize(50);
        //创建新对象
        return Redisson.create(config);
    }

    protected Properties build(Environment env, String prefix) {
        Properties prop = new Properties();
        prop.put("host", env.getProperty(prefix + "host"));
        prop.put("port", env.getProperty(prefix + "port"));
        prop.put("password", env.getProperty(prefix + "password"));
        return prop;
    }
}
