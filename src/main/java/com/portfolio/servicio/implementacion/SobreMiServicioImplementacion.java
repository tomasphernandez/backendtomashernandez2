package com.portfolio.servicio.implementacion;

import com.portfolio.dto.EntradaSobreMi;
import com.portfolio.dto.SalidaSobreMi;
import com.portfolio.entidad.SobreMi;
import com.portfolio.mapeador.MapeadorSobreMi;
import com.portfolio.repositorio.SobreMiRepositorio;
import com.portfolio.servicio.SobreMiServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SobreMiServicioImplementacion implements SobreMiServicio {

    @Autowired
    private SobreMiRepositorio sobreMiRepositorio;

    @Autowired
    private MapeadorSobreMi mapeadorSobreMi;

    @Override
    public SalidaSobreMi editarSobreMi(Long id, MultipartFile archivo, EntradaSobreMi entradaSobreMi) {
        if (!sobreMiRepositorio.existsById(id)) {
            throw new RuntimeException("Sobre mi no encontrado");
        }

        SobreMi sobreMi = sobreMiRepositorio.findById(id).orElseThrow(() -> new RuntimeException("Sobre mi no encontrado"));
        return mapeadorSobreMi.sobreMiASalidaSobreMi(
                sobreMiRepositorio.save(mapeadorSobreMi.entradaSobreMiASobreMi(archivo, sobreMi, entradaSobreMi)));
    }
}
