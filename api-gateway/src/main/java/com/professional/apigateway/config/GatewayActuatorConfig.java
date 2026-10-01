package com.professional.apigateway.config;

import java.util.List;

import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.cloud.gateway.actuate.GatewayControllerEndpoint;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.cloud.gateway.handler.predicate.RoutePredicateFactory;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class GatewayActuatorConfig {

    @Bean
    public GatewayControllerEndpoint gatewayControllerEndpoint(
            List<GlobalFilter> globalFilters,
            List<GatewayFilterFactory> gatewayFilters,
            List<RoutePredicateFactory> routePredicates,
            RouteDefinitionWriter routeDefinitionWriter,
            RouteLocator routeLocator,
            RouteDefinitionLocator routeDefinitionLocator,
            WebEndpointProperties webEndpointProperties) {
        return new GatewayControllerEndpoint(
                globalFilters,
                gatewayFilters,
                routePredicates,
                routeDefinitionWriter,
                routeLocator,
                routeDefinitionLocator,
                webEndpointProperties);
    }
}
