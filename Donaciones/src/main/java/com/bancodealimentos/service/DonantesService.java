package com.bancodealimentos.service;

import com.bancodealimentos.model.Donantes;
import com.bancodealimentos.repository.DonantesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonantesService {
    @Autowired
private DonantesRepository donantesRepository;

public List<Donantes> getAll() {
    return donantesRepository.findAll();
}

public Optional<Donantes> getById(Long id) {
    return donantesRepository.findById(id);
}

public Donantes save(Donantes donante) {
    return donantesRepository.save(donante);
}

public void delete(Long id) {
    donantesRepository.deleteById(id);
}

}