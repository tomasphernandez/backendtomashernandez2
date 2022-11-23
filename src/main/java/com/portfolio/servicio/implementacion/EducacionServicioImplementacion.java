package com.portfolio.servicio.implementacion;

import com.portfolio.dto.EntradaEducacion;
import com.portfolio.dto.SalidaEducacion;
import com.portfolio.entidad.Educacion;
import com.portfolio.mapeador.MapeadorEducacion;
import com.portfolio.repositorio.EducacionRepositorio;
import com.portfolio.servicio.EducacionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EducacionServicioImplementacion implements EducacionServicio {

    @Autowired
    private EducacionRepositorio educacionRepositorio;

    @Autowired
    private MapeadorEducacion mapeadorEducacion;

    @Override
    public SalidaEducacion crearEducacion(EntradaEducacion entradaEducacion) {
        return mapeadorEducacion.educacionASalidaEducacion(
                educacionRepositorio.save(mapeadorEducacion.entradaEducacionAEducacion(entradaEducacion)));
    }

    @Override
    public SalidaEducacion editarEducacion(Long id, EntradaEducacion entradaEducacion) {
        if (!educacionRepositorio.existsById(id)) {
            throw new RuntimeException("No se encuentra educacion");
        }
        Educacion educacion = educacionRepositorio.findById(id).orElseThrow(() -> new RuntimeException("No se encuentra educacion"));
        return mapeadorEducacion.educacionASalidaEducacion(educacionRepositorio.save(
                mapeadorEducacion.entradaEducacionAEducacion(educacion, entradaEducacion)));
    }

    @Override
    public void borrarEducacion(Long id) {
        if (!educacionRepositorio.existsById(id)) {
            throw new RuntimeException("No se encuentra educacion");
        }
        educacionRepositorio.deleteById(id);
    }
}
