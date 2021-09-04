package com.example.PCAS.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonAlias;
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
	
}
