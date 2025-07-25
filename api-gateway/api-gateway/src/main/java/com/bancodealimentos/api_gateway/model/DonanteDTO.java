package com.bancodealimentos.api_gateway.model;

import lombok.Data;
import java.util.List;

@Data
public class DonanteDTO {
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private List<DonacionDTO> donaciones;
    
    // Constructor, getters y setters son generados por Lombok con @Data
}
