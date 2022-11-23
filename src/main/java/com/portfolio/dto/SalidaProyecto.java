package com.portfolio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalidaProyecto {

    private Long id;

    private String nombre;

    private String descripcion;

    private String sitio;

    private byte[] imagen;

    @Builder.Default
    private List<SalidaTecnologia> tecnologias = new ArrayList<>();

}
