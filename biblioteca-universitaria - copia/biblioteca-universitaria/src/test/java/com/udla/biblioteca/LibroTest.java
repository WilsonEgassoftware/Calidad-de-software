package com.udla.biblioteca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Pruebas unitarias para la clase {@link Libro}.
 */
class LibroTest {

    private Libro libro;

    @BeforeEach
    void setUp() {
        libro = new Libro("978-0134685991", "Effective Java", "Joshua Bloch", 2018);
    }

    @Test
    @DisplayName("Debe crear un libro válido y marcarlo como disponible")
    void debeCrearLibroValido() {
        assertEquals("978-0134685991", libro.getIsbn());
        assertEquals("Effective Java", libro.getTitulo());
        assertEquals("Joshua Bloch", libro.getAutor());
        assertEquals(2018, libro.getAnioPublicacion());
        assertTrue(libro.isDisponible());
    }

    @Test
    @DisplayName("Debe lanzar excepción si los parámetros del constructor son inválidos")
    void debeValidarParametrosConstructor() {
        assertThrows(IllegalArgumentException.class,
                () -> new Libro("", "Titulo", "Autor", 2020));
        assertThrows(IllegalArgumentException.class,
                () -> new Libro(null, "Titulo", "Autor", 2020));
        assertThrows(IllegalArgumentException.class,
                () -> new Libro("123", "   ", "Autor", 2020));
        assertThrows(IllegalArgumentException.class,
                () -> new Libro("123", "Titulo", null, 2020));
        assertThrows(IllegalArgumentException.class,
                () -> new Libro("123", "Titulo", "Autor", 1400));
    }

    @Test
    @DisplayName("Debe permitir prestar y devolver un libro correctamente")
    void debePrestarYDevolverLibro() {
        assertTrue(libro.prestar());
        assertFalse(libro.isDisponible());
        assertFalse(libro.prestar(), "No debe permitir prestar un libro ya prestado");

        assertTrue(libro.devolver());
        assertTrue(libro.isDisponible());
        assertFalse(libro.devolver(), "No debe permitir devolver un libro ya disponible");
    }

    @Test
    @DisplayName("Debe calcular correctamente la multa por días de retraso")
    void debeCalcularMultaPorRetraso() {
        assertEquals(0.0, libro.calcularMultaPorRetraso(10));
        assertEquals(0.0, libro.calcularMultaPorRetraso(15));
        assertEquals(2.50, libro.calcularMultaPorRetraso(20), 0.001);
        assertThrows(IllegalArgumentException.class,
                () -> libro.calcularMultaPorRetraso(-1));
    }

    @Test
    @DisplayName("Debe comparar igualdad por ISBN y generar toString")
    void debeVerificarEqualsHashCodeYToString() {
        Libro mismoIsbn = new Libro("978-0134685991", "Otro Título", "Otro Autor", 2020);
        Libro distintoIsbn = new Libro("978-0132350884", "Clean Code", "Robert C. Martin", 2008);

        assertEquals(libro, libro);
        assertEquals(libro, mismoIsbn);
        assertEquals(libro.hashCode(), mismoIsbn.hashCode());
        assertNotEquals(libro, distintoIsbn);
        assertNotEquals(libro, null);
        assertNotEquals(libro, "cadena");
        assertTrue(libro.toString().contains("Effective Java"));
    }
}
