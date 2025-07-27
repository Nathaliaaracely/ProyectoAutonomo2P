package com.bancodealimentos.repository;

import com.bancodealimentos.model.ProductosDonados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductosDonadosRepository extends JpaRepository<ProductosDonados, Long> {
}