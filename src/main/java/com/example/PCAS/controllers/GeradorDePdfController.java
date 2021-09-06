package com.example.PCAS.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.PCAS.services.GeradorDePdfService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(description = "Responsável por gerar o relatório")
@RestController
@RequestMapping("/relatorio")
public class GeradorDePdfController {

	@Autowired
	private GeradorDePdfService service;
	
	@ApiOperation(value = "Retorna o relatório completo do hospital")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "PDF com o relatório do hospital"),
		})
	@GetMapping(produces="application/pdf")
	@ResponseStatus(HttpStatus.OK)
	public void gerarRelatorio(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        service.gerarRelatorio(response);
	}
	
}
