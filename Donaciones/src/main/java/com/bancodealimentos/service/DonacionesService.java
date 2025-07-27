package com.bancodealimentos.service;

import com.bancodealimentos.model.Donaciones;
import com.bancodealimentos.repository.DonacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonacionesService {
    private final DonacionesRepository donacionesRepository;

    @Autowired
    public DonacionesService(DonacionesRepository donacionesRepository) {
        this.donacionesRepository = donacionesRepository;
    }

    public List<Donaciones> listarTodas() {
        return donacionesRepository.findAll();
    }

    // Alias for listarTodas() to match controller's expectations
    public List<Donaciones> getAll() {
        return listarTodas();
    }

    public Optional<Donaciones> obtenerPorId(Long id) {
        return donacionesRepository.findById(id);
    }

    // Alias for obtenerPorId() to match controller's expectations
    public Optional<Donaciones> getById(Long id) {
        return obtenerPorId(id);
    }

    public List<Donaciones> buscarPorDonanteId(Long donanteId) {
        return donacionesRepository.findByDonanteId(donanteId);
    }

    public List<Donaciones> buscarPorReceptorId(Long receptorId) {
        return donacionesRepository.findByReceptorId(receptorId);
    }

    public List<Donaciones> buscarPorTipoDonacion(String tipoDonacion) {
        return donacionesRepository.findByTipoDonacion(tipoDonacion);
    }

    public List<Donaciones> buscarPorEstado(String estado) {
        return donacionesRepository.findByEstado(estado);
    }

    public Donaciones guardar(Donaciones donacion) {
        return donacionesRepository.save(donacion);
    }

    // Alias for guardar() to match controller's expectations
    public Donaciones save(Donaciones donacion) {
        return guardar(donacion);
    }

    public void eliminar(Long id) {
        donacionesRepository.deleteById(id);
    }

    // Alias for eliminar() to match controller's expectations
    public void delete(Long id) {
        eliminar(id);
    }
}