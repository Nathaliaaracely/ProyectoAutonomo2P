package com.bancodealimentos.api_gateway.model;

import lombok.Data;

@Data
public class DonanteDTO {
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    
    // Constructor, getters y setters son generados por Lombok con @Data
}
