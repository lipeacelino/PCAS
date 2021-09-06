package com.example.PCAS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PCAS.entities.HistoricoTransacao;

@Repository
public interface HistoricoTransacaoRepository extends JpaRepository<HistoricoTransacao, Long> {

}
