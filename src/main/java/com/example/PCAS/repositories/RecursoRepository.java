package com.example.PCAS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PCAS.entities.Recurso;

@Repository
public interface RecursoRepository extends JpaRepository<Recurso, Long> {

}
