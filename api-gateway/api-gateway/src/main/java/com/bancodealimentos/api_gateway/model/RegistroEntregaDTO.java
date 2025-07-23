package com.bancodealimentos.api_gateway.model;

import lombok.Data;
import java.util.List;

@Data
public class RegistroEntregaDTO {
    private Long id;
    private String fecha;
    private ReceptorDTO receptor;
    private List<ProductoDonadoDTO> productos;
    private String observaciones;
}
