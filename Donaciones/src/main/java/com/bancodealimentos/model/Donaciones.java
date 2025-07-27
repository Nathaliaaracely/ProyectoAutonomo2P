package com.bancodealimentos.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "donaciones")
public class Donaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_donacion")
    private LocalDate fechaDonacion;

    @Column(name = "tipo_donacion")
    private String tipoDonacion;

    @Column
    private String descripcion;

    @Column
    private Double cantidad;

    @Column(name = "unidad_medida")
    private String unidadMedida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "donante_id")
    private Donantes donante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receptor_id")
    private Receptores receptor;

    @Column(name = "fecha_vencimiento")
    private LocalDate fechaVencimiento;

    @Column
    private String estado;

    // Constructor vacío obligatorio para JPA
    public Donaciones() {}

    // Constructor con parámetros (sin id)
    public Donaciones(LocalDate fechaDonacion, String tipoDonacion, String descripcion, Double cantidad, String unidadMedida, Donantes donante, Receptores receptor, LocalDate fechaVencimiento, String estado) {
        this.fechaDonacion = fechaDonacion;
        this.tipoDonacion = tipoDonacion;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.unidadMedida = unidadMedida;
        this.donante = donante;
        this.receptor = receptor;
        this.fechaVencimiento = fechaVencimiento;
        this.estado = estado;
    }

    // Getters y Setters para las relaciones
    public Donantes getDonante() {
        return donante;
    }

    public void setDonante(Donantes donante) {
        this.donante = donante;
    }

    public Receptores getReceptor() {
        return receptor;
    }

    public void setReceptor(Receptores receptor) {
        this.receptor = receptor;
    }

    // Resto de los getters y setters existentes...
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaDonacion() {
        return fechaDonacion;
    }

    public void setFechaDonacion(LocalDate fechaDonacion) {
        this.fechaDonacion = fechaDonacion;
    }

    public String getTipoDonacion() {
        return tipoDonacion;
    }

    public void setTipoDonacion(String tipoDonacion) {
        this.tipoDonacion = tipoDonacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Donaciones{" +
                "id=" + id +
                ", fechaDonacion=" + fechaDonacion +
                ", tipoDonacion='" + tipoDonacion + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", cantidad=" + cantidad +
                ", unidadMedida='" + unidadMedida + '\'' +
                ", donante=" + donante +
                ", receptor=" + receptor +
                ", fechaVencimiento=" + fechaVencimiento +
                ", estado='" + estado + '\'' +
                '}';
    }
}
