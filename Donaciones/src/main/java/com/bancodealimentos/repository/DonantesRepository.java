package com.bancodealimentos.repository;

import com.bancodealimentos.model.Donantes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonantesRepository extends JpaRepository<Donantes, Long> {
}