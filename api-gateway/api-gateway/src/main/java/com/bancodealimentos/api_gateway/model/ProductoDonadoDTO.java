package com.bancodealimentos.api_gateway.model;

import lombok.Data;

@Data
public class ProductoDonadoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private int cantidad;
    private String fechaVencimiento;
    private DonacionDTO donacion;
}
