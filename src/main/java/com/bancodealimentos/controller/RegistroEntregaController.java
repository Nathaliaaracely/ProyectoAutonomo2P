package com.bancodealimentos.controller;
import com.bancodealimentos.model.RegistroEntrega;
import jakarta.persistence.*;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public interface RegistroEntregaController {
    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/entregas")
    public List<RegistroEntrega> getAllEntregas() {
        return entityManager.createQuery("SELECT r FROM RegistroEntrega r", RegistroEntrega.class).getResultList();
    }

    @PostMapping("/entregas")
    public RegistroEntrega createEntrega(RegistroEntrega registroEntrega) {
        entityManager.persist(registroEntrega);
        return registroEntrega;
    }
    
    @GetMapping("/{id}")
    public RegistroEntrega getEntregaById(Long id) {
        return entityManager.find(RegistroEntrega.class, id);
    }

    @PostMapping("/{id}")
    public RegistroEntrega updateEntrega(Long id, RegistroEntrega registroEntrega) {
        RegistroEntrega existingRegistro = entityManager.find(RegistroEntrega.class, id);
        if (existingRegistro != null) {
            existingRegistro.setFecha(registroEntrega.getFecha());
            existingRegistro.setCantidad(registroEntrega.getCantidad());
            existingRegistro.setDonante(registroEntrega.getDonante());
            existingRegistro.setReceptor(registroEntrega.getReceptor());
            entityManager.merge(existingRegistro);
            return existingRegistro;
        }
        return null; // or throw an exception
    }
    
}
