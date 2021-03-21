package com.atguigu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhanghao
 * @date 2021/3/21 18:47
 */
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRuleIRule(){
        return new RandomRule();
    }
}
