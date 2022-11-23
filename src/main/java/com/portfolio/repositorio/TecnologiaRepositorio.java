package com.portfolio.repositorio;

import com.portfolio.entidad.Tecnologia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnologiaRepositorio extends JpaRepository<Tecnologia, Long> {
}
