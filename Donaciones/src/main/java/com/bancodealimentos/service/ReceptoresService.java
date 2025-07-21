package com.bancodealimentos.service;

import com.bancodealimentos.model.Receptores;
import com.bancodealimentos.repository.ReceptoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceptoresService {
    @Autowired
private ReceptoresRepository receptoresRepository;

public List<Receptores> getAll() {
    return receptoresRepository.findAll();
}

public Optional<Receptores> getById(Long id) {
    return receptoresRepository.findById(id);
}

public Receptores save(Receptores receptor) {
    return receptoresRepository.save(receptor);
}

public void delete(Long id) {
    receptoresRepository.deleteById(id);
}

}