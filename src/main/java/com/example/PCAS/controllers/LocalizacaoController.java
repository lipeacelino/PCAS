package com.example.PCAS.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.PCAS.entities.Localizacao;
import com.example.PCAS.services.LocalizacaoService;

@RestController
@RequestMapping("/localizacao")
public class LocalizacaoController {

	@Autowired
	private LocalizacaoService service;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Localizacao> getAll() {
		return service.getAll();
	}
	
}
