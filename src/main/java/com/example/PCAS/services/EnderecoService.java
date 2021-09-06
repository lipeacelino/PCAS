package com.example.PCAS.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PCAS.entities.Endereco;
import com.example.PCAS.repositories.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repository;
	
	public List<Endereco> getAll() {
		return repository.findAll();
	}
	
	public Endereco addEndereco(Endereco endereco) {
		return repository.save(endereco);
	}
	
}
