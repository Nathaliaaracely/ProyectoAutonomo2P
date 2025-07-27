package com.bancodealimentos.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ConnectionTester implements CommandLineRunner {
    
    private final JdbcTemplate jdbcTemplate;
    
    public ConnectionTester(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public void run(String... args) throws Exception {
        try {
            String result = jdbcTemplate.queryForObject(
                "SELECT ' Conexión exitosa a la base de datos'", 
                String.class
            );
            System.out.println(result);
        } catch (Exception e) {
            System.err.println(" Error de conexión: " + e.getMessage());
            e.printStackTrace();
        }
    }
}