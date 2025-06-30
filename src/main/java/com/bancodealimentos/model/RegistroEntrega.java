package com.bancodealimentos.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Representa un registro de la entrega de una donación.
 */
@Entity
@Table(name = "registro_entrega")
public class RegistroEntrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "donacion_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_entrega_donacion"))
    private Donacion donacion;

    @Column(name = "fecha_entrega")
    private LocalDateTime fechaEntrega;

    @Column(name = "responsable_entrega")
    private String responsableEntrega;

    @ManyToOne
    @JoinColumn(name = "receptor_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_entrega_receptor"))
    private Receptor receptor;

    @Column(name = "firma_digital")
    private String firmaDigital;

    // Constructor
    public RegistroEntrega() {
        this.fechaEntrega = LocalDateTime.now();
    }

    public RegistroEntrega(Donacion donacion, String responsableEntrega, Receptor receptor, String firmaDigital) {
        this.donacion = donacion;
        this.fechaEntrega = LocalDateTime.now();
        this.responsableEntrega = responsableEntrega;
        this.receptor = receptor;
        this.firmaDigital = firmaDigital;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public Donacion getDonacion() {
        return donacion;
    }

    public void setDonacion(Donacion donacion) {
        this.donacion = donacion;
    }

    public LocalDateTime getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDateTime fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getResponsableEntrega() {
        return responsableEntrega;
    }

    public void setResponsableEntrega(String responsableEntrega) {
        this.responsableEntrega = responsableEntrega;
    }

    public Receptor getReceptor() {
        return receptor;
    }

    public void setReceptor(Receptor receptor) {
        this.receptor = receptor;
    }

    public String getFirmaDigital() {
        return firmaDigital;
    }

    public void setFirmaDigital(String firmaDigital) {
        this.firmaDigital = firmaDigital;
    }
}
