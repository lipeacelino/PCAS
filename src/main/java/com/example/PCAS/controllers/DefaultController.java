package com.example.PCAS.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(description = "URL base de apresentação do sistema")
@RestController
@RequestMapping("/")
public class DefaultController {

	@ApiOperation(value = "Retorna uma mensagem de boas vindas")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Mensagem de boas vindas"),
		})
	@GetMapping(produces="application/json")
	@ResponseStatus(HttpStatus.OK)
	public String boasVindas() {
		return "Bem vindo a Pandemic Combat Aid System api! :)";
	}
	
}
