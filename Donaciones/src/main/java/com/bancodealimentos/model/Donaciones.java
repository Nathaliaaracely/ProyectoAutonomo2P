package com.bancodealimentos.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "donaciones")
public class Donaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "monto", nullable = false)
    private Double monto;

    @Column(name = "donante", nullable = false)
    private String donante;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    // Constructor vacío obligatorio para JPA
    public Donaciones() {}

    // Constructor con parámetros (sin id)
    public Donaciones(Double monto, String donante, LocalDate fecha) {
        this.monto = monto;
        this.donante = donante;
        this.fecha = fecha;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getDonante() {
        return donante;
    }

    public void setDonante(String donante) {
        this.donante = donante;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Donaciones{" +
                "id=" + id +
                ", monto=" + monto +
                ", donante='" + donante + '\'' +
                ", fecha=" + fecha +
                '}';
    }
}
