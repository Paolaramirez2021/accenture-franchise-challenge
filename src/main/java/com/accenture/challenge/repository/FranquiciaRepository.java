package com.accenture.challenge.repository;

import com.accenture.challenge.model.Franquicia;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FranquiciaRepository extends MongoRepository<Franquicia, String> {
    // Spring Boot genera automáticamente todos los métodos de guardar, eliminar y buscar aquí.
}