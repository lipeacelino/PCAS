package com.example.PCAS.exceptions;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lowagie.text.DocumentException;

@RestControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(ErroDeValidacaoException.class)
    public ResponseEntity erroDeValidacao(ErroDeValidacaoException ex) {
        ErroDetalhes erroDetalhes = ErroDetalhes.builder()
                .timestamp(this.getDataFormatada())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.name())
                .message(ex.getMessage()).build();

        return new ResponseEntity(erroDetalhes, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler({IOException.class, DocumentException.class})
	public ResponseEntity erroAoGerarRelatorio(Exception ex) {
        ErroDetalhes erroDetalhes = ErroDetalhes.builder()
                .timestamp(this.getDataFormatada())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.name())
                .message(ex.getMessage()).build();

        return new ResponseEntity(erroDetalhes, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(HospitalNaoEncontradoException.class)
	public ResponseEntity erroAoBuscarHospital(Exception ex) {
        ErroDetalhes erroDetalhes = ErroDetalhes.builder()
                .timestamp(this.getDataFormatada())
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.name())
                .message(ex.getMessage()).build();

        return new ResponseEntity(erroDetalhes, HttpStatus.NOT_FOUND);
    }
	
	private String getDataFormatada() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - kk:mm:ss");
        String date = sdf.format(new Date().getTime());
        return date;
    }
}
