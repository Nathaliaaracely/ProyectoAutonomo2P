package com.bancodealimentos.service;

import com.bancodealimentos.model.Donaciones;
import com.bancodealimentos.repository.DonacionesRepository;
import com.bancodealimentos.websocket.DonacionSubscriptionController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar las operaciones relacionadas con las donaciones.
 * Incluye integración con WebSockets para notificaciones en tiempo real.
 */
@Service
@Transactional
public class DonacionesService {
    
    private final DonacionesRepository donacionesRepository;
    private final DonacionSubscriptionController subscriptionController;

    @Autowired
    public DonacionesService(DonacionesRepository donacionesRepository, 
                           DonacionSubscriptionController subscriptionController) {
        this.donacionesRepository = donacionesRepository;
        this.subscriptionController = subscriptionController;
    }

    // Métodos para el controlador REST
    public List<Donaciones> getAll() {
        return listarTodas();
    }

    public Optional<Donaciones> getById(Long id) {
        return obtenerPorId(id);
    }

    public Donaciones save(Donaciones donacion) {
        return guardarDonacion(donacion);
    }

    public void delete(Long id) {
        eliminarDonacion(id);
    }

    // Métodos para el Resolver de GraphQL
    /**
     * Obtiene todas las donaciones
     */
    public List<Donaciones> listarTodas() {
        return donacionesRepository.findAll();
    }

    /**
     * Obtiene una donación por su ID
     */
    public Optional<Donaciones> obtenerPorId(Long id) {
        return donacionesRepository.findById(id);
    }

    /**
     * Busca donaciones por tipo de donación
     */
    public List<Donaciones> buscarPorTipoDonacion(String tipoDonacion) {
        return donacionesRepository.findByTipoDonacion(tipoDonacion);
    }

    /**
     * Busca donaciones por ID de donante
     */
    public List<Donaciones> buscarPorDonanteId(Long donanteId) {
        return donacionesRepository.findByDonanteId(donanteId);
    }

    /**
     * Busca donaciones por ID de receptor
     */
    public List<Donaciones> buscarPorReceptorId(Long receptorId) {
        return donacionesRepository.findByReceptorId(receptorId);
    }

    /**
     * Guarda una nueva donación
     */
    public Donaciones guardarDonacion(Donaciones donacion) {
        Donaciones donacionGuardada = donacionesRepository.save(donacion);
        // Notificar a los suscriptores sobre la nueva donación
        subscriptionController.notificarNuevaDonacion(donacionGuardada);
        return donacionGuardada;
    }

    /**
     * Actualiza una donación existente
     */
    public Donaciones actualizarDonacion(Long id, Donaciones donacionActualizada) {
        return donacionesRepository.findById(id)
                .map(donacionExistente -> {
                    // Actualizar campos permitidos
                    if (donacionActualizada.getFechaDonacion() != null) {
                        donacionExistente.setFechaDonacion(donacionActualizada.getFechaDonacion());
                    }
                    if (donacionActualizada.getEstado() != null) {
                        donacionExistente.setEstado(donacionActualizada.getEstado());
                    }
                    if (donacionActualizada.getDescripcion() != null) {
                        donacionExistente.setDescripcion(donacionActualizada.getDescripcion());
                    }
                    
                    return donacionesRepository.save(donacionExistente);
                })
                .orElseThrow(() -> new RuntimeException("No se encontró la donación con ID: " + id));
    }

    /**
     * Elimina una donación por su ID
     */
    public void eliminarDonacion(Long id) {
        donacionesRepository.deleteById(id);
    }
    
    // Alias para GraphQL
    public Donaciones guardar(Donaciones donacion) {
        return guardarDonacion(donacion);
    }
    
    public void eliminar(Long id) {
        eliminarDonacion(id);
    }
}