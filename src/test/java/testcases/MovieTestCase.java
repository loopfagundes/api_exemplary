package testcases;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class MovieTestCase {
    // é passo passo aqui
    @Test
    public void GETMovieWithSuccess(){
        //https://api.themoviedb.org/3/movie/284053?api_key=085b2e979e220c316cdcdbc544587aaa é link completo.

        String movieId = "284053";  //se quiser usar assim...tem que alterar a pathParam e colocar o valor 'movieId'

        Response response =
                given().
                        baseUri("https://api.themoviedb.org/3"). // é base url e tem que ver o documento.
                        basePath("movie"). // é base path... antes base url, mas depende de contexto e o documento.
                        pathParam("movie_id", "284053"). // pode colocar o valor 'movieId' string ou numero id original... se colocar o numero original e tem que apagar String movieId.
                        param("api_key", "085b2e979e220c316cdcdbc544587aaa"). // seu apiKey, dar uma olhada seu conta que receber a chave api.
                when().
                        get("/{movie_id}"); // se caso id, colocar aqui... se nao, nao precisa colocar.

        System.out.println("Status Line: " + response.getStatusLine());
        response.getBody().prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    // é avancado aqui.
    @Parameters ({"baseUrl", "movieEP", "movieId", "apiKey"})
    @Test
    public void GETMovieWithSuccess01(String baseUrl, String movieEP, String movieId, String apiKey){
        Response response =
                given().
                        baseUri(baseUrl).
                        basePath(movieEP).
                        pathParam("movie_id", movieId).
                        param("api_key", apiKey).
                when().
                        get("/{movie_id}");

        System.out.println("Status Line: " + response.getStatusLine());
        response.getBody().prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200);
    }


}
