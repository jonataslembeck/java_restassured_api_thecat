package com.thecat.TesteAPI;


import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class VotacaoAPI {

	@Test
	public void testVotacaoApiTheCat() {
		String url = "https://api.thecatapi.com/v1/votes/";

		Response response = 
				given().contentType("application/json").body("{\"image_id\": \"auj\", \"value\": \"true\", \"sub_id\": \"demo-f78843\"}").
				when().post(url);

		response.then().body("message", containsString("SUCCESS")).statusCode(200);

		String id = response.jsonPath().getString("id"); // Pega o id

		// Visualiza mensagem de resposta
		System.out.println("Retorno:" + response.body().asString());
		System.out.println("Retorno ID:" + id);
	}
}
