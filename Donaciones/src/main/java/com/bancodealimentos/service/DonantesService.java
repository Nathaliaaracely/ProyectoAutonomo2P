package com.bancodealimentos.service;

import com.bancodealimentos.model.Donantes;
import com.bancodealimentos.repository.DonantesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
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
        // Validaciones
        if (donante.getEmail() == null || donante.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("El email es obligatorio");
        }
        if (donante.getNombre() == null || donante.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (donante.getTelefono() == null || donante.getTelefono().trim().isEmpty()) {
            throw new IllegalArgumentException("El teléfono es obligatorio");
        }
        
        // Asegurarse de que el tipo tenga un valor por defecto
        if (donante.getTipo() == null || donante.getTipo().trim().isEmpty()) {
            donante.setTipo("persona");
        }
        
        return donantesRepository.save(donante);
    }
    
    public void delete(Long id) {
        donantesRepository.deleteById(id);
    }
}