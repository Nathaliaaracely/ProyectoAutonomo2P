package com.bancodealimentos.controller;
import com.bancodealimentos.model.ProductoDonado;
import jakarta.persistence.*;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/receptores")
public interface ReceptorController {
    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/productos")
    public List<ProductoDonado> getAllProductos() {
        return entityManager.createQuery("SELECT p FROM ProductoDonado p", ProductoDonado.class).getResultList();
    }

    @PostMapping("/productos")
    public ProductoDonado createProducto(ProductoDonado producto) {
        entityManager.persist(producto);
        return producto;
    }
    @GetMapping("/{id}")
    public ProductoDonado getProductoById(Long id) {
        return entityManager.find(ProductoDonado.class, id);
    }
    @PostMapping("/{id}")
    public ProductoDonado updateProducto(Long id, ProductoDonado producto) {

        ProductoDonado existingProducto = entityManager.find(ProductoDonado.class, id);
        if (existingProducto != null) {
            existingProducto.setNombreProducto(producto.getNombreProducto());
            existingProducto.setTipoProducto(producto.getTipoProducto());
            existingProducto.setFechaVencimiento(producto.getFechaVencimiento());
            existingProducto.setCantidad(producto.getCantidad());
            existingProducto.setUnidad(producto.getUnidad());
            entityManager.merge(existingProducto);
            return existingProducto;
        }
        return null; // or throw an exception
    }
    
}
