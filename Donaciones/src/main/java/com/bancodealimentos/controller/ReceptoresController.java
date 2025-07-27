package com.bancodealimentos.controller;

import com.bancodealimentos.model.Receptores;
import com.bancodealimentos.service.ReceptoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/receptores")
public class ReceptoresController {
    @Autowired
private ReceptoresService receptoresService;

@GetMapping
public List<Receptores> getAll() {
    return receptoresService.getAll();
}

@GetMapping("/{id}")
public Optional<Receptores> getById(@PathVariable Long id) {
    return receptoresService.getById(id);
}

@PostMapping
public Receptores save(@RequestBody Receptores receptor) {
    return receptoresService.save(receptor);
}

@DeleteMapping("/{id}")
public void delete(@PathVariable Long id) {
    receptoresService.delete(id);
}

}