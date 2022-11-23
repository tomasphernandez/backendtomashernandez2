package com.portfolio.servicio.implementacion;

import com.portfolio.dto.EntradaPersona;
import com.portfolio.dto.SalidaPersona;
import com.portfolio.entidad.Persona;
import com.portfolio.mapeador.MapeadorPersona;
import com.portfolio.repositorio.PersonaRepositorio;
import com.portfolio.servicio.PersonaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaServicioImplementacion implements PersonaServicio {

    @Autowired
    private PersonaRepositorio personaRepositorio;

    @Autowired
    private MapeadorPersona mapeadorPersona;

    @Override
    public SalidaPersona obtenerPersona() {
        return mapeadorPersona.personaASalidaPersona(personaRepositorio.findAll().stream().findFirst().get());
    }

    @Override
    public SalidaPersona editarPersona(Long id, EntradaPersona entradaPersona) {
        if (!personaRepositorio.existsById(id)) {
            throw new RuntimeException("Persona no encontrada");
        }

        Persona persona = personaRepositorio.findById(id).orElseThrow(() -> new RuntimeException("Persona no encontrada"));
        return mapeadorPersona.personaASalidaPersona(
                personaRepositorio.save(mapeadorPersona.entradaPersonaAPersona(persona, entradaPersona)));
    }
}
