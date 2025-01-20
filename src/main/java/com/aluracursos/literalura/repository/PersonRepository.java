package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByNombre(String nombre); // Buscar por nombre

    // Buscar personas vivas en un año determinado
    @Query("SELECT p FROM Person p WHERE p.anhoNacimiento <= :year AND (p.anhoFallecimiento >= :year OR p.anhoFallecimiento IS NULL)")
    List<Person> findByPeriodoDeAhnoVivo(@Param("year") Integer year);
}
