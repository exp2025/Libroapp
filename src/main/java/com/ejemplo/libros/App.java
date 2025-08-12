package com.ejemplo.libros;

import com.ejemplo.libros.model.Autor;
import com.ejemplo.libros.model.Libro;
import com.ejemplo.libros.service.LibroService;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibroService service = new LibroService();

        while (true) {
            System.out.println("\n---- Menú ----");
            System.out.println("1. Buscar libro por título");
            System.out.println("2. Listar libros");
            System.out.println("3. Listar autores");
            System.out.println("4. Listar autores vivos en un año");
            System.out.println("5. Listar libros por idioma (EN, ES, FR, PT)");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese el título del libro: ");
                    String titulo = scanner.nextLine();
                    String resultado = service.buscarYAgregarLibro(titulo);
                    System.out.println(resultado);
                }
                case 2 -> {
                    List<Libro> libros = service.listarLibros();
                    if (libros.isEmpty()) {
                        System.out.println("No hay libros registrados.");
                    } else {
                        libros.forEach(System.out::println);
                    }
                }
                case 3 -> {
                    List<Autor> autores = service.listarAutores();
                    if (autores.isEmpty()) {
                        System.out.println("No hay autores registrados.");
                    } else {
                        autores.forEach(System.out::println);
                    }
                }
                case 4 -> {
                    System.out.print("Ingrese el año: ");
                    int anio = Integer.parseInt(scanner.nextLine());
                    List<Autor> vivos = service.autoresVivosEn(anio);
                    if (vivos.isEmpty()) {
                        System.out.println("No se encontraron autores vivos en ese año.");
                    } else {
                        vivos.forEach(System.out::println);
                    }
                }
                case 5 -> {
                    System.out.print("Ingrese el idioma (EN, ES, FR, PT): ");
                    String idioma = scanner.nextLine();
                    List<Libro> librosIdioma = service.librosPorIdioma(idioma);
                    if (librosIdioma.isEmpty()) {
                        System.out.println("No hay libros en ese idioma.");
                    } else {
                        librosIdioma.forEach(System.out::println);
                    }
                }
                case 0 -> {
                    System.out.println("Saliendo...");
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }
}
