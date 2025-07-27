package com.bancodealimentos.repository;

import com.bancodealimentos.model.Receptores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceptoresRepository extends JpaRepository<Receptores, Long> {
}