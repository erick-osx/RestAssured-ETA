package models;

import models.User;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;

public class Produto {
    public String nome;
    public String preco;
    public String descricao;
    public String quantidade;
    public String prodID;

    public Produto(String nome, String preco, String descricao, String quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.quantidade = quantidade;
    }

    public void setProdID(String prodID) {
        this.prodID = prodID;
    }
    private static User validUser5;

    public Response registerProdRequest(RequestSpecification spec) {

        validUser5 = new User("Jacsk", "jck@soxe.com", "1w298a", "true");
        validUser5.registerUserRequest(spec);
        validUser5.authenticateUser();
        String authToken = validUser5.getAuthToken();

        JSONObject productJson = new JSONObject();
        productJson.put("nome", this.nome);
        productJson.put("preco", this.preco);
        productJson.put("descricao", this.descricao);
        productJson.put("quantidade", this.quantidade);

        Response registerProdResponse =
                given().
                        spec(spec).
                        header("Content-Type", "application/json").
                        header("Authorization",authToken).

                and().
                        body(productJson.toJSONString()).
                when().
                        post("produtos/");

        JsonPath jsonPathEvaluator = registerProdResponse.jsonPath();
        setProdID(jsonPathEvaluator.get("_id"));

        return registerProdResponse;
    }
}


