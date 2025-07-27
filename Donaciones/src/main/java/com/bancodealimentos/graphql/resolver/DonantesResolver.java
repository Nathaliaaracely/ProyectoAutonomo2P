package com.bancodealimentos.graphql.resolver;

import com.bancodealimentos.model.Donantes;
import com.bancodealimentos.service.DonantesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class DonantesResolver {

    private final DonantesService donantesService;

    @Autowired
    public DonantesResolver(DonantesService donantesService) {
        this.donantesService = donantesService;
    }

    @QueryMapping
    public List<Donantes> donantes() {
        return donantesService.getAll();
    }

    @QueryMapping
    public Donantes donante(@Argument Long id) {
        return donantesService.getById(id)
                .orElseThrow(() -> new RuntimeException("Donante no encontrado con id: " + id));
    }

    @MutationMapping
    public Donantes crearDonante(
            @Argument String nombre,
            @Argument String email,
            @Argument String telefono,
            @Argument String direccion) {
        
        Donantes donante = new Donantes();
        donante.setNombre(nombre);
        donante.setEmail(email);
        donante.setTelefono(telefono);
        donante.setDireccion(direccion);
        donante.setTipo("persona"); // Valor por defecto
        
        return donantesService.save(donante);
    }

    @MutationMapping
    public Donantes actualizarDonante(
            @Argument Long id,
            @Argument String nombre,
            @Argument String email,
            @Argument String telefono,
            @Argument String direccion) {
        
        Donantes existente = donantesService.getById(id)
                .orElseThrow(() -> new RuntimeException("Donante no encontrado con id: " + id));
        
        if (nombre != null) existente.setNombre(nombre);
        if (email != null) existente.setEmail(email);
        if (telefono != null) existente.setTelefono(telefono);
        if (direccion != null) existente.setDireccion(direccion);
        
        return donantesService.save(existente);
    }

    @MutationMapping
    public Boolean eliminarDonante(@Argument Long id) {
        donantesService.delete(id);
        return true;
    }
}