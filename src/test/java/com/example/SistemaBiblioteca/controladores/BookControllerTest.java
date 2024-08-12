package com.example.SistemaBiblioteca.controladores;

import com.example.SistemaBiblioteca.entidades.BookEntity;
import com.example.SistemaBiblioteca.servicios.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BookControllerTest {
    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void mostrarListaDeLibros() {
        BookEntity book1 = new BookEntity("1", "El Nombre del Viento", "Patrick Rothfuss", "978-8401352836", true);
        BookEntity book2 = new BookEntity("2", "El Nombre del Viento 2", "Patrick Rothfuss", "978-840135435745", false);
        List<BookEntity> books = Arrays.asList(book1, book2);

        when(bookService.mostrarTodosLibros()).thenReturn(books);

        List<BookEntity> response = bookController.mostrarTodosLibros();

        assertEquals(2, response.size());
        assertEquals("El Nombre del Viento", response.get(0).getTitulo());
        assertEquals("El Nombre del Viento 2", response.get(1).getTitulo());
    }

    @Test
    public void buscarLibroPorId() {
        BookEntity book = new BookEntity("1", "El Nombre del Viento", "Patrick Rothfuss", "978-8401352836", true);

        when(bookService.buscarLibroPorId("1")).thenReturn(book);

        BookEntity response = bookController.buscarLibroPorId("1");

        assertEquals("El Nombre del Viento", response.getTitulo());
        assertEquals("Patrick Rothfuss", response.getAutor());
    }

    @Test
    public void crearLibro() {
        BookEntity book = new BookEntity("1", "El Nombre del Viento", "Patrick Rothfuss", "978-8401352836", true);
        when(bookService.crearLibro(book)).thenReturn(book);
        BookEntity response = bookController.crearLibro(book);

        assertEquals("El Nombre del Viento", response.getTitulo());
        assertEquals("Patrick Rothfuss", response.getAutor());
    }

    @Test
    public void eliminarLibro() {
        doNothing().when(bookService).eliminarLibro("1");
        bookController.eliminarLibro("1");
        verify(bookService, times(1)).eliminarLibro("1");
    }

    @Test
    public void mostrarListaDeLibrosVacia() {
        when(bookService.mostrarTodosLibros()).thenReturn(Arrays.asList());
        List<BookEntity> response = bookController.mostrarTodosLibros();
        assertEquals(0, response.size());
    }

    @Test
    public void buscarLibroInexistentePorId() {
        when(bookService.buscarLibroPorId("999")).thenReturn(null);
        BookEntity response = bookController.buscarLibroPorId("999");
        assertEquals(null, response);
    }

}
