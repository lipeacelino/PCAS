package com.example.PCAS.controllers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.PCAS.entities.Hospital;
import com.example.PCAS.services.HospitalService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class HospitalControllerTest {

	@Autowired
	private MockMvc mvc;
	@MockBean
	private HospitalService service;

	@Test
	public void deveBuscarTodosOsHospitais() throws Exception {

		List<Hospital> hospLista = new ArrayList<Hospital>();

		given(service.getAll()).willReturn(hospLista);

		mvc.perform(get("/hospital")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	public void deveAdicionarHospital() throws Exception {

		Hospital hosp = Hospital.builder().id(1L).build();
		
		given(service.addHospital(Mockito.any())).willReturn(hosp);

		mvc.perform(post("/hospital")
				.contentType(MediaType.APPLICATION_JSON).content(toJson(hosp)))
				.andExpect(status().is(201))
				.andExpect(jsonPath("$.id").value("1"));

	}
	
	@Test
	public void deveAtualizarPercentualDoHospital() throws Exception {
		
		Hospital hosp = Hospital.builder().id(1L).percOcupacao(5).build();
		given(service.updatePercOcupacao(Mockito.any(), Mockito.any())).willReturn(hosp);
		
		mvc.perform(put("/hospital/{id}", 1L)
				.contentType(MediaType.APPLICATION_JSON).content(toJson(hosp)))
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
