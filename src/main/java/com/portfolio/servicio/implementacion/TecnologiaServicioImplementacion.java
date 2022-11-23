package com.portfolio.servicio.implementacion;

import com.portfolio.dto.EntradaTecnologia;
import com.portfolio.dto.SalidaTecnologia;
import com.portfolio.entidad.Tecnologia;
import com.portfolio.mapeador.MapeadorTecnologia;
import com.portfolio.repositorio.TecnologiaRepositorio;
import com.portfolio.servicio.TecnologiaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TecnologiaServicioImplementacion implements TecnologiaServicio {

    @Autowired
    private TecnologiaRepositorio tecnologiaRepositorio;

    @Autowired
    private MapeadorTecnologia mapeadorTecnologia;

    @Override
    public SalidaTecnologia crearTecnologia(MultipartFile archivo, EntradaTecnologia entradaTecnologia) {
        return mapeadorTecnologia.tecnologiaASalidaTecnologia(
                tecnologiaRepositorio.save(mapeadorTecnologia.entradaTecnologiaATecnologia(archivo, entradaTecnologia)));
    }

    @Override
    public SalidaTecnologia editarTecnologia(Long id, MultipartFile archivo, EntradaTecnologia entradaTecnologia) {
        if (!tecnologiaRepositorio.existsById(id)) {
            throw new RuntimeException("Tecnologia no encontrada");
        }
        Tecnologia tecnologia = tecnologiaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Tecnologia no encontrada"));
        return mapeadorTecnologia.tecnologiaASalidaTecnologia(
                tecnologiaRepositorio.save(mapeadorTecnologia.entradaTecnologiaATecnologia(archivo, tecnologia, entradaTecnologia)));
    }

    @Override
    public void borrarTecnologia(Long id) {
        if (!tecnologiaRepositorio.existsById(id)) {
            throw new RuntimeException("Tecnologia no encontrada");
        }
        tecnologiaRepositorio.deleteById(id);
    }

}
