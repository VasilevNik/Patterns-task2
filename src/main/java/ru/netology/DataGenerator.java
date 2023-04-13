package ru.netology;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class DataGenerator {

    private final static String activeStatus = "active";
    private final static String blockedStatus = "blocked";
    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();
    private static Gson gson = new Gson();
    private static Faker faker = new Faker(new Locale("en"));


private DataGenerator() {
    }
    private static void registrationUsers(UserRegistration user) {
        given()
                .spec(requestSpec)
                .body(gson.toJson(user))
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }

    public static UserRegistration generateValidActive() {
        UserRegistration user = new UserRegistration(faker.name().username(),
                faker.internet().password(true), activeStatus);
        registrationUsers(user);
        return user;
    }

    public static UserRegistration generateValidBlocked() {
        UserRegistration user = new UserRegistration(faker.name().username(),
                faker.internet().password(true), blockedStatus);
        registrationUsers(user);
        return user;
    }

    public static UserRegistration generateInvalidLogin() {
        String password = faker.internet().password(true);
        UserRegistration userReg = new UserRegistration(faker.name().username(),
                password, activeStatus);
        registrationUsers(userReg);
        return new UserRegistration(faker.name().username(),
                password, activeStatus);
    }

    public static UserRegistration generateInvalidPassword() {
        String login = faker.name().username();
        UserRegistration userReg = new UserRegistration(login,
                faker.internet().password(true), activeStatus);
        registrationUsers(userReg);
        return new UserRegistration(login,
                faker.internet().password(true), activeStatus);
    }

}
