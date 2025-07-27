package com.bancodealimentos.api_gateway.model;

import lombok.Data;
import java.util.List;

@Data
public class RegistroEntregaInput {
    private String fecha;
    private Long receptorId;
    private List<Long> productosIds;
    private String observaciones;
}
