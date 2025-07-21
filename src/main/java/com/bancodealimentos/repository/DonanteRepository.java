package com.bancodealimentos.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bancodealimentos.model.Donante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DonanteRepository extends JpaRepository<Donante, Long> {
    List<Donante> findByNombre(String nombre);
    List<Donante> findByTipo(String tipo); // Buscar por tipo: PERSONA o EMPRESA
    List<Donante> findByCorreo(String correo);
    List<Donante> findByContacto(String contacto);
    List<Donante> findByDireccion(String direccion);
    // Puedes agregar más métodos de consulta según sea necesario
    // Por ejemplo, para buscar por nombre y tipo:
    List<Donante> findByNombreAndTipo(String nombre, String tipo);
    // O para buscar por nombre y correo:
    List<Donante> findByNombreAndCorreo(String nombre, String correo);
    // O para buscar por nombre y contacto:
    List<Donante> findByNombreAndContacto(String nombre, String contacto);
    // O para buscar por nombre y dirección:
}

