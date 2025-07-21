package com.bancodealimentos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "receptores")
public class Receptores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "tipo", nullable = false)
    private String tipo; // persona o fundación

    @Column(name = "ubicacion", nullable = false)
    private String ubicacion;

    @Column(name = "telefono", nullable = false)
    private String telefono;

    // Constructor vacío obligatorio para JPA
    public Receptores() {
    }

    // Constructor con parámetros
    public Receptores(String nombre, String email, String tipo, String ubicacion, String telefono) {
        this.nombre = nombre;
        this.email = email;
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.telefono = telefono;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Receptores{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", tipo='" + tipo + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
