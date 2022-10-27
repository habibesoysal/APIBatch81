package get_request;

import base_url.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.*;
import static org.hamcrest.Matchers.*;

public class Get06 extends RestfulBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/2325
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is "application/json"
        And
            Response body should be like;
         {
    "firstname": "Bradley",
    "lastname": "Pearson",
    "totalprice": 132,
    "depositpaid": false,
    "bookingdates": { // Outer json
        "checkin": "2022-10-27", // Inner json
        "checkout": "2022-11-07" // Inner json
    },
    "additionalneeds": "None"
}
     */

    @Test
    public void get01() {

        // 1. Set the URL (URL oluştur)
        spec.pathParams("first", "booking", "second", 2325);

        // 2. Set the expected data
        // Bu casede istenmediği için geçiyoruz

        // 3. Set the request and get the response (Talep gönder ve cevap al)
        Response response = given().spec(spec).when().get("/{first}/{second}");
        //response.prettyPrint();

        // 4. Do Assertion
        // 1. Yol
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname", equalTo("Bradley"),
                        "lastname", equalTo("Pearson"),
                        "totalprice ", equalTo(132),
                        "depositpaid", equalTo(false),
                        "bookingdates.checkin", equalTo("2022-10-27"),
                        "bookingdates.checkout", equalTo("2022-11-07"),
                        "additionalneeds", equalTo("None"));

        // 2. Yol Jsonpath classının kullanımı
        JsonPath json = response.jsonPath();

        assertEquals("Bradley", json.getString("firstname"));
        assertEquals("Pearson", json.getString("lastname"));
        assertEquals(132, json.getInt("totalprice"));
        assertFalse(json.getBoolean("depositpaid"));
        assertEquals("2022-10-27", json.getString("bookingdates.checkin"));
        assertEquals("2022-11-07", json.getString("bookingdates.checkout"));
        assertEquals("None", json.getString("additionalneeds"));

        // 3. Yol Soft Assertion kullanımı
        // softAssert classı 3 adımda kullanılır

        // 1) Obje oluşturma
        SoftAssert softAssert = new SoftAssert();

        // 2) Do Assertion (doğrulama yapma)
        softAssert.assertEquals(json.getString("firstname"), "Bradley", "First name hatalı");
        softAssert.assertEquals(json.getString("lastname"), "Pearson", "Last name hatalı");
        softAssert.assertEquals(json.getInt("totalprice"), 132, "Total price hatalı");
        softAssert.assertEquals(json.getBoolean("depositpaid"), false, "depositpaid hatalı");
        softAssert.assertEquals(json.getString("bookingdates.checkin"), "2022-10-27", "Check in tarihi hatalı");
        softAssert.assertEquals(json.getString("bookingdates.checkout"), "2022-11-07", "Check out tarihi hatalı");
        softAssert.assertEquals(json.getString("additionalneeds"), "None", "Additionalneeds hatalı");
        softAssert.assertAll();

         /*
         3) Doğrulama işlemleri sonunda softAssert.assertAll() diyerek
         yaptığımız tüm doğrulama işlemlerini kjontrol edilmesini sağlıyoruz.
         Eğer işlemin sonunda softAssert.assertAll() kullanmaz isek taleplerimiz hatalı bile testimiz pass olacaktır.
         */
    }
}