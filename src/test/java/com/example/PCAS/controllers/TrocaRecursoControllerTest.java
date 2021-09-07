package com.example.PCAS.controllers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.PCAS.DTO.TrocaDeRecursosDTO;
import com.example.PCAS.entities.Hospital;
import com.example.PCAS.entities.Recurso;
import com.example.PCAS.services.TrocaRecursoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class TrocaRecursoControllerTest {
	
	@Autowired
	private MockMvc mvc;
	@MockBean
	private TrocaRecursoService service;
	
	@Test
	public void deveTrocarRecursoEntreHospitais() throws Exception {
		
		Recurso recurso1 = Recurso.builder().build();
		Recurso recurso2 = Recurso.builder().build();
		
		Hospital hosp = Hospital.builder().id(1L).build();
		
		TrocaDeRecursosDTO trocaDeRecursos = TrocaDeRecursosDTO
				.builder()
				.recursosHosp1(recurso1)
				.recursosHosp2(recurso2)
				.build();
		
		given(service.trocarRecursos(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).willReturn(hosp);
		
		mvc.perform(put("/hospital/trocar-recursos/{idHosp1}/{idHosp2}", 1L, 2L)
				.contentType(MediaType.APPLICATION_JSON).content(toJson(trocaDeRecursos)))
				.andExpect(status().is(200))
				.andExpect(jsonPath("$.id").value("1"));
	}
	
	public static String toJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
