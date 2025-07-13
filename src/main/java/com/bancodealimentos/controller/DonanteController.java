package com.bancodealimentos.model;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
@Table(name = "donante")
public class Donante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String tipo; // Tipo de donante (ej. persona, empresa, organización)

    @Column(nullable = false)
    private String contacto; // Información de contacto del donante
    @Column(nullable = false)
    private String correo; // Correo electrónico del donante
    @Column(nullable = false)
    private String direccion; // Dirección del donante
    // Constructores
    public Donante() {
    }
    public Donante(String nombre, String tipo, String contacto, String correo, String direccion) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.contacto = contacto;
        this.correo = correo;
        this.direccion = direccion;
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
    public String getContacto() {
        return contacto;
    }
    public void setContacto(String contacto) {
        this.contacto = contacto;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }