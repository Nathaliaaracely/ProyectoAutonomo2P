package com.bancodealimentos.services;
import com.bancodealimentos.model.Donacion;
import com.bancodealimentos.repository.DonacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class DonacionService {
    private final DonacionRepository donacionRepository;

    @Autowired
    public DonacionService(DonacionRepository donacionRepository) {
        this.donacionRepository = donacionRepository;
    }

    public List<Donacion> getAllDonaciones() {
        return donacionRepository.findAll();
    }

    public Donacion getDonacionById(Long id) {
        return donacionRepository.findById(id).orElse(null);
    }

    public Donacion createDonacion(Donacion donacion) {
        return donacionRepository.save(donacion);
    }

    public Donacion updateDonacion(Long id, Donacion donacion) {
        if (donacionRepository.existsById(id)) {
            donacion.setId(id);
            return donacionRepository.save(donacion);
        }
        return null;
    }

    public boolean deleteDonacion(Long id) {
        if (donacionRepository.existsById(id)) {
            donacionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
