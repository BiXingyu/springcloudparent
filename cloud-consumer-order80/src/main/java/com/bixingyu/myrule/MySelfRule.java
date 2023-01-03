package com.bixingyu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author BiXingyu
 * @create 2022-12-24 21:55
 */
@Configuration
public class MySelfRule{
    @Bean
    public IRule myRule(){
        return new RandomRule();//定义为随机
    }
}
