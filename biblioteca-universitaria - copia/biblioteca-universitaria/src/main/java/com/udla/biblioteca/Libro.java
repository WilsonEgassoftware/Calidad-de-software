package com.udla.biblioteca;

import java.util.Objects;

/**
 * Representa un libro dentro del catálogo de la biblioteca universitaria.
 */
public class Libro {

    /** Año mínimo válido para la publicación de un libro impreso. */
    private static final int ANIO_MINIMO_PUBLICACION = 1450;

    /** Días máximos permitidos para un préstamo estándar. */
    private static final int DIAS_PRESTAMO_ESTANDAR = 15;

    /** Tarifa de multa diaria por retraso en dólares. */
    private static final double MULTA_DIARIA = 0.50;

    private final String isbn;
    private final String titulo;
    private final String autor;
    private final int anioPublicacion;
    private boolean disponible;

    /**
     * Construye una nueva instancia de Libro validando sus datos obligatorios.
     *
     * @param isbnParam            Código ISBN único del libro.
     * @param tituloParam          Título del libro.
     * @param autorParam           Autor principal del libro.
     * @param anioPublicacionParam Año de publicación del libro.
     */
    public Libro(final String isbnParam, final String tituloParam,
                 final String autorParam, final int anioPublicacionParam) {
        if (isbnParam == null || isbnParam.isBlank()) {
            throw new IllegalArgumentException("El ISBN no puede ser nulo o vacío.");
        }
        if (tituloParam == null || tituloParam.isBlank()) {
            throw new IllegalArgumentException("El título no puede ser nulo o vacío.");
        }
        if (autorParam == null || autorParam.isBlank()) {
            throw new IllegalArgumentException("El autor no puede ser nulo o vacío.");
        }
        if (anioPublicacionParam < ANIO_MINIMO_PUBLICACION) {
            throw new IllegalArgumentException("El año de publicación no es válido.");
        }

        this.isbn = isbnParam.trim();
        this.titulo = tituloParam.trim();
        this.autor = autorParam.trim();
        this.anioPublicacion = anioPublicacionParam;
        this.disponible = true;
    }

    /**
     * Marca el libro como prestado si se encuentra disponible.
     *
     * @return {@code true} si el préstamo fue exitoso; {@code false} si ya estaba prestado.
     */
    public boolean prestar() {
        if (!this.disponible) {
            return false;
        }
        this.disponible = false;
        return true;
    }

    /**
     * Marca el libro como disponible nuevamente tras su devolución.
     *
     * @return {@code true} si la devolución se registró; {@code false} si ya estaba disponible.
     */
    public boolean devolver() {
        if (this.disponible) {
            return false;
        }
        this.disponible = true;
        return true;
    }

    /**
     * Calcula la multa por retraso según el total de días transcurridos desde el préstamo.
     *
     * @param diasTranscurridos Días totales desde que se retiró el libro.
     * @return Monto de la multa en dólares (0.0 si está dentro del plazo estándar).
     */
    public double calcularMultaPorRetraso(final int diasTranscurridos) {
        if (diasTranscurridos < 0) {
            throw new IllegalArgumentException("Los días transcurridos no pueden ser negativos.");
        }
        if (diasTranscurridos <= DIAS_PRESTAMO_ESTANDAR) {
            return 0.0;
        }
        final int diasExcedidos = diasTranscurridos - DIAS_PRESTAMO_ESTANDAR;
        return diasExcedidos * MULTA_DIARIA;
    }

    /**
     * Obtiene el ISBN del libro.
     *
     * @return ISBN del libro.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Obtiene el título del libro.
     *
     * @return Título del libro.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Obtiene el autor del libro.
     *
     * @return Autor del libro.
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Obtiene el año de publicación.
     *
     * @return Año de publicación.
     */
    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    /**
     * Indica si el libro está disponible para préstamo.
     *
     * @return {@code true} si está disponible; {@code false} en caso contrario.
     */
    public boolean isDisponible() {
        return disponible;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Libro otro = (Libro) obj;
        return Objects.equals(isbn, otro.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    @Override
    public String toString() {
        return "Libro{isbn='" + isbn + "', titulo='" + titulo + "', autor='" + autor
                + "', anio=" + anioPublicacion + ", disponible=" + disponible + "}";
    }
}
