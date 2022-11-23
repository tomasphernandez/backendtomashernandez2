package com.portfolio.servicio;

import com.portfolio.dto.EntradaPersona;
import com.portfolio.dto.SalidaPersona;

import java.util.List;

public interface PersonaServicio {

    SalidaPersona obtenerPersona();
    SalidaPersona editarPersona(Long id, EntradaPersona entradaPersona);

}
