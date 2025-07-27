package com.bancodealimentos.api_gateway.service;

import com.bancodealimentos.api_gateway.model.ReceptorDTO;
import com.bancodealimentos.api_gateway.model.ReceptorInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReceptorService {

    private final WebClient webClient;
    private static final String RECEPTORES_SERVICE_URL = "http://receptores-service/api/receptores";

    public List<ReceptorDTO> obtenerTodosLosReceptores() {
        return Arrays.asList(webClient.get()
                .uri(RECEPTORES_SERVICE_URL)
                .retrieve()
                .bodyToMono(ReceptorDTO[].class)
                .block());
    }

    public ReceptorDTO obtenerReceptorPorId(Long id) {
        return webClient.get()
                .uri(RECEPTORES_SERVICE_URL + "/{id}", id)
                .retrieve()
                .bodyToMono(ReceptorDTO.class)
                .block();
    }

    public ReceptorDTO crearReceptor(ReceptorInput input) {
        return webClient.post()
                .uri(RECEPTORES_SERVICE_URL)
                .bodyValue(input)
                .retrieve()
                .bodyToMono(ReceptorDTO.class)
                .block();
    }

    public ReceptorDTO actualizarReceptor(Long id, ReceptorInput input) {
        return webClient.put()
                .uri(RECEPTORES_SERVICE_URL + "/{id}", id)
                .bodyValue(input)
                .retrieve()
                .bodyToMono(ReceptorDTO.class)
                .block();
    }

    public Boolean eliminarReceptor(Long id) {
        return webClient.delete()
                .uri(RECEPTORES_SERVICE_URL + "/{id}", id)
                .retrieve()
                .toBodilessEntity()
                .map(response -> response.getStatusCode().is2xxSuccessful())
                .block();
    }
}
