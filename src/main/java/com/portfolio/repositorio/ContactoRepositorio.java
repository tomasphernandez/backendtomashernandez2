package com.portfolio.repositorio;

import com.portfolio.entidad.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactoRepositorio extends JpaRepository<Contacto, Long> {
}
