package com.bancodealimentos.controller;

import com.bancodealimentos.model.Donantes;
import com.bancodealimentos.service.DonantesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.bancodealimentos.websocket.NotificacionService;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/donantes")
    public class DonantesController {
        @Autowired
    private DonantesService donantesService;

    @Autowired
    private NotificacionService notificacionService;

    @GetMapping
    public List<Donantes> getAll() {
        return donantesService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Donantes> getById(@PathVariable Long id) {
        return donantesService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Donantes> save(@RequestBody Donantes donante) {
        Donantes saved = donantesService.save(donante);
        notificacionService.notificarDonanteCreado(saved);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (donantesService.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}