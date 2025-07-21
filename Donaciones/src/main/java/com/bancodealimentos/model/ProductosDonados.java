package com.bancodealimentos.model;
import jakarta.persistence.*;
import java.time.LocalDate;
    


@Entity
@Table(name = "productos_donados")
public class ProductosDonados {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "donacion_id", nullable = false)
    private Long donacionId;

    @Column(name = "producto_nombre", nullable = false)
    private String productoNombre;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "tipo_producto", nullable = false)
    private String tipoProducto; // alimentos, ropa, etc.

    @Column(name = "unidad_medida", nullable = false)
    private String unidadMedida; // kg, litros, unidades, etc.

    @Column(name = "fecha_vencimiento")
    private LocalDate fechaVencimiento;
    

    
    public Long getDonacionId() {
        return donacionId;
    }

    public void setDonacionId(Long donacionId) {
        this.donacionId = donacionId;
    }

    public String getProductoNombre() {
        return productoNombre;
    }

    public void setProductoNombre(String productoNombre) {
        this.productoNombre = productoNombre;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public String toString() {
        return "ProductosDonados{" +
                "id=" + id +
                ", donacionId=" + donacionId +
                ", productoNombre='" + productoNombre + '\'' +
                ", cantidad=" + cantidad +
                ", tipoProducto='" + tipoProducto + '\'' +
                ", unidadMedida='" + unidadMedida + '\'' +
                ", fechaVencimiento=" + fechaVencimiento +
                '}';
    }
}
