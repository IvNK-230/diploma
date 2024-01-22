package test.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.pages.PageObject;
import test.test_data.DataHelper;
import test.test_data.MonthAndYear;
import test.utilities.SQLHelper;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUI {

    @BeforeEach
    void setup() {
        open("http://localhost:8080");
        SQLHelper.deleteTable();
    }


    @Test//Сценарий №1. Отправка формы "Оплата по карте" валидными данными
    public void validTestWithPaymentGate() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber(DataHelper.validCardNumber());
        MonthAndYear fill = new MonthAndYear();
        fill.validFillTheMonthAndYearFields();
        page.fillTheFieldOfName(DataHelper.getName());
        page.fillTheFieldOfCvv(DataHelper.getCVV());
        page.pressTheEnterButton();
        page.successNotification();
        assertEquals("APPROVED", SQLHelper.getPaymentStatus());
    }


    @Test//Сценарий №2. Отправка формы "Кредит по данным карты" валидными данными
    public void validTestWithCreditGate() {
        PageObject page = new PageObject();
        page.pressTheCreditButton();
        page.fillTheFiledOfCardNumber(DataHelper.validCardNumber());
        MonthAndYear fill = new MonthAndYear();
        fill.validFillTheMonthAndYearFields();
        page.fillTheFieldOfName(DataHelper.getName());
        page.fillTheFieldOfCvv(DataHelper.getCVV());
        page.pressTheEnterButton();
        page.successNotification();
    }


    @Test//Сценарий №3. Отправка формы "Оплата по карте" невалидными данными только в поле "Номер карты"
    public void testWithInvalidCardNumber() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber(DataHelper.invalidCardNumber());
        MonthAndYear fill = new MonthAndYear();
        fill.validFillTheMonthAndYearFields();
        page.fillTheFieldOfName(DataHelper.getName());
        page.fillTheFieldOfCvv(DataHelper.getCVV());
        page.pressTheEnterButton();
        page.failNotification();
    }


    @Test// Сценарий №4. Отправка формы "Оплата по карте" с картой со статусом DECLINED
    public void testWithDeclinedCard() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber("4444 4444 4444 4442");
        MonthAndYear fill = new MonthAndYear();
        fill.validFillTheMonthAndYearFields();
        page.fillTheFieldOfName(DataHelper.getName());
        page.fillTheFieldOfCvv(DataHelper.getCVV());
        page.pressTheEnterButton();
//      page.failNotification();
        assertEquals("DECLINED", SQLHelper.getPaymentStatus());
    }

    @Test
//Сценарий №5. Отправка формы "Оплата по карте" со значением поля "Месяц" не соответствующее шаблону, одна цифра.
    public void testWithInvalidFormatMonth() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber(DataHelper.randomCardNumber());
        page.fillTheFieldOfMonth(DataHelper.getInvalidFormatMonth());
        page.fillTheFieldOfYear(DataHelper.getYear());
        page.fillTheFieldOfName(DataHelper.getName());
        page.fillTheFieldOfCvv(DataHelper.getCVV());
        page.pressTheEnterButton();
        page.invalidFormatMessage();
    }

    @Test//Сценарий №6. Отправка формы "Оплата по карте" с несуществующим значением поля "Месяц"
    public void testWithNonExistentFormatMonth() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber(DataHelper.randomCardNumber());
        page.fillTheFieldOfMonth(DataHelper.getNonExistentFormatMonth());
        page.fillTheFieldOfYear(DataHelper.getYear());
        page.fillTheFieldOfName(DataHelper.getName());
        page.fillTheFieldOfCvv(DataHelper.getCVV());
        page.pressTheEnterButton();
        page.invalidExpirationDateMessage();
    }

    @Test//Сценарий №7. Отправка формы "Оплата по карте" с указанием просроченной в текущем году карты.
    public void testWithExpiredValidityPeriod() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber(DataHelper.randomCardNumber());
        MonthAndYear expiredMonth = new MonthAndYear();
        expiredMonth.fillTheExpiredInThisYearValidityPeriod();
        page.fillTheFieldOfName(DataHelper.getName());
        page.fillTheFieldOfCvv(DataHelper.getCVV());
        page.pressTheEnterButton();
        page.invalidExpirationDateMessage();
    }

    @Test//Сценарий №8 Отправка формы "Оплата по карте" с указанием просроченной в прошлом году карты.
    public void testWithPreviousYear() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber(DataHelper.randomCardNumber());
        MonthAndYear expiredMonth = new MonthAndYear();
        expiredMonth.fillTheExpiredValidPeriod();
        page.fillTheFieldOfName(DataHelper.getName());
        page.fillTheFieldOfCvv(DataHelper.getCVV());
        page.pressTheEnterButton();
        page.ExpirationDateMessage();
    }

    @Test
