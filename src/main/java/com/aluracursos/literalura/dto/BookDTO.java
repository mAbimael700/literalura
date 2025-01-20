package com.aluracursos.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public record BookDTO(
        @JsonAlias("title") String titulo,
        @JsonAlias("subjects") List<String> temas,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("download_count") Integer numeroDescargas,
        @JsonAlias("authors") List<PersonDTO> autores
) {
}
