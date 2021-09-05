package com.example.PCAS.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PCAS.entities.Hospital;
import com.example.PCAS.repositories.HospitalRepository;

@Service
public class GeradorDeRelatorio {

	@Autowired
	private HospitalRepository hospRepository;
	
	public List<Hospital> hospComOcupacaoMaiorQue90() {
		return hospRepository.findAll().stream().filter(h -> h.getPercOcupacao() > 90).collect(Collectors.toList());
	}
	
	public List<Hospital> hospComOcupacaoMenorQue90() {
		return hospRepository.findAll().stream().filter(h -> h.getPercOcupacao() < 90).collect(Collectors.toList());
	}
	
}
