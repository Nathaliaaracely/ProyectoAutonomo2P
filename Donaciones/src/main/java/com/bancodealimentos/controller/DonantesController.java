package com.bancodealimentos.controller;

import com.bancodealimentos.model.Donantes;
import com.bancodealimentos.service.DonantesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/donantes")
public class DonantesController {
    @Autowired
private DonantesService donantesService;

@GetMapping
public List<Donantes> getAll() {
    return donantesService.getAll();
}

@GetMapping("/{id}")
public Optional<Donantes> getById(@PathVariable Long id) {
    return donantesService.getById(id);
}

@PostMapping
public Donantes save(@RequestBody Donantes donante) {
    return donantesService.save(donante);
}

@DeleteMapping("/{id}")
public void delete(@PathVariable Long id) {
    donantesService.delete(id);
}

}