package com.portfolio.mapeador;

import com.portfolio.dto.EntradaTrabajo;
import com.portfolio.dto.SalidaTrabajo;
import com.portfolio.entidad.Trabajo;
import com.portfolio.repositorio.PersonaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MapeadorTrabajo {

    @Autowired
    private PersonaRepositorio personaRepositorio;

    public Trabajo entradaTrabajoATrabajo(EntradaTrabajo entradaTrabajo) {
        Trabajo trabajo = Trabajo.builder()
                .fechaInicio(entradaTrabajo.getFechaInicio())
                .fechaFinalizacion(fechaFinalizacion(entradaTrabajo.getFechaFinalizacion(), entradaTrabajo.getPresente()))
                .descripcion(entradaTrabajo.getDescripcion())
                .nombreEmpresa(entradaTrabajo.getNombreEmpresa())
                .rol(entradaTrabajo.getRol())
                .build();

        if (entradaTrabajo.getIdPersona() != null) {
            trabajo.setPersona(personaRepositorio.findById(entradaTrabajo.getIdPersona())
                    .orElseThrow(() -> new RuntimeException("Persona no encontrada")));
        }

        return trabajo;
    }

    public Trabajo entradaTrabajoATrabajo(Trabajo trabajo, EntradaTrabajo entradaTrabajo) {
        trabajo.setNombreEmpresa(entradaTrabajo.getNombreEmpresa());
        trabajo.setDescripcion(entradaTrabajo.getDescripcion());
        trabajo.setRol(entradaTrabajo.getRol());
        trabajo.setFechaInicio(entradaTrabajo.getFechaInicio());
        trabajo.setFechaFinalizacion(fechaFinalizacion(entradaTrabajo.getFechaFinalizacion(), entradaTrabajo.getPresente()));

        if (entradaTrabajo.getIdPersona() != null) {
            trabajo.setPersona(personaRepositorio.findById(entradaTrabajo.getIdPersona())
                    .orElseThrow(() -> new RuntimeException("Persona no encontrada")));
        }

        return trabajo;
    }

    public SalidaTrabajo trabajoASalidaTrabajo(Trabajo trabajo) {
        return SalidaTrabajo.builder()
                .id(trabajo.getId())
                .descripcionTrabajo(trabajo.getDescripcion())
                .fechaInicio(trabajo.getFechaInicio())
                .fechaFinalizacion(trabajo.getFechaFinalizacion())
                .nombreEmpresa(trabajo.getNombreEmpresa())
                .rol(trabajo.getRol())
                .build();
    }

    private LocalDate fechaFinalizacion(LocalDate fechaFinalizacion, Boolean presente) {
        if (Boolean.TRUE.equals(presente)) {
            return LocalDate.of(2100, 1, 1);
        }
        return fechaFinalizacion;
    }
}
