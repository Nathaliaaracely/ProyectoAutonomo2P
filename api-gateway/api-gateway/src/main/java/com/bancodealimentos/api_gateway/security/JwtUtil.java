package com.bancodealimentos.api_gateway.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {
    // Clave secreta para firmar el token (guárdala segura y no la expongas en el código real)
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String generarToken(String username) {
        long expiracion = 1000 * 60 * 60; // 1 hora en milisegundos
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiracion))
                .signWith(key)
                .compact();
    }
}