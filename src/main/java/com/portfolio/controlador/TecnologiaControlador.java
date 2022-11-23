package com.portfolio.controlador;

import com.portfolio.dto.EntradaTecnologia;
import com.portfolio.dto.SalidaTecnologia;
import com.portfolio.servicio.TecnologiaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("tecnologia")
public class TecnologiaControlador {

    @Autowired
    private TecnologiaServicio tecnologiaServicio;

    @PostMapping
    public ResponseEntity<SalidaTecnologia> crearTecnologia(
            @RequestParam("archivo") MultipartFile archivo,
            @RequestParam String nombre,
            @RequestParam Integer nivel,
            @RequestParam Long idPersona
    ) {
        EntradaTecnologia entradaTecnologia = EntradaTecnologia.builder()
                .idPersona(idPersona)
                .nivel(nivel)
                .nombre(nombre)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(tecnologiaServicio.crearTecnologia(archivo, entradaTecnologia));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalidaTecnologia> editarTecnologia(
            @PathVariable Long id,
            @RequestParam("archivo") MultipartFile archivo,
            @RequestParam String nombre,
            @RequestParam Integer nivel,
            @RequestParam Long idPersona
    ) {
        EntradaTecnologia entradaTecnologia = EntradaTecnologia.builder()
                .idPersona(idPersona)
                .nivel(nivel)
                .nombre(nombre)
                .build();
        return ResponseEntity.ok(tecnologiaServicio.editarTecnologia(id, archivo, entradaTecnologia));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarTecnologia(@PathVariable Long id) {
        tecnologiaServicio.borrarTecnologia(id);
        return ResponseEntity.ok().build();
    }


}
