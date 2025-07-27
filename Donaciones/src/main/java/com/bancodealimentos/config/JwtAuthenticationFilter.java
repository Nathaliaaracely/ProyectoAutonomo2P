package com.bancodealimentos.config;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter extends AbstractGatewayFilterFactory<JwtAuthenticationFilter.Config> {

    public JwtAuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            // Obtener la solicitud
            ServerHttpRequest request = exchange.getRequest();
            
            // Verificar si la ruta es pública (como la autenticación)
            if (isSecured.test(request)) {
                return chain.filter(exchange);
            }

            // Obtener el token del encabezado
            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, "No hay token de autorización", HttpStatus.UNAUTHORIZED);
            }

            String token = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (token == null || !token.startsWith("Bearer ")) {
                return onError(exchange, "Formato de token inválido", HttpStatus.UNAUTHORIZED);
            }

            // Validar el token (aquí deberías implementar la validación real del token)
            String jwt = token.substring(7); // Eliminar 'Bearer '
            if (!isValidToken(jwt)) {
                return onError(exchange, "Token inválido o expirado", HttpStatus.UNAUTHORIZED);
            }

            // Agregar el encabezado de usuario autenticado a la solicitud
            ServerHttpRequest modifiedRequest = exchange.getRequest().mutate()
                .header("X-Authenticated-User", getUsernameFromToken(jwt))
                .build();

            // Continuar con la cadena de filtros
            return chain.filter(exchange.mutate().request(modifiedRequest).build());
        };
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private boolean isValidToken(String token) {
        // Implementar lógica de validación del token JWT
        // Por ahora, solo verificamos que no esté vacío
        return token != null && !token.isEmpty();
    }

    private String getUsernameFromToken(String token) {
        // Implementar extracción del nombre de usuario del token JWT
        // Por ahora, devolvemos un valor por defecto
        return "usuario_ejemplo";
    }

    // Predicado para verificar si la ruta es pública
    private final java.util.function.Predicate<ServerHttpRequest> isSecured =
        request -> request.getURI().getPath().contains("/api/auth/");

    public static class Config {
        // Configuraciones adicionales pueden ir aquí
    }
}
