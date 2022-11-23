package com.portfolio.mapeador;

import com.portfolio.dto.*;
import com.portfolio.entidad.*;
import com.portfolio.repositorio.ContactoRepositorio;
import com.portfolio.repositorio.ProyectoRepositorio;
import com.portfolio.repositorio.SobreMiRepositorio;
import com.portfolio.repositorio.TecnologiaRepositorio;
import com.portfolio.servicio.implementacion.ArchivosServicioImplementacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapeadorPersona {

    @Autowired
    private ArchivosServicioImplementacion archivosServicioImplementacion;

    @Autowired
    private SobreMiRepositorio sobreMiRepositorio;

    @Autowired
    private TecnologiaRepositorio tecnologiaRepositorio;

    @Autowired
    private ProyectoRepositorio proyectoRepositorio;

    public SalidaPersona personaASalidaPersona(Persona persona) {
        SalidaPersona salidaPersona = SalidaPersona.builder()
                .id(persona.getId())
                .nombre(persona.getNombre())
                .apellido(persona.getApellido())
                .build();

        if (persona.getSobreMi() != null) {
            SobreMi sobreMi = persona.getSobreMi();
            SalidaSobreMi salidaSobreMi = SalidaSobreMi.builder()
                    .id(sobreMi.getId())
                    .profesion(sobreMi.getProfesion())
                    .sobreMi(sobreMi.getSobreMi())
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

            salidaPersona.setSobreMi(salidaSobreMi);
        }

        if (persona.getContactos() != null && !persona.getContactos().isEmpty()) {
            List<Contacto> contactos = persona.getContactos();
            List<SalidaContacto> salidaContactos = contactos.stream().map(contacto -> SalidaContacto.builder()
                            .id(contacto.getId())
                            .contacto(contacto.getContacto())
                            .descripcion(contacto.getDescripcion())
                            .build())
                    .collect(Collectors.toList());

            salidaPersona.setContactos(salidaContactos);
        }

        if (persona.getInstitutos() != null && !persona.getInstitutos().isEmpty()) {
            List<Educacion> institutos = persona.getInstitutos();
            List<SalidaEducacion> salidaEducacion = institutos.stream().map(instituto -> SalidaEducacion.builder()
                            .id(instituto.getId())
                            .fechaInicio(instituto.getFechaInicio())
                            .fechaFinalizacion(instituto.getFechaFinalizacion())
                            .titulo(instituto.getTitulo())
                            .instituto(instituto.getInstituto())
                            .build())
                    .collect(Collectors.toList());

            salidaPersona.setInstitutos(salidaEducacion);
        }

        if (persona.getProyectos() != null && !persona.getProyectos().isEmpty()) {
            List<Proyecto> proyectos = persona.getProyectos();
            List<SalidaProyecto> salidaProyectos = proyectos.stream().map(proyecto -> {
                        SalidaProyecto salidaProyecto = SalidaProyecto.builder()
                                .id(proyecto.getId())
                                .sitio(proyecto.getSitio())
                                .nombre(proyecto.getNombreProyecto())
                                .tecnologias(tecnologias(proyecto.getTecnologias()))
                                .descripcion(proyecto.getDescripcion())
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
                    })
                    .collect(Collectors.toList());

            salidaPersona.setProyectos(salidaProyectos);
        }

        if (persona.getTecnologias() != null && !persona.getTecnologias().isEmpty()) {
            List<Tecnologia> tecnologias = persona.getTecnologias();
            List<SalidaTecnologia> salidaTecnologias = tecnologias.stream().map(tecnologia -> {
                        SalidaTecnologia salidaTecnologia = SalidaTecnologia.builder()
                                .id(tecnologia.getId())
                                .nombre(tecnologia.getNombreTecnologia())
                                .nivel(tecnologia.getNivel())
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
                    })
                    .collect(Collectors.toList());

            salidaPersona.setTecnologias(salidaTecnologias);
        }

        if (persona.getTrabajos() != null && !persona.getTrabajos().isEmpty()) {
            List<Trabajo> trabajos = persona.getTrabajos();
            List<SalidaTrabajo> salidaTrabajos = trabajos.stream().map(trabajo -> SalidaTrabajo.builder()
                            .id(trabajo.getId())
                            .rol(trabajo.getRol())
                            .nombreEmpresa(trabajo.getNombreEmpresa())
                            .descripcionTrabajo(trabajo.getDescripcion())
                            .fechaInicio(trabajo.getFechaInicio())
                            .fechaFinalizacion(trabajo.getFechaFinalizacion())
                            .build())
                    .collect(Collectors.toList());

            salidaPersona.setTrabajos(salidaTrabajos);
        }

        return salidaPersona;
    }

    private List<SalidaTecnologia> tecnologias(List<Tecnologia> tecnologias) {
        if (tecnologias != null && !tecnologias.isEmpty()) {
            return tecnologias.stream().map(tecnologia -> {
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
                    })
                    .collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

    public Persona entradaPersonaAPersona(Persona persona, EntradaPersona entradaPersona) {
        persona.setNombre(entradaPersona.getNombre());
        persona.setApellido(entradaPersona.getApellido());
        return persona;
    }
}
