package com.ejemplo.libros.repository;

import com.ejemplo.libros.model.Autor;
import com.ejemplo.libros.model.Libro;

import java.util.*;

public class BaseDatos {
    private Set<Libro> libros = new HashSet<>();
    private Set<Autor> autores = new HashSet<>();

    public boolean agregarLibro(Libro libro) {
        if (!libros.add(libro)) {
            return false; // Ya existe
        }
        autores.add(libro.getAutor());
        return true;
    }

    public List<Libro> listarLibros() {
        return new ArrayList<>(libros);
    }

    public List<Autor> listarAutores() {
        return new ArrayList<>(autores);
    }

    public List<Autor> autoresVivosEn(int anio) {
        List<Autor> vivos = new ArrayList<>();
        for (Autor a : autores) {
            if (a.getAnioNacimiento() > 0 &&
                    a.getAnioNacimiento() <= anio &&
                    (a.getAnioFallecimiento() == null || a.getAnioFallecimiento() >= anio)) {
                vivos.add(a);
            }
        }
        return vivos;
    }

    public List<Libro> librosPorIdioma(String idioma) {
        List<Libro> filtrados = new ArrayList<>();
        for (Libro l : libros) {
            if (l.getIdioma().equalsIgnoreCase(idioma)) {
                filtrados.add(l);
            }
        }
        return filtrados;
    }
}
