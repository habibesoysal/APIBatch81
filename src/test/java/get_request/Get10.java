package get_request;

import base_url.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.GoRestTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get10 extends GoRestBaseUrl {
     /*
   Given
       https://gorest.co.in/public/v1/users/2986
   When
       User send GET Request to the URL
   Then
       Status Code should be 200
   And
       Response body should be like
    {
    "meta": null,
    "data": {
        "id": 2986,
        "name": "Navin Talwar",
        "email": "navin_talwar@mclaughlin.name",
        "gender": "male",
        "status": "inactive"
    }
}
*/

    @Test
    public void get10() {
        // Set the URL
        spec.pathParams("first", "users", "second", 2986);

        // Expected data oluştur

        GoRestTestData obje = new GoRestTestData();

        Map<String, String> dataKeyMap = obje.dataKeyMapMethod("Navin Talwar", "navin_talwar@mclaughlin.name",
                "male", "inactive");

        Map<String, Object> exDataMap = obje.exDataMethod(null, dataKeyMap);
        System.out.println(exDataMap);


        // Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // Do Assertion
        Map<String, Object> acDataMap = response.as(HashMap.class);
        System.out.println("acDataMap = " + acDataMap);

        assertEquals(exDataMap.get("meta"), acDataMap.get("meta"));

        assertEquals(dataKeyMap.get("name"), ((Map) acDataMap.get("data")).get("name"));
        assertEquals(dataKeyMap.get("email"), ((Map) acDataMap.get("data")).get("email"));
        assertEquals(dataKeyMap.get("gender"), ((Map) acDataMap.get("data")).get("gender"));
        assertEquals(dataKeyMap.get("status"), ((Map) acDataMap.get("data")).get("status"));

        assertEquals(200, response.getStatusCode());
    }
}