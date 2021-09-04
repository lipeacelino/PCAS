package com.example.PCAS.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.PCAS.entities.Hospital;
import com.example.PCAS.services.HospitalService;
import com.fasterxml.jackson.annotation.JsonAlias;

@RestController
@RequestMapping("/hospital")
public class HospitalController {

	@Autowired
	private HospitalService service;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Hospital addHospital(@RequestBody Hospital hospital) {
		return service.addHospital(hospital);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Hospital updatePercOcupacao(@PathVariable Long id, @RequestBody @JsonAlias("perc_ocupacao") Map<String, Integer> percOcupacao) {
		return service.updatePercOcupacao(id, percOcupacao.get("perc_ocupacao"));
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Hospital> getAll() {
		return service.getAll();
	}
	
}
