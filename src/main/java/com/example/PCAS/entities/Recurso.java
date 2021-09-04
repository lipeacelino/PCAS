package com.example.PCAS.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Recurso {

	public Recurso() {
		this.medico = 0;
		this.enfermeiro = 0;
		this.respirador = 0;
		this.tomografo = 0;
		this.ambulancia = 0;
	}
	
	@Id
	@GeneratedValue
	private Long id;
	
	private Integer medico;
	private Integer enfermeiro;
	private Integer respirador;
	private Integer tomografo;
	private Integer ambulancia;
	
}
