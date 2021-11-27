
// импортируем RestAssured
import io.restassured.RestAssured;
// импортируем Before
import io.restassured.response.Response;
import org.junit.Before;
// импортируем Test
import org.junit.Test;
// дополнительный статический импорт нужен, чтобы использовать given(), get() и then()
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class ApiTest {
    // аннотация Before показывает, что метод будет выполняться перед каждым тестовым методом
    @Before
    public void setUp() {
        // повторяющуюся для разных ручек часть URL лучше записать в переменную в методе Before
        // если в классе будет несколько тестов, указывать её придётся только один раз
        RestAssured.baseURI = "https://qa-mesto.praktikum-services.ru";
    }

    // создаём метод автотеста
    @Test
    public void getMyInfoStatusCode() {
        // метод given() помогает сформировать запрос
        given()
                // указываем протокол и данные авторизации
                .auth().oauth2("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MTRmNDY5MzkyYWE5NTAwM2Q2MDEyOGIiLCJpYXQiOjE2MzY2NjA2NzcsImV4cCI6MTYzNzI2NTQ3N30.BR8-7o_h-S04Oo3IVWOH0esS-0Agh0mYPTRpIjgL1TM")
                // отправляем GET-запрос с помощью метода get, недостающую часть URL (ручку) передаём в него в качестве параметра
                .get("/api/users/me")
                // проверяем, что статус-код ответа равен 200
                .then().statusCode(200);
    }
    /*
    Посмотри на код теста, который проверяет имя пользователя. Он состоит из запроса и ответа.
    Программа посылает запрос, получает имя пользователя и сравнивает его со значением, которое задано в автотесте:
     */
    @Test
    public void checkUserName() {
        given()
                .auth().oauth2("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MTRmNDY5MzkyYWE5NTAwM2Q2MDEyOGIiLCJpYXQiOjE2MzY2NjA2NzcsImV4cCI6MTYzNzI2NTQ3N30.BR8-7o_h-S04Oo3IVWOH0esS-0Agh0mYPTRpIjgL1TM")
                .get("/api/users/me")
                .then().assertThat().body("data.name", equalTo("89650100100@mail.ru"));
    }
    /*
    Можно сохранить ответ на запрос в переменную и дальше работать
    уже с ней — например, вывести в консоль. Смотри:
     */
    @Test
    public void checkUserNameAndPrintResponseBody() {

        // отправляет запрос и сохраняет ответ в переменную response, экзмепляр класса Response
        Response response = given().auth().oauth2("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MTRmNDY5MzkyYWE5NTAwM2Q2MDEyOGIiLCJpYXQiOjE2MzY2NjA2NzcsImV4cCI6MTYzNzI2NTQ3N30.BR8-7o_h-S04Oo3IVWOH0esS-0Agh0mYPTRpIjgL1TM").get("/api/users/me");
        // проверяет, что в теле ответа ключу name соответствует нужное имя пользователя
        response.then().assertThat().body("data.name",equalTo("89650100100@mail.ru"));
        // выводит тело ответа на экран
        System.out.println(response.body().asString());

    }
}
