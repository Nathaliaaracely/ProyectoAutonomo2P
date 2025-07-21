package com.bancodealimentos.controller;
import com.bancodealimentos.model.RegistroEntrega;
import com.bancodealimentos.service.RegistroEntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/registros-entrega")
public class RegistroEntregaController {

    @Autowired
    private RegistroEntregaService registroEntregaService;

    @GetMapping
    public List<RegistroEntrega> getAll() {
        return registroEntregaService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<RegistroEntrega> getById(@PathVariable Long id) {
        return registroEntregaService.getById(id);
    }

    @PostMapping
    public RegistroEntrega save(@RequestBody RegistroEntrega registro) {
        return registroEntregaService.save(registro);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        registroEntregaService.delete(id);
    }
}
