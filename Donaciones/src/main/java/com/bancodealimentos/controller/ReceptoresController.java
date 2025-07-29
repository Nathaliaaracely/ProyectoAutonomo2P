package com.bancodealimentos.controller;

import com.bancodealimentos.model.Receptores;
import com.bancodealimentos.service.ReceptoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bancodealimentos.websocket.NotificacionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/receptores")
public class ReceptoresController {
    @Autowired
    private ReceptoresService receptoresService;

    @Autowired
    private NotificacionService notificacionService;
    

    @GetMapping
    public List<Receptores> getAll() {
        return receptoresService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receptores> getById(@PathVariable Long id) {
        return receptoresService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Receptores> save(@RequestBody Receptores receptor) {
        Receptores saved = receptoresService.save(receptor);
        notificacionService.notificarReceptorCreado(saved);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (receptoresService.delete(id)) {
            notificacionService.notificarReceptorEliminado(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}