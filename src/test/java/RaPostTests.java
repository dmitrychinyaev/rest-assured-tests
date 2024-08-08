import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reg.Register;
import reg.SuccessReg;
import spec.Specifications;

import static io.restassured.RestAssured.given;

public class RaPostTests {

    private final static String URL = "https://reqres.in/";

    @Test
    public void testPost(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        Register user = new Register("eve.holt@reqres.in","pistol");
        SuccessReg successUserReg = given()
                .body(user)
                .when()
                .post("api/register")
                .then()
                .log().all()
                .extract().as(SuccessReg.class);
        Assertions.assertNotNull(successUserReg.getId());
        Assertions.assertNotNull(successUserReg.getToken());
    }
}
