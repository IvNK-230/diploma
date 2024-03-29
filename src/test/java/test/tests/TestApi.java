package test.tests;

import test.pages.Specification;
import test.test_data.CardInformation;
import org.junit.jupiter.api.Test;
import test.test_data.DataHelper;

import static io.restassured.RestAssured.given;


public class TestApi {

    public class RegresTest {
        private final static String URL = "http://localhost:8080";

    }

    @Test
        // Сценарий №18. Отправка формы "Оплата по карте" валидными данными
    void testWithPaymentGate() {
        Specification.installSpecification(Specification.requestSpec(RegresTest.URL), Specification.response200());
        CardInformation reg = new CardInformation(DataHelper.validCardNumber(), DataHelper.getYear(), DataHelper.getMonth(), DataHelper.getName(), DataHelper.getCVV());
        given()
                .baseUri(RegresTest.URL)
                .body(reg)
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(200);
    }

    @Test
        // Сценарий №19. Отправка формы "Кредит по данным карты" валидными данными
    void testWithCreditGate() {
        Specification.installSpecification(Specification.requestSpec(RegresTest.URL), Specification.response200());
        CardInformation reg = new CardInformation(DataHelper.validCardNumber(), DataHelper.getYear(), DataHelper.getMonth(), DataHelper.getName(), DataHelper.getCVV());
        given()
                .baseUri(RegresTest.URL)
                .body(reg)
                .when()
                .post("/api/v1/credit")
                .then()
                .statusCode(200);
    }

    @Test
        // Сценарий №20. Отправка формы "Оплата по карте" с картой со статусом DECLINED
    void testWithCardDeclined() {
        Specification.installSpecification(Specification.requestSpec(RegresTest.URL), Specification.response400());
        CardInformation reg = new CardInformation("4444 4444 4444 4442", DataHelper.getYear(), DataHelper.getMonth(), DataHelper.getName(), DataHelper.getCVV());
        given()
                .baseUri(RegresTest.URL)
                .body(reg)
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(400);
    }

    @Test
        // Сценарий №21. Отправка формы "Оплата по карте" с валидной картой и пустыми значениями остальных полей
    void testWithPaymentGateValidCardNumberAndEmpty() {
        Specification.installSpecification(Specification.requestSpec(RegresTest.URL), Specification.response400());
        CardInformation reg = new CardInformation(DataHelper.validCardNumber(), "", "", "", "");
        given()
                .baseUri(RegresTest.URL)
                .body(reg)
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(400);
    }

    @Test
        //Сценарий №22. Отправка формы с пустыми значениями всех полей.
    void testWithPaymentGateEmpty() {
        Specification.installSpecification(Specification.requestSpec(RegresTest.URL), Specification.response500());
        CardInformation reg = new CardInformation("", "", "", "", "");
        given()
                .baseUri(RegresTest.URL)
                .body(reg)
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(500);
    }

}