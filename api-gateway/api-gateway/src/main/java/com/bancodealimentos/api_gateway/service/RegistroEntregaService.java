package com.bancodealimentos.api_gateway.service;

import com.bancodealimentos.api_gateway.model.RegistroEntregaDTO;
import com.bancodealimentos.api_gateway.model.RegistroEntregaInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistroEntregaService {

    private final WebClient webClient;
    private static final String REGISTROS_ENTREGA_SERVICE_URL = "http://entregas-service/api/registros-entrega";

    public List<RegistroEntregaDTO> obtenerTodosLosRegistrosEntrega() {
        return Arrays.asList(webClient.get()
                .uri(REGISTROS_ENTREGA_SERVICE_URL)
                .retrieve()
                .bodyToMono(RegistroEntregaDTO[].class)
                .block());
    }

    public RegistroEntregaDTO obtenerRegistroEntregaPorId(Long id) {
        return webClient.get()
                .uri(REGISTROS_ENTREGA_SERVICE_URL + "/{id}", id)
                .retrieve()
                .bodyToMono(RegistroEntregaDTO.class)
                .block();
    }

    public RegistroEntregaDTO crearRegistroEntrega(RegistroEntregaInput input) {
        return webClient.post()
                .uri(REGISTROS_ENTREGA_SERVICE_URL)
                .bodyValue(input)
                .retrieve()
                .bodyToMono(RegistroEntregaDTO.class)
                .block();
    }

    public RegistroEntregaDTO actualizarRegistroEntrega(Long id, RegistroEntregaInput input) {
        return webClient.put()
                .uri(REGISTROS_ENTREGA_SERVICE_URL + "/{id}", id)
                .bodyValue(input)
                .retrieve()
                .bodyToMono(RegistroEntregaDTO.class)
                .block();
    }

    public Boolean eliminarRegistroEntrega(Long id) {
        return webClient.delete()
                .uri(REGISTROS_ENTREGA_SERVICE_URL + "/{id}", id)
                .retrieve()
                .toBodilessEntity()
                .map(response -> response.getStatusCode().is2xxSuccessful())
                .block();
    }
}
