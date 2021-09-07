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
	public ApplicationRunner inicializador(LocalizacaoRepository locRepository, 
			HospitalRepository hospRepository, EnderecoRepository endRepository, RecursoRepository recRepository) {
		return args -> {
			Localizacao loc1 = locRepository.save(Localizacao.builder().latitude(-2.8488).longitude(9.1399).build());
			Endereco end1 = endRepository.save(Endereco.builder().cep("08790050").estado("PB").numero("30").rua("Rua Silva Paranhos").build());
			Recurso rec1 = recRepository.save(Recurso.builder().ambulancia(0).tomografo(0).enfermeiro(2).medico(0).respirador(2).build()); 
			hospRepository.save(Hospital.builder().percOcupacao(4).nome("Hospital Santa Clara")
					.cnpj("29760875000173").endereco(end1).localizacao(loc1).recurso(rec1).build());
			
			Localizacao loc2 = locRepository.save(Localizacao.builder().latitude(56.1413).longitude(-43.4193).build());
			Endereco end2 = endRepository.save(Endereco.builder().cep("17031722").estado("SP").numero("1001").rua("Rua Landerico Micheletti").build());
			Recurso rec2 = recRepository.save(Recurso.builder().ambulancia(1).tomografo(0).enfermeiro(0).medico(1).respirador(0).build()); 
			hospRepository.save(Hospital.builder().percOcupacao(98).nome("Hospital Abílio de Oliveira")
					.cnpj("96264554000175").endereco(end2).localizacao(loc2).recurso(rec2).build());
			
			Localizacao loc3 = locRepository.save(Localizacao.builder().latitude(56.1413).longitude(-43.4193).build());
			Endereco end3 = endRepository.save(Endereco.builder().cep("13185690").estado("SP").numero("710").rua("Rua Atibaia").build());
			Recurso rec3 = recRepository.save(Recurso.builder().ambulancia(1).tomografo(0).enfermeiro(0).medico(1).respirador(0).build()); 
			hospRepository.save(Hospital.builder().percOcupacao(97).nome("Hospital Santa Luzia")
					.cnpj("82197267000185").endereco(end3).localizacao(loc3).recurso(rec3).build());
			
			
			Localizacao loc4 = locRepository.save(Localizacao.builder().latitude(56.1413).longitude(-43.4193).build());
			Endereco end4 = endRepository.save(Endereco.builder().cep("76829078").estado("RO").numero("271").rua("Rua Quirino Campofiorito").build());
			Recurso rec4 = recRepository.save(Recurso.builder().ambulancia(1).tomografo(0).enfermeiro(0).medico(3).respirador(0).build()); 
			hospRepository.save(Hospital.builder().percOcupacao(88).nome("Hospital Aparecida Cruz")
					.cnpj("20511339000160").endereco(end4).localizacao(loc4).recurso(rec4).build());
			
			
			Localizacao loc5 = locRepository.save(Localizacao.builder().latitude(56.1413).longitude(-43.4193).build());
			Endereco end5 = endRepository.save(Endereco.builder().cep("58084105").estado("PB").numero("374").rua("Conjunto Norfil").build());
			Recurso rec5 = recRepository.save(Recurso.builder().ambulancia(1).tomografo(4).enfermeiro(0).medico(1).respirador(0).build()); 
			hospRepository.save(Hospital.builder().percOcupacao(38).nome("Hospital Américo Cruz")
					.cnpj("07504277000109").endereco(end5).localizacao(loc5).recurso(rec5).build());
			
			
			Localizacao loc6 = locRepository.save(Localizacao.builder().latitude(56.1413).longitude(-43.4193).build());
			Endereco end6 = endRepository.save(Endereco.builder().cep("58100464").estado("CE").numero("287").rua("Travessa Primo José Viana").build());
			Recurso rec6 = recRepository.save(Recurso.builder().ambulancia(1).tomografo(0).enfermeiro(0).medico(1).respirador(0).build()); 
			hospRepository.save(Hospital.builder().percOcupacao(90).nome("Hospital Ruan Oliveira")
					.cnpj("52096543000158").endereco(end6).localizacao(loc6).recurso(rec6).build());
			
			
			Localizacao loc7 = locRepository.save(Localizacao.builder().latitude(56.1413).longitude(-43.4193).build());
			Endereco end7 = endRepository.save(Endereco.builder().cep("67015301").estado("CE").numero("869").rua("Rua Aparecida de Amaral").build());
			Recurso rec7 = recRepository.save(Recurso.builder().ambulancia(1).tomografo(1).enfermeiro(2).medico(1).respirador(0).build()); 
			hospRepository.save(Hospital.builder().percOcupacao(99).nome("Hospital José Chagas")
					.cnpj("20578106000184").endereco(end7).localizacao(loc7).recurso(rec7).build());
		};
	}
}
