package com.bancodealimentos.controller;

import com.bancodealimentos.model.Donaciones;
import com.bancodealimentos.service.DonacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
public class DonacionesController {
    
    private static final Logger logger = LoggerFactory.getLogger(DonacionesController.class);
    
    @Autowired
    private DonacionesService donacionesService;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/tables")
    public ResponseEntity<?> getDatabaseInfo() {
        try {
            // Obtener información de la base de datos
            String dbName = jdbcTemplate.queryForObject(
                "SELECT current_database()", String.class);
                
            String schema = jdbcTemplate.queryForObject(
                "SELECT current_schema()", String.class);
                
            // Listar todas las tablas en el esquema actual
            List<Map<String, Object>> tables = jdbcTemplate.queryForList(
                "SELECT table_name FROM information_schema.tables WHERE table_schema = ?", 
                schema);
                
            return ResponseEntity.ok(Map.of(
                "database", dbName,
                "schema", schema,
                "tables", tables
            ));
            
        } catch (Exception e) {
            logger.error("Error al obtener información de la base de datos", e);
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/donaciones")
    public ResponseEntity<?> getAll() {
        try {
            logger.info("Obteniendo todas las donaciones");
            return ResponseEntity.ok(donacionesService.getAll());
        } catch (Exception e) {
            logger.error("Error al obtener donaciones", e);
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/donaciones/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            logger.info("Obteniendo donación con ID: {}", id);
            Optional<Donaciones> donacion = donacionesService.getById(id);
            return donacion.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            logger.error("Error al obtener donación con ID: " + id, e);
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/donaciones")
    public ResponseEntity<?> save(@RequestBody Donaciones donacion) {
        try {
            logger.info("Guardando nueva donación: {}", donacion);
            return ResponseEntity.ok(donacionesService.save(donacion));
        } catch (Exception e) {
            logger.error("Error al guardar donación", e);
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/donaciones/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            logger.info("Eliminando donación con ID: {}", id);
            donacionesService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("Error al eliminar donación con ID: " + id, e);
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
