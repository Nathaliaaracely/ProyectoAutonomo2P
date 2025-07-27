package com.bancodealimentos.controller;

import com.bancodealimentos.model.Donaciones;
import com.bancodealimentos.model.Donantes;
import com.bancodealimentos.model.Receptores;
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
    public ResponseEntity<?> save(@RequestBody Map<String, Object> donacionData) {
        try {
            logger.info("Guardando nueva donación: {}", donacionData);
            
            // Validar campos requeridos
            if (donacionData.get("fechaDonacion") == null) {
                return ResponseEntity.badRequest().body("El campo 'fechaDonacion' es requerido");
            }
            
            if (donacionData.get("idDonante") == null) {
                return ResponseEntity.badRequest().body("El campo 'idDonante' es requerido");
            }
            
            if (donacionData.get("idReceptor") == null) {
                return ResponseEntity.badRequest().body("El campo 'idReceptor' es requerido");
            }
            
            // Crear nueva instancia de Donaciones
            Donaciones donacion = new Donaciones();
            
            try {
                // Establecer campos básicos
                donacion.setFechaDonacion(java.time.LocalDate.parse(donacionData.get("fechaDonacion").toString()));
                
                if (donacionData.get("estado") != null) {
                    donacion.setEstado(donacionData.get("estado").toString());
                } else {
                    donacion.setEstado("Pendiente");
                }
                
                // Establecer donante
                Long donanteId = Long.parseLong(donacionData.get("idDonante").toString());
                Donantes donante = new Donantes();
                donante.setId(donanteId);
                donacion.setDonante(donante);
                
                // Establecer receptor
                Long receptorId = Long.parseLong(donacionData.get("idReceptor").toString());
                Receptores receptor = new Receptores();
                receptor.setId(receptorId);
                donacion.setReceptor(receptor);
                
                // Guardar la donación
                Donaciones donacionGuardada = donacionesService.save(donacion);
                logger.info("Donación guardada exitosamente con ID: {}", donacionGuardada.getId());
                return ResponseEntity.ok(donacionGuardada);
                
            } catch (java.time.format.DateTimeParseException e) {
                return ResponseEntity.badRequest().body("Formato de fecha inválido. Use el formato YYYY-MM-DD");
            } catch (NumberFormatException e) {
                return ResponseEntity.badRequest().body("Los IDs de donante y receptor deben ser números válidos");
            } catch (Exception e) {
                logger.error("Error al guardar en la base de datos", e);
                return ResponseEntity.status(500).body("Error al guardar en la base de datos: " + e.getMessage());
            }
            
        } catch (Exception e) {
            logger.error("Error inesperado al guardar donación", e);
            return ResponseEntity.status(500).body("Error inesperado al procesar la solicitud: " + e.getMessage());
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

    @GetMapping("/donaciones/schema")
    public ResponseEntity<?> getDonacionesSchema() {
        try {
            // Obtener información de las columnas de la tabla donaciones
            List<Map<String, Object>> columns = jdbcTemplate.queryForList(
                "SELECT column_name, data_type, is_nullable, column_default " +
                "FROM information_schema.columns " +
                "WHERE table_name = 'donaciones'");
                
            // Obtener las llaves foráneas
            List<Map<String, Object>> foreignKeys = jdbcTemplate.queryForList(
                "SELECT kcu.column_name, ccu.table_name AS foreign_table_name, " +
                "       ccu.column_name AS foreign_column_name " +
                "FROM information_schema.table_constraints AS tc " +
                "JOIN information_schema.key_column_usage AS kcu " +
                "  ON tc.constraint_name = kcu.constraint_name " +
                "  AND tc.table_schema = kcu.table_schema " +
                "JOIN information_schema.constraint_column_usage AS ccu " +
                "  ON ccu.constraint_name = tc.constraint_name " +
                "  AND ccu.table_schema = tc.table_schema " +
                "WHERE tc.constraint_type = 'FOREIGN KEY' AND tc.table_name = 'donaciones'");
            
            // Obtener datos de ejemplo
            List<Map<String, Object>> sampleData = jdbcTemplate.queryForList(
                "SELECT * FROM donaciones LIMIT 5");
            
            return ResponseEntity.ok(Map.of(
                "columns", columns,
                "foreignKeys", foreignKeys,
                "sampleData", sampleData
            ));
            
        } catch (Exception e) {
            logger.error("Error al obtener el esquema de la tabla donaciones", e);
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
