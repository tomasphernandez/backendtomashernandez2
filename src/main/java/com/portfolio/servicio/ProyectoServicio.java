package com.portfolio.servicio;

import com.portfolio.dto.EntradaProyecto;
import com.portfolio.dto.SalidaProyecto;
import org.springframework.web.multipart.MultipartFile;

public interface ProyectoServicio {

    SalidaProyecto crearProyecto(MultipartFile archivo, EntradaProyecto entradaProyecto);
    SalidaProyecto editarProyecto(Long id, MultipartFile archivo, EntradaProyecto entradaProyecto);
    void borrarProyecto(Long id);

}
