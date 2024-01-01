package test.test_data;

public class CardInformation {

    private String number;
    private String year;
    private String month;
    private String holder;
    private String cvc;

    public CardInformation(String number, String year, String month, String holder, String cvc) {
        this.number = number;
        this.month = month;
        this.year = year;
        this.holder = holder;
        this.cvc = cvc;
    }

    public String getNumber() {
        return number;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public String getHolder() {
        return holder;
    }

    public String getCvc() {
        return cvc;
    }
}


