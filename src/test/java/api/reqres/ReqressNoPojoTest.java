package api.reqres;

import api.reqres.spec.Specifications;
import com.sun.istack.NotNull;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Чтобы не создавать много pojo классов нужно использовать интерфейс response
 * Получить его из запроса, конвертировать обратно в json и доставать данные уже из него
 */
public class ReqressNoPojoTest {
    private final static String URL = "https://reqres.in/";

    @Test
    public void checkAvatars() {
        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                                             Specifications.responseSpecificationUnique(200));

        Response response = given()
                .when()
                .get("api/users?page=2")
                .then().log().all()
                .body("page", equalTo(2))
                .body("data.id", notNullValue())
                .body("data.email", notNullValue())
                .body("data.avatar", notNullValue())
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<String> avatars = jsonPath.get("data.avatar");
        List<String> emails = jsonPath.get("data.email");
        List<Integer> ids = jsonPath.get("data.id");

        for(int i = 0; i < avatars.size(); i++) {
            Assert.assertTrue(avatars.get(i).contains(ids.get(i).toString()));
        }
        Assert.assertTrue(emails.stream().allMatch(x-> x.endsWith("@reqres.in")));
    }

    /**
     * Можно проверять в теле запроса RestAssured
     */
    @Test
    public void successRegisterTest() {
        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                                             Specifications.responseSpecificationOK200());
        Map<String, String> dataRegister = new HashMap<>();
        dataRegister.put("email", "eve.holt@reqres.in");
        dataRegister.put("password", "pistol");
        given()
                .body(dataRegister)
                .when()
                .post("api/register")
                .then().log().all()
                .body("id", equalTo(4))
                .body("token", equalTo("QpwL5tke4Pnpja7X4"));
    }

    /**
     * Также проверять и с помощью Response и JsonPath
     */
    @Test
    public void unSuccessRegisterTest() {
        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.responseSpecificationError400());
        Map<String, String> dataRegister = new HashMap<>();
        dataRegister.put("email", "sydney@fife");
        Response response = given()
                .body(dataRegister)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals("Missing password", jsonPath.get("error"));
    }
}

