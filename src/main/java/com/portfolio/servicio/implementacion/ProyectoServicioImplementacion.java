package com.portfolio.servicio.implementacion;

import com.portfolio.dto.EntradaProyecto;
import com.portfolio.dto.SalidaProyecto;
import com.portfolio.entidad.Proyecto;
import com.portfolio.mapeador.MapeadorProyecto;
import com.portfolio.repositorio.ProyectoRepositorio;
import com.portfolio.servicio.ProyectoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProyectoServicioImplementacion implements ProyectoServicio {

    @Autowired
    private ProyectoRepositorio proyectoRepositorio;

    @Autowired
    private MapeadorProyecto mapeadorProyecto;

    @Override
    public SalidaProyecto crearProyecto(MultipartFile archivo, EntradaProyecto entradaProyecto) {
        return mapeadorProyecto.proyectoASalidaProyecto(proyectoRepositorio.save(mapeadorProyecto.entradaProyectoAProyecto(archivo, entradaProyecto)));
    }

    @Override
    public SalidaProyecto editarProyecto(Long id, MultipartFile archivo, EntradaProyecto entradaProyecto) {
        if (!proyectoRepositorio.existsById(id)) {
            throw new RuntimeException("Proyecto no encontrado");
        }
        Proyecto proyecto = proyectoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));
        return mapeadorProyecto.proyectoASalidaProyecto(proyectoRepositorio.save(mapeadorProyecto.entradaProyectoAProyecto(archivo, proyecto, entradaProyecto)));
    }

    @Override
    public void borrarProyecto(Long id) {
        if (!proyectoRepositorio.existsById(id)) {
            throw new RuntimeException("Proyecto no encontrado");
        }
        proyectoRepositorio.deleteById(id);
    }

}
