package com.portfolio.controlador;

import com.portfolio.dto.EntradaUsuario;
import com.portfolio.dto.SalidaUsuario;
import com.portfolio.servicio.InicioSesionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("inicio-sesion")
public class InicioSesionControlador {

    @Autowired
    private InicioSesionServicio usuarioServicio;

    @PostMapping
    public ResponseEntity<SalidaUsuario> iniciarSesion(@Valid @RequestBody EntradaUsuario entradaUsuario) {
        return ResponseEntity.ok(usuarioServicio.iniciarSesion(entradaUsuario));
    }

}
