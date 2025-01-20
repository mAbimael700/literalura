package com.aluracursos.literalura.services;

import com.aluracursos.literalura.api.LibrosApi;
import com.aluracursos.literalura.dto.BookDTO;
import com.aluracursos.literalura.dto.PersonDTO;
import com.aluracursos.literalura.dto.SearchResponseDTO;
import com.aluracursos.literalura.models.Book;
import com.aluracursos.literalura.models.Person;
import com.aluracursos.literalura.repository.BookRepository;
import com.aluracursos.literalura.repository.PersonRepository;
import com.aluracursos.literalura.utils.ConvierteDatos;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {


    @Autowired
    private LibrosApi librosApi;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PersonRepository personRepository;

    public void searchBook(@NotNull String bookTitle) {
        System.out.println("Buscando el libro en la API...");

        var response = librosApi.searchBook(bookTitle);
        if (response == null || response.results().isEmpty()) {
            System.out.println("No se encontró el libro solicitado.");
            return;
        }

        BookDTO primerResultado = response.results().get(0);

        // Verifica si ya existe un libro con el título del resultado
        Optional<Book> existingBook = bookRepository.findByTitulo(primerResultado.titulo());

        if (existingBook.isPresent()) {
            System.out.println("El libro ya está registrado.");
        } else {
            // Si el libro no existe, lo creamos
            this.saveBook(primerResultado);
        }

    }

    @Transactional
    public void imprimirListadoDeLibrosRegistrados() {
        List<Book> books = bookRepository.findAllWithAutores();
        books.forEach(System.out::println);
    }

    public void imprimirListadoDeLibrosPorIdioma(String idioma) {
       var books = bookRepository.findByIdioma(idioma.toLowerCase());
       if (books.isEmpty()){
           System.out.println("No existen libros en el idioma dado");
       }
        books.forEach(System.out::println);
    }

    @Transactional
    public void saveBook(BookDTO bookDTO) {

        Book newBook = new Book(bookDTO);

        // Obtener o crear autores
        var autores = bookDTO.autores().stream()
                .map(this::existeAuthorEnLaBaseDeDatos)
                .collect(Collectors.toList());

        newBook.setAutores(autores);
        try {
            bookRepository.save(newBook);  // Guarda o actualiza el libro

            // Evita duplicados en la relación autores-libros
            autores.forEach(autor -> autor.getLibros().add(newBook));
            autores.forEach(personRepository::save);  // Guarda o actualiza autores
            System.out.println("Libro guardado exitosamente.");
            System.out.println(newBook);
        } catch (Exception e) {
            System.out.println("Error al guardar el libro: " + e.getMessage());
            throw e;
        }
    }

    @Transactional
    private Person existeAuthorEnLaBaseDeDatos(PersonDTO personDTO) {
        return personRepository.findByNombre(personDTO.name().trim())
                .orElseGet(() -> personRepository.save(new Person(personDTO)));
    }


}
