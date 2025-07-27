package com.bancodealimentos.repository;

import com.bancodealimentos.model.Donaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DonacionesRepository extends JpaRepository<Donaciones, Long> {
    
    // Buscar donaciones por ID de donante
    @Query("SELECT d FROM Donaciones d WHERE d.donante.id = :donanteId")
    List<Donaciones> findByDonanteId(@Param("donanteId") Long donanteId);
    
    // Buscar donaciones por ID de receptor
    @Query("SELECT d FROM Donaciones d WHERE d.receptor.id = :receptorId")
    List<Donaciones> findByReceptorId(@Param("receptorId") Long receptorId);
    
    // Buscar donaciones por tipo de donación
    List<Donaciones> findByTipoDonacion(String tipoDonacion);
    
    // Buscar donaciones por fecha de donación
    List<Donaciones> findByFechaDonacion(LocalDate fecha);
    
    // Buscar donaciones por rango de fechas
    List<Donaciones> findByFechaDonacionBetween(LocalDate fechaInicio, LocalDate fechaFin);
    
    // Buscar donaciones por estado
    List<Donaciones> findByEstado(String estado);
}