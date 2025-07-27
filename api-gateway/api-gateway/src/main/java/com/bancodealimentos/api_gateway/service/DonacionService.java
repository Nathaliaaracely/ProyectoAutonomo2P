package com.bancodealimentos.api_gateway.service;

import com.bancodealimentos.api_gateway.model.DonacionDTO;
import com.bancodealimentos.api_gateway.model.DonacionInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DonacionService {

    private final WebClient webClient;
    private static final String DONACIONES_SERVICE_URL = "http://donaciones-service/api/donaciones";

    public List<DonacionDTO> obtenerTodasLasDonaciones() {
        return Arrays.asList(webClient.get()
                .uri(DONACIONES_SERVICE_URL)
                .retrieve()
                .bodyToMono(DonacionDTO[].class)
                .block());
    }

    public DonacionDTO obtenerDonacionPorId(Long id) {
        return webClient.get()
                .uri(DONACIONES_SERVICE_URL + "/{id}", id)
                .retrieve()
                .bodyToMono(DonacionDTO.class)
                .block();
    }

    public DonacionDTO crearDonacion(DonacionInput input) {
        return webClient.post()
                .uri(DONACIONES_SERVICE_URL)
                .bodyValue(input)
                .retrieve()
                .bodyToMono(DonacionDTO.class)
                .block();
    }

    public DonacionDTO actualizarDonacion(Long id, DonacionInput input) {
        return webClient.put()
                .uri(DONACIONES_SERVICE_URL + "/{id}", id)
                .bodyValue(input)
                .retrieve()
                .bodyToMono(DonacionDTO.class)
                .block();
    }

    public Boolean eliminarDonacion(Long id) {
        return webClient.delete()
                .uri(DONACIONES_SERVICE_URL + "/{id}", id)
                .retrieve()
                .toBodilessEntity()
                .map(response -> response.getStatusCode().is2xxSuccessful())
                .block();
    }
}