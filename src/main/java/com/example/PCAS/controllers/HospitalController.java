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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(description = "Responsável por fazer adição e modifição de hospitais")
@RestController
@RequestMapping("/hospital")
public class HospitalController {

	@Autowired
	private HospitalService service;
	
	@ApiOperation(value = "Cadastra e retorna hospital cadastrado")
	@ApiResponses(value = {
		    @ApiResponse(code = 201, message = "Hospital cadastrado com sucesso"),
		    
		})
	@PostMapping(produces="application/json", consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Hospital addHospital(@RequestBody Hospital hospital) {
		return service.addHospital(hospital);
	}
	
	@ApiOperation(value = "Atualiza percentual de ocupação do hospital")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Percentual de ocupação atualizado com sucesso"),
		    @ApiResponse(code = 404, message = "Erro ao buscar hospital por ID")
		})
	@PutMapping(path="/{id}", produces="application/json", consumes="application/json")
	@ResponseStatus(HttpStatus.OK)
	public Hospital updatePercOcupacao(@PathVariable Long id, @RequestBody @JsonAlias("perc_ocupacao") Map<String, Integer> percOcupacao) {
		return service.updatePercOcupacao(id, percOcupacao.get("perc_ocupacao"));
	}
	
	@ApiOperation(value = "Retorna todos os hospital cadastrados")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Retorna lista de hospitais cadastrados"),
		})
	@GetMapping(produces="application/json")
	@ResponseStatus(HttpStatus.OK)
	public List<Hospital> getAll() {
		return service.getAll();
	}
	
}
