package com.aluracursos.literalura.models;

import com.aluracursos.literalura.dto.PersonDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "personas")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer anhoNacimiento;
    private Integer anhoFallecimiento;
    private String nombre;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "libros_autores", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "autor_id", referencedColumnName = "id"), // FK hacia Person
            inverseJoinColumns = @JoinColumn(name = "libro_id", referencedColumnName = "id") // FK hacia Book
    )
    private List<Book> libros = new ArrayList<>();

    public Person() {
    }

    public Person(PersonDTO personDTO) {
        this.nombre = personDTO.name();
        this.anhoFallecimiento = personDTO.death_year();
        this.anhoNacimiento = personDTO.birth_year();
    }

    public List<Book> getLibros() {
        return libros;
    }

    public void setLibros(List<Book> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre +
                "\nAño de nacimiento: " + anhoNacimiento +
                "\nAño de fallecimiento: " + anhoFallecimiento +
                "\nLibros: " + libros.stream().map(Book::getTitulo).toList();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAnhoNacimiento() {
        return anhoNacimiento;
    }

    public void setAnhoNacimiento(Integer anhoNacimiento) {
        this.anhoNacimiento = anhoNacimiento;
    }

    public Integer getAnhoFallecimiento() {
        return anhoFallecimiento;
    }

    public void setAnhoFallecimiento(Integer anhoFallecimiento) {
        this.anhoFallecimiento = anhoFallecimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
