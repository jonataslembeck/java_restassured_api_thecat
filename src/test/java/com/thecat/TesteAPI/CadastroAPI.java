package com.thecat.TesteAPI;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.apache.http.HttpStatus;

public class CadastroAPI {
	
	private static final String CADASTRO_ENDPOINT = "user/passwordlesssignup";
	
	@BeforeClass
	public static void setUp() {
		baseURI = "https://api.thecatapi.com/v1";
		enableLoggingOfRequestAndResponseIfValidationFails(); // Cria log se falhar
	}
	
	@Test
	public void cadastroApiTheCat() {
		given().
			contentType(ContentType.JSON).
			body("{\"email\": \"testapi@getnada.com\",\"appDescription\": \"Testes de API\"}").
		when().
			post(CADASTRO_ENDPOINT).
		then().
			statusCode(HttpStatus.SC_OK).
			body("message", is("SUCCESS"));
	}	

}
