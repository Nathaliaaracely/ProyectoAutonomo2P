package com.bancodealimentos.service;

import com.bancodealimentos.model.Receptores;
import com.bancodealimentos.repository.ReceptoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
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

    public boolean delete(Long id) {
        try {
            if (receptoresRepository.existsById(id)) {
                receptoresRepository.deleteById(id);
                return true;
            }
            return false;
        } catch (DataIntegrityViolationException e) {
            // Si hay restricciones de clave foránea
            return false;
        } catch (EmptyResultDataAccessException e) {
            // Si el ID no existe
            return false;
        } catch (Exception e) {
            // Cualquier otro error
            return false;
        }
    }

}