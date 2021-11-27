//import io.qameta.allure.junit4.DisplayName;
//import io.restassured.RestAssured;
//import io.restassured.response.Response;
//import org.junit.Before;
//import org.junit.Test;
//
//
//
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.equalTo;
//
//
//public class RestAssuredGetAllureTest {
//
//    String bearerToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MTRmNDY5MzkyYWE5NTAwM2Q2MDEyOGIiLCJpYXQiOjE2MzY2NjA2NzcsImV4cCI6MTYzNzI2NTQ3N30.BR8-7o_h-S04Oo3IVWOH0esS-0Agh0mYPTRpIjgL1TM";
//
//    @Before
//    public void setUp() {
//        RestAssured.baseURI= "https://qa-mesto.praktikum-services.ru";
//    }
//
//    @Test
//    @DisplayName("Check status code of /users/me") // имя теста
//    public void getMyInfoStatusCode() {
//        given()
//                .auth().oauth2(bearerToken)
//                .get("/api/users/me")
//                .then().statusCode(200);
//    }
//
//    @Test
//    @DisplayName("Check user name") // имя теста
//    public void checkUserName() {
//        given()
//                .auth().oauth2(bearerToken)
//                .get("/api/users/me")
//                .then().assertThat().body("data.name",equalTo("89650100100@mail.ru"));
//    }
//
//    @Test
//    @DisplayName("Check user name and print response body") // имя теста
//    public void checkUserNameAndPrintResponseBody() {
//
//        Response response = given().auth().oauth2(bearerToken).get("/api/users/me");
//        // отправили запрос и сохранили ответ в переменную response - экземпляр класса Response
//
//        response.then().assertThat().body("data.name",equalTo("89650100100@mail.ru"));
//        // проверили, что в теле ответа ключу name соответствует нужное имя пользователя
//
//        System.out.println(response.body().asString()); // вывели тело ответа на экран
//
//    }
//
//}