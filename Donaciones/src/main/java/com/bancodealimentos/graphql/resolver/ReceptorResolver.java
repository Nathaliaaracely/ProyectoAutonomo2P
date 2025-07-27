package com.bancodealimentos.graphql.resolver;

import com.bancodealimentos.model.Receptores;
import com.bancodealimentos.service.ReceptoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ReceptorResolver {

    @Autowired
    private ReceptoresService receptorService;

    @QueryMapping
    public List<Receptores> receptores() {
        return receptorService.getAll();
    }

    @QueryMapping
    public Receptores receptor(@Argument Long id) {
        return receptorService.getById(id)
                .orElseThrow(() -> new RuntimeException("Receptor no encontrado con ID: " + id));
    }

    @MutationMapping
    public Receptores crearReceptor(
            @Argument String nombre,
            @Argument String correo,
            @Argument String telefono,
            @Argument String direccion
    ) {
        // Usando el constructor completo con todos los campos requeridos
        // tipo: "persona" por defecto, ubicacion: "Desconocida" como valor por defecto
        Receptores receptor = new Receptores(
            nombre, 
            "persona", // tipo
            "Desconocida", // ubicacion
            direccion, 
            telefono, 
            correo
        );
        return receptorService.save(receptor);
    }

    @MutationMapping
    public Receptores actualizarReceptor(
            @Argument Long id,
            @Argument String nombre,
            @Argument String correo,
            @Argument String telefono,
            @Argument String direccion
    ) {
        // Primero obtenemos el receptor existente
        Receptores receptor = receptorService.getById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el receptor con ID: " + id));
        
        // Actualizamos solo los campos proporcionados
        if (nombre != null) receptor.setNombre(nombre);
        if (correo != null) receptor.setEmail(correo);
        if (telefono != null) receptor.setTelefono(telefono);
        if (direccion != null) receptor.setDireccion(direccion);
        
        return receptorService.save(receptor);
    }

    @MutationMapping
    public Boolean eliminarReceptor(@Argument Long id) {
        if (receptorService.getById(id).isPresent()) {
            receptorService.delete(id);
            return true;
        }
        return false;
    }
}
