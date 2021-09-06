package com.example.PCAS.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PCAS.entities.Recurso;
import com.example.PCAS.repositories.RecursoRepository;

@Service
public class RecursoService {

	@Autowired
	private RecursoRepository repository;
	
	public Recurso addRecurso(Recurso recurso) {
		return repository.save(recurso);
	}
	
}
