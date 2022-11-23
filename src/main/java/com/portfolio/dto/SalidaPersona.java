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
public class SalidaPersona {

    private Long id;

    private String nombre;

    private String apellido;

    private SalidaSobreMi sobreMi;

    @Builder.Default
    private List<SalidaTecnologia> tecnologias = new ArrayList<>();

    @Builder.Default
    private List<SalidaEducacion> institutos = new ArrayList<>();

    @Builder.Default
    private List<SalidaContacto> contactos = new ArrayList<>();

    @Builder.Default
    private List<SalidaTrabajo> trabajos = new ArrayList<>();

    @Builder.Default
    private List<SalidaProyecto> proyectos = new ArrayList<>();

}
