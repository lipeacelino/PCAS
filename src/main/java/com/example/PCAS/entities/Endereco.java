package com.example.PCAS.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
public class Endereco {

	@Id
	@GeneratedValue
	private Long id;
	
	private String rua;
	
	private String numero;
	
	private String cep;
	
	private String estado;

	@Override
	public String toString() {
		return "Rua " + rua + ", número " + numero + ", cep " + cep + ", estado " + estado;
	}
	
}
