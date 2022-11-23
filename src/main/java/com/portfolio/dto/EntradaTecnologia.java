package com.portfolio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EntradaTecnologia {

    @NotEmpty
    private String nombre;

    @NotNull
    private Integer nivel;

    @NotNull
    private Long idPersona;

    private String imagen;

}
