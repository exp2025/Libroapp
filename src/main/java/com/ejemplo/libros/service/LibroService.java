package com.ejemplo.libros.service;

import com.ejemplo.libros.model.Autor;
import com.ejemplo.libros.model.Libro;
import com.ejemplo.libros.repository.BaseDatos;
import com.ejemplo.libros.util.ApiLibroClient;

import java.util.List;

public class LibroService {
    private final ApiLibroClient api = new ApiLibroClient();
    private final BaseDatos db = new BaseDatos();

    public String buscarYAgregarLibro(String titulo) {
        Libro libro = api.buscarLibroPorTitulo(titulo);
        if (libro == null) return "Libro no encontrado.";
        boolean agregado = db.agregarLibro(libro);
        return agregado ? "Libro agregado: " + libro : "El libro ya existe en la base de datos.";
    }

    public List<Libro> listarLibros() {
        return db.listarLibros();
    }

    public List<Autor> listarAutores() {
        return db.listarAutores();
    }

    public List<Autor> autoresVivosEn(int anio) {
        return db.autoresVivosEn(anio);
    }

    public List<Libro> librosPorIdioma(String idioma) {
        return db.librosPorIdioma(idioma);
    }
}
