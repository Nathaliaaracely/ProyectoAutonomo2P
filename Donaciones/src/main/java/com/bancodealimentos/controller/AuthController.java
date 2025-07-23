package com.bancodealimentos.controller;

import com.bancodealimentos.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/token")
    public ResponseEntity<?> getToken(@RequestBody AuthRequest authRequest) {
        // Validación simple (puedes personalizarla según necesites)
        if (!"admin".equals(authRequest.getUsername()) || !"admin123".equals(authRequest.getPassword())) {
            return ResponseEntity.badRequest().body("Credenciales inválidas");
        }

        // Generar token
        String token = tokenProvider.generateToken(authRequest.getUsername());
        
        // Devolver el token
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        
        return ResponseEntity.ok(response);
    }
    
    static class AuthRequest {
        private String username;
        private String password;
        
        // Getters y Setters
        public String getUsername() { 
            return username; 
        }
        public void setUsername(String username) { 
            this.username = username; 
        }
        public String getPassword() { 
            return password; 
        }
        public void setPassword(String password) { 
            this.password = password; 
        }
    }
}