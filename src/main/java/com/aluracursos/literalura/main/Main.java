package com.aluracursos.literalura.main;

import com.aluracursos.literalura.models.Idiomas;
import com.aluracursos.literalura.services.BookService;
import com.aluracursos.literalura.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class Main {

    private final Scanner teclado = new Scanner(System.in);
    private Integer opcion = -1;

    @Autowired
    private BookService bookService;

    @Autowired
    private PersonService personService;

    public void menuPrincipal() {

        System.out.println("""
                Bienvenido al sistema Literalura, tu aplicación de confianza para la consulta de tus libros favoritos ;)
                """);
        while (opcion != 0) {
            mostrarMenu();
            try {
                opcion = Integer.valueOf(teclado.nextLine());

                switch (opcion) {
                    case 0:
                        System.out.println("Saliendo del sistema, hasta pronto! <3");
                        break;
                    case 1:
                        System.out.println("Ingrese el nombre del libro");
                        String busqueda = teclado.nextLine();
                        bookService.searchBook(busqueda);
                        break;

                    case 2:
                        bookService.imprimirListadoDeLibrosRegistrados();
                        break;

                    case 3:
                        personService.imprimirListadoAutores();
                        break;

                    case 4:
                        System.out.println("Escriba el año en el cuál el autor estuvo vivo");
                        var periodoAhno = Integer.valueOf(teclado.nextLine());
                        personService.imprimirListadoAutoresVivosPorAnho(periodoAhno);
                        break;

                    case 5:
                        System.out.println("Escriba el idioma en la que quieres buscar los libros. \nEstas son las opciones disponibles:");
                        System.out.println(
                                Arrays.stream(Idiomas.values())
                                        .map(i -> String.format("%d) %s",
                                                Arrays.asList(Idiomas.values()).indexOf(i) + 1,
                                                i.name().toLowerCase()))
                                        .collect(Collectors.joining("\n"))
                        );
                        var idioma = Idiomas.valueOf(teclado.nextLine().toUpperCase());
                        bookService.imprimirListadoDeLibrosPorIdioma(idioma.name());
                        break;

                    default:
                        System.out.printf("Opción %d no es disponible\n", opcion);
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: !El valor ingresado no es un número!");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: !El valor ingresado no es válido!");
            }
        }

    }


    private static void mostrarMenu() {
        System.out.println(
                """
                            Elija la opción a través de su número:
                            1) Buscar título
                            2) Listar libros registrados
                            3) Listar autores registrados
                            4) Listar autores vivos en un determinado año
                            5) Listar libros por idioma
                            0) Salir
                        """);
    }
}
