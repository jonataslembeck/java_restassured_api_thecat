package com.thecat.TesteAPI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

import org.junit.Test;

import io.restassured.response.Response;

public class DeletaVotacao {
	
	String vote_id;
	String apiKey = "114caa4e-413d-4a64-9ed9-8d0aa8ed4efb";
	
	public void votacaoApiTheCat() {
		String url = "https://api.thecatapi.com/v1/votes/";
		
		Response response = 
				given().contentType("application/json").
				body("{\"image_id\": \"auj\", \"value\": \"true\", \"sub_id\": \"demo-f78843\"}").
				when().post(url);

		validacao(response);
		
		String id = response.jsonPath().getString("id"); //Pega o id
		vote_id = id;
		
		// Visualiza mensagem de resposta
		System.out.println("Retorno:" + response.body().asString());
		System.out.println("Retorno ID:" + id);
	}
	
	public void deletaVotoApiTheCat() {
		String url = "https://api.thecatapi.com/v1/votes/{vote_id}";
		
		Response response = 
				given().
				contentType("application/json").
				header("x-api-key", apiKey).
				pathParam("vote_id", vote_id).
				when().delete(url);
		
		System.out.println("Retorno:" + response.body().asString());

		validacao(response);		
	}
	
	@Test
	public void deletaVotacaoApiTheCat() {
		votacaoApiTheCat();
		deletaVotoApiTheCat();
	}
	
	private void validacao(Response response) {
		response.then().body("message", containsString("SUCCESS")).statusCode(200);
	}

}
