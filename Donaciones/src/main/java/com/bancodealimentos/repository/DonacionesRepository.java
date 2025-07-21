package com.bancodealimentos.repository;

import com.bancodealimentos.model.Donaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonacionesRepository extends JpaRepository<Donaciones, Long> {
}