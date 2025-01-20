package com.aluracursos.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PersonDTO(
        String name,
        Integer birth_year,
        Integer death_year
) {
}
