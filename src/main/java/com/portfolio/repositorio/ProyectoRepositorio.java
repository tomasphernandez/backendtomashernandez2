package com.portfolio.repositorio;

import com.portfolio.entidad.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProyectoRepositorio extends JpaRepository<Proyecto, Long> {
}
