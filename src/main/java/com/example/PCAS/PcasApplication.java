package com.example.PCAS;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.PCAS.entities.Endereco;
import com.example.PCAS.entities.Hospital;
import com.example.PCAS.entities.Localizacao;
import com.example.PCAS.entities.Recurso;
import com.example.PCAS.repositories.EnderecoRepository;
import com.example.PCAS.repositories.HospitalRepository;
import com.example.PCAS.repositories.LocalizacaoRepository;
import com.example.PCAS.repositories.RecursoRepository;

@SpringBootApplication
public class PcasApplication {

	public static void main(String[] args) {
		SpringApplication.run(PcasApplication.class, args);
	}

	@Bean
	public ApplicationRunner initializer(LocalizacaoRepository locRepository, 
			HospitalRepository hospRepository, EnderecoRepository endRepository, RecursoRepository recRepository) {
		return args -> {
			Localizacao loc1 = locRepository.save(Localizacao.builder().latitude(-2.8488).longitude(9.1399).build());
			Endereco end1 = endRepository.save(Endereco.builder().cep(345098000).estado("PB").numero(22).rua("Rua Luís Da Silva").build());
			Recurso rec1 = recRepository.save(Recurso.builder().ambulancia(0).tomografo(0).enfermeiro(1).medico(0).respirador(2).build()); 
			hospRepository.save(Hospital.builder().percOcupacao(91).nome("Hospital Santa Clara")
					.cnpj("66120943000166").endereco(end1).localizacao(loc1).recurso(rec1).build());
			
			Localizacao loc2 = locRepository.save(Localizacao.builder().latitude(56.1413).longitude(-43.4193).build());
			Endereco end2 = endRepository.save(Endereco.builder().cep(86751000).estado("CE").numero(22).rua("Rua Aparecida de Amaral").build());
			Recurso rec2 = recRepository.save(Recurso.builder().ambulancia(1).tomografo(0).enfermeiro(0).medico(1).respirador(0).build()); 
			hospRepository.save(Hospital.builder().percOcupacao(88).nome("Hospital Abílio de Oliveira")
					.cnpj("66120943000166").endereco(end2).localizacao(loc2).recurso(rec2).build());
		};
	}
}
