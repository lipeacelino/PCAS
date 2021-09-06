package com.example.PCAS.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PCAS.entities.Hospital;
import com.example.PCAS.repositories.EnderecoRepository;
import com.example.PCAS.repositories.HospitalRepository;
import com.example.PCAS.repositories.LocalizacaoRepository;
import com.example.PCAS.repositories.RecursoRepository;

@Service
public class HospitalService {

	@Autowired
	private HospitalRepository hospRepository;
	@Autowired
	private LocalizacaoRepository locRepository;
	@Autowired
	private EnderecoRepository endRepository;
	@Autowired
	private RecursoRepository recRepository;
	
	public Hospital addHospital(Hospital hospital) {
		locRepository.save(hospital.getLocalizacao());
		endRepository.save(hospital.getEndereco());
		recRepository.save(hospital.getRecurso());
		return hospRepository.save(hospital);
	}
	
	public List<Hospital> getAll() {
		return hospRepository.findAll();
	}

	public Hospital updatePercOcupacao(Long id, Integer perOcupacao) {
		Hospital hospital = hospRepository.findById(id).get();
		hospital.setPercOcupacao(perOcupacao);
		if (hospital.getPercOcupacao() > 90) {
			hospital.setDateTimeMaiorQue90(new Date());
			hospital.setDateTimeMenorQue90(null);
		} else {
			hospital.setDateTimeMenorQue90(new Date());
			hospital.setDateTimeMaiorQue90(null);
		}
		return hospRepository.save(hospital);
	}
	
}
