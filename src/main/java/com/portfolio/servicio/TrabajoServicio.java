package com.portfolio.servicio;

import com.portfolio.dto.EntradaTrabajo;
import com.portfolio.dto.SalidaTrabajo;

public interface TrabajoServicio {

    SalidaTrabajo crearTrabajo(EntradaTrabajo entradaTrabajo);
    SalidaTrabajo editarTrabajo(Long id, EntradaTrabajo entradaTrabajo);
    void borrarTrabajo(Long id);

}
