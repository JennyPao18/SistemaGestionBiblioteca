package com.example.SistemaBiblioteca.Repositorios;

import com.example.SistemaBiblioteca.Entidades.LoanEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LoanRepository extends MongoRepository<LoanEntity, String> {
}
