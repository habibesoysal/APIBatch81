package post_requests;

import base_urls.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.RestfulTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Post02 extends RestfulBaseUrl {

     /*
   Given
       1) https://restful-booker.herokuapp.com/booking
       2) {
            "firstname": "John",
            "lastname": "Doe",
            "totalprice": 11111,
            "depositpaid": true,
            "bookingdates": {
            "checkin": "2021-09-09",
            "checkout": "2021-09-21"
        }
    }
   When
       I send POST Request to the Url
   Then
       Status code is 200
       And response body should be like
       {
           "bookingid": 5315,
           "booking": {
               "firstname": "John",
               "lastname": "Doe",
               "totalprice": 11111,
               "depositpaid": true,
               "bookingdates": {
                   "checkin": "2021-09-09",
                   "checkout": "2021-09-21"
               }
           }
        }
*/

    RestfulTestData obje = new RestfulTestData();

    @Test
    public void post02() {
        // Set the URL
        spec.pathParam("first", "booking");

        // Set the expected data
        Map<String, String> innerMap = obje.bookingDatesMethod("2021-09-09", "2021-09-21");
        Map<String, Object> expectedDataMap = obje.exDataMethod("John", "Doe", 11111, true, innerMap);

        // Send the request and get the response

        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedDataMap).when().post("/{first}");
        response.prettyPrint();

        // Do Assertion
        Map<String, Object> actualDataMap = response.as(HashMap.class);
        System.out.println(actualDataMap);

        assertEquals(expectedDataMap.get("firstname"), ((Map)actualDataMap.get("booking")).get("firstname"));
        assertEquals(expectedDataMap.get("lastname"), ((Map)actualDataMap.get("booking")).get("lastname"));
        assertEquals(expectedDataMap.get("totalprice"), ((Map)actualDataMap.get("booking")).get("totalprice"));
        assertEquals(expectedDataMap.get("totalprice"), ((Map)actualDataMap.get("booking")).get("totalprice"));

        assertEquals(innerMap.get("checkin"), ((Map)((Map)actualDataMap.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(innerMap.get("checkout"), ((Map)((Map)actualDataMap.get("booking")).get("bookingdates")).get("checkout"));
    }
}