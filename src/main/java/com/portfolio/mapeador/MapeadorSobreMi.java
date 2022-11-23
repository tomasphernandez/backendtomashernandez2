package com.portfolio.mapeador;

import com.portfolio.dto.EntradaSobreMi;
import com.portfolio.dto.SalidaSobreMi;
import com.portfolio.entidad.SobreMi;
import com.portfolio.repositorio.PersonaRepositorio;
import com.portfolio.repositorio.SobreMiRepositorio;
import com.portfolio.servicio.implementacion.ArchivosServicioImplementacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class MapeadorSobreMi {

    @Autowired
    private PersonaRepositorio personaRepositorio;

    @Autowired
    private SobreMiRepositorio sobreMiRepositorio;

    @Autowired
    private ArchivosServicioImplementacion archivosServicioImplementacion;

    public SalidaSobreMi sobreMiASalidaSobreMi(SobreMi sobreMi) {
        SalidaSobreMi salidaSobreMi = SalidaSobreMi.builder()
                .id(sobreMi.getId())
                .sobreMi(sobreMi.getSobreMi())
                .profesion(sobreMi.getProfesion())
                .build();

        if (sobreMi.getImagen() != null) {
            byte[] imagen = archivosServicioImplementacion.descargarArchivo(sobreMi.getImagen());
            if (imagen == null) {
                sobreMi.setImagen(null);
                sobreMiRepositorio.save(sobreMi);
            } else {
                salidaSobreMi.setImagen(imagen);
            }
        }
        return salidaSobreMi;
    }

    public SobreMi entradaSobreMiASobreMi(MultipartFile archivo, SobreMi sobreMi, EntradaSobreMi entradaSobreMi) {
        sobreMi.setSobreMi(entradaSobreMi.getSobreMi());
        sobreMi.setProfesion(entradaSobreMi.getProfesion());

        if (entradaSobreMi.getIdPersona() != null) {
            sobreMi.setPersona(personaRepositorio.findById(entradaSobreMi.getIdPersona())
                    .orElseThrow(() -> new RuntimeException("Persona no encontrada")));
        }

        if (sobreMi.getImagen() != null) {
            archivosServicioImplementacion.borrarArchivo(sobreMi.getImagen());
        }
        sobreMi.setImagen(archivosServicioImplementacion.guardarArchivo(archivo).toString());

        return sobreMi;
    }

}
