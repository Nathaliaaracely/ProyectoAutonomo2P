package com.bancodealimentos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "donantes")
public class Donantes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "tipo", nullable = false)
    private String tipo; // persona o empresa

    @Column(name = "telefono", nullable = false)
    private String telefono;

    // Constructor vacío para JPA
    public Donantes() {}

    // Constructor con parámetros
    public Donantes(String nombre, String email, String tipo, String telefono) {
        this.nombre = nombre;
        this.email = email;
        this.tipo = tipo;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Donantes{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", tipo='" + tipo + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
