package com.thecat.TesteAPI;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.apache.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ListaCategorias {
	
	String apiKey = "114caa4e-413d-4a64-9ed9-8d0aa8ed4ef";
	
	@BeforeClass
	public static void setUp() {
		baseURI = "https://api.thecatapi.com/"; // ou baseURI = "https://api.thecatapi.com/v1/";
		basePath = "v1/";
		enableLoggingOfRequestAndResponseIfValidationFails(); // Cria log se falhar
		
		RestAssured.requestSpecification = new RequestSpecBuilder().setContentType(ContentType.JSON).build();
		RestAssured.responseSpecification = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
	}
	
	@Test
	public void testListaCategorias() {
		Response response =
		
		given().
			contentType(ContentType.JSON). // ou contentType("application/json; charset=utf-8").
			header("x-api-key",apiKey).
		when().
			get("categories");
		
		// [{"id": 5,"name":"boxes"}, {"id": 15,"name": "clothes"},{"id": 1,"name": "hats"},{"id": 14,"name": "sinks"},{"id": 2,"name": "space"},{"id": 4,"name": "sunglasses"},{"id": 7,"name": "ties"}]
		
		response.then().statusCode(200); // Valida se retorno é 200
		response.then().statusCode(HttpStatus.SC_OK); // Valida se status é 200
		response.then().body("id", is(notNullValue())); // Valida se não é nullo o array no field id
		response.then().body("id[0]", is(5)); //Valida se id na posição 0 é 5
		response.then().body("name[0]", is("boxes")); //Valida se name na posição 0 é boxes
		response.then().body("size()", is(7)); //Valida qtd de registros ou por id -> response.then().body("id.size()", is(7));
		
		// Faz um for dentro do array, verificando se tem determinado valor em todos
		// it = for, startsWith = Começa com 's', size = tudo aquilo tem o tamanho = 3
		response.then().body("findAll { it.name.startsWith('s') }.size()", is(3));
	}

}
