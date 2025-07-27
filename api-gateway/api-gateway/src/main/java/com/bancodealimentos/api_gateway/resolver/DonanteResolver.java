package com.bancodealimentos.api_gateway.resolver;

import com.bancodealimentos.api_gateway.model.DonanteDTO;
import com.bancodealimentos.api_gateway.model.DonanteInput;
import com.bancodealimentos.api_gateway.service.DonanteService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DonanteResolver {

    private final DonanteService donanteService;

    @QueryMapping
    public List<DonanteDTO> donantes() {
        return donanteService.obtenerTodosLosDonantes();
    }

    @QueryMapping
    public DonanteDTO donante(@Argument Long id) {
        return donanteService.obtenerDonantePorId(id);
    }

    @MutationMapping
    public DonanteDTO crearDonante(@Argument("input") DonanteInput input) {
        return donanteService.crearDonante(input);
    }

    @MutationMapping
    public DonanteDTO actualizarDonante(
            @Argument Long id,
            @Argument("input") DonanteInput input) {
        return donanteService.actualizarDonante(id, input);
    }

    @MutationMapping
    public Boolean eliminarDonante(@Argument Long id) {
        return donanteService.eliminarDonante(id);
    }
}
