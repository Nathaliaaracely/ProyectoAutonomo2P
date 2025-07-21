package com.bancodealimentos.api_gateway.service;

import com.bancodealimentos.api_gateway.model.DonacionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class DonacionService {
    private final WebClient webClient;

    public DonacionService(WebClient.Builder webClientBuilder, 
                         @Value("${donaciones.service.url}") String baseUrl) {
        this.webClient = webClientBuilder
            .baseUrl(baseUrl)
            .build();
    }

    public Flux<DonacionDTO> obtenerTodasLasDonaciones() {
        return webClient.get()
                .uri("/donaciones")
                .retrieve()
                .bodyToFlux(DonacionDTO.class)
                .doOnError(error -> log.error("Error al obtener donaciones: {}", error.getMessage()));
    }

    public Mono<DonacionDTO> obtenerDonacionPorId(Long id) {
        return webClient.get()
                .uri("/donaciones/{id}", id)
                .retrieve()
                .bodyToMono(DonacionDTO.class)
                .doOnError(error -> log.error("Error al obtener donación con id {}: {}", id, error.getMessage()));
    }

    public Mono<DonacionDTO> crearDonacion(DonacionDTO donacion) {
        return webClient.post()
                .uri("/donaciones")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(donacion)
                .retrieve()
                .bodyToMono(DonacionDTO.class)
                .doOnError(error -> log.error("Error al crear donación: {}", error.getMessage()));
    }

    public Mono<DonacionDTO> actualizarDonacion(Long id, DonacionDTO donacion) {
        return webClient.put()
                .uri("/donaciones/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(donacion)
                .retrieve()
                .bodyToMono(DonacionDTO.class)
                .doOnError(error -> log.error("Error al actualizar donación con id {}: {}", id, error.getMessage()));
    }

    public Mono<Void> eliminarDonacion(Long id) {
        return webClient.delete()
                .uri("/donaciones/{id}", id)
                .retrieve()
                .bodyToMono(Void.class)
                .doOnError(error -> log.error("Error al eliminar donación con id {}: {}", id, error.getMessage()));
    }
}