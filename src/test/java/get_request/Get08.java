package get_request;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;


public class Get08 extends JsonplaceholderBaseUrl {

    /*
De-serialization : Json datayı Java objesine cevirme
Serialization: Java objesini Json formatına cevirme
De-serialization : Iki turlu yapacagiz.
Gson: Google tarafindan uretilmistir
Object Mapper: Daha popüler
 */


    /*
    Given
       https://jsonplaceholder.typicode.com/todos/2
   When
       I send GET Request to the URL
   Then
       Status code is 200
       And "completed" is false
       And "userId" is 1
       And "title" is "quis ut nam facilis et officia qui"
       And header "Via" is "1.1 vegur"
       And header "Server" is "cloudflare"
       {
           "userId": 1,
           "id": 2,
           "title": "quis ut nam facilis et officia qui",
           "completed": false
       }
*/

    @Test
    public void get08() {

        spec.pathParams("first", "todos", "second", 2);

        // Set the Expected Data ==> Payload
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", 1);
        expectedData.put("id", 2);
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("completed", false);
        System.out.println("expectedData = " + expectedData);

        // Send the get request
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // Do Assertion
        Map<String, Object> actualData = response.as(HashMap.class); // De Serialization
        System.out.println("actualData = " + actualData);

        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("id"), actualData.get("id"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));

        assertEquals("1.1 vegur", response.getHeader("Via"));
        assertEquals("cloudflare", response.getHeader("Server"));
        assertEquals(200, response.statusCode());

    }

    // Dinamik yöntem
    @Test
    public void get08b() {

        spec.pathParams("first", "todos", "second", 2);

        // Set the Expected Data ==> Payload
        JsonPlaceHolderTestData exDataObje = new JsonPlaceHolderTestData();

        Map<String, Object> exData = exDataObje.exDataMethod(1, "quis ut nam facilis et officia qui", false);

        // Send the get request
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // Do Assertion
        Map<String, Object> actualData = response.as(HashMap.class); // De Serialization
        System.out.println("actualData = " + actualData);

        assertEquals(exData.get("userId"), actualData.get("userId"));
        assertEquals(exData.get("title"), actualData.get("title"));
        assertEquals(exData.get("completed"), actualData.get("completed"));

        assertEquals("1.1 vegur", response.getHeader("Via"));
        assertEquals("cloudflare", response.getHeader("Server"));
        assertEquals(200, response.statusCode());
    }
}