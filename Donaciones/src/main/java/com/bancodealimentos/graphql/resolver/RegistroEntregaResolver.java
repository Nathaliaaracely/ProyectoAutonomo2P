package com.bancodealimentos.graphql.resolver;

import com.bancodealimentos.model.RegistroEntrega;
import com.bancodealimentos.service.RegistroEntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

@Controller
public class RegistroEntregaResolver {

    @Autowired
    private RegistroEntregaService entregaService;

    @QueryMapping
    public List<RegistroEntrega> registrosEntrega() {
        return entregaService.getAll();
    }

    @QueryMapping
    public RegistroEntrega registroEntrega(@Argument Long id) {
        return entregaService.getById(id)
                .orElseThrow(() -> new RuntimeException("Registro de entrega no encontrado con ID: " + id));
    }

    @MutationMapping
    public RegistroEntrega crearRegistroEntrega(
            @Argument String fechaEntrega,
            @Argument Long donacionId,
            @Argument Long receptorId,
            @Argument String observaciones
    ) {
        RegistroEntrega entrega = new RegistroEntrega();
        entrega.setFechaEntrega(LocalDate.parse(fechaEntrega));
        entrega.setDonacionId(donacionId);
        entrega.setReceptorId(receptorId);
        entrega.setResponsableEntrega("Sistema");
        entrega.setCantidadProductos(0);
        entrega.setFirmaDigital(observaciones);
        return entregaService.save(entrega);
    }

    @MutationMapping
    public Boolean eliminarRegistroEntrega(@Argument Long id) {
        if (entregaService.getById(id).isPresent()) {
            entregaService.delete(id);
            return true;
        }
        return false;
    }
}
