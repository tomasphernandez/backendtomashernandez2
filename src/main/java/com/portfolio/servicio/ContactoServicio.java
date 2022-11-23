package com.portfolio.servicio;

import com.portfolio.dto.EntradaContacto;
import com.portfolio.dto.SalidaContacto;

public interface ContactoServicio {

    SalidaContacto crearContacto(EntradaContacto contacto);
    SalidaContacto editarContacto(Long id, EntradaContacto contacto);
    void borrarContacto(Long id);

}
