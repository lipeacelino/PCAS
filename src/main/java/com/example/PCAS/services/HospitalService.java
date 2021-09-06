package com.example.PCAS.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PCAS.entities.Hospital;
import com.example.PCAS.exceptions.HospitalNaoEncontradoException;
import com.example.PCAS.repositories.HospitalRepository;

@Service
public class HospitalService {

	@Autowired
	private HospitalRepository hospRepository;
	@Autowired
	private LocalizacaoService locRepository;
	@Autowired
	private EnderecoService endRepository;
	@Autowired
	private RecursoService recRepository;
	
	public Hospital addHospital(Hospital hospital) {
		locRepository.addLocalizacao(hospital.getLocalizacao());
		endRepository.addEndereco(hospital.getEndereco());
		recRepository.addRecurso(hospital.getRecurso());
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

	public Hospital findById(Long idHosp1) {
		return hospRepository.findById(idHosp1).orElseThrow(() -> 
		new HospitalNaoEncontradoException("Erro ao buscar hospital por ID"));
	}
	
}
