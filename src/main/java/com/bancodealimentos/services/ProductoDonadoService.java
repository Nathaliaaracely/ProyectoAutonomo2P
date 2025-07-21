package com.bancodealimentos.services;
import com.bancodealimentos.model.ProductoDonado;
import com.bancodealimentos.repository.ProductoDonadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoDonadoService {
    private final ProductoDonadoRepository productoDonadoRepository;

    @Autowired
    public ProductoDonadoService(ProductoDonadoRepository productoDonadoRepository) {
        this.productoDonadoRepository = productoDonadoRepository;
    }

    public List<ProductoDonado> getAllProductosDonados() {
        return productoDonadoRepository.findAll();
    }

    public ProductoDonado getProductoDonadoById(Long id) {
        return productoDonadoRepository.findById(id).orElse(null);
    }

    public ProductoDonado createProductoDonado(ProductoDonado productoDonado) {
        return productoDonadoRepository.save(productoDonado);
    }

    public ProductoDonado updateProductoDonado(Long id, ProductoDonado productoDonado) {
        if (productoDonadoRepository.existsById(id)) {
            productoDonado.setId(id);
            return productoDonadoRepository.save(productoDonado);
        }
        return null;
    }

    public boolean deleteProductoDonado(Long id) {
        if (productoDonadoRepository.existsById(id)) {
            productoDonadoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
