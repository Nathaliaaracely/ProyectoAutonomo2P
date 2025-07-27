package com.bancodealimentos.graphql.resolver;

import com.bancodealimentos.model.Donaciones;
import com.bancodealimentos.model.Donantes;
import com.bancodealimentos.model.Receptores;
import com.bancodealimentos.repository.DonantesRepository;
import com.bancodealimentos.repository.ReceptoresRepository;
import com.bancodealimentos.service.DonacionesService;
import com.bancodealimentos.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class DonacionResolver {

    private final DonacionesService donacionesService;
    private final DonantesRepository donantesRepository;
    private final ReceptoresRepository receptoresRepository;

    @Autowired
    public DonacionResolver(DonacionesService donacionesService, 
                          DonantesRepository donantesRepository,
                          ReceptoresRepository receptoresRepository) {
        this.donacionesService = donacionesService;
        this.donantesRepository = donantesRepository;
        this.receptoresRepository = receptoresRepository;
    }

    @QueryMapping
    public List<Donaciones> donaciones() {
        return donacionesService.listarTodas();
    }

    @QueryMapping
    public Donaciones donacion(@Argument Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID de la donación no puede ser nulo");
        }
        return donacionesService.obtenerPorId(id)
                .orElseThrow(() -> new RuntimeException("Donación no encontrada con id: " + id));
    }

    @QueryMapping
    public List<Donaciones> donacionesPorTipo(@Argument String tipo) {
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de donación no puede estar vacío");
        }
        return donacionesService.buscarPorTipoDonacion(tipo);
    }

    @QueryMapping
    public List<Donaciones> donacionesPorDonante(@Argument Long donanteId) {
        if (donanteId == null) {
            throw new IllegalArgumentException("El ID del donante no puede ser nulo");
        }
        return donacionesService.buscarPorDonanteId(donanteId);
    }

    @QueryMapping
    public List<Donaciones> donacionesPorReceptor(@Argument Long receptorId) {
        if (receptorId == null) {
            throw new IllegalArgumentException("El ID del receptor no puede ser nulo");
        }
        return donacionesService.buscarPorReceptorId(receptorId);
    }

    @SchemaMapping(typeName = "Donacion", field = "donante")
    public Donantes donante(Donaciones donacion) {
        if (donacion.getDonante() == null) {
            return null;
        }
        return donacion.getDonante();
    }

    @SchemaMapping(typeName = "Donacion", field = "receptor")
    public Receptores receptor(Donaciones donacion) {
        if (donacion.getReceptor() == null) {
            return null;
        }
        return donacion.getReceptor();
    }

    @MutationMapping
    public Donaciones crearDonacion(
            @Argument String fechaDonacion,
            @Argument String tipoDonacion,
            @Argument String descripcion,
            @Argument Double cantidad,
            @Argument String unidadMedida,
            @Argument Long donanteId,
            @Argument Long receptorId,
            @Argument String fechaVencimiento,
            @Argument String estado) {
        
        try {
            Donaciones donacion = new Donaciones();
            donacion.setFechaDonacion(DateUtil.parseFecha(fechaDonacion));
            donacion.setTipoDonacion(tipoDonacion);
            donacion.setDescripcion(descripcion);
            donacion.setCantidad(cantidad);
            donacion.setUnidadMedida(unidadMedida);
            
            // Establecer donante si se proporciona el ID
            if (donanteId != null) {
                Donantes donante = donantesRepository.findById(donanteId)
                    .orElseThrow(() -> new IllegalArgumentException("No se encontró el donante con ID: " + donanteId));
                donacion.setDonante(donante);
            }
            
            // Establecer receptor si se proporciona el ID
            if (receptorId != null) {
                Receptores receptor = receptoresRepository.findById(receptorId)
                    .orElseThrow(() -> new IllegalArgumentException("No se encontró el receptor con ID: " + receptorId));
                donacion.setReceptor(receptor);
            }
            
            if (fechaVencimiento != null) {
                donacion.setFechaVencimiento(DateUtil.parseFecha(fechaVencimiento));
            }
            
            donacion.setEstado(estado);
            
            return donacionesService.guardar(donacion);
            
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al crear la donación: " + e.getMessage());
        }
    }

    @MutationMapping
    public Donaciones actualizarDonacion(
            @Argument Long id,
            @Argument String fechaDonacion,
            @Argument String tipoDonacion,
            @Argument String descripcion,
            @Argument Double cantidad,
            @Argument String unidadMedida,
            @Argument Long donanteId,
            @Argument Long receptorId,
            @Argument String fechaVencimiento,
            @Argument String estado) {
        
        Donaciones donacionExistente = donacionesService.obtenerPorId(id)
            .orElseThrow(() -> new RuntimeException("No se encontró la donación con ID: " + id));
        
        try {
            if (fechaDonacion != null) {
                donacionExistente.setFechaDonacion(DateUtil.parseFecha(fechaDonacion));
            }
            
            if (tipoDonacion != null) {
                donacionExistente.setTipoDonacion(tipoDonacion);
            }
            
            if (descripcion != null) {
                donacionExistente.setDescripcion(descripcion);
            }
            
            if (cantidad != null) {
                donacionExistente.setCantidad(cantidad);
            }
            
            if (unidadMedida != null) {
                donacionExistente.setUnidadMedida(unidadMedida);
            }
            
            if (donanteId != null) {
                Donantes donante = donantesRepository.findById(donanteId)
                    .orElseThrow(() -> new IllegalArgumentException("No se encontró el donante con ID: " + donanteId));
                donacionExistente.setDonante(donante);
            }
            
            if (receptorId != null) {
                Receptores receptor = receptoresRepository.findById(receptorId)
                    .orElseThrow(() -> new IllegalArgumentException("No se encontró el receptor con ID: " + receptorId));
                donacionExistente.setReceptor(receptor);
            }
            
            if (fechaVencimiento != null) {
                donacionExistente.setFechaVencimiento(DateUtil.parseFecha(fechaVencimiento));
            }
            
            if (estado != null) {
                donacionExistente.setEstado(estado);
            }
            
            return donacionesService.guardar(donacionExistente);
            
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al actualizar la donación: " + e.getMessage());
        }
    }

    @MutationMapping
    public Boolean eliminarDonacion(@Argument Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID de la donación no puede ser nulo");
        }
        donacionesService.eliminar(id);
        return true;
    }
}