//Сценарий №9. Отправка формы "Оплата по карте" с указанием карты срок действия которой более 6 лет от текущей даты.
    public void testWithFutureYear() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber(DataHelper.randomCardNumber());
        MonthAndYear expiredMonth = new MonthAndYear();
        expiredMonth.fillWithFutureDate();
        page.fillTheFieldOfName(DataHelper.getName());
        page.fillTheFieldOfCvv(DataHelper.getCVV());
        page.pressTheEnterButton();
        page.invalidExpirationDateMessage();
    }

    @Test//Сценарий №10. Отправка формы "Оплата по карте" со значением поля "Год" не соответствующее шаблону, одна цифра
    public void testWithInvalidFormatYear() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber(DataHelper.randomCardNumber());
        page.fillTheFieldOfMonth(DataHelper.getMonth());
        page.fillTheFieldOfYear(DataHelper.getInvalidFormatYear());
        page.fillTheFieldOfName(DataHelper.getName());
        page.fillTheFieldOfCvv(DataHelper.getCVV());
        page.pressTheEnterButton();
        page.invalidFormatMessage();
    }

    @Test
//Сценарий №11. Отправка формы "Оплата по карте" со значением поля "CVC/CVV" не соответствующее шаблону, одна цифра
    public void testWithInvalidFormatCvv() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber(DataHelper.randomCardNumber());
        page.fillTheFieldOfMonth(DataHelper.getMonth());
        page.fillTheFieldOfYear(DataHelper.getYear());
        page.fillTheFieldOfName(DataHelper.getName());
        page.fillTheFieldOfCvv(DataHelper.getInvalidFormatCVV());
        page.pressTheEnterButton();
        page.invalidFormatMessage();
    }

    @Test//Сценарий №12. Отправка формы "Оплата по карте" с пустыми полями
    public void testWithEmptyFields() {
        PageObject page = new PageObject();
        page.fillWithEmptyFields();
    }

    @Test//Сценарий №13. Отправка формы "Оплата по карте" с пустым значением поля "Номер карты"
    public void testWithEmptyFieldOfCardNumber() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFieldOfMonth(DataHelper.getMonth());
        page.fillTheFieldOfYear(DataHelper.getYear());
        page.fillTheFieldOfName(DataHelper.getName());
        page.fillTheFieldOfCvv(DataHelper.getCVV());
        page.pressTheEnterButton();
        page.invalidFormat();
    }

    @Test//Сценарий №14. Отправка формы "Оплата по карте" с пустым значением поля "Месяц"
    public void testWithEmptyFieldOfMonth() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber(DataHelper.randomCardNumber());
        page.fillTheFieldOfYear(DataHelper.getYear());
        page.fillTheFieldOfName(DataHelper.getName());
        page.fillTheFieldOfCvv(DataHelper.getCVV());
        page.pressTheEnterButton();
        page.invalidFormat();
    }

    @Test//Сценарий №15. Отправка формы "Оплата по карте" с пустым значением поля "Год"
    public void testWithEmptyFieldOfYear() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber(DataHelper.randomCardNumber());
        page.fillTheFieldOfMonth(DataHelper.getMonth());
        page.fillTheFieldOfName(DataHelper.getName());
        page.fillTheFieldOfCvv(DataHelper.getCVV());
        page.pressTheEnterButton();
        page.invalidFormat();
    }

    @Test//Сценарий №16. Отправка формы "Оплата по карте" с пустым значением поля "Владелец"
    public void testWithEmptyFieldOfOwner() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber(DataHelper.randomCardNumber());
        page.fillTheFieldOfMonth(DataHelper.getMonth());
        page.fillTheFieldOfYear(DataHelper.getYear());
        page.fillTheFieldOfCvv(DataHelper.getCVV());
        page.pressTheEnterButton();
        page.essentialField();

    }

    @Test//Сценарий №17. Отправка формы "Оплата по карте" с пустым значением поля "CVC/CVV"
    public void testWithEmptyFieldOfCvv() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber(DataHelper.randomCardNumber());
        page.fillTheFieldOfMonth(DataHelper.getMonth());
        page.fillTheFieldOfYear(DataHelper.getYear());
        page.fillTheFieldOfName(DataHelper.getName());
        page.pressTheEnterButton();
        page.invalidFormat();
    }

}