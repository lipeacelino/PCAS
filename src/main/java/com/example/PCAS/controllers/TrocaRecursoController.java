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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(description = "Responsável por fazer a troca de recursos entre hospitais")
@RestController
@RequestMapping("/hospital/trocar-recursos")
public class TrocaRecursoController {
	
	@Autowired
	private TrocaRecursoService interRecService;
	
	@ApiOperation(value = "Realiza a troca de recursos entre os hospitais")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Troca de recursos realizada com sucesso"),
		    @ApiResponse(code = 400, message = "Erro de validação, não foi possível concluir a troca de recrusos")
		})
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(path="/{idHosp1}/{idHosp2}", produces="application/json", consumes="application/json") 
	public Hospital trocarRecursos(@PathVariable Long idHosp1, @PathVariable Long idHosp2,
			@RequestBody TrocaDeRecursosDTO recursosParaTroca ) { //hosp1 é o hospital que toma a iniciativa da troca e é ele que será retornado
		return interRecService.trocarRecursos(idHosp1, idHosp2, recursosParaTroca.getRecursosHosp1(), recursosParaTroca.getRecursosHosp2());
	}
	
}
