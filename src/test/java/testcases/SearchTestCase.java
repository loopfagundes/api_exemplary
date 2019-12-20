package testcases;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SearchTestCase {

    @Parameters({"baseUrl", "searchEP", "apiKey", "query", "page", "adult"})
    @Test
    public void GETSearchWithSuccess(String baseUrl, String searchEP, String apiKey, String query, String page, String adult ){
        //https://api.themoviedb.org/3/search/movie?api_key=085b2e979e220c316cdcdbc544587aaa&query=thor&page=1&include_adult=false

        Response response =
                given().
                        baseUri(baseUrl).
                        basePath(searchEP).
                        param("api_key", apiKey).
                        param("query", query).
                        param("page", page).
                        param("include_adult", adult).
                when().
                        get();

        Assert.assertEquals(response.getStatusCode(),200);
        System.out.println("Status Code -> [200] = " + response.getStatusCode());

        System.out.println("Status Line: " + response.getStatusLine());

        System.out.println("Body: " + response.getBody());

        System.out.println("Content Type: " + response.getContentType());

        response.getBody().prettyPrint();
    }
}
