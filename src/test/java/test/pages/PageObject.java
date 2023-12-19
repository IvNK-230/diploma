package test.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import test.test_data.DataHelper;
import test.test_data.MonthAndYear;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class PageObject {

    // адреса элементов на странице
    private final SelenideElement buyButton = $(byText("Купить"));
    private final SelenideElement creditButton = $(byText("Купить в кредит"));
    private final SelenideElement filedOfCardNumber = $(By.xpath("//input[@placeholder='0000 0000 0000 0000']"));
    private final SelenideElement filedOfOwner = $(By.xpath("//span[text()='Владелец']/../span[@class='input__box']/input"));
    private final SelenideElement filedOfCvv = $(By.xpath("//input[@placeholder='999']"));
    private final SelenideElement enterButton = $(By.xpath("//span[text()='Продолжить']/../.."));
    private final SelenideElement successNotification = $(By.xpath("//div[text()='Успешно']"));
    private final SelenideElement failNotification = $(By.xpath("//div[text()='Ошибка']"));
    private final SelenideElement filedOfMonth = $(By.xpath("//input[@placeholder='08']"));
    private final SelenideElement filedOfYear = $(By.xpath("//input[@placeholder='22']"));

    // сообщения
    public void successNotification() {
        successNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void failNotification() {
        failNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void invalidFormatMessage() {
        $(By.xpath("//span[text()='Неверный формат']")).shouldBe(Condition.visible);
    }

    public void invalidExpirationDateMessage() {
        $(By.xpath("//span[text()='Неверно указан срок действия карты']")).shouldBe(Condition.visible);
    }

    public void ExpirationDateMessage() {
        $(By.xpath("//span[text()='Истёк срок действия карты']")).shouldBe(Condition.visible);
    }

    public void invalidFormat() {
        $(By.xpath("(//*[text()='Неверный формат'])")).shouldBe(Condition.visible);
    }

    public void essentialField() {
        $(By.xpath("//span[text()='Поле обязательно для заполнения']")).shouldBe(Condition.visible);
    }

    public void fillWithEmptyFields() {
        buyButton.click();
        enterButton.click();
        $(By.xpath("(//*[text()='Неверный формат'])[1]")).shouldBe(Condition.visible);
        $(By.xpath("(//*[text()='Неверный формат'])[2]")).shouldBe(Condition.visible);
        $(By.xpath("(//*[text()='Неверный формат'])[3]")).shouldBe(Condition.visible);
        $(By.xpath("(//*[text()='Неверный формат'])[4]")).shouldBe(Condition.visible);
        $(By.xpath("//span[text()='Поле обязательно для заполнения']")).shouldBe(Condition.visible);
    }

    public void openFormAfterClickOnButtonBuy() {

        buyButton.click();
        final SelenideElement name = $(By.xpath("//h3[text()='Оплата по карте']"));
        name.shouldBe(Condition.visible);

    }

    public void openFormAfterClickOnButtonCredit() {
        creditButton.click();
        final SelenideElement name = $(By.xpath("//h3[text()='Кредит по данным карты']"));
        name.shouldBe(Condition.visible);
    }


    public void pressTheBuyButton() {
        buyButton.click();
    }

    public void pressTheCreditButton() {
        creditButton.click();
    }

    public void fillTheFieldOfMonth(String month) {
        filedOfMonth.setValue(month);
    }

    public void fillTheFieldOfYear(String year) {
        filedOfYear.setValue(year);
    }

    public void pressTheEnterButton() {
        enterButton.click();
    }

    public void fillTheFiledOfCardNumber(String cardNumber) {
        filedOfCardNumber.setValue(cardNumber);
    }

    public void fillTheFieldOfName(String name) {
        filedOfOwner.setValue(name);
    }

    public void fillTheFieldOfCvv(String cvv) {
        filedOfCvv.setValue(cvv);
    }

}
