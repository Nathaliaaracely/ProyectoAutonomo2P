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

    @Column(name = "contacto")
    private String contacto;

    @Column(name = "direccion")
    private String direccion;

    // Constructor vacío para JPA
    public Donantes() {}

    // Constructor con parámetros
    public Donantes(String nombre, String email, String tipo, String telefono, String contacto, String direccion) {
        this.nombre = nombre;
        this.email = email;
        this.tipo = tipo;
        this.telefono = telefono;
        this.contacto = contacto;
        this.direccion = direccion;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
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

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Donantes{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", tipo='" + tipo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", contacto='" + contacto + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
