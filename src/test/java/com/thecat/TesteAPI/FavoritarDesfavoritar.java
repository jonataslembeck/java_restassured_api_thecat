package com.thecat.TesteAPI;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class FavoritarDesfavoritar extends MassaDeDados {
	
	String apiKey = "114caa4e-413d-4a64-9ed9-8d0aa8ed4efb";
	
	@BeforeClass
	public static void urlBase() {
		RestAssured.baseURI = "https://api.thecatapi.com/v1/";
	}
	
	private void validacao(Response response) {
		response.then().body("message", containsString("SUCCESS")).statusCode(200);
	}
	
	private void favoritar() {		
		Response response =
				given().
				contentType("application/json").
				body(corpoFavoritar).
				header("x-api-key", apiKey).
				when().post("favourites");
		
		validacao(response);
			
		favourite_id = response.jsonPath().getString("id"); // Armazena ID em váriavel glogal
	}
	
	private void desfavoritar() {
		Response response = 
				given().contentType("application/json").
				header("x-api-key", apiKey).
				pathParam("favourite_id", favourite_id).
				when().delete("favourites/{favourite_id}");
		
		validacao(response);
	}

	
	@Test
	public void testFavoritarDesfavoritar() {
		favoritar();
		desfavoritar();
	}
	

	@Test
	public void testRetornaFavoritoEspecifico() {
		favoritar();
		Response response =
		given()
		.contentType("application/json")
		.header("x-api-key", apiKey)
		.pathParam("favourite_id", favourite_id)
		.when()
		.get("favourites/{favourite_id}");
		
		validacao(response);
		System.out.println("Retorno:" + response.body().asString());
		
	}

}
