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
public class SalidaEducacion {

    private Long id;

    private String instituto;

    private String titulo;

    private LocalDate fechaInicio;

    private LocalDate fechaFinalizacion;

}
