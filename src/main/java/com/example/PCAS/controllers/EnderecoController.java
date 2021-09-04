package com.example.PCAS.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PCAS.entities.Endereco;
import com.example.PCAS.services.EnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	private EnderecoService service;
	
	@GetMapping
	public List<Endereco> getAll() {
		return service.getAll();
	}
	
}
