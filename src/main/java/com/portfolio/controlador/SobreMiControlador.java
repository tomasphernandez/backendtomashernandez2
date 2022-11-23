package com.portfolio.controlador;

import com.portfolio.dto.EntradaSobreMi;
import com.portfolio.dto.SalidaSobreMi;
import com.portfolio.servicio.SobreMiServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("sobre-mi")
public class SobreMiControlador {

    @Autowired
    private SobreMiServicio sobreMiServicio;

    @PutMapping("/{id}")
    public ResponseEntity<SalidaSobreMi> editarSobreMi(
            @PathVariable Long id,
            @RequestParam(required = false) MultipartFile archivo,
            @RequestParam String profesion,
            @RequestParam String sobreMi,
            @RequestParam Long idPersona
    ) {
        EntradaSobreMi entradaSobreMi = EntradaSobreMi.builder()
                .sobreMi(sobreMi)
                .idPersona(idPersona)
                .profesion(profesion)
                .build();
        return ResponseEntity.ok(sobreMiServicio.editarSobreMi(id, archivo, entradaSobreMi));
    }
}
