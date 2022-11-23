package com.portfolio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalidaTecnologia {

    private Long id;

    private String nombre;

    private Integer nivel;

    private byte[] imagen;

}
