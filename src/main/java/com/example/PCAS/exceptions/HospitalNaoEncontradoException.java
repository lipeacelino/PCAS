package com.example.PCAS.exceptions;

public class HospitalNaoEncontradoException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public HospitalNaoEncontradoException(String message) {
		super(message);
	}
	
}
