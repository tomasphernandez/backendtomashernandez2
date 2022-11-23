package com.portfolio.servicio;

import com.portfolio.dto.EntradaEducacion;
import com.portfolio.dto.SalidaEducacion;

public interface EducacionServicio {

    SalidaEducacion crearEducacion(EntradaEducacion entradaEducacion);
    SalidaEducacion editarEducacion(Long id, EntradaEducacion entradaEducacion);
    void borrarEducacion(Long id);

}
