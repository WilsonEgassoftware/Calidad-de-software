package com.udla.biblioteca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Gestiona el catálogo y los préstamos de libros de la biblioteca universitaria.
 */
public class Biblioteca {

    private final List<Libro> catalogo;

    /**
     * Inicializa una biblioteca con un catálogo vacío.
     */
    public Biblioteca() {
        this.catalogo = new ArrayList<>();
    }

    /**
     * Agrega un libro al catálogo si no existe previamente otro con el mismo ISBN.
     *
     * @param libro Libro a registrar.
     * @return {@code true} si se agregó; {@code false} si el ISBN ya existía.
     */
    public boolean agregarLibro(final Libro libro) {
        if (libro == null) {
            throw new IllegalArgumentException("El libro no puede ser nulo.");
        }
        if (catalogo.contains(libro)) {
            return false;
        }
        return catalogo.add(libro);
    }

    /**
     * Busca un libro por su código ISBN.
     *
     * @param isbn Código ISBN a consultar.
     * @return Un {@link Optional} con el libro si se encuentra en el catálogo.
     */
    public Optional<Libro> buscarPorIsbn(final String isbn) {
        if (isbn == null || isbn.isBlank()) {
            return Optional.empty();
        }
        final String isbnLimpio = isbn.trim();
        return catalogo.stream()
                .filter(libro -> libro.getIsbn().equals(isbnLimpio))
                .findFirst();
    }

    /**
     * Obtiene una vista inmutable de todos los libros registrados.
     *
     * @return Lista inmutable del catálogo.
     */
    public List<Libro> obtenerCatalogo() {
        return Collections.unmodifiableList(catalogo);
    }

    /**
     * Cuenta cuántos libros se encuentran actualmente disponibles para préstamo.
     *
     * @return Cantidad de libros disponibles.
     */
    public long contarDisponibles() {
        return catalogo.stream()
                .filter(Libro::isDisponible)
                .count();
    }
}
