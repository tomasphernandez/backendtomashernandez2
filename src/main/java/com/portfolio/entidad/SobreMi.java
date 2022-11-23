package com.portfolio.entidad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SobreMi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String profesion;

    private String sobreMi;

    private String imagen;

    @OneToOne(cascade = CascadeType.MERGE)
    private Persona persona;



}
