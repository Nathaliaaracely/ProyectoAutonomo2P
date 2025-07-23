package com.bancodealimentos.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.RequestRateLimiterGatewayFilterFactory;

import java.time.Duration;

@Configuration
public class GatewayConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final RequestRateLimiterGatewayFilterFactory rateLimiter;

    public GatewayConfig(JwtAuthenticationFilter jwtAuthenticationFilter, 
                        RequestRateLimiterGatewayFilterFactory rateLimiter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.rateLimiter = rateLimiter;
    }

    @Bean
    public KeyResolver userKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
    }

    @Bean
    public RedisRateLimiter redisRateLimiter() {
        return new RedisRateLimiter(100, 100, 1);
    }

    private GatewayFilter createRateLimiterFilter() {
        RequestRateLimiterGatewayFilterFactory.Config config = new RequestRateLimiterGatewayFilterFactory.Config()
            .setKeyResolver(userKeyResolver())
            .setRateLimiter(redisRateLimiter());
        return rateLimiter.apply(config);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        GatewayFilter rateLimiterFilter = createRateLimiterFilter();
        
        return builder.routes()
            // Ruta para autenticación (pública)
            .route("auth_route", r ->
                r.path("/api/auth/**")
                .filters(f -> f
                    .rewritePath("/api/auth/(?<segment>.*)", "/api/auth/${segment}")
                    .circuitBreaker(c -> c.setName("authCircuitBreaker").setFallbackUri("forward:/fallback"))
                    .filter(rateLimiterFilter)
                    .setResponseHeader("X-Response-Time", java.time.LocalDateTime.now().toString()))
                .uri("http://localhost:8080"))
            
            // Ruta para donaciones (protegida)
            .route("donaciones_route", r -> 
                r.path("/api/donaciones/**")
                .filters(f -> 
                    f.filter(jwtAuthenticationFilter.apply(new JwtAuthenticationFilter.Config()))
                    .circuitBreaker(c -> c.setName("donacionesCircuitBreaker"))
                    .retry(rc -> rc.setRetries(3).setMethods(HttpMethod.GET, HttpMethod.POST)
                        .setBackoff(Duration.ofMillis(100), Duration.ofSeconds(2), 2, true))
                    .addRequestHeader("X-Request-ID", java.util.UUID.randomUUID().toString()))
                .uri("lb://donaciones-service"))
                
            // Ruta para donantes (protegida)
            .route("donantes_route", r ->
                r.path("/api/donantes/**")
                .filters(f -> 
                    f.filter(jwtAuthenticationFilter.apply(new JwtAuthenticationFilter.Config()))
                    .circuitBreaker(c -> c.setName("donantesCircuitBreaker"))
                    .filter(rateLimiterFilter)
                    .addResponseHeader("X-RateLimit-Limit", "100"))
                .uri("lb://donantes-service"))
                
            // Ruta para productos donados (protegida)
            .route("productos_route", r ->
                r.path("/api/productos/**")
                .filters(f -> 
                    f.filter(jwtAuthenticationFilter.apply(new JwtAuthenticationFilter.Config()))
                    .circuitBreaker(c -> c.setName("productosCircuitBreaker"))
                    .filter(rateLimiterFilter)
                    .addResponseHeader("X-RateLimit-Limit", "100"))
                .uri("lb://productos-service"))
                
            // Ruta para receptores (protegida)
            .route("receptores_route", r ->
                r.path("/api/receptores/**")
                .filters(f -> 
                    f.filter(jwtAuthenticationFilter.apply(new JwtAuthenticationFilter.Config()))
                    .circuitBreaker(c -> c.setName("receptoresCircuitBreaker"))
                    .filter(rateLimiterFilter)
                    .addResponseHeader("X-RateLimit-Limit", "100"))
                .uri("lb://receptores-service"))
                
            // Ruta para registro de entregas (protegida)
            .route("entregas_route", r ->
                r.path("/api/entregas/**")
                .filters(f -> 
                    f.filter(jwtAuthenticationFilter.apply(new JwtAuthenticationFilter.Config()))
                    .circuitBreaker(c -> c.setName("entregasCircuitBreaker"))
                    .filter(rateLimiterFilter)
                    .addResponseHeader("X-RateLimit-Limit", "100"))
                .uri("lb://entregas-service"))
                
            // Ruta de fallback global
            .route("fallback_route", r ->
                r.path("/fallback")
                .filters(f -> f.rewritePath("/fallback", "/error"))
                .uri("forward:/error"))
            .build();
    }
}
