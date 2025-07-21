package com.bancodealimentos.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "registro_entrega")
public class RegistroEntrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "donacion_id", nullable = false)
    private Long donacionId;

    @Column(name = "receptor_id", nullable = false)
    private Long receptorId;

    @Column(name = "fecha_entrega", nullable = false)
    private LocalDate fechaEntrega;

    @Column(name = "cantidad_productos", nullable = false)
    private Integer cantidadProductos;

    @Column(name = "responsable_entrega", nullable = false)
    private String responsableEntrega;

    @Column(name = "firma_digital", nullable = false, columnDefinition = "TEXT")
    private String firmaDigital;

    // Constructor vacío para JPA
    public RegistroEntrega() {
    }

    // Constructor con parámetros (sin id)
    public RegistroEntrega(Long donacionId, Long receptorId, LocalDate fechaEntrega,
                          Integer cantidadProductos, String responsableEntrega, String firmaDigital) {
        this.donacionId = donacionId;
        this.receptorId = receptorId;
        this.fechaEntrega = fechaEntrega;
        this.cantidadProductos = cantidadProductos;
        this.responsableEntrega = responsableEntrega;
        this.firmaDigital = firmaDigital;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public Long getDonacionId() {
        return donacionId;
    }

    public void setDonacionId(Long donacionId) {
        this.donacionId = donacionId;
    }

    public Long getReceptorId() {
        return receptorId;
    }

    public void setReceptorId(Long receptorId) {
        this.receptorId = receptorId;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Integer getCantidadProductos() {
        return cantidadProductos;
    }

    public void setCantidadProductos(Integer cantidadProductos) {
        this.cantidadProductos = cantidadProductos;
    }

    public String getResponsableEntrega() {
        return responsableEntrega;
    }

    public void setResponsableEntrega(String responsableEntrega) {
        this.responsableEntrega = responsableEntrega;
    }

    public String getFirmaDigital() {
        return firmaDigital;
    }

    public void setFirmaDigital(String firmaDigital) {
        this.firmaDigital = firmaDigital;
    }

    @Override
    public String toString() {
        return "RegistroEntrega{" +
                "id=" + id +
                ", donacionId=" + donacionId +
                ", receptorId=" + receptorId +
                ", fechaEntrega=" + fechaEntrega +
                ", cantidadProductos=" + cantidadProductos +
                ", responsableEntrega='" + responsableEntrega + '\'' +
                ", firmaDigital='" + firmaDigital + '\'' +
                '}';
    }
}
