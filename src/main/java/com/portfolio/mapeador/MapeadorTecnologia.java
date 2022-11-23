package com.portfolio.mapeador;

import com.portfolio.dto.EntradaTecnologia;
import com.portfolio.dto.SalidaTecnologia;
import com.portfolio.entidad.Persona;
import com.portfolio.entidad.Tecnologia;
import com.portfolio.repositorio.PersonaRepositorio;
import com.portfolio.repositorio.TecnologiaRepositorio;
import com.portfolio.servicio.implementacion.ArchivosServicioImplementacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class MapeadorTecnologia {

    @Autowired
    private PersonaRepositorio personaRepositorio;

    @Autowired
    private TecnologiaRepositorio tecnologiaRepositorio;

    @Autowired
    private ArchivosServicioImplementacion archivosServicioImplementacion;

    public SalidaTecnologia tecnologiaASalidaTecnologia(Tecnologia tecnologia) {
        SalidaTecnologia salidaTecnologia = SalidaTecnologia.builder()
                .id(tecnologia.getId())
                .nivel(tecnologia.getNivel())
                .nombre(tecnologia.getNombreTecnologia())
                .build();

        if (tecnologia.getImagen() != null) {
            byte[] imagen = archivosServicioImplementacion.descargarArchivo(tecnologia.getImagen());
            if (imagen == null) {
                tecnologia.setImagen(null);
                tecnologiaRepositorio.save(tecnologia);
            } else {
                salidaTecnologia.setImagen(imagen);
            }
        }

        return salidaTecnologia;
    }

    public Tecnologia entradaTecnologiaATecnologia(MultipartFile archivo, EntradaTecnologia entradaTecnologia) {
        Tecnologia tecnologia = Tecnologia.builder()
                .nivel(entradaTecnologia.getNivel())
                .nombreTecnologia(entradaTecnologia.getNombre())
                .persona(persona(entradaTecnologia.getIdPersona()))
                .build();

        tecnologia.setImagen(archivosServicioImplementacion.guardarArchivo(archivo).toString());

        return tecnologia;
    }

    public Tecnologia entradaTecnologiaATecnologia(MultipartFile archivo, Tecnologia tecnologia, EntradaTecnologia entradaTecnologia) {
        tecnologia.setNombreTecnologia(entradaTecnologia.getNombre());
        tecnologia.setNivel(entradaTecnologia.getNivel());
        tecnologia.setPersona(persona(entradaTecnologia.getIdPersona()));

        if (tecnologia.getImagen() != null) {
            archivosServicioImplementacion.borrarArchivo(tecnologia.getImagen());
        }
        tecnologia.setImagen(archivosServicioImplementacion.guardarArchivo(archivo).toString());

        return tecnologia;
    }

    private Persona persona(Long idPersona) {
        if (!personaRepositorio.existsById(idPersona)) {
            throw new RuntimeException("Persona no encontrada");
        }
        return personaRepositorio.findById(idPersona)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));
    }
}
