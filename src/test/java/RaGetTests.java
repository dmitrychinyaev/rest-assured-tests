import org.junit.jupiter.api.Test;
import pojo.UserData;
import spec.Specifications;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.hasItems;

public class RaGetTests {
    private final static String URL = "https://reqres.in/";

    @Test
    public void testFindItemsWhereIdGreaterThan10(){
        when().
                get(URL+"api/users?page=2").
        then().
                body("data.findAll { it.id > 10 }.first_name", hasItems("George", "Rachel"));
    }

    @Test
    public void testGetToList(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        //1 способ сравнивать значения напрямую из экземпляров класса
        List<UserData> users = given()
                .when()
                .get("api/users?page=2")
                .then()
                .log().all()
                .extract().body().jsonPath().getList("data", UserData.class);
    }


}
