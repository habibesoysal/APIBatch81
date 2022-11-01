package get_request;

import base_url.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.BosClassTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class BosClass extends GoRestBaseUrl {
     /*
   Given
       https://gorest.co.in/public/v1/users/53
   When
       User send GET Request to the URL
   Then
       Status Code should be 200
   And
       Response body should be like
    {
    "meta": null,
    "data": {
        "id": 53,
        "name": "Smita Patel",
        "email": "patel_smita@schaden-ferry.info",
        "gender": "female",
        "status": "inactive"
    }
}
*/

    @Test
    public void name() {

        // Set the URL
        spec.pathParams("pp1", "users", "pp2", 53);

        // Set the ex data
        BosClassTestData obje = new BosClassTestData();

        Map<String, String> innerMap = obje.innerMapOlusturMethodu("Smita Patel", "patel_smita@schaden-ferry.info",
                "female", "inactive");

        Map<String, Object> exDataMap = obje.outerMapOlusturMethodu(null, innerMap);

        // Set the response
        Response response = given().spec(spec).when().get("{pp1}/{pp2}");
        response.prettyPrint();

        // Do assertion
        Map<String, Object> actualDataMap = response.as(HashMap.class);

        assertEquals(exDataMap.get("meta"), actualDataMap.get("meta"));

        assertEquals(innerMap.get("name"), ((Map)actualDataMap.get("data")).get("name"));
        assertEquals(innerMap.get("email"), ((Map)actualDataMap.get("data")).get("email"));
        assertEquals(innerMap.get("gender"), ((Map)actualDataMap.get("data")).get("gender"));
        assertEquals(innerMap.get("status"), ((Map)actualDataMap.get("data")).get("status"));

    }
}