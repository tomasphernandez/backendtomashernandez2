package com.portfolio.controlador;

import com.portfolio.dto.EntradaTrabajo;
import com.portfolio.dto.SalidaTrabajo;
import com.portfolio.servicio.TrabajoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("trabajo")
public class TrabajoControlador {

    @Autowired
    private TrabajoServicio trabajoServicio;

    @PostMapping
    public ResponseEntity<SalidaTrabajo> crearTrabajo(@Valid @RequestBody EntradaTrabajo entradaTrabajo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(trabajoServicio.crearTrabajo(entradaTrabajo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalidaTrabajo> editarTrabajo(@PathVariable Long id, @Valid @RequestBody EntradaTrabajo entradaTrabajo) {
        return ResponseEntity.ok(trabajoServicio.editarTrabajo(id, entradaTrabajo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarTrabajo(@PathVariable Long id) {
        trabajoServicio.borrarTrabajo(id);
        return ResponseEntity.ok().build();
    }
}
