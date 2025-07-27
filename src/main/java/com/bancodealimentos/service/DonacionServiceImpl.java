package com.bancodealimentos.service;

import com.bancodealimentos.model.Donacion;
import com.bancodealimentos.repository.DonacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonacionServiceImpl implements DonacionService {

    private final DonacionRepository donacionRepository;

    @Autowired
    public DonacionServiceImpl(DonacionRepository donacionRepository) {
        this.donacionRepository = donacionRepository;
    }

    @Override
    public List<Donacion> getAll() {
        return donacionRepository.findAll();
    }

    @Override
    public Optional<Donacion> getById(Long id) {
        return donacionRepository.findById(id);
    }

    @Override
    public Donacion save(Donacion donacion) {
        return donacionRepository.save(donacion);
    }

    @Override
    public void delete(Long id) {
        donacionRepository.deleteById(id);
    }
}
