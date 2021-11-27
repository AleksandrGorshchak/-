// импортируем RestAssured
import io.restassured.RestAssured;
// импортируем Before
import io.restassured.response.Response;
import org.junit.Before;
// импортируем Test
import org.junit.Test;
// дополнительный статический импорт нужен, чтобы использовать given(), get() и then()
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;


public class ApiPutTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-mesto.praktikum-services.ru";
    }
    @Test
    public void createNewPlaceAndCheckResponse(){
        File json = new File("src/test/java/Tests/newCard.json");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .auth().oauth2("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MTRmNDY5MzkyYWE5NTAwM2Q2MDEyOGIiLCJpYXQiOjE2MzY2NjA2NzcsImV4cCI6MTYzNzI2NTQ3N30.BR8-7o_h-S04Oo3IVWOH0esS-0Agh0mYPTRpIjgL1TM")
                        .and()
                        .body(json)
                        .when()
                        .post("/api/cards");
        response.then().assertThat().body("data._id", notNullValue())
                .and()
                .statusCode(201);
    }
    /*
     Передать JSON в тело запроса через строковую переменную, а не в файле:
    Если в строке есть кавычки, их нужно экранировать — добавить перед ними символ \
     */
    @Test
    public void createNewPlaceAndCheckResponse2(){
        String json = "{\"name\": \"Очень интересное место\", \"link\": \"https://code.s3.yandex.net/qa-automation-engineer/java/files/paid-track/sprint1/photoSelenide.jpg\"}";;
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .auth().oauth2("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MTRmNDY5MzkyYWE5NTAwM2Q2MDEyOGIiLCJpYXQiOjE2MzY2NjA2NzcsImV4cCI6MTYzNzI2NTQ3N30.BR8-7o_h-S04Oo3IVWOH0esS-0Agh0mYPTRpIjgL1TM")
                        .and()
                        .body(json)
                        .when()
                        .post("/api/cards");
        response.then().assertThat().body("data._id", notNullValue())
                .and()
                .statusCode(201);
    }

    // Представь, что у ручки /api/cards есть метод PATCH. Тогда получился бы такой код:
    @Test
    public void Patch(){
        File json = new File("src/test/java/Tests/updateProfile.json");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .auth().oauth2("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MTRmNDY5MzkyYWE5NTAwM2Q2MDEyOGIiLCJpYXQiOjE2MzY2NjA2NzcsImV4cCI6MTYzNzI2NTQ3N30.BR8-7o_h-S04Oo3IVWOH0esS-0Agh0mYPTRpIjgL1TM")
                        .and()
                        .body(json)
                        .when()
                        .patch("api/users/me");
        response.then().assertThat().body("data.name", equalTo("Василий Васильев"))
                .and()
                .statusCode(200);
    }
    //************** Allure репорт:**************
    String bearerToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MTRmNDY5MzkyYWE5NTAwM2Q2MDEyOGIiLCJpYXQiOjE2MzY2NjA2NzcsImV4cCI6MTYzNzI2NTQ3N30.BR8-7o_h-S04Oo3IVWOH0esS-0Agh0mYPTRpIjgL1TM";

    @Test
    public void getMyInfoStatusCode() {
        given()
                .auth().oauth2(bearerToken)
                .get("/api/users/me")
                .then().statusCode(200);
    }

    @Test
    public void checkUserName() {
        given()
                .auth().oauth2(bearerToken)
                .get("/api/users/me")
                .then().assertThat().body("data.name",equalTo("89650100100@mail.ru"));
    }

    @Test
    public void checkUserNameAndPrintResponseBody() {

        Response response =given().auth().oauth2(bearerToken).get("/api/users/me");
        // отправили запрос и сохранили ответ в переменную response - экземпляр класса Response

        response.then().assertThat().body("data.name",equalTo("89650100100@mail.ru"));
        // проверили, что в теле ответа ключу name соответствует нужное имя пользователя

        System.out.println(response.body().asString()); // вывели тело ответа на экран

    }
    // ************** Конец Allure репорта. **************
}