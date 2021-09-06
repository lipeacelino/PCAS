package com.example.PCAS.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.example.PCAS.controllers")).paths(PathSelectors.any())
				.build()
				.useDefaultResponseMessages(false)
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfoBuilder().title("Pandemic Combat Aid System - API")
				.description("API RESTFul para armazenar informações sobre os hospitais, "
						+ "seus recursos (pessoais e materiais), " + "etc, ajudando no intercâmbio de recursos.")
				.license("Apache License Version 2.0").licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
				.termsOfServiceUrl("/service.html").version("1.0.0").build();

		return apiInfo;

	}
}
