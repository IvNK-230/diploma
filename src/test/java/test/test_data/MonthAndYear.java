package test.test_data;

import test.test_data.DataHelper;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MonthAndYear {

    private final SelenideElement filedOfMonth = $(By.xpath("//input[@placeholder='08']"));
    private final SelenideElement filedOfYear = $(By.xpath("//input[@placeholder='22']"));

    String currentYear = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
    String currentMonth = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));

    @BeforeEach
    void setup() {
        open("http://localhost:8080");
    }

    public void validFillTheMonthAndYearFields() {

        var getYear = DataHelper.getYear();
        var getMonth = DataHelper.getMonth();

        if (getYear == currentYear) {
            if (getMonth == "12") {
                filedOfMonth.setValue(getMonth);
                var finalYear = Integer.parseInt(getYear) + 1;
                filedOfYear.setValue(String.valueOf(finalYear));
            } else {
                filedOfYear.setValue(getYear);
                var finalMonth = Integer.parseInt(currentMonth) + 1;
                filedOfMonth.setValue(String.valueOf(finalMonth));
            }
        } else {
            filedOfYear.setValue(getYear);
            filedOfMonth.setValue(getMonth);
        }
    }

    public void fillTheExpiredInThisYearValidityPeriod() {

        filedOfYear.setValue(currentYear);
        var getMonth = DataHelper.getMonth();

        if (Integer.parseInt(getMonth) < Integer.parseInt(currentMonth)) {
            filedOfMonth.setValue(getMonth);
        } else {
            var lastMonth = Integer.parseInt(currentMonth) - 1;
            filedOfMonth.setValue(String.valueOf(lastMonth));
        }
    }

    public void fillTheExpiredValidPeriod() {

        filedOfMonth.setValue(DataHelper.getMonth());
        var year = Integer.parseInt(currentYear);
        int min = 10;
        int max = year;
        int diff = max - min;
        Random random = new Random();
        int i = random.nextInt(diff);
        i += min;
        filedOfYear.setValue(String.valueOf(i));
    }

    public void fillWithFutureDate() {

        filedOfMonth.setValue(DataHelper.getMonth());
        var year = Integer.parseInt(currentYear);
        int min = year + 7;
        int max = 99;
        int diff = max - min;
        Random random = new Random();
        int i = random.nextInt(diff);
        i += min;
        filedOfYear.setValue(String.valueOf(i));
    }

}
