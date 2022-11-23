package com.portfolio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EntradaContacto {

    @NotBlank
    private String descripcion;

    @NotBlank
    private String contacto;

    @NotNull
    private Long idPersona;
}
