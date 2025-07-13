package com.bancodealimentos.controller;

import com.bancodealimentos.model.Donacion;
import com.bancodealimentos.service.DonacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@ReadingConverterquestMapping("/api/donaciones")
public class DonacionController {

    @Autowired
    private DonacionService donacionService;

    @GetMapping
    public List<Donacion> getAll() {
        return donacionService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Donacion> getById(@PathVariable Long id) {
        return donacionService.getById(id);
    }

    @PostMapping
    public Donacion save(@RequestBody Donacion donacion) {
        return donacionService.save(donacion);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        donacionService.delete(id);
    }
}
