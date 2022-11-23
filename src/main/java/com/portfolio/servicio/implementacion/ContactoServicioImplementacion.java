package com.portfolio.servicio.implementacion;

import com.portfolio.dto.EntradaContacto;
import com.portfolio.dto.SalidaContacto;
import com.portfolio.entidad.Contacto;
import com.portfolio.mapeador.MapeadorContacto;
import com.portfolio.repositorio.ContactoRepositorio;
import com.portfolio.servicio.ContactoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactoServicioImplementacion implements ContactoServicio {

    @Autowired
    private ContactoRepositorio contactoRepositorio;

    @Autowired
    private MapeadorContacto mapeadorContacto;

    @Override
    public SalidaContacto crearContacto(EntradaContacto contacto) {
        return mapeadorContacto.contactoASalidaContacto(
                contactoRepositorio.save(mapeadorContacto.entradaContactoAContacto(contacto)));
    }

    @Override
    public SalidaContacto editarContacto(Long id, EntradaContacto entradaContacto) {
        if (!contactoRepositorio.existsById(id)) {
            throw new RuntimeException("No existe el contacto");
        }

        Contacto contacto = contactoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Contacto no encontrado"));

        return mapeadorContacto.contactoASalidaContacto(
                contactoRepositorio.save(mapeadorContacto.entradaContactoAContacto(contacto, entradaContacto)));
    }

    @Override
    public void borrarContacto(Long id) {
        if (!contactoRepositorio.existsById(id)) {
            throw new RuntimeException("No existe el contacto");
        }

        contactoRepositorio.deleteById(id);
    }
}
