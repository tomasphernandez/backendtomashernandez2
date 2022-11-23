package com.portfolio.mapeador;

import com.portfolio.dto.EntradaEducacion;
import com.portfolio.dto.SalidaEducacion;
import com.portfolio.entidad.Educacion;
import com.portfolio.repositorio.PersonaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MapeadorEducacion {

    @Autowired
    private PersonaRepositorio personaRepositorio;

    public Educacion entradaEducacionAEducacion(EntradaEducacion entradaEducacion) {
        Educacion educacion = Educacion.builder()
                .instituto(entradaEducacion.getInstituto())
                .titulo(entradaEducacion.getTitulo())
                .fechaInicio(entradaEducacion.getFechaInicio())
                .fechaFinalizacion(fechaFinalizacion(entradaEducacion.getPresente(), entradaEducacion.getFechaFinalizacion()))
                .build();

        if (entradaEducacion.getIdPersona() != null) {
            educacion.setPersona(personaRepositorio.findById(entradaEducacion.getIdPersona())
                    .orElseThrow(() -> new RuntimeException("Persona no encontrada")));
        }

        return educacion;
    }

    public Educacion entradaEducacionAEducacion(Educacion educacion, EntradaEducacion entradaEducacion) {
        educacion.setInstituto(entradaEducacion.getInstituto());
        educacion.setTitulo(entradaEducacion.getTitulo());
        educacion.setFechaInicio(entradaEducacion.getFechaInicio());
        educacion.setFechaFinalizacion(fechaFinalizacion(entradaEducacion.getPresente(), entradaEducacion.getFechaFinalizacion()));

        if (entradaEducacion.getIdPersona() != null) {
            educacion.setPersona(personaRepositorio.findById(entradaEducacion.getIdPersona())
                    .orElseThrow(() -> new RuntimeException("Persona no encontrada")));
        }

        return educacion;
    }

    public SalidaEducacion educacionASalidaEducacion(Educacion educacion) {
        return SalidaEducacion.builder()
                .id(educacion.getId())
                .instituto(educacion.getInstituto())
                .titulo(educacion.getTitulo())
                .fechaInicio(educacion.getFechaInicio())
                .fechaFinalizacion(educacion.getFechaFinalizacion())
                .build();
    }

    private LocalDate fechaFinalizacion(Boolean presente, LocalDate fechaFinalizacion) {
        if (Boolean.TRUE.equals(presente)) {
            return LocalDate.of(2100, 1, 1);
        }
        return fechaFinalizacion;
    }
}
