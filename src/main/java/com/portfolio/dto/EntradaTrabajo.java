package com.portfolio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EntradaTrabajo {

    @NotNull
    private LocalDate fechaInicio;

    private LocalDate fechaFinalizacion;

    private Boolean presente;

    @NotEmpty
    private String nombreEmpresa;

    @NotEmpty
    private String rol;

    @NotEmpty
    private String descripcion;

    @NotNull
    private Long idPersona;

}
