package com.bancodealimentos.api_gateway.service;

import com.bancodealimentos.api_gateway.model.DonanteDTO;
import com.bancodealimentos.api_gateway.model.DonanteInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DonanteService {

    private final WebClient webClient;
    private static final String DONANTES_SERVICE_URL = "http://donantes-service/api/donantes";

    public List<DonanteDTO> obtenerTodosLosDonantes() {
        return Arrays.asList(webClient.get()
                .uri(DONANTES_SERVICE_URL)
                .retrieve()
                .bodyToMono(DonanteDTO[].class)
                .block());
    }

    public DonanteDTO obtenerDonantePorId(Long id) {
        return webClient.get()
                .uri(DONANTES_SERVICE_URL + "/{id}", id)
                .retrieve()
                .bodyToMono(DonanteDTO.class)
                .block();
    }

    public DonanteDTO crearDonante(DonanteInput input) {
        return webClient.post()
                .uri(DONANTES_SERVICE_URL)
                .bodyValue(input)
                .retrieve()
                .bodyToMono(DonanteDTO.class)
                .block();
    }

    public DonanteDTO actualizarDonante(Long id, DonanteInput input) {
        return webClient.put()
                .uri(DONANTES_SERVICE_URL + "/{id}", id)
                .bodyValue(input)
                .retrieve()
                .bodyToMono(DonanteDTO.class)
                .block();
    }

    public Boolean eliminarDonante(Long id) {
        return webClient.delete()
                .uri(DONANTES_SERVICE_URL + "/{id}", id)
                .retrieve()
                .toBodilessEntity()
                .map(response -> response.getStatusCode().is2xxSuccessful())
                .block();
    }
}
