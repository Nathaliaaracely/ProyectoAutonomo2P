package com.bancodealimentos.services;
import com.bancodealimentos.model.Donante;
import com.bancodealimentos.repository.DonanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DonanteService {
    // Inyectar el repositorio de Donante
    private final DonanteRepository donanteRepository;
    // Constructor para inyectar el repositorio
    @Autowired
    public DonanteService(DonanteRepository donanteRepository) {
        this.donanteRepository = donanteRepository;
    }

    private final DonanteRepository donanteRepository;

    @Autowired
    public DonanteService(DonanteRepository donanteRepository) {
        this.donanteRepository = donanteRepository;
    }

    public List<Donante> getAllDonantes() {
        return donanteRepository.findAll();
    }

    public Donante getDonanteById(Long id) {
        return donanteRepository.findById(id).orElse(null);
    }

    public Donante createDonante(Donante donante) {
        return donanteRepository.save(donante);
    }

    public Donante updateDonante(Long id, Donante donante) {
        if (donanteRepository.existsById(id)) {
            donante.setId(id);
            return donanteRepository.save(donante);
        }
        return null;
    }

    public boolean deleteDonante(Long id) {
        if (donanteRepository.existsById(id)) {
            donanteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
