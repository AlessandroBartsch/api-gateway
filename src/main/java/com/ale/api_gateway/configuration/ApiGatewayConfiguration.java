package com.ale.api_gateway.configuration;

import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.netty.http.client.HttpClient;

import java.util.function.Function;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/get")
                        .filters(f -> f
                                .addRequestHeader("Hello", "World")
                                .addRequestParameter("Hello", "World"))
                        .uri("http://httpbin.org:80"))
                .route(p -> p.path("/cambioservice/**")
                        .uri("lb://cambioservice"))
                .route(p -> p.path("/bookservice/**")
                        .uri("lb://bookservice"))
                .build();
    }

}
