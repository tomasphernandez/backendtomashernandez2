package com.portfolio.controlador;

import com.portfolio.dto.EntradaProyecto;
import com.portfolio.dto.SalidaProyecto;
import com.portfolio.servicio.ProyectoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("proyecto")
public class ProyectoControlador {

    @Autowired
    private ProyectoServicio proyectoServicio;

    @PostMapping
    public ResponseEntity<SalidaProyecto> crearProyecto(
            @RequestParam("archivo") MultipartFile archivo,
            @RequestParam String descripcion,
            @RequestParam Long idPersona,
            @RequestParam List<Long> idTecnologias,
            @RequestParam String nombre,
            @RequestParam String sitio
    ) {
        EntradaProyecto entradaProyecto = EntradaProyecto.builder()
                .descripcion(descripcion)
                .idPersona(idPersona)
                .idTecnologias(idTecnologias)
                .nombre(nombre)
                .sitio(sitio)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(proyectoServicio.crearProyecto(archivo, entradaProyecto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalidaProyecto> editarProyecto(
            @PathVariable Long id,
            @RequestParam("archivo") MultipartFile archivo,
            @RequestParam String descripcion,
            @RequestParam Long idPersona,
            @RequestParam List<Long> idTecnologias,
            @RequestParam String nombre,
            @RequestParam String sitio
    ) {
        EntradaProyecto entradaProyecto = EntradaProyecto.builder()
                .descripcion(descripcion)
                .idPersona(idPersona)
                .idTecnologias(idTecnologias)
                .nombre(nombre)
                .sitio(sitio)
                .build();
        return  ResponseEntity.ok(proyectoServicio.editarProyecto(id, archivo, entradaProyecto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarProyecto(@PathVariable Long id) {
        proyectoServicio.borrarProyecto(id);
        return ResponseEntity.ok().build();
    }
}
