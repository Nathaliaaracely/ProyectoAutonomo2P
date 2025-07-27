package com.bancodealimentos.service;

import com.bancodealimentos.model.RegistroEntrega;
import com.bancodealimentos.repository.RegistroEntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistroEntregaService {

    @Autowired
    private RegistroEntregaRepository registroEntregaRepository;

    public List<RegistroEntrega> getAll() {
        return registroEntregaRepository.findAll();
    }

    public Optional<RegistroEntrega> getById(Long id) {
        return registroEntregaRepository.findById(id);
    }

    public RegistroEntrega save(RegistroEntrega registro) {
        return registroEntregaRepository.save(registro);
    }

    public void delete(Long id) {
        registroEntregaRepository.deleteById(id);
    }

}
    

