package com.example.PCAS.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErroDetalhes {

	private String timestamp;
	private int status;
	private String error;
	private String message;
	
}
