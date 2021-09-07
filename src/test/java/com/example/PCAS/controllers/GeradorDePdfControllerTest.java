package com.example.PCAS.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.PCAS.services.GeradorDePdfService;

@SpringBootTest
@AutoConfigureMockMvc
public class GeradorDePdfControllerTest {

	@Autowired
	private MockMvc mvc;
	@MockBean
	private GeradorDePdfService service;
	
	@Test
	public void deveGerarUmPdf() throws Exception {
		
		mvc.perform(get("/hospital/relatorio")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_PDF));
	}
	
}
