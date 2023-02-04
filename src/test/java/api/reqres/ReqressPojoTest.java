package api.reqres;

import api.reqres.colors.ColorsData;
import api.reqres.registration.Register;
import api.reqres.registration.SuccessRegistration;
import api.reqres.registration.UnSuccessRegistration;
import api.reqres.spec.Specifications;
import api.reqres.users.SuccessUserUpdate;
import api.reqres.users.UserData;
import api.reqres.users.UserUpdate;
import org.junit.Assert;
import org.junit.Test;

import java.time.Clock;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class ReqressPojoTest {
    private final static String URL = "https://reqres.in/";

    /**
     * Возвращаемый результат записывается в pojo класс UserData
     * x - счетчик элементов в потоке в данном случае экземпляр
     * класса. Функция map из stream служит для преобразования
     * типов данных.
     */
    @Test
    public void checkAvatars() {
        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                                             Specifications.responseSpecificationOK200());

        List<UserData> users = given()
                .when()
                .get("api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);

        //Запрос без спецификаций
//        List<UserData> users = given()
//                .when()
//                .contentType(ContentType.JSON)
//                .get(URL + "api/users?page=2")
//                .then().log().all()
//                .extract().body().jsonPath().getList("data", UserData.class);

        //С помощью stream api:
        users.forEach(x-> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));
        Assert.assertTrue(users.stream().allMatch(x-> x.getEmail().endsWith("@reqres.in")));

        //Способ понятнее
        List<String> avatars = users.stream().map(UserData::getAvatar).collect(Collectors.toList());
        List<String> ids = users.stream().map(x-> x.getId().toString()).collect(Collectors.toList());
        List<String> emails = users.stream().map(UserData::getEmail).collect(Collectors.toList());

        for(int i = 0; i < users.size(); i++) {
            Assert.assertTrue(avatars.get(i).contains(ids.get(i)));
            Assert.assertTrue(emails.get(i).endsWith("@reqres.in"));
        }
    }

    @Test
    public void successRegTest() {
        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                                             Specifications.responseSpecificationOK200());
        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";
        Register registerData = new Register("eve.holt@reqres.in", "pistol");
        SuccessRegistration successRegistration = given()
                .body(registerData)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().as(SuccessRegistration.class);

        Assert.assertNotNull(successRegistration.getId());
        Assert.assertNotNull(successRegistration.getToken());
        Assert.assertEquals(id, successRegistration.getId());
        Assert.assertEquals(token, successRegistration.getToken());
    }

    @Test
    public void unSuccessRegTest() {
        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.responseSpecificationError400());
        String error = "Missing password";
        Register registerData = new Register("sydney@fife", "");
        UnSuccessRegistration unSuccessRegistration = given()
                .body(registerData)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().as(UnSuccessRegistration.class);
        Assert.assertEquals(error, unSuccessRegistration.getError());
    }

    @Test
    public void checkYearsTest() {
        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.responseSpecificationOK200());
        List<ColorsData> colorsData = given()
                .when()
                .get("api/unknown")
                .then().log().all()
                .extract().body().jsonPath().getList("data", ColorsData.class);
        List<Integer> years = colorsData.stream().map(ColorsData::getYear).collect(Collectors.toList());
        List<Integer> sortedYears = years.stream().sorted().collect(Collectors.toList());
        Assert.assertEquals(sortedYears, years);
    }

    @Test
    public void deleteUserTest() {
        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.responseSpecificationUnique(204));
        given()
                .when()
                .delete("api/users/2")
                .then();
    }

    @Test
    public void checkTime() {
        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.responseSpecificationUnique(200));
        UserUpdate userUpdate = new UserUpdate("morpheus", "zion resident");
        SuccessUserUpdate successUserUpdate = given()
                .body(userUpdate)
                .when()
                .put("api/users/2")
                .then().log().all()
                .extract().as(SuccessUserUpdate.class);
        String regex = "(.{8})$";
        String localTime = Clock.systemUTC().instant().toString().replaceAll(regex, "");
        String serverTime = successUserUpdate.getUpdatedAt().replaceAll(regex, "");
        Assert.assertEquals(localTime, serverTime);
    }
}
