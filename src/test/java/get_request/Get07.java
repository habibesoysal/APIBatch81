package get_request;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;


import java.util.List;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.*;

public class Get07 extends JsonplaceholderBaseUrl {
     /*
      Given
       https://jsonplaceholder.typicode.com/todos
When
    I send GET Request to the URL == > URL'e Get Request gonderin
Then
    1)Status code is 200 == > Status kodu 200 olmali
    2)Print all ids greater than 190 on the console ==> id si 190 dan buyuk olanlari konsola yazdirin
      Assert that there are 10 ids greater than 190 == > 10 tane id nin 190 dan buyuk oldugunu dogrulayin
    3)Print all userIds whose ids are less than 5 on the console ==> id si 5 den kucuk olan tum userid lerini konsolunu yazdirin
      Assert that the number of userIds whose ids are less than 5 is 4 ==> id si 5 den kucuk olan 4 tane userId oldugunu dogrulayin
    4)Print all titles whose ids are less than 5 ==> ıd si 5 den kucuk olan tum basliklari yazdirin
      Assert that "delectus aut autem" is one of the titles whose id is less than 5 ==> id si 5 den kucuk olan datalarin birinin
      basliginin "delectus aut autem" icerdigini dogrulayin
   */

    @Test
    public void get01() {
        // 1) Set the URL
        spec.pathParam("first", "todos");

        // 2) Set the expected data
        // Bu casede olmadığı için geçiyoruz

        // 3) Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}");
        //response.prettyPrint();

        // 4) So Assertion

         /*
         1)Status code is 200 == > Status kodu 200 olmali
         */
        response.then().assertThat().statusCode(200);

        /*
        2)Print all ids greater than 190 on the console ==> id si 190 dan buyuk olanlari konsola yazdirin
        Assert that there are 10 ids greater than 190 == > 10 tane id nin 190 dan buyuk oldugunu dogrulayin
        */
        JsonPath json = response.jsonPath();
        List<Integer> idler = json.getList("findAll{it.id>190}.id"); // Groovy Language : Java temelli prog dili.
        System.out.println("ID'si 190'dan büyük olanlar : " + idler);
        assertEquals("ID 190'dan büyük olan eşleşmedi", 10, idler.size());

        /*
        3)Print all userIds whose ids are less than 5 on the console ==> id si 5 den kucuk olan tum userid lerini konsolunu yazdirin
        Assert that the number of userIds whose ids are less than 5 is 4 ==> id si 5 den kucuk olan 4 tane userId oldugunu dogrulayin
         */
        List<Integer> userIdler = json.getList("findAll{it.id<5}.userId");
        System.out.println("ID'si 5'ten küçük olan UserID'ler : " + userIdler);
        assertEquals("ID'si 5'ten küçük olan userID'ler 4 tane değil", 4, userIdler.size());

        /*
        4)Print all titles whose ids are less than 5 ==> ıd si 5 den kucuk olan tum basliklari yazdirin
        Assert that "delectus aut autem" is one of the titles whose id is less than 5 ==> id si 5 den kucuk olan datalarin birinin
        basliginin "delectus aut autem" icerdigini dogrulayin
         */
        List<String> titles = json.getList("findAll{it.id<5}.title");
        System.out.println("ID'si 5'ten küçük olan title'lar : " + titles);
        assertTrue("ID'si 5'ten küçük olan titlelardan herhangi biri \"delectus aut autem\" içermemektedir",
                titles.contains("delectus aut autem"));

        assertTrue("ID'si 5'ten küçük olan titlelardan herhangi biri \"delectus aut autem\" içermemektedir",
                titles.stream().anyMatch(t -> t.equals("delectus aut autem")));
    }
}