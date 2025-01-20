package com.aluracursos.literalura.utils;

import com.aluracursos.literalura.dto.SearchResponseDTO;
import com.aluracursos.literalura.models.Book;
import com.aluracursos.literalura.models.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ConvierteDatos {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public Book convertToBookEntity(String json) {
        return this.convertJsonToClass(json, Book.class);
    }

    public Person convertToPersonEntity(String json) {
        return this.convertJsonToClass(json, Person.class);
    }

    public SearchResponseDTO converToSearchResponseEntity(String json) {
        return this.convertJsonToClass(json, SearchResponseDTO.class);
    }

    // Método genérico para convertir JSON a cualquier clase
    public <T> T convertJsonToClass(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error al convertir JSON a la clase " + clazz.getSimpleName(), e);
        }
    }
}
