package com.portfolio.entidad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreProyecto;

    private String descripcion;

    private String sitio;

    private String imagen;

    @Builder.Default
    @ManyToMany
    private List<Tecnologia> tecnologias = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.MERGE)
    private Persona persona;

}
