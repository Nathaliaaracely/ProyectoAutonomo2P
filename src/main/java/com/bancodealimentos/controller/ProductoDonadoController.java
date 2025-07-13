package com.bancodealimentos.controller;
import com.bancodealimentos.model.ProductoDonado;
import jakarta.persistence.*;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class ProductoDonadoController {
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
    
}
    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public Donacion getDonacion() {
        return donacion;
    }

    public void setDonacion(Donacion donacion) {
        this.donacion = donacion;
    }
}
