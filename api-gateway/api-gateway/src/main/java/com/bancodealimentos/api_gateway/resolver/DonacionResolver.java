package com.bancodealimentos.api_gateway.resolver;

import com.bancodealimentos.api_gateway.model.DonacionDTO;
import com.bancodealimentos.api_gateway.model.DonacionInput;
import com.bancodealimentos.api_gateway.service.DonacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DonacionResolver {

    private final DonacionService donacionService;

    @QueryMapping
    public List<DonacionDTO> donaciones() {
        return donacionService.obtenerTodasLasDonaciones();
    }

    @QueryMapping
    public DonacionDTO donacion(@Argument Long id) {
        return donacionService.obtenerDonacionPorId(id);
    }

    @MutationMapping
    public DonacionDTO crearDonacion(@Argument("input") DonacionInput input) {
        return donacionService.crearDonacion(input);
    }

    @MutationMapping
    public DonacionDTO actualizarDonacion(
            @Argument Long id,
            @Argument("input") DonacionInput input) {
        return donacionService.actualizarDonacion(id, input);
    }

    @MutationMapping
    public Boolean eliminarDonacion(@Argument Long id) {
        return donacionService.eliminarDonacion(id);
    }
}