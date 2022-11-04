package get_request;

import base_url.RestfulBaseUrl;
import groovy.transform.stc.POJO;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import test_data.RestfulTestData;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Get12Pojo extends RestfulBaseUrl {
     /*
     Given
         https://restful-booker.herokuapp.com/booking/18
     When
    I send GET Request to the URL
   Then
    Status code is 200
And
    Response body is like:
                        {
    "firstname": "Dane",
    "lastname": "Combs",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "Breakfast"
}
  */

    @Test
    public void get12Pojo() {

        // Set the URL
        spec.pathParams("first", "booking", "second", 18);

        // Set the expected data
        BookingDatesPojo bookingDatesObje = new BookingDatesPojo("2018-01-01", "2019-01-01");
        BookingPojo expectedData = new BookingPojo("Dane", "Combs", 111, true, bookingDatesObje, "Breakfast");

        // Send the request and get the response
        Response response = given().spec(spec).when().get("{first}/{second}");

        // Do Assertion
        BookingPojo actualData = response.as(BookingPojo.class);

        assertEquals(expectedData.getFirstname(), actualData.getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
        assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());

        // 1. Yol
        assertEquals(expectedData.getBookingdates().getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout(), actualData.getBookingdates().getCheckout());

        // 2. Yol ==> Tavsiye edilen
        assertEquals(bookingDatesObje.getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(bookingDatesObje.getCheckout(), actualData.getBookingdates().getCheckout());
    }
}