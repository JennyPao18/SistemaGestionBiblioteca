package com.example.SistemaBiblioteca.repositorios;

import com.example.SistemaBiblioteca.entidades.BookEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<BookEntity, String> {
}
