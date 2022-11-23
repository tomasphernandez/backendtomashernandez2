package com.portfolio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalidaTrabajo {

    private Long id;

    private LocalDate fechaInicio;

    private LocalDate fechaFinalizacion;

    private String nombreEmpresa;

    private String rol;

    private String descripcionTrabajo;

}
