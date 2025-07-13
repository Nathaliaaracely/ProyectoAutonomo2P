package com.bancodealimentos.repository;
import com.bancodealimentos.model.RegistroEntrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
@Repository
public interface RegistroEntregaRepository extends JpaRepository<RegistroEntrega, Long> {
    List<RegistroEntrega> findByReceptorId(Long receptorId);
    List<RegistroEntrega> findByFechaEntrega(String fechaEntrega);
    List<RegistroEntrega> findByEstado(String estado);
    // Puedes agregar más métodos de consulta según sea necesario
}
