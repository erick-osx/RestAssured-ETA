import baseTest.TestBase;
import models.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.AfterClass;


public class PostUserTests extends TestBase {

    private static  User validUser87;

    @BeforeClass
    public static void generateTestData(){
        validUser87 = new User("fseenny", "feeee@oxe.com", "1234", "true");

    }

    @AfterClass
    public void removeTestData(){
        validUser87.deleteUserRequest(spec);
    }

    @Test
    public void shouldAddNewUserAndStatus201(){

        Response  post_UserResponse = validUser87.registerUserRequest(spec);
        post_UserResponse.
        then().
                assertThat().
                statusCode(201).
                body("message", equalTo("Cadastro realizado com sucesso"));

    }
}
