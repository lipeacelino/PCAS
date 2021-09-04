package com.example.PCAS.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PCAS.entities.Localizacao;
import com.example.PCAS.repositories.LocalizacaoRepository;

@Service
public class LocalizacaoService {

	@Autowired
	private LocalizacaoRepository repository;
	
	public List<Localizacao> getAll() {
		return repository.findAll();
	}
	
}
