package com.bancodealimentos.api_gateway.service;

import com.bancodealimentos.api_gateway.model.ProductoDonadoDTO;
import com.bancodealimentos.api_gateway.model.ProductoDonadoInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoDonadoService {

    private final WebClient webClient;
    private static final String PRODUCTOS_DONADOS_SERVICE_URL = "http://productos-service/api/productos-donados";

    public List<ProductoDonadoDTO> obtenerTodosLosProductosDonados() {
        return Arrays.asList(webClient.get()
                .uri(PRODUCTOS_DONADOS_SERVICE_URL)
                .retrieve()
                .bodyToMono(ProductoDonadoDTO[].class)
                .block());
    }

    public ProductoDonadoDTO obtenerProductoDonadoPorId(Long id) {
        return webClient.get()
                .uri(PRODUCTOS_DONADOS_SERVICE_URL + "/{id}", id)
                .retrieve()
                .bodyToMono(ProductoDonadoDTO.class)
                .block();
    }

    public ProductoDonadoDTO crearProductoDonado(ProductoDonadoInput input) {
        return webClient.post()
                .uri(PRODUCTOS_DONADOS_SERVICE_URL)
                .bodyValue(input)
                .retrieve()
                .bodyToMono(ProductoDonadoDTO.class)
                .block();
    }

    public ProductoDonadoDTO actualizarProductoDonado(Long id, ProductoDonadoInput input) {
        return webClient.put()
                .uri(PRODUCTOS_DONADOS_SERVICE_URL + "/{id}", id)
                .bodyValue(input)
                .retrieve()
                .bodyToMono(ProductoDonadoDTO.class)
                .block();
    }

    public Boolean eliminarProductoDonado(Long id) {
        return webClient.delete()
                .uri(PRODUCTOS_DONADOS_SERVICE_URL + "/{id}", id)
                .retrieve()
                .toBodilessEntity()
                .map(response -> response.getStatusCode().is2xxSuccessful())
                .block();
    }
}
