package com.bancodealimentos.services;
import com.bancodealimentos.model.Receptor;
import com.bancodealimentos.repository.ReceptorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class ReceptorService {
    private final ReceptorRepository receptorRepository;

    @Autowired
    public ReceptorService(ReceptorRepository receptorRepository) {
        this.receptorRepository = receptorRepository;
    }

    public List<Receptor> getAllReceptores() {
        return receptorRepository.findAll();
    }

    public Receptor getReceptorById(Long id) {
        return receptorRepository.findById(id).orElse(null);
    }

    public Receptor createReceptor(Receptor receptor) {
        return receptorRepository.save(receptor);
    }

    public Receptor updateReceptor(Long id, Receptor receptor) {
        if (receptorRepository.existsById(id)) {
            receptor.setId(id);
            return receptorRepository.save(receptor);
        }
        return null;
    }

    public boolean deleteReceptor(Long id) {
        if (receptorRepository.existsById(id)) {
            receptorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
