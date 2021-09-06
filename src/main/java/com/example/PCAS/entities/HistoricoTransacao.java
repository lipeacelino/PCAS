package com.example.PCAS.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

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
public class HistoricoTransacao {

	@Id
	@GeneratedValue
	private Long id;
	@OneToOne
	private Hospital h1;
	@OneToOne
	private Hospital h2;
	@OneToOne
	private Recurso recurso1;
	@OneToOne
	private Recurso recurso2;
	
}
