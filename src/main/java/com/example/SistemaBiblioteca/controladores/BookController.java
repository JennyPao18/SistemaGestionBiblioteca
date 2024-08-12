package com.example.SistemaBiblioteca.controladores;

import com.example.SistemaBiblioteca.entidades.BookEntity;
import com.example.SistemaBiblioteca.servicios.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<BookEntity> mostrarTodosLibros() {
        return bookService.mostrarTodosLibros();
    }

    @GetMapping("/{id}")
    public BookEntity buscarLibroPorId(@PathVariable String id) {
        return bookService.buscarLibroPorId(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public BookEntity crearLibro(@RequestBody BookEntity libro) {
        return bookService.crearLibro(libro);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void eliminarLibro(@PathVariable String id) {
        bookService.eliminarLibro(id);
    }

}
