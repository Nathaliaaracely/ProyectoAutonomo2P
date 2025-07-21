package com.bancodealimentos.service;

import com.bancodealimentos.model.Donaciones;
import com.bancodealimentos.repository.DonacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonacionesService {
    @Autowired
private DonacionesRepository donacionesRepository;

public List<Donaciones> getAll() {
    return donacionesRepository.findAll();
}

public Optional<Donaciones> getById(Long id) {
    return donacionesRepository.findById(id);
}

public Donaciones save(Donaciones donacion) {
    return donacionesRepository.save(donacion);
}

public void delete(Long id) {
    donacionesRepository.deleteById(id);
}

}
