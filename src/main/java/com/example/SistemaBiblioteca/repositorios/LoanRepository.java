package com.example.SistemaBiblioteca.repositorios;

import com.example.SistemaBiblioteca.entidades.LoanEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LoanRepository extends MongoRepository<LoanEntity, String> {
}
