package com.bancodealimentos.api_gateway.model;

import lombok.Data;
import java.util.List;

@Data
public class ReceptorDTO {
    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;
    private List<RegistroEntregaDTO> registros;
}
