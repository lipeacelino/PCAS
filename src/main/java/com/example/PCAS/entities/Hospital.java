package com.example.PCAS.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Hospital {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@JsonProperty("perc_ocupacao")
	private Integer percOcupacao;

	private String nome;

	private String cnpj;
	
	@OneToOne
	private Endereco endereco;
	
	@OneToOne
	private Recurso recurso;
	
	@OneToOne
	private Localizacao localizacao;
	
	/*
	 * Cada hospital possui uma data, que dependendo do percentual de ocupação pode ser dateTimeMaiorQue90 ou dateTimeMenor90 
	 * 
	 * A cada transação as datas são atualizadas
	 * 
	 * A data mais velha pertence ao hospital que mantém o percentual de ocupação a mais tempo
	 * 
	 */
	@JsonIgnore
	private Date dateTimeMaiorQue90; 
	@JsonIgnore
	private Date dateTimeMenorQue90; 
	
	@PrePersist
	public void verificaSuperlotacaoInicial() {
		if (percOcupacao > 90) {
			this.dateTimeMaiorQue90 = new Date();
			this.dateTimeMenorQue90 = null;
		} else {
			this.dateTimeMenorQue90 = new Date();
			this.dateTimeMaiorQue90 = null;
		}
	}
	
	
	
}
