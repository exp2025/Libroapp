package com.ejemplo.libros.model;

import java.util.Objects;

public class Autor {
    private String apellido;
    private String nombre;
    private int anioNacimiento;
    private Integer anioFallecimiento; // null si sigue vivo

    public Autor(String apellido, String nombre, int anioNacimiento, Integer anioFallecimiento) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.anioNacimiento = anioNacimiento;
        this.anioFallecimiento = anioFallecimiento;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public int getAnioNacimiento() {
        return anioNacimiento;
    }

    public Integer getAnioFallecimiento() {
        return anioFallecimiento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Autor)) return false;
        Autor autor = (Autor) o;
        return apellido.equalsIgnoreCase(autor.apellido) &&
                nombre.equalsIgnoreCase(autor.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apellido.toLowerCase(), nombre.toLowerCase());
    }

    @Override
    public String toString() {
        return apellido + ", " + nombre;
    }
}
