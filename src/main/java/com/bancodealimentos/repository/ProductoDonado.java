package com.bancodealimentos.repository;
import com.bancodealimentos.model.ProductoDonado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ProductoDonadoRepository extends JpaRepository<ProductoDonado, Long> {
    List<ProductoDonado> findByNombre(String nombre);
    List<ProductoDonado> findByTipoProducto(String tipoProducto);
    List<ProductoDonado> findByFechaVencimiento(LocalDate fechaVencimiento);
    List<ProductoDonado> findByCantidad(BigDecimal cantidad);
    List<ProductoDonado> findByUnidad(String unidad);
    // Puedes agregar más métodos de consulta según sea necesario
}
package com.bancodealimentos.model;
import jakarta.persistence.*;

@Entity
@Table(name = "productos_donados")
public class ProductoDonado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private Integer cantidad;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
