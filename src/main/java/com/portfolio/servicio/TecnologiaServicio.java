package com.portfolio.servicio;

import com.portfolio.dto.EntradaTecnologia;
import com.portfolio.dto.SalidaTecnologia;
import org.springframework.web.multipart.MultipartFile;

public interface TecnologiaServicio {

    SalidaTecnologia crearTecnologia(MultipartFile archivo, EntradaTecnologia entradaTecnologia);
    SalidaTecnologia editarTecnologia(Long id, MultipartFile archivo, EntradaTecnologia entradaTecnologia);
    void borrarTecnologia(Long id);

}
