package com.bancodealimentos.api_gateway.resolver;

import com.bancodealimentos.api_gateway.model.RegistroEntregaDTO;
import com.bancodealimentos.api_gateway.model.RegistroEntregaInput;
import com.bancodealimentos.api_gateway.service.RegistroEntregaService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RegistroEntregaResolver {

    private final RegistroEntregaService registroEntregaService;

    @QueryMapping
    public List<RegistroEntregaDTO> registrosEntrega() {
        return registroEntregaService.obtenerTodosLosRegistrosEntrega();
    }

    @QueryMapping
    public RegistroEntregaDTO registroEntrega(@Argument Long id) {
        return registroEntregaService.obtenerRegistroEntregaPorId(id);
    }

    @MutationMapping
    public RegistroEntregaDTO crearRegistroEntrega(@Argument("input") RegistroEntregaInput input) {
        return registroEntregaService.crearRegistroEntrega(input);
    }

    @MutationMapping
    public RegistroEntregaDTO actualizarRegistroEntrega(
            @Argument Long id,
            @Argument("input") RegistroEntregaInput input) {
        return registroEntregaService.actualizarRegistroEntrega(id, input);
    }

    @MutationMapping
    public Boolean eliminarRegistroEntrega(@Argument Long id) {
        return registroEntregaService.eliminarRegistroEntrega(id);
    }
}
