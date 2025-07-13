package com.bancodealimentos.services;
import com.bancodealimentos.model.RegistroEntrega;
import com.bancodealimentos.repository.RegistroEntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class RegistroEntregaService {
    private final RegistroEntregaRepository registroEntregaRepository;

    @Autowired
    public RegistroEntregaService(RegistroEntregaRepository registroEntregaRepository) {
        this.registroEntregaRepository = registroEntregaRepository;
    }

    public List<RegistroEntrega> getAllRegistrosEntrega() {
        return registroEntregaRepository.findAll();
    }

    public RegistroEntrega getRegistroEntregaById(Long id) {
        return registroEntregaRepository.findById(id).orElse(null);
    }

    public RegistroEntrega createRegistroEntrega(RegistroEntrega registroEntrega) {
        return registroEntregaRepository.save(registroEntrega);
    }

    public RegistroEntrega updateRegistroEntrega(Long id, RegistroEntrega registroEntrega) {
        if (registroEntregaRepository.existsById(id)) {
            registroEntrega.setId(id);
            return registroEntregaRepository.save(registroEntrega);
        }
        return null;
    }

    public boolean deleteRegistroEntrega(Long id) {
        if (registroEntregaRepository.existsById(id)) {
            registroEntregaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
