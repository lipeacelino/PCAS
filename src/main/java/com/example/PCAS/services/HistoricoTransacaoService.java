package com.example.PCAS.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PCAS.entities.HistoricoTransacao;
import com.example.PCAS.repositories.HistoricoTransacaoRepository;
import com.example.PCAS.repositories.RecursoRepository;


@Service
public class HistoricoTransacaoService {
	
	@Autowired
	private HistoricoTransacaoRepository hisTransacaoRepository;
	@Autowired
	private RecursoRepository recursoRepository;
	
	public List<HistoricoTransacao> getAll() {
		return hisTransacaoRepository.findAll();
	}
	
	public HistoricoTransacao addTransacao(HistoricoTransacao transacao) {
		recursoRepository.saveAll(Arrays.asList(transacao.getRecurso1(), transacao.getRecurso2()));
		return hisTransacaoRepository.save(transacao);
	}
	
}
