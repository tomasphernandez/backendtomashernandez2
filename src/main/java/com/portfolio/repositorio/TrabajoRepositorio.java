package com.portfolio.repositorio;

import com.portfolio.entidad.Trabajo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrabajoRepositorio extends JpaRepository<Trabajo, Long> {
}
