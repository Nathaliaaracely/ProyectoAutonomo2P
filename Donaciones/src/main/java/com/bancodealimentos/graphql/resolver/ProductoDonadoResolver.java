package com.bancodealimentos.graphql.resolver;

import com.bancodealimentos.model.ProductosDonados;
import com.bancodealimentos.service.ProductosDonadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ProductoDonadoResolver {

    @Autowired
    private ProductosDonadosService productoService;

    @QueryMapping
    public List<ProductosDonados> productosDonados() {
        return productoService.getAll();
    }

    @QueryMapping
    public ProductosDonados productoDonado(@Argument Long id) {
        return productoService.getById(id)
                .orElseThrow(() -> new RuntimeException("Producto donado no encontrado con ID: " + id));
    }

    @MutationMapping
    public ProductosDonados crearProductoDonado(
            @Argument Long donacionId,
            @Argument String productoNombre,
            @Argument Integer cantidad,
            @Argument String tipoProducto,
            @Argument String unidadMedida,
            @Argument String fechaVencimiento
    ) {
        ProductosDonados producto = new ProductosDonados();
        producto.setDonacionId(donacionId);
        producto.setProductoNombre(productoNombre);
        producto.setCantidad(cantidad);
        producto.setTipoProducto(tipoProducto);
        producto.setUnidadMedida(unidadMedida);
        if (fechaVencimiento != null && !fechaVencimiento.isEmpty()) {
            producto.setFechaVencimiento(LocalDate.parse(fechaVencimiento));
        }
        return productoService.save(producto);
    }

    @MutationMapping
    public Boolean eliminarProductoDonado(@Argument Long id) {
        if (productoService.getById(id).isPresent()) {
            productoService.delete(id);
            return true;
        }
        return false;
    }
}
