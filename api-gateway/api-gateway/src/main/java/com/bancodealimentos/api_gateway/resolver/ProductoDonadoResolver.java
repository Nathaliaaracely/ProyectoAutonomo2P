package com.bancodealimentos.api_gateway.resolver;

import com.bancodealimentos.api_gateway.model.ProductoDonadoDTO;
import com.bancodealimentos.api_gateway.model.ProductoDonadoInput;
import com.bancodealimentos.api_gateway.service.ProductoDonadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductoDonadoResolver {

    private final ProductoDonadoService productoDonadoService;

    @QueryMapping
    public List<ProductoDonadoDTO> productosDonados() {
        return productoDonadoService.obtenerTodosLosProductosDonados();
    }

    @QueryMapping
    public ProductoDonadoDTO productoDonado(@Argument Long id) {
        return productoDonadoService.obtenerProductoDonadoPorId(id);
    }

    @MutationMapping
    public ProductoDonadoDTO crearProductoDonado(@Argument("input") ProductoDonadoInput input) {
        return productoDonadoService.crearProductoDonado(input);
    }

    @MutationMapping
    public ProductoDonadoDTO actualizarProductoDonado(
            @Argument Long id,
            @Argument("input") ProductoDonadoInput input) {
        return productoDonadoService.actualizarProductoDonado(id, input);
    }

    @MutationMapping
    public Boolean eliminarProductoDonado(@Argument Long id) {
        return productoDonadoService.eliminarProductoDonado(id);
    }
}
