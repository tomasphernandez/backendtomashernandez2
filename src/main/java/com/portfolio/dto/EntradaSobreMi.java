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
public class EntradaSobreMi {

    @NotBlank
    private String profesion;

    @NotBlank
    private String sobreMi;

    @NotNull
    private Long idPersona;

}
