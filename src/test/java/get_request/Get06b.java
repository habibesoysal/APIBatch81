package get_request;

import base_url.ReqresBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

public class Get06b extends ReqresBaseUrl {
     /*
   Given
          https://reqres.in/api/unknown/
   When
        I send GET Request to the URL
   Then

        1)Status code is 200
        2)Print all pantone_values
        3)Print all ids greater than 3 on the console
          Assert that there are 3 ids greater than 3
        4)Print all names whose ids are less than 3 on the console
          Assert that the number of names whose ids are less than 3 is 2
*/

    @Test
    public void get06() {

        spec.pathParam("first", "unknown");
        Response response = given().spec(spec).when().get("/{first}");
        //response.prettyPrint();

        // 1)Status code is 200
        assertEquals(200, response.statusCode());

        // 2)Print all pantone_values
        JsonPath jsonPath = response.jsonPath();
        jsonPath.getList("data.pantone_value");

        /*
        3)Print all ids greater than 3 on the console
        Assert that there are 3 ids greater than 3
        */
        List<Integer> ids = jsonPath.getList("data.findAll{it.id>3}.id");
        System.out.println("ids = " + ids);
        assertEquals(3, ids.size());

        /*
        4)Print all names whose ids are less than 3 on the console
        Assert that the number of names whose ids are less than 3 is 2
        */
        List<String> names = jsonPath.getList("data.findAll{it.id<3}.name");
        assertEquals(2, names.size());
        System.out.println("names = " + names);
    }
}