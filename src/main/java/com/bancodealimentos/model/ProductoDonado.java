package com.bancodealimentos.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Representa un producto donado dentro de una donación.
 */
@Entity
@Table(name = "producto_donado")
public class ProductoDonado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_producto", nullable = false)
    private String nombreProducto;

    @Column(name = "tipo_producto", nullable = false)
    private String tipoProducto;

    @Column(name = "fecha_vencimiento")
    private LocalDate fechaVencimiento;

    private BigDecimal cantidad;

    private String unidad;

    @ManyToOne
    @JoinColumn(name = "donacion_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_producto_donacion"))
    private Donacion donacion;

    // Constructores
    public ProductoDonado() {
    }

    public ProductoDonado(String nombreProducto, String tipoProducto, LocalDate fechaVencimiento,
                          BigDecimal cantidad, String unidad, Donacion donacion) {
        this.nombreProducto = nombreProducto;
        this.tipoProducto = tipoProducto;
        this.fechaVencimiento = fechaVencimiento;
        this.cantidad = cantidad;
        this.unidad = unidad;
        this.donacion = donacion;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
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
