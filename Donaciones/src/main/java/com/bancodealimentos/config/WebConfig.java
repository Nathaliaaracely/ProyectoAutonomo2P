package com.bancodealimentos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.lang.NonNull;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        
        // Permitir solicitudes desde cualquier origen
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        
        // Permitir cabeceras específicas
        config.addAllowedHeader("*");
        
        // Permitir métodos HTTP específicos
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        
        // Configurar el tiempo máximo de caché para las respuestas preflight
        config.setMaxAge(3600L);
        
        // Aplicar la configuración a todas las rutas
        source.registerCorsConfiguration("/**", config);
        
        return new CorsFilter(source);
    }

    @Override
    public void addResourceHandlers(@NonNull ResourceHandlerRegistry registry) {
        // Habilitar GraphiQL
        registry.addResourceHandler("/graphiql/**")
                .addResourceLocations("classpath:/graphiql/");
                
        // Asegurar que los recursos estáticos se sirvan correctamente
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }
}
