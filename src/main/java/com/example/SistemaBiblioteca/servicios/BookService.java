package com.example.SistemaBiblioteca.servicios;

import com.example.SistemaBiblioteca.entidades.BookEntity;
import com.example.SistemaBiblioteca.repositorios.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<BookEntity> mostrarTodosLibros () {
        return bookRepository.findAll();
    }

    public BookEntity buscarLibroPorId (String id) {
        return bookRepository.findById(id).orElse(null);
    }

    public BookEntity crearLibro (BookEntity libro) {
        return bookRepository.save(libro);
    }

    public void eliminarLibro (String id) {
        bookRepository.deleteById(id);
        System.out.println("Libro eliminado exitosamente");
    }
}
