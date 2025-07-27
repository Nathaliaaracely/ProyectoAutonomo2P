package com.bancodealimentos.service;

import com.bancodealimentos.model.Donacion;

import java.util.List;
import java.util.Optional;

public interface DonacionService {
    List<Donacion> getAll();
    Optional<Donacion> getById(Long id);
    Donacion save(Donacion donacion);
    void delete(Long id);
}
