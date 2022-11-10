package get_request;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestApiDataPojo;
import pojos.DummyRestApiResponseBodyPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get17 extends DummyRestApiBaseUrl {
 /*
    Given
        URL: https://dummy.restapiexample.com/api/v1/employee/1
    When
        User sends GET Request
    Then
        Status code is 200
    And
        "employee_name" is "Tiger Nixon"
    And
        "employee_salary" is 320800
    And
        "employee_age" is 61
    And
        "status" is "success"
    And
        "message" is "Successfully! Record has been fetched."
     */

    @Test
    public void get17() {
        spec.pathParams("first", "employee", "second", 1);

        Response response = given().spec(spec).get("{first}/{second}");

        DummyRestApiDataPojo data = new DummyRestApiDataPojo("Tiger Nixon", 320800, 61, "");
        DummyRestApiResponseBodyPojo expectedData = new DummyRestApiResponseBodyPojo("success", data, "Successfully! Record has been fetched.");

        DummyRestApiResponseBodyPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), DummyRestApiResponseBodyPojo.class);

        assertEquals(200, response.getStatusCode());

        assertEquals(expectedData.getData().getEmployee_name(), actualData.getData().getEmployee_name());
        assertEquals(expectedData.getData().getEmployee_salary(), actualData.getData().getEmployee_salary());
        assertEquals(expectedData.getData().getEmployee_age(), actualData.getData().getEmployee_age());

        assertEquals(expectedData.getStatus(), actualData.getStatus());
        assertEquals(expectedData.getMessage(), actualData.getMessage());
    }
}