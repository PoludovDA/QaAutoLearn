package hh;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$x;

public class HhResumePage {
    private final static SelenideElement gender = $x("//span[@data-qa='resume-personal-gender']");
    private final static SelenideElement personalAge = $x("//span[@data-qa='resume-personal-age']");
    private final static SelenideElement personalAddress = $x("//span[@data-qa='resume-personal-address']");
    //ancestor::p - в верх по иерархии ищем тег p
    private final static SelenideElement dataPerson = $x("//span[@data-qa='resume-personal-address']/ancestor::p");
    private final static SelenideElement phone = $x("//div[@data-qa='resume-contacts-phone']//span");

    //Перечисляем ключи к хэш карте
    public static String GENDER = "Пол";
    public static String AGE = "Возраст";
    public static String TOWN = "Город";
    public static String READY_DISLOCATE = "Готовность к переезду";
    public static String CONFIRMED_PHONE = "Подтвержден номер телефона";

    public HhResumePage(String url) {
        Selenide.open(url);
    }

    public String getGender() {
//        if(gender.getText().equals("Мужчина"))
//            return "М";
//        return "Ж";

        //Тернарный оператор иф елс более красивый
        return gender.getText().equals("Мужчина") ? "М" : "Ж";
    }

    public int getAge() {
//        return Integer.parseInt(personalAge.getText().split(" ")[0]);

        //Или через регулярные выражение (почитать про regex)
        //Данное выражение убирает все символы кроме цифр
        return Integer.parseInt(personalAge.getText().replaceAll("[^0-9]", ""));
    }

    public String getAddress() {
        return personalAddress.getText();
    }

    public Boolean isFreeTravel() {
        return !dataPerson.getText().split(", ")[1].equals("не готов к переезду");
    }

    public Boolean isConfirmedPhone() {
        return phone.isDisplayed();
    }

    public Map<String, Object> getAttributes() {
        return new HashMap<String, Object>() {{
            put(GENDER, getGender());
            put(AGE, getAge());
            put(TOWN, getAddress());
            put(READY_DISLOCATE, isFreeTravel());
            put(CONFIRMED_PHONE, isConfirmedPhone());
        }};
    }
}
