package test.test_data;

import com.github.javafaker.CreditCardType;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataHelper {


    public static String randomCardNumber() {
        Faker faker = new Faker();
        String cardNumber = faker.finance().creditCard(CreditCardType.MASTERCARD);
        return cardNumber;
    }

    public static String validCardNumber() {
        String validNumber = "4444 4444 4444 4441";
        return validNumber;
    }

    public static String invalidCardNumber() {
        String invalidNumber = "4444 4444 4444 4442";
        return invalidNumber;
    }

    // генерация валидного номера месяца
    public static String getMonth() {
        var months = new String[]{
                "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"
        };
        return months[new Random().nextInt(months.length)];
    }

    public static String getInvalidFormatMonth() {
        Random random = new Random();
        var month = random.nextInt(10);
        return String.valueOf(month);
    }

    public static String getNonExistentFormatMonth() {
        var min = 13;
        var max = 100;
        var dif = max - min;

        Random random = new Random();
        var month = random.nextInt(dif);
        return String.valueOf(month);
    }

    public static String getYear() {
        var years = new String[]{
                "24", "25", "26", "27", "28"
        };
        return years[new Random().nextInt(years.length)];

    }

    public static String getInvalidFormatYear() {
        Random random = new Random();
        var year = random.nextInt(10);
        return String.valueOf(year);
    }

    public static String getName() {
        Faker faker = new Faker(new Locale("en"));
        String name = faker.name().fullName();
        return name;
    }

    public static String getCVV() {
        double CVV = Math.random() * 1000;
        return String.valueOf(CVV);
    }

    public static String getInvalidFormatCVV() {
        Random random = new Random();
        var CVV = random.nextInt(5);
        return String.valueOf(CVV);
    }

}
