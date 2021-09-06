package com.example.PCAS.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.PCAS.DTO.TrocaDeRecursosDTO;
import com.example.PCAS.entities.Hospital;
import com.example.PCAS.services.TrocaRecursoService;

@RestController
@RequestMapping("/hospital/trocar-recursos")
public class TrocaRecursoController {
	
	@Autowired
	private TrocaRecursoService interRecService;
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/{idHosp1}/{idHosp2}") 
	public Hospital trocarRecursos(@PathVariable Long idHosp1, @PathVariable Long idHosp2,
			@RequestBody TrocaDeRecursosDTO recursosParaTroca ) { //hosp1 é o hospital que toma a iniciativa da troca e é ele que será retornado
		return interRecService.trocarRecursos(idHosp1, idHosp2, recursosParaTroca.getRecursosHosp1(), recursosParaTroca.getRecursosHosp2());
	}
	
}
