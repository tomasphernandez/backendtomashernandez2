package com.portfolio.mapeador;

import com.portfolio.dto.EntradaProyecto;
import com.portfolio.dto.SalidaProyecto;
import com.portfolio.dto.SalidaTecnologia;
import com.portfolio.entidad.Persona;
import com.portfolio.entidad.Proyecto;
import com.portfolio.entidad.Tecnologia;
import com.portfolio.repositorio.PersonaRepositorio;
import com.portfolio.repositorio.ProyectoRepositorio;
import com.portfolio.repositorio.TecnologiaRepositorio;
import com.portfolio.servicio.implementacion.ArchivosServicioImplementacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapeadorProyecto {

    @Autowired
    private PersonaRepositorio personaRepositorio;

    @Autowired
    private TecnologiaRepositorio tecnologiaRepositorio;

    @Autowired
    private ProyectoRepositorio proyectoRepositorio;

    @Autowired
    private ArchivosServicioImplementacion archivosServicioImplementacion;

    public SalidaProyecto proyectoASalidaProyecto(Proyecto proyecto) {
        SalidaProyecto salidaProyecto = SalidaProyecto.builder()
                .id(proyecto.getId())
                .descripcion(proyecto.getDescripcion())
                .nombre(proyecto.getNombreProyecto())
                .sitio(proyecto.getSitio())
                .tecnologias(tecnologias(proyecto.getTecnologias()))
                .build();

        if (proyecto.getImagen() != null) {
            byte[] imagen = archivosServicioImplementacion.descargarArchivo(proyecto.getImagen());
            if (imagen == null) {
                proyecto.setImagen(null);
                proyectoRepositorio.save(proyecto);
            } else {
                salidaProyecto.setImagen(imagen);
            }
        }

        return salidaProyecto;
    }

    public Proyecto entradaProyectoAProyecto(MultipartFile archivo, EntradaProyecto entradaProyecto) {
        Proyecto proyecto = Proyecto.builder()
                .descripcion(entradaProyecto.getDescripcion())
                .nombreProyecto(entradaProyecto.getNombre())
                .sitio(entradaProyecto.getSitio())
                .persona(persona(entradaProyecto.getIdPersona()))
                .tecnologias(entidadesTecnologia(entradaProyecto.getIdTecnologias()))
                .build();

        proyecto.setImagen(archivosServicioImplementacion.guardarArchivo(archivo).toString());

        return proyecto;
    }

    public Proyecto entradaProyectoAProyecto(MultipartFile archivo, Proyecto proyecto, EntradaProyecto entradaProyecto) {
        proyecto.setNombreProyecto(entradaProyecto.getNombre());
        proyecto.setDescripcion(entradaProyecto.getDescripcion());
        proyecto.setSitio(entradaProyecto.getSitio());
        proyecto.setPersona(persona(entradaProyecto.getIdPersona()));
        proyecto.setTecnologias(entidadesTecnologia(entradaProyecto.getIdTecnologias()));

        if (proyecto.getImagen() != null) {
            archivosServicioImplementacion.borrarArchivo(proyecto.getImagen());
            proyecto.setImagen(archivosServicioImplementacion.guardarArchivo(archivo).toString());
        }
        return proyecto;
    }

    private Persona persona(Long idPersona) {
        if (idPersona != null) {
            return personaRepositorio.findById(idPersona)
                    .orElseThrow(() -> new RuntimeException("Persona no encontrada"));
        }
        return null;
    }

    private List<Tecnologia> entidadesTecnologia(List<Long> idTecnologias) {
        if (idTecnologias != null && !idTecnologias.isEmpty()) {
            return tecnologiaRepositorio.findAllById(idTecnologias);
        }
        return new ArrayList<>();
    }

    private List<SalidaTecnologia> tecnologias(List<Tecnologia> tecnologias) {
        if (tecnologias != null && !tecnologias.isEmpty()) {
            return tecnologias.stream().map(tecnologia -> {
                        SalidaTecnologia salida = SalidaTecnologia.builder()
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
                                salida.setImagen(imagen);
                            }
                        }

                        return salida;
                    })
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
