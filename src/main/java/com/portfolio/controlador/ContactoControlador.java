package com.portfolio.controlador;

import com.portfolio.dto.EntradaContacto;
import com.portfolio.dto.SalidaContacto;
import com.portfolio.servicio.ContactoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("contacto")
public class ContactoControlador {

    @Autowired
    private ContactoServicio contactoServicio;

    @PostMapping
    public ResponseEntity<SalidaContacto> crearContacto(@Valid @RequestBody EntradaContacto contacto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contactoServicio.crearContacto(contacto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalidaContacto> editarContacto(@PathVariable Long id, @Valid @RequestBody EntradaContacto contacto) {
        return ResponseEntity.ok(contactoServicio.editarContacto(id, contacto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarContacto(@PathVariable Long id) {
        contactoServicio.borrarContacto(id);
        return ResponseEntity.ok().build();
    }
}
