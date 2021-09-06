package com.example.PCAS.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PCAS.entities.HistoricoTransacao;
import com.example.PCAS.entities.Hospital;
import com.example.PCAS.entities.Recurso;
import com.example.PCAS.exceptions.ErroDeValidacaoException;

@Service
public class TrocaRecursoService {
	
	@Autowired
	private HospitalService hospitalRepository;
	@Autowired
	private HistoricoTransacaoService histTransacaoService;

	public Hospital trocarRecursos(Long idHosp1, Long idHosp2, Recurso recHosp1, Recurso recHosp2) {
		
		Hospital hospJaSalvo1 = hospitalRepository.findById(idHosp1);
		Hospital hospJaSalvo2 = hospitalRepository.findById(idHosp2);
		
		if (verificaPossibilidadeDeEfetuarTroca(hospJaSalvo1, hospJaSalvo2)) {
			return efetivarTroca(hospJaSalvo1, hospJaSalvo2, recHosp1, recHosp2);
		} else {
			throw new ErroDeValidacaoException("Não foi possível concluir a troca de recursos!");
		}
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
		
		recHospJaSalvo1.setAmbulancia((recHospJaSalvo1.getAmbulancia() - recHosp1.getAmbulancia()) + recHosp2.getAmbulancia());
		recHospJaSalvo1.setEnfermeiro((recHospJaSalvo1.getEnfermeiro() - recHosp1.getEnfermeiro()) + recHosp2.getEnfermeiro());
		recHospJaSalvo1.setMedico((recHospJaSalvo1.getMedico() - recHosp1.getMedico()) + recHosp2.getMedico());
		recHospJaSalvo1.setRespirador((recHospJaSalvo1.getRespirador() - recHosp1.getRespirador()) + recHosp2.getRespirador());
		recHospJaSalvo1.setTomografo((recHospJaSalvo1.getTomografo() - recHosp1.getTomografo()) + recHosp2.getTomografo());
		
		recHospJaSalvo2.setAmbulancia((recHospJaSalvo2.getAmbulancia() - recHosp2.getAmbulancia()) + recHosp1.getAmbulancia());
		recHospJaSalvo2.setEnfermeiro((recHospJaSalvo2.getEnfermeiro() - recHosp2.getEnfermeiro()) + recHosp1.getEnfermeiro());
		recHospJaSalvo2.setMedico((recHospJaSalvo2.getMedico() - recHosp2.getMedico()) + recHosp1.getMedico());
		recHospJaSalvo2.setRespirador((recHospJaSalvo2.getRespirador() - recHosp2.getRespirador()) + recHosp1.getRespirador());
		recHospJaSalvo2.setTomografo((recHospJaSalvo2.getTomografo() - recHosp2.getTomografo()) + recHosp1.getTomografo());
		
		hospitalRepository.addHospital(hospJaSalvo2);
		Hospital hospital = hospitalRepository.addHospital(hospJaSalvo1);
		
		HistoricoTransacao ht = HistoricoTransacao
				.builder()
				.h1(hospJaSalvo1)
				.h2(hospJaSalvo2)
				.recurso1(recHosp1)
				.recurso2(recHosp2)
				.build();
		histTransacaoService.addTransacao(ht);
		
		return hospital;
		
	}
	
}
