package com.portfolio.servicio;

import com.portfolio.dto.EntradaSobreMi;
import com.portfolio.dto.SalidaSobreMi;
import org.springframework.web.multipart.MultipartFile;

public interface SobreMiServicio {

    SalidaSobreMi editarSobreMi(Long id, MultipartFile archivo, EntradaSobreMi entradaSobreMi);

}
