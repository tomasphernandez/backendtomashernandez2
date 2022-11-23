package com.portfolio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EntradaEducacion {

    @NotBlank
    private String instituto;

    @NotBlank
    private String titulo;

    @NotNull
    private LocalDate fechaInicio;

    private LocalDate fechaFinalizacion;

    private Boolean presente;

    @NotNull
    private Long idPersona;
}
