package ru.netology;

import com.codeborne.selenide.Configuration;
import data.DataGenerator;
import entities.UserRegistration;
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

    @Test
    void signInBlockedUser() {
        UserRegistration user = DataGenerator.generateValidBlocked();
        open("http://localhost:9999");
        $("[data-test-id=login] input").setValue(user.getLogin());
        $("[data-test-id=password] input").setValue(user.getPassword());
        $("button[data-test-id=action-login]").click();
        $("[data-test-id=error-notification]").shouldHave(text("Пользователь заблокирован"));
    }

    @Test
    void signInInvalidLogin() {
        UserRegistration user = DataGenerator.generateInvalidLogin();
        open("http://localhost:9999");
        $("[data-test-id=login] input").setValue(user.getLogin());
        $("[data-test-id=password] input").setValue(user.getPassword());
        $("button[data-test-id=action-login]").click();
        $("[data-test-id=error-notification]").shouldHave(text("Ошибка!"))
                .shouldHave(text("Неверно указан логин или пароль"));
    }

    @Test
    void signInInvalidPassword() {
        UserRegistration user = DataGenerator.generateInvalidPassword();
        open("http://localhost:9999");
        $("[data-test-id=login] input").setValue(user.getLogin());
        $("[data-test-id=password] input").setValue(user.getPassword());
        $("button[data-test-id=action-login]").click();
        $("[data-test-id=error-notification]").shouldHave(text("Ошибка!"))
                .shouldHave(text("Неверно указан логин или пароль"));
    }

    @Test
    void signInWithoutPassword() {
        UserRegistration user = DataGenerator.generateValidActive();
        open("http://localhost:9999");
        $("[data-test-id=login] input").setValue(user.getLogin());
        $("button[data-test-id=action-login]").click();
        $("[data-test-id=password]").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void signInUnregisteredUser() {
        UserRegistration user = DataGenerator.generateUnregisteredUser();
        open("http://localhost:9999");
        $("[data-test-id=login] input").setValue(user.getLogin());
        $("[data-test-id=password] input").setValue(user.getPassword());
        $("button[data-test-id=action-login]").click();
        $("[data-test-id=error-notification]").shouldHave(text("Ошибка!"))
                .shouldHave(text("Неверно указан логин или пароль"));
    }
}
