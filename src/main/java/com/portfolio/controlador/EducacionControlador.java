package com.portfolio.controlador;

import com.portfolio.dto.EntradaEducacion;
import com.portfolio.dto.SalidaEducacion;
import com.portfolio.entidad.Educacion;
import com.portfolio.servicio.EducacionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("educacion")
public class EducacionControlador {

    @Autowired
    private EducacionServicio educacionServicio;

    @PostMapping
    public ResponseEntity<SalidaEducacion> crearEducacion(@Valid @RequestBody EntradaEducacion entradaEducacion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(educacionServicio.crearEducacion(entradaEducacion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalidaEducacion> editarEducacion(@PathVariable Long id, @Valid @RequestBody EntradaEducacion entradaEducacion) {
        return ResponseEntity.ok(educacionServicio.editarEducacion(id, entradaEducacion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarEducacion(@PathVariable Long id) {
        educacionServicio.borrarEducacion(id);
        return ResponseEntity.ok().build();
    }
}
