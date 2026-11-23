package com.udla.biblioteca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Pruebas unitarias para la clase {@link Biblioteca}.
 */
class BibliotecaTest {

    private Biblioteca biblioteca;
    private Libro libro1;
    private Libro libro2;

    @BeforeEach
    void setUp() {
        biblioteca = new Biblioteca();
        libro1 = new Libro("978-0134685991", "Effective Java", "Joshua Bloch", 2018);
        libro2 = new Libro("978-0132350884", "Clean Code", "Robert C. Martin", 2008);
    }

    @Test
    @DisplayName("Debe agregar libros sin permitir ISBN duplicados ni nulos")
    void debeAgregarLibrosSinDuplicados() {
        assertTrue(biblioteca.agregarLibro(libro1));
        assertFalse(biblioteca.agregarLibro(libro1));
        assertTrue(biblioteca.agregarLibro(libro2));
        assertEquals(2, biblioteca.obtenerCatalogo().size());
        assertThrows(IllegalArgumentException.class, () -> biblioteca.agregarLibro(null));
    }

    @Test
    @DisplayName("Debe buscar libros por ISBN y contar los disponibles")
    void debeBuscarYContarDisponibles() {
        biblioteca.agregarLibro(libro1);
        biblioteca.agregarLibro(libro2);

        Optional<Libro> encontrado = biblioteca.buscarPorIsbn("978-0134685991");
        assertTrue(encontrado.isPresent());
        assertEquals("Effective Java", encontrado.get().getTitulo());

        assertTrue(biblioteca.buscarPorIsbn("000-000").isEmpty());
        assertTrue(biblioteca.buscarPorIsbn("").isEmpty());
        assertTrue(biblioteca.buscarPorIsbn(null).isEmpty());

        assertEquals(2, biblioteca.contarDisponibles());
        libro1.prestar();
        assertEquals(1, biblioteca.contarDisponibles());
    }
}
