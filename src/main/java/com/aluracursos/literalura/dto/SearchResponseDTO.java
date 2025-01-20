package com.aluracursos.literalura.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SearchResponseDTO(
        List<BookDTO> results
) {
}
