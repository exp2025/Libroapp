package com.ejemplo.libros.model;

import java.util.Objects;

public class Libro {
    private String titulo;
    private Autor autor;
    private String idioma;
    private int descargas;

    public Libro(String titulo, Autor autor, String idioma, int descargas) {
        this.titulo = titulo;
        this.autor = autor;
        this.idioma = idioma;
        this.descargas = descargas;
    }

    public String getTitulo() {
        return titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public int getDescargas() {
        return descargas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Libro)) return false;
        Libro libro = (Libro) o;
        return titulo.equalsIgnoreCase(libro.titulo) &&
                autor.equals(libro.autor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo.toLowerCase(), autor);
    }

    @Override
    public String toString() {
        return titulo + " - " + autor + " [" + idioma + "] (" + descargas + " descargas)";
    }
}
