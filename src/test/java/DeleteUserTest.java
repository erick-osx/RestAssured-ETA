import baseTest.TestBase;
import io.restassured.response.Response;
import models.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DeleteUserTest extends TestBase {


    private static User validUser8;

    @BeforeClass
    public void generateTestData(){
        validUser8 = new User("Uyz", "uyz@oxe.com", "12w98a", "true");
        validUser8.registerUserRequest(spec);

    }

    @Test
    public void shouldRemoveUserReturnSuccessMessageAndStatus200(){

//        given().
//                spec(spec).
//                header("Content-Type", "application/json").
//        when().
//                delete("usuarios/"+validUser8.userID).
//
//        then().
//                assertThat().
//                statusCode(200).
//                body("message", equalTo("Registro excluído com sucesso"));
        Response deleteUserResponse = validUser8.deleteUserRequest(spec);
        deleteUserResponse.
                then().
                    assertThat().
                    statusCode(200).
                    body("message", equalTo("Registro excluído com sucesso"));

    }
}
