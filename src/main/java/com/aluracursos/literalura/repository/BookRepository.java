package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitulo(String titulo); // Método para buscar por título

    @Query("SELECT b FROM Book b LEFT JOIN FETCH b.autores")
    List<Book> findAllWithAutores();

    @Query("SELECT b FROM Book b WHERE :idioma MEMBER OF b.idiomas")
    List<Book> findByIdioma(@Param("idioma") String idioma);

}
