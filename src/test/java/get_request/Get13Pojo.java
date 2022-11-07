package get_request;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GoRestDataPojo;
import pojos.GoRestPojo;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get13Pojo extends GoRestBaseUrl {

    /*
        Given
            https://gorest.co.in/public/v1/users/2508
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
          {
            "meta": null,
            "data": {
                "id": 2508,
                "name": "Sharmila Deshpande VM",
                "email": "deshpande_sharmila_vm@becker.name",
                "gender": "female",
                "status": "active"
                 }
          }
    */

    @Test
    public void get13Pojo() {

        // Set the URL
        spec.pathParams("first", "users", "second", 2508);

        // Set the expected data
        GoRestDataPojo goRestDataObje = new GoRestDataPojo(2508, "Sharmila Deshpande VM", "deshpande_sharmila_vm@becker.name", "female", "active");
        GoRestPojo expectedData = new GoRestPojo(null, goRestDataObje);

        // Send the request and get the response
        Response response = given().spec(spec).when().get("{first}/{second}");
        response.prettyPrint();

        // Do Assertion
        GoRestPojo actualData = response.as(GoRestPojo.class);

        assertEquals(200, response.getStatusCode());

        assertEquals(expectedData.getMeta(), actualData.getMeta());

        assertEquals(goRestDataObje.getId(), actualData.getData().getId());
        assertEquals(goRestDataObje.getName(), actualData.getData().getName());
        assertEquals(goRestDataObje.getEmail(), actualData.getData().getEmail());
        assertEquals(goRestDataObje.getGender(), actualData.getData().getGender());
        assertEquals(goRestDataObje.getStatus(), actualData.getData().getStatus());

    }
}