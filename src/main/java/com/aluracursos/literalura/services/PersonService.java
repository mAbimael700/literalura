package com.aluracursos.literalura.services;

import com.aluracursos.literalura.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public void imprimirListadoAutores(){
        personRepository.findAll().forEach(p -> {
            System.out.println("\n----AUTOR----");
            System.out.println(p);
            System.out.println("------------");
        });
    }

    public void imprimirListadoAutoresVivosPorAnho(Integer periodoAhno) {
        personRepository.findByPeriodoDeAhnoVivo(periodoAhno).forEach(p -> {
            System.out.println("\n----AUTOR----");
            System.out.println(p);
            System.out.println("------------");
        });
    }
}
