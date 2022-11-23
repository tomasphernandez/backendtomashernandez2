package com.portfolio.servicio.implementacion;

import com.portfolio.dto.EntradaTrabajo;
import com.portfolio.dto.SalidaTrabajo;
import com.portfolio.entidad.Trabajo;
import com.portfolio.mapeador.MapeadorTrabajo;
import com.portfolio.repositorio.TrabajoRepositorio;
import com.portfolio.servicio.TrabajoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TrabajoServicioImplementacion implements TrabajoServicio {

    @Autowired
    private TrabajoRepositorio trabajoRepositorio;

    @Autowired
    private MapeadorTrabajo mapeadorTrabajo;

    @Override
    public SalidaTrabajo crearTrabajo(EntradaTrabajo entradaTrabajo) {
        return mapeadorTrabajo.trabajoASalidaTrabajo(
                trabajoRepositorio.save(mapeadorTrabajo.entradaTrabajoATrabajo(entradaTrabajo)));
    }

    @Override
    public SalidaTrabajo editarTrabajo(Long id, EntradaTrabajo entradaTrabajo) {
        if (!trabajoRepositorio.existsById(id)) {
            throw new RuntimeException("Trabajo no encontrado");
        }
        Trabajo trabajo = trabajoRepositorio.findById(id).orElseThrow(() -> new RuntimeException("Trabajo no encontrado"));

        return mapeadorTrabajo.trabajoASalidaTrabajo(
                trabajoRepositorio.save(mapeadorTrabajo.entradaTrabajoATrabajo(trabajo, entradaTrabajo)));
    }

    private LocalDate fechaFinalizacion(LocalDate fechaFinalizacion, Boolean presente) {
        if (Boolean.TRUE.equals(presente)) {
            return LocalDate.of(2100, 1, 1);
        }
        return fechaFinalizacion;
    }

    @Override
    public void borrarTrabajo(Long id) {
        if (!trabajoRepositorio.existsById(id)) {
            throw new RuntimeException("Trabajo no encontrado");
        }
        trabajoRepositorio.deleteById(id);
    }

}
