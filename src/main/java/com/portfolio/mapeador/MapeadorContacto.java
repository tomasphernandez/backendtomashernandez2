package com.portfolio.mapeador;

import com.portfolio.dto.EntradaContacto;
import com.portfolio.dto.SalidaContacto;
import com.portfolio.entidad.Contacto;
import com.portfolio.repositorio.PersonaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapeadorContacto {

    @Autowired
    private PersonaRepositorio personaRepositorio;

    public Contacto entradaContactoAContacto(EntradaContacto entradaContacto) {
        Contacto contacto = Contacto.builder()
                .contacto(entradaContacto.getContacto())
                .descripcion(entradaContacto.getDescripcion())
                .build();

        if (entradaContacto.getIdPersona() != null) {
            contacto.setPersona(personaRepositorio.findById(entradaContacto.getIdPersona())
                    .orElseThrow(() -> new RuntimeException("Persona no encontrada")));
        }

        return contacto;
    }

    public Contacto entradaContactoAContacto(Contacto contacto, EntradaContacto entradaContacto) {
        contacto.setContacto(entradaContacto.getContacto());
        contacto.setDescripcion(entradaContacto.getDescripcion());
        contacto.setPersona(personaRepositorio.findById(entradaContacto.getIdPersona())
                .orElseThrow(() -> new RuntimeException("Persona no encontrada")));

        return contacto;
    }

    public SalidaContacto contactoASalidaContacto(Contacto contacto) {
        return SalidaContacto.builder()
                .id(contacto.getId())
                .contacto(contacto.getContacto())
                .descripcion(contacto.getDescripcion())
                .build();
    }

}
