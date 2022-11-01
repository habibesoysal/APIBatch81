package get_request;

import base_url.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Get09 extends RestfulBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/91
    When
        I send GET Request to the url
    Then
        Response body should be like that;
            {
            "firstname": "Sally",
            "lastname": "Brown",
            "totalprice": 111,
            "depositpaiddepositpaid": true,
            "bookingdates": {
                "checkin": "2013-02-23",
                "checkout": "2014-10-23"
            },
            "additionalneedsadditionalneeds": "Breakfast"
            }
 */

    @Test
    public void get09() {

        // Set the URL
        spec.pathParams("first", "booking", "second", 91);

        // Set the expected data
        Map<String, String> bookingdatesMap = new HashMap<>();
        bookingdatesMap.put("checkin", "2013-02-23");
        bookingdatesMap.put("checkout", "2014-10-23");

        Map<String, Object> exDataMap = new HashMap<>();
        exDataMap.put("firstname", "Sally");
        exDataMap.put("lastname", "Brown");
        exDataMap.put("totalprice", 111);
        exDataMap.put("depositpaid", true);
        exDataMap.put("bookingdates", bookingdatesMap);
        exDataMap.put("bookingdatesMap", "Breakfast");

        System.out.println(exDataMap);

        // Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // Do Assertion

        Map<String, Object> actualDataMap = response.as(HashMap.class);
        assertEquals(exDataMap.get("firstname"), actualDataMap.get("firstname"));
        assertEquals(exDataMap.get("lastname"), actualDataMap.get("lastname"));
        assertEquals(exDataMap.get("totalprice"), actualDataMap.get("totalprice"));
        assertEquals(exDataMap.get("depositpaid"), actualDataMap.get("depositpaid"));

        assertEquals(bookingdatesMap.get("checkin"), ((Map)actualDataMap.get("bookingdates")).get("checkin"));
        assertEquals(bookingdatesMap.get("checkout"), ((Map)actualDataMap.get("bookingdates")).get("checkout"));

        assertEquals(exDataMap.get("additionalneedsadditionalneeds"), actualDataMap.get("additionalneedsadditionalneeds"));
    }
}