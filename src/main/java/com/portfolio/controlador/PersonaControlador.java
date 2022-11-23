package com.portfolio.controlador;

import com.portfolio.dto.EntradaPersona;
import com.portfolio.dto.SalidaPersona;
import com.portfolio.servicio.PersonaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("persona")
public class PersonaControlador {

    @Autowired
    private PersonaServicio personaServicio;

    @GetMapping
    public ResponseEntity<SalidaPersona> obtenerPersona() {
        return ResponseEntity.ok(personaServicio.obtenerPersona());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalidaPersona> editarPersona(@PathVariable Long id, @Valid @RequestBody EntradaPersona entradaPersona) {
        return ResponseEntity.ok(personaServicio.editarPersona(id, entradaPersona));
    }

}
