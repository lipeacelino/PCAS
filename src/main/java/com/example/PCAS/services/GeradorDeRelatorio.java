package com.example.PCAS.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PCAS.DTO.RecursoRelatorioDTO;
import com.example.PCAS.entities.HistoricoTransacao;
import com.example.PCAS.entities.Hospital;
import com.example.PCAS.entities.Recurso;

@Service
public class GeradorDeRelatorio {

	@Autowired
	private HospitalService hospService;
	@Autowired
	private HistoricoTransacaoService hihTransacaoService;

	public List<Hospital> relaHospComOcupacaoMaiorQue90() {
		return hospService.getAll().stream().filter(h -> h.getPercOcupacao() > 90).collect(Collectors.toList());
	}

	public List<Hospital> relaHospComOcupacaoMenorQue90() {
		return hospService.getAll().stream().filter(h -> h.getPercOcupacao() < 90).collect(Collectors.toList());
	}

	public List<HistoricoTransacao> historicoTransacoes() {
		return hihTransacaoService.getAll();
	}

	public Hospital relaHospitalComOcupacaoMaiorQue90PorMaisTempo() {

		List<Hospital> hospList = hospService.getAll();
		Hospital hospitalComDataMaisAntiga = null;

		for (int i = 0; i < hospList.size(); i++) {
			if (hospList.get(i).getDateTimeMaiorQue90() != null) {
				if (hospitalComDataMaisAntiga == null) {
					hospitalComDataMaisAntiga = hospList.get(i);
				}
			}
			for (int j = 1; j < hospService.getAll().size(); j++) {
				if ((hospList.get(j).getDateTimeMaiorQue90() != null) && (hospitalComDataMaisAntiga != null)) {
					if (hospitalComDataMaisAntiga.getDateTimeMaiorQue90()
							.compareTo(hospList.get(j).getDateTimeMaiorQue90()) > 0) {
						hospitalComDataMaisAntiga = hospList.get(j);
					}
					;
				}
			}
		}

		return hospitalComDataMaisAntiga;
	}

	public Hospital relaHospitalComOcupacaoMenorQue90PorMaisTempo() {

		List<Hospital> hospList = hospService.getAll();
		Hospital hospitalComDataMaisRecente = null;

		for (int i = 0; i < hospList.size(); i++) {
			if (hospList.get(i).getDateTimeMenorQue90() != null) {
				if(hospitalComDataMaisRecente == null) {
					hospitalComDataMaisRecente = hospList.get(i);
				}
			}
			for (int j = 1; j < hospService.getAll().size(); j++) {
				if ((hospList.get(j).getDateTimeMenorQue90() != null) && (hospitalComDataMaisRecente != null)) {
					if (hospitalComDataMaisRecente.getDateTimeMenorQue90()
							.compareTo(hospList.get(j).getDateTimeMenorQue90()) > 0) {
						hospitalComDataMaisRecente = hospList.get(j);
					};
				}
			}
		}

		return hospitalComDataMaisRecente;
	}

	public RecursoRelatorioDTO relMediaDeRecursos() {

		List<Recurso> recursosDosHospitais = hospService.getAll().stream().map(h -> h.getRecurso())
				.collect(Collectors.toList());

		RecursoRelatorioDTO qtdTotalDeRecursos = new RecursoRelatorioDTO();

		for (Recurso r : recursosDosHospitais) {
			qtdTotalDeRecursos.setAmbulancia(qtdTotalDeRecursos.getAmbulancia() + r.getAmbulancia());
			qtdTotalDeRecursos.setEnfermeiro(qtdTotalDeRecursos.getEnfermeiro() + r.getEnfermeiro());
			qtdTotalDeRecursos.setMedico(qtdTotalDeRecursos.getMedico() + r.getMedico());
			qtdTotalDeRecursos.setRespirador(qtdTotalDeRecursos.getRespirador() + r.getRespirador());
			qtdTotalDeRecursos.setTomografo(qtdTotalDeRecursos.getTomografo() + r.getTomografo());
		}

		int qtdDeHospitais = hospService.getAll().size();

		qtdTotalDeRecursos.setAmbulancia(qtdTotalDeRecursos.getAmbulancia() / qtdDeHospitais);
		qtdTotalDeRecursos.setEnfermeiro(qtdTotalDeRecursos.getEnfermeiro() / qtdDeHospitais);
		qtdTotalDeRecursos.setMedico(qtdTotalDeRecursos.getMedico() / qtdDeHospitais);
		qtdTotalDeRecursos.setRespirador(qtdTotalDeRecursos.getRespirador() / qtdDeHospitais);
		qtdTotalDeRecursos.setTomografo(qtdTotalDeRecursos.getTomografo() / qtdDeHospitais);

		return qtdTotalDeRecursos;
	}
}
