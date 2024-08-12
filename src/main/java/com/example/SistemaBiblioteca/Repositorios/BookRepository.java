package com.example.SistemaBiblioteca.Repositorios;

import com.example.SistemaBiblioteca.Entidades.BookEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<BookEntity, String> {
}
