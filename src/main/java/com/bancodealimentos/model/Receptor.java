package com.bancodealimentos.model;

import jakarta.persistence.*;

/**
 * Representa a una persona o fundación que recibe donaciones.
 */
@Entity
@Table(name = "receptor")
public class Receptor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String tipo; // FUNDACION o PERSONA

    private String necesidad;

    private String ubicacion;

    private String contacto;

    // Constructores
    public Receptor() {
    }

    public Receptor(String nombre, String tipo, String necesidad, String ubicacion, String contacto) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.necesidad = necesidad;
        this.ubicacion = ubicacion;
        this.contacto = contacto;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNecesidad() {
        return necesidad;
    }

    public void setNecesidad(String necesidad) {
        this.necesidad = necesidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }
}
