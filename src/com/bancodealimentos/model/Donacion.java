package com.bancodealimentos.model;
import jakarta.persistence.*; 
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "donacion")

public class Donacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_donacion", nullable = false)
    private String tipoDonacion; // Tipo de donación (ej. alimentos, dinero, ropa, otros)

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal cantidad;

    @Column(nullable = false)
    private String estado; // Estado de la donación (ej. pendiente, aceptada, rechazada)

    @Column(nullable = false)
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "donante_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_donancion_donante"))
    private Donante donante;

    @ManyToOne
    @JoinColumn(name = "receptor_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_donancion_receptor"))
    private Receptor receptor;

    //constructor
    public Donacion() {
        this.fecha = LocalDateTime.now(); // Inicializa la fecha al momento de crear la donación
    }

    public Donacion(String tipoDonacion, BigDecimal cantidad, String estado, Donante donante, Receptor receptor) {
        this.tipoDonacion = tipoDonacion;
        this.cantidad = cantidad;
        this.estado = estado;
        this.fecha = LocalDateTime.now();
        this.donante = donante;
        this.receptor = receptor;

    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getTipoDonacion() {
        return tipoDonacion;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Donante getDonante() {
        return donante;
    }

    public void setDonante(Donante donante) {
        this.donante = donante;
    }

    public Receptor getReceptor() {
        return receptor;
    }

    public void setReceptor(Receptor receptor) {
        this.receptor = receptor;
    }
}