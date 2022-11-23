package com.portfolio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EntradaProyecto {

    @NotEmpty
    private String nombre;

    @NotEmpty
    private String descripcion;

    @NotEmpty
    private String sitio;

    private String imagen;

    @NotNull
    private Long idPersona;

    @Builder.Default
    private List<Long> idTecnologias = new ArrayList<>();


}
