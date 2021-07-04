package produto;

import baseTest.TestBase;
import models.User;
import io.restassured.response.Response;
import models.Produto;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;


public class postProdutoTest extends TestBase {

    private static  Produto validProd;


    @BeforeClass
    public void generateTestData(){
        validProd = new Produto("Pesfn", "542", "asgRed color", "2");
    }


    @AfterClass
    public void removeTestData(){
       // validProd.deleteUserRequest(spec);
    }

    @Test
    public void shouldAddNewProdAndStatus201(){

        Response  post_ProdResponse = validProd.registerProdRequest(spec);
        post_ProdResponse.
                then().
                assertThat().
                statusCode(201).
                body("message", equalTo("Cadastro realizado com sucesso"));

    }
}

