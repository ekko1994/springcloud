package com.atguigu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhanghao
 * @date 2021/3/22 20:20
 */
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeBuilder){
        RouteLocatorBuilder.Builder routes = routeBuilder.routes();
        routes.route("path_route_atguigu",
                r -> r.path("/guonei")
                        .uri("https://news.baidu.com/guonei"))
                .route("path_route_atguigu2",
                        r -> r.path("/guoji")
                                .uri("https://news.baidu.com/guoji")).build();
        return routes.build();
    }
}
