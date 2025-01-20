package com.aluracursos.literalura.models;

import com.aluracursos.literalura.dto.BookDTO;
import com.aluracursos.literalura.repository.BookRepository;
import com.aluracursos.literalura.repository.PersonRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "libros")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> temas;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> idiomas;
    private Integer numeroDescargas;
    @ManyToMany(mappedBy = "libros", fetch = FetchType.EAGER) // Relación inversa desde la clase Person
    private List<Person> autores;


    public Book() {
    }

    public Book(BookDTO datosLibro) {
        // Solo guarda los primeros 255 caracteres del titulo del libro
        String titulo = datosLibro.titulo().trim();
        if (titulo.length() > 255) {
            titulo = titulo.substring(0, 255);
        }

        this.titulo = titulo;
        this.idiomas = datosLibro.idiomas();
        this.numeroDescargas = datosLibro.numeroDescargas();
        this.temas = datosLibro.temas();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getTemas() {
        return temas;
    }

    public void setTemas(List<String> temas) {
        this.temas = temas;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Integer numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    public List<Person> getAutores() {
        return autores;
    }

    public void setAutores(List<Person> autores) {
        this.autores = autores;
    }

    @Override
    public String toString() {
        return "----LIBRO-----" +
                "\n Titulo: " + titulo +
                "\n Temas: " + temas +
                "\n Idiomas: " + idiomas +
                "\n Numero de descargas: " + numeroDescargas +
                "\n Autores: " + autores.stream().map(Person::getNombre).toList() +
                "\n--------------\n";
    }
}
