package com.portfolio.repositorio;

import com.portfolio.entidad.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepositorio extends JpaRepository<Persona, Long> {
}
