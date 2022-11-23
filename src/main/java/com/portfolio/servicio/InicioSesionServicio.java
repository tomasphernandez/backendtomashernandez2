package com.portfolio.servicio;

import com.portfolio.dto.EntradaUsuario;
import com.portfolio.dto.SalidaUsuario;

public interface InicioSesionServicio {

    SalidaUsuario iniciarSesion(EntradaUsuario entradaUsuario);
}
