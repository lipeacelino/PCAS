package com.example.PCAS.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.PCAS.services.TrocaRecursoService;

@SpringBootTest
@AutoConfigureMockMvc
public class TrocaRecursoControllerTest {
	
	@Autowired
	private MockMvc mvc;
	@MockBean
	private TrocaRecursoService interRecService;

}
