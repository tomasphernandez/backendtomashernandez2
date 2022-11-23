package com.portfolio.entidad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Educacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String instituto;

    private String titulo;

    private LocalDate fechaInicio;

    private LocalDate fechaFinalizacion;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Persona persona;

}
