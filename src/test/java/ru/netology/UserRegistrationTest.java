package ru.netology;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class UserRegistrationTest {

    @Test
    void signInTest() {
        UserRegistration user = DataGenerator.generateValidActive();
        open("http://localhost:9999");
        Configuration.holdBrowserOpen = true;
        $("[data-test-id=login] input").setValue(user.getLogin());
        $("[data-test-id=password] input").setValue(user.getPassword());
        $("button[data-test-id=action-login]").click();
        $("h2.heading_theme_alfa-on-white").shouldHave(text("Личный кабинет"));
    }
}
