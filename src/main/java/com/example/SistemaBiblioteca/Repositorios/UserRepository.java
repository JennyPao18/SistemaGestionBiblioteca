package com.example.SistemaBiblioteca.Repositorios;

import com.example.SistemaBiblioteca.Entidades.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {
    Optional<UserEntity> findByCorreo(String correo);
}
