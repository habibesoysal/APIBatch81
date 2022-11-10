package get_request;

import base_urls.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get15 extends RestfulBaseUrl {

    /*
        Given
	            https://restful-booker.herokuapp.com/booking/22
        When
		 		I send GET Request to the URL
		Then
		 		Status code is 200
           {
    "firstname": "Guoqiang",
    "lastname": "Morante Briones",
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
    public void get15() {

        // Set the URL
        spec.pathParams("first", "booking", "second", 22);

        // Set the expected data
        BookingDatesPojo bookingDatesObje = new BookingDatesPojo("2018-01-01", "2019-01-01");
        BookingPojo expectedData = new BookingPojo("Raajx", "Mangukiya", 111, true, bookingDatesObje, "Breakfast");

        // Send the request and get the response
        Response response = given().spec(spec).when().get("{first}/{second}");
        response.prettyPrint();

        // Do Assertion
        BookingPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), BookingPojo.class);

        // Soft Assertion
        // 1. Adım : softAssert objesi oluştur
        SoftAssert softAssert = new SoftAssert();

        // 2. Adım : Assertion yap
        softAssert.assertEquals(actualData.getFirstname(), expectedData.getFirstname(), "Firstname uyusmadi");
        softAssert.assertEquals(actualData.getLastname(), expectedData.getLastname(), "Lastname uyusmadi");
        softAssert.assertEquals(actualData.getTotalprice(), expectedData.getTotalprice(), "Totalprice uyusmadi");
        softAssert.assertEquals(actualData.getDepositpaid(), expectedData.getDepositpaid(), "Depositpadi uyusmadi");
        softAssert.assertEquals(actualData.getAdditionalneeds(), expectedData.getAdditionalneeds(), "Additionalneeds uyusmadi");

        softAssert.assertEquals(actualData.getBookingdates().getCheckin(), bookingDatesObje.getCheckin(), "Checkin uyusmadi");
        softAssert.assertEquals(actualData.getBookingdates().getCheckout(), bookingDatesObje.getCheckout(), "Checkout uyusmadi");

        // 3. Adım : assertAll(); yazmayı unutma
        softAssert.assertAll();


        // Hard Assertion
        assertEquals(200, response.getStatusCode());

        assertEquals(expectedData.getFirstname(), actualData.getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getAdditionalneeds());

        assertEquals(bookingDatesObje.getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(bookingDatesObje.getCheckout(), actualData.getBookingdates().getCheckout());
    }
}