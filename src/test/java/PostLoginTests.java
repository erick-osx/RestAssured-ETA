import baseTest.TestBase;
import io.restassured.response.Response;
import models.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostLoginTests extends TestBase {

    private static  User validUser5, validUser6, invalidUser;

    @BeforeClass
    public static void generateTestData(){
        validUser5 = new User("Jack", "jck@oxe.com", "1298a", "true");
        //validUser5.registerUserRequest();
        validUser6 = new User("Bigorna", "bn@oxe.com", "1e298a", "true");
        invalidUser = new User("Jack", "jck@oxe.com", "1292342a", "true");

    }

    @Test
    public void shouldReturnSuscessMessageAuthTokenAndStatus200(){
        Response loginResponse = validUser5.authenticateUser();
        loginResponse.then().assertThat().
                statusCode(200).
                body("message", equalTo("Login realizado com sucesso"));
    }

    @Test
    public void shouldReturnSuccessMessageAuthTokenAndStatus200(){

        given().
                spec(spec).
                header("Content-Type", "application/json").

        and().
                body(validUser6.getUserCredentials()).

        when().
                post("login").

        then().
                assertThat().
                statusCode(200).
                body("message", equalTo("Login realizado com sucesso")).
              // body("authorization", contains("Bearer")).
                body("authorization", notNullValue()).log().body();

    }
    @Test
    public void shouldReturnSuccessMessageAuthTokenAndStatus400(){

        given().
                spec(spec).
                header("Content-Type", "application/json").

        and().
                body(invalidUser.getUserCredentials()).

        when().
                post("login").

        then().
                assertThat().
                statusCode(200).
                body("message", equalTo("Login realizado com sucesso")).
                // body("authorization", contains("Bearer")).
                body("authorization", notNullValue()).log().body();

    }

}
