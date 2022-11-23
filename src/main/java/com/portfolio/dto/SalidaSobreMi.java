package com.portfolio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalidaSobreMi {

    private Long id;

    private String profesion;

    private String sobreMi;

    private byte[] imagen;

}
