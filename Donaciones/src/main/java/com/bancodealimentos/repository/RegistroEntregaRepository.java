package com.bancodealimentos.repository;
import com.bancodealimentos.model.RegistroEntrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroEntregaRepository extends JpaRepository<RegistroEntrega, Long> {
}
 
