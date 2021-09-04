package com.example.PCAS.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PCAS.entities.Hospital;
import com.example.PCAS.entities.Recurso;
import com.example.PCAS.repositories.HospitalRepository;

@Service
public class IntercambioRecursoService {
	
	@Autowired
	private HospitalRepository hospitalRepository;

	public Hospital trocarRecursos(Long idHosp1, Long idHosp2, Recurso recHosp1, Recurso recHosp2) {
		
		Hospital hospJaSalvo1 = hospitalRepository.findById(idHosp1).get();
		Hospital hospJaSalvo2 = hospitalRepository.findById(idHosp2).get();
		
		if (verificaPossibilidadeDeEfetuarTroca(hospJaSalvo1, hospJaSalvo2)) {
			return efetivarTroca(hospJaSalvo1, hospJaSalvo2, recHosp1, recHosp2);
		} else {
			//lançar exceção
		}
		return null;
	}
	
	public boolean verificaPossibilidadeDeEfetuarTroca(Hospital hosp1, Hospital hosp2) {
		
		Recurso recHosp1 = hosp1.getRecurso();
		int pontuacaoHosp1 = (recHosp1.getMedico() * 3) + (recHosp1.getEnfermeiro() * 3) + (recHosp1.getRespirador() * 5) 
				+ (recHosp1.getTomografo() * 12) + (recHosp1.getAmbulancia() * 10);
		
		Recurso recHosp2 = hosp2.getRecurso();
		int pontuacaoHosp2 = (recHosp2.getMedico() * 3) + (recHosp2.getEnfermeiro() * 3) + (recHosp2.getRespirador() * 5) 
				+ (recHosp2.getTomografo() * 12) + (recHosp2.getAmbulancia() * 10);
		
		if((pontuacaoHosp1 == pontuacaoHosp2) || (hosp1.getPercOcupacao() > 90)) {
			return true;
		} 
		
		 return false;
	}
	
	public Hospital efetivarTroca(Hospital hospJaSalvo1, Hospital hospJaSalvo2, 
			Recurso recHosp1, Recurso recHosp2) {
		
		Recurso recHospJaSalvo1 = hospJaSalvo1.getRecurso();
		
		Recurso recHospJaSalvo2 = hospJaSalvo2.getRecurso();
		
		recHospJaSalvo1.setAmbulancia(recHosp2.getAmbulancia());
		recHospJaSalvo1.setEnfermeiro(recHosp2.getEnfermeiro());
		recHospJaSalvo1.setMedico(recHosp2.getMedico());
		recHospJaSalvo1.setRespirador(recHosp2.getRespirador());
		recHospJaSalvo1.setTomografo(recHosp2.getTomografo());
		
		recHospJaSalvo2.setAmbulancia(recHosp1.getAmbulancia());
		recHospJaSalvo2.setEnfermeiro(recHosp1.getEnfermeiro());
		recHospJaSalvo2.setMedico(recHosp1.getMedico());
		recHospJaSalvo2.setRespirador(recHosp1.getRespirador());
		recHospJaSalvo2.setTomografo(recHosp1.getTomografo());
		
		hospitalRepository.save(hospJaSalvo2);
		
		return hospitalRepository.save(hospJaSalvo1);
		
	}
	
}
