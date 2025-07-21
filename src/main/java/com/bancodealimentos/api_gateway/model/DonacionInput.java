package com.bancodealimentos.api_gateway.model;

import lombok.Data;

@Data
public class DonacionInput {
    private Double monto;
    private Long donanteId;
    private String fecha;
}
