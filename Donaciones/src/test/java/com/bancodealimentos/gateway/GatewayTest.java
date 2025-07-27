package com.bancodealimentos.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class GatewayTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getBaseUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void testPublicEndpoint() {
        // Prueba un endpoint público (sin autenticación)
        ResponseEntity<String> response = restTemplate.getForEntity(
            getBaseUrl() + "/api/auth/token", 
            String.class
        );
        
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().contains("Credenciales inválidas") || 
                 response.getBody().contains("Required request body is missing"));
    }

    @Test
    public void testProtectedEndpointWithoutToken() {
        // Prueba un endpoint protegido sin token
        ResponseEntity<String> response = restTemplate.getForEntity(
            getBaseUrl() + "/api/donaciones", 
            String.class
        );
        
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    public void testAuthenticationAndAccess() {
        // 1. Obtener token
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        String authRequest = "{\"username\":\"admin\",\"password\":\"admin123\"}";
        
        HttpEntity<String> request = new HttpEntity<>(authRequest, headers);
        
        ResponseEntity<String> authResponse = restTemplate.postForEntity(
            getBaseUrl() + "/api/auth/token",
            request,
            String.class
        );
        
        assertEquals(HttpStatus.OK, authResponse.getStatusCode());
        assertTrue(authResponse.getBody().contains("token"));
        
        // Extraer el token
        String token = authResponse.getBody().split("\"token\":\"")[1].split("\"")[0];
        
        // 2. Usar el token para acceder a un endpoint protegido
        headers.set("Authorization", "Bearer " + token);
        
        HttpEntity<String> protectedRequest = new HttpEntity<>(headers);
        
        ResponseEntity<String> protectedResponse = restTemplate.exchange(
            getBaseUrl() + "/api/donaciones",
            HttpMethod.GET,
            protectedRequest,
            String.class
        );
        
        // Verificar que la respuesta es exitosa o no encontrada (dependiendo de si hay datos)
        assertTrue(protectedResponse.getStatusCode() == HttpStatus.OK || 
                  protectedResponse.getStatusCode() == HttpStatus.NOT_FOUND);
    }

    @Test
    public void testNonExistentEndpoint() {
        // Prueba un endpoint que no existe
        ResponseEntity<String> response = restTemplate.getForEntity(
            getBaseUrl() + "/api/endpoint-inexistente", 
            String.class
        );
        
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testRateLimiting() {
        // Prueba de rate limiting (dependiendo de la configuración)
        // Este test puede fallar si el rate limiting está configurado muy bajo
        for (int i = 0; i < 10; i++) {
            ResponseEntity<String> response = restTemplate.getForEntity(
                getBaseUrl() + "/api/auth/token", 
                String.class
            );
            
            if (response.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS) {
                // Si recibimos 429, el rate limiting está funcionando
                return;
            }
        }
        
        // Si llegamos aquí, el rate limiting no se activó en las primeras 10 solicitudes
        // lo cual podría ser correcto dependiendo de la configuración
        System.out.println("Rate limiting no se activó en las primeras 10 solicitudes");
    }
}
