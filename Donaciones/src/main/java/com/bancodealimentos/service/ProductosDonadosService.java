package com.bancodealimentos.service;

import com.bancodealimentos.model.ProductosDonados;
import com.bancodealimentos.repository.ProductosDonadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductosDonadosService {
    @Autowired
private ProductosDonadosRepository repository;

public List<ProductosDonados> getAll() {
    return repository.findAll();
}

public Optional<ProductosDonados> getById(Long id) {
    return repository.findById(id);
}

public ProductosDonados save(ProductosDonados producto) {
    return repository.save(producto);
}

public void delete(Long id) {
    repository.deleteById(id);
}

}