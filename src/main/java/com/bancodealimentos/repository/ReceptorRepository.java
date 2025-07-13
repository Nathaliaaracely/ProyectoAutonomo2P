package com.bancodealimentos.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bancodealimentos.model.Receptor;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ReceptorRepository extends JpaRepository<Receptor, Long> {
    List<Receptor> findByNombre(String nombre);
    List<Receptor> findByTipo(String tipo); // Buscar por tipo: PERSONA o ORGANIZACION
    List<Receptor> findByCorreo(String correo);
    List<Receptor> findByContacto(String contacto);
    List<Receptor> findByDireccion(String direccion);
    // Puedes agregar más métodos de consulta según sea necesario
    // Por ejemplo, para buscar por nombre y tipo:
    List<Receptor> findByNombreAndTipo(String nombre, String tipo);
    // O para buscar por nombre y correo:
    List<Receptor> findByNombreAndCorreo(String nombre, String correo);
    // O para buscar por nombre y contacto:
    List<Receptor> findByNombreAndContacto(String nombre, String contacto);
    // O para buscar por nombre y dirección:
}
package com.bancodealimentos.repository;
import com.bancodealimentos.model.Receptor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReceptorRepository extends JpaRepository<Receptor, Long> {
    // Métodos de consulta para la entidad Receptor
}
