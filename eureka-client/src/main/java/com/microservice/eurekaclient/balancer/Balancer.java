package com.microservice.eurekaclient.balancer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.BestAvailableRule;
import com.netflix.loadbalancer.IRule;

@Configuration
public class Balancer {

    @Autowired
    private SpringClientFactory springClientFactory;

    @Bean
    public IRule BestAvailableRule() {
        return new BestAvailableRule();
    }
}