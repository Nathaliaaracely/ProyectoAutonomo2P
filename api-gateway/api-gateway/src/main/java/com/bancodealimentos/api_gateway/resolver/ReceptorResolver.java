package com.bancodealimentos.api_gateway.resolver;

import com.bancodealimentos.api_gateway.model.ReceptorDTO;
import com.bancodealimentos.api_gateway.model.ReceptorInput;
import com.bancodealimentos.api_gateway.service.ReceptorService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReceptorResolver {

    private final ReceptorService receptorService;

    @QueryMapping
    public List<ReceptorDTO> receptores() {
        return receptorService.obtenerTodosLosReceptores();
    }

    @QueryMapping
    public ReceptorDTO receptor(@Argument Long id) {
        return receptorService.obtenerReceptorPorId(id);
    }

    @MutationMapping
    public ReceptorDTO crearReceptor(@Argument("input") ReceptorInput input) {
        return receptorService.crearReceptor(input);
    }

    @MutationMapping
    public ReceptorDTO actualizarReceptor(
            @Argument Long id,
            @Argument("input") ReceptorInput input) {
        return receptorService.actualizarReceptor(id, input);
    }

    @MutationMapping
    public Boolean eliminarReceptor(@Argument Long id) {
        return receptorService.eliminarReceptor(id);
    }
}
