import baseTest.TestBase;
import models.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterClass;




public class GetUserTests extends TestBase {

    private static  User validUser1, validUser2, validUser3;

    @BeforeClass
    public void generateTestData(){
        validUser1 = new User("Jhonny", "jho@oxe.com", "1234", "true");
        validUser1.registerUserRequest(spec);
        validUser2 = new User("Spike", "spike@oxe.com", "1234", "true");
        validUser2.registerUserRequest(spec);
        validUser3 = new User("Jhonny", "jho@mai.com", "8965", "false");
        validUser3.registerUserRequest(spec);
    }

    @AfterClass
    public void removeTestData(){
        validUser1.deleteUserRequest(spec);
        validUser2.deleteUserRequest(spec);
        validUser3.deleteUserRequest(spec);
    }


    @DataProvider(name = "userData")
    public Object[][] createTestData(){
        return new Object[][]{
                {"?nome="+validUser1.name, 2},
                {"?password="+validUser2.password, 2},
                {"?email="+validUser3.email, 1}
        };
    }

    @Test(dataProvider = "userData")
    public void shouldReturnAllUsersAndStatus200(String query, int totalUsers){

        given().
                spec(spec).

        when().
                get("usuarios"+query).

        then().
                assertThat().
                statusCode(200).
                body("quantidade", equalTo(totalUsers));

    }
}
