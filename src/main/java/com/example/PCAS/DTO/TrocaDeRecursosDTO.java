package com.example.PCAS.DTO;

import com.example.PCAS.entities.Recurso;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrocaDeRecursosDTO {
	
	@JsonProperty("recursos_hospital_1")
	Recurso recursosHosp1;
	@JsonProperty("recursos_hospital_2")
	Recurso recursosHosp2;
}
