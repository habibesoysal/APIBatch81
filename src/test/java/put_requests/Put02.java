package put_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestApiDataPojo;
import pojos.DummyRestApiResponseBodyPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Put02 extends DummyRestApiBaseUrl {

    /*
    Given
    https://dummy.restapiexample.com/api/v1/update/21
     {
        "employee_name": "Ali Can",
        "employee_salary": 111111,
        "employee_age": 23,
        "profile_image": "Perfect image"
     }
When
    PUT Request
      Request body:
Then
    i) Status code is 200
And
    ii) Response body should be like the following
                {
                    "status": "success",
                    "data": {
                        "employee_name": "Ali Can",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                    },
                    "message": "Successfully! Record has been updated."
                }
     */


    @Test
    public void put02() {

        spec.pathParams("first", "update", "second", 21);

        DummyRestApiDataPojo data = new DummyRestApiDataPojo("Ali Can", 111111,
                23, "Perfect image");

        DummyRestApiResponseBodyPojo expectedData = new DummyRestApiResponseBodyPojo("success", data,
                "Successfully! Record has been updated.");

        Response response = given().spec(spec).contentType(ContentType.JSON).body(data).put("{first}/{second}");



        DummyRestApiResponseBodyPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(),)

                /*
        assertEquals(200, response.getStatusCode());

        assertEquals(expectedData.getStatus(), actualData.getStatus());

        assertEquals(expectedData.getData().getEmployee_name(), actualData.getData().getEmployee_name());
        assertEquals(expectedData.getData().getEmployee_salary(), actualData.getData().getEmployee_salary());
        assertEquals(expectedData.getData().getEmployee_age(), actualData.getData().getEmployee_age());
        assertEquals(expectedData.getData().getProfile_image(), actualData.getData().getProfile_image());

        assertEquals(expectedData.getMessage(), actualData.getMessage());

                 */
    }
}