package com.bancodealimentos.controller;

import com.bancodealimentos.model.ProductosDonados;
import com.bancodealimentos.service.ProductosDonadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos-donados")
public class ProductosDonadosController {
    @Autowired
private ProductosDonadosService productosDonadosService;

@GetMapping
public List<ProductosDonados> getAll() {
    return productosDonadosService.getAll();
}

@GetMapping("/{id}")
public Optional<ProductosDonados> getById(@PathVariable Long id) {
    return productosDonadosService.getById(id);
}

@PostMapping
public ProductosDonados save(@RequestBody ProductosDonados producto) {
    return productosDonadosService.save(producto);
}

@DeleteMapping("/{id}")
public void delete(@PathVariable Long id) {
    productosDonadosService.delete(id);
}

}