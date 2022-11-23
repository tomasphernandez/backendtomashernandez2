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
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String apellido;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "persona", orphanRemoval = true)
    private SobreMi sobreMi;

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona", orphanRemoval = true)
    private List<Contacto> contactos = new ArrayList<>();

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona", orphanRemoval = true)
    private List<Educacion> institutos = new ArrayList<>();

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona", orphanRemoval = true)
    private List<Proyecto> proyectos = new ArrayList<>();

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona", orphanRemoval = true)
    private List<Tecnologia> tecnologias = new ArrayList<>();

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona", orphanRemoval = true)
    private List<Trabajo> trabajos = new ArrayList<>();

    public void agregarContacto(Trabajo trabajo) {
        this.trabajos.add(trabajo);
        trabajo.setPersona(this);
    }
}
