package com.bancodealimentos.api_gateway.resolver;

import com.bancodealimentos.api_gateway.model.DonacionDTO;
import com.bancodealimentos.api_gateway.model.DonacionInput;
import com.bancodealimentos.api_gateway.service.DonacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Controller
@RequiredArgsConstructor
public class DonacionResolver {
    
    private final DonacionService donacionService;

    @QueryMapping
    public Flux<DonacionDTO> donaciones() {
        return donacionService.obtenerTodasLasDonaciones();
    }

    @QueryMapping
    public Mono<DonacionDTO> donacion(@Argument Long id) {
        return donacionService.obtenerDonacionPorId(id);
    }

    @MutationMapping
    public Mono<DonacionDTO> crearDonacion(@Argument DonacionInput input) {
        DonacionDTO donacion = new DonacionDTO();
        donacion.setMonto(BigDecimal.valueOf(input.getMonto()));
        donacion.setDonanteId(input.getDonanteId());
        donacion.setFecha(input.getFecha());
        return donacionService.crearDonacion(donacion);
    }

    @MutationMapping
    public Mono<DonacionDTO> actualizarDonacion(
            @Argument Long id,
            @Argument DonacionInput input) {
        DonacionDTO donacion = new DonacionDTO();
        donacion.setId(id);
        donacion.setMonto(BigDecimal.valueOf(input.getMonto()));
        donacion.setDonanteId(input.getDonanteId());
        donacion.setFecha(input.getFecha());
        return donacionService.actualizarDonacion(id, donacion);
    }

    @MutationMapping
    public Mono<Boolean> eliminarDonacion(@Argument Long id) {
        return donacionService.eliminarDonacion(id)
                .thenReturn(true)
                .onErrorReturn(false);
    }
}